package com.smart.controller;/*
 * Copyright 2015 Future TV, Inc.
 *
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.icntv.tv/licenses/LICENSE-1.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.smart.common.Page;
import com.smart.common.ResponseMsg;
import com.smart.model.CompanyInfo;
import com.smart.model.SellerInfo;
import com.smart.service.SellerService;
import com.smart.vo.SellerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/07/14
 * Time: 10:00
 */
@Controller
@RequestMapping("/1.0/seller/*")
public class SellerController extends BaseController{
    @Autowired
    private SellerService sellerService;

    @RequestMapping("code/{id}/{type}")
    public String getCode(HttpServletRequest request,HttpServletResponse response,@PathVariable Long id,@PathVariable Integer type){

        try {
            JSONObject ob = sellerService.getCode(id,type);
            request.setAttribute("abcd",ob.toJSONString());
        } catch (Exception e) {
            logger.error("error1",e);


        }
        return "abc";
    }
    @RequestMapping("getAccountLists")
    public String getHome(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer page) throws Exception{
        request.setAttribute("msgs",sellerService.getSellers(page));
        return "companyAcountManager";
    }
    @RequestMapping("page")
    @ResponseBody
    public Page get(){
        try {
            return sellerService.getSellers(1);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("updateSellerUser")
    @ResponseBody
    public com.smart.common.ResponseBody updateSeller(HttpServletRequest request,HttpServletResponse response,SellerVo info) {
        logger.info(info.toString());
        if(info.getId()==null){
            return new ResponseMsg("1","更新对象ID不存在");
        }

        if(info.verfiy()){
            return new ResponseMsg("1","参数不能为空或者2次密码输入不正确");
        };



        try{

            long id=sellerService.updateSeller(info);
            if(id>0){
                request.getSession().setAttribute("userType", info.getType());
                request.getSession().setAttribute("addUserId",id);
                return  new ResponseMsg();
            };
            return  new ResponseMsg("1","插入失败");
        }catch (Exception e){
            logger.error("error!",e);
            return new ResponseMsg("1","插入失败,"+e.getMessage());
        }
    }
    @RequestMapping("addUser")
    @ResponseBody
    public com.smart.common.ResponseBody addSeller(HttpServletRequest request,HttpServletResponse response,SellerVo info)  throws Exception{
        logger.info(info.toString());


        if(info.verfiy()){
            return new ResponseMsg("1","参数不能为空或者2次密码输入不正确");
        };



        try{
            if(sellerService.fingByPhone(info)){
                return  new ResponseMsg("1","该用户已经存在"+info.getUserName());
            };
            long id=sellerService.addSeller(info);
            if(id>0){
                request.getSession().setAttribute("userType", info.getType());
                request.getSession().setAttribute("addUserId",id);
                return  new ResponseMsg();
            };
            return  new ResponseMsg("1","插入失败");
        }catch (Exception e){
            logger.error("error!",e);
            return new ResponseMsg("1","插入失败,"+e.getMessage());
        }
//        return"redirect:getAccountLists";

    }

    @RequestMapping("addCompany")
    @ResponseBody
    public com.smart.common.ResponseBody addCompany(HttpServletRequest request,HttpServletResponse response,CompanyInfo info)  throws Exception{
        logger.info(info.toString());
        if(!info.verfiy()){
            return new ResponseMsg("1","error!");
        };
        try{
//            info.setType(Integer.parseInt(request.getSession().getAttribute("userType").toString()));
            if(sellerService.addCompany(info)>0){
                return  new ResponseMsg();
            };
            return  new ResponseMsg("1","插入失败");
        }catch (Exception e){
            logger.error("error!",e);
            return new ResponseMsg("1","插入失败,"+e.getMessage());
        }
//        return"redirect:getAccountLists";

    }
    @RequestMapping("updateCompanyInfo")
    @ResponseBody
    public com.smart.common.ResponseBody updateCompany(HttpServletRequest request,HttpServletResponse response,CompanyInfo info){
        logger.info(info.toString());
        if(!info.verfiy()){
            return new ResponseMsg("1","error!");
        };
        try{
//            info.setType(Integer.parseInt(request.getSession().getAttribute("userType").toString()));
            if(sellerService.updateCompany(info)>0){
                return  new ResponseMsg();
            };
            return  new ResponseMsg("1","插入失败");
        }catch (Exception e){
            logger.error("error!",e);
            return new ResponseMsg("1","插入失败,"+e.getMessage());
        }
    }

    @RequestMapping("addPage")
    public String getAdd(HttpServletRequest request,HttpServletResponse response)  throws Exception{
//        Map<Integer,Map<Integer,String>> type=sellerService.getTypes();
//        request.setAttribute("types",type);
        return "companyAcountManager-addUser";
    }
    @RequestMapping("addCompanyPage")
    public String getAddCompany(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Integer type = Integer.parseInt(request.getSession().getAttribute("userType").toString());
        Map<Integer,String> maps=sellerService.getTypes(type);
        request.setAttribute("secondaryTypes",maps);
        return "companyAcountManager-addCompany";
    }

    @RequestMapping("main")
    public String getHomeSystem(){
        return "main";
    }
    @RequestMapping("editSeller/{id}")
    public ModelAndView getSellerVo(@PathVariable Long id){
        try{
            SellerVo vo = sellerService.getUserById(id);
            logger.info(vo.toString());
            Map<String,String> maps = getMaps(vo);
            return new ModelAndView("editUserInfo",maps);
        }catch (Exception e){
            logger.error("error",e);
            return new ModelAndView("editUserInfo");
        }
    }
    @RequestMapping("editCompanyPage/{id}/{type}")
    public ModelAndView getCompanyUpdatePage(@PathVariable Long id,@PathVariable Integer type){
        try{
            CompanyInfo vo = sellerService.getCompanyByUserId(id,type);
            if(null == vo ){
                vo = new CompanyInfo();
                vo.setUserId(id);
                vo.setType(type);
                Long companyId=sellerService.addCompany(vo);
                vo.setId(companyId);
            }
//            Map<String,String> maps = getMapsByCompany(vo);
            Map<String,Object> maps = Maps.newHashMap();

            Map types = sellerService.getTypes(type);
            maps.put("user",vo);
            maps.put("secondaryTypeInfo",types);
            return new ModelAndView("editCompanyInfo",maps);
        }catch (Exception e){
            logger.error("error",e);
            return new ModelAndView("editCompanyInfo");
        }
    }

    private Map<String, String> getMapsByCompany(CompanyInfo vo) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    private Map<String, String> getMaps(SellerVo vo) {

        Map<String,String> maps = Maps.newHashMap();
        maps.put("userName",vo.getUserName());
        maps.put("pwd",vo.getPwd());
        maps.put("pwd2",vo.getPwd2());
        maps.put("type",vo.getType());
        maps.put("free",vo.getFree()+"");
        maps.put("remark",vo.getRemark());
        maps.put("id",vo.getId()+"");
        return maps;
    }

}
