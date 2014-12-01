package jp.zyyx.training;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String idString = request.getParameter("id");
		if (idString != null) {
			try {
				int id = Integer.parseInt(idString);
				ArticleService articleService = new DatabaseService();
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int id;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		Date date = new Date();
		String dateString = dateFormat.format(date);
		ArticleBean article = new ArticleBean();
		article.setTitle(title);
		article.setContent(content);
		article.setId(id);
		article.setDate(dateString);
		
		if (title.trim().length() != 0 && content.trim().length() != 0) {
			ArticleService articleService = new DatabaseService();
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
