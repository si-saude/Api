package br.com.saude.api.generic;

public abstract class GenericFilter {
	protected long id;
	protected int pageNumber;
	protected int pageSize;
	protected OrderFilter order;
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public OrderFilter getOrder() {
		return order;
	}
	public void setOrder(OrderFilter order) {
		this.order = order;
	}
	
}
