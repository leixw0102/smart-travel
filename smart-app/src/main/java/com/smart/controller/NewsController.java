package com.smart.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;
import com.google.common.base.Strings;
import com.smart.common.ResponseConstantCode;
import com.smart.common.ResponseMsg;
import com.smart.model.NewsInfo;
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping("/1.0/news/*")
public class NewsController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(NewsController.class);
    @Autowired
    private UserService userService;



    @RequestMapping(value = "create")
    @ResponseBody
    public void newsCreate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        try {
           // JSONObject obj = getJsonObject(request);
           // String body = readRequestBody(request);


            String title = request.getParameter("title");
            String abs = request.getParameter("abs");
          String content = request.getParameter("content");
            //String uid = obj.getString("uid");
            //String picture = obj.getString("picture");
          //  logger.info(title + abs);
           // LifeInfo id=lifeService.getId(userId);
            List<NewsInfo> list= userService.userNewsList(1,10);
            JSONObject result =	getSuccessJsonObject();
            result.put("title", title);
           result.put("abs", abs);
            result.put("list", list);
         //   result.put("list", list);
            returnInfo(response, result,200);

        } catch (Exception e) {
         //   throw new ApiException(e);
            returnInfo(response, getFailedJsonObject(1003, "内部错误"),200);
        }
    }

      @RequestMapping(value = "list")
       public String newsList(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer page) throws Exception {
          request.setAttribute("msgs",userService.userNewsList(page,10));
          return "publicNews";
    }

}
