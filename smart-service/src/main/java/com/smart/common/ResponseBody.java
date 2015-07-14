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
 * Date: 13-12-27
 * Time: 上午11:32
 */
public abstract  class ResponseBody {
    protected ResponseBody(String code, String message) {
        this.code = code;
        this.message = message;
    }

    protected ResponseBody() {
        this(ResponseConstantCode.SUCCESS_CODE,ResponseConstantCode.SUCCESS_DESC);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String code;
    private String message;
    public  abstract List<NameFilter> nameFilters();
}
