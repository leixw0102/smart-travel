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
import com.smart.service.FinanceService;
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
 * Date: 2015/07/16
 * Time: 20:01
 */
@Controller
@RequestMapping("/1.0/finance/*")
public class FinanceController extends BaseController {
    @Autowired
    private FinanceService financeService;
    @RequestMapping("confirm/{applyId}")
    @ResponseBody
    public ResponseMsg confirm(HttpServletRequest request,HttpServletResponse response,@PathVariable Long applyId){
        try{
            if(financeService.confirm(applyId)){
                return new ResponseMsg();
            }
            return new ResponseMsg("1005","兑现失败,请联系维护人员");
        }catch (Exception e){
            logger.error("confirm error!",e);
            return new ResponseMsg("1004",e.getMessage());
        }
    }
    @RequestMapping("refuse/{applyId}")
    @ResponseBody
    public ResponseMsg refuse(@PathVariable Long applyId){
        try{
            if(financeService.confirm(applyId)){
                return new ResponseMsg();
            }
            return new ResponseMsg("1005","兑现失败,请联系维护人员");
        }catch (Exception e){
            logger.error("confirm error!",e);
            return new ResponseMsg("1004",e.getMessage());
        }
    }
    @RequestMapping("getLists/{page}")
    @ResponseBody
    public com.smart.common.Page getList(HttpServletRequest request,HttpServletResponse response,@PathVariable Integer page,
                                                 @RequestParam(required = false) String from,@RequestParam(required = false) String to,
                                                 @RequestParam(required = false) Integer type){
        try{
            return financeService.search(page,from,to,type);
        }catch (Exception e){
           throw new ApiException(new ResponseMsg("1004",e.getMessage()));
        }
    }
}
