/*
 * 
 */

/**
 * Class Description -This is  class has implementation of all the methods 
 *					 declared in IRefMastermaintainDAO class  
 * 
 * @author Mehul
 */
package com.delaypredictions.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delaypredictions.util.ApplicationConstantsUtil;
import com.delaypredictions.util.SearchParameter;
import com.delaypredictions.util.SortDetail;




@Repository
public class RefMasterMaintainDAOImpl<T> extends AbstractDAO {

	
	protected Log LOGGER = LogFactory.getLog(getClass());
	
	
	/**
	 * This method is is used to find the entity List from database based on the
	 * entity type , list of searchparameters and sort details entered by the
	 * user
	 * 
	 * @param Class
	 *            <T> entityType
	 * @param List
	 *            <SearchParameter> searchParameters
	 * @param List
	 *            <SortDetail> sortDetails
	 * @throws WebDaoException
	 * @return List<T>
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findEntityList(final Class<T> entityType,
			final List<SearchParameter> searchParameters,
			final List<SortDetail> sortDetails)  {
		LOGGER.info("findEntityList Method Start");
		Session session = this.getSession();
		Criteria c = createCriteria(entityType,
				searchParameters, session, sortDetails);
		List<T> entityList = c.list();
		session.flush();
		LOGGER.info("findEntityList Method End");
		return entityList;
		
	}

	/**
	 * This method is used to get count the number of records for the search
	 * criteria entered by the user.
	 * 
	 * @param Class
	 *            <T> entityType
	 * @param List
	 *            <SearchParameter> searchParameters
	 * @throws WebDaoException
	 * @return Long
	 */
	public Long getRecordCount(Class<T> entityType,
			List<SearchParameter> searchParameters)  {
		LOGGER.info("getRecordCount Method Start");
		Session session = this.getSession();
		Criteria c = createCriteria(entityType, searchParameters, session, null);
		c.setProjection(Projections.rowCount()); // All the entity classes needs
		// to have primary column
		// variable name as id
		Long collectionSize = (Long) c.uniqueResult();
		LOGGER.info("getRecordCount Method End");
		return collectionSize;
	}

	/**
	 * This method is used to create criteria based on details entered by the
	 * user.
	 * 
	 * @param Class
	 *            <T> entityType
	 * @param List
	 *            <SearchParameter> searchParameters
	 * @param Session
	 *            session
	 * @param List
	 *            <SortDetail> sortDetails
	 * @throws WebDaoException
	 * @return Criteria
	 */
	private Criteria createCriteria(Class<T> entityType,
			List<SearchParameter> searchParameters, Session session,
			List<SortDetail> sortDetails) {
		Criteria c = session.createCriteria(entityType);

		List<String> totalAliasList = new ArrayList<String>();
		c = generateAlias(searchParameters, totalAliasList, c);

		if (sortDetails != null) {
			Map<String, String> aliasMap = null;
			for (SortDetail sortDetail : sortDetails) {
				if (null != sortDetail.getSearchAliasMap()) {
					aliasMap = sortDetail.getSearchAliasMap();
					for (Map.Entry<String, String> alias : aliasMap.entrySet()) {
						if (alias != null
								&& !totalAliasList.contains(alias.getKey())) {
							c.createAlias(alias.getValue(), alias.getKey());
							totalAliasList.add(alias.getKey());
						}
					}
				}
				if (sortDetail.getSortBy() != null) {
					if (ApplicationConstantsUtil.DESCENDING.equals(sortDetail
							.getSortDirection())) {
						c.addOrder(Order.desc(sortDetail.getSortBy())
								.ignoreCase());
					} else {
						c.addOrder(Order.asc(sortDetail.getSortBy())
								.ignoreCase());
					}
				}
			}
		}
		return c;
	}

	/**
	 * This method is is used to merge Entity into database based on the Entity
	 * given by the user
	 * 
	 * @param entity
	 * @throws WebDaoException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public T mergeEntity(T entity) {
		LOGGER.info("mergeEntity Method Start");
		return (T) this.getSession().merge(entity);
	}
	
	/**
	 * This method is is used to merge Entity into database based on the Entity
	 * given by the user
	 * 
	 * @param entity
	 * @throws WebDaoException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public T saveEntity(T entity) {
		LOGGER.info("mergeEntity Method Start");
		return (T) this.getSession().save(entity);
	}
	
	
	public void saveOrUpdateEntity(T entity) {
		LOGGER.info("saveOrUpdateEntity Method Start");
		System.out.println("save or update");
		this.getSession().saveOrUpdate(entity);

	}


	/**
	 * This method is is used to search the entity List from database based on
	 * the entity and id entered by the user.
	 * 
	 * @param Class
	 *            <T> entityType
	 * @param Long
	 *            id
	 * @throws WebDaoException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public T findById(Class<T> entityType, Long id)  {
		return (T) this.getSession().load(entityType, id);
	}

	/**
	 * This method is used to find the List for the search criteria entered by
	 * the user.
	 * 
	 * @param Class
	 *            <T> entityType
	 * @param List
	 *            <SearchParameter> searchParameters
	 * @param DatatablesCriterias
	 *            criterias
	 * @return List<T>
	 */
		/**
	 * This method is used to get the filtered count of records for the search
	 * criteria entered by the user
	 * 
	 * @param Class
	 *            <T> entityType
	 * @param List
	 *            <SearchParameter> searchParameters
	 * @param DatatablesCriterias
	 *            criterias
	 * @return Long
	 */
	/**
	 * This method is used to get the total count of records for the entityType
	 * entered by the user
	 * 
	 * @param Class
	 *            <T> entityType
	 * @return Long
	 */

	public Long getTotalCount(Class<T> entityType) {
		LOGGER.info("getTotalCount Method Start");
		Session session = getSessionFactory()
				.getCurrentSession();
		Criteria c = session.createCriteria(entityType);
		Long collectionSize = Long.parseLong(String.valueOf(c.list().size()));
		LOGGER.info("getTotalCount Method End");
		return collectionSize;
	}

	
	private Criteria buildCriteria(SearchParameter searchParam, Criteria c) {
		boolean searchFlag = false;
		boolean longFlag = false;
		boolean dateFlag = false;
		boolean intFlag = false;
		Criteria criteria = c;
		Object searchValue = searchParam.getSearchValue();
		if(searchValue != null){
			if (searchValue.getClass().equals(String.class)) {
				String searchVal = (String) searchValue;
				if (!searchVal.isEmpty()) {
					searchValue = searchVal.trim();
				}
				searchFlag = true;
			} else if (searchValue.getClass().equals(Long.class)) {
				searchFlag = true;
				longFlag = true;
			} else if (searchValue.getClass().equals(Date.class)) {
				searchFlag = true;
				dateFlag = true;
			} else if (searchValue.getClass().equals(Integer.class)) {
				searchFlag = true;
				intFlag = true;
			} else {
				searchFlag = true;
			}
		} else {
			searchFlag = true;
		}
		String searchType = searchParam.getSearchType();
		if(searchValue != null){
			if (searchFlag && !searchValue.toString().isEmpty()) {
				if (searchType.equals(ApplicationConstantsUtil.MC_LIKE)) {
					criteria = criteria.add(Restrictions.ilike(searchParam
							.getSearchColumn(), searchValue.toString(),
							MatchMode.EXACT));
				} else if (searchType.equals(ApplicationConstantsUtil.MC_EQUAL)) {
					if (longFlag) {
						criteria = criteria.add(Restrictions.eq(
								searchParam.getSearchColumn(), searchValue));
					} else if (dateFlag) {
						criteria = criteria.add(Restrictions.eq(
								searchParam.getSearchColumn(), (Date) searchValue));
					} else if (intFlag) {
						criteria = criteria.add(Restrictions.eq(
								searchParam.getSearchColumn(),
								Integer.parseInt(searchValue.toString())));
					} else {
						criteria = criteria.add(Restrictions.eq(
								searchParam.getSearchColumn(),
								searchValue.toString()).ignoreCase());
					}

				} else if (searchType.equals(ApplicationConstantsUtil.MC_NOT_EQUAL)) {
					if (longFlag) {
						criteria = criteria.add(Restrictions.ne(
								searchParam.getSearchColumn(), searchValue));
					} else if (dateFlag) {
						criteria = criteria.add(Restrictions.ne(
								searchParam.getSearchColumn(), (Date) searchValue));
					} else if (intFlag) {
						criteria = criteria.add(Restrictions.ne(
								searchParam.getSearchColumn(),
								Integer.parseInt(searchValue.toString())));
					} else {
						criteria = criteria.add(Restrictions.ne(
								searchParam.getSearchColumn(),
								searchValue.toString()).ignoreCase());
					}

				}  

				else if (searchParam.getSearchType().equals(
						ApplicationConstantsUtil.MC_BETWEEN)) {
					criteria = criteria.add(Restrictions.between(
							searchParam.getSearchColumn(), searchValue,
							searchParam.getSearchValue2()));
				}  else if (searchParam.getSearchType().equals(
						ApplicationConstantsUtil.MC_LESS_THAN)) {
					criteria = criteria.add(Restrictions.lt(
							searchParam.getSearchColumn(), searchValue));
				} else if (searchParam.getSearchType().equals(
						ApplicationConstantsUtil.MC_GREATER_THAN)) {
					criteria = criteria.add(Restrictions.gt(
							searchParam.getSearchColumn(), searchValue));
				}else if (searchParam.getSearchType().equals(
						ApplicationConstantsUtil.MC_LESS_THAN_EQUAL)) {
					criteria = criteria.add(Restrictions.le(
							searchParam.getSearchColumn(), searchValue));
				} else if (searchParam.getSearchType().equals(
						ApplicationConstantsUtil.MC_GREATER_THAN_EQUAL)) {
					criteria = criteria.add(Restrictions.ge(
							searchParam.getSearchColumn(), searchValue));
				} else if (searchParam.getSearchType().equals(
						ApplicationConstantsUtil.MC_IN)) {
					if (searchParam.getSearchValue() instanceof Set) {
						criteria = criteria
								.add(Restrictions.in(searchParam.getSearchColumn(),
										(Set<?>) searchValue));
					} else if (searchParam.getSearchValue() instanceof List) {
						criteria = criteria.add(Restrictions.in(
								searchParam.getSearchColumn(),
								(List<?>) searchValue));
					}
				}
			}
		}else if (searchType.equals(ApplicationConstantsUtil.MC_IS_NULL)) {
			criteria = criteria.add(Restrictions.isNull(searchParam.getSearchColumn()));
		}
		return criteria;
	}

	private Criteria generateAlias(List<SearchParameter> searchParameters,
			List<String> totalAliasList, Criteria c) {
		if (searchParameters != null) {
			for (SearchParameter searchParam : searchParameters) {
				Map<String, String> aliasMap = searchParam.getSearchAliasMap();
				if (aliasMap != null) {
					for (Map.Entry<String, String> alias : aliasMap.entrySet()) {
						if (alias != null
								&& !totalAliasList.contains(alias.getKey())) {
							c.createAlias(alias.getValue(), alias.getKey());
							totalAliasList.add(alias.getKey());
						}
					}
				}
				if (searchParam.getSearchType() != null
						&& searchParam.getSearchValue() != null) {
					buildCriteria(searchParam, c);
				}
				else if (searchParam.getSearchType().equals(ApplicationConstantsUtil.MC_IS_NULL)) {
					buildCriteria(searchParam, c);
				}
			}
		}
		return c;
	}

	
	  public void removeAprioriRecords() 
		{

		  final Session session = this.getSessionFactory().openSession();
			 Transaction sessionTransaction = session.getTransaction();
			 if (!sessionTransaction.isActive()) {
		      session.beginTransaction();
		    }
		    try {
		    	System.out.println("Begin transaction");
		         String sql = "Truncate table mining";
		         SQLQuery query = session.createSQLQuery(sql);
		         query.executeUpdate();
		         System.out.println("done");
		      sessionTransaction.commit();
		    } catch (Exception e) {
		    	sessionTransaction.rollback();
		      System.out.println("CommonDAO saveOrUpdate failed");
		      e.printStackTrace();
		    }
		    finally{
		    	session.close();
		    }
			
		} 


	
	
	/**
	 * This method performs batch update
	 * @param boList
	 */
	public void batchSaveOrUpdate(List<T> boList) {
		for(T t :boList){
			this.getSession().saveOrUpdate(t);
		}
		
	}
}