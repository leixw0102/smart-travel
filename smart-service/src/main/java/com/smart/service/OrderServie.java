package com.smart.service;/*
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
import com.smart.model.UserClientInfo;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/07/19
 * Time: 16:49
 */
public interface OrderServie {
    Page search(Long id, Integer page, String from, String to, Integer type, Integer orderType) throws Exception;

    Page<UserClientInfo> getClientUsers(Integer page) throws Exception;

    boolean deleteClientUserById(Long id) throws Exception;

    ResponseBody searchUserOrder(Long id, Integer page) throws Exception;

    ResponseBody search(Integer page, String from, String to, Integer type, Integer orderType, String name) throws Exception;
}
