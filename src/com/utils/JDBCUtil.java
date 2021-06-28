 package com.utils;

 import com.alibaba.druid.pool.DruidDataSourceFactory;

 import javax.sql.DataSource;
 import java.io.FileInputStream;
 import java.sql.Connection;
 import java.sql.ResultSet;
 import java.sql.Statement;
 import java.util.Properties;

 public class JDBCUtil {
     private JDBCUtil(){}
     public static DataSource ds = null;
     static {
         try {
             Properties p = new Properties();
             String path = JDBCUtil.class.getClassLoader().getResource("db.properties").getPath();
             FileInputStream in = new FileInputStream(path);
             p.load(in);
             ds = DruidDataSourceFactory.createDataSource(p);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
     public static DataSource getDataSource() {
         return ds;
     }
     public static Connection getConn() {
         try {
             return ds.getConnection();
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
     }
     public static void close(Connection conn, Statement st, ResultSet rs) {
         if (rs != null) {
             try {
                 rs.close();
             } catch (Exception e1) {
                 e1.printStackTrace();
             }
         }
         if (st != null) {
             try {
                 st.close();
             } catch (Exception e2) {
                 e2.printStackTrace();
             }
         }
         if (conn != null) {
             try {
                 conn.close();
             } catch (Exception e3) {
                 e3.printStackTrace();
             }
         }
     }
 }
