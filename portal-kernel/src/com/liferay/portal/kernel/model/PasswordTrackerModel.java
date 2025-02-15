/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.bean.AutoEscape;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the PasswordTracker service. Represents a row in the &quot;PasswordTracker&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.PasswordTrackerModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.PasswordTrackerImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PasswordTracker
 * @generated
 */
@ProviderType
public interface PasswordTrackerModel
	extends BaseModel<PasswordTracker>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a password tracker model instance should use the {@link PasswordTracker} interface instead.
	 */

	/**
	 * Returns the primary key of this password tracker.
	 *
	 * @return the primary key of this password tracker
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this password tracker.
	 *
	 * @param primaryKey the primary key of this password tracker
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this password tracker.
	 *
	 * @return the mvcc version of this password tracker
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this password tracker.
	 *
	 * @param mvccVersion the mvcc version of this password tracker
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the password tracker ID of this password tracker.
	 *
	 * @return the password tracker ID of this password tracker
	 */
	public long getPasswordTrackerId();

	/**
	 * Sets the password tracker ID of this password tracker.
	 *
	 * @param passwordTrackerId the password tracker ID of this password tracker
	 */
	public void setPasswordTrackerId(long passwordTrackerId);

	/**
	 * Returns the company ID of this password tracker.
	 *
	 * @return the company ID of this password tracker
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this password tracker.
	 *
	 * @param companyId the company ID of this password tracker
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this password tracker.
	 *
	 * @return the user ID of this password tracker
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this password tracker.
	 *
	 * @param userId the user ID of this password tracker
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this password tracker.
	 *
	 * @return the user uuid of this password tracker
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this password tracker.
	 *
	 * @param userUuid the user uuid of this password tracker
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this password tracker.
	 *
	 * @return the create date of this password tracker
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this password tracker.
	 *
	 * @param createDate the create date of this password tracker
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the password of this password tracker.
	 *
	 * @return the password of this password tracker
	 */
	@AutoEscape
	public String getPassword();

	/**
	 * Sets the password of this password tracker.
	 *
	 * @param password the password of this password tracker
	 */
	public void setPassword(String password);

	@Override
	public PasswordTracker cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}