package edu.gatech.edutech.smarterap.services;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import edu.gatech.edutech.smarterap.daos.DatabaseDao;
import edu.gatech.edutech.smarterap.dtos.BaseDto;
import edu.gatech.edutech.smarterap.dtos.json.JsonResponse;
import edu.gatech.edutech.smarterap.exceptions.SmarterApException;

@Service
@Transactional
public class CrudService
{
	private final Logger	LOG	= LoggerFactory.getLogger(CrudService.class);

	@Autowired
	private DatabaseDao		databaseDao;

	public <T extends BaseDto> List<T> list(final Class<T> clazz)
	{
		try
		{
			return databaseDao.list(clazz);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
			return Lists.newArrayList();
		}
	}

	public <T extends BaseDto> T get(final Class<T> clazz, final Serializable uid)
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
			System.out.println(e.getMessage());
			message = "Unable to create";
			success = false;
			e.printStackTrace();
		}
		return new JsonResponse<T>(success, message, dto);
	}

	public <T extends BaseDto> JsonResponse<T> update(final Serializable uid, final T dto)
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

	public <T extends BaseDto> JsonResponse<T> delete(final Class<T> clazz, final Serializable uid)
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

	public <T> Long count(final Class<T> clazz)
	{
		try
		{
			return databaseDao.count(clazz);
		}
		catch (final SmarterApException e)
		{
			return -1L;
		}
	}
}
