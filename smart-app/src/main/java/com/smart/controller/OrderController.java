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
import com.smart.model.UserClientInfo;
import com.smart.service.OrderServie;
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
 * Date: 2015/07/19
 * Time: 16:37
 */
@Controller
@RequestMapping("/1.0/order/*")
public class OrderController extends BaseController {
    @Autowired
    private OrderServie orderServie;
    @RequestMapping("getOrderLists/{id}/{type}/{orderType}/{page}")
    @ResponseBody
    public com.smart.common.Page getList(HttpServletRequest request,HttpServletResponse response,@PathVariable Long id,@PathVariable Integer type,@PathVariable Integer page,
                                         @RequestParam(required = false) String from,@RequestParam(required = false) String to,
                                         @PathVariable Integer orderType){
        try{
            return orderServie.search(id,page,from,to,type,orderType);
        }catch (Exception e){
            throw new ApiException(new ResponseMsg("1004",e.getMessage()));
        }
    }
    @RequestMapping("userHome")
    public String getUserOrderHome(){
        return "userAcountManager";
    }
    @RequestMapping("clientUsers/{page}")
    @ResponseBody
    public Page<UserClientInfo> getClientUsers(@PathVariable Integer page){
        try{
            return orderServie.getClientUsers(page);
        }catch (Exception e){
            logger.error("error msg !",e);
            Page<UserClientInfo> info= new Page<UserClientInfo>(){

                @Override
                protected String listAlias() {
                    return null;  //To change body of implemented methods use File | Settings | File Templates.
                }
            };
            info.setCode("2");
            info.setMessage("查询失败");
            return info;
        }
    }
    @RequestMapping("delete/{id}")
    @ResponseBody
    public ResponseMsg<String> deleteClientUser(@PathVariable Long id){
        try{
//            info.setType(Integer.parseInt(request.getSession().getAttribute("userType").toString()));
            if(orderServie.deleteClientUserById(id)){
                return  new ResponseMsg();
            };
            return  new ResponseMsg("1","删除失败");
        }catch (Exception e){
            logger.error("error!",e);
            return new ResponseMsg("1","删除失败,"+e.getMessage());
        }
    }

}
