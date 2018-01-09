package br.com.saude.api.generic;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

public class CriteriaExample {
	private Example example;
	private List<Criterion> criterions;
	private Object entity;
	
	public Example getExample() {
		return example;
	}
	public void setExample(Example example) {
		this.example = example;
	}
	public List<Criterion> getCriterions() {
		return criterions;
	}
	public void setCriterions(List<Criterion> criterions) {
		this.criterions = criterions;
	}
	public Object getEntity() {
		return entity;
	}
	public void setEntity(Object entity) {
		this.entity = entity;
	}
	
}
