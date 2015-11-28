package edu.gatech.edutech.smarterap.daos;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import edu.gatech.edutech.smarterap.dtos.BaseDto;

@Transactional(readOnly = true)
public interface DatabaseDao
{
	public <T> T getByUniqueField(final Class<T> clazz, final String field, final Object value);

	@Transactional(readOnly = false)
	public <T extends BaseDto> void merge(List<T> dtos);

	@Transactional(readOnly = false)
	public <T extends BaseDto> void merge(T dto);

	@Transactional(readOnly = false)
	public <T extends BaseDto> void delete(List<T> dto);

	@Transactional(readOnly = false)
	public <T extends BaseDto> void delete(T dto);

	@Transactional(readOnly = false)
	public <T extends BaseDto> void delete(Class<T> clazz, Serializable uid);

	public <T extends BaseDto> T get(T dto);

	public <T extends BaseDto> T get(Class<T> clazz, Serializable uid);

	@Transactional(readOnly = false)
	public <T extends BaseDto> void saveOrUpdate(T dto);

	@Transactional(readOnly = false)
	public <T extends BaseDto> void saveOrUpdate(List<T> dtos);

	public <T extends BaseDto> List<T> list(Class<? extends T> clazz);

	public <T extends BaseDto> List<T> list(Class<T> clazz, List<Serializable> uids);

	public <T extends BaseDto> List<T> getByUniqueFieldInCollection(final Class<T> clazz, final String collection, final String alias, String field, final Object value);

	public <T extends BaseDto> List<T> getByUniqueFieldInCollectionWithOtherCriteria(Class<T> clazz, String collection, String alias, String field, Object value,
	        Map<String, Object> criterias);

	public <T> Long count(Class<T> clazz);
}
