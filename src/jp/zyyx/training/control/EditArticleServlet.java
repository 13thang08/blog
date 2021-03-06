package jp.zyyx.training.control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.zyyx.training.model.ArticleBean;
import jp.zyyx.training.model.ArticleService;
import jp.zyyx.training.model.ServiceFactory;
import jp.zyyx.training.utility.Utility;

/**
 * Servlet implementation class EditArticleServlet
 */
@WebServlet("/edit-article")
public class EditArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idString = Utility.preprocessingString(request.getParameter("id"));
		if (idString != null) {
			try {
				int id = Integer.parseInt(idString);
				ArticleService articleService = ServiceFactory.getService(ApplicationParameter.SERVICE);
				ArticleBean article = articleService.getArticle(id);
				if (article != null) {
					request.setAttribute("article", article);
					request.getRequestDispatcher("editArticle.jsp").forward(request, response);
				} else {
					System.out.println("Can't find the article!\n");
					response.sendRedirect("show-articles");
				}
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
				response.sendRedirect("show-articles");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = Utility.preprocessingString(request.getParameter("title"));
		String content = Utility.preprocessingString(request.getParameter("content"));
		int id;
		try {
			id = Integer.parseInt(Utility.preprocessingString(request.getParameter("id")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(ArticleService.dateFormat);
		Date date = new Date();
		String dateString = dateFormat.format(date);
		ArticleBean article = new ArticleBean();
		article.setTitle(title);
		article.setContent(content);
		article.setId(id);
		article.setDate(dateString);
		
		if (!"".equals(title) && !"".equals(content)) {
			ArticleService articleService = ServiceFactory.getService(ApplicationParameter.SERVICE);
			if (articleService.editArticle(article)) {
				response.sendRedirect("show-articles");
			} else {
				// process for can't edit article case
				request.setAttribute("article", article);
				request.setAttribute("databaseError", "更新に失敗しました。");
				request.getRequestDispatcher("editArticle.jsp").forward(request, response);
			}
			
		} else {
			request.setAttribute("article", article);
			request.getRequestDispatcher("editArticle.jsp").forward(request, response);
		}
	}

}
