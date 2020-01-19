package com.neusoft.common.util;

public class RandomUtil {
	
	/**
	 * @Dept：南京软件研发中心
	 * @Description：生成一串随机数
	 * @Author：shengtt
	 * @Date: 2019/3/26
	 * @Param：count
	 * @Return：java.lang.String
	 */
	public static String radmonkey(int count){
		StringBuffer sbf=new StringBuffer();
		for (int i = 0; i <count; i++) {
			int num=(int)(Math.random()*10);
			sbf.append(num);
		}
		
		return sbf.toString();
	}
	
	public static void main(String[] args) {
		System.err.println(radmonkey(32));
	}

}
