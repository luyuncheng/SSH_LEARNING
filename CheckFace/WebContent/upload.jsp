<%@ page language="java" import="java.util.*,com.lyc.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%

	String fileName=UploadFile.getUploadFile(request, response);
	if(fileName!=null){
		session.setAttribute("fileName", "upload/"+fileName);
		response.sendRedirect("face.jsp");
	}
%>