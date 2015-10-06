package br.com.baseDAOLib.entity;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;

@Embeddable
public class Email {

	private String email;
	
	@PrePersist
	public void con(){
		System.out.println("Embeddable");
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
