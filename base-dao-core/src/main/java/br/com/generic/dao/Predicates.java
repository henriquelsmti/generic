/*
 * Copyright 2015 Henrique Luiz da Silva Mota
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package br.com.generic.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public enum Predicates {
	NOT_EQUAL("!=") {
		@Override
		public <T> Predicate getPredicate(CriteriaBuilder builder,
				Root<T> root, Parameter parameter) {
			String property = parameter.getProperty();
			
			return builder.notEqual(this.<T>getPath(root, property)
					.get(getLastProperty(property)), parameter.getValue());
		}
	},
	
	GREATER_THAN_OR_EQUAL_TO(">=") {
		@Override
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public <T> Predicate getPredicate(CriteriaBuilder builder,
				Root<T> root, Parameter parameter) {
			String property = parameter.getProperty();

			
			return builder.greaterThanOrEqualTo(this.<T>getPath(root, property)
					.<Comparable>get(getLastProperty(property)), (Comparable) parameter.getValue());
		}
	},
	
	LESS_THAN_OR_EQUAL_TO("<=") {
		@Override
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public <T> Predicate getPredicate(CriteriaBuilder builder,
				Root<T> root, Parameter parameter) {
			String property = parameter.getProperty();
			return builder.lessThan(this.<T>getPath(root, property)
					.<Comparable>get(getLastProperty(property)), (Comparable) parameter.getValue());
		}
	},
	
	GREATER_THAN(">") {
		@Override
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public <T> Predicate getPredicate(CriteriaBuilder builder,
				Root<T> root, Parameter parameter) {
			String property = parameter.getProperty();
			return builder.greaterThan(this.<T>getPath(root, property)
					.<Comparable>get(getLastProperty(property)), (Comparable) parameter.getValue());
		}
	},
	
	LESS_THAN("<") {
		@Override
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public <T> Predicate getPredicate(CriteriaBuilder builder,
				Root<T> root, Parameter parameter) {
			String property = parameter.getProperty();
			return builder.lessThan(this.<T>getPath(root, property)
					.<Comparable>get(getLastProperty(property)), (Comparable) parameter.getValue());
		}
	},
	
	LIKE("+") {
		@Override
		public <T> Predicate getPredicate(CriteriaBuilder builder,
				Root<T> root, Parameter parameter) {
			String property = parameter.getProperty();
			return builder.like(this.<T>getPath(root, property)
					.<String>get(getLastProperty(property)), (String) parameter.getValue());
		}
	},
	
	//sempre colocar esse por ultimo
	EQUAL("=") {
		@Override
		public <T> Predicate getPredicate(CriteriaBuilder builder,
				Root<T> root, Parameter parameter) {
			String property = parameter.getProperty();
			return builder.equal(this.<T>getPath(root, property)
					.get(getLastProperty(property)), parameter.getValue());
		}
	};
	
	
	private String value;
	
	private Predicates(String value){
		this.value = value;
	}
	
	public abstract <T> Predicate getPredicate(CriteriaBuilder builder, Root<T> root, Parameter parameter);

	protected <T> Path<T> getPath(Root<T> root, String properties){
		
		if(properties.contains(".")){
			String[] ropertys =  properties.split("\\.");
			
			Path<T> path = root;
			
			for(int i = 0 ; i < (ropertys.length - 1) ; i++){
				path = root.get(ropertys[i]);
			}
			return path;
		}else{
			return root;
		}
	}
	
	protected String getLastProperty(String property){
		if(property.contains(".")){
			return property.substring(property.lastIndexOf(".") + 1);
		}else{
			return property;
		}
	}
	
	public String getValue() {
		return value;
	}
	
}
