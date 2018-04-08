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
	 * @author[马智]
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
	 * Created on 2017年5月27日
	 * @param status required APIStatus 
	 * @param data 结果集
	 * @return {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
	 * @author[马智]
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
	 * Created on 2017年5月27日
	 * @param status required APIStatus 
	 * @return {"status":{"code":xxx,"message":"xxx"}}
	 * @author[马智]
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
	 * Created on 2017年5月27日
	 * @param status required APIStatus 
	 * @return {"status":{"code":xxx,"message":"xxx"}}
	 * @author[马智]
	 */
	public static JSONObject jsonData(APIStatus status) {
		JSONObject result = new JSONObject();
		JSONObject statusJson = new JSONObject();
		statusJson.put("code", status.getCode());
		statusJson.put("message", status.getMessage());
		result.put("status", statusJson);
		return result;
	}

//	/**
//	 *
//	 * <p>Discription:[获取标准格式返回结果，加入英文处理]</p>
//	 * Created on 2017年12月25日
//	 * @param status required APIStatus
//	 * @param  language  en、ch
//	 * @return {"status":{"code":xxx,"message":"xxx"}}
//	 * @author[马智]
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
	 * Created on 2017年6月20日
	 * @return {"status":{"code":200,"message":"ok"}}
	 * @author[马智]
	 */
	public static JSONObject success() {
		return jsonData(APIStatus.OK_200);
	}

	/**
	 * 
	 * <p>Discription:[获取请求处理成功返回结果]</p>
	 * Created on 2017年6月20日
	 * @param data 返回数据 
	 * @return {"status":{"code":200,"message":"ok"},"data":xxx}
	 * @author[马智]
	 */
	public static JSONObject success(Object data) {
		return jsonData(APIStatus.OK_200, data);
	}

	/**
	 * 
	 * <p>Discription:[获取请求参数错误返回结果]</p>
	 * Created on 2017年6月20日
	 * @return {"status":{"code":400,"message":"Bad Request."}}
	 * @author[马智]
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
	 * <p>Description:[指定语言类型,返回400]</p>
	 * Created on 2017年12月26日
	 * @param language
	 * @return jsonbobject
	 * @author:[马智]
	 */
	public static JSONObject errorPara(String language) {
		return jsonData(APIStatus.ERROR_400, language);
	}
	/**
	 * 
	 * <p>Discription:[获取请求参数错误返回结果]</p>
	 * Created on 2017年6月20日
	 * @param data 返回数据 
	 * @return {"status":{"code":400,"message":"Bad Request."},"data":xxx}
	 * @author[马智]
	 */
	public static JSONObject errorPara(Object data) {
		return jsonData(APIStatus.ERROR_400, data);
	}
	
}
