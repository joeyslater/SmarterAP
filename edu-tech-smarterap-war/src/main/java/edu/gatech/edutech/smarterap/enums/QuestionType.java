package edu.gatech.edutech.smarterap.enums;

public enum QuestionType
{
	//TODO Fill in different types when more question types added.
	MULTIPLE_CHOICE("Multiple Choice");

	private String type;

	QuestionType(final String type)
	{
		this.type = type;
	}

	public String getType()
	{
		return type;
	}

	public static QuestionType getValueFromString(final String type)
	{
		for (final QuestionType questionType : QuestionType.values())
		{
			if (questionType.getType().equalsIgnoreCase(type))
			{
				return questionType;
			}
		}
		return null;
	}
}
