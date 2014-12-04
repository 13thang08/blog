package jp.zyyx.training.control;

import java.io.IOException;
import java.text.DateFormat;
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
import jp.zyyx.training.utility.*;

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
		request.setCharacterEncoding("UTF-8");
		
		// 新しい記事Beanを作成します
		String title = Utility.preprocessingString(request.getParameter("title"));
		String content = Utility.preprocessingString(request.getParameter("content"));
		DateFormat dateFormat = new SimpleDateFormat(ArticleService.dateFormat);
		Date date = new Date();
		String dateString = dateFormat.format(date);
		ArticleBean article = new ArticleBean();
		article.setDate(dateString);
		article.setTitle(title);
		article.setContent(content);
		
		if (title != null && content != null) { //　タイトルと内容は正したら
			ArticleService articleService = ServiceFactory.getService();
			if (articleService.addArticle(article)) { //　追加は成功したら
				response.sendRedirect("show-articles");
			} else { //　追加は失敗したら
				request.setAttribute("article", article);
				request.setAttribute("databaseError", "登録に失敗しました。");
				request.getRequestDispatcher("createArticle.jsp").forward(request, response);
			}
			
		} else { //　タイトルまたは内容は正しくなかったら
			request.setAttribute("article", article);
			request.getRequestDispatcher("createArticle.jsp").forward(request, response);
		}
		
	}

}
