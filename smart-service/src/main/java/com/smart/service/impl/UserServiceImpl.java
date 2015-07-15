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
    public  Page userNewsList(int pageNumber, int pageSize)
            throws Exception {
      //  List<NewsInfo>
        Page<NewsInfo> page = new Page<SellerInfo>() {
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


}
