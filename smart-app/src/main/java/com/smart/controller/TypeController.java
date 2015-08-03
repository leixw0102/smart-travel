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

import com.alibaba.fastjson.JSON;
import com.smart.common.Page;
import com.smart.common.ResponseMsg;
import com.smart.model.TypeInfo;
import com.smart.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/08/03
 * Time: 08:50
 */
@Controller
@RequestMapping("/1.0/category/*")
public class TypeController extends BaseController{
    @Autowired
    private SellerService sellerService;
    @RequestMapping("types")
    @ResponseBody
    public com.smart.common.ResponseBody getCategoryAB( HttpServletRequest request,HttpServletResponse response){
        try {
           Page<TypeInfo> page =sellerService.getTypes();
//            request.setAttribute("typeies-abc",type);
            logger.debug(JSON.toJSONString(page));
            return page;
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return new ResponseMsg("10","查询出错");
        }
//        return "category-m";
    }

    @RequestMapping("deleteA/{id}")
    @ResponseBody
    public ResponseMsg deleteCategotyAB(@PathVariable Long id){

        try{
            if(sellerService.deleteCategory(id)){
                return new ResponseMsg();
            };
            return  new ResponseMsg("20","删除失败");
        }catch (Exception e){
            return  new ResponseMsg("20","删除失败,"+e);
        }

    }
    @RequestMapping("updateA/{id}")
    @ResponseBody
    public ResponseMsg updateCategoryAB(@PathVariable Long id,@RequestParam(required = true) String name){

        try{
            if(sellerService.updateCategory(id,name)){
                return new ResponseMsg();
            };
            return  new ResponseMsg("20","删除失败");
        }catch (Exception e){
            return  new ResponseMsg("20","删除失败,"+e);
        }
    }
    @RequestMapping("addCategory/{id}")
    @ResponseBody
    public ResponseMsg addCategory(@PathVariable Long id,@RequestParam String name){
        try{
            if(sellerService.addCategory(id,name)){
                return new ResponseMsg();
            };
            return  new ResponseMsg("20","添加失败");
        }catch (Exception e){
            return  new ResponseMsg("20","添加失败,"+e);
        }
    }
}
