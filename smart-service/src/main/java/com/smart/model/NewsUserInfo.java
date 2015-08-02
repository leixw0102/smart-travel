package com.smart.model;/*
 * Copyright 2015 Future TV, Inc.
 *
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.icntv.tv/licenses/LICENSE-1.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import com.google.common.base.Strings;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/08/02
 * Time: 12:48
 */
public class NewsUserInfo {
    private long  id;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String userName;
    private  String pwd;
    private String pwd2;
    private String contactName;
    private String mark;

    public String getContactName() {
        return contactName;
    }


    public boolean check(){
        return Strings.isNullOrEmpty(userName) || Strings.isNullOrEmpty(pwd) || Strings.isNullOrEmpty(pwd2) || Strings.isNullOrEmpty(contactName) || !pwd.equals(pwd2);
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getPwd2() {
        return pwd2;
    }

    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "CashUserInfo{" +
                "contactName='" + contactName + '\'' +
                ", id=" + id +
                ", userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", pwd2='" + pwd2 + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}
