package com.neo.domain;

import java.io.Serializable;

/**
 * Api接口消息体
 * 
 * @author zhangbaocheng2 2019年6月20日
 */
public class ApiResponse implements Serializable {
	private static final long serialVersionUID = 2896629193507347877L;

	// 错误代码
	public static final Integer SUCCESS = 1;
	// 业务错误代码
	public static final Integer ERROR = -1;
	// 系统、计费及权限相关错误代码
	public static final Integer NO_TOKEN = -2;
	public static final Integer INVALID_TOKEN = -3;
	public static final Integer NOT_FOUND_TOKEN = -4;
	public static final Integer UNAUTHORIZED_TOKEN = -10;
	public static final Integer FORBIDDEN_URL = -11;
	public static final Integer BALANCE_LOW = -12;
	public static final Integer DISABLED_URL = -13;
	public static final Integer INVALID_VEHICLE = -14;
	public static final Integer SYSTEM_ERROR = -15;

	protected Object data;
	protected Integer code;
	protected String msg;

	public ApiResponse() {

	}

	public ApiResponse(Object data, Integer code, String msg) {
		this.data = data;
		this.code = code;
		this.msg = msg;
	}

	public ApiResponse(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static ApiResponse success(String msg, Object data) {
		return new ApiResponse(data, SUCCESS, msg);
	}

	public static ApiResponse error(String msg, Object data) {
		return new ApiResponse(data, ERROR, msg);
	}

	public static ApiResponse error(Integer errorCode, String msg, Object data) {
		return new ApiResponse(data, errorCode, msg);
	}

	public static ApiResponse success(String msg) {
		return new ApiResponse(SUCCESS, msg);
	}

	public static ApiResponse error(Integer errorCode, String msg) {
		return new ApiResponse(errorCode, msg);
	}

	public static ApiResponse error(String msg) {
		return new ApiResponse(ERROR, msg);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
