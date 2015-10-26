package edu.gatech.edutech.smarterap.dtos.json;

import org.pojomatic.annotations.AutoProperty;

@AutoProperty
public class JsonResponse<T> extends JsonDto
{
	private boolean	success;
	private String	message;
	private T		data;

	public JsonResponse()
	{
	}

	public JsonResponse(final boolean success, final String message, final T data)
	{
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(final boolean success)
	{
		this.success = success;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(final String message)
	{
		this.message = message;
	}

	public T getData()
	{
		return data;
	}

	public void setData(final T data)
	{
		this.data = data;
	}

}
