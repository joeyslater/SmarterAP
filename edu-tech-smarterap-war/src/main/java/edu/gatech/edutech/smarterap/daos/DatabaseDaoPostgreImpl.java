package edu.gatech.edutech.smarterap.daos;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import edu.gatech.edutech.smarterap.dtos.BaseDto;
import edu.gatech.edutech.smarterap.exceptions.ErrorCode;
import edu.gatech.edutech.smarterap.exceptions.SmarterApException;

@Repository
public class DatabaseDaoPostgreImpl implements DatabaseDao
{
	private final SessionFactory sessionFactory;

	public DatabaseDaoPostgreImpl()
	{
		sessionFactory = null;
	}

	public DatabaseDaoPostgreImpl(final SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	private <T> List<T> findByCriteria(final Criteria criteria)
	{
		return criteria.list();
	}

	@Override
	public <T extends BaseDto> void merge(final List<T> dtos)
	{
		for (final T dto : dtos)
		{
			merge(dto);
		}
	}

	@Override
	public <T extends BaseDto> void merge(final T dto)
	{
		try
		{
			getCurrentSession().merge(dto);
		}
		catch (final Throwable e)
		{
			throw new SmarterApException(ErrorCode.DATABASE_UPDATE, e.getMessage(), e);
		}
	}

	@Override
	public <T extends BaseDto> void delete(final List<T> dtos)
	{
		if (dtos != null)
		{
			for (final T dto : dtos)
			{
				delete(dto);
			}
		}
	}

	@Override
	public <T extends BaseDto> void delete(final T dto)
	{
		try
		{
			getCurrentSession().delete(dto);
		}
		catch (final Throwable e)
		{
			throw new SmarterApException(ErrorCode.DATABASE_DELETE, e.getMessage(), e);
		}
	}

	@Override
	public <T extends BaseDto> void delete(final Class<T> clazz, final Serializable uid)
	{
		try
		{
			final Query query = createQuery(clazz, QueryConstants.DELETE_FROM);
			query.setParameter("uid", uid);
			query.executeUpdate();
		}
		catch (final Throwable e)
		{
			throw new SmarterApException(ErrorCode.DATABASE_DELETE, e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends BaseDto> T get(final T dto)
	{
		try
		{
			return (T) get(dto.getClass(), dto.getUid());
		}
		catch (final Throwable e)
		{
			throw new SmarterApException(ErrorCode.DATABASE_FIND, e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends BaseDto> T get(final Class<T> clazz, final Serializable uid)
	{
		try
		{
			return (T) getCurrentSession().get(clazz, uid);
		}
		catch (final Exception e)
		{
			throw new SmarterApException(ErrorCode.DATABASE_FIND, e.getMessage(), e);
		}
	}

	@Override
	public <T extends BaseDto> void saveOrUpdate(final T dto)
	{
		try
		{
			getCurrentSession().saveOrUpdate(dto);
		}
		catch (final Exception e)
		{
			throw new SmarterApException(ErrorCode.DATABASE_UPDATE, e.getMessage(), e);
		}
	}

	@Override
	public <T extends BaseDto> void saveOrUpdate(final List<T> dtos)
	{
		for (final T dto : dtos)
		{
			saveOrUpdate(dto);
		}
	}

	@Override
	public <T extends BaseDto> List<T> list(final Class<? extends T> clazz)
	{
		return findByCriteria(getCurrentSession().createCriteria(clazz));
	}

	@Override
	public <T extends BaseDto> List<T> list(final Class<T> clazz, final List<Serializable> uids)
	{
		final Criteria criteria = getCurrentSession().createCriteria(clazz);
		final Disjunction or = Restrictions.disjunction();
		for (final Serializable uid : uids)
		{
			or.add(Restrictions.idEq(uid));
		}
		criteria.add(or);
		return findByCriteria(criteria);
	}

	private <T> Query createQuery(final Class<T> clazz, final String queryType)
	{
		return getCurrentSession().createQuery(queryType + clazz.getSimpleName() + QueryConstants.WHERE_ID);
	}

	private class QueryConstants
	{
		public static final String	WHERE_ID	= "where uid = :uid";
		public static final String	DELETE_FROM	= "delete from ";
	}
}