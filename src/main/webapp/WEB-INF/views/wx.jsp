<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
 <%@ page import="drivingSchool.drivingSchool.domain.AccessTokenInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  微信学习
    <hr/>
      access_token为：<%=AccessTokenInfo.accessToken.getAccessToken()%>
   </body>
</body>
</html>