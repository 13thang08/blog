<%@page import="com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.zyyx.training.model.*,java.util.ArrayList,org.apache.commons.lang3.StringEscapeUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link media="screen" href="elements/css/style.css" type="text/css" rel="stylesheet" />
<title>記事一覧</title>
</head>

<body>
<!-- wrap start -->
<div id="wrap">

	<jsp:include page="header.jsp" />
	
	<!-- contentWrap start -->
	<div id="contentWrap">
		
		<!-- content start -->
		<div id="content">
			<!-- newPost start -->
			<div id="newPost">
				<a href="createArticle.jsp" title="新規作成画面へ">新規</a>
			</div>
			<!-- /newPost end -->
			
			<!-- print blog's content -->
			
			<c:choose>
				<c:when test="${! empty articlesList.list }">
					<c:forEach var="article" items="${articlesList.list }" varStatus="status">
						<!-- blog start-->
						<div class="blog">
							<!-- blog_title start -->
							<div class="blogTitle">
								
								<div class="entry">
									<div class="date">${article.date }</div>
									<div class="editor">
										<a href="edit-article?id=${article.id}" title="編集"><img src="elements/img/btn/btn_edit.png" alt="編集" border="0" /></a>
										<a href="remove-article?id=${article.id}" title="削除" onclick="return confirmAction()"><img src="elements/img/btn/btn_delete.png" alt="削除" border="0" /></a>
									</div>
								</div>
								<h1>
									<c:out value="${article.title }"></c:out>
									<c:if test="${article.newArticle }">
										<span>NEW!</span>
									</c:if>
		
								</h1>
							</div>
							<!-- /blog_title end -->
							
							<div class="blogBody">
								${article.contentEscapeHtml }
							</div>
							
						</div>
						<!-- /blog end-->
					</c:forEach>				
				</c:when>
				<c:otherwise>
					<div class="errMes">
					${articlesList.searchInfo.searchText } に該当する記事は見つかりませんでした。
					</div>
				</c:otherwise>
			</c:choose>
			
			<!-- end blog's content -->
			
			
			<jsp:include page="pager.jsp" />
			 
			 
		</div>
		<!-- /content end -->
		
		<jsp:include page="side.jsp" />
		
		<jsp:include page="footer.jsp" />
		
	</div>
	<!-- /contentWrap end -->

</div>
<!-- /wrap end -->

<script>
function confirmAction() {
	var confirmed = confirm("削除してもよいですか？");
	return confirmed;
}
</script>
</body>
</html>
