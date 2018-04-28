package com.camelot.pmt.common;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @Package: com.camelot.pmt.common
 * @ClassName: SendRequestUtil
 * @Description: TODO
 * @author: xueyj
 * @date: 2018-04-28 10:25
 */
public class SendRequestUtil {
    @Autowired
    private  HttpServletRequest request;
    public String sendRequest(String url, String param)  throws Exception{
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/platform/version/queryVersionInfoById?versionId=6";
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(basePath)
                .build();
        Response response = httpClient.newCall(request).execute();
        System.out.println("============================\n"+response+"\n============================");
        return response.body().string(); // 返回的是string 类型，json的mapper可以直接处理
    }
}
