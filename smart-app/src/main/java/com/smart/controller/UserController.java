package com.smart.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;
import com.google.common.base.Strings;
import com.smart.common.ResponseConstantCode;
import com.smart.common.ResponseMsg;
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



    @RequestMapping(value = "login")
    @ResponseBody
    public com.smart.common.ResponseBody sellerLogin(HttpServletResponse response,@RequestParam String userName,@RequestParam String pwd) throws ServletException, IOException {
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
                    Date use=new Date();
                    response.setHeader("user", JSON.toJSONString(new UserVo(3,"3",getLong(use))));
                    userService.updateUseTime(userInfo.getId(),use);
                    return new com.smart.common.ResponseBody() {
                        @Override
                        public List<NameFilter> nameFilters() {
                            return null;  //To change body of implemented methods use File | Settings | File Templates.
                        }
                    };
                }
            }
            //	logger.info("影人图片查询结束，返回终端数据；");
        } catch (Exception e) {
            throw new ApiException(e);
        }
    }

}
