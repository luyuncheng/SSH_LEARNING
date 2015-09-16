<%@ page language="java" import="java.util.*,com.lyc.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%

	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	
	String path = request.getParameter("path");
	System.out.println("faceData。jsp里面获取到的参数："+path);
	/*新版本的已经摈弃getRealPath 这个用法 有些地方要加/有些不用加*/
	String pathName = request.getRealPath("/"+path);
	System.out.println("faceData。jsp里面pathName："+pathName);
	String result =  faceMessage.getfaceMessage(pathName);
	System.out.println("faceData。jsp里面的结果："+result);
	out.println(result);
%>