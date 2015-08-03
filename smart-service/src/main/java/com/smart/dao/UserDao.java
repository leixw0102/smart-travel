package com.smart.dao;

import com.smart.common.Page;
import com.smart.model.CashUserInfo;
import com.smart.model.NewsUserInfo;
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
    public Long count(String time, String to) throws Exception;

    List<CashUserInfo> searchCashUser(int i, int i1) throws Exception;

    long countCashUser() throws Exception;

    boolean saveFinaceUser(CashUserInfo info) throws Exception;

    CashUserInfo searchFinanceUser(CashUserInfo info) throws Exception;

    boolean deleteById(Long id) throws Exception;

    boolean updateById(Long id, String old, String newPwd) throws Exception;

    CashUserInfo findUserByIdAndPwd(Long id, String old) throws Exception;

    List<NewsUserInfo> searchNewsUser(int i, Integer page) throws Exception;

    Long countNewsUser() throws Exception;

    boolean saveNewsUser(NewsUserInfo info) throws Exception;
    NewsUserInfo searchNewsUser(NewsUserInfo info) throws Exception;

    Long count(String time)throws Exception;

    List<NewsInfo> userNewsList(Long page1, int i, String time, String to) throws Exception;

    NewsInfo view(Long id) throws Exception;

    boolean update(String join, String title, String content, String abs, Long id) throws Exception;
}
