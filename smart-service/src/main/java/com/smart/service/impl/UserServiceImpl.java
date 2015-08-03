package com.smart.service.impl;

import com.smart.common.Page;
import com.smart.common.DateUtils;
import com.smart.common.ResponseBody;
import com.smart.dao.UserDao;
import com.smart.model.*;
import com.smart.service.UserService;
import com.smart.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    UserDao userDao;
     private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public UserInfo sellerLogin(String user, String passwd) throws Exception {
        return userDao.sellerLogin(user,passwd);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isValid(UserVo vo,Date date) throws Exception {

        Long id= userDao.isValid(vo.getUserName(),vo.getRole(),simpleDateFormat.format(vo.getUseTime()));  //To change body of implemented methods use File | Settings | File Templates.
        if(null == id){
            return false;
        }
        Date newDate = DateUtils.getDate(vo.getUseTime(),30);
        if(date.getTime()-newDate.getTime()>0){

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
        Long total = userDao.count("", "");
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

    @Override
    public Page<CashUserInfo> searchCashUser(int i, int i1) throws Exception {
        Page<CashUserInfo> infos = new Page<CashUserInfo>() {
            @Override
            protected String listAlias() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        Long total = userDao.countCashUser();
        infos.setCount(total);
        List<CashUserInfo> users= userDao.searchCashUser(i,i1);  //To change body of implemented methods use File | Settings | File Templates.
        infos.setMessages(users);
        infos.setPageSize(i1);
        infos.setPageNumber(i);
        return infos;
    }

    @Override
    public CashUserInfo searchFinanceUser(CashUserInfo info) throws Exception {
        return userDao.searchFinanceUser(info);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean saveFinanceUser(CashUserInfo info) throws Exception {
        return userDao.saveFinaceUser(info);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean deleteById(Long id) throws Exception {
        return userDao.deleteById(id);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean updateById(Long id, String old, String newPwd) throws Exception {
        return userDao.updateById(id,old,newPwd);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CashUserInfo findUserByIdAndPwd(Long id, String old) throws Exception {
        return userDao.findUserByIdAndPwd(id,old);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Page<NewsUserInfo> searchNewsUser(Integer page, int i) throws Exception {
        Page<NewsUserInfo> infos = new Page<NewsUserInfo>() {
            @Override
            protected String listAlias() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        Long total = userDao.countNewsUser();
        infos.setCount(total);
        List<NewsUserInfo> users= userDao.searchNewsUser(page,i);  //To change body of implemented methods use File | Settings | File Templates.
        infos.setMessages(users);
        infos.setPageSize(page);
        infos.setPageNumber(i);
        return infos;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean saveNewsUser(NewsUserInfo info) throws Exception {
        return userDao.saveNewsUser(info);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public NewsUserInfo searchNewsUser(NewsUserInfo info) throws Exception {
        return userDao.searchNewsUser(info);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseBody userNewsList(Long page1, int i, String time,String to) throws Exception {
        Page<NewsInfo> page = new Page<NewsInfo>() {

            @Override
            protected String listAlias() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        page.setPageNumber(i);
        page.setPageSize(10);
        Long total = userDao.count(time,to);
        List<NewsInfo> list = userDao.userNewsList(page1, i,time,to);
        page.setCount(total);
        page.setMessages(list);
        return page;   //To change body of implemented methods use File | Settings | File Templates.
    }


}
