<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>用户个更新:</h1>
<s:form action="user_update" method="post">
<s:hidden name="id"/>
	<s:textfield label="用户名称" name="username"/>
	<s:password label="用户密码" name="password"/>
	<s:textfield label="用户昵称" name="nickname"/>
	<s:select list="#gs" listKey="id" listValue="name" name="gid" label="选择组"/>
	<s:submit value="更新用户"/>
</s:form>
</body>
</html>