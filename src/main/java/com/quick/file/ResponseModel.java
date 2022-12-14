package com.quick.file;

public class ResponseModel {
	
	public ResponseModel(){}
	
	public ResponseModel(int code,Object data,String msg){
		this.code = code;
		this.data = data;
		this.msg = msg;
	}
	
	private int code;
	
	private Object data;
	
	private String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
