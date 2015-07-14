package com.smart.controller;

import com.smart.common.ResponseMsg;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = {ApiException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody Object exception(ApiException apiException) {
        Object obj = apiException.getResponseMsg();
        if(obj instanceof ResponseMsg){
             return obj;
        }
        if(obj instanceof Map){
            return obj;
        }
		return apiException.getMessage();
	}
}
