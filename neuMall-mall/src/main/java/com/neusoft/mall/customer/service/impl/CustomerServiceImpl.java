package com.neusoft.mall.customer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.admin.accountAdmin.entity.SysUser;
import com.neusoft.common.entity.PageVo;
import com.neusoft.common.response.AppResponse;
import com.neusoft.common.util.CreateMD5;
import com.neusoft.common.util.UUIDUtil;
import com.neusoft.mall.customer.dao.CustomerDao;
import com.neusoft.mall.customer.entity.Customer;
import com.neusoft.mall.customer.entity.CustomerVo;
import com.neusoft.mall.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName: CustomerServiceImpl
 * @Description: 业务层实现逻辑
 * @Author: hanjz
 * @Date: 2019/4/9
 */
@Transactional(rollbackFor = Exception.class)
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerDao customerDao;
    @Autowired
    private RedisTemplate redisTemplate;
/**
 * @Dept：南京软件研发中心
 * @Description： 调dao层 查询客户列表
 * @Author：hanjz
 * @Date: 2019/4/10
 * @Param： 状态，账号，姓名，页码，条数
 * @Return： Map
 */
    @Override
    public AppResponse getCustomerList(CustomerVo customerVo) {
        PageVo pageVo=new PageVo();
        PageHelper.startPage(customerVo.getPageNum(), customerVo.getPageSize());
        List<Customer> cusList=customerDao.getCustomerList(customerVo);
        pageVo.setList(cusList);
        pageVo.setTotalRecords((int) new PageInfo<>(cusList).getTotal());
        return AppResponse.success("查询客户列表成功",pageVo);
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 调dao层 查询客户详情
     * @Author：hanjz
     * @Date: 2019/4/10
     * @Param： 客户id
     * @Return： Map
     */
    @Override
    public AppResponse getCustomerDetail(Customer customer) {
        if(customerDao.getCustomerDetail(customer).isEmpty()){
            return AppResponse.notFound("此客户id不存在");
        }
        return AppResponse.success("查询客户详情成功",customerDao.getCustomerDetail(customer));
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 调dao层 新增客户
     * @Author：hanjz
     * @Date: 2019/4/10
     * @Param： 账号，姓名，密码，是否使用
     * @Return： Map
     */
    @Override
    public AppResponse addCustomer(Customer customer) {
        if(!customerDao.IfExistCustomerNumber(customer).isEmpty()){
           return AppResponse.bizError("此账户已存在");
        }
        SysUser userInfo=(SysUser) redisTemplate.opsForValue().get(customer.getTokenBackend());
        customer.setCustomerId(UUIDUtil.uuidStr());
        customer.setCreatedBy(userInfo.getUserUuid());
        customer.setLastModifiedBy(userInfo.getUserUuid());
        customer.setIsDeleted(0);
        customer.setSortNo(0);
        customer.setVersion(0);
        try{
            String md5=CreateMD5.getMd5(customer.getCustomerPassword());
            customer.setCustomerPassword(md5);
        }catch (Exception e){
            log.error("设置密码失败",e);
            return AppResponse.bizError("密码不符合规则");
        }

        customerDao.addCustomer(customer);
        return AppResponse.success("新增客户成功");
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 调dao层 修改客户
     * @Author：hanjz
     * @Date: 2019/4/10
     * @Param： 客户代码，姓名，版本号，是否使用
     * @Return： Map
     */
    @Override
    public AppResponse updateAccount(Customer customer) {
        SysUser userInfo=(SysUser) redisTemplate.opsForValue().get(customer.getTokenBackend());
        if(customerDao.getCustomerDetail(customer).isEmpty()){
            return AppResponse.notFound("此客户id不存在");
        }
        customer.setLastModifiedBy(userInfo.getUserUuid());
        if (customerDao.updateAccount(customer) == 0) {
            return AppResponse.versionError("版本不一致");
        }
        customer.setVersion(customer.getVersion()+1);
        customerDao.addVersion(customer);
        return AppResponse.success("修改客户成功");
    }
    /**
     * @Dept：南京软件研发中心
     * @Description： 调dao层 客户启用/禁用
     * @Author：hanjz
     * @Date: 2019/4/10
     * @Param： 客户代码，是否使用
     * @Return： Map
     */
    @Override
    public AppResponse updateAccountByStatus(Customer customer) {
        SysUser userInfo=(SysUser)redisTemplate.opsForValue().get(customer.getTokenBackend());
        customer.setLastModifiedBy(userInfo.getUserUuid());
        customerDao.updateAccountByStatus(customer);
        return AppResponse.success("客户启用/禁用成功");
    }

    @Override
    public Customer getCustomerByNumber(String customerNumber) {
        return customerDao.getCustomerByNumber(customerNumber);
    }
    //一个实体对象转map的方法 没用上
    public Map<String, String> obj2Map(Object obj) {

        Map<String, String> map = new HashMap<String, String>();
        // System.out.println(obj.getClass());
        // 获取f对象对应类中的所有属性域
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            varName = varName.toLowerCase();//将key置为小写，默认为对象的属性
            try {
                // 获取原来的访问控制权限
                boolean accessFlag = fields[i].isAccessible();
                // 修改访问控制权限
                fields[i].setAccessible(true);
                // 获取在对象f中属性fields[i]对应的对象中的变量
                Object o = fields[i].get(obj);
                if (o != null)
                    map.put(varName, o.toString());
                // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);
                // 恢复访问控制权限
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return map;
    }

}
