/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.microblogs.service.persistence;

import com.liferay.microblogs.NoSuchEntryException;
import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.model.impl.MicroblogsEntryImpl;
import com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the microblogs entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MicroblogsEntryPersistence
 * @see MicroblogsEntryUtil
 * @generated
 */
public class MicroblogsEntryPersistenceImpl extends BasePersistenceImpl<MicroblogsEntry>
	implements MicroblogsEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MicroblogsEntryUtil} to access the microblogs entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MicroblogsEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_T_RU = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByT_RU",
			new String[] {
				Integer.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_T_RU = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByT_RU",
			new String[] { Integer.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_T_RE = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByT_RE",
			new String[] {
				Integer.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_T_RE = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByT_RE",
			new String[] { Integer.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_U_T = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByU_T",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_U_T = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByU_T",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the microblogs entry in the entity cache if it is enabled.
	 *
	 * @param microblogsEntry the microblogs entry to cache
	 */
	public void cacheResult(MicroblogsEntry microblogsEntry) {
		EntityCacheUtil.putResult(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryImpl.class, microblogsEntry.getPrimaryKey(),
			microblogsEntry);
	}

	/**
	 * Caches the microblogs entries in the entity cache if it is enabled.
	 *
	 * @param microblogsEntries the microblogs entries to cache
	 */
	public void cacheResult(List<MicroblogsEntry> microblogsEntries) {
		for (MicroblogsEntry microblogsEntry : microblogsEntries) {
			if (EntityCacheUtil.getResult(
						MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
						MicroblogsEntryImpl.class,
						microblogsEntry.getPrimaryKey(), this) == null) {
				cacheResult(microblogsEntry);
			}
		}
	}

	/**
	 * Clears the cache for all microblogs entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(MicroblogsEntryImpl.class.getName());
		EntityCacheUtil.clearCache(MicroblogsEntryImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the microblogs entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(MicroblogsEntry microblogsEntry) {
		EntityCacheUtil.removeResult(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryImpl.class, microblogsEntry.getPrimaryKey());
	}

	/**
	 * Creates a new microblogs entry with the primary key. Does not add the microblogs entry to the database.
	 *
	 * @param microblogsEntryId the primary key for the new microblogs entry
	 * @return the new microblogs entry
	 */
	public MicroblogsEntry create(long microblogsEntryId) {
		MicroblogsEntry microblogsEntry = new MicroblogsEntryImpl();

		microblogsEntry.setNew(true);
		microblogsEntry.setPrimaryKey(microblogsEntryId);

		return microblogsEntry;
	}

	/**
	 * Removes the microblogs entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the microblogs entry to remove
	 * @return the microblogs entry that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the microblogs entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param microblogsEntryId the primary key of the microblogs entry to remove
	 * @return the microblogs entry that was removed
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry remove(long microblogsEntryId)
		throws NoSuchEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry microblogsEntry = (MicroblogsEntry)session.get(MicroblogsEntryImpl.class,
					new Long(microblogsEntryId));

			if (microblogsEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						microblogsEntryId);
				}

				throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					microblogsEntryId);
			}

			return microblogsEntryPersistence.remove(microblogsEntry);
		}
		catch (NoSuchEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Removes the microblogs entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param microblogsEntry the microblogs entry to remove
	 * @return the microblogs entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry remove(MicroblogsEntry microblogsEntry)
		throws SystemException {
		return super.remove(microblogsEntry);
	}

	protected MicroblogsEntry removeImpl(MicroblogsEntry microblogsEntry)
		throws SystemException {
		microblogsEntry = toUnwrappedModel(microblogsEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, microblogsEntry);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryImpl.class, microblogsEntry.getPrimaryKey());

		return microblogsEntry;
	}

	public MicroblogsEntry updateImpl(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry,
		boolean merge) throws SystemException {
		microblogsEntry = toUnwrappedModel(microblogsEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, microblogsEntry, merge);

			microblogsEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryImpl.class, microblogsEntry.getPrimaryKey(),
			microblogsEntry);

		return microblogsEntry;
	}

	protected MicroblogsEntry toUnwrappedModel(MicroblogsEntry microblogsEntry) {
		if (microblogsEntry instanceof MicroblogsEntryImpl) {
			return microblogsEntry;
		}

		MicroblogsEntryImpl microblogsEntryImpl = new MicroblogsEntryImpl();

		microblogsEntryImpl.setNew(microblogsEntry.isNew());
		microblogsEntryImpl.setPrimaryKey(microblogsEntry.getPrimaryKey());

		microblogsEntryImpl.setMicroblogsEntryId(microblogsEntry.getMicroblogsEntryId());
		microblogsEntryImpl.setCompanyId(microblogsEntry.getCompanyId());
		microblogsEntryImpl.setUserId(microblogsEntry.getUserId());
		microblogsEntryImpl.setUserName(microblogsEntry.getUserName());
		microblogsEntryImpl.setCreateDate(microblogsEntry.getCreateDate());
		microblogsEntryImpl.setModifiedDate(microblogsEntry.getModifiedDate());
		microblogsEntryImpl.setContent(microblogsEntry.getContent());
		microblogsEntryImpl.setType(microblogsEntry.getType());
		microblogsEntryImpl.setReceiverUserId(microblogsEntry.getReceiverUserId());
		microblogsEntryImpl.setReceiverEntryId(microblogsEntry.getReceiverEntryId());
		microblogsEntryImpl.setSocialRelationType(microblogsEntry.getSocialRelationType());

		return microblogsEntryImpl;
	}

	/**
	 * Finds the microblogs entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the microblogs entry to find
	 * @return the microblogs entry
	 * @throws com.liferay.portal.NoSuchModelException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the microblogs entry with the primary key or throws a {@link com.liferay.microblogs.NoSuchEntryException} if it could not be found.
	 *
	 * @param microblogsEntryId the primary key of the microblogs entry to find
	 * @return the microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByPrimaryKey(long microblogsEntryId)
		throws NoSuchEntryException, SystemException {
		MicroblogsEntry microblogsEntry = fetchByPrimaryKey(microblogsEntryId);

		if (microblogsEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + microblogsEntryId);
			}

			throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				microblogsEntryId);
		}

		return microblogsEntry;
	}

	/**
	 * Finds the microblogs entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the microblogs entry to find
	 * @return the microblogs entry, or <code>null</code> if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the microblogs entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param microblogsEntryId the primary key of the microblogs entry to find
	 * @return the microblogs entry, or <code>null</code> if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry fetchByPrimaryKey(long microblogsEntryId)
		throws SystemException {
		MicroblogsEntry microblogsEntry = (MicroblogsEntry)EntityCacheUtil.getResult(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
				MicroblogsEntryImpl.class, microblogsEntryId, this);

		if (microblogsEntry == null) {
			Session session = null;

			try {
				session = openSession();

				microblogsEntry = (MicroblogsEntry)session.get(MicroblogsEntryImpl.class,
						new Long(microblogsEntryId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (microblogsEntry != null) {
					cacheResult(microblogsEntry);
				}

				closeSession(session);
			}
		}

		return microblogsEntry;
	}

	/**
	 * Finds all the microblogs entries where userId = &#63;.
	 *
	 * @param userId the user ID to search with
	 * @return the matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the microblogs entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @return the range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the microblogs entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				userId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<MicroblogsEntry> list = (List<MicroblogsEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_USERID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first microblogs entry in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		List<MicroblogsEntry> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last microblogs entry in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		int count = countByUserId(userId);

		List<MicroblogsEntry> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the microblogs entries before and after the current microblogs entry in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param userId the user ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] findByUserId_PrevAndNext(long microblogsEntryId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = getByUserId_PrevAndNext(session, microblogsEntry,
					userId, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = getByUserId_PrevAndNext(session, microblogsEntry,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry getByUserId_PrevAndNext(Session session,
		MicroblogsEntry microblogsEntry, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Filters by the user's permissions and finds all the microblogs entries where userId = &#63;.
	 *
	 * @param userId the user ID to search with
	 * @return the matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByUserId(long userId)
		throws SystemException {
		return filterFindByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the microblogs entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @return the range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByUserId(long userId, int start,
		int end) throws SystemException {
		return filterFindByUserId(userId, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the microblogs entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByUserId(long userId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUserId(userId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			return (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Filters the microblogs entries before and after the current microblogs entry in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param userId the user ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] filterFindByUserId_PrevAndNext(
		long microblogsEntryId, long userId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUserId_PrevAndNext(microblogsEntryId, userId,
				orderByComparator);
		}

		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = filterGetByUserId_PrevAndNext(session, microblogsEntry,
					userId, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = filterGetByUserId_PrevAndNext(session, microblogsEntry,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry filterGetByUserId_PrevAndNext(Session session,
		MicroblogsEntry microblogsEntry, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the microblogs entries where companyId = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @return the matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Finds a range of all the microblogs entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @return the range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the microblogs entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<MicroblogsEntry> list = (List<MicroblogsEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_COMPANYID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first microblogs entry in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		List<MicroblogsEntry> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last microblogs entry in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		int count = countByCompanyId(companyId);

		List<MicroblogsEntry> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the microblogs entries before and after the current microblogs entry in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param companyId the company ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] findByCompanyId_PrevAndNext(
		long microblogsEntryId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, microblogsEntry,
					companyId, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = getByCompanyId_PrevAndNext(session, microblogsEntry,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry getByCompanyId_PrevAndNext(Session session,
		MicroblogsEntry microblogsEntry, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Filters by the user's permissions and finds all the microblogs entries where companyId = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @return the matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByCompanyId(long companyId)
		throws SystemException {
		return filterFindByCompanyId(companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the microblogs entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @return the range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByCompanyId(long companyId,
		int start, int end) throws SystemException {
		return filterFindByCompanyId(companyId, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the microblogs entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByCompanyId(long companyId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCompanyId(companyId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Filters the microblogs entries before and after the current microblogs entry in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param companyId the company ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] filterFindByCompanyId_PrevAndNext(
		long microblogsEntryId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCompanyId_PrevAndNext(microblogsEntryId, companyId,
				orderByComparator);
		}

		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = filterGetByCompanyId_PrevAndNext(session,
					microblogsEntry, companyId, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = filterGetByCompanyId_PrevAndNext(session,
					microblogsEntry, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry filterGetByCompanyId_PrevAndNext(
		Session session, MicroblogsEntry microblogsEntry, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	 *
	 * @param type the type to search with
	 * @param receiverUserId the receiver user ID to search with
	 * @return the matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByT_RU(int type, long receiverUserId)
		throws SystemException {
		return findByT_RU(type, receiverUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type to search with
	 * @param receiverUserId the receiver user ID to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @return the range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByT_RU(int type, long receiverUserId,
		int start, int end) throws SystemException {
		return findByT_RU(type, receiverUserId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type to search with
	 * @param receiverUserId the receiver user ID to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByT_RU(int type, long receiverUserId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				type, receiverUserId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<MicroblogsEntry> list = (List<MicroblogsEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_T_RU,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_T_RU_TYPE_2);

			query.append(_FINDER_COLUMN_T_RU_RECEIVERUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

				qPos.add(receiverUserId);

				list = (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_T_RU,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_T_RU,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type to search with
	 * @param receiverUserId the receiver user ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByT_RU_First(int type, long receiverUserId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		List<MicroblogsEntry> list = findByT_RU(type, receiverUserId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("type=");
			msg.append(type);

			msg.append(", receiverUserId=");
			msg.append(receiverUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type to search with
	 * @param receiverUserId the receiver user ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByT_RU_Last(int type, long receiverUserId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		int count = countByT_RU(type, receiverUserId);

		List<MicroblogsEntry> list = findByT_RU(type, receiverUserId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("type=");
			msg.append(type);

			msg.append(", receiverUserId=");
			msg.append(receiverUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the microblogs entries before and after the current microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param type the type to search with
	 * @param receiverUserId the receiver user ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] findByT_RU_PrevAndNext(long microblogsEntryId,
		int type, long receiverUserId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = getByT_RU_PrevAndNext(session, microblogsEntry, type,
					receiverUserId, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = getByT_RU_PrevAndNext(session, microblogsEntry, type,
					receiverUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry getByT_RU_PrevAndNext(Session session,
		MicroblogsEntry microblogsEntry, int type, long receiverUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_T_RU_TYPE_2);

		query.append(_FINDER_COLUMN_T_RU_RECEIVERUSERID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(type);

		qPos.add(receiverUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Filters by the user's permissions and finds all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	 *
	 * @param type the type to search with
	 * @param receiverUserId the receiver user ID to search with
	 * @return the matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByT_RU(int type, long receiverUserId)
		throws SystemException {
		return filterFindByT_RU(type, receiverUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type to search with
	 * @param receiverUserId the receiver user ID to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @return the range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByT_RU(int type,
		long receiverUserId, int start, int end) throws SystemException {
		return filterFindByT_RU(type, receiverUserId, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type to search with
	 * @param receiverUserId the receiver user ID to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByT_RU(int type,
		long receiverUserId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByT_RU(type, receiverUserId, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_T_RU_TYPE_2);

		query.append(_FINDER_COLUMN_T_RU_RECEIVERUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(type);

			qPos.add(receiverUserId);

			return (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Filters the microblogs entries before and after the current microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param type the type to search with
	 * @param receiverUserId the receiver user ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] filterFindByT_RU_PrevAndNext(
		long microblogsEntryId, int type, long receiverUserId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByT_RU_PrevAndNext(microblogsEntryId, type,
				receiverUserId, orderByComparator);
		}

		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = filterGetByT_RU_PrevAndNext(session, microblogsEntry,
					type, receiverUserId, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = filterGetByT_RU_PrevAndNext(session, microblogsEntry,
					type, receiverUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry filterGetByT_RU_PrevAndNext(Session session,
		MicroblogsEntry microblogsEntry, int type, long receiverUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_T_RU_TYPE_2);

		query.append(_FINDER_COLUMN_T_RU_RECEIVERUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(type);

		qPos.add(receiverUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the microblogs entries where type = &#63; and receiverEntryId = &#63;.
	 *
	 * @param type the type to search with
	 * @param receiverEntryId the receiver entry ID to search with
	 * @return the matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByT_RE(int type, long receiverEntryId)
		throws SystemException {
		return findByT_RE(type, receiverEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the microblogs entries where type = &#63; and receiverEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type to search with
	 * @param receiverEntryId the receiver entry ID to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @return the range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByT_RE(int type, long receiverEntryId,
		int start, int end) throws SystemException {
		return findByT_RE(type, receiverEntryId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the microblogs entries where type = &#63; and receiverEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type to search with
	 * @param receiverEntryId the receiver entry ID to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByT_RE(int type, long receiverEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				type, receiverEntryId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<MicroblogsEntry> list = (List<MicroblogsEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_T_RE,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_T_RE_TYPE_2);

			query.append(_FINDER_COLUMN_T_RE_RECEIVERENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

				qPos.add(receiverEntryId);

				list = (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_T_RE,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_T_RE,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first microblogs entry in the ordered set where type = &#63; and receiverEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type to search with
	 * @param receiverEntryId the receiver entry ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByT_RE_First(int type, long receiverEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		List<MicroblogsEntry> list = findByT_RE(type, receiverEntryId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("type=");
			msg.append(type);

			msg.append(", receiverEntryId=");
			msg.append(receiverEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last microblogs entry in the ordered set where type = &#63; and receiverEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type to search with
	 * @param receiverEntryId the receiver entry ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByT_RE_Last(int type, long receiverEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		int count = countByT_RE(type, receiverEntryId);

		List<MicroblogsEntry> list = findByT_RE(type, receiverEntryId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("type=");
			msg.append(type);

			msg.append(", receiverEntryId=");
			msg.append(receiverEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the microblogs entries before and after the current microblogs entry in the ordered set where type = &#63; and receiverEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param type the type to search with
	 * @param receiverEntryId the receiver entry ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] findByT_RE_PrevAndNext(long microblogsEntryId,
		int type, long receiverEntryId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = getByT_RE_PrevAndNext(session, microblogsEntry, type,
					receiverEntryId, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = getByT_RE_PrevAndNext(session, microblogsEntry, type,
					receiverEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry getByT_RE_PrevAndNext(Session session,
		MicroblogsEntry microblogsEntry, int type, long receiverEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_T_RE_TYPE_2);

		query.append(_FINDER_COLUMN_T_RE_RECEIVERENTRYID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(type);

		qPos.add(receiverEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Filters by the user's permissions and finds all the microblogs entries where type = &#63; and receiverEntryId = &#63;.
	 *
	 * @param type the type to search with
	 * @param receiverEntryId the receiver entry ID to search with
	 * @return the matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByT_RE(int type, long receiverEntryId)
		throws SystemException {
		return filterFindByT_RE(type, receiverEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the microblogs entries where type = &#63; and receiverEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type to search with
	 * @param receiverEntryId the receiver entry ID to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @return the range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByT_RE(int type,
		long receiverEntryId, int start, int end) throws SystemException {
		return filterFindByT_RE(type, receiverEntryId, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the microblogs entries where type = &#63; and receiverEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type to search with
	 * @param receiverEntryId the receiver entry ID to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByT_RE(int type,
		long receiverEntryId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByT_RE(type, receiverEntryId, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_T_RE_TYPE_2);

		query.append(_FINDER_COLUMN_T_RE_RECEIVERENTRYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(type);

			qPos.add(receiverEntryId);

			return (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Filters the microblogs entries before and after the current microblogs entry in the ordered set where type = &#63; and receiverEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param type the type to search with
	 * @param receiverEntryId the receiver entry ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] filterFindByT_RE_PrevAndNext(
		long microblogsEntryId, int type, long receiverEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByT_RE_PrevAndNext(microblogsEntryId, type,
				receiverEntryId, orderByComparator);
		}

		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = filterGetByT_RE_PrevAndNext(session, microblogsEntry,
					type, receiverEntryId, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = filterGetByT_RE_PrevAndNext(session, microblogsEntry,
					type, receiverEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry filterGetByT_RE_PrevAndNext(Session session,
		MicroblogsEntry microblogsEntry, int type, long receiverEntryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_T_RE_TYPE_2);

		query.append(_FINDER_COLUMN_T_RE_RECEIVERENTRYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(type);

		qPos.add(receiverEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the microblogs entries where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID to search with
	 * @param type the type to search with
	 * @return the matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByU_T(long userId, int type)
		throws SystemException {
		return findByU_T(userId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Finds a range of all the microblogs entries where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID to search with
	 * @param type the type to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @return the range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByU_T(long userId, int type, int start,
		int end) throws SystemException {
		return findByU_T(userId, type, start, end, null);
	}

	/**
	 * Finds an ordered range of all the microblogs entries where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID to search with
	 * @param type the type to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByU_T(long userId, int type, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				userId, type,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<MicroblogsEntry> list = (List<MicroblogsEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_U_T,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_T_USERID_2);

			query.append(_FINDER_COLUMN_U_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(type);

				list = (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_U_T,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_U_T,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID to search with
	 * @param type the type to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByU_T_First(long userId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		List<MicroblogsEntry> list = findByU_T(userId, type, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID to search with
	 * @param type the type to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByU_T_Last(long userId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		int count = countByU_T(userId, type);

		List<MicroblogsEntry> list = findByU_T(userId, type, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the microblogs entries before and after the current microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param userId the user ID to search with
	 * @param type the type to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] findByU_T_PrevAndNext(long microblogsEntryId,
		long userId, int type, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = getByU_T_PrevAndNext(session, microblogsEntry, userId,
					type, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = getByU_T_PrevAndNext(session, microblogsEntry, userId,
					type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry getByU_T_PrevAndNext(Session session,
		MicroblogsEntry microblogsEntry, long userId, int type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_U_T_USERID_2);

		query.append(_FINDER_COLUMN_U_T_TYPE_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Filters by the user's permissions and finds all the microblogs entries where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID to search with
	 * @param type the type to search with
	 * @return the matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByU_T(long userId, int type)
		throws SystemException {
		return filterFindByU_T(userId, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the microblogs entries where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID to search with
	 * @param type the type to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @return the range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByU_T(long userId, int type,
		int start, int end) throws SystemException {
		return filterFindByU_T(userId, type, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the microblogs entries where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID to search with
	 * @param type the type to search with
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByU_T(long userId, int type,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByU_T(userId, type, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_U_T_USERID_2);

		query.append(_FINDER_COLUMN_U_T_TYPE_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			qPos.add(type);

			return (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Filters the microblogs entries before and after the current microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param userId the user ID to search with
	 * @param type the type to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] filterFindByU_T_PrevAndNext(
		long microblogsEntryId, long userId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByU_T_PrevAndNext(microblogsEntryId, userId, type,
				orderByComparator);
		}

		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = filterGetByU_T_PrevAndNext(session, microblogsEntry,
					userId, type, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = filterGetByU_T_PrevAndNext(session, microblogsEntry,
					userId, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry filterGetByU_T_PrevAndNext(Session session,
		MicroblogsEntry microblogsEntry, long userId, int type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_U_T_USERID_2);

		query.append(_FINDER_COLUMN_U_T_TYPE_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the microblogs entries.
	 *
	 * @return the microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the microblogs entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @return the range of microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the microblogs entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of microblogs entries to return
	 * @param end the upper bound of the range of microblogs entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<MicroblogsEntry> list = (List<MicroblogsEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_MICROBLOGSENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MICROBLOGSENTRY.concat(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<MicroblogsEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<MicroblogsEntry>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs,
						list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the microblogs entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (MicroblogsEntry microblogsEntry : findByUserId(userId)) {
			microblogsEntryPersistence.remove(microblogsEntry);
		}
	}

	/**
	 * Removes all the microblogs entries where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (MicroblogsEntry microblogsEntry : findByCompanyId(companyId)) {
			microblogsEntryPersistence.remove(microblogsEntry);
		}
	}

	/**
	 * Removes all the microblogs entries where type = &#63; and receiverUserId = &#63; from the database.
	 *
	 * @param type the type to search with
	 * @param receiverUserId the receiver user ID to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByT_RU(int type, long receiverUserId)
		throws SystemException {
		for (MicroblogsEntry microblogsEntry : findByT_RU(type, receiverUserId)) {
			microblogsEntryPersistence.remove(microblogsEntry);
		}
	}

	/**
	 * Removes all the microblogs entries where type = &#63; and receiverEntryId = &#63; from the database.
	 *
	 * @param type the type to search with
	 * @param receiverEntryId the receiver entry ID to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByT_RE(int type, long receiverEntryId)
		throws SystemException {
		for (MicroblogsEntry microblogsEntry : findByT_RE(type, receiverEntryId)) {
			microblogsEntryPersistence.remove(microblogsEntry);
		}
	}

	/**
	 * Removes all the microblogs entries where userId = &#63; and type = &#63; from the database.
	 *
	 * @param userId the user ID to search with
	 * @param type the type to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_T(long userId, int type) throws SystemException {
		for (MicroblogsEntry microblogsEntry : findByU_T(userId, type)) {
			microblogsEntryPersistence.remove(microblogsEntry);
		}
	}

	/**
	 * Removes all the microblogs entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (MicroblogsEntry microblogsEntry : findAll()) {
			microblogsEntryPersistence.remove(microblogsEntry);
		}
	}

	/**
	 * Counts all the microblogs entries where userId = &#63;.
	 *
	 * @param userId the user ID to search with
	 * @return the number of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MICROBLOGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Filters by the user's permissions and counts all the microblogs entries where userId = &#63;.
	 *
	 * @param userId the user ID to search with
	 * @return the number of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByUserId(long userId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUserId(userId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Counts all the microblogs entries where companyId = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @return the number of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MICROBLOGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Filters by the user's permissions and counts all the microblogs entries where companyId = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @return the number of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByCompanyId(long companyId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByCompanyId(companyId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Counts all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	 *
	 * @param type the type to search with
	 * @param receiverUserId the receiver user ID to search with
	 * @return the number of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByT_RU(int type, long receiverUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { type, receiverUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_T_RU,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MICROBLOGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_T_RU_TYPE_2);

			query.append(_FINDER_COLUMN_T_RU_RECEIVERUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

				qPos.add(receiverUserId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_T_RU,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Filters by the user's permissions and counts all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	 *
	 * @param type the type to search with
	 * @param receiverUserId the receiver user ID to search with
	 * @return the number of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByT_RU(int type, long receiverUserId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByT_RU(type, receiverUserId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_T_RU_TYPE_2);

		query.append(_FINDER_COLUMN_T_RU_RECEIVERUSERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(type);

			qPos.add(receiverUserId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Counts all the microblogs entries where type = &#63; and receiverEntryId = &#63;.
	 *
	 * @param type the type to search with
	 * @param receiverEntryId the receiver entry ID to search with
	 * @return the number of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByT_RE(int type, long receiverEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { type, receiverEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_T_RE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MICROBLOGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_T_RE_TYPE_2);

			query.append(_FINDER_COLUMN_T_RE_RECEIVERENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

				qPos.add(receiverEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_T_RE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Filters by the user's permissions and counts all the microblogs entries where type = &#63; and receiverEntryId = &#63;.
	 *
	 * @param type the type to search with
	 * @param receiverEntryId the receiver entry ID to search with
	 * @return the number of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByT_RE(int type, long receiverEntryId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByT_RE(type, receiverEntryId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_T_RE_TYPE_2);

		query.append(_FINDER_COLUMN_T_RE_RECEIVERENTRYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(type);

			qPos.add(receiverEntryId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Counts all the microblogs entries where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID to search with
	 * @param type the type to search with
	 * @return the number of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_T(long userId, int type) throws SystemException {
		Object[] finderArgs = new Object[] { userId, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MICROBLOGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_T_USERID_2);

			query.append(_FINDER_COLUMN_U_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(type);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_T, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Filters by the user's permissions and counts all the microblogs entries where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID to search with
	 * @param type the type to search with
	 * @return the number of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByU_T(long userId, int type)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByU_T(userId, type);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_U_T_USERID_2);

		query.append(_FINDER_COLUMN_U_T_TYPE_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			qPos.add(type);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Counts all the microblogs entries.
	 *
	 * @return the number of microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MICROBLOGSENTRY);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the microblogs entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.microblogs.model.MicroblogsEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<MicroblogsEntry>> listenersList = new ArrayList<ModelListener<MicroblogsEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<MicroblogsEntry>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(MicroblogsEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = MicroblogsEntryPersistence.class)
	protected MicroblogsEntryPersistence microblogsEntryPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_MICROBLOGSENTRY = "SELECT microblogsEntry FROM MicroblogsEntry microblogsEntry";
	private static final String _SQL_SELECT_MICROBLOGSENTRY_WHERE = "SELECT microblogsEntry FROM MicroblogsEntry microblogsEntry WHERE ";
	private static final String _SQL_COUNT_MICROBLOGSENTRY = "SELECT COUNT(microblogsEntry) FROM MicroblogsEntry microblogsEntry";
	private static final String _SQL_COUNT_MICROBLOGSENTRY_WHERE = "SELECT COUNT(microblogsEntry) FROM MicroblogsEntry microblogsEntry WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "microblogsEntry.userId = ?";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "microblogsEntry.companyId = ?";
	private static final String _FINDER_COLUMN_T_RU_TYPE_2 = "microblogsEntry.type = ? AND ";
	private static final String _FINDER_COLUMN_T_RU_RECEIVERUSERID_2 = "microblogsEntry.receiverUserId = ?";
	private static final String _FINDER_COLUMN_T_RE_TYPE_2 = "microblogsEntry.type = ? AND ";
	private static final String _FINDER_COLUMN_T_RE_RECEIVERENTRYID_2 = "microblogsEntry.receiverEntryId = ?";
	private static final String _FINDER_COLUMN_U_T_USERID_2 = "microblogsEntry.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_T_TYPE_2 = "microblogsEntry.type = ?";
	private static final String _FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE = "SELECT DISTINCT {microblogsEntry.*} FROM Microblogs_MicroblogsEntry microblogsEntry WHERE ";
	private static final String _FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {Microblogs_MicroblogsEntry.*} FROM (SELECT DISTINCT microblogsEntry.microblogsEntryId FROM Microblogs_MicroblogsEntry microblogsEntry WHERE ";
	private static final String _FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN Microblogs_MicroblogsEntry ON TEMP_TABLE.microblogsEntryId = Microblogs_MicroblogsEntry.microblogsEntryId";
	private static final String _FILTER_SQL_COUNT_MICROBLOGSENTRY_WHERE = "SELECT COUNT(DISTINCT microblogsEntry.microblogsEntryId) AS COUNT_VALUE FROM Microblogs_MicroblogsEntry microblogsEntry WHERE ";
	private static final String _FILTER_COLUMN_PK = "microblogsEntry.microblogsEntryId";
	private static final String _FILTER_COLUMN_USERID = "microblogsEntry.userId";
	private static final String _FILTER_ENTITY_ALIAS = "microblogsEntry";
	private static final String _FILTER_ENTITY_TABLE = "Microblogs_MicroblogsEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "microblogsEntry.";
	private static final String _ORDER_BY_ENTITY_TABLE = "Microblogs_MicroblogsEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MicroblogsEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MicroblogsEntry exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(MicroblogsEntryPersistenceImpl.class);
}