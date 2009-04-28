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
 * <a href="CategoriesPropertyLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.liferay.portlet.categories.service.impl.CategoriesPropertyLocalServiceImpl</code>.
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
 * @see com.liferay.portlet.categories.service.CategoriesPropertyLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CategoriesPropertyLocalService {
	public com.liferay.portlet.categories.model.CategoriesProperty addCategoriesProperty(
		com.liferay.portlet.categories.model.CategoriesProperty categoriesProperty)
		throws com.liferay.portal.SystemException;

	public com.liferay.portlet.categories.model.CategoriesProperty createCategoriesProperty(
		long propertyId);

	public void deleteCategoriesProperty(long propertyId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteCategoriesProperty(
		com.liferay.portlet.categories.model.CategoriesProperty categoriesProperty)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portlet.categories.model.CategoriesProperty getCategoriesProperty(
		long propertyId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portlet.categories.model.CategoriesProperty> getCategoriesProperties(
		int start, int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCategoriesPropertiesCount()
		throws com.liferay.portal.SystemException;

	public com.liferay.portlet.categories.model.CategoriesProperty updateCategoriesProperty(
		com.liferay.portlet.categories.model.CategoriesProperty categoriesProperty)
		throws com.liferay.portal.SystemException;

	public com.liferay.portlet.categories.model.CategoriesProperty updateCategoriesProperty(
		com.liferay.portlet.categories.model.CategoriesProperty categoriesProperty,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.portlet.categories.model.CategoriesProperty addProperty(
		long userId, long entryId, java.lang.String key, java.lang.String value)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void deleteProperties(long entryId)
		throws com.liferay.portal.SystemException;

	public void deleteProperty(long propertyId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void deleteProperty(
		com.liferay.portlet.categories.model.CategoriesProperty property)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portlet.categories.model.CategoriesProperty> getProperties()
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portlet.categories.model.CategoriesProperty> getProperties(
		long entryId) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portlet.categories.model.CategoriesProperty getProperty(
		long propertyId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portlet.categories.model.CategoriesProperty getProperty(
		long entryId, java.lang.String key)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portlet.categories.model.CategoriesProperty> getPropertyValues(
		long groupId, java.lang.String key)
		throws com.liferay.portal.SystemException;

	public com.liferay.portlet.categories.model.CategoriesProperty updateProperty(
		long propertyId, java.lang.String key, java.lang.String value)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;
}