package jp.zyyx.training;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

public class FileDataService implements ArticleService{

	@Override
	public ArticlesList showArticles(String searchText, int pageIndex) {
		// TODO Auto-generated method stub
		
		ArticlesList articlesList = new ArticlesList(searchText, pageIndex);
		
		
		if (searchText == null || searchText.trim().length() == 0) {
			CSVReader reader;
			try {
				reader = new CSVReader(new FileReader("data.csv"));
			} catch (FileNotFoundException e) {
				System.out.println("Can't read the data!\n");
				e.printStackTrace();
				return null;
			}
			int count = 0;
			String[] nextLine;
			try {
				while ((nextLine = reader.readNext()) != null  && count < numArticlesPerPage * (pageIndex + 1)) {
					if (count >= numArticlesPerPage * pageIndex) {
						ArticleBean bean = new ArticleBean();
						if (nextLine[0] == null) {
							System.out.printf("Error format!\n");
							return null;
						} else {
							bean.setId(nextLine[0]);
						}
						
						if (nextLine[1] == null) {
							System.out.println("Error format!\n");
							return null;
						} else {
							bean.setDate(nextLine[1]);
						}
						
						if (nextLine[2] == null) {
							System.out.println("Error format!\n");
							return null;
						} else {
							bean.setTitle(nextLine[2]);
						}
						
						if (nextLine[3] == null) {
							System.out.println("Error format!\n");
							return null;
						} else {
							bean.setContent(nextLine[3]);
						}
						articlesList.addArticle(bean);
					}
					count++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return articlesList;
		}
		
		return null;
	}
	
}
