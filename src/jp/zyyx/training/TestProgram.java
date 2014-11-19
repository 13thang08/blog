package jp.zyyx.training;

import java.util.ArrayList;

public class TestProgram {
	public static void main(String[] args) {
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
}
