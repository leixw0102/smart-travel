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

import com.google.common.base.Strings;
import com.smart.dao.OrderDao;
import com.smart.model.Apply;
import com.smart.model.OrderInfo;
import com.smart.model.UserClientInfo;
import org.joda.time.DateTime;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
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
 * Date: 2015/07/19
 * Time: 17:38
 */
@Transactional(readOnly = true)
@Repository
public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    @Override
    public Long count(Long id, String from, String to, Integer type, Integer orderType) throws Exception {
        String cause="";
        if(!Strings.isNullOrEmpty(from) && !Strings.isNullOrEmpty(to)){
            cause+=" and a.create_time >='"+from+"' and a.create_time<='"+to+"'";
        }
        logger.info("select count(*) "+ getOrderSql(type) +" where a.buyer_id="+id+cause+" and a.order_status_id="+orderType);
        return super.getJdbcTemplate().queryForLong("select count(*) "+ getOrderSql(type) +" where a.buyer_id="+id+cause+" and a.order_status_id="+orderType +"  and a.seller_id=b.user_id" );  //To change body of implemented methods use File | Settings | File Templates.
    }

    private String getOrderSql( Integer type){

        switch (type){
            case 2:
                return " from hotel_order as a,hotel as b ";
            case 4:
                return " from pay_now_order as a,restaurant b ";
            case 5:
                return " from view_spot_order as a,view_spot as b ";
            case 6:
                return " from pay_now_order as a ,life as b";
            default:
                return "  " ;
        }
    }

    @Override
    public List<OrderInfo> search(Long id, Integer page, String from, String to, Integer type, final Integer orderType) throws Exception {
        String cause="";
        if(!Strings.isNullOrEmpty(from) && !Strings.isNullOrEmpty(to)){
            cause+=" and a.create_time >='"+from+"' and a.create_time<='"+to+"'";
        }
        logger.info("select a.order_id,a.id,a.create_time,b.name,a.order_status_id "+getOrderSql(type)+" where a.buyer_id="+id+cause+" and a.order_status_id="+orderType +" and a.seller_id=b.user_id");
        final String sql = "select a.order_id,a.id,a.create_time,b.name,a.order_status_id "+getOrderSql(type)+" where a.buyer_id="+id+cause+" and a.order_status_id="+orderType +" and a.seller_id=b.user_id limit "+(page-1)*5+",5";
        return super.getBySqlRowMapper(sql,new RowMapper<OrderInfo>() {
            @Override
            public OrderInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                OrderInfo info = new OrderInfo();
                info.setId(rs.getLong("id"));
                info.setCreateTime(new DateTime(rs.getTimestamp("create_time").getTime()).toString("yyyy-MM-dd HH:mm:ss"));
                info.setName(rs.getString("name"));
                info.setOrderId(rs.getLong("order_id"));
                info.setStatus(getOrderStatus(orderType));
                return info;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });  //To change body of implemented methods use File | Settings | File Templates.
    }

    private String getOrderStatus(int i){
        switch (i){
            case 1:
                return "待支付";
            case 2:
                return "已取消";
            case 4:
                return "已支付";
            case 5:
                return "已退款";
            default:
                return "其他";
        }
    }

    @Override
    public Long countUserInfos() throws Exception {
        return super.getJdbcTemplate().queryForLong("select count(*) from user where roletype=3 and is_delete=0");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<UserClientInfo> getUsersByPage(Integer page) throws Exception {
        String sql = "select a.userid,a.username,a.mark,a.createTime,b.name from user as a ,other_msg as b where a.userId=b.user_id and a.roletype=3 and a.is_delete=0 order by a.createtime desc limit "+(page-1)*15+",15";
        return super.getBySqlRowMapper(sql,new RowMapper<UserClientInfo>() {
            @Override
            public UserClientInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserClientInfo info = new UserClientInfo();
                info.setMark(rs.getString("mark")==null?"":rs.getString("mark"));
                info.setContactName(rs.getString("name"));
                info.setCreateDate(rs.getTimestamp("createtime"));
                info.setId(rs.getLong("userId"));
                info.setUserName(rs.getString("username"));
                return info;  //To change body of implemented methods use File | Settings | File Templates.
            }
        }); //To change body of implemented methods use File | Settings | File Templates.
    }
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean deleteClientUserById(Long id) throws Exception {
        return super.update("update user set is_delete=1 where userid= "+id);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String[] getApplyTime(Long id) throws Exception {
       List<Apply> applies= super.getBySqlRowMapper("select create_time from cash_apply where user_id="+id + " order by create_time desc",new RowMapper<Apply>() {
            @Override
            public Apply mapRow(ResultSet rs, int rowNum) throws SQLException {
                Apply apply = new Apply();
                apply.setTime(rs.getTimestamp("create_time"));

                return apply;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });  //To change body of implemented methods use File | Settings | File Templates.
        if(applies.size()==0){
            return new String[]{};
        }                    if(applies.size()==1){
            return new String[]{applies.get(0).getTime()+""};
        } else {
            return new String[]{applies.get(0).getTime()+"",applies.get(1).getTime()+""};

        }
    }

    @Override
    public int getTypeById(Long id) throws Exception {
        return super.getJdbcTemplate().query("select roletype from user where userid="+id,new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()){
                    return rs.getInt("roletype");
                };  //To change body of implemented methods use File | Settings | File Templates.
                return -1;
            }
        });  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Long count(Long id, String[] times, int type, int type1, int orderType) throws Exception {

        return super.getJdbcTemplate().queryForLong("select count(*) "+ getOrderSql1(type,times) +" and a.buyer_id="+id+"  and a.seller_id=b.user_id" );
    }

    private String getOrderSql1(int type, String[] times) {
        String ab="";
        if(type==2){
            ab="a.use_time";
        }else{
            ab="a.pay_time";
        }
        String cause="";
        if(times==null ||times.length==0){

        }else{
            if(times.length==1){
                cause+= " and "+ab+"<='"+new DateTime(Long.parseLong(times[0])).toString("yyyy-MM-dd HH:mm:ss")+"'";
            }else{
                cause+=" and "+ab+"<='"+new DateTime(Long.parseLong(times[0])).toString("yyyy-MM-dd HH:mm:ss") +"' "+ab+">='"+new DateTime(times[1]).toString("yyyy-MM-dd HH:mm:ss")+"'";
            }

        }
        switch (type){
            case 2:
                return " from hotel_order as a,hotel as b where 1=1 "+cause;
            case 4:
                return " from pay_now_order as a,restaurant b where 1=1 "+cause+" and a.order_pay_type_id=3 ";
            case 5:
                return " from view_spot_order as a,view_spot as b where 1=1 "+cause +" and a.order_pay_type_id=3 ";
            case 6:
                return " from pay_now_order as a ,life as b where 1=1"+cause+" and a.order_pay_type_id=3 ";
            default:
                return "  " ;
        }
    }


    @Override
    public List<OrderInfo> search(Long id, Integer page, String[] times, int type, int type1, int orderType) throws Exception {

        final String sql = "select a.order_id,a.id,a.create_time,b.name,a.order_status_id "+getOrderSql1(type,times)+" and a.buyer_id="+id+"  and a.seller_id=b.user_id limit "+(page-1)*5+",5";
        return super.getBySqlRowMapper(sql,new RowMapper<OrderInfo>() {
            @Override
            public OrderInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                OrderInfo info = new OrderInfo();
                info.setId(rs.getLong("id"));
                info.setCreateTime(new DateTime(rs.getTimestamp("create_time").getTime()).toString("yyyy-MM-dd HH:mm:ss"));
                info.setName(rs.getString("name"));
                info.setOrderId(rs.getLong("order_id"));
                info.setStatus(getOrderStatus(rs.getInt("order_status_id")));
                return info;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Long count(String from, String to, Integer type, Integer orderType, String name) throws Exception {
        String cause="";
        if(!Strings.isNullOrEmpty(from) && !Strings.isNullOrEmpty(to)){
            cause+=" and a.create_time >='"+from+"' and a.create_time<='"+to+"'";
        }
        if(!Strings.isNullOrEmpty(name)){
            cause+=" and b.name like '%"+name+"%'";
        }
        logger.info("select count(*) "+ getOrderSql(type) +" where 1=1 "+cause+" and a.order_pay_type_id in (3,4)");
        return super.getJdbcTemplate().queryForLong("select count(*) "+ getOrderSql(type) +" where 1=1 "+cause+" and a.order_pay_type_id in (3,4)  and a.seller_id=b.user_id" ); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<OrderInfo> search(Integer page, String from, String to, Integer type, Integer orderType, String name) throws Exception {
        String cause="";
        if(!Strings.isNullOrEmpty(from) && !Strings.isNullOrEmpty(to)){
            cause+=" and a.create_time >='"+from+"' and a.create_time<='"+to+"'";
        }
        if(!Strings.isNullOrEmpty(name)){
            cause+=" and b.name like '%"+name+"%'";
        }
        logger.info(getSqlForCompleted(type)+" where 1=1 "+cause+" and a.order_pay_type_id in (3,4) and a.seller_id=b.user_id");
        final String sql = getSqlForCompleted(type)+" where 1=1 "+cause+" and a.order_pay_type_id in (3,4) and a.seller_id=b.user_id limit "+(page-1)*10+",10";
        return super.getBySqlRowMapper(sql,new RowMapper<OrderInfo>() {
            @Override
            public OrderInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                OrderInfo info = new OrderInfo();
                info.setId(rs.getLong("id"));
                if(rs.getTimestamp("time")!=null){
                    info.setCreateTime(new DateTime(rs.getTimestamp("time").getTime()).toString("yyyy-MM-dd HH:mm:ss"));
                }
                info.setName(rs.getString("name"));
                info.setOrderId(rs.getLong("order_id"));
                info.setStatus(getOrderStatus(rs.getInt("order_status_id")));
                info.setMoney(rs.getFloat("total_price"));
                info.setContactName(rs.getString("contact_name"));
                info.setPhoneNumber(rs.getString("phone_number"));
                return info;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getSqlForCompleted(Integer type){
        switch (type){
            case 2:
                return "select a.total_price,b.phone_number,b.contact_name, a.order_id,a.id,a.use_time as time,b.name,a.order_status_id from hotel_order as a,hotel as b ";
            case 4:
                return "select a.total_price,b.phone_number,b.contact_name, a.order_id,a.id,a.pay_time as time,b.name,a.order_status_id from pay_now_order as a,restaurant b ";
            case 5:
                return "select a.total_price,b.phone_number,b.contact_name, a.order_id,a.id,a.pay_time as time,b.name,a.order_status_id from view_spot_order as a,view_spot as b ";
            case 6:
                return "select a.total_price,b.phone_number,b.contact_name, a.order_id,a.id,a.pay_time as time,b.name,a.order_status_id from pay_now_order as a ,life as b";
            default:
                return "  " ;
        }
    }
}
