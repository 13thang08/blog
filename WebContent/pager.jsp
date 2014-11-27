<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.zyyx.training.*"%>

<%! ArticlesList articlesList = null;%>
<%! int start, stop, current, total; %>
<%! int numPages = 5; %>
<%! String URLString; %>

<%
articlesList = (ArticlesList) request.getAttribute("articlesList");
if (articlesList != null && articlesList.getList().size() != 0) {
	if (articlesList.getSearchText() != null && articlesList.getSearchText().trim().length() != 0) {
		URLString = "show-articles?searchText=" + articlesList.getSearchText() + "&page=";
	} else {
		URLString = "show-articles?page=";
	}
	current = articlesList.getPage();
	total = articlesList.getTotalPage();
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
		
	}%>
	<div class="pager">
		<ul>
			<%
			if (current == 1) {
				out.println("<li><span> < 前へ</span></li>");
			} else {
				out.println("<li><a href=\""+ URLString + (current - 1) +"\">< 前へ</a></li>");
			}
			
			for (int i = start; i <= stop; i++) {
				if (i == current) {
					out.println("<li><span class=\"now\">" + i + "</span></li>");
				} else {
					out.println("<li><a href=\"" + URLString + i + "\">" + i + "</a></li>");
				}
			}
			if (current == total) {
				out.println("<li><span> 次へ ></span></li>");
			} else {
				out.println("<li><a href=\""+ URLString + (current + 1) + "\">次へ ></a></li>");
			}
			%>
		</ul>
	</div>		
<%}
%>

