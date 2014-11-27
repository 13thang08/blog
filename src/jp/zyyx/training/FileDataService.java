package jp.zyyx.training;

import java.io.FileReader;
import java.io.FileWriter;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
 * ファイルとしてデータをアクセスするクラス
 * @author thangvm
 *
 */
public class FileDataService implements ArticleService {

	/**
	 * 記事を表示するメソッド
	 * @param searchText 検索のストリング、検索しない場合はnullになります
	 * @param page 表示したいページ
	 * @return 検索結果のArticlesListオブジェクト
	 */
	@Override
	public ArticlesList showArticles(String searchText, int page) {
		// TODO Auto-generated method stub
		try {
			ArticlesList articlesList = new ArticlesList(searchText, page);

			CSVReader reader;
			reader = new CSVReader(new FileReader("D:\\data.csv"));
			int count = 0;
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null
					&& count < numArticlesPerPage * page) {
				if (count >= numArticlesPerPage * (page - 1)) {
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

	/**
	 * 記事データに記事を追加するメソッド
	 * @param article 追加したい記事
	 * @return true 追加成功
	 *         false 追加失敗
	 */	
	@Override
	public boolean addArticle(ArticleBean article) {
		
		if (getNewId() == -1) {
			return false;
		}
		
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
			return ret+1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 記事を削除するメソッド
	 * @param id 削除したい記事のid
	 * @return true 削除成功
	 *         false 削除失敗
	 */	
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

	/**
	 * 記事を編集するメソッド
	 * @param article 編集したい情報があるAritcleBeanオブジェクト
	 * @return true 編集成功
	 *         false 編集失敗
	 */
	@Override
	public boolean editArticle(ArticleBean article) {
		// TODO Auto-generated method stub
		boolean ret = false;
		if (article.getId() == 0 || article.getTitle() == null || article.getContent() == null) {
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
						tempString[1] = readLine[1];
						tempString[2] = article.getTitle();
						tempString[3] = article.getContent();
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
	
	/**
	 * 記事をとるメソッド
	 * @param id 取りたい記事のid
	 * @return idがある記事
	 */
	@Override
	public ArticleBean getArticle(int id) {
		try {
			boolean isExist = false;
			ArticleBean article = new ArticleBean();
			CSVReader reader = new CSVReader(new FileReader("D:\\data.csv"));
			String[] readLine;
			while ((readLine = reader.readNext()) != null) {
				if (readLine[0] == null || readLine[1] == null || readLine[2] == null || readLine[3] == null) {
					isExist = false;
					break;
				} else if (Integer.parseInt(readLine[0]) == id) {
					article.setId(Integer.parseInt(readLine[0]));
					article.setDate(readLine[1]);
					article.setTitle(readLine[2]);
					article.setContent(readLine[3]);
					isExist = true;
					break;
				}
			}
			
			reader.close();
			if (isExist) {
				return article;
			} else {
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
