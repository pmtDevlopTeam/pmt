package com.camelot.pmt.platform.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * 
 */
public class PlatformR extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	private Map<String,Object> data=new HashMap<String,Object>();
	public PlatformR() {
		put("code", 0);
		put("msg", "");
		put("data", data);
	}
	
	public static PlatformR error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static PlatformR error(String msg) {
		return error(500, msg);
	}
	
	public static PlatformR error(int code, String msg) {
		PlatformR r = new PlatformR();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static PlatformR ok(String msg) {
		PlatformR r = new PlatformR();
		r.put("msg", msg);
		return r;
	}
	
	public static PlatformR ok(Map<String, Object> map) {
		PlatformR r = new PlatformR();
		r.putAll(map);
		return r;
	}
	
	public static PlatformR ok() {
		return new PlatformR();
	}

	public PlatformR put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	public PlatformR data(String key,Object value){
		data.put(key, value);
		return this;
	}
	public PlatformR data(Map<String,Object> map){
		data.putAll(map);
		return this;
	}

}
