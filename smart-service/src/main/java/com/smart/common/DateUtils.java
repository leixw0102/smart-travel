package com.smart.common;/*
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

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by leixw
 * <p/>
 * Author: leixw
 * Date: 2015/07/13
 * Time: 17:39
 */
public class DateUtils {

    public static Date getDate(Long date,int min){
       DateTime dateTime = new  DateTime(date);
       return dateTime.plusMinutes(min).toDate();
    }

    public static String plusDay(String day,int plus){
        return DateTime.parse(day).plusDays(plus).toString("yyyy-MM-dd");
    }
    public static void main(String[]args){
        System.out.println(plusDay("2015-09-09",1));
    }
}
