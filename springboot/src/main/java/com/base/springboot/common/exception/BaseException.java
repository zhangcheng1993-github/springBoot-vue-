/**
 *
 * 成都铁路局科研所
 * <ul>
 * <li>Author: 焦运磊</li>
 * <li>E-Mail: jylapple@163.com</li>
 * <li>T-Phone: 18628168078</li>
 * <li>Date: 2012-12-11</li>
 * <li>Description:</li>
 * <li>+-History-------------------------------------+</li>
 * <li>| Date Author Description</li>
 * <li>|2012-12-11 焦运磊 Created</li>
 * <li>+------------------------------------------------</li>
 * </ul>
 */
package com.base.springboot.common.exception;


/**
 *异常处理基类
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -3653870580234213024L;
	protected String messageKey;


	public BaseException() {
		super();
	}

	public BaseException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public BaseException(Throwable throwable) {
		super(throwable);
	}

	public BaseException(String messageKey) {
		super();
		this.messageKey = messageKey;
	}


	/**
	 * 取得异常信息key
	 *
	 * @author 焦运磊
	 * @date 2011-3-21
	 * @return String
	 */
	public String getMessageKey() {
		return messageKey;
	}


	/**
	 * 设置异常信息key
	 *
	 * @author 焦运磊
	 * @date 2011-3-21
	 * @param messageKey
	 * @return void
	 */
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	@Override
	public String getMessage() {
		return messageKey;
	}


}
