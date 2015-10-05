package edu.gatech.edutech.smarterap.dtos;

import java.util.Set;

import org.pojomatic.annotations.AutoProperty;

import edu.gatech.edutech.smarterap.enums.QuestionType;

@AutoProperty
public class Question extends BaseDto{

	private String text;
	private QuestionType type; 
	private Set<Answer> answers;
	private String hint;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public QuestionType getType() {
		return type;
	}
	public void setType(QuestionType type) {
		this.type = type;
	}
	public Set<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}

	
	
}
