/* Copyright 2013 Future TV, Inc.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package com.smart.common;

import com.alibaba.fastjson.serializer.NameFilter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaowu lei
 * Date: 13-12-26
 * Time: 下午7:03
 */
public class ResponseMsg<T> extends ResponseBody{

    /**
     * Send this msg instance if found illegal parameters
     */
    public static final ResponseMsg ILLEGAL_PARAMETER_MSG =
            new ResponseMsg(ResponseConstantCode.INVALID_PARAMETER_CODE,
                    ResponseConstantCode.INVALID_PARAMETER_DESC);
    private T info;
    public ResponseMsg(){
        super();
    }
    public ResponseMsg(String code, String message) {
        super(code,message);
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    @Override
    public List<NameFilter> nameFilters() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
