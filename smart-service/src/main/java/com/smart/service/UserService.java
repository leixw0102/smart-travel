package com.smart.service;


import com.smart.model.CashUserInfo;
import com.smart.model.UserInfo;
import com.smart.vo.UserVo;
import com.smart.model.NewsInfo;
import java.util.List;
import java.util.Date;
import com.smart.common.Page;
public interface UserService {
    public UserInfo sellerLogin(String user, String passwd)throws Exception;

    boolean isValid(UserVo vo,Date date) throws Exception;

    public boolean  updateUseTime(Long id,Date use) throws Exception;

    public Page userNewsList(int pageNumber, int pageSize) throws Exception;

    public NewsInfo userNewsDetail(int id) throws Exception;
    public boolean create(String join, String title, String content, String abs) throws Exception;

    Page<CashUserInfo> searchCashUser(int i, int i1)throws Exception;
}
