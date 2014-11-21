package jp.zyyx.training;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

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

	@Override
	public boolean addArticle(ArticleBean article) {
		article.setId(getNewId());
		try {
			CSVWriter writer = new CSVWriter(new FileWriter("D:\\temp.csv"));
			String[] entry = new String[4];
			entry[0] = article.getId();
			entry[1] = article.getDate();
			entry[2] = article.getTitle();
			entry[3] = article.getContent();
			writer.writeNext(entry);
			writer.close();
			
			final Runtime rt = Runtime.getRuntime();
			rt.exec("cmd.exe /c type D:\\data.csv >> D:\\temp.csv");
			rt.exec("cmd.exe /c mv D:\\temp.csv D:\\data.csv");
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	private String getNewId(){
		int ret = 0;
		try {
			CSVReader reader = new CSVReader(new FileReader("D:\\data.csv"));
			String[] firstLine;
			if ((firstLine = reader.readNext()) != null) {
				ret = Integer.parseInt(firstLine[0]);
			}
			reader.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Integer.toString(ret+1);
	}

}
