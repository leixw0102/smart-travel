package com.smart.dao;

import com.smart.model.UserInfo;


public interface UserDao {
	

    UserInfo sellerLogin(String user, String passwd)throws Exception;


    Long  isValid(String userName, Integer role, String format) throws Exception;

    boolean updateTime(Long id,String time)throws Exception;
}
