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
 *数据操作层异常处理类
 */
public class DaoException extends BaseException {

	private static final long serialVersionUID = -9006104640618533135L;

	public DaoException(String messageKey) {
		super.setMessageKey(messageKey);
	}

	public DaoException(String messageKey, Throwable t) {
		super.setMessageKey(messageKey);
		super.initCause(t);
	}

	public DaoException(Throwable t) {
		super.setMessageKey(t.getClass().getSimpleName());
		super.initCause(t);
	}

	public Throwable getOrignalException() {
		Throwable t = this.getCause();
		while(t.getCause() != null){
			t = t.getCause();
		}
		return t;
	}

	public String getOrignalMessageKey() {
		return this.getOrignalException().getClass().getSimpleName();
	}
}
