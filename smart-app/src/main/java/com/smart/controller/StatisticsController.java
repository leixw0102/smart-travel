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
import com.smart.common.ResponseMsg;
import com.smart.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/07/16
 * Time: 20:47
 */
@Controller
@RequestMapping("/1.0/*")
public class StatisticsController extends BaseController {
    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping("hotel/lastWeek")
    @ResponseBody
    public com.smart.common.ResponseBody getLastVSXY(@RequestParam(required = false)Integer type){
        try{
            com.smart.common.ResponseBody responseMsg= statisticsService.getHotelXy(1, type);
            logger.info(JSON.toJSONString(responseMsg));
            return responseMsg;
        }catch (Exception e){
            logger.error("error",e);
            return new ResponseMsg("2","查询出错");
        }
    }

    @RequestMapping("hotel/nextWeek")
    @ResponseBody
    public com.smart.common.ResponseBody getNextVSXY(@RequestParam(required = false)Integer type){
        try{
            return statisticsService.getHotelXy(2, type);
        }catch (Exception e){
            logger.error("error",e);
            return new ResponseMsg("2","查询出错");
        }
    }
    @RequestMapping("hotel/getMapData")
    @ResponseBody
    public com.smart.common.ResponseBody getMapView(){
        try{
            return statisticsService.getHotelMap();
        }catch (Exception e){
            return new ResponseMsg("2","查询出错");
        }
    }
}
