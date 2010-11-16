/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.journal.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the JournalFeed service. Represents a row in the &quot;JournalFeed&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.journal.model.impl.JournalFeedModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.journal.model.impl.JournalFeedImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JournalFeed
 * @see com.liferay.portlet.journal.model.impl.JournalFeedImpl
 * @see com.liferay.portlet.journal.model.impl.JournalFeedModelImpl
 * @generated
 */
public interface JournalFeedModel extends BaseModel<JournalFeed> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a journal feed model instance should use the {@link JournalFeed} interface instead.
	 */

	/**
	 * Gets the primary key of this journal feed.
	 *
	 * @return the primary key of this journal feed
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this journal feed
	 *
	 * @param pk the primary key of this journal feed
	 */
	public void setPrimaryKey(long pk);

	/**
	 * Gets the uuid of this journal feed.
	 *
	 * @return the uuid of this journal feed
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this journal feed.
	 *
	 * @param uuid the uuid of this journal feed
	 */
	public void setUuid(String uuid);

	/**
	 * Gets the id of this journal feed.
	 *
	 * @return the id of this journal feed
	 */
	public long getId();

	/**
	 * Sets the id of this journal feed.
	 *
	 * @param id the id of this journal feed
	 */
	public void setId(long id);

	/**
	 * Gets the group id of this journal feed.
	 *
	 * @return the group id of this journal feed
	 */
	public long getGroupId();

	/**
	 * Sets the group id of this journal feed.
	 *
	 * @param groupId the group id of this journal feed
	 */
	public void setGroupId(long groupId);

	/**
	 * Gets the company id of this journal feed.
	 *
	 * @return the company id of this journal feed
	 */
	public long getCompanyId();

	/**
	 * Sets the company id of this journal feed.
	 *
	 * @param companyId the company id of this journal feed
	 */
	public void setCompanyId(long companyId);

	/**
	 * Gets the user id of this journal feed.
	 *
	 * @return the user id of this journal feed
	 */
	public long getUserId();

	/**
	 * Sets the user id of this journal feed.
	 *
	 * @param userId the user id of this journal feed
	 */
	public void setUserId(long userId);

	/**
	 * Gets the user uuid of this journal feed.
	 *
	 * @return the user uuid of this journal feed
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this journal feed.
	 *
	 * @param userUuid the user uuid of this journal feed
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Gets the user name of this journal feed.
	 *
	 * @return the user name of this journal feed
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this journal feed.
	 *
	 * @param userName the user name of this journal feed
	 */
	public void setUserName(String userName);

	/**
	 * Gets the create date of this journal feed.
	 *
	 * @return the create date of this journal feed
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this journal feed.
	 *
	 * @param createDate the create date of this journal feed
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Gets the modified date of this journal feed.
	 *
	 * @return the modified date of this journal feed
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this journal feed.
	 *
	 * @param modifiedDate the modified date of this journal feed
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Gets the feed id of this journal feed.
	 *
	 * @return the feed id of this journal feed
	 */
	public String getFeedId();

	/**
	 * Sets the feed id of this journal feed.
	 *
	 * @param feedId the feed id of this journal feed
	 */
	public void setFeedId(String feedId);

	/**
	 * Gets the name of this journal feed.
	 *
	 * @return the name of this journal feed
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this journal feed.
	 *
	 * @param name the name of this journal feed
	 */
	public void setName(String name);

	/**
	 * Gets the description of this journal feed.
	 *
	 * @return the description of this journal feed
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this journal feed.
	 *
	 * @param description the description of this journal feed
	 */
	public void setDescription(String description);

	/**
	 * Gets the type of this journal feed.
	 *
	 * @return the type of this journal feed
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this journal feed.
	 *
	 * @param type the type of this journal feed
	 */
	public void setType(String type);

	/**
	 * Gets the structure id of this journal feed.
	 *
	 * @return the structure id of this journal feed
	 */
	public String getStructureId();

	/**
	 * Sets the structure id of this journal feed.
	 *
	 * @param structureId the structure id of this journal feed
	 */
	public void setStructureId(String structureId);

	/**
	 * Gets the template id of this journal feed.
	 *
	 * @return the template id of this journal feed
	 */
	public String getTemplateId();

	/**
	 * Sets the template id of this journal feed.
	 *
	 * @param templateId the template id of this journal feed
	 */
	public void setTemplateId(String templateId);

	/**
	 * Gets the renderer template id of this journal feed.
	 *
	 * @return the renderer template id of this journal feed
	 */
	@AutoEscape
	public String getRendererTemplateId();

	/**
	 * Sets the renderer template id of this journal feed.
	 *
	 * @param rendererTemplateId the renderer template id of this journal feed
	 */
	public void setRendererTemplateId(String rendererTemplateId);

	/**
	 * Gets the delta of this journal feed.
	 *
	 * @return the delta of this journal feed
	 */
	public int getDelta();

	/**
	 * Sets the delta of this journal feed.
	 *
	 * @param delta the delta of this journal feed
	 */
	public void setDelta(int delta);

	/**
	 * Gets the order by col of this journal feed.
	 *
	 * @return the order by col of this journal feed
	 */
	@AutoEscape
	public String getOrderByCol();

	/**
	 * Sets the order by col of this journal feed.
	 *
	 * @param orderByCol the order by col of this journal feed
	 */
	public void setOrderByCol(String orderByCol);

	/**
	 * Gets the order by type of this journal feed.
	 *
	 * @return the order by type of this journal feed
	 */
	@AutoEscape
	public String getOrderByType();

	/**
	 * Sets the order by type of this journal feed.
	 *
	 * @param orderByType the order by type of this journal feed
	 */
	public void setOrderByType(String orderByType);

	/**
	 * Gets the target layout friendly url of this journal feed.
	 *
	 * @return the target layout friendly url of this journal feed
	 */
	@AutoEscape
	public String getTargetLayoutFriendlyUrl();

	/**
	 * Sets the target layout friendly url of this journal feed.
	 *
	 * @param targetLayoutFriendlyUrl the target layout friendly url of this journal feed
	 */
	public void setTargetLayoutFriendlyUrl(String targetLayoutFriendlyUrl);

	/**
	 * Gets the target portlet id of this journal feed.
	 *
	 * @return the target portlet id of this journal feed
	 */
	@AutoEscape
	public String getTargetPortletId();

	/**
	 * Sets the target portlet id of this journal feed.
	 *
	 * @param targetPortletId the target portlet id of this journal feed
	 */
	public void setTargetPortletId(String targetPortletId);

	/**
	 * Gets the content field of this journal feed.
	 *
	 * @return the content field of this journal feed
	 */
	@AutoEscape
	public String getContentField();

	/**
	 * Sets the content field of this journal feed.
	 *
	 * @param contentField the content field of this journal feed
	 */
	public void setContentField(String contentField);

	/**
	 * Gets the feed type of this journal feed.
	 *
	 * @return the feed type of this journal feed
	 */
	@AutoEscape
	public String getFeedType();

	/**
	 * Sets the feed type of this journal feed.
	 *
	 * @param feedType the feed type of this journal feed
	 */
	public void setFeedType(String feedType);

	/**
	 * Gets the feed version of this journal feed.
	 *
	 * @return the feed version of this journal feed
	 */
	public double getFeedVersion();

	/**
	 * Sets the feed version of this journal feed.
	 *
	 * @param feedVersion the feed version of this journal feed
	 */
	public void setFeedVersion(double feedVersion);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(JournalFeed journalFeed);

	public int hashCode();

	public JournalFeed toEscapedModel();

	public String toString();

	public String toXmlString();
}