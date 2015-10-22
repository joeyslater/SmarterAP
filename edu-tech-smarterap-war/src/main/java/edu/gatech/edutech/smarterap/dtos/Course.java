package edu.gatech.edutech.smarterap.dtos;

public class Course extends BaseDto {

	private static final long serialVersionUID = 1L;
	private String subject;
	private String period;
	private int year;
	
	public Course(){
		
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
