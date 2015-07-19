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
import com.smart.dao.FinanceDao;
import com.smart.model.Apply;
import com.smart.service.FinanceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/07/16
 * Time: 20:23
 */
@Service
public class FinanceServiceImpl implements FinanceService {

    private FinanceDao financeDao;
    @Override
    public boolean confirm(Long applyId) throws Exception {
        return financeDao.confirm(applyId);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean refuse(Long applyId) throws Exception {
        return financeDao.refuse(applyId);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Page search(Integer page, String from, String to, Integer type) throws Exception {
        Page<Apply> applies = new Page<Apply>() {
            @Override
            protected String listAlias() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        applies.setPageNumber(page);
        applies.setPageSize(10);
        Long total=financeDao.searchCount(from,to,type);

        List<Apply> list = financeDao.search(page,from,to,type);
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
