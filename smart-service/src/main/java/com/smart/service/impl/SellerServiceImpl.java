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
import com.smart.dao.SellerDao;
import com.smart.model.SellerInfo;
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
        Long total = sellerDao.count();
        List<SellerInfo> list = sellerDao.getSeller(pageNumber);
        page.setCount(total);
        page.setMessages(list);
        return page;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<Integer, Map<Integer, String>> getTypes() throws Exception {
        return sellerDao.getTypes();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean addSeller(SellerVo info) throws Exception {
        return sellerDao.addSeller(info);
    }
}
