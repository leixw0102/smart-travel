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

import com.google.common.collect.Maps;
import com.smart.dao.SellerDao;
import com.smart.model.SellerInfo;
import com.smart.model.TypeConfig;
import com.smart.vo.SellerVo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.smart.common.Token;
/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/07/14
 * Time: 17:15
 */
@Transactional(readOnly = true)
@Repository
public class SellerDaoImpl extends BaseDaoImpl implements SellerDao {
    @Override
    public List<SellerInfo> getSeller(Integer pageNumber,Integer pageSize) throws Exception {
        String sql ="select * from user where roletype in (2,4,5,6) order by createtime desc limit "+(pageNumber-1)*pageSize+","+pageSize;
        logger.info(sql);
        return super.getBySqlRowMapper(sql,new RowMapper<SellerInfo>() {
            @Override
            public SellerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                SellerInfo info = new SellerInfo();
                info.setPwd(rs.getString("password"));
                info.setUserName(rs.getString("username"));
                info.setCreateTime(rs.getTimestamp("createtime"));
                int role=rs.getInt("roleType");
                info.setType(getRoleName(role));
                info.setContactName(rs.getString("contact_name") == null ? "" : rs.getString("contact_name"));
                info.setGrade(rs.getDouble("grade"));
                info.setRemark(rs.getString("mark")==null?"":rs.getString("mark"));
                info.setSellerName(rs.getString("seller_name")==null?"":rs.getString("seller_name"));
                info.setFree(rs.getFloat("servicefee_level"));
                return info;  //To change body of implemented methods use File | Settings | File Templates.
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

    public int get(int i){
        switch (i){
            case 1:
                return 2;
            case 2:
                return 5;
            case 3:
                return 6;
            case 4:
                return 4;
            default:
                return 0;
        }
    }
    @Override
    public Long count() throws Exception {
        return super.getJdbcTemplate().queryForObject("select count(*) from user where roleType in (2,4,5,6)",Long.class);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<Integer, Map<Integer, String>> getTypes() throws Exception {
        List<TypeConfig> configs=super.getBySqlRowMapper("select * from type_config",new RowMapper<TypeConfig>() {
            @Override
            public TypeConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
                TypeConfig config = new TypeConfig();
                config.setModuleId(rs.getInt("module"));
                config.setTypeId(rs.getInt("type_id"));
                config.setTypeName(rs.getString("type_name"));
                return config;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
       Map<Integer,Map<Integer,String>>  maps = Maps.newHashMap();
        for(TypeConfig config : configs){
            if(maps.containsKey(config.getModuleId())){
                Map<Integer,String> m=maps.get(config.getModuleId());
                m.put(config.getTypeId(),config.getTypeName());
                maps.put(config.getModuleId(),m);
            }else {
                Map<Integer,String> m = Maps.newHashMap();
                m.put(config.getTypeId(),config.getTypeName());
                maps.put(config.getModuleId(),m);

            }
        }
        return maps;
    }
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean addSeller(SellerVo info) throws Exception {
        String usekey =Token.getToken(info.getUserName(), String.valueOf(get(Integer.parseInt(info.getType()))), "1");
       return  super.update("insert into user(Mac,UserKey,username,password,roleType,servicefee_level,grade,contact_name,mark,level,seller_name) values('1','"+usekey+"','"+info.getUserName()+"','"+info.getPwd()+"'," +
                "'"+get(Integer.parseInt(info.getType()))+"','"+info.getFree()+"','"+info.getGrade()+"','"+info.getContactName()+"','"+info.getRemark()+"',1,'"+info.getSellerName()+"')");
    }
}
