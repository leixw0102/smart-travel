package com.smart.service;


import com.smart.common.ResponseBody;
import com.smart.model.*;
import com.smart.vo.UserVo;

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

    CashUserInfo searchFinanceUser(CashUserInfo info) throws Exception;

    boolean saveFinanceUser(CashUserInfo info) throws Exception;

    boolean deleteById(Long id) throws Exception;

    boolean updateById(Long id, String old, String newPwd) throws Exception;

    CashUserInfo findUserByIdAndPwd(Long id, String old) throws Exception;

    Page<NewsUserInfo> searchNewsUser(Integer page, int i) throws Exception;

    boolean saveNewsUser(NewsUserInfo info) throws Exception;
    NewsUserInfo searchNewsUser(NewsUserInfo info) throws Exception;

    ResponseBody userNewsList(Long page, int i, String time) throws Exception;
}
