package com.it.data;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * 返回值包装类
 */
@Data
public class Result implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -754380652123627456L;
	private int code;
	private String msg = "";
	private Object data = new Object();
	
	private Result(int code) {
		this.code = code;
	}
	
	private Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 请求成功
	 * 只返回code: 100
	 */
	public static Result success() {
		return new Result(Constants.Global.SUCCESS);
	}
	/**
	 * 请求成功
	 * 只返回code: 100
	 * 和 msg: 自定义
	 */
	public static Result successMsg(String msg) {
		return new Result(Constants.Global.SUCCESS,msg);
	}
	/**
	 * 请求成功
	 * 只返回code: 100
	 * 和 data: 自定义
	 */
	public static Result success(Object data) {
		return success().data(data);
	}
	/**
	 * 请求成功
	 * 返回code: 100
	 * 和 msg: 自定义
	 * 和 data: 自定义
	 */
	public static Result success(String msg, Object data) {
		return successMsg(msg).data(data);
	}
	/**
	 * 请求失败
	 * 返回code: 自定义
	 */
	public static Result fail(int code) {
		return new Result(code);
	}
	/**
	 * 请求失败
	 * 返回code: 自定义
	 * 和 msg: 自定义
	 */
	public static Result fail(int code, String msg) {
		return new Result(code,msg);
	}
	/**
	 * 请求失败
	 * 返回code: 自定义
	 * 和 msg: 自定义
	 * 和 data: 自定义
	 */
	public static Result fail(int code, String msg, Object data) {
		return fail(code,msg).data(data);
	}

	/**
	 * 参数有误
	 * 返回code:103
	 * 和 msg: 自定义
	 */
	public static Result paramError(String msg) {
		return new Result(Constants.Global.PARAM_ERROR,msg);
	}

	/**
	 * 系统错误，如网络连接失败或请求超时
	 * 返回code:101
	 * 和 msg: 自定义
	 */
	public static Result error(String msg) {
		return new Result(Constants.Global.ERROR,msg);
	}


	public static Result code(int code) {
		return new Result(code);
	}

	public Result data(Object data) {
		this.data = data;
		return this;
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

}