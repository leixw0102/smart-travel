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

import com.alibaba.fastjson.JSONObject;
import com.smart.common.Page;
import com.smart.common.ResponseBody;
import com.smart.dao.SellerDao;
import com.smart.model.CompanyInfo;
import com.smart.model.SellerInfo;
import com.smart.model.Type;
import com.smart.model.TypeInfo;
import com.smart.service.SellerService;
import com.smart.vo.SellerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/07/14
 * Time: 17:13
 */
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerDao sellerDao;
    @Override
    public Page getSellers(Integer pageNumber) throws Exception {

        Page<SellerInfo> page = new Page<SellerInfo>() {
            @Override
            protected String listAlias() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        page.setPageNumber(pageNumber);
        page.setPageSize(10);
        Long total = sellerDao.count();
        List<SellerInfo> list = sellerDao.getSeller(pageNumber,page.getPageSize());
        page.setCount(total);
        page.setMessages(list);
        return page;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Page getTypes() throws Exception {
        Page<TypeInfo> page = new Page<TypeInfo>() {
            @Override
            protected String listAlias() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        List<TypeInfo> infos=sellerDao.getTypes1();//To change body of implemented methods use File | Settings | File Templates.
        page.setMessages(infos);
        return page;
    }

    @Override
    public Map<Integer, String> getTypes(Integer type) throws Exception {
        return sellerDao.getTypes(type);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Long addSeller(SellerVo info) throws Exception {
        Long id= sellerDao.addSeller(info);
        sellerDao.addAccount(id);
        return id;
    }

    @Override
    public long addCompany(CompanyInfo info) throws Exception {
        long id= sellerDao.addCompany(info);  //To change body of implemented methods use File | Settings | File Templates.
        sellerDao.updateSeller(info.getName(),info.getUserId());
        return id;
    }

    @Override
    public boolean fingByPhone(SellerVo userName) throws Exception {
        return sellerDao.findByPhone(userName);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SellerVo getUserById(Long id) throws Exception {
        return sellerDao.getUserById(id);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long updateSeller(SellerVo info) throws Exception {
        return sellerDao.updateSeller(info);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CompanyInfo getCompanyByUserId(Long id, Integer type) throws Exception {
        return sellerDao.getCompanyByUserId(id,type);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Long updateCompany(CompanyInfo info) throws Exception {
        long id= sellerDao.updateCompany(info);  //To change body of implemented methods use File | Settings | File Templates.
        sellerDao.updateSeller(info.getName(),info.getUserId());
        return id;
    }

    @Override
    public boolean deleteCategory(Long typeId) throws Exception {
        return sellerDao.deleteCategory(typeId);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean updateCategory(Long id, String name) throws Exception {
        return sellerDao.updateCategory(id,name);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public JSONObject getCode(Long id, Integer type) throws Exception {
        return sellerDao.getCode(id,type);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean addCategory(Long id, String name) throws Exception {
        List<Type> types = sellerDao.getTypesA(id.intValue());
        int typeId=  types.get(types.size()-1).getType()+1;
        return sellerDao.addCategory(id,name,typeId);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Page getSellers(Integer pageNumber, String name) throws Exception {
        Page<SellerInfo> page = new Page<SellerInfo>() {
            @Override
            protected String listAlias() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        page.setPageNumber(pageNumber);
        page.setPageSize(10);
        Long total = sellerDao.count(name);
        List<SellerInfo> list = sellerDao.getSeller(pageNumber,name,page.getPageSize());
        page.setCount(total);
        page.setMessages(list);
        return page;
    }

    @Override
    public ResponseBody getSellers(Integer pageNumber, String name, Integer type) throws Exception {
        Page<SellerInfo> page = new Page<SellerInfo>() {
            @Override
            protected String listAlias() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        page.setPageNumber(pageNumber);
        page.setPageSize(10);
        Long total = sellerDao.count(name,type);
        List<SellerInfo> list = sellerDao.getSeller(pageNumber,name,page.getPageSize(),type);
        page.setCount(total);
        page.setMessages(list);
        return page;
    }
}
