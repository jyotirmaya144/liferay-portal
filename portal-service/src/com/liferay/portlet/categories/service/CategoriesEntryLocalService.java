/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.categories.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;

/**
 * <a href="CategoriesEntryLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.liferay.portlet.categories.service.impl.CategoriesEntryLocalServiceImpl</code>.
 * Modify methods in that class and rerun ServiceBuilder to populate this class
 * and all other generated classes.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portlet.categories.service.CategoriesEntryLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CategoriesEntryLocalService {
	public com.liferay.portlet.categories.model.CategoriesEntry addCategoriesEntry(
		com.liferay.portlet.categories.model.CategoriesEntry categoriesEntry)
		throws com.liferay.portal.SystemException;

	public com.liferay.portlet.categories.model.CategoriesEntry createCategoriesEntry(
		long entryId);

	public void deleteCategoriesEntry(long entryId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteCategoriesEntry(
		com.liferay.portlet.categories.model.CategoriesEntry categoriesEntry)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portlet.categories.model.CategoriesEntry getCategoriesEntry(
		long entryId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portlet.categories.model.CategoriesEntry> getCategoriesEntries(
		int start, int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCategoriesEntriesCount()
		throws com.liferay.portal.SystemException;

	public com.liferay.portlet.categories.model.CategoriesEntry updateCategoriesEntry(
		com.liferay.portlet.categories.model.CategoriesEntry categoriesEntry)
		throws com.liferay.portal.SystemException;

	public com.liferay.portlet.categories.model.CategoriesEntry updateCategoriesEntry(
		com.liferay.portlet.categories.model.CategoriesEntry categoriesEntry,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.portlet.categories.model.CategoriesEntry addEntry(
		long userId, long vocabularyId, long parentEntryId,
		java.lang.String name, java.lang.String[] properties,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void addEntryResources(
		com.liferay.portlet.categories.model.CategoriesEntry entry,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void addEntryResources(
		com.liferay.portlet.categories.model.CategoriesEntry entry,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void deleteEntry(long entryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void deleteEntry(
		com.liferay.portlet.categories.model.CategoriesEntry entry)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void deleteVocabularyEntries(long vocabularyId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portlet.categories.model.CategoriesEntry> getAssetEntries(
		long assetId) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portlet.categories.model.CategoriesEntry> getEntries()
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portlet.categories.model.CategoriesEntry> getEntries(
		java.lang.String className, long classPK)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portlet.categories.model.CategoriesEntry> getEntries(
		long classNameId, long classPK)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portlet.categories.model.CategoriesEntry getEntry(
		long entryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portlet.categories.model.CategoriesEntry> getChildEntries(
		long parentEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portlet.categories.model.CategoriesEntry> getRootVocabularyEntries(
		long vocabularyId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void mergeEntries(long fromEntryId, long toEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.kernel.json.JSONArray search(long groupId,
		java.lang.String name, java.lang.String[] properties, int start, int end)
		throws com.liferay.portal.SystemException;

	public com.liferay.portlet.categories.model.CategoriesEntry updateEntry(
		long userId, long entryId, long vocabularyId, long parentEntryId,
		java.lang.String name, java.lang.String[] properties)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;
}