package com.smart.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class BaseController {

    protected final static Logger logger = LoggerFactory.getLogger(BaseController.class);
    
    public static final String ERROR_CODE = "errorCode";
    public static final String ERROR_DESC = "errorDesc";
    
    public static final String RESULT_CODE = "resultCode";
    public static final String RESULT_DESC = "resultDesc";
    protected Long getLong(Date date){
        return date.getTime()^1004L;
    }
    protected void loggeRequestrMsg(String url,String info){
		logger.info("url={},request info = {}",url,info);
	}
    protected SimpleDateFormat sdf1 = new SimpleDateFormat("yyMMdd"); 
    public JSONObject getSuccessJsonObject(){
        JSONObject object = new JSONObject();
        object.put("code",1000);
        object.put("msg","success");
        return object;
    }
    
    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    public JSONObject getSuccessListJsons(Long total,List list){
    	JSONObject object = new JSONObject();
        object.put("code",1000);
        object.put("msg","success");
        object.put("total", total);
        object.put("list", list);
        return object;
    }
    
    public JSONObject getFailedJsonObject(int errorCode,String error){
        JSONObject object = new JSONObject();
        object.put("code",errorCode);
        object.put("msg",error);
        return object;
    }
    
    public JSONObject getJsonObject(HttpServletRequest  request) throws IOException{
    	String body = readRequestBody(request);
    	return JSON.parseObject(body);
    }
    
    public static final String readRequestBody(HttpServletRequest request) throws IOException {    	
    	//TODO: PATCH token
    	 Object buffer1 = request.getAttribute("tmp_string");
    	 if (buffer1!=null){
    		 StringBuilder buffer =(StringBuilder) buffer1;
    		 return  buffer1.toString();
    	 }
    	 //
    	 
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            buffer.append(line);
        }	 	
        return buffer.toString();
    }

    protected void returnInfo(HttpServletRequest request, HttpServletResponse response, int httpStatusCode, String returnInfo) throws IOException {
        response.setStatus(httpStatusCode);
        if (returnInfo != null && returnInfo.trim().length() > 0) {
            PrintWriter out = response.getWriter();
            logger.info("返回终端数据：" + returnInfo);
            out.print(returnInfo);
            out.flush();
            out.close();
        } else {
            logger.info("本次请求无返回数据！");
        }
    }

    protected void returnInfo(HttpServletRequest request, HttpServletResponse response, int httpStatusCode) throws IOException {
        response.setStatus(httpStatusCode);
        logger.info(httpStatusCode + "本次请求无返回数据！");
    }

    protected void returnInfo(HttpServletRequest request, HttpServletResponse response, int httpStatusCode, JSONObject returnJson) throws IOException {
        if (returnJson == null || returnJson.isEmpty()) {
            returnInfo(request, response, httpStatusCode);
        } else {
            returnInfo(request, response, httpStatusCode, returnJson.toString());
        }
    }

    protected void returnInfo(HttpServletRequest request, HttpServletResponse response, int httpStatusCode, JSONArray returnJsonArray) throws IOException {
        logger.info("本次请求结果状态码：" + httpStatusCode);
        if (returnJsonArray == null || returnJsonArray.isEmpty()) {
            returnInfo(request, response, httpStatusCode);
        } else {
            returnInfo(request, response, httpStatusCode, returnJsonArray.toString());
        }
    }
    
    protected void returnInfo(HttpServletResponse response ,JSONObject json) throws IOException{
    	 returnInfo(null, response,json.getIntValue("code"),json.toJSONString());
    	
    }
    
    protected void returnInfo(HttpServletResponse response ,JSONObject json,Integer code) throws IOException{
   	 returnInfo(null, response,code,json.toJSONString());
   	
   }

}
