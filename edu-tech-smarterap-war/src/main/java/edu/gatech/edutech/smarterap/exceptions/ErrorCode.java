package edu.gatech.edutech.smarterap.exceptions;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

/**
 * Use in conjunction with SmarterApException.
 * A-B
 * 	A: Category
 *  B: Error Id
 */

@AutoProperty
public final class ErrorCode
{
	private String						errorCode;
	private static final ResourceBundle	BUNDLE	= ResourceBundle.getBundle(ErrorCode.class.getName());

	private ErrorCode()
	{

	}

	private ErrorCode(final String errorCode)
	{
		this.errorCode = errorCode;
	}

	public static final ErrorCode	UNKNOWN			= new ErrorCode("0-0");

	public static final ErrorCode	DATABASE_FIND	= new ErrorCode("1-0");
	public static final ErrorCode	DATABASE_UPDATE	= new ErrorCode("1-1");
	public static final ErrorCode	DATABASE_DELETE	= new ErrorCode("1-2");

	//TODO Create more for the Conversion Utilities
	public String getErrorCode()
	{
		return errorCode;
	}

	public String getErrorCodeDescription()
	{
		try
		{
			return BUNDLE.getString(errorCode);
		}
		catch (final MissingResourceException e)
		{
			return "!" + errorCode + "!";
		}
	}

	@Override
	public String toString()
	{
		return Pojomatic.toString(this);
	}
}
