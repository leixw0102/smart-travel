package com.smart.service.impl;
import com.smart.common.DateUtils;
import com.smart.dao.UserDao;
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
         //超过30分钟
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
    public List<NewsInfo> userNewsList(int pageNumber, int pageSize)
            throws Exception {
        // TODO Auto-generated method stub
        return userDao.userNewsList(  pageNumber,   pageSize);
    }

    @Override
    public NewsInfo userNewsDetail(int id) throws Exception {
        // TODO Auto-generated method stub
        return userDao.userNewsDetail(id);
    }
}
