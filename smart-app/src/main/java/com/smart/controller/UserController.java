package com.smart.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;
import com.google.common.base.Strings;
import com.smart.common.Page;
import com.smart.common.ResponseConstantCode;
import com.smart.common.ResponseMsg;
import com.smart.model.CashUserInfo;
import com.smart.model.UserInfo;
import com.smart.service.UserService;
import com.smart.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/1.0/user/*")
public class UserController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("addFinanceUser")
    public String addUser(HttpServletResponse response,HttpServletRequest request,CashUserInfo info){
           if(info.check()){
               throw new ApiException(new ResponseMsg("填写信息错误","10"));
           }
            return "";//页面财务
    }

    @RequestMapping("cashUsers")
    public String getCashUsers(HttpServletResponse response,HttpServletRequest request){
        try {
            userService.searchCashUser(1,20);
            return "" ;
        } catch (Exception e) {
            throw new ApiException(new ResponseMsg(ResponseConstantCode.DATA_CANT_FOUND_CODE,ResponseConstantCode.DATA_CANT_FOUND_DESC));
        }

    }

    @RequestMapping(value = "login")
    @ResponseBody
    public com.smart.common.ResponseBody sellerLogin(HttpServletRequest request,HttpServletResponse response,@RequestParam String userName,@RequestParam String pwd) throws ServletException, IOException {
        try {

            //String token = userService.buyerLogin( username,  password,mac) ;
            if (Strings.isNullOrEmpty(userName)
                    || Strings.isNullOrEmpty(pwd)
                    ) {
                throw new ApiException(ResponseMsg.ILLEGAL_PARAMETER_MSG);
            }else{
                UserInfo userInfo = userService.sellerLogin(userName,  pwd) ;
                if (userInfo == null){
                    throw new ApiException(new ResponseMsg(ResponseConstantCode.DATA_CANT_FOUND_CODE,ResponseConstantCode.DATA_CANT_FOUND_DESC));
                }else{
                      request.getSession().setAttribute("userSessionId",userInfo.getId()+"-"+userInfo.getRole()+"-"+userInfo.getUserName());
//                    Date use=new Date();
//                    response.setHeader("user", JSON.toJSONString(new UserVo(3,"3",getLong(use))));
//                    userService.updateUseTime(userInfo.getId(),use);
                    ResponseMsg<String> msg=new ResponseMsg<String>();
                    int type = userInfo.getRole();
                    if(type==1){
                        msg.setInfo("/1.0/seller/main");
                    }else if(type==2){
                        msg.setInfo("/1.0/news/list?page=1");
                    } else if(type==3){
                        msg.setInfo("/1.0/finance/getHomeList/2/1");
                    }
                    return msg;
                }
            }
            //	logger.info("影人图片查询结束，返回终端数据；");
        } catch (Exception e) {
            throw new ApiException(e);
        }
    }

}
