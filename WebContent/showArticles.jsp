<%@page import="com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.zyyx.training.*,java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>記事一覧</title>
</head>
<body>
<% 
ArticlesList articlesList = (ArticlesList) request.getAttribute("articlesList");
if (articlesList != null) {
	ArrayList<ArticleBean> beanList = articlesList.getList();
	if (beanList != null) {
		for (int i = 0; i < beanList.size(); i++) {
			out.print(Integer.toString(beanList.get(i).getId()) + "<br>");
			out.print(beanList.get(i).getDate() + "<br>");
			out.print(beanList.get(i).getTitle() + "<br>");
			out.print(beanList.get(i).getContent() + "<br>");
		}
	}
}
%> 

</body>
</html>