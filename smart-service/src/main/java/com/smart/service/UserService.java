package com.smart.service;


import com.smart.model.UserInfo;
import com.smart.vo.UserVo;

import java.util.Date;

public interface UserService {
    public UserInfo sellerLogin(String user, String passwd)throws Exception;

    boolean isValid(UserVo vo,Date date) throws Exception;

    public boolean  updateUseTime(Long id,Date use) throws Exception;
}
