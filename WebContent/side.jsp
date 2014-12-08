<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jp.zyyx.training.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- side start -->
<div id="side">
	<div class="sideTitle">プロフィール</div>
	<div class="sideBody">
		<p class="profile"><img src="elements/img/profile/momiji.png" /></p>
		<p>
			ニックネーム:タン<br />
			性別:男<br />
			誕生日:1991年8月13日<br />
			血液型:O<br />
			好きな言葉:
		</p>
	</div>
	
	<div class="sideTitle">サイト内検索</div>
	<div class="sideBody">
		<div class="search">
			<form action="show-articles">
				<input type="text" name="searchText" value="${articlesList.searchInfo.searchText }" class="search_text">
				<input type="submit" value="検索">
			</form>
		</div>
	</div>
	
	<c:if test="${!empty articlesCalendar }">
		<div class="sideTitle">カレンダー</div>
		<div class="sideBody">
			<table border="0" cellpadding="2" cellspacing="1" bgcolor="#666666" width="100%">
			<tr align="center" bgcolor="#FFFFFF">
				<td colspan="7">
					<table border="0" cellpadding="0" cellspacing="0" width="100%" class="calender">
						<tr>
							<td align="left">
								<c:choose>
									<c:when test="${!empty articlesCalendar.previousMonth}">
										<a href="show-articles?calendar=${articlesCalendar.previousMonth }">&lt; 前月へ</a>
									</c:when>
									<c:otherwise>
										&lt; 前月へ
									</c:otherwise>
								</c:choose>
							</td>
							<td align="center">${articlesCalendar.currentMonth }</td>
							<td align="right">
								<c:choose>
									<c:when test="${!empty articlesCalendar.nextMonth }">
										<a href="show-articles?calendar=${articlesCalendar.nextMonth }">次月へ &gt;</a>
									</c:when>
									<c:otherwise>
										次月へ &gt;
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr align="center" bgcolor="#CCCCCC">
				<td>日</td>
				<td>月</td>
				<td>火</td>
				<td>水</td>
				<td>木</td>
				<td>金</td>
				<td>土</td>
			</tr>
				<c:forEach var="i" begin="0" end="${articlesCalendar.rows * 7 - 1 }"
					step="1">
	
					<c:if test="${(i % 7) == 0 }">
						<% out.println("<tr align=\"center\" bgcolor=\"#FFFFFF\">"); %>
					</c:if>
					<c:set var="day" value="${i + 2 - articlesCalendar.firstDayOfWeek}"></c:set>
					<c:choose>
						<c:when test="${ (day >= 1) && (day <= articlesCalendar.lastDayOfMonth) }">
							<c:choose>
								<c:when test="${(i % 7) == 0 || (i % 7) == 6 }">
									<% out.println("<td bgcolor=\"#FFD9D9\">"); %>
								</c:when>
								<c:otherwise>
									<% out.println("<td>"); %>
								</c:otherwise>
							</c:choose>
	
							<c:set var="contains" value="false"></c:set>
							<c:forEach var="item" items="${articlesCalendar.articleDays }">
								<c:if test="${item eq day }">
									<c:set var="contains" value="true"></c:set>
								</c:if>
							</c:forEach>
	
							<c:choose>
								<c:when test="${contains}">
									<c:set var="dayString" value="${day }"></c:set>
									<c:if test="${day < 10 }">
										<c:set var="dayString" value="0${day }"></c:set>
									</c:if>
									<a href="show-articles?searchDate=${articlesCalendar.currentMonth }-${dayString}">${day }</a>
								</c:when>
								<c:otherwise>
									${day }
								</c:otherwise>
							</c:choose>
	
							<% out.println("</td>"); %>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
					<c:if test="${(i % 7) == 6 }">
						<% out.println("</tr>"); %>
					</c:if>
	
	
				</c:forEach>
			</table>
		</div>
	</c:if>
	
</div>
<!-- /side end -->