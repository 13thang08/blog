package jp.zyyx.training.control;

import jp.zyyx.training.model.ServiceFactory;

/**
 * システムの使っている各パラメーター
 * @author thangvm
 *
 */
public class ApplicationParameter {
	
	/**
	 * SERVICEはMYSQL、または FILESYS
	 */
	public static final int SERVICE = ServiceFactory.MYSQL;
}
