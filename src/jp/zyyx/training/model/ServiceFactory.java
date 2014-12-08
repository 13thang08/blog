package jp.zyyx.training.model;

/**
 * 適当なサービスを取るためのクラス
 * @author thangvm
 *
 */
public class ServiceFactory {
	
	public static final int MYSQL = 0;
	public static final int FILESYS = 1;
	
	/**
	 * 適当なサービスを取る
	 * @return FileDataService または DatabaseService
	 */
	public static ArticleService getService(int type) {
		switch (type) {
		case MYSQL:
			return ((ArticleService) new DatabaseService());
		case FILESYS:
			return ((ArticleService) new FileDataService());
		default:
			return null;
		}
	}
}
