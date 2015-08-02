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
import com.smart.model.TotalInfo;
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
    public List<MapInfo> getHotelMap(int type) throws Exception {
        String sql="";
        if(type==1){
            sql="select sum(a.num) as total,b.x,b.y from hotel_order as a,hotel as b where a.hotel_id = b.id ";
        } else if(type ==2){
            sql ="select sum(a.num) as total,b.x,b.y from view_spot_order as a,view_spot as b where a.vs_id = b.id ";
        }

        return super.getBySqlRowMapper(sql, new RowMapper<MapInfo>() {
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

    @Override
    public List<XYModel> getLifeXY(Integer module) throws Exception {
        logger.debug("select count(a.id) as total ,date(a.create_time) as time from pay_now_order  as a, life as b where b.id=a.where_id  and a.where_type_id =4 and b.life_type="+module+" and a.create_time>='"+getDay(-30)+"' group by date(a.create_time) desc ");
        return super.getBySqlRowMapper("select count(a.id) as total ,date(a.create_time) as time from pay_now_order  as a, life as b where b.id=a.where_id  and a.where_type_id =4 and b.life_type="+module+" and a.create_time>='"+getDay(-30)+"' group by date(create_time) desc ",new RowMapper<XYModel>() {
            @Override
            public XYModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                XYModel model = new XYModel();
                model.setDay(rs.getString("time"));
                model.setValue(rs.getString("total"));
                return model;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    @Override
    public String[] getTotal(Integer type) throws Exception {
//       String day=DateTime.now().toYearMonthDay();
        String current=DateTime.now().plusMonths(type).toString("yyyy-MM");
        String next=DateTime.now().plusMonths(type+1).toString("yyyy-MM");
       Double hotel_total=super.getJdbcTemplate().queryForObject("select sum(total_price) from hotel_order where create_time<='"+next+"' and create_time>'"+current+"'",Double.class);
        Double vs_total=super.getJdbcTemplate().queryForObject("select sum(total_price) from view_spot_order where create_time<='"+next+"' and create_time>'"+current+"'",Double.class);
        Double pay_total=super.getJdbcTemplate().queryForObject("select sum(total_price) from pay_now_order where create_time<='"+next+"' and create_time>'"+current+"'",Double.class);
        if(null == hotel_total){
            hotel_total=0d;
        }
        if(null == vs_total){
            vs_total=0d;
        }
        if(pay_total==null){
            pay_total=0d;
        }
        return new String[]{current,(hotel_total+vs_total+pay_total)+""};
    }

    @Override
    public String total() throws Exception {
        Double hotel_total=super.getJdbcTemplate().queryForObject("select sum(total_price) from hotel_order ",Double.class);
        Double vs_total=super.getJdbcTemplate().queryForObject("select sum(total_price) from view_spot_order ",Double.class);
        Double pay_total=super.getJdbcTemplate().queryForObject("select sum(total_price) from pay_now_order ",Double.class);
        if(null == hotel_total){
            hotel_total=0d;
        }
        if(null == vs_total){
            vs_total=0d;
        }
        if(pay_total==null){
            pay_total=0d;
        }
        return (hotel_total+vs_total+pay_total)+"";
    }

    @Override
    public List<XYModel> getVsXY(int i, Integer type) throws Exception {

        String sql= getVsSql(i, type);

        return super.getBySqlRowMapper(sql,new RowMapper<XYModel>() {
            @Override
            public XYModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                XYModel model = new XYModel();
                model.setDay(rs.getString("time"));
                model.setValue(rs.getString("total"));
                return model;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });    //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<XYModel> getCateXY(Integer type) throws Exception {
        return super.getBySqlRowMapper("select count(a.id) as total ,date(a.create_time) as time from pay_now_order  as a, restaurant as b where b.id=a.where_id  and a.where_type_id =3 and a.create_time>='"+getDay(-30)+"' group by date(a.create_time) desc ",new RowMapper<XYModel>() {
            @Override
            public XYModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                XYModel model = new XYModel();
                model.setDay(rs.getString("time"));
                model.setValue(rs.getString("total"));
                return model;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });  //To change body of implemented methods use File | Settings | File Templates.
    }

    private String getVsSql(int i, Integer type) {
        if(i==1){
            return "select sum(total_price) as total,pay_time as time from view_spot_order where order_status_id=4 and order_pay_type_id=3 and to_time>='"+getDay(-7)+"'  and  pay_time<'"+getDay(-1)+"' group by pay_time desc ";
        }
        if(i==2){
            return "select sum(total_price) as total,from_time as time from view_spot_order where order_status_id=4 and order_pay_type_id=3 and from_time>='"+getDay(1)+"'  and  from_time<'"+getDay(7)+"' group by from_time desc ";
        }
        return null;  //To change body of created methods use File | Settings | File Templates.
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
    public static void main(String[]args){
        System.out.println(DateTime.now().toString("yyyy-MM"));
    }

}
