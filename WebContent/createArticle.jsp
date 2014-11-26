<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.zyyx.training.*,java.util.ArrayList" %>
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
			<!-- newPost start -->
			<div id="newPost">
				<a href="createArticle.jsp" title="新規作成画面へ">新規</a>
			</div>
			<!-- /newPost end -->
			
			<!-- form start -->
				<div id="form">
					<form action="create-article" method="post">
						<h1>記事タイトル</h1>
						<input type="text" maxlength="30" name="title" class="postTitle">	
						<h1>記事内容</h1>
						<textarea name="content" class="postContent"></textarea>
						
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