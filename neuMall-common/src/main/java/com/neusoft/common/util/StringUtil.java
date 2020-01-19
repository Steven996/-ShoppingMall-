package com.neusoft.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class StringUtil {


    /**
     * @Dept：南京软件研发中心
     * @Description：生成编号   年月日时分秒+8位随机数
     * @Author：shengtt
     * @Date: 2019/3/26
     * @Param：
     * @Return：java.lang.String
     */
    public static String initNo(){
		String prefix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String suffix = RandomUtil.radmonkey(8);
		return prefix + suffix;
	}

	/**
	 * 部门：软件开发事业部
	 * 功能：生成code
	 * 描述：年月日时分秒毫秒18位（201711111111111000）+num位随机数
	 * 作成者：封新芳
	 * 作成时间：2017-10-11
	 */
	/**
	 * @Dept：南京软件研发中心
	 * @Description：生成code  年月日时分秒毫秒18位（201711111111111000）+num位随机数
	 * @Author：shengtt
	 * @Date: 2019/3/26
	 * @Param：
	 * @Return：java.lang.String
	 */
	public static String getCommonCode(int num) {
		String prefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String suffix = RandomUtil.radmonkey(num);
		return prefix + suffix;
	}
	/**
	 * 部门：软件开发事业部
	 * 功能：生成UUID
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}