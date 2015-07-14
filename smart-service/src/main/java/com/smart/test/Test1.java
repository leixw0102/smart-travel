package com.smart.test;/*
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

import com.alibaba.fastjson.JSON;
import com.smart.dao.UserDao;
import com.smart.service.SellerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/07/09
 * Time: 21:43
 */
public class Test1 {
    public static void main(String[]args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SellerService userDao = context.getBean(SellerService.class);
        if(null == userDao){
            System.out.println("abc");
        }  else{
            System.out.println(JSON.toJSONString(userDao.getSellers(1)));
        }
    }
}
