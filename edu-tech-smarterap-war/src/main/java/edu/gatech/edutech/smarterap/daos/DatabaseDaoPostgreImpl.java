package edu.gatech.edutech.smarterap.daos;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
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
	@SuppressWarnings("unchecked")
	public <T> T getByUniqueField(final Class<T> clazz, final String field, final Object value)
	{
		try
		{
			if (value != null)
			{
				final Criteria criteria = getCurrentSession().createCriteria(clazz);
				criteria.add(Restrictions.ilike(field, value));
				final List<Object> list = findByCriteria(criteria);
				if (list != null && list.size() == 1)
				{
					return (T) list.get(0);
				}
			}
			return null;
		}
		catch (final Exception e)
		{
			e.printStackTrace();
			return null;
		}
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
			e.printStackTrace();
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
		System.out.println(queryType + clazz.getSimpleName() + QueryConstants.WHERE_ID);
		return getCurrentSession().createQuery(queryType + clazz.getSimpleName() + QueryConstants.WHERE_ID);
	}

	private class QueryConstants
	{
		public static final String	WHERE_ID	= " where uid = :uid";
		public static final String	DELETE_FROM	= "delete from ";
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends BaseDto> List<T> getByUniqueFieldInCollection(final Class<T> clazz, final String collection, final String alias, final String field, final Object value)
	{
		return buildCollectionCriteria(clazz, collection, alias, field, value).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends BaseDto> List<T> getByUniqueFieldInCollectionWithOtherCriteria(final Class<T> clazz, final String collection, final String alias, final String field,
	        final Object value, final Map<String, Object> criterias)
	{
		return buildCollectionCriteria(clazz, collection, alias, field, value).add(Restrictions.allEq(criterias)).list();
	}

	private <T extends BaseDto> Criteria buildCollectionCriteria(final Class<T> clazz, final String collection, final String alias, final String field, final Object value)
	{
		return getCurrentSession().createCriteria(clazz).createAlias(collection, alias).add(Restrictions.eq(alias + "." + field, value));
	}

	@Override
	public <T> Long count(final Class<T> clazz)
	{
		return (Long) getCurrentSession().createCriteria(clazz).setProjection(Projections.rowCount()).uniqueResult();
	}
}