package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.entity.User;
import net.minidev.json.JSONObject;

import com.jwt.Jwt;
@WebServlet(urlPatterns="/api/login",loadOnStartup=1)
public class LoginServlet extends HttpServlet {

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
		if(userDao.countByParam(user)>0){
			//生成token
			Map<String , Object> payload=new HashMap<String, Object>();
			Date date=new Date();
			payload.put("uid", user.getId());//用户ID
			payload.put("iat", date.getTime());//生成时间
			payload.put("ext",date.getTime()+1000*30);//过期时间半分钟
			String token = Jwt.createToken(payload);

			resultJSON.put("success", true);
			resultJSON.put("msg", "login success");
			resultJSON.put("token", token);

		}else{
			resultJSON.put("success", false);
			resultJSON.put("msg", "username/password wrong!");
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
