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
import org.springframework.web.bind.annotation.PathVariable;
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
    public com.smart.common.ResponseBody getLastVSXY(@RequestParam(required = false) Integer totalType,@RequestParam(required = false)Integer type){
        try{
            com.smart.common.ResponseBody responseMsg= statisticsService.getHotelXy(1, type,totalType);
            logger.info(JSON.toJSONString(responseMsg));
            return responseMsg;
        }catch (Exception e){
            logger.error("error",e);
            return new ResponseMsg("2","查询出错");
        }
    }

    @RequestMapping("hotel/nextWeek")
    @ResponseBody
    public com.smart.common.ResponseBody getNextVSXY(@RequestParam(required = false) Integer totalType,@RequestParam(required = false)Integer type){
        try{
            return statisticsService.getHotelXy(2, type, totalType);
        }catch (Exception e){
            logger.error("error",e);
            return new ResponseMsg("2","查询出错");
        }
    }
    @RequestMapping("getMapData/{type}")
    @ResponseBody
    public com.smart.common.ResponseBody getMapView(@PathVariable Integer type){
        try{
            return statisticsService.getHotelMap(type);
        }catch (Exception e){
            return new ResponseMsg("2","查询出错");
        }
    }

    @RequestMapping("cate/getXy/{type}")
    @ResponseBody
    public com.smart.common.ResponseBody getCateXY(@PathVariable Integer type){
        try{
            return statisticsService.getCateXy(type);
        }catch (Exception e){
            logger.error("er",e);
            return new ResponseMsg("2","查询出错");
        }
    }

    @RequestMapping("vs/lastWeek")
    @ResponseBody
    public com.smart.common.ResponseBody getLastXY(@RequestParam(required = false)Integer type){
        try{
            com.smart.common.ResponseBody responseMsg= statisticsService.getVsXy(1, type);
            logger.info(JSON.toJSONString(responseMsg));
            return responseMsg;
        }catch (Exception e){
            logger.error("error",e);
            return new ResponseMsg("2","查询出错");
        }
    }

    @RequestMapping("vs/nextWeek")
    @ResponseBody
    public com.smart.common.ResponseBody getNextXY(@RequestParam(required = false)Integer type){
        try{
            return statisticsService.getVsXy(2, type);
        }catch (Exception e){
            logger.error("error",e);
            return new ResponseMsg("2","查询出错");
        }
    }
    @RequestMapping("life/getXy/{module}")
    @ResponseBody
    public com.smart.common.ResponseBody getLifeXY(@PathVariable Integer module,@RequestParam(required = false)Integer type){
        try{
            return statisticsService.getLifeXy(module);
        }catch (Exception e){
            logger.error("error",e);
            return new ResponseMsg("2","查询出错");
        }
    }
    @RequestMapping("getTotal")
    @ResponseBody
    public com.smart.common.ResponseBody getTotal(){
        try{
            return statisticsService.getTotal(0);
        }catch (Exception e){
            logger.error("e!",e);
            return new ResponseMsg("2","查询出错");
        }
    }

}
