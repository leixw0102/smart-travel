package com.smart.ui.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class FinaceAction
 */
public class FinaceAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinaceAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		if(type.equalsIgnoreCase("finace_main")){
			request.getRequestDispatcher("finace1.jsp").forward(request, response);
		}else if(type.equalsIgnoreCase("search")){
			JSONArray ja = new JSONArray();
			 JSONObject object = new JSONObject();
			 object.put("number","1");
			 object.put("user","111111");
			 object.put("account","222222");
			 object.put("pwd","3333333");
			 object.put("mark","4444444");
			 ja.add(object);
			 System.out.println(ja.toJSONString());
			 response.getOutputStream().write(ja.toJSONString().getBytes("UTF-8"));  
			 response.setContentType("text/json; charset=UTF-8");  
		}else if(type.equalsIgnoreCase("user_search")){
			JSONArray ja = new JSONArray();
			 JSONObject object = new JSONObject();
			 object.put("total_page","90");
			 object.put("current_page","2");
			 object.put("number","1");
			 object.put("phoneNumber","111111");
			 object.put("userName","222222");
			 object.put("registerDate","3333333");
			 object.put("mark","4444444");
			 ja.add(object);
			 System.out.println(ja.toJSONString());
			 response.getOutputStream().write(ja.toJSONString().getBytes("UTF-8"));  
			 response.setContentType("text/json; charset=UTF-8");  
		}else if(type.equalsIgnoreCase("order_search")){
			JSONObject object_all = new JSONObject();
			
			 JSONArray ja = new JSONArray();
			 JSONObject object = new JSONObject();
			 object.put("number","1");
			 object.put("orderNumber","111111");
			 object.put("mappingCompany","222222");
			 object.put("orderFL","3333333");
			 object.put("mark","orderStauts");
			 object.put("orderDate","2015-7-29");
			 ja.add(object);
			 object_all.put("list", ja);
			 object_all.put("total_page", "90");
			 object_all.put("current_page", "2");
			 
			 System.out.println(object_all.toJSONString());
			 response.getOutputStream().write(object_all.toJSONString().getBytes("UTF-8"));  
			 response.setContentType("text/json; charset=UTF-8");  
		}else if (type.equalsIgnoreCase("chars")){
			ArrayList<String> strArray = new ArrayList<String> ();
			strArray.add("2015-8-5");
			strArray.add("2015-8-6");
			strArray.add("2015-8-7");
			strArray.add("2015-8-8");
			strArray.add("2015-8-9");
			
			ArrayList<String> strArray1 = new ArrayList<String> ();
			strArray1.add("185");
			strArray1.add("79");
			strArray1.add("53");
			strArray1.add("26");
			strArray1.add("89");
			JSONArray ja = new JSONArray();
			JSONObject object = new JSONObject();
			JSONObject object_xAxis = new JSONObject();
			object.put("type","category");
			object.put("data",strArray);
			object_xAxis.put("xAxis",object);
			JSONObject object1 = new JSONObject();
			JSONObject object_series = new JSONObject();
			object1.put("type","bar");
			object1.put("data",strArray1);
			object_series.put("series",object1);
			
			ja.add(object_xAxis);
			ja.add(object_series);
			
			System.out.println(ja.toJSONString());
			response.getOutputStream().write(ja.toJSONString().getBytes("UTF-8"));  
			response.setContentType("text/json; charset=UTF-8");  

		}else if (type.equalsIgnoreCase("mapData")){
			JSONArray ja = new JSONArray();
			 JSONObject object = new JSONObject();
			 object.put("lon","100.944716");
			 object.put("lat","37.973845");
			 object.put("number","100人");
			 
			 JSONObject object1 = new JSONObject();
			 object1.put("lon","100.259491");
			 object1.put("lat","38.183462");
			 object1.put("number","80人");
			 ja.add(object);
			 ja.add(object1);
			 
			 System.out.println(ja.toJSONString());
			 response.getOutputStream().write(ja.toJSONString().getBytes("UTF-8"));  
			 response.setContentType("text/json; charset=UTF-8");  
		}else if(type.equalsIgnoreCase("addNewCategory")){
			RequestDispatcher dispatcher=request.getRequestDispatcher("/addNewCategory.jsp");
	        dispatcher.forward(request, response);
		}else if(type.equalsIgnoreCase("editCategoryName")){
			RequestDispatcher dispatcher=request.getRequestDispatcher("/editCategoryName.jsp");
	        dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
