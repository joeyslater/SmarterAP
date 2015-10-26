package edu.gatech.edutech.smarterap.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gatech.edutech.smarterap.daos.DatabaseDao;
import edu.gatech.edutech.smarterap.dtos.BaseDto;
import edu.gatech.edutech.smarterap.dtos.json.JsonResponse;
import edu.gatech.edutech.smarterap.exceptions.SmarterApException;

@Service
public class CrudService
{
	@Autowired
	private DatabaseDao databaseDao;

	public <T extends BaseDto> List<T> list(final Class<T> clazz)
	{
		return databaseDao.list(clazz);
	}

	public <T extends BaseDto> T get(final Class<T> clazz, final Long uid)
	{
		return databaseDao.get(clazz, uid);
	}

	public <T extends BaseDto> JsonResponse<T> create(final T dto)
	{
		boolean success = true;
		String message = "Created Dto";

		try
		{
			databaseDao.saveOrUpdate(dto);
			if (databaseDao.get(dto) == null || dto.getUid() != -1)
			{
				message = "Unable to create";
				success = false;
			}
		}
		catch (final Exception e)
		{
			message = "Unable to create";
			success = false;
		}
		return new JsonResponse<T>(success, message, dto);
	}

	public <T extends BaseDto> JsonResponse<T> update(final Long uid, final T dto)
	{
		boolean success = true;
		String message = "Updated Dto";

		try
		{
			databaseDao.saveOrUpdate(dto);
			if (databaseDao.get(dto) == null || dto.getUid() != uid)
			{
				message = "Unable to update";
				success = false;
			}
		}
		catch (final SmarterApException e)
		{
			message = "Unable to update";
			success = false;
		}
		return new JsonResponse<T>(success, message, dto);
	}

	public <T extends BaseDto> JsonResponse<T> delete(final Class<T> clazz, final Long uid)
	{
		boolean success = true;
		String message = "Deleted Dto";

		try
		{
			databaseDao.delete(clazz, uid);
			if (databaseDao.get(clazz, uid) != null)
			{
				message = "Failed to delete";
				success = false;
			}
		}
		catch (final SmarterApException e)
		{
			message = "Failed to delete";
			success = false;
		}
		return new JsonResponse<T>(success, message, null);
	}
}
