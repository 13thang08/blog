<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.zyyx.training.*,java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link media="screen" href="elements/css/style.css" type="text/css" rel="stylesheet" />
<title>新規</title>
</head>
<body>
<!-- wrap start -->
<div id="wrap">

	<jsp:include page="header.jsp"></jsp:include>
	
	<!-- contentWrap start -->
	<div id="contentWrap">
	
		<!-- content start -->
		<div id="content">
			<!-- form start -->
				<div id="form">
					<form action="create-article" method="post">
					
						<c:if test="${!empty databaseError }">
							<div class='errMes'>${databaseError }</div>
						</c:if>
						
						<h1>記事タイトル</h1>
						
						<c:if test="${empty article.title }">
							<div class='errMes'>* タイトルを入力してください</div>
						</c:if>
						
						<input type="text" maxlength="30" name="title" class="postTitle" value="<c:out value="${article.title }"></c:out>">	
						
						<h1>記事内容</h1>
						
						<c:if test="${empty article.content }">
							<div class='errMes'>* 内容を入力してください</div>
						</c:if>
						
						<textarea name="content" class="postContent">${article.content }</textarea>
						
						<div id="postBtn">
							<ul>
								<li><a href="show-articles">キャンセル</a></li>
								<li><input type="submit" value="登録"></li>
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