package com.smart.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.smart.common.ResponseConstantCode;
import com.smart.common.ResponseMsg;
import com.smart.model.Config;
import com.smart.model.NewsInfo;
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

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
@RequestMapping("/1.0/news/*")
public class NewsController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(NewsController.class);
    @Autowired
    private UserService userService;
    @Resource(name="configImg")
    private Config config;



    @RequestMapping(value = "create")
    @ResponseBody

    public ResponseMsg newsCreate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
            // JSONObject obj = getJsonObject(request);
            // String body = readRequestBody(request);
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            multipartResolver.setDefaultEncoding("utf-8");
            logger.info(multipartResolver.isMultipart(request)+"");
            String title=null, abs=null,content=null;
            if(multipartResolver.isMultipart(request))  {
                MultipartHttpServletRequest multiRequest = multipartResolver.resolveMultipart(request);

                Iterator<String> ite = multiRequest.getFileNames();
                String af="";
                File localDir ;
                File news;
                List<String> paths= Lists.newArrayList();
                title = multiRequest.getParameter("title");
                abs= multiRequest.getParameter("abs");
                content = multiRequest.getParameter("content");
                try{
                    localDir= new File(config.getRootPath());
                    if(!localDir.exists()){
                        localDir.mkdirs();
                    }
                    news = new File(localDir,config.getPath());
                    if(!news.exists()){
                        news.mkdirs();
                    }
                    while(ite.hasNext()){
                        String name = ite.next();
                        MultipartFile file = multiRequest.getFile(name);
                        if(file!=null){
                            File localFile=null;
                            try {
                                String serverFileName=System.nanoTime()+"_"+file.getOriginalFilename();
                                localFile = new File(news,serverFileName);
                                logger.debug("af = "+af +" ;localdir = "+localDir.getAbsolutePath()+" ; file = "+localFile.getAbsolutePath()+"news"+news.getAbsolutePath());
                                file.transferTo(localFile); //将上传文件写到服务器上指定的文件
                                paths.add(config.getUrl()+File.separator+config.getPath()+File.separator+serverFileName);
                            } catch (IllegalStateException e) {
                                e.printStackTrace();
                                logger.error("error msg!",e);
                                e.printStackTrace();
                                throw new Exception("upload error!");
                            } catch (IOException e) {
                                e.printStackTrace();
                                logger.error("error msg!",e);
                                throw new Exception("upload error");
                            }
                        }
                    }

                }catch(Exception e){
                    logger.error("error!",e);
                    return new ResponseMsg("1","error!"+e.getMessage());
                }
                logger.debug(Joiner.on(";").join(paths));
                //update
                try {
                    if(userService.create(Joiner.on(";").join(paths), title, content, abs)){
                        return new ResponseMsg();
                    };
                    Files.deleteDirectoryContents(localDir);
                    return new ResponseMsg("12","上传失败！检查用户相关信息");
                } catch (Exception e) {
                    logger.error("upload error msg!",e);
                    return new ResponseMsg("12","update hote msg error!");
                }
            }
        return  new ResponseMsg("12","update hote msg error!");
    }

//    @RequestMapping(value = "list1")
//      @ResponseBody
//      public void newsList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//        try {
//            String page = request.getParameter("page");
//            String size = request.getParameter("size");
//            logger.info("newsList:"+page+":"+  size);
//            //  logger.info(title + abs);
//            // LifeInfo id=lifeService.getId(userId);
//            //Integer.parseInt(page)
//            List<NewsInfo> list= userService.userNewsList(1,10);
//            JSONObject result =	getSuccessJsonObject();
//            result.put("list", list);
//            returnInfo(response, result,200);
//        } catch (Exception e) {
//            //   throw new ApiException(e);
//            returnInfo(response, getFailedJsonObject(1003, "get news list error"),200);
//        }

    @RequestMapping(value = "list")
    public String newsList(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer page) throws Exception {
        request.setAttribute("msgs",userService.userNewsList(page,10));
        return "publicNews";

    }
    @RequestMapping("lists/{page}")
    @ResponseBody
    public com.smart.common.ResponseBody getNewsList(HttpServletRequest request,HttpServletResponse response,@PathVariable Long page, @RequestParam(required = false) String time){
        try{
            return userService.userNewsList(page,10,time);
        } catch (Exception e){
            logger.error("ds",e);
            return new ResponseMsg<>("10","error!"+e.getMessage());
        }
    }

}
