/*
 * Company: 
 * Copyright (c) 2012-2032 
 * All Rights Reserved.
 */
package com.example.demo.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.common.Page;
import com.example.demo.dao.BaseDao;
import com.mongodb.client.result.UpdateResult;

import dev.morphia.Datastore;
import dev.morphia.UpdateOptions;
import dev.morphia.query.FindOptions;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.updates.UpdateOperators;
import dev.morphia.query.internal.MorphiaCursor;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@SuppressWarnings("unchecked")
	private Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
			.getActualTypeArguments()[0];

	@Autowired
	private Datastore datastore;

	@Override
	public T save(T entity) {
		return datastore.save(entity);
	}

	@Override
	public List<T> save(List<T> entities) {
		return datastore.save(entities);
	}

	@Override
	public List<T> findAll() {
		Query<T> query = datastore.find(entityClass);
		List<T> list = new ArrayList<T>();
		query.forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Page<T> queryByExample(T entity, int pageSize, int currentPage) {
		int skip = pageSize * (currentPage - 1);
		Query<T> query = datastore.queryByExample(entity);
		FindOptions options = new FindOptions();
		options.limit(pageSize).skip(skip);
		MorphiaCursor<T> morphiaCursor = query.iterator(options);
		List<T> list = new ArrayList<T>();
		morphiaCursor.forEachRemaining(e -> list.add(e));
		Page<T> page = new Page<T>();
		page.setPageSize(pageSize);
		page.setTotalRow(query.count());
		page.setData(list);
		return page;
	}

	@Override
	public List<T> queryByExample(T entity) {
		Query<T> query = datastore.queryByExample(entity);
		List<T> list = new ArrayList<T>();
		query.forEach(e -> list.add(e));
		return list;
	}

	@Override
	public long update(T entity, T updateEntity) {
		Query<T> query = datastore.queryByExample(entity);
		// new UpdateOptions().multi(true) 如果不设置只会更新第一条
		UpdateResult updateResult = query.update(UpdateOperators.set(updateEntity))
				.execute(new UpdateOptions().multi(true));
		return updateResult.getMatchedCount();
	}

	@Override
	public long delete(T entity) {
		return datastore.delete(entity).getDeletedCount();
	}

}
