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

			CSVReader reader;
			reader = new CSVReader(new FileReader("D:\\data.csv"));
			int count = 0;
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null
					&& count < numArticlesPerPage * (pageIndex + 1)) {
				if (count >= numArticlesPerPage * pageIndex) {
					ArticleBean bean = new ArticleBean();
					if (nextLine[0] == null || nextLine[1] == null
							|| nextLine[2] == null || nextLine[3] == null) {
						System.out.println("File format error!\n");
						reader.close();
						return null;
					} else {
						bean.setId(Integer.parseInt(nextLine[0]));
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

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean addArticle(ArticleBean article) {
		article.setId(getNewId());
		try {
			CSVWriter writer = new CSVWriter(new FileWriter("D:\\temp.csv"));
			String[] entry = new String[4];
			entry[0] = Integer.toString(article.getId());
			entry[1] = article.getDate();
			entry[2] = article.getTitle();
			entry[3] = article.getContent();
			writer.writeNext(entry);
			writer.close();

			Process p = Runtime.getRuntime().exec(
					"cmd.exe /c type D:\\data.csv >> D:\\temp.csv");
			p.waitFor();
			p = Runtime.getRuntime().exec(
					"cmd.exe /c mv D:\\temp.csv D:\\data.csv");
			p.waitFor();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	private int getNewId() {
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
		return ret+1;
	}

	@Override
	public boolean removeArticle(int id) {
		// TODO Auto-generated method stub
		boolean ret = false;
		
		try {
			CSVReader reader = new CSVReader(new FileReader("D:\\data.csv"));
			CSVWriter writer = new CSVWriter(new FileWriter("D:\\temp.csv"));
			String[] readLine;
			while ((readLine = reader.readNext()) != null) {
				if (readLine[0] == null || readLine[1] == null || readLine[2] == null || readLine[3] == null) {
					System.out.println("File data error format!\n");
					reader.close();
					writer.close();
					return false;
				} else {
					if (Integer.parseInt(readLine[0]) != id) {
						writer.writeNext(readLine);
					} else {
						ret = true;
					}
				}
			}
			reader.close();
			writer.close();
			Process p = Runtime.getRuntime().exec("cmd.exe /c mv D:\\temp.csv D:\\data.csv");
			p.waitFor();
			return ret;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean editArticle(ArticleBean article) {
		// TODO Auto-generated method stub
		boolean ret = false;
		if (article.getId() == 0 || article.getDate() == null || article.getTitle() == null || article.getContent() == null) {
			return false;
		}
		
		try {
			CSVReader reader = new CSVReader(new FileReader("D:\\data.csv"));
			CSVWriter writer = new CSVWriter(new FileWriter("D:\\temp.csv"));
			String[] readLine;
			while ((readLine = reader.readNext()) != null) {
				if(readLine[0] == null || readLine[1] == null || readLine[2] == null || readLine[3] == null) {
					System.out.println("File data error!\n");
				} else {
					if (Integer.parseInt(readLine[0]) != article.getId()) {
						writer.writeNext(readLine);
					} else {
						String[] tempString = new String[4];
						tempString[0] = Integer.toString(article.getId());
						tempString[1] = article.getDate();
						tempString[2] = article.getTitle();
						tempString[3] = article.getContent();
						// test code
						System.out.println(tempString[0]);
						System.out.println(tempString[1]);
						System.out.println(tempString[2]);
						System.out.println(tempString[3]);
						writer.writeNext(tempString);
						ret = true;
					}
				}
			}
			reader.close();
			writer.close();
			Process p;
			if (ret == true) {
				p = Runtime.getRuntime().exec("cmd.exe /c mv D:\\temp.csv D:\\data.csv");
				p.waitFor();
			} else {
				p = Runtime.getRuntime().exec("cmd.exe /c rm D:\\temp.csv");
				p.waitFor();
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
