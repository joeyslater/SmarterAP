package edu.gatech.edutech.smarterap.dtos;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

/**
 * This class serves as a super class so all subclasses extend Pojomatic hashCode, equals, toString methods.
 */

@AutoProperty
public class BaseDto {
	
	//Used for the database for unique identifiers
	private long uid = -1l;
	
	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	@Override
	public int hashCode() {
		return Pojomatic.hashCode(this);
	}
	
	@Override
	public boolean equals(Object other) {
		return Pojomatic.equals(this, other);
	}
	
	@Override
	public String toString() {
		return Pojomatic.toString(this);
	}
	
}
