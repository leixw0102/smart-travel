package com.smart.dao.impl;


import com.smart.common.Page;
import com.smart.dao.UserDao;
import com.smart.model.CashUserInfo;
import com.smart.model.NewsUserInfo;
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
        String sql="select * from news where del=0  order by create_time desc limit "+(pageNumber-1)*pageSize+","+pageSize ;
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
                info.setContent(rs.getString("content"));
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

    @Override
    public Long count() throws Exception {
        return super.getJdbcTemplate().queryForObject("select count(*) from news ", Long.class);  //To change body of implemented methods use File | Settings | File Templates.

    }

    @Override
    public List<CashUserInfo> searchCashUser(int i, int i1) throws Exception {
        return super.getBySqlRowMapper("select * from t_user where role=3 order by id desc limit "+(i-1)*i1+","+i1,new RowMapper<CashUserInfo>() {
            @Override
            public CashUserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                CashUserInfo info = new CashUserInfo();
                info.setMark(rs.getString("mark"));
                info.setContactName(rs.getString("alias"));
                info.setId(rs.getLong("id"));
                info.setPwd(rs.getString("pwd"));
                info.setUserName(rs.getString("user_name"));
                return info;
            }
        });  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long countCashUser() throws Exception {
        return super.getJdbcTemplate().queryForLong("select count(*) from t_user where role=3");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean saveFinaceUser(CashUserInfo info) throws Exception {
        return super.update("insert into t_user(user_name,pwd,phone,alias,mark,role) values('"+info.getUserName()+"','"+info.getPwd()+"','"+info.getPhone()+"','"+info.getContactName()+"','"+info.getMark()+"',3)");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CashUserInfo searchFinanceUser(CashUserInfo info) throws Exception {
        return super.getJdbcTemplate().query("select * from t_user where user_name='"+info.getUserName()+"' and pwd='"+info.getPwd()+"'",new ResultSetExtractor<CashUserInfo>() {
            @Override
            public CashUserInfo extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()){
                    CashUserInfo info = new CashUserInfo();
                    info.setMark(rs.getString("mark"));
                    info.setUserName(rs.getString("user_name"));
                    info.setPwd(rs.getString("pwd"));
                    info.setId(rs.getLong("id"));
                    info.setContactName(rs.getString("alias"));
                    info.setPhone(rs.getString("phone"));
                    return info;
                }
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });  //To change body of implemented methods use File | Settings | File Templates.
    }
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean deleteById(Long id) throws Exception {
        try{
            super.delete("delete from t_user where id="+id);  //To change body of implemented methods use File | Settings | File Templates.
            return true;
        }catch (Exception e){
            logger.error("delete by id error!",e);
            return false;
        }
    }
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, String old, String newPwd) throws Exception {
        return super.update("update t_user set pwd='"+newPwd+"' where id="+id);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CashUserInfo findUserByIdAndPwd(Long id, String old) throws Exception {
        return super.getJdbcTemplate().query("select * from t_user where id="+id+" and pwd='"+old+"'",new ResultSetExtractor<CashUserInfo>() {
            @Override
            public CashUserInfo extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()){
                    CashUserInfo info = new CashUserInfo();
                    info.setMark(rs.getString("mark"));
                    info.setUserName(rs.getString("user_name"));
                    info.setPwd(rs.getString("pwd"));
                    info.setId(rs.getLong("id"));
                    info.setContactName(rs.getString("alias"));
                    info.setPhone(rs.getString("phone"));
                    return info;
                }
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<NewsUserInfo> searchNewsUser(int page, Integer size) throws Exception {
        return super.getBySqlRowMapper("select * from t_user where role=2 order by id desc limit "+(page-1)*size+","+size,new RowMapper<CashUserInfo>() {
            @Override
            public CashUserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                CashUserInfo info = new CashUserInfo();
                info.setMark(rs.getString("mark"));
                info.setContactName(rs.getString("alias"));
                info.setId(rs.getLong("id"));
                info.setPwd(rs.getString("pwd"));
                info.setUserName(rs.getString("user_name"));
                return info;
            }
        }); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Long countNewsUser() throws Exception {
        return super.getJdbcTemplate().queryForLong("select count(*) from t_user where role=3");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean saveNewsUser(NewsUserInfo info) throws Exception {
        return super.update("insert into t_user(user_name,pwd,phone,alias,mark,role) values('"+info.getUserName()+"','"+info.getPwd()+"','"+info.getPhone()+"','"+info.getContactName()+"','"+info.getMark()+"',2)");//To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public NewsUserInfo searchNewsUser(NewsUserInfo info) throws Exception {
        return super.getJdbcTemplate().query("select * from t_user where user_name='"+info.getUserName()+"' and pwd='"+info.getPwd()+"'",new ResultSetExtractor<NewsUserInfo>() {
            @Override
            public NewsUserInfo extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()){
                    NewsUserInfo info = new NewsUserInfo();
                    info.setMark(rs.getString("mark"));
                    info.setUserName(rs.getString("user_name"));
                    info.setPwd(rs.getString("pwd"));
                    info.setId(rs.getLong("id"));
                    info.setContactName(rs.getString("alias"));
                    info.setPhone(rs.getString("phone"));
                    return info;
                }
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });    //To change body of implemented methods use File | Settings | File Templates.
    }

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean create(String join, String title, String content, String abs) throws Exception {
        return super.update("insert into news(title,content,picture,abs) values('"+title+"','"+content+"','"+join+"','"+abs+"')");  //To change body of implemented methods use File | Settings | File Templates.
    }


}
