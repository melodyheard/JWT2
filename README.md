# JWT
##
### directory structure
```
.
├── README.md
├── src
|    └── com
|        |── filter
|        |   └── Filter0_CrossOriginResource.java 
|		 |	 └── Filter1_CheckToken.java	
|        ├── jwt
|        │   └── Jwt.java
|        │   └── JwtTestCase.java 
|        │   └── TokenState.java  
|        |
|        └── servlet
|            └── AuthorServlet.java
├── WebRoot
|   |── WEB-INFO
|   |── index.jsp
|	|── login.html
|	|── main.html
|   └── jquery-2.1.0.js
```
## using the servlet3.0, need the environment of JDK7 or above, Tomcat7 or above

This project depends on those jar package  
+ nimbus-jose-jwt-4.13.1.jar (a open source JSON WEB TOKEN solution)
+ json-smart-2.0-RC2.jar和asm-1.0-RC1.jar (basicly used for serializing JSONObject)
+ cors-filter-2.2.1.jar和java-property-utils-1.9.1.jar（used for ajax request）
+ junit.jar（unit test）


Jwt.java structure：
> 2 static method createToken and validToken，used for generated TOKEN and check TOKEN respectively;
> define the TokenState，used to store the result of token validation，address with different method according to the state：
   * EXPIRED  token expired
   * INVALID  token invalid（including token illegal ，token format error，exception in checking ）
   * VALID    token valid

   
   
## use example
### get token

```Java
Map<String , Object> payload=new HashMap<String, Object>();
Date date=new Date();
payload.put("uid", "291969452");//user id
payload.put("iat", date.getTime());//time stamp
payload.put("ext",date.getTime()+1000*30);//expire time
String token=Jwt.createToken(payload);
System.out.println("token:"+token);

```

### check token
```Java

String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIyOTE5Njk0NTIiLCJpYXQiOjE0NjA0MzE4ODk2OTgsImV4dCI6MTQ2MDQzNTQ4OTY5OH0.RAa71BnklRMPyPhYBbxsfJdtXBnXeWevxcXLlwC2PrY";
Map<String, Object> result=Jwt.validToken(token);

String state=(String)result.get("state");
switch (TokenState.getTokenState(state)) {
case VALID:
	//To do somethings
	System.out.println("valid token");
	break;
case EXPIRED:
	System.out.println("expired token");
	break;
case INVALID:
	System.out.println("invalid token");
	break;
}

System.out.println("return result：" +result.toString());
	


```

