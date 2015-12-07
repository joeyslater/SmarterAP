package edu.gatech.edutech.smarterap.dtos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

/**
 * This class serves as a super class so all subclasses extend Pojomatic hashCode, equals, toString methods.
 */

@Entity
@AutoProperty
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseDto
{
	//Used for the database for unique identifiers
	@Id
	@GeneratedValue
	private Long uid = -1L;

	public BaseDto()
	{

	}

	public BaseDto(final Long uid)
	{
		this.uid = uid;
	}

	public Long getUid()
	{
		return uid;
	}

	public void setUid(final Long uid)
	{
		this.uid = uid;
	}

	@Override
	public int hashCode()
	{
		return Pojomatic.hashCode(this);
	}

	@Override
	public boolean equals(final Object other)
	{
		return Pojomatic.equals(this, other);
	}

	@Override
	public String toString()
	{
		return Pojomatic.toString(this);
	}
}
