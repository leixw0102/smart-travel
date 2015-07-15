package com.smart.dao;

import com.smart.model.UserInfo;
import com.smart.model.NewsInfo;
import java.util.List;
public interface UserDao {
    UserInfo sellerLogin(String user, String passwd)throws Exception;


    Long  isValid(String userName, Integer role, String format) throws Exception;

    boolean updateTime(Long id,String time)throws Exception;

    boolean create(String join, String title, String content, String abs) throws Exception;


    public List<NewsInfo> userNewsList(int pageNumber, int pageSize)throws Exception;
    public NewsInfo userNewsDetail(int id)throws Exception;
    public Long count() throws Exception;
}
