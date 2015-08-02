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
import com.smart.common.DateUtils;
import com.smart.dao.FinanceDao;
import com.smart.model.Apply;
import com.smart.model.CompanyInfo;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/07/16
 * Time: 20:26
 */
@Transactional(readOnly = true)
@Repository
public class FinanceDaoImpl extends BaseDaoImpl implements FinanceDao {
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean confirm(Long applyId) throws Exception {
        return super.update("update cash_apply set status=3,finish_time='"+DateTime.now().toString("yyyy-MM-dd HH:mm:ss")+"' where id="+applyId);  //To change body of implemented methods use File | Settings | File Templates.
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean refuse(Long applyId) throws Exception {
        return super.update("update cash_apply set status=2 ,set finish_time='"+ DateTime.now().toString("yyyy-MM-dd HH:mm:ss")+"' where id ="+applyId);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Long searchCount(String from, String to, Integer type) throws Exception {
        String sql ="select count(*) from cash_apply as a,user as b where 1=1 and a.user_id=b.userid ";
        String where = "";
//        if(type!=0){
            where+=" and b.roleType="+type;
//        }
        if(!Strings.isNullOrEmpty(from) ){

            where +=" and a.create_time>'"+from+"' ";
        }

        if( !Strings.isNullOrEmpty(to)){
            String tonew= DateUtils.plusDay(to,1);
            where+=" and a.create_time<='"+tonew+"'";
        }

        return super.getJdbcTemplate().queryForLong(sql+where);  //To change body of implemented methods use File | Settings | File Templates.
    }



    private String selectSql(Integer i,String from,String to){
        String where="";
        if(i!=null || i!=0){
            where +=" and  c.roletype="+i ;
        }
        if(!Strings.isNullOrEmpty(from) ){

            where +=" and a.create_time>'"+from+"' ";
        }

        if( !Strings.isNullOrEmpty(to)){
            String tonew= DateUtils.plusDay(to,1);
            where+=" and a.create_time<='"+tonew+"'";
        }

        switch (i){
            case 2:
                return "select a.*,b.contact_name,b.phone_number from cash_apply as a,hotel as b ,user as c where 1=1 and a.user_id=b.user_id and a.user_id = c.userId  "+where +" order by create_time,finish_time desc ";
            case 5:
                return "select a.*,b.contact_name,b.phone_number from cash_apply as a,view_spot as b ,user as c where 1=1 and a.user_id=b.user_id and a.user_id = c.userId "+where+" order by create_time,finish_time desc "; //"insert into view_spot(user_id,contact_name,view_spot_type,name,grade) values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
            case 6:
                return "select a.*,b.contact_name,b.phone_number from cash_apply as a,life as b ,user as c where 1=1 and a.user_id=b.user_id and a.user_id = c.userId "+where+" order by create_time,finish_time desc "; //"insert into view_spot(user_id,contact_name,view_spot_type,name,grade) values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
            case 4:
                return "select a.*,b.contact_name,b.phone_number from cash_apply as a,restaurant as b ,user as c where 1=1 and a.user_id=b.user_id and a.user_id = c.userId "+where+" order by create_time,finish_time desc "; //"insert into view_spot(user_id,contact_name,view_spot_type,name,grade) values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
            default:
                return "";
        }
    }

    @Override
    public List<Apply> search(Integer page, String from, String to, final Integer type) throws Exception {
        String sql =  selectSql(type,from,to)+ " limit "+(page-1)*10+","+10;
        logger.debug(sql);
        return getBySqlRowMapper(sql,new RowMapper<Apply>() {
            @Override
            public Apply mapRow(ResultSet rs, int rowNum) throws SQLException {
                if(rs.next()){
                    Apply apply= new Apply();
                    apply.setApplyDesc(rs.getString("apply_desc"));
                    apply.setContactName(rs.getString("contact_name"));
                    apply.setFinishTime(rs.getDate("finish_time"));
                    apply.setType(getRoleName(type));
                    apply.setId(rs.getLong("id"));
                    apply.setUserId(rs.getLong("user_id"));
                    apply.setMoney(rs.getFloat("money"));
                    apply.setPhoneNumber(rs.getString("phone_number"));
                    apply.setName(rs.getString("name"));
                    apply.setServiceCharge(rs.getFloat("service_charge"));
                    apply.setStatus(rs.getInt("status"));
                    apply.setTime(rs.getTimestamp("create_time"));
                    return apply;
                }

                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getRoleName(int i){
        switch (i){
            case 2:
                return "酒店";
            case 4:
                return "美食";
            case 5:
                return "景点";
            case 6:
                return "生活";
            default:
                return "";
        }
    }
}
