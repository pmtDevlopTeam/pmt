package com.camelot.pmt.platform.common;


import com.alibaba.fastjson.JSONObject;

/**
 * 
 * <p>Description: [API调用结果json]</p>
 * @author[tongxiying]
 */
public class ApiResponse {
	/**
	 * 
	 * <p>Discription:[获取标准格式返回结果]</p>
	 * @param status required APIStatus 
	 * @param data 结果集
	 * @return {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
	 * @author[tongxiying]
	 */
	public static String data(APIStatus status, Object data) {
		JSONObject result = new JSONObject();
		JSONObject statusJson = new JSONObject();
		statusJson.put("code", status.getCode());
		statusJson.put("message", status.getMessage());
		result.put("status", statusJson);
		result.put("data", data);
		return result.toJSONString();
	}
	/**
	 * 
	 * <p>Discription:[获取标准格式返回结果]</p>
	 * @param status required APIStatus
	 * @param data 结果集
	 * @return {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
	 * @author[tongxiying]
	 */
	public static JSONObject jsonData(APIStatus status, Object data) {
		JSONObject result = new JSONObject();
		JSONObject statusJson = new JSONObject();
		statusJson.put("code", status.getCode());
		statusJson.put("message", status.getMessage());
		result.put("status", statusJson);
		result.put("data", data);
		return result;
	}
	/**
	 * 
	 * <p>Discription:[获取标准格式返回结果]</p>
	 * @param status required APIStatus
	 * @return {"status":{"code":xxx,"message":"xxx"}}
	 * @author[tongxiying]
	 */
	public static String data(APIStatus status) {
		JSONObject result = new JSONObject();
		JSONObject statusJson = new JSONObject();
		statusJson.put("code", status.getCode());
		statusJson.put("message", status.getMessage());
		result.put("status", statusJson);
		return result.toJSONString();
	}
	/**
	 * 
	 * <p>Discription:[获取标准格式返回结果]</p>
	 * @param status required APIStatus
	 * @return {"status":{"code":xxx,"message":"xxx"}}
	 * @author[tongxiying]
	 */
	public static JSONObject jsonData(APIStatus status) {
		JSONObject result = new JSONObject();
		JSONObject statusJson = new JSONObject();
		statusJson.put("code", status.getCode());
		statusJson.put("message", status.getMessage());
		result.put("status", statusJson);
		return result;
	}

	/**
	 * 
	 * <p>Discription:[获取标准格式返回结果]</p>
	 * @param status required APIStatus
	 * @return {"status":{"code":xxx,"message":"xxx"}}
	 * @author[tongxiying]
	 */
	public static JSONObject jsonData(APIStatus status, String message) {
		JSONObject result = new JSONObject();
		JSONObject statusJson = new JSONObject();
		statusJson.put("code", status.getCode());
		statusJson.put("message", message);
		result.put("status", statusJson);
		return result;
	}

//	/**
//	 *
//	 * <p>Discription:[获取标准格式返回结果，加入英文处理]</p>
//	 * @param status required APIStatus
//	 * @param  language  en、ch
//	 * @return {"status":{"code":xxx,"message":"xxx"}}
//	 * @author[tongxiying]
//	 */
//	public static JSONObject jsonData(APIStatus status,String language) {
//		JSONObject result = new JSONObject();
//		JSONObject statusJson = new JSONObject();
//		statusJson.put("code", status.getCode());
//		if (null != language && language.equals("en")){
//			statusJson.put("message", status.getEnglishMessage());
//		}else {
//			statusJson.put("message", status.getMessage());
//		}
//		result.put("status", statusJson);
//		return result;
//	}
	
	/**
	 * 
	 * <p>Discription:[获取请求处理成功返回结果]</p>
	 * @return {"status":{"code":200,"message":"ok"}}
	 * @author[tongxiying]
	 */
	public static JSONObject success() {
		return jsonData(APIStatus.OK_200);
	}

	/**
	 * 
	 * <p>Discription:[获取请求处理成功返回结果]</p>
	 * @param data 返回数据
	 * @return {"status":{"code":200,"message":"ok"},"data":xxx}
	 * @author[tongxiying]
	 */
	public static JSONObject success(Object data) {
		return jsonData(APIStatus.OK_200, data);
	}

	/**
	 * 
	 * <p>Discription:[获取请求参数错误返回结果]</p>
	 * @return {"status":{"code":400,"message":"Bad Request."}}
	 * @author[tongxiying]
	 */
	public static JSONObject errorPara() {
		return jsonData(APIStatus.ERROR_400);
	}
	
	
	/**
	 * 系统错误返回结果
	 * @return
	 */
	public static JSONObject error() {
		return jsonData(APIStatus.ERROR_500);
	}	
	
	/**
	 * 系统错误返回结果
	 * @return {"status":{"code":400,"message":"Bad Request."}}
	 */
	public static JSONObject error(String message) {
		return jsonData(APIStatus.ERROR_500, message);
	}
	
	
	/**
	 * <p>Description:[指定语言类型,返回400]</p>
	 * @param language
	 * @return jsonbobject
	 * @author:[tongxiying]
	 */
	public static JSONObject errorPara(String language) {
		return jsonData(APIStatus.ERROR_400, language);
	}
	/**
	 * 
	 * <p>Discription:[获取请求参数错误返回结果]</p>
	 * @param data 返回数据
	 * @return {"status":{"code":400,"message":"Bad Request."},"data":xxx}
	 * @author[tongxiying]
	 */
	public static JSONObject errorPara(Object data) {
		return jsonData(APIStatus.ERROR_400, data);
	}
	
}
