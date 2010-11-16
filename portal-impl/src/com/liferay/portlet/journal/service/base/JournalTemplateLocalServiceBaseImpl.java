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

package com.liferay.portlet.journal.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ImageLocalService;
import com.liferay.portal.service.ImageService;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.WebDAVPropsLocalService;
import com.liferay.portal.service.persistence.ImagePersistence;
import com.liferay.portal.service.persistence.ResourceFinder;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.WebDAVPropsPersistence;

import com.liferay.portlet.expando.service.ExpandoValueLocalService;
import com.liferay.portlet.expando.service.ExpandoValueService;
import com.liferay.portlet.expando.service.persistence.ExpandoValuePersistence;
import com.liferay.portlet.journal.model.JournalTemplate;
import com.liferay.portlet.journal.service.JournalArticleImageLocalService;
import com.liferay.portlet.journal.service.JournalArticleLocalService;
import com.liferay.portlet.journal.service.JournalArticleResourceLocalService;
import com.liferay.portlet.journal.service.JournalArticleService;
import com.liferay.portlet.journal.service.JournalContentSearchLocalService;
import com.liferay.portlet.journal.service.JournalFeedLocalService;
import com.liferay.portlet.journal.service.JournalFeedService;
import com.liferay.portlet.journal.service.JournalStructureLocalService;
import com.liferay.portlet.journal.service.JournalStructureService;
import com.liferay.portlet.journal.service.JournalTemplateLocalService;
import com.liferay.portlet.journal.service.JournalTemplateService;
import com.liferay.portlet.journal.service.persistence.JournalArticleFinder;
import com.liferay.portlet.journal.service.persistence.JournalArticleImagePersistence;
import com.liferay.portlet.journal.service.persistence.JournalArticlePersistence;
import com.liferay.portlet.journal.service.persistence.JournalArticleResourcePersistence;
import com.liferay.portlet.journal.service.persistence.JournalContentSearchPersistence;
import com.liferay.portlet.journal.service.persistence.JournalFeedFinder;
import com.liferay.portlet.journal.service.persistence.JournalFeedPersistence;
import com.liferay.portlet.journal.service.persistence.JournalStructureFinder;
import com.liferay.portlet.journal.service.persistence.JournalStructurePersistence;
import com.liferay.portlet.journal.service.persistence.JournalTemplateFinder;
import com.liferay.portlet.journal.service.persistence.JournalTemplatePersistence;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the journal template local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.journal.service.impl.JournalTemplateLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.journal.service.impl.JournalTemplateLocalServiceImpl
 * @see com.liferay.portlet.journal.service.JournalTemplateLocalServiceUtil
 * @generated
 */
public abstract class JournalTemplateLocalServiceBaseImpl
	implements JournalTemplateLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portlet.journal.service.JournalTemplateLocalServiceUtil} to access the journal template local service.
	 */

	/**
	 * Adds the journal template to the database. Also notifies the appropriate model listeners.
	 *
	 * @param journalTemplate the journal template to add
	 * @return the journal template that was added
	 * @throws SystemException if a system exception occurred
	 */
	public JournalTemplate addJournalTemplate(JournalTemplate journalTemplate)
		throws SystemException {
		journalTemplate.setNew(true);

		return journalTemplatePersistence.update(journalTemplate, false);
	}

	/**
	 * Creates a new journal template with the primary key. Does not add the journal template to the database.
	 *
	 * @param id the primary key for the new journal template
	 * @return the new journal template
	 */
	public JournalTemplate createJournalTemplate(long id) {
		return journalTemplatePersistence.create(id);
	}

	/**
	 * Deletes the journal template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the journal template to delete
	 * @throws PortalException if a journal template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteJournalTemplate(long id)
		throws PortalException, SystemException {
		journalTemplatePersistence.remove(id);
	}

	/**
	 * Deletes the journal template from the database. Also notifies the appropriate model listeners.
	 *
	 * @param journalTemplate the journal template to delete
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteJournalTemplate(JournalTemplate journalTemplate)
		throws SystemException {
		journalTemplatePersistence.remove(journalTemplate);
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query to search with
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return journalTemplatePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query to search with
	 * @param start the lower bound of the range of model instances to return
	 * @param end the upper bound of the range of model instances to return (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return journalTemplatePersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query to search with
	 * @param start the lower bound of the range of model instances to return
	 * @param end the upper bound of the range of model instances to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return journalTemplatePersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Counts the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query to search with
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return journalTemplatePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Gets the journal template with the primary key.
	 *
	 * @param id the primary key of the journal template to get
	 * @return the journal template
	 * @throws PortalException if a journal template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JournalTemplate getJournalTemplate(long id)
		throws PortalException, SystemException {
		return journalTemplatePersistence.findByPrimaryKey(id);
	}

	/**
	 * Gets the journal template with the UUID and group id.
	 *
	 * @param uuid the UUID of journal template to get
	 * @param groupId the group id of the journal template to get
	 * @return the journal template
	 * @throws PortalException if a journal template with the UUID and group id could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public JournalTemplate getJournalTemplateByUuidAndGroupId(String uuid,
		long groupId) throws PortalException, SystemException {
		return journalTemplatePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Gets a range of all the journal templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of journal templates to return
	 * @param end the upper bound of the range of journal templates to return (not inclusive)
	 * @return the range of journal templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<JournalTemplate> getJournalTemplates(int start, int end)
		throws SystemException {
		return journalTemplatePersistence.findAll(start, end);
	}

	/**
	 * Gets the number of journal templates.
	 *
	 * @return the number of journal templates
	 * @throws SystemException if a system exception occurred
	 */
	public int getJournalTemplatesCount() throws SystemException {
		return journalTemplatePersistence.countAll();
	}

	/**
	 * Updates the journal template in the database. Also notifies the appropriate model listeners.
	 *
	 * @param journalTemplate the journal template to update
	 * @return the journal template that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public JournalTemplate updateJournalTemplate(
		JournalTemplate journalTemplate) throws SystemException {
		journalTemplate.setNew(false);

		return journalTemplatePersistence.update(journalTemplate, true);
	}

	/**
	 * Updates the journal template in the database. Also notifies the appropriate model listeners.
	 *
	 * @param journalTemplate the journal template to update
	 * @param merge whether to merge the journal template with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the journal template that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public JournalTemplate updateJournalTemplate(
		JournalTemplate journalTemplate, boolean merge)
		throws SystemException {
		journalTemplate.setNew(false);

		return journalTemplatePersistence.update(journalTemplate, merge);
	}

	/**
	 * Gets the journal article local service.
	 *
	 * @return the journal article local service
	 */
	public JournalArticleLocalService getJournalArticleLocalService() {
		return journalArticleLocalService;
	}

	/**
	 * Sets the journal article local service.
	 *
	 * @param journalArticleLocalService the journal article local service
	 */
	public void setJournalArticleLocalService(
		JournalArticleLocalService journalArticleLocalService) {
		this.journalArticleLocalService = journalArticleLocalService;
	}

	/**
	 * Gets the journal article remote service.
	 *
	 * @return the journal article remote service
	 */
	public JournalArticleService getJournalArticleService() {
		return journalArticleService;
	}

	/**
	 * Sets the journal article remote service.
	 *
	 * @param journalArticleService the journal article remote service
	 */
	public void setJournalArticleService(
		JournalArticleService journalArticleService) {
		this.journalArticleService = journalArticleService;
	}

	/**
	 * Gets the journal article persistence.
	 *
	 * @return the journal article persistence
	 */
	public JournalArticlePersistence getJournalArticlePersistence() {
		return journalArticlePersistence;
	}

	/**
	 * Sets the journal article persistence.
	 *
	 * @param journalArticlePersistence the journal article persistence
	 */
	public void setJournalArticlePersistence(
		JournalArticlePersistence journalArticlePersistence) {
		this.journalArticlePersistence = journalArticlePersistence;
	}

	/**
	 * Gets the journal article finder.
	 *
	 * @return the journal article finder
	 */
	public JournalArticleFinder getJournalArticleFinder() {
		return journalArticleFinder;
	}

	/**
	 * Sets the journal article finder.
	 *
	 * @param journalArticleFinder the journal article finder
	 */
	public void setJournalArticleFinder(
		JournalArticleFinder journalArticleFinder) {
		this.journalArticleFinder = journalArticleFinder;
	}

	/**
	 * Gets the journal article image local service.
	 *
	 * @return the journal article image local service
	 */
	public JournalArticleImageLocalService getJournalArticleImageLocalService() {
		return journalArticleImageLocalService;
	}

	/**
	 * Sets the journal article image local service.
	 *
	 * @param journalArticleImageLocalService the journal article image local service
	 */
	public void setJournalArticleImageLocalService(
		JournalArticleImageLocalService journalArticleImageLocalService) {
		this.journalArticleImageLocalService = journalArticleImageLocalService;
	}

	/**
	 * Gets the journal article image persistence.
	 *
	 * @return the journal article image persistence
	 */
	public JournalArticleImagePersistence getJournalArticleImagePersistence() {
		return journalArticleImagePersistence;
	}

	/**
	 * Sets the journal article image persistence.
	 *
	 * @param journalArticleImagePersistence the journal article image persistence
	 */
	public void setJournalArticleImagePersistence(
		JournalArticleImagePersistence journalArticleImagePersistence) {
		this.journalArticleImagePersistence = journalArticleImagePersistence;
	}

	/**
	 * Gets the journal article resource local service.
	 *
	 * @return the journal article resource local service
	 */
	public JournalArticleResourceLocalService getJournalArticleResourceLocalService() {
		return journalArticleResourceLocalService;
	}

	/**
	 * Sets the journal article resource local service.
	 *
	 * @param journalArticleResourceLocalService the journal article resource local service
	 */
	public void setJournalArticleResourceLocalService(
		JournalArticleResourceLocalService journalArticleResourceLocalService) {
		this.journalArticleResourceLocalService = journalArticleResourceLocalService;
	}

	/**
	 * Gets the journal article resource persistence.
	 *
	 * @return the journal article resource persistence
	 */
	public JournalArticleResourcePersistence getJournalArticleResourcePersistence() {
		return journalArticleResourcePersistence;
	}

	/**
	 * Sets the journal article resource persistence.
	 *
	 * @param journalArticleResourcePersistence the journal article resource persistence
	 */
	public void setJournalArticleResourcePersistence(
		JournalArticleResourcePersistence journalArticleResourcePersistence) {
		this.journalArticleResourcePersistence = journalArticleResourcePersistence;
	}

	/**
	 * Gets the journal content search local service.
	 *
	 * @return the journal content search local service
	 */
	public JournalContentSearchLocalService getJournalContentSearchLocalService() {
		return journalContentSearchLocalService;
	}

	/**
	 * Sets the journal content search local service.
	 *
	 * @param journalContentSearchLocalService the journal content search local service
	 */
	public void setJournalContentSearchLocalService(
		JournalContentSearchLocalService journalContentSearchLocalService) {
		this.journalContentSearchLocalService = journalContentSearchLocalService;
	}

	/**
	 * Gets the journal content search persistence.
	 *
	 * @return the journal content search persistence
	 */
	public JournalContentSearchPersistence getJournalContentSearchPersistence() {
		return journalContentSearchPersistence;
	}

	/**
	 * Sets the journal content search persistence.
	 *
	 * @param journalContentSearchPersistence the journal content search persistence
	 */
	public void setJournalContentSearchPersistence(
		JournalContentSearchPersistence journalContentSearchPersistence) {
		this.journalContentSearchPersistence = journalContentSearchPersistence;
	}

	/**
	 * Gets the journal feed local service.
	 *
	 * @return the journal feed local service
	 */
	public JournalFeedLocalService getJournalFeedLocalService() {
		return journalFeedLocalService;
	}

	/**
	 * Sets the journal feed local service.
	 *
	 * @param journalFeedLocalService the journal feed local service
	 */
	public void setJournalFeedLocalService(
		JournalFeedLocalService journalFeedLocalService) {
		this.journalFeedLocalService = journalFeedLocalService;
	}

	/**
	 * Gets the journal feed remote service.
	 *
	 * @return the journal feed remote service
	 */
	public JournalFeedService getJournalFeedService() {
		return journalFeedService;
	}

	/**
	 * Sets the journal feed remote service.
	 *
	 * @param journalFeedService the journal feed remote service
	 */
	public void setJournalFeedService(JournalFeedService journalFeedService) {
		this.journalFeedService = journalFeedService;
	}

	/**
	 * Gets the journal feed persistence.
	 *
	 * @return the journal feed persistence
	 */
	public JournalFeedPersistence getJournalFeedPersistence() {
		return journalFeedPersistence;
	}

	/**
	 * Sets the journal feed persistence.
	 *
	 * @param journalFeedPersistence the journal feed persistence
	 */
	public void setJournalFeedPersistence(
		JournalFeedPersistence journalFeedPersistence) {
		this.journalFeedPersistence = journalFeedPersistence;
	}

	/**
	 * Gets the journal feed finder.
	 *
	 * @return the journal feed finder
	 */
	public JournalFeedFinder getJournalFeedFinder() {
		return journalFeedFinder;
	}

	/**
	 * Sets the journal feed finder.
	 *
	 * @param journalFeedFinder the journal feed finder
	 */
	public void setJournalFeedFinder(JournalFeedFinder journalFeedFinder) {
		this.journalFeedFinder = journalFeedFinder;
	}

	/**
	 * Gets the journal structure local service.
	 *
	 * @return the journal structure local service
	 */
	public JournalStructureLocalService getJournalStructureLocalService() {
		return journalStructureLocalService;
	}

	/**
	 * Sets the journal structure local service.
	 *
	 * @param journalStructureLocalService the journal structure local service
	 */
	public void setJournalStructureLocalService(
		JournalStructureLocalService journalStructureLocalService) {
		this.journalStructureLocalService = journalStructureLocalService;
	}

	/**
	 * Gets the journal structure remote service.
	 *
	 * @return the journal structure remote service
	 */
	public JournalStructureService getJournalStructureService() {
		return journalStructureService;
	}

	/**
	 * Sets the journal structure remote service.
	 *
	 * @param journalStructureService the journal structure remote service
	 */
	public void setJournalStructureService(
		JournalStructureService journalStructureService) {
		this.journalStructureService = journalStructureService;
	}

	/**
	 * Gets the journal structure persistence.
	 *
	 * @return the journal structure persistence
	 */
	public JournalStructurePersistence getJournalStructurePersistence() {
		return journalStructurePersistence;
	}

	/**
	 * Sets the journal structure persistence.
	 *
	 * @param journalStructurePersistence the journal structure persistence
	 */
	public void setJournalStructurePersistence(
		JournalStructurePersistence journalStructurePersistence) {
		this.journalStructurePersistence = journalStructurePersistence;
	}

	/**
	 * Gets the journal structure finder.
	 *
	 * @return the journal structure finder
	 */
	public JournalStructureFinder getJournalStructureFinder() {
		return journalStructureFinder;
	}

	/**
	 * Sets the journal structure finder.
	 *
	 * @param journalStructureFinder the journal structure finder
	 */
	public void setJournalStructureFinder(
		JournalStructureFinder journalStructureFinder) {
		this.journalStructureFinder = journalStructureFinder;
	}

	/**
	 * Gets the journal template local service.
	 *
	 * @return the journal template local service
	 */
	public JournalTemplateLocalService getJournalTemplateLocalService() {
		return journalTemplateLocalService;
	}

	/**
	 * Sets the journal template local service.
	 *
	 * @param journalTemplateLocalService the journal template local service
	 */
	public void setJournalTemplateLocalService(
		JournalTemplateLocalService journalTemplateLocalService) {
		this.journalTemplateLocalService = journalTemplateLocalService;
	}

	/**
	 * Gets the journal template remote service.
	 *
	 * @return the journal template remote service
	 */
	public JournalTemplateService getJournalTemplateService() {
		return journalTemplateService;
	}

	/**
	 * Sets the journal template remote service.
	 *
	 * @param journalTemplateService the journal template remote service
	 */
	public void setJournalTemplateService(
		JournalTemplateService journalTemplateService) {
		this.journalTemplateService = journalTemplateService;
	}

	/**
	 * Gets the journal template persistence.
	 *
	 * @return the journal template persistence
	 */
	public JournalTemplatePersistence getJournalTemplatePersistence() {
		return journalTemplatePersistence;
	}

	/**
	 * Sets the journal template persistence.
	 *
	 * @param journalTemplatePersistence the journal template persistence
	 */
	public void setJournalTemplatePersistence(
		JournalTemplatePersistence journalTemplatePersistence) {
		this.journalTemplatePersistence = journalTemplatePersistence;
	}

	/**
	 * Gets the journal template finder.
	 *
	 * @return the journal template finder
	 */
	public JournalTemplateFinder getJournalTemplateFinder() {
		return journalTemplateFinder;
	}

	/**
	 * Sets the journal template finder.
	 *
	 * @param journalTemplateFinder the journal template finder
	 */
	public void setJournalTemplateFinder(
		JournalTemplateFinder journalTemplateFinder) {
		this.journalTemplateFinder = journalTemplateFinder;
	}

	/**
	 * Gets the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Gets the image local service.
	 *
	 * @return the image local service
	 */
	public ImageLocalService getImageLocalService() {
		return imageLocalService;
	}

	/**
	 * Sets the image local service.
	 *
	 * @param imageLocalService the image local service
	 */
	public void setImageLocalService(ImageLocalService imageLocalService) {
		this.imageLocalService = imageLocalService;
	}

	/**
	 * Gets the image remote service.
	 *
	 * @return the image remote service
	 */
	public ImageService getImageService() {
		return imageService;
	}

	/**
	 * Sets the image remote service.
	 *
	 * @param imageService the image remote service
	 */
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	/**
	 * Gets the image persistence.
	 *
	 * @return the image persistence
	 */
	public ImagePersistence getImagePersistence() {
		return imagePersistence;
	}

	/**
	 * Sets the image persistence.
	 *
	 * @param imagePersistence the image persistence
	 */
	public void setImagePersistence(ImagePersistence imagePersistence) {
		this.imagePersistence = imagePersistence;
	}

	/**
	 * Gets the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Gets the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Gets the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	/**
	 * Gets the resource finder.
	 *
	 * @return the resource finder
	 */
	public ResourceFinder getResourceFinder() {
		return resourceFinder;
	}

	/**
	 * Sets the resource finder.
	 *
	 * @param resourceFinder the resource finder
	 */
	public void setResourceFinder(ResourceFinder resourceFinder) {
		this.resourceFinder = resourceFinder;
	}

	/**
	 * Gets the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Gets the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Gets the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Gets the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	/**
	 * Gets the web d a v props local service.
	 *
	 * @return the web d a v props local service
	 */
	public WebDAVPropsLocalService getWebDAVPropsLocalService() {
		return webDAVPropsLocalService;
	}

	/**
	 * Sets the web d a v props local service.
	 *
	 * @param webDAVPropsLocalService the web d a v props local service
	 */
	public void setWebDAVPropsLocalService(
		WebDAVPropsLocalService webDAVPropsLocalService) {
		this.webDAVPropsLocalService = webDAVPropsLocalService;
	}

	/**
	 * Gets the web d a v props persistence.
	 *
	 * @return the web d a v props persistence
	 */
	public WebDAVPropsPersistence getWebDAVPropsPersistence() {
		return webDAVPropsPersistence;
	}

	/**
	 * Sets the web d a v props persistence.
	 *
	 * @param webDAVPropsPersistence the web d a v props persistence
	 */
	public void setWebDAVPropsPersistence(
		WebDAVPropsPersistence webDAVPropsPersistence) {
		this.webDAVPropsPersistence = webDAVPropsPersistence;
	}

	/**
	 * Gets the expando value local service.
	 *
	 * @return the expando value local service
	 */
	public ExpandoValueLocalService getExpandoValueLocalService() {
		return expandoValueLocalService;
	}

	/**
	 * Sets the expando value local service.
	 *
	 * @param expandoValueLocalService the expando value local service
	 */
	public void setExpandoValueLocalService(
		ExpandoValueLocalService expandoValueLocalService) {
		this.expandoValueLocalService = expandoValueLocalService;
	}

	/**
	 * Gets the expando value remote service.
	 *
	 * @return the expando value remote service
	 */
	public ExpandoValueService getExpandoValueService() {
		return expandoValueService;
	}

	/**
	 * Sets the expando value remote service.
	 *
	 * @param expandoValueService the expando value remote service
	 */
	public void setExpandoValueService(ExpandoValueService expandoValueService) {
		this.expandoValueService = expandoValueService;
	}

	/**
	 * Gets the expando value persistence.
	 *
	 * @return the expando value persistence
	 */
	public ExpandoValuePersistence getExpandoValuePersistence() {
		return expandoValuePersistence;
	}

	/**
	 * Sets the expando value persistence.
	 *
	 * @param expandoValuePersistence the expando value persistence
	 */
	public void setExpandoValuePersistence(
		ExpandoValuePersistence expandoValuePersistence) {
		this.expandoValuePersistence = expandoValuePersistence;
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query to perform
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = journalTemplatePersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = JournalArticleLocalService.class)
	protected JournalArticleLocalService journalArticleLocalService;
	@BeanReference(type = JournalArticleService.class)
	protected JournalArticleService journalArticleService;
	@BeanReference(type = JournalArticlePersistence.class)
	protected JournalArticlePersistence journalArticlePersistence;
	@BeanReference(type = JournalArticleFinder.class)
	protected JournalArticleFinder journalArticleFinder;
	@BeanReference(type = JournalArticleImageLocalService.class)
	protected JournalArticleImageLocalService journalArticleImageLocalService;
	@BeanReference(type = JournalArticleImagePersistence.class)
	protected JournalArticleImagePersistence journalArticleImagePersistence;
	@BeanReference(type = JournalArticleResourceLocalService.class)
	protected JournalArticleResourceLocalService journalArticleResourceLocalService;
	@BeanReference(type = JournalArticleResourcePersistence.class)
	protected JournalArticleResourcePersistence journalArticleResourcePersistence;
	@BeanReference(type = JournalContentSearchLocalService.class)
	protected JournalContentSearchLocalService journalContentSearchLocalService;
	@BeanReference(type = JournalContentSearchPersistence.class)
	protected JournalContentSearchPersistence journalContentSearchPersistence;
	@BeanReference(type = JournalFeedLocalService.class)
	protected JournalFeedLocalService journalFeedLocalService;
	@BeanReference(type = JournalFeedService.class)
	protected JournalFeedService journalFeedService;
	@BeanReference(type = JournalFeedPersistence.class)
	protected JournalFeedPersistence journalFeedPersistence;
	@BeanReference(type = JournalFeedFinder.class)
	protected JournalFeedFinder journalFeedFinder;
	@BeanReference(type = JournalStructureLocalService.class)
	protected JournalStructureLocalService journalStructureLocalService;
	@BeanReference(type = JournalStructureService.class)
	protected JournalStructureService journalStructureService;
	@BeanReference(type = JournalStructurePersistence.class)
	protected JournalStructurePersistence journalStructurePersistence;
	@BeanReference(type = JournalStructureFinder.class)
	protected JournalStructureFinder journalStructureFinder;
	@BeanReference(type = JournalTemplateLocalService.class)
	protected JournalTemplateLocalService journalTemplateLocalService;
	@BeanReference(type = JournalTemplateService.class)
	protected JournalTemplateService journalTemplateService;
	@BeanReference(type = JournalTemplatePersistence.class)
	protected JournalTemplatePersistence journalTemplatePersistence;
	@BeanReference(type = JournalTemplateFinder.class)
	protected JournalTemplateFinder journalTemplateFinder;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ImageLocalService.class)
	protected ImageLocalService imageLocalService;
	@BeanReference(type = ImageService.class)
	protected ImageService imageService;
	@BeanReference(type = ImagePersistence.class)
	protected ImagePersistence imagePersistence;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = ResourceFinder.class)
	protected ResourceFinder resourceFinder;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;
	@BeanReference(type = WebDAVPropsLocalService.class)
	protected WebDAVPropsLocalService webDAVPropsLocalService;
	@BeanReference(type = WebDAVPropsPersistence.class)
	protected WebDAVPropsPersistence webDAVPropsPersistence;
	@BeanReference(type = ExpandoValueLocalService.class)
	protected ExpandoValueLocalService expandoValueLocalService;
	@BeanReference(type = ExpandoValueService.class)
	protected ExpandoValueService expandoValueService;
	@BeanReference(type = ExpandoValuePersistence.class)
	protected ExpandoValuePersistence expandoValuePersistence;
}