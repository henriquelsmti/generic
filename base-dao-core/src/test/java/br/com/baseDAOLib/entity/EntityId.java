package br.com.baseDAOLib.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;


@MappedSuperclass
public abstract class EntityId implements Serializable{
	

	private static final long serialVersionUID = -1842377326317111427L;
	@Id
	@GeneratedValue
	protected long id;

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@PrePersist
	public void peristente(){
		System.out.println("Entity super");
		
	}
	
	
}
