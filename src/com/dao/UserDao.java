package com.dao;

import com.entity.User;

import com.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDao {

	public List<User> findAllGoods(int first, int last) {
		//1.连接数据库
		QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
		//2.从数据库中获取数据
		String sql = "SELECT * FROM t_user ";
		List<User> userList = null;
		try {
			userList = qr.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(userList);
		return userList;

	}

	public int countUserByUserName(String userName) {
		//1.连接数据库
		QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
		//2.从数据库中获取数据
		String sql = "SELECT count(*) FROM t_user where username = '"+userName+"' ";
		int num = 0;
		try {
			num =Integer.parseInt((Long) qr.query(sql, new ScalarHandler())+"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;

	}

	public int countByParam(User user) {
		//1.连接数据库
		QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
		//2.从数据库中获取数据
		String sql = "SELECT count(*) FROM t_user where username  = '"+user.getUsername() +"' and password='"+user.getPassword()+"'";
		int num = 0;
		try {
			num =Integer.parseInt((Long) qr.query(sql, new ScalarHandler())+"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;

	}

	public void delGoods(String id)   {
		QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
		//2.从数据库中获取数据
		String sql = "delete FROM t_user where id = "+id;
		try{
			int row = qr.update(sql);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public void insert(User user)   {
		QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
		//2.从数据库中获取数据
		String sql = "insert into t_user(username,password) values('"+user.getUsername()+"','"+user.getPassword()+"') ";
		try{
			int row = qr.update(sql);
		}catch (Exception e){
			e.printStackTrace();
		}
	}


}
