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
package com.smart.service;

import com.smart.common.ResponseBody;
import com.smart.common.ResponseMsg;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/07/30
 * Time: 09:46
 */
public interface StatisticsService {

    ResponseBody getHotelXy(int i, Integer type) throws Exception;

    ResponseBody getHotelMap(int type) throws Exception;

    ResponseBody getLifeXy(Integer module) throws Exception;

    ResponseBody getTotal(Integer type) throws Exception;

    ResponseBody getVsXy(int i, Integer type) throws Exception;

    ResponseBody getCateXy(Integer type) throws Exception;
}
