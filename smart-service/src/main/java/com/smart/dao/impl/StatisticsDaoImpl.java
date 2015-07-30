package com.smart.dao.impl;/*
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

import com.smart.dao.StatisticsDao;
import com.smart.model.MapInfo;
import com.smart.model.XYModel;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/07/30
 * Time: 10:09
 */
@Transactional(readOnly = true)
@Repository
public class StatisticsDaoImpl extends BaseDaoImpl implements StatisticsDao {
    @Override
    public List<XYModel> getHotelXY(int i, Integer type) throws Exception {

        String sql= getHotelSql(i, type);

        return super.getBySqlRowMapper(sql,new RowMapper<XYModel>() {
            @Override
            public XYModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                XYModel model = new XYModel();
                model.setDay(rs.getString("time"));
                model.setValue(rs.getString("total"));
                return model;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<MapInfo> getHotelMap() throws Exception {
        return super.getBySqlRowMapper("select sum(a.num) as total,b.x,b.y from hotel_order as a,hotel as b where a.hotel_id = b.id ", new RowMapper<MapInfo>() {
            @Override
            public MapInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                MapInfo info = new MapInfo();
                info.setInfo(rs.getString("total"));
                info.setX(rs.getString("x"));
                info.setY(rs.getString("y"));
                return info;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });  //To change body of implemented methods use File | Settings | File Templates.
    }

    private String getHotelSql(int i, Integer type) {
        //i==1 实际 i==2下周
        if(i==1){
            return "select sum(total_price) as total,to_time as time from hotel_order where order_status_id=4 and order_pay_type_id=3 and to_time>='"+getDay(-7)+"'  and  to_time<'"+getDay(-1)+"' group by to_time desc ";
        }
        if(i==2){
            return "select sum(total_price) as total,from_time as time from hotel_order where order_status_id=4 and order_pay_type_id=3 and from_time>='"+getDay(1)+"'  and  from_time<'"+getDay(7)+"' group by from_time desc ";
        }
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    private String getDay(int i){
        return DateTime.now().plusDays(i).toString("yyyy-MM-dd");
    }

}
