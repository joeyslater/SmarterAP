package edu.gatech.edutech.smarterap.exceptions;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.google.common.collect.Lists;

/**
 * Custom exception class to throw. It can bubble to the top and can be resolved after the fact.
 */
@AutoProperty
public class SmarterApException extends RuntimeException
{
	private static final long	serialVersionUID	= 1L;
	private ErrorCode			errorCode;
	private Throwable			targetException		= null;
	private List<String>		errorMessages		= Lists.newArrayList();

	public SmarterApException()
	{
		super();
	}

	public SmarterApException(final ErrorCode errorCode, final String errorMessage, final Throwable targetException)
	{
		this(errorCode, newArrayList(errorMessage), targetException);
	}

	public SmarterApException(final ErrorCode errorCode, final List<String> errorMessages, final Throwable targetException)
	{
		super(errorMessages.toString(), targetException);
		this.errorCode = errorCode;
		this.errorMessages = errorMessages;
		this.targetException = targetException;
	}

	public SmarterApException(final ErrorCode errorCode, final List<String> errorMessages)
	{
		this(errorCode, errorMessages, null);
	}

	public SmarterApException(final ErrorCode errorCode, final String errorMessage)
	{
		this(errorCode, errorMessage, null);
	}

	public ErrorCode getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(final ErrorCode errorCode)
	{
		this.errorCode = errorCode;
	}

	public Throwable getTargetException()
	{
		return targetException;
	}

	public void setTargetException(final Throwable targetException)
	{
		this.targetException = targetException;
	}

	public List<String> getErrorMessages()
	{
		return errorMessages;
	}

	public void setErrorMessages(final List<String> errorMessages)
	{
		this.errorMessages = errorMessages;
	}

	@Override
	public String toString()
	{
		return Pojomatic.toString(this);
	}

}
