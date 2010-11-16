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

package com.liferay.portlet.expando.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the ExpandoTable service. Represents a row in the &quot;ExpandoTable&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.expando.model.impl.ExpandoTableModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.expando.model.impl.ExpandoTableImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoTable
 * @see com.liferay.portlet.expando.model.impl.ExpandoTableImpl
 * @see com.liferay.portlet.expando.model.impl.ExpandoTableModelImpl
 * @generated
 */
public interface ExpandoTableModel extends BaseModel<ExpandoTable> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a expando table model instance should use the {@link ExpandoTable} interface instead.
	 */

	/**
	 * Gets the primary key of this expando table.
	 *
	 * @return the primary key of this expando table
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this expando table
	 *
	 * @param pk the primary key of this expando table
	 */
	public void setPrimaryKey(long pk);

	/**
	 * Gets the table id of this expando table.
	 *
	 * @return the table id of this expando table
	 */
	public long getTableId();

	/**
	 * Sets the table id of this expando table.
	 *
	 * @param tableId the table id of this expando table
	 */
	public void setTableId(long tableId);

	/**
	 * Gets the company id of this expando table.
	 *
	 * @return the company id of this expando table
	 */
	public long getCompanyId();

	/**
	 * Sets the company id of this expando table.
	 *
	 * @param companyId the company id of this expando table
	 */
	public void setCompanyId(long companyId);

	/**
	 * Gets the class name of the model instance this expando table is polymorphically associated with.
	 *
	 * @return the class name of the model instance this expando table is polymorphically associated with
	 */
	public String getClassName();

	/**
	 * Gets the class name id of this expando table.
	 *
	 * @return the class name id of this expando table
	 */
	public long getClassNameId();

	/**
	 * Sets the class name id of this expando table.
	 *
	 * @param classNameId the class name id of this expando table
	 */
	public void setClassNameId(long classNameId);

	/**
	 * Gets the name of this expando table.
	 *
	 * @return the name of this expando table
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this expando table.
	 *
	 * @param name the name of this expando table
	 */
	public void setName(String name);

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

	public int compareTo(ExpandoTable expandoTable);

	public int hashCode();

	public ExpandoTable toEscapedModel();

	public String toString();

	public String toXmlString();
}