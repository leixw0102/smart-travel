package com.smart.service.impl;/*
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

import com.smart.common.Page;
import com.smart.common.ResponseBody;
import com.smart.dao.OrderDao;
import com.smart.model.OrderInfo;
import com.smart.model.UserClientInfo;
import com.smart.service.OrderServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/07/19
 * Time: 16:50
 */
@Service
public class OrderServiceImpl implements OrderServie {
    @Autowired
    private OrderDao orderDao;
    @Override
    public Page search(Long id, Integer page, String from, String to, Integer type, Integer orderType) throws Exception {
//        orderDao.search(page,);  //To change body of implemented methods use File | Settings | File Templates.
        Page<OrderInfo> infos = new Page<OrderInfo>() {
            @Override
            protected String listAlias() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        Long total=orderDao.count(id,from,to,type,orderType);
        infos.setPageSize(5);
        infos.setPageNumber(page);
        infos.setCount(total);
        List<OrderInfo> orders=orderDao.search(id,page,from,to,type,orderType);
        infos.setMessages(orders);
        return infos;
    }

    @Override
    public Page<UserClientInfo> getClientUsers(Integer page) throws Exception {
        Page<UserClientInfo> infos = new Page<UserClientInfo>() {
            @Override
            protected String listAlias() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        infos.setPageSize(15);
        infos.setPageNumber(page);
        Long total = orderDao.countUserInfos();
        List<UserClientInfo> list = orderDao.getUsersByPage(page);
        infos.setCount(total);
        infos.setMessages(list);
        return infos;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean deleteClientUserById(Long id) throws Exception {
        return orderDao.deleteClientUserById(id);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResponseBody searchUserOrder(Long id, Integer page) throws Exception {
       String[] times = orderDao.getApplyTime(id);
        Page<OrderInfo> infos = new Page<OrderInfo>() {
            @Override
            protected String listAlias() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        int type=orderDao.getTypeById(id);
        Long total=orderDao.count(id,times,type,4,9);
        infos.setPageSize(5);
        infos.setPageNumber(page);
        infos.setCount(total);
        List<OrderInfo> orders=orderDao.search(id,page,times,type,4,9) ;
        infos.setMessages(orders);
        return infos;
    }

    @Override
    public ResponseBody search(Integer page, String from, String to, Integer type, Integer orderType, String name) throws Exception {
        Page<OrderInfo> infos = new Page<OrderInfo>() {
            @Override
            protected String listAlias() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        Long total=orderDao.count(from,to,type,orderType,name);
        infos.setPageSize(10);
        infos.setPageNumber(page);
        infos.setCount(total);
        List<OrderInfo> orders=orderDao.search(page,from,to,type,orderType,name);
        infos.setMessages(orders);
        return infos;
    }

}
