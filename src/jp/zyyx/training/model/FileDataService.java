package jp.zyyx.training.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
		ArticlesList articlesList = new ArticlesList(searchText, page);

		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader("D:\\data.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		int count = 0;
		String[] nextLine;
		try {
			// データファイルを読む
			while ((nextLine = reader.readNext()) != null) {

				// 記事は表示したいページにあったら
				if (count >= numArticlesPerPage * (page - 1)
						&& count < numArticlesPerPage * page) {
					ArticleBean bean = new ArticleBean();

					// エラーハンドリング
					if (nextLine[0] == null || nextLine[1] == null
							|| nextLine[2] == null || nextLine[3] == null) {
						System.out.println("File format error!\n");
						return null;
					} else { // アウトプットデータのため、Beanを作成
						bean.setId(Integer.parseInt(nextLine[0]));
						bean.setDate(nextLine[1]);
						bean.setTitle(nextLine[2]);
						bean.setContent(nextLine[3]);
					}
					articlesList.addArticle(bean); // リストに記事を追加する
				}
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		articlesList.setTotalPage((int) Math.ceil(((double) count)
				/ numArticlesPerPage));
		return articlesList;

	}

	/**
	 * 記事データに記事を追加するメソッド
	 * @param article 追加したい記事
	 * @return true 追加成功
	 *         false 追加失敗
	 */	
	@Override
	public boolean addArticle(ArticleBean article) {
		// 適当なidを取れない
		if (getNewId() == -1) {
			return false;
		}
		
		article.setId(getNewId());
		CSVWriter writer;
		try {
			writer = new CSVWriter(new FileWriter("D:\\temp.csv"));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	
		// 一時的なファイルに記事を書き込む
		String[] entry = new String[4];
		entry[0] = Integer.toString(article.getId());
		entry[1] = article.getDate();
		entry[2] = article.getTitle();
		entry[3] = article.getContent();
		writer.writeNext(entry);

		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			// データファイルを更新する
			Process p = Runtime.getRuntime().exec(
					"cmd.exe /c type D:\\data.csv >> D:\\temp.csv");
			p.waitFor();
			p = Runtime.getRuntime().exec(
					"cmd.exe /c mv D:\\temp.csv D:\\data.csv");
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 新しい記事のために、適当なidを取る
	 * 
	 * @return 適当なid 失敗したら、‐1を返す
	 */
	private int getNewId() {
		int ret = 0;
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader("D:\\data.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1;
		}

		String[] firstLine;
		try {
			if ((firstLine = reader.readNext()) != null) {
				ret = Integer.parseInt(firstLine[0]);
			}
			return ret + 1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				return -1;
			}
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
		
		CSVReader reader;
		CSVWriter writer;
		
		try {
			reader = new CSVReader(new FileReader("D:\\data.csv"));
			writer = new CSVWriter(new FileWriter("D:\\temp.csv"));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		String[] readLine;
		try {
			while ((readLine = reader.readNext()) != null) {
				if (readLine[0] == null || readLine[1] == null || readLine[2] == null || readLine[3] == null) {
					System.out.println("File data error format!\n");
					ret = false;
					break;
				} else {
					if (Integer.parseInt(readLine[0]) != id) {
						writer.writeNext(readLine);
					} else {
						ret = true;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			ret = false;
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
				ret = false;
			}
		}
			
		try {
			Process p = Runtime.getRuntime().exec("cmd.exe /c mv D:\\temp.csv D:\\data.csv");
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
			ret = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			ret = false;
		}
		return ret;
	}

	/**
	 * 記事を編集するメソッド
	 * @param article 編集したい情報があるAritcleBeanオブジェクト
	 * @return true 編集成功
	 *         false 編集失敗
	 */
	@Override
	public boolean editArticle(ArticleBean article) {
		if (!removeArticle(article.getId())) {
			return false;
		}
		
		if (!addArticle(article)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 記事をとるメソッド
	 * @param id 取りたい記事のid
	 * @return idがある記事
	 */
	@Override
	public ArticleBean getArticle(int id) {
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader("D:\\data.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		boolean isExist = false;
		ArticleBean article = new ArticleBean();

		String[] readLine;
		try {
			while ((readLine = reader.readNext()) != null) {
				if (readLine[0] == null || readLine[1] == null
						|| readLine[2] == null || readLine[3] == null) {
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
			if (isExist) {
				return article;
			} else {
				return null;
			}

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
