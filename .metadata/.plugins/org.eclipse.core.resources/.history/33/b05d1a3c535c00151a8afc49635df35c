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
<s:iterator value="#gl">
	${id }----<a href="group_show.action?cid=${id }">${name }</a>
	<a href="group_delete.action?id=${id }">删除</a>&nbsp;
	<a href="group_updateInput.action?id=${id }">更新</a>
	<br/>
</s:iterator>
</body>
</html>