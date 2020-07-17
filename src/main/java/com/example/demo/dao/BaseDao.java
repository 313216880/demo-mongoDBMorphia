/*
 * Company: 
 * Copyright (c) 2012-2032 
 * All Rights Reserved.
 */
package com.example.demo.dao;

import java.util.List;

import com.example.demo.common.Page;


public interface BaseDao<T> {

	/**
	 * 
	 * Description: 添加
	 * @param entity
	 * @return
	 */
	T save(T entity);
	
	/**
	 * 
	 * Description: 批量添加
	 * @param entities
	 * @return
	 */
	List<T> save(List<T> entities);
	
	/**
	 * 
	 * Description: 查询全部
	 * @return
	 */
	List<T> findAll();
	
	/**
	 * 
	 * Description: 条件精确查询
	 * @param entity
	 * @return
	 */
	List<T> queryByExample(T entity);
	
	/**
	 * 
	 * Description: 条件精确查询并分页
	 * @param entity 查询参数
	 * @param size 每页条数
	 * @param page 第几页
	 * @return
	 */
	Page<T> queryByExample(T entity, int size, int page);
	
	/**
	 * 
	 * Description: 根据对象条件查询结果并全部更新
	 * @param entity
	 * @param updateEntity
	 * @return
	 */
	long update(T entity, T updateEntity);
	
	/**
	 * 
	 * Description: 根据id删除
	 * @param entity
	 * @return
	 */
	long delete(T entity);
}
