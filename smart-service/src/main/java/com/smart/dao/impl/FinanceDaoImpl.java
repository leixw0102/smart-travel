package com.smart.dao.impl;/*
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

import com.smart.dao.FinanceDao;
import com.smart.model.Apply;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/07/16
 * Time: 20:26
 */
@Transactional(readOnly = true)
@Repository
public class FinanceDaoImpl extends BaseDaoImpl implements FinanceDao {
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean confirm(Long applyId) throws Exception {
        return super.update("update cash_apply set status=2,,finish_time='"+DateTime.now().toString("yyyy-MM-dd HH:mm:ss")+"' where id="+applyId);  //To change body of implemented methods use File | Settings | File Templates.
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean refuse(Long applyId) throws Exception {
        return super.update("update cash_apply set status=4 ,set finish_time='"+ DateTime.now().toString("yyyy-MM-dd HH:mm:ss")+"' where id ="+applyId);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Long searchCount(String from, String to, Integer type) throws Exception {
        String sql ="select count(*) from cash_apply as a,user as b where 1=1 and a.user_id=b.userid ";
        String where = "";
        if(type!=0){
            where+=" b.roleType="+type;
        }
        return super.getJdbcTemplate().queryForLong(sql);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Apply> search(Integer page, String from, String to, Integer type) throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
