package edu.gatech.edutech.smarterap.dtos;

import org.pojomatic.annotations.AutoProperty;

@AutoProperty
public class Answer extends BaseDto {

	private String text;
	private String message;
	private boolean correct;
	
}
