package com.smart.ui.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.*;

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
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//dotGet(request, response);
	}

}
