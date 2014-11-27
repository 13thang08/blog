package jp.zyyx.training;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateArticleServlet
 */
@WebServlet("/create-article")
public class CreateArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		Date date = new Date();
		String dateString = dateFormat.format(date);
		ArticleBean article = new ArticleBean();
		article.setDate(dateString);
		article.setTitle(title);
		article.setContent(content);
		
		if (title.trim().length() != 0 && content.trim().length() != 0) {
			ArticleService articleService = new FileDataService();
			if (articleService.addArticle(article)) {
				response.sendRedirect("show-articles");
			} else {
				// process for can't add article case
				request.setAttribute("article", article);
				request.setAttribute("databaseError", "登録に失敗しました。");
				request.getRequestDispatcher("createArticle.jsp").forward(request, response);
			}
			
		} else {
			request.setAttribute("article", article);
			request.getRequestDispatcher("createArticle.jsp").forward(request, response);
		}
		
	}

}
