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

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.smart.dao.SellerDao;
import com.smart.model.*;
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
                info.setT(Integer.parseInt(getRole(role)));
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

    private String updateSql(int i){
        switch (i){
            case 1:
//                return "insert into hotel(user_id,contact_name,type,name,grade) values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
                return "insert into hotel(user_id,contact_name,type,name,grade) values(?,?,?,?,?)";
            case 2:
//                return "insert into view_spot(user_id,contact_name,view_spot_type,name,grade) values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
                return "insert into view_spot(user_id,contact_name,view_spot_type,name,grade) values(?,?,?,?,?)";
            case 3:
//                return "insert into life(user_id,contact_name,life_type,name,grade) values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
                return "insert into life(user_id,contact_name,life_type,name,grade) values(?,?,?,?,?)";
            case 4:
//                return "insert into restaurant(user_id,contact_name,rt_type,name,grade) values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
                return "insert into restaurant(user_id,contact_name,rt_type,name,grade) values(?,?,?,?,?)";
            default:
                return "";
        }
    }
    private String updateCSql(int i,CompanyInfo info){
        switch (i){
            case 1:
//                return "insert into hotel(user_id,contact_name,type,name,grade) values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
                return "update  hotel set user_id="+info.getUserId()+",contact_name='"+info.getContactName()+"',type="+info.getSecondaryType()+",name='"+info.getName()+"',grade="+info.getGrade()+" where id="+info.getId();
            case 2:
//                return "insert into view_spot(user_id,contact_name,view_spot_type,name,grade) values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
                return "update view_spot set user_id="+info.getUserId()+",contact_name='"+info.getContactName()+"',view_spot_type="+info.getSecondaryType()+",name='"+info.getName()+"',grade="+info.getGrade()+" where id="+info.getId();//set user_id,contact_name,view_spot_type,name,grade) ";
            case 3:
//                return "insert into life(user_id,contact_name,life_type,name,grade) values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
                return "update life set user_id="+info.getUserId()+",contact_name='"+info.getContactName()+"',life_type="+info.getSecondaryType()+",name='"+info.getName()+"',grade="+info.getGrade()+" where id="+info.getId();
            case 4:
//                return "insert into restaurant(user_id,contact_name,rt_type,name,grade) values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
                return "update restaurant set user_id="+info.getUserId()+",contact_name='"+info.getContactName()+"',rt_type="+info.getSecondaryType()+",name='"+info.getName()+"',grade="+info.getGrade()+" where id="+info.getId();
            default:
                return "";
        }
    }
    @Transactional(rollbackFor = Exception.class,readOnly = false)
    @Override
    public Long addCompany(final CompanyInfo info) throws Exception {
        final String sql = updateSql(info.getType());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        super.getJdbcTemplate().update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setLong(1, info.getUserId());
                ps.setString(2,info.getContactName());
                ps.setLong(3, info.getSecondaryType() == null ? 1 : info.getSecondaryType());
                ps.setString(4,info.getName()==null?"":info.getName());
                ps.setDouble(5,info.getGrade()==null?4.0:info.getGrade());
//                ps.setDouble(6, info.getFree());
//                ps.setString(7,info.getRemark());
//                ps.setString(8,"1");
                return ps;
            }
        }, keyHolder);



        return keyHolder.getKey().longValue();
//        return super.update(updateSql(info.getType(),info));  //To change body of implemented methods use File | Settings | File Templates.
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

    @Override
    public CompanyInfo getCompanyByUserId(final Long id, final Integer type) throws Exception {
//        String sql = get
        return super.getJdbcTemplate().query(getSelectSql(type,id),new ResultSetExtractor<CompanyInfo>() {
            @Override
            public CompanyInfo extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()){
                    CompanyInfo info = new CompanyInfo();
                    info.setType(type);
                    info.setContactName(rs.getString("contact_name"));
                    info.setGrade(rs.getDouble("grade"));
                    info.setName(rs.getString("name"));
                    info.setSecondaryType(rs.getInt("type"));
                    info.setUserId(id);
                    info.setId(rs.getLong("id"));
                    return info;
                }
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });  //To change body of implemented methods use File | Settings | File Templates.
    }
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public Long updateCompany(CompanyInfo info) throws Exception {
       super.update(updateCSql(info.getType(),info));  //To change body of implemented methods use File | Settings | File Templates.
        return info.getId();
    }
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean deleteCategory(Long typeId) throws Exception {
        return super.update("delete from type_config where id="+typeId);  //To change body of implemented methods use File | Settings | File Templates.
    }
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean updateCategory(Long id, String name) throws Exception {
        return super.update("update type_config set type_name='"+name+"' where id="+id);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public JSONObject getCode(Long id) throws Exception {
        JSONObject object=super.getJdbcTemplate().query("select a.userId,a.roletype from user as a where userid="+id,new ResultSetExtractor<JSONObject>() {
             @Override
             public JSONObject extractData(ResultSet rs) throws SQLException, DataAccessException {
                 if(rs.next()){

                     JSONObject object = new JSONObject();
                     object.put("sellerId",rs.getLong("userid"));
                     object.put("sellerType",rs.getInt("roletype"));
                     return object;
                 }
                 return null;  //To change body of implemented methods use File | Settings | File Templates.
             }
         });  //To change body of implemented methods use File | Settings | File Templates.
        if(null ==object)  {
            return null;
        }
        long whereId=super.getJdbcTemplate().queryForLong("select id from "+getTypeSql(object.getInteger("sellerType")) +" where user_id="+id);
        object.put("whereId",whereId);
        String v=object.remove("sellerType").toString();
        object.put("sellerType",Integer.parseInt(getRole(Integer.parseInt(v))));
        return object;
    }

    @Override
    public List<TypeInfo> getTypes1() throws Exception {
//        List<Type> configs=super.getBySqlRowMapper("select * from type_config",new RowMapper<TypeConfig>() {
//            @Override
//            public Type mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Type config = new Type();
//                config.setId(rs.getInt("id"));
//                config.setTypeId(rs.getInt("type_id"));
//                config.setTypeName(rs.getString("type_name"));
//                return config;  //To change body of implemented methods use File | Settings | File Templates.
//            }
//        });
        List<TypeInfo> infos = Lists.newArrayList();
        for(int i=1;i<=4;i++){
            TypeInfo info = new TypeInfo();
            info.setType(i);
            info.setTypes(getTypesA(i));
            infos.add(info);
//            info.setName();
        }

        return infos;  //To change body of implemented methods use File | Settings | File Templates.
    }
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean addCategory(Long id, String name, int i) throws Exception {
        return super.update("insert into type_config(type_id,type_name,module,is_modify) values ("+i+",'"+name+"',"+id+",0)");  //To change body of implemented methods use File | Settings | File Templates.
    }


    public String getTypeSql(int i){
        switch (i){
            case 2:
                return "hotel";
            case 4:
                return "restaurant";
            case 5:
                return "view_spot";
            case 6:
                return "life";
            default:
                return "";
        }
    }

    public String getSelectSql(int type,Long id){
        switch (type){
            case 1:
                return "select a.id, a.name,a.grade ,a.contact_name, a.type as type from  hotel as a where user_id="+id;// values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
            case 2:
                return "select a.id,  a.name,a.grade ,a.contact_name, a.view_spot_type as type  from  view_spot as a where user_id="+id;//"insert into view_spot(user_id,contact_name,view_spot_type,name,grade) values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
            case 3:
                return "select a.id,  a.name,a.grade ,a.contact_name, a.life_type as type from  life as a where user_id="+id;//"insert into life(user_id,contact_name,life_type,name,grade) values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
            case 4:
                return "select a.id,  a.name,a.grade ,a.contact_name, a.rt_type as type from  restaurant as a where user_id="+id;//"insert into restaurant(user_id,contact_name,rt_type,name,grade) values("+info.getUserId()+",'"+info.getContactName()+"',"+info.getSecondaryType()+",'"+info.getName()+"',"+info.getGrade()+")";
            default:
                return "";
        }
    }

    private String getRole(int role) {
        switch (role){
            case 2:
                return 1+"";
            case 5:
                return "2";
            case 6:
                return "4";
            case 4:
                return "3";
            default:
                return "0";
        }
    }
    @Override
    public List<Type> getTypesA(Integer type) throws Exception {
        List<Type> configs=super.getBySqlRowMapper("select * from type_config where module="+type +" order by type_id ",new RowMapper<Type>() {
            @Override
            public Type mapRow(ResultSet rs, int rowNum) throws SQLException {
                Type config = new Type();
                config.setId(rs.getLong("id"));
                config.setType(rs.getInt("type_id"));
                config.setName(rs.getString("type_name"));
//                config.setModify(rs.getInt(""));
                return config;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
       return configs;
    }
}
