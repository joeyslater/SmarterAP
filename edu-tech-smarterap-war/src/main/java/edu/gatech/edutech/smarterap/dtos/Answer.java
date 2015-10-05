package edu.gatech.edutech.smarterap.dtos;

import org.pojomatic.annotations.AutoProperty;

@AutoProperty
public class Answer extends BaseDto {

	private int order;
	private String text;
	private String message;
	private boolean correct;
	
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
	
	
}
