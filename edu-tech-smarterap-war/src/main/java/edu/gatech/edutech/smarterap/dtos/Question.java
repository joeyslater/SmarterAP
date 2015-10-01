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
	
	
}
