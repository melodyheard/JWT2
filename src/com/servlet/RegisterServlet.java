package com.servlet;

import com.dao.UserDao;
import com.entity.User;
import com.jwt.Jwt;
import net.minidev.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns="/api/register",loadOnStartup=1)
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 5285600116871825644L;
	
	/**
	 * 校验用户名密码
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userName=request.getParameter("userName");
		String password =request.getParameter("password");
		JSONObject resultJSON=new JSONObject();
		UserDao userDao = new UserDao();
		User user = new User();
		user.setUsername(userName);
		user.setPassword(password);
		//用户名密码校验成功后，生成token返回客户端
		if(userDao.countUserByUserName(userName)>0){

			resultJSON.put("success", false);
			resultJSON.put("msg", "username duplicate！！");

		}else{
			userDao.insert(user);
			resultJSON.put("success", true);
			resultJSON.put("msg", "registe success");
		}
		//输出结果
		output(resultJSON.toJSONString(), response);
	
		
		
	}
	
	
	
	public void output(String jsonStr,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8;");
		PrintWriter out = response.getWriter();
		out.println(jsonStr);
		out.flush();
		out.close();
		
	}

}
