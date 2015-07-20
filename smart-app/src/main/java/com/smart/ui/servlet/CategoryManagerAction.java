package com.smart.ui.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CategoryManagerAction
 */
public class CategoryManagerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryManagerAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		String phoneNumber = request.getParameter("phoneNumber");
		if(type != null && (Integer.parseInt("0") == Integer.parseInt(type))){
			return;
		}else if(type != null && (Integer.parseInt("1") == Integer.parseInt(type))){//��ת�̻���Ϣҳ��
			//request.getRequestDispatcher("/page/companyAcountManager-addCompany.jsp").forward(request, response);
			return;
		}else if(type != null && (Integer.parseInt("2") == Integer.parseInt(type))){//�����û���Ϣ
			//request.getRequestDispatcher("/page/companyAcountManager-addCompany.jsp").forward(request, response);
			return;
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("/finace.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
