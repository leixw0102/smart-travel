/**
 * Copyright 2013 Future TV, Inc.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.smart.common;

/**
 * Created by Luzt
 * <p/>
 * Author: Luzt
 * Date: 2013/12/30
 * Time: 11:38
 */
public class PageUtil {
    //开始记录行数
    private int startRow;
    //当前页码
    private int currentPage;
    //总记录数
    private int totalRows;
    //每页大小
    private int pageSize;
    //总页数
    private int totalPages;
    public PageUtil(){
        this.currentPage = 0;
        this.pageSize = 5;
        this.totalRows = 0;

    }

    public PageUtil(int currentPage ,int pageSize , int totalRows){
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalRows = totalRows;
    }

    public int getStartRow(){
        startRow = pageSize * (currentPage - 1);
        return startRow;
    }

    public int getTotalPages(){
        this.totalPages = totalRows % pageSize == 0 ? totalRows / pageSize : totalRows / pageSize + 1 ;
        return totalPages;

    }

    /**
     * 判断是否需要进行Page列表的查询
     * @return true 需要查询，false 不需要查询
     */
    public boolean needQuery (){
        if(totalRows < 1){
            return false;
        }
        return this.currentPage <= getTotalPages();
    }


    public static void main(String []s){
        PageUtil pageUtil = new PageUtil(4,2,10);
        System.out.println(pageUtil.getStartRow());
        System.out.println(pageUtil.getTotalPages());
        System.out.println(pageUtil.needQuery());
    }



}
