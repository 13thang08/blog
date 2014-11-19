package jp.zyyx.training;

import java.io.FileReader;
import com.opencsv.CSVReader;

public class FileDataService implements ArticleService {

	@Override
	public ArticlesList showArticles(String searchText, int pageIndex) {
		// TODO Auto-generated method stub
		try {
			ArticlesList articlesList = new ArticlesList(searchText, pageIndex);

			if (searchText == null || searchText.trim().length() == 0) {
				CSVReader reader;
				reader = new CSVReader(new FileReader("D:\\data.csv"));
				int count = 0;
				String[] nextLine;
				while ((nextLine = reader.readNext()) != null
						&& count < numArticlesPerPage * (pageIndex + 1)) {
					if (count >= numArticlesPerPage * pageIndex) {
						ArticleBean bean = new ArticleBean();
						if (nextLine[0] == null || nextLine[1] == null || nextLine[2] == null || nextLine[3] == null) {
							System.out.println("File format error!\n");
							reader.close();
							return null;
						} else {
							bean.setId(nextLine[0]);
							bean.setDate(nextLine[1]);
							bean.setTitle(nextLine[2]);
							bean.setContent(nextLine[3]);
						}
						articlesList.addArticle(bean);
					}
					count++;
				}
				reader.close();
				return articlesList;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
