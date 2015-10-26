package edu.gatech.edutech.smarterap.dtos.json;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

@AutoProperty
public class JsonDto
{
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
