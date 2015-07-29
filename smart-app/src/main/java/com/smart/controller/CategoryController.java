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

import com.smart.common.ResponseMsg;
import com.smart.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
 * Date: 2015/07/28
 * Time: 14:55
 */
@RequestMapping("/1.0/category/")
public class CategoryController extends BaseController {
    @Autowired
    private SellerService sellerService;
    @RequestMapping("typies")
    public String getCategory( HttpServletRequest request,HttpServletResponse response){
        try {
            Map<Integer,Map<Integer,String>> type=sellerService.getTypes();
            request.setAttribute("typeies-abc",type);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "category-m";
    }
    @RequestMapping("delete/{id}")
    @ResponseBody
    public ResponseMsg deleteCategoty(@PathVariable Long id){

        try{
            if(sellerService.deleteCategory(id)){
                return new ResponseMsg();
            };
            return  new ResponseMsg("20","删除失败");
        }catch (Exception e){
            return  new ResponseMsg("20","删除失败,"+e);
        }

    }
    @RequestMapping("update/{id}")
    @ResponseBody
    public ResponseMsg updateCategory(@PathVariable Long id,@RequestParam(required = true) String name){

        try{
            if(sellerService.updateCategory(id,name)){
                return new ResponseMsg();
            };
            return  new ResponseMsg("20","删除失败");
        }catch (Exception e){
            return  new ResponseMsg("20","删除失败,"+e);
        }
    }
    //    Map<Integer,String> maps=sellerService.getTypes(type);
}
