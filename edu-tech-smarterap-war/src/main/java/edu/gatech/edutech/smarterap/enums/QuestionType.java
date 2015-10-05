package edu.gatech.edutech.smarterap.enums;

public enum QuestionType {
	MULTIPLE_CHOICE("Multiple Choice");
	
	private String type;
	
	QuestionType(String type){
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public static QuestionType getValueFromString(String type){
		for (QuestionType questionType : QuestionType.values()) {
			if (questionType.getType().equalsIgnoreCase(type)){
				return questionType;
			}
		}
		return null;
	}
}
