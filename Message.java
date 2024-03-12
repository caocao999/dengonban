package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="messages")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;
	
	@Column(length = 80, nullable = false)
	@NotBlank(message="メッセージを入力してください")
	private String messageBody; 
	
	@Column(length = 80, nullable = false)
	@NotBlank(message="名前を入力してください")
	private String name; 

	@Column(length = 40, nullable = false)
	private String postedDateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostedDateTime() {
		return postedDateTime;
	}

	public void setPostedDateTime(String postedDateTime) {
		this.postedDateTime = postedDateTime;
	} 
}
