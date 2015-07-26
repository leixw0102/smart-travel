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
import com.smart.model.CompanyInfo;
import com.smart.model.SellerInfo;
import com.smart.model.TypeConfig;
import com.smart.vo.SellerVo;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
                info.setId(rs.getLong("userId"));
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

    @Override
    public Map<Integer, String> getTypes(Integer type) throws Exception {
        List<TypeConfig> configs=super.getBySqlRowMapper("select * from type_config where module="+type,new RowMapper<TypeConfig>() {
            @Override
            public TypeConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
                TypeConfig config = new TypeConfig();
                config.setModuleId(rs.getInt("module"));
                config.setTypeId(rs.getInt("type_id"));
                config.setTypeName(rs.getString("type_name"));
                return config;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
       Map<Integer,String>  maps = Maps.newHashMap();
       for(TypeConfig config : configs){
           maps.put(config.getTypeId(),config.getTypeName());
       }
        return maps;
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public Long addSeller(final SellerVo info) throws Exception {
        final String usekey =Token.getToken(info.getUserName(), String.valueOf(get(Integer.parseInt(info.getType()))), "1");


        final String sql="insert into user(Mac,UserKey,username,password,roleType,servicefee_level,mark,level) values(?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        super.getJdbcTemplate().update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1,"1");
                ps.setString(2,usekey);
                ps.setString(3,info.getUserName());
                ps.setString(4,info.getPwd());
                ps.setInt(5, get(Integer.parseInt(info.getType())));
                ps.setDouble(6, info.getFree());
                ps.setString(7,info.getRemark());
                ps.setString(8,"1");
                return ps;
            }
        }, keyHolder);



        return keyHolder.getKey().longValue();
//       return  super.update("insert into user(Mac,UserKey,username,password,roleType,servicefee_level,mark,level) values('1','"+usekey+"','"+info.getUserName()+"','"+info.getPwd()+"'," +
//                "'"+get(Integer.parseInt(info.getType()))+"','"+info.getFree()+"','"+info.getRemark()+"',1')");
//        Connection connection = super.getConnection();
//        connection.setAutoCommit(false);
//        return false;
    }

    private String updateSql(int i,CompanyInfo info){
        switch (i){
                case 1:
                    return "insert into hotel(user_id,contact_name,type,name,grade) values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
                case 2:
                    return "insert into view_spot(user_id,contact_name,view_spot_type,name,grade) values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
                case 3:
                    return "insert into life(user_id,contact_name,life_type,name,grade) values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
                case 4:
                    return "insert into restaurant(user_id,contact_name,rt_type,name,grade) values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
                default:
                    return "";
        }
    }
    @Transactional(rollbackFor = Exception.class,readOnly = false)
    @Override
    public boolean addCompany(CompanyInfo info) throws Exception {

        return super.update(updateSql(info.getType(),info));  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean findByPhone(String userName) throws Exception {
       return super.getJdbcTemplate().query("select userId from user where username='" + userName + "'", new ResultSetExtractor<Boolean>() {
           @Override
           public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
               return rs.next();  //To change body of implemented methods use File | Settings | File Templates.
           }
       }) ;
//        return super.getJdbcTemplate().queryForLong(;  //To change body of implemented methods use File | Settings | File Templates.
    }
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean addAccount(Long userId) throws Exception {

        return super.update("insert into account(userId) values("+userId+")");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SellerVo getUserById(Long id) throws Exception {
        return super.getJdbcTemplate().query("select * from user where userId="+id,new ResultSetExtractor<SellerVo>() {
            @Override
            public SellerVo extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()){
                    SellerVo info = new SellerVo();
                    info.setPwd(rs.getString("password"));
                    info.setUserName(rs.getString("username"));
                    info.setCreateTime(rs.getTimestamp("createtime"));
                    int role=rs.getInt("roleType");
                    info.setType(getRole(role));
                    info.setContactName(rs.getString("contact_name") == null ? "" : rs.getString("contact_name"));
                    info.setGrade(rs.getDouble("grade"));
                    info.setRemark(rs.getString("mark")==null?"":rs.getString("mark"));
                    info.setSellerName(rs.getString("seller_name")==null?"":rs.getString("seller_name"));
                    info.setFree(rs.getFloat("servicefee_level"));
                    info.setId(rs.getLong("userId"));
                    return info;
                }
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

        });  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public long updateSeller(SellerVo info) throws Exception {
        final String usekey =Token.getToken(info.getUserName(), String.valueOf(get(Integer.parseInt(info.getType()))), "1");
         super.update("update user set Mac='1',UserKey='"+usekey+"',username='"+info.getUserName()+"',password='"+info.getPwd()+"',roleType="+get(Integer.parseInt(info.getType()))+",servicefee_level="+info.getFree()+",mark='"+info.getRemark()+"',level='1' where userId= "+info.getId());  //To change body of implemented methods use File | Settings | File Templates.
        return info.getId();
    }


    private String getRole(int role) {
        switch (role){
                    case 2:
                        return 1+"";
                    case 5:
                        return "2";
                    case 6:
                        return "3";
                    case 4:
                        return "4";
                    default:
                        return "0";
                }
    }
}
