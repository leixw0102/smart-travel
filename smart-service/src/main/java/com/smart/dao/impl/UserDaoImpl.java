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

    @Override
    public Long count() throws Exception {
        return super.getJdbcTemplate().queryForObject("select count(*) from news ", Long.class);  //To change body of implemented methods use File | Settings | File Templates.

    }
}
