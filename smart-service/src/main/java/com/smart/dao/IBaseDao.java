package com.smart.dao;/*
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

import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/06/03
 * Time: 11:02
 */
public interface IBaseDao<I> {

    public boolean add(I sql) throws Exception;
    public boolean update(I sql)throws Exception;
    public void delete(I sql)throws Exception;
    public List getBySql(I sql, Class cls)throws Exception;
    
    
    public List getBySqlRowMapper(I sql, RowMapper mapper)throws Exception ;
    public boolean updateCheckRowNum(I o, int rowNum) throws Exception;
}
