package com.base.springboot.common.util;

/**
 * @Description 集成mybatis RowBounds 实现分页 总记录数封装
 *
 * @author luoqi
 * @date 2017-1-12
 */
public class RowBounds extends org.apache.ibatis.session.RowBounds {

	public final static int NO_ROW_OFFSET = 0;
	public final static int NO_ROW_LIMIT = Integer.MAX_VALUE;
	public final static RowBounds DEFAULT = new RowBounds();

	private int offset;
	private int limit;
	private int count;

	public RowBounds() {
		this.offset = NO_ROW_OFFSET;
		this.limit = NO_ROW_LIMIT;
	}

	public RowBounds(int offset, int limit) {
		this.offset = offset;
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
