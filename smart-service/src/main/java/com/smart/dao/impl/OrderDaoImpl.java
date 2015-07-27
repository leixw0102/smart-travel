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

import com.smart.dao.OrderDao;
import com.smart.model.OrderInfo;
import com.smart.model.UserClientInfo;
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
    public Long count(String from, String to, Integer type, Integer orderType) throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private String getOrderSql(String from, String to, Integer type, Integer orderType){
        switch (type){
            case 2:
                return "";
            case 4:
                return "";
            default:
                return "";
        }
    }

    @Override
    public List<OrderInfo> search(Integer page, String from, String to, Integer type, Integer orderType) throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
}
