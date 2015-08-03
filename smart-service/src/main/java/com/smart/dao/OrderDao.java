/*
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
package com.smart.dao;

import com.smart.model.OrderInfo;
import com.smart.model.UserClientInfo;

import java.util.List;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/07/19
 * Time: 17:23
 */
public interface OrderDao {
    Long count(Long id, String from, String to, Integer type, Integer orderType) throws Exception;
    List<OrderInfo> search(Long id, Integer page, String from, String to, Integer type, Integer orderType) throws Exception;

    Long countUserInfos() throws Exception;

    List<UserClientInfo> getUsersByPage(Integer page) throws Exception;

    boolean deleteClientUserById(Long id) throws Exception;


    String[] getApplyTime(Long id) throws Exception;


    int getTypeById(Long id) throws Exception;

    Long count(Long id, String[] times, int type, int type1, int orderType) throws Exception;

    List<OrderInfo> search(Long id, Integer page, String[] times, int type, int type1, int orderType) throws Exception;

    Long count(String from, String to, Integer type, Integer orderType, String name) throws Exception;

    List<OrderInfo> search(Integer page, String from, String to, Integer type, Integer orderType, String name) throws Exception;
}
