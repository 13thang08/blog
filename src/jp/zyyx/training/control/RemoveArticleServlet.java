package jp.zyyx.training.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.zyyx.training.model.ArticleService;
import jp.zyyx.training.model.ServiceFactory;
import jp.zyyx.training.utility.Utility;

/**
 * Servlet implementation class RemoveArticleServlet
 */
@WebServlet("/remove-article")
public class RemoveArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = Utility.preprocessingString(request.getParameter("id"));
		try {
			int id = Integer.parseInt(idString);
			ArticleService articleService = ServiceFactory.getService();
			if (articleService != null) {
				articleService.removeArticle(id);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		response.sendRedirect("show-articles");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
