package jp.zyyx.training.utility;

/**
 * ユーティリティークラス
 * @author thangvm
 *
 */
public class Utility {
	
	/**
	 * ストリングの先頭とリアのスペースを削除
	 * @param str
	 * @return
	 */
	public static String preprocessingString (String str) {
		if (str != null) {
			str = str.trim();
			if (str.length() == 0) {
				str = null;
			}
		}
		return str;
	}
	
}
