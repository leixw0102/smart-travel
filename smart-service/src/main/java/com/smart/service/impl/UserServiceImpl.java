package com.smart.service.impl;

import com.smart.common.Page;
import com.smart.common.DateUtils;
import com.smart.dao.UserDao;
import com.smart.model.SellerInfo;
import com.smart.model.UserInfo;
import com.smart.service.UserService;
import com.smart.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.smart.model.NewsInfo;
import java.util.List;
@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public UserInfo sellerLogin(String user, String passwd) throws Exception {
        return userDao.sellerLogin(user, passwd);
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public boolean isValid(UserVo vo,Date date) throws Exception {
//        Date errorTime = DateUtils.getDate(vo.getUseTime()&1004,30);
        Date oldDate = new Date(vo.getUseTime()^1004L);

        Long id= userDao.isValid(vo.getUserName(),vo.getRole(),simpleDateFormat.format(oldDate));  //To change body of implemented methods use File | Settings | File Templates.
        if(null == id){
            return false;
        }
        Date newDate = DateUtils.getDate(oldDate.getTime(),30);
        if(date.getTime()-newDate.getTime()>0){
            //瓒呰繃30鍒嗛挓
            return false;
        }
        userDao.updateTime(id,simpleDateFormat.format(date)) ;
        return true;
    }

    @Override
    public boolean updateUseTime(Long id,Date use) throws Exception {
        return userDao.updateTime(id,simpleDateFormat.format(use));  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public  Page userNewsList(int pageNumber, int pageSize)
            throws Exception {
      //  List<NewsInfo>
        Page<NewsInfo> page = new Page<NewsInfo>() {
            @Override
            protected String listAlias() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        page.setPageNumber(pageNumber);
        page.setPageSize(10);
        Long total = userDao.count();
        List<NewsInfo> list = userDao.userNewsList(pageNumber, pageSize);
        page.setCount(total);
        page.setMessages(list);
        return page;  //To change
       // return userDao.userNewsList(  pageNumber,   pageSize);
    }

    @Override
    public NewsInfo userNewsDetail(int id) throws Exception {
        // TODO Auto-generated method stub
        return userDao.userNewsDetail(id);
    }
    @Override
    public boolean create(String join, String title, String content, String abs) throws Exception {

        return userDao.create(join, title, content, abs);  //To change body of implemented methods use File | Settings | File Templates.
    }


}
