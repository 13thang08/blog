package jp.zyyx.training.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.zyyx.training.model.ArticleService;
import jp.zyyx.training.model.ArticlesList;
import jp.zyyx.training.model.SearchInfo;
import jp.zyyx.training.model.ServiceFactory;
import jp.zyyx.training.utility.Utility;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/show-articles")
public class ShowArticlesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowArticlesServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pageString = request.getParameter("page");
		String searchText = request.getParameter("searchText");
		String searchDate = request.getParameter("searchDate");
		
		int page = 1;
		try {
			page = Integer.parseInt(pageString);
		} catch(NumberFormatException e) {
			
		}
		
		SearchInfo searchInfo = new SearchInfo(searchText, page, searchDate);
		
		ArticleService articleService = ServiceFactory.getService(ApplicationParameter.SERVICE);
		ArticlesList articlesList = articleService
				.showArticles(searchInfo);
		
		request.setAttribute("articlesList", articlesList);
		request.getRequestDispatcher("/showArticles.jsp").forward(request,
				response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
