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
import com.smart.model.CompanyInfo;
import com.smart.vo.SellerVo;

import java.util.Map;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/07/14
 * Time: 17:11
 */
public interface SellerService {
    public Page getSellers(Integer pageNumber) throws  Exception;

    Map<Integer,Map<Integer,String>> getTypes() throws Exception;
    Map<Integer,String> getTypes(Integer type) throws Exception;
    Long addSeller(SellerVo info) throws Exception;

    long addCompany(CompanyInfo info) throws Exception;

    boolean fingByPhone(String userName) throws Exception;

    SellerVo getUserById(Long id) throws Exception;

    long updateSeller(SellerVo info) throws Exception;

    CompanyInfo getCompanyByUserId(Long id, Integer type) throws Exception;

    Long updateCompany(CompanyInfo info)throws Exception;
}
