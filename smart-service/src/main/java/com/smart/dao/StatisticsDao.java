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

import com.smart.model.MapInfo;
import com.smart.model.TotalInfo;
import com.smart.model.XYModel;

import java.util.List;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/07/30
 * Time: 10:09
 */
public interface StatisticsDao {
    List getHotelXY(int i, Integer type, Integer totalType) throws Exception;

    List<MapInfo> getHotelMap(int type) throws Exception;

    List<XYModel> getLifeXY(Integer module, Integer type) throws Exception;

    String[] getTotal(Integer type) throws Exception;

    public String total()throws Exception;

    List<XYModel> getVsXY(int i, Integer type) throws Exception;

    List<XYModel> getCateXY(Integer type) throws Exception
            ;
}
