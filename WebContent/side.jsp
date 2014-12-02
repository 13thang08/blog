<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.zyyx.training.*"%>

<%! ArticlesList articlesList = null;%>
<%! String searchText = null; %>
<%
articlesList = (ArticlesList) request.getAttribute("articlesList");
if (articlesList != null) {
	searchText = articlesList.getSearchText();
}
%>
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
				<input type="text" name="searchText" <%if (searchText != null) {out.println("value=" + "'" + searchText + "'");} %>class="search_text">
				<input type="submit" value="検索">
			</form>
		</div>
	</div>
	
	<div class="sideTitle">カレンダー</div>
	<div class="sideBody">
		<table border="0" cellpadding="2" cellspacing="1" bgcolor="#666666" width="100%">
		<tr align="center" bgcolor="#FFFFFF">
			<td colspan="7">
				<table border="0" cellpadding="0" cellspacing="0" width="100%" class="calender">
					<tr>
						<td align="left"><a href="#">&lt; 前月へ</a></td>
						<td align="center">2014年4月</td>
						<td align="right"><a href="#">次月へ &gt;</a></td>
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
		<tr align="center" bgcolor="#FFFFFF">
			<td></td>
			<td></td>
			<td>1</td>
			<td>2</td>
			<td>3</td>
			<td>4</td>
			<td bgcolor="#FFD9D9"><a href="#">5</a></td>
		</tr>
		<tr align="center" bgcolor="#FFFFFF">
			<td bgcolor="#FFD9D9"><a href="#">6</a></td>
			<td><a href="#">7</a></td>
			<td><a href="#">8</a></td>
			<td><a href="#">9</a></td>
			<td>10</td>
			<td>11</td>
			<td bgcolor="#FFD9D9">12</td>
		</tr>
		<tr align="center" bgcolor="#FFFFFF">
			<td bgcolor="#FFD9D9">13</td>
			<td>14</td>
			<td>15</td>
			<td>16</td>
			<td>17</td>
			<td>18</td>
			<td bgcolor="#FFD9D9">19</td>
		</tr>
		<tr align="center" bgcolor="#FFFFFF">
			<td bgcolor="#FFD9D9">20</td>
			<td>21</td>
			<td>22</td>
			<td>23</td>
			<td>24</td>
			<td>25</td>
			<td bgcolor="#FFD9D9">26</td>
		</tr>
		<tr align="center" bgcolor="#FFFFFF">
			<td bgcolor="#FFD9D9">27</td>
			<td>28</td>
			<td bgcolor="#FFD9D9">29</td>
			<td>30</td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		</table>


	</div>
	
</div>
<!-- /side end -->