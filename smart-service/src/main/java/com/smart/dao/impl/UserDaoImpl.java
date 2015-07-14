package com.smart.dao.impl;


import com.smart.dao.UserDao;
import com.smart.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.smart.model.NewsInfo;
import java.util.List;

@Transactional(readOnly = true)
@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
	private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);


    @Override
    public UserInfo sellerLogin(String user, String passwd) throws Exception {
        return super.getJdbcTemplate().query("select * from t_user where user_name='"+user+"' and pwd = '"+passwd+"'",new ResultSetExtractor<UserInfo>() {
            @Override
            public UserInfo extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()){
                    UserInfo info=new UserInfo();
                    info.setAlias(rs.getString("alias"));
                    info.setId(rs.getLong("id"));
                    info.setMark(rs.getString("mark"));
                    info.setPhone(rs.getString("phone"));
                    info.setRole(rs.getInt("role"));
                    info.setPwd(rs.getString("pwd"));
                    info.setUseTime(rs.getDate("use_time"));
                    info.setUserName(rs.getString("user_name"));
                    return info;
                }
                return null;
            }
        });
    }

    @Override
    public Long isValid(String userName, Integer role, String format) throws Exception {
       Long id= super.jdbcTemplate.queryForLong("select id from t_user where t_user where user_name='"+userName+"' and role='"+role+"' and use_time ='"+format+"'", new RowMapper<Long>() {
           @Override
           public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
               if (rs.next()) {
                   return rs.getLong("id");
               }
               return null;
           }
       });
        return id;
    }
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean updateTime(Long id, String time) throws Exception {
        return super.update("update t_user set use_time='"+time+"' where id="+id);
    }

    @Override
    public List<NewsInfo> userNewsList(int pageNumber, int pageSize)
            throws Exception {
        String sql="select * from news where del=0 limit "+(pageNumber-1)*pageSize+","+pageSize ;
        return jdbcTemplate.query(sql, new RowMapper<NewsInfo>(){

            @Override
            public NewsInfo mapRow(ResultSet rs, int arg1)
                    throws SQLException {
                // TODO Auto-generated method stub
                //if(rs.next()){
                NewsInfo info = new NewsInfo();
                info.setId(rs.getLong("id"));
                info.setTitle(rs.getString("title"));
                info.setAbs(rs.getString("abs"));
                info.setCreateTime(rs.getDate("create_time"));
                info.setPicture(rs.getString("picture"));
                return info;
                //}
                //return null;
            }});
    }

    @Override
    public NewsInfo userNewsDetail(int id) throws Exception {
        String sql="select * from news where del=0 and id='"+id+"'" ;
        return jdbcTemplate.query(sql, new ResultSetExtractor<NewsInfo>(){
            @Override
            public NewsInfo extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if(rs.next()){
                    //UserInfo userInfo = new UserInfo();
                    NewsInfo info = new NewsInfo();
                    info.setId(rs.getLong("id"));
                    info.setTitle(rs.getString("title"));
                    info.setAbs(rs.getString("abs"));
                    info.setCreateTime(rs.getDate("create_time"));
                    info.setPicture(rs.getString("picture"));
                    info.setContent(rs.getString("content"));
                    return info;
                }
                return null;
            }});
    }
}
