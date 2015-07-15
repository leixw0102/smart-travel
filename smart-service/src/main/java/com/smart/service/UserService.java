package com.smart.service;


import com.smart.model.UserInfo;
import com.smart.vo.UserVo;
import com.smart.model.NewsInfo;
import java.util.List;
import java.util.Date;
import com.smart.common.Page;
public interface UserService {

    public Page userNewsList(int pageNumber, int pageSize) throws Exception;

    public NewsInfo userNewsDetail(int id) throws Exception;
}
