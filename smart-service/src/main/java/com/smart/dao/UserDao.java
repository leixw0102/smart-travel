package com.smart.dao;

import com.smart.model.UserInfo;
import com.smart.model.NewsInfo;
import java.util.List;
public interface UserDao {
	


    public List<NewsInfo> userNewsList(int pageNumber, int pageSize)throws Exception;
    public NewsInfo userNewsDetail(int id)throws Exception;
    public Long count() throws Exception;
}
