<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.zyyx.training.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%! ArticlesList articlesList = null;%>
<%! int start, stop, current, total = 0; %>
<%! int numPages = 5; %>
<%! String URLString = null; %>

<%
articlesList = (ArticlesList) request.getAttribute("articlesList");
if (articlesList != null && articlesList.getList().size() != 0) {
	URLString = "show-articles?searchText=" + articlesList.getSearchInfo().getSearchText() + "&searchDate=" + articlesList.getSearchInfo().getSearchDate() +"&page=";
	current = articlesList.getSearchInfo().getPage();
	total = articlesList.getTotalPage();
	if (1 <= current && current <= total) {
		if (total < numPages) {
			start = 1;
			stop = total;
		} else {
			start = current - (numPages - 1) / 2;
			if (start < 1) {
				start = 1;
				stop = numPages;
			} else {
				stop = current + (numPages - 1) / 2;
				stop = (stop < total) ? stop : total;
				start = stop - numPages + 1;
			}
		}
		pageContext.setAttribute("start", start);
		pageContext.setAttribute("stop", stop);
		pageContext.setAttribute("current", current);
		pageContext.setAttribute("total", total);
		pageContext.setAttribute("URLString", URLString);
	}
}
%>
<c:if test="${1 <= current && current <= total && total > 1}">
	<div class="pager">
		<ul>
			<c:choose>
				<c:when test="${current == 1 }">
					<li><span> &lt; 前へ</span></li>
				</c:when>
				<c:otherwise>
					<li><a href="${URLString }${current - 1}" onclick="return changeContent(this)">&lt; 前へ</a></li>
				</c:otherwise>
			</c:choose>
			
			<c:forEach var="i" begin="${start }" end="${stop }" step="1">
				<c:choose>
					<c:when test="${i == current }">
						<li><span class="now">${i }</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="${URLString }${i}" onclick="return changeContent(this)">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:choose>
				<c:when test="${current == total }">
					<li><span> 次へ ></span></li>
				</c:when>
				<c:otherwise>
					<li><a href="${URLString }${current + 1 }" onclick="return changeContent(this)">次へ ></a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</c:if>
