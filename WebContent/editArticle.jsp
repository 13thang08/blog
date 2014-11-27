<%@page import="org.apache.commons.lang3.StringEscapeUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.zyyx.training.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link media="screen" href="elements/css/style.css" type="text/css" rel="stylesheet" />
<title>編集</title>
</head>
<body>
<%! ArticleBean article = null;%>
<%! String databaseError = null; %>
<%
article = (ArticleBean) request.getAttribute("article");
databaseError = (String) request.getAttribute("databaseError");
%>

<!-- wrap start -->
<div id="wrap">

	<jsp:include page="header.jsp"></jsp:include>
	
	<!-- contentWrap start -->
	<div id="contentWrap">
	
		<!-- content start -->
		<div id="content">
			<!-- form start -->
				<div id="form">
					<form action="edit-article" method="post">
						<%
						if (databaseError != null) {
							out.print("<div class='errMes'>" + databaseError + "</div>");
						}
						%>
						<h1>記事タイトル</h1>
						<%
						if (article != null && article.getTitle().trim().length() == 0) {
							out.print("<div class='errMes'>* タイトルを入力してください</div>");
						}
						%>
						<input type="text" maxlength="30" name="title" class="postTitle" 
						<%
						if (article != null) {
							out.print("value=\"" + StringEscapeUtils.escapeHtml4(article.getTitle()) + "\"");
						}
						%>
						>	
						<h1>記事内容</h1>
						<%
						if (article != null && article.getContent().trim().length() == 0) {
							out.print("<div class='errMes'>* 内容を入力してください</div>");
						}
						%>
						<textarea name="content" class="postContent"><%
							if (article != null && article.getContent().trim().length() != 0) {
								out.print(article.getContent());
							}%></textarea>
						
						<input type="hidden" name="id" value="<%= article.getId() %>">
						
						<div id="postBtn">
							<ul>
								<li><a href="show-articles">キャンセル</a></li>
								<li><input type="submit" value="確定"></li>
							</ul>
						</div>
						
					</form>
				</div>
			<!-- /form end -->
		
		</div>
		<!-- /content end -->
		
		<!-- include side -->
		<jsp:include page="side.jsp"></jsp:include>
		
		<!-- include footer -->
		<jsp:include page="footer.jsp"></jsp:include>
		
	</div>
	<!-- /contentWrap end -->

</div>
<!-- /wrap end -->
</body>
</html>