package jp.zyyx.training;

import java.io.IOException;
import java.util.ArrayList;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArticleService arcticleService = new FileDataService();
		int pageIndex = 0;
		ArticlesList articlesList = arcticleService.showArticles(null, pageIndex);
		System.out.println(pageIndex);
		if (articlesList != null) {
			ArrayList<ArticleBean> beanList = articlesList.getList();
			for (int i = 0; i < beanList.size(); i++) {
				System.out.println(beanList.get(i).getId());
				System.out.println(beanList.get(i).getDate());
				System.out.println(beanList.get(i).getTitle());
				System.out.println(beanList.get(i).getContent());
				System.out.println();
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
