package jp.zyyx.training;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pageString = request.getParameter("page");
		int page;
		try {
			page = Integer.parseInt(pageString);
		} catch(NumberFormatException e) {
			page = 1;
		}
		if (page < 1)
			page = 1;
		ArticleService articleService = new FileDataService();
		ArticlesList articlesList = articleService
				.showArticles(null, page);
		// test start
		System.out.println(articlesList.getTotalPage());
		// test end
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
