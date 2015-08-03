package com.smart.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;
import com.google.common.base.Strings;
import com.smart.common.Page;
import com.smart.common.ResponseConstantCode;
import com.smart.common.ResponseMsg;
import com.smart.model.Apply;
import com.smart.model.CashUserInfo;
import com.smart.model.NewsUserInfo;
import com.smart.model.UserInfo;
import com.smart.service.UserService;
import com.smart.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/1.0/user/*")
public class UserController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("addNewsUser")
    @ResponseBody
    public com.smart.common.ResponseBody addNewsUser(HttpServletResponse response,HttpServletRequest request,NewsUserInfo info){
        if(info.check()){
            throw new ApiException(new ResponseMsg("10","填写信息错误"));
        }
        try{

            NewsUserInfo find= userService.searchNewsUser(info);
            if(null != find){
                return new ResponseMsg("1","用户已经存在");
            }

            if(userService.saveNewsUser(info)){
                return new ResponseMsg();
            };
            return new ResponseMsg("1","插入失败");
        }catch (Exception e){
            logger.error("add finance user error!" ,e);
            return new ResponseMsg("1","插入失败"+e.getMessage());
        }
    }


    @RequestMapping("addFinanceUser")
    @ResponseBody
    public com.smart.common.ResponseBody addUser(HttpServletResponse response,HttpServletRequest request,CashUserInfo info){
        if(info.check()){
            throw new ApiException(new ResponseMsg("10","填写信息错误"));
        }
        try{

            CashUserInfo find= userService.searchFinanceUser(info);
            if(null != find){
                return new ResponseMsg("1","用户已经存在");
            }

            if(userService.saveFinanceUser(info)){
                return new ResponseMsg();
            };
            return new ResponseMsg("1","插入失败");
        }catch (Exception e){
            logger.error("add finance user error!" ,e);
            return new ResponseMsg("1","插入失败"+e.getMessage());
        }
    }

    @RequestMapping("getCashHome")
    public String getCashUserHome()  {
        return "finaceAcountManager";
    }

    @RequestMapping("cashUsers/{page}")
    @ResponseBody
    public Page<CashUserInfo> getCashUsers(HttpServletResponse response,HttpServletRequest request,@PathVariable Integer page){
        try {
            Page<CashUserInfo> financeUsers=userService.searchCashUser(page,20);
            return financeUsers;
//            request.setAttribute("financeUsers",financeUsers);
//            return "finaceAccountManager" ;
        } catch (Exception e) {
            throw new ApiException(new ResponseMsg(ResponseConstantCode.DATA_CANT_FOUND_CODE,ResponseConstantCode.DATA_CANT_FOUND_DESC));
        }

    }

    @RequestMapping("newsUsers/{page}")
    @ResponseBody
    public Page<NewsUserInfo> getNewsUsers(HttpServletResponse response,HttpServletRequest request,@PathVariable Integer page){
        try {
            Page<NewsUserInfo> financeUsers=userService.searchNewsUser(page, 20);
            return financeUsers;
//            request.setAttribute("financeUsers",financeUsers);
//            return "finaceAccountManager" ;
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
                    return new ResponseMsg(ResponseConstantCode.DATA_CANT_FOUND_CODE,ResponseConstantCode.DATA_CANT_FOUND_DESC);
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
                        msg.setInfo("/publicNews.jsp");
                    } else if(type==3){
                        msg.setInfo("/finace-main.jsp");
                    }
                    return msg;
                }
            }
            //	logger.info("影人图片查询结束，返回终端数据；");
        } catch (Exception e) {
            throw new ApiException(e);
        }
    }
    @RequestMapping("financeDeleteById/{id}")
    @ResponseBody
    public com.smart.common.ResponseBody deleteFinance(@PathVariable Long id){
        try{
            if(userService.deleteById(id)){
               return new ResponseMsg();
            }
            return new ResponseMsg("1","删除失败");
        }catch (Exception e){
            return new ResponseMsg("1","删除失败");
        }

    }

    @RequestMapping("newsDeleteById/{id}")
    @ResponseBody
    public com.smart.common.ResponseBody deleteUser(@PathVariable Long id){
        try{
            if(userService.deleteById(id)){
                return new ResponseMsg();
            }
            return new ResponseMsg("1","删除失败");
        }catch (Exception e){
            return new ResponseMsg("1","删除失败");
        }

    }
    @RequestMapping("updatePwd/{id}")
    @ResponseBody
    public   com.smart.common.ResponseBody updatePwd(@PathVariable Long id,@RequestParam String old,@RequestParam String newPwd) {
        try{

            CashUserInfo info=userService.findUserByIdAndPwd(id,old);
            if(null == info){
                return new ResponseMsg("1","原始密码输入错误");
            }

            if(userService.updateById(id,old,newPwd)){
                return new ResponseMsg();
            }
            return new ResponseMsg("1","更新失败");
        }catch (Exception e){
            return new ResponseMsg("1","更新失败");
        }
    }
    @RequestMapping("getUpdatePwdPage/{id}")
    public String getReWritePage(HttpServletRequest request,HttpServletResponse response,@PathVariable Long id){
        request.setAttribute("financeUpdateId",id);
        return "pwdRewrite";
    }
    @RequestMapping("logout")
    @ResponseBody
    public ResponseMsg logout(HttpServletRequest request,HttpServletResponse response){
        HttpSession session=request.getSession(false);
        session.removeAttribute("userSessionId");
        ResponseMsg<String> msg=new ResponseMsg<String>();
        msg.setInfo("login.jsp");
        return msg;
    }

    @RequestMapping("getOrderMsgPage/{id}")
    public String getOrderMsgPage(HttpServletRequest request,HttpServletResponse response,@PathVariable Long id){
        request.setAttribute("userForOrderId",id);
        return "test";
    }
}
