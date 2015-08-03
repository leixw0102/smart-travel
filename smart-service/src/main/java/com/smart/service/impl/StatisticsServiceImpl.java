package com.smart.service.impl;/*
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

import com.google.common.collect.Lists;
import com.smart.common.Page;
import com.smart.common.ResponseBody;
import com.smart.common.ResponseMsg;
import com.smart.dao.StatisticsDao;
import com.smart.model.MapInfo;
import com.smart.model.TotalInfo;
import com.smart.model.XY;
import com.smart.model.XYModel;
import com.smart.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/07/30
 * Time: 09:51
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsDao statisticsDao;
    @Override
    public ResponseBody getHotelXy(int i, Integer type, Integer totalType) throws Exception {
        Page<XY> msg = new Page<XY>() {
            @Override
            protected String listAlias() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        List<String> x= Lists.newArrayList();
        List<String> y = Lists.newArrayList();
        List<XYModel> models=statisticsDao.getHotelXY(i, type,totalType);
        for(XYModel model:models){
            x.add(model.getDay());
            y.add(model.getValue());
        }
        XY x_=new XY("category");
        x_.setData(x);
        XY y_ = new XY("line");
        y_.setData(y);
        List<XY> i1=Lists.newArrayList();
        i1.add(x_);
        i1.add(y_);

        msg.setMessages(i1);
        return msg;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseBody getHotelMap(int type) throws Exception {
        Page<MapInfo> maps = new Page<MapInfo>() {
            @Override
            protected String listAlias() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        List<MapInfo> infos = statisticsDao.getHotelMap(type);
        maps.setMessages(infos);
        return maps;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseBody getLifeXy(Integer module, Integer type) throws Exception {
        Page<XY> msg = new Page<XY>() {
            @Override
            protected String listAlias() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        List<String> x= Lists.newArrayList();
        List<String> y = Lists.newArrayList();
        List<XYModel> models=statisticsDao.getLifeXY(module,type);
        for(XYModel model:models){
            x.add(model.getDay());
            y.add(model.getValue());
        }
        XY x_=new XY("category");
        x_.setData(x);
        XY y_ = new XY("bar");
        y_.setData(y);
        List<XY> i1=Lists.newArrayList();
        i1.add(x_);
        i1.add(y_);

        msg.setMessages(i1);
        return msg;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseBody getTotal(Integer type) throws Exception {
        ResponseMsg<TotalInfo> msg = new ResponseMsg<TotalInfo>();
        String[] current=statisticsDao.getTotal(0);
        TotalInfo info = new TotalInfo();
        info.setCurrent_day(current[0]);
        info.setCurrent_value(current[1]);
        String[] last=statisticsDao.getTotal(-1);
        info.setLast_value(last[1]);
        info.setTotal_value(statisticsDao.total());
        msg.setInfo(info);
        return msg;
    }

    @Override
    public ResponseBody getVsXy(int i, Integer type) throws Exception {
        Page<XY> msg = new Page<XY>() {
            @Override
            protected String listAlias() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        List<String> x= Lists.newArrayList();
        List<String> y = Lists.newArrayList();
        List<XYModel> models=statisticsDao.getVsXY(i, type);
        for(XYModel model:models){
            x.add(model.getDay());
            y.add(model.getValue());
        }
        XY x_=new XY("category");
        x_.setData(x);
        XY y_ = new XY("line");
        y_.setData(y);
        List<XY> i1=Lists.newArrayList();
        i1.add(x_);
        i1.add(y_);

        msg.setMessages(i1);
        return msg;
         //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseBody getCateXy(Integer type) throws Exception {
        Page<XY> msg = new Page<XY>() {
            @Override
            protected String listAlias() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        List<String> x= Lists.newArrayList();
        List<String> y = Lists.newArrayList();
        List<XYModel> models=statisticsDao.getCateXY(type);
        for(XYModel model:models){
            x.add(model.getDay());
            y.add(model.getValue());
        }
        XY x_=new XY("category");
        x_.setData(x);
        XY y_ = new XY("bar");
        y_.setData(y);
        List<XY> i1=Lists.newArrayList();
        i1.add(x_);
        i1.add(y_);

        msg.setMessages(i1);
        return msg;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());
}
