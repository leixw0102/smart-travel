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

import com.smart.common.Page;
import com.smart.common.ResponseMsg;
import com.smart.model.SellerInfo;
import com.smart.service.SellerService;
import com.smart.vo.SellerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping("add")
    @ResponseBody
    public com.smart.common.ResponseBody addSeller(HttpServletRequest request,HttpServletResponse response,SellerVo info)  throws Exception{
        logger.info(info.toString());
        if(info.verfiy()){
            return new ResponseMsg("1","参数不能为空或者2次密码输入不正确");
        };
        try{
            if(sellerService.addSeller(info)){
                return  new ResponseMsg();
            };
            return  new ResponseMsg("1","插入失败");
        }catch (Exception e){
            logger.error("error!",e);
            return new ResponseMsg("1","插入失败,"+e.getMessage());
        }
//        return"redirect:getAccountLists";

    }
    @RequestMapping("addPage")
    public String getAdd(HttpServletRequest request,HttpServletResponse response)  throws Exception{
        Map<Integer,Map<Integer,String>> type=sellerService.getTypes();
        request.setAttribute("types",type);
        return "companyAcountManager-add";
    }

}
