package jp.zyyx.training;

/**
 * 適当なサービスを取るためのクラス
 * @author thangvm
 *
 */
public class ServiceFactory {
	
	/**
	 * 適当なサービスを取る
	 * @return FileDataService または DatabaseService
	 */
	public static ArticleService getService() {
		return new FileDataService();
	}
}
