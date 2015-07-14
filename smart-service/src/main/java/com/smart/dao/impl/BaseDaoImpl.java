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

import com.smart.dao.IBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/06/03
 * Time: 11:35
 */
@Transactional(readOnly  = true)
public abstract class BaseDaoImpl extends JdbcDaoSupport implements IBaseDao<String> {
	protected  Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
    protected JdbcTemplate jdbcTemplate;
	@Override
    public List getBySql(String sql, Class cls) throws Exception{
        return getJdbcTemplate().queryForList(sql,cls);  //To change body of implemented methods use File | Settings | File Templates.
    }
	@Transactional(readOnly=false,rollbackFor=Exception.class)
    @Override
    public boolean add(String o) throws Exception{
        return getJdbcTemplate().update(o)==1?true:false;  //To change body of implemented methods use File | Settings | File Templates.
    }
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    @Override
    public List getBySqlRowMapper(String sql, RowMapper mapper)throws Exception {
        return getJdbcTemplate().query(sql,mapper);  //To change body of implemented methods use File | Settings | File Templates.
    }
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    @Override
    public boolean update(String o) throws Exception{
        return  getJdbcTemplate().update(o)==1?true:false;  //To change body of implemented methods use File | Settings | File Templates.
    }
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    @Override
    public boolean updateCheckRowNum(String o,int rowNum) throws Exception{
        return  getJdbcTemplate().update(o)==rowNum?true:false;  //To change body of implemented methods use File | Settings | File Templates.
    }
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    @Override
    public void delete(String sql) {
    	getJdbcTemplate().update(sql); //To change body of implemented methods use File | Settings | File Templates.
    }
    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
}
