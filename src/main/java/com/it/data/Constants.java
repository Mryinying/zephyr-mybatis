package com.it.data;

public interface Constants {
	
	/**
	 * 公共请求返回code
	 * @author Administrator
	 *
	 */
	class Global {
		
		/*
		 * 请求成功
		 */
		public static final int SUCCESS = 100;
		/*
		 * 服务器请求失败 
		 */
		public static final int ERROR = 101;
		/*
		 * 接口请求失败
		 */
		public static final int FAILURE = 102;		
		/*
		 * 参数有误
		 */
		public static final int PARAM_ERROR = 103;
		/*
		 * 非法请求
		 */
		public static final int REQUEST_INVALID = 199;
		/*
		 * token失效【登录过期】
		 */
		public static final int TOKEN_INVALID = 190;
		
	}

}