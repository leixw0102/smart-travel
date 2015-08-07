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

import com.alibaba.fastjson.JSONObject;
import com.smart.common.ResponseMsg;
import com.smart.model.CompanyInfo;
import com.smart.model.SellerInfo;
import com.smart.model.Type;
import com.smart.model.TypeInfo;
import com.smart.vo.SellerVo;

import java.util.List;
import java.util.Map;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/07/14
 * Time: 17:13
 */
public interface SellerDao {
    public List<SellerInfo> getSeller(Integer pageNumber,Integer pageSize) throws Exception;
    public Long count()throws Exception;
    public List<Type> getTypesA(Integer type) throws Exception;
    Map<Integer,Map<Integer,String>> getTypes() throws Exception;
   Map<Integer,String> getTypes(Integer type) throws Exception;
    Long addSeller(SellerVo info) throws Exception;

    Long addCompany(CompanyInfo info) throws Exception;

    boolean findByPhone(SellerVo userName) throws Exception;

    boolean addAccount(Long userId) throws Exception;

    SellerVo getUserById(Long id) throws Exception;

    long updateSeller(SellerVo info) throws Exception;

    CompanyInfo getCompanyByUserId(Long id, Integer type) throws Exception;

    Long updateCompany(CompanyInfo info) throws Exception;

    boolean deleteCategory(Long typeId) throws Exception;

    boolean updateCategory(Long id, String name) throws Exception;

    JSONObject getCode(Long id, Integer type) throws Exception;

    List<TypeInfo> getTypes1() throws Exception;

    boolean addCategory(Long id, String name, int i) throws Exception;

    Long count(String name) throws Exception;

    List<SellerInfo> getSeller(Integer pageNumber, String name, Integer pageSize) throws Exception;

    Long count(String name, Integer type) throws Exception;

    List<SellerInfo> getSeller(Integer pageNumber, String name, Integer pageSize, Integer type) throws Exception;

    boolean updateSeller(String name,Long id) throws Exception;
}
