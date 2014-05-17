package com.weixin.domain.model.shop;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1476627600563934250L;


	private String comment;
	
	@ManyToOne
	private Member member;


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
