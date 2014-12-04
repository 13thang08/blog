package jp.zyyx.training.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.zyyx.training.model.ArticleService;
import jp.zyyx.training.model.ArticlesList;
import jp.zyyx.training.model.ServiceFactory;

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
		
		if ("".equals(searchText)) {
			searchText = null;
		}
		
		int page;
		try {
			page = Integer.parseInt(pageString);
		} catch(NumberFormatException e) {
			page = 1;
		}
		if (page < 1)
			page = 1;
		ArticleService articleService = ServiceFactory.getService();
		ArticlesList articlesList = articleService
				.showArticles(searchText, page);
		
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
