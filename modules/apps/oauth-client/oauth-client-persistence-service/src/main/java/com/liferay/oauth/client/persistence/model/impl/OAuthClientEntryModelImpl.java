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

package com.liferay.oauth.client.persistence.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.oauth.client.persistence.model.OAuthClientEntry;
import com.liferay.oauth.client.persistence.model.OAuthClientEntryModel;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the OAuthClientEntry service. Represents a row in the &quot;OAuthClientEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>OAuthClientEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OAuthClientEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuthClientEntryImpl
 * @generated
 */
@JSON(strict = true)
public class OAuthClientEntryModelImpl
	extends BaseModelImpl<OAuthClientEntry> implements OAuthClientEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a o auth client entry model instance should use the <code>OAuthClientEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "OAuthClientEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"oAuthClientEntryId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"authRequestParametersJSON", Types.VARCHAR},
		{"authServerWellKnownURI", Types.VARCHAR}, {"clientId", Types.VARCHAR},
		{"infoJSON", Types.CLOB}, {"tokenRequestParametersJSON", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("oAuthClientEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("authRequestParametersJSON", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("authServerWellKnownURI", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("clientId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("infoJSON", Types.CLOB);
		TABLE_COLUMNS_MAP.put("tokenRequestParametersJSON", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table OAuthClientEntry (mvccVersion LONG default 0 not null,oAuthClientEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,authRequestParametersJSON VARCHAR(3999) null,authServerWellKnownURI VARCHAR(256) null,clientId VARCHAR(128) null,infoJSON TEXT null,tokenRequestParametersJSON VARCHAR(3999) null)";

	public static final String TABLE_SQL_DROP = "drop table OAuthClientEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY oAuthClientEntry.oAuthClientEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY OAuthClientEntry.oAuthClientEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long AUTHSERVERWELLKNOWNURI_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CLIENTID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long OAUTHCLIENTENTRYID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	public OAuthClientEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _oAuthClientEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOAuthClientEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _oAuthClientEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return OAuthClientEntry.class;
	}

	@Override
	public String getModelClassName() {
		return OAuthClientEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<OAuthClientEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<OAuthClientEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OAuthClientEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((OAuthClientEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<OAuthClientEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<OAuthClientEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(OAuthClientEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<OAuthClientEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<OAuthClientEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<OAuthClientEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<OAuthClientEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<OAuthClientEntry, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<OAuthClientEntry, Object>>();
		Map<String, BiConsumer<OAuthClientEntry, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<OAuthClientEntry, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", OAuthClientEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<OAuthClientEntry, Long>)
				OAuthClientEntry::setMvccVersion);
		attributeGetterFunctions.put(
			"oAuthClientEntryId", OAuthClientEntry::getOAuthClientEntryId);
		attributeSetterBiConsumers.put(
			"oAuthClientEntryId",
			(BiConsumer<OAuthClientEntry, Long>)
				OAuthClientEntry::setOAuthClientEntryId);
		attributeGetterFunctions.put(
			"companyId", OAuthClientEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<OAuthClientEntry, Long>)OAuthClientEntry::setCompanyId);
		attributeGetterFunctions.put("userId", OAuthClientEntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<OAuthClientEntry, Long>)OAuthClientEntry::setUserId);
		attributeGetterFunctions.put("userName", OAuthClientEntry::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<OAuthClientEntry, String>)
				OAuthClientEntry::setUserName);
		attributeGetterFunctions.put(
			"createDate", OAuthClientEntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<OAuthClientEntry, Date>)
				OAuthClientEntry::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", OAuthClientEntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<OAuthClientEntry, Date>)
				OAuthClientEntry::setModifiedDate);
		attributeGetterFunctions.put(
			"authRequestParametersJSON",
			OAuthClientEntry::getAuthRequestParametersJSON);
		attributeSetterBiConsumers.put(
			"authRequestParametersJSON",
			(BiConsumer<OAuthClientEntry, String>)
				OAuthClientEntry::setAuthRequestParametersJSON);
		attributeGetterFunctions.put(
			"authServerWellKnownURI",
			OAuthClientEntry::getAuthServerWellKnownURI);
		attributeSetterBiConsumers.put(
			"authServerWellKnownURI",
			(BiConsumer<OAuthClientEntry, String>)
				OAuthClientEntry::setAuthServerWellKnownURI);
		attributeGetterFunctions.put("clientId", OAuthClientEntry::getClientId);
		attributeSetterBiConsumers.put(
			"clientId",
			(BiConsumer<OAuthClientEntry, String>)
				OAuthClientEntry::setClientId);
		attributeGetterFunctions.put("infoJSON", OAuthClientEntry::getInfoJSON);
		attributeSetterBiConsumers.put(
			"infoJSON",
			(BiConsumer<OAuthClientEntry, String>)
				OAuthClientEntry::setInfoJSON);
		attributeGetterFunctions.put(
			"tokenRequestParametersJSON",
			OAuthClientEntry::getTokenRequestParametersJSON);
		attributeSetterBiConsumers.put(
			"tokenRequestParametersJSON",
			(BiConsumer<OAuthClientEntry, String>)
				OAuthClientEntry::setTokenRequestParametersJSON);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public long getOAuthClientEntryId() {
		return _oAuthClientEntryId;
	}

	@Override
	public void setOAuthClientEntryId(long oAuthClientEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_oAuthClientEntryId = oAuthClientEntryId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("userId"));
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getAuthRequestParametersJSON() {
		if (_authRequestParametersJSON == null) {
			return "";
		}
		else {
			return _authRequestParametersJSON;
		}
	}

	@Override
	public void setAuthRequestParametersJSON(String authRequestParametersJSON) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_authRequestParametersJSON = authRequestParametersJSON;
	}

	@JSON
	@Override
	public String getAuthServerWellKnownURI() {
		if (_authServerWellKnownURI == null) {
			return "";
		}
		else {
			return _authServerWellKnownURI;
		}
	}

	@Override
	public void setAuthServerWellKnownURI(String authServerWellKnownURI) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_authServerWellKnownURI = authServerWellKnownURI;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalAuthServerWellKnownURI() {
		return getColumnOriginalValue("authServerWellKnownURI");
	}

	@JSON
	@Override
	public String getClientId() {
		if (_clientId == null) {
			return "";
		}
		else {
			return _clientId;
		}
	}

	@Override
	public void setClientId(String clientId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_clientId = clientId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalClientId() {
		return getColumnOriginalValue("clientId");
	}

	@JSON
	@Override
	public String getInfoJSON() {
		if (_infoJSON == null) {
			return "";
		}
		else {
			return _infoJSON;
		}
	}

	@Override
	public void setInfoJSON(String infoJSON) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_infoJSON = infoJSON;
	}

	@JSON
	@Override
	public String getTokenRequestParametersJSON() {
		if (_tokenRequestParametersJSON == null) {
			return "";
		}
		else {
			return _tokenRequestParametersJSON;
		}
	}

	@Override
	public void setTokenRequestParametersJSON(
		String tokenRequestParametersJSON) {

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_tokenRequestParametersJSON = tokenRequestParametersJSON;
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), OAuthClientEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public OAuthClientEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, OAuthClientEntry>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		OAuthClientEntryImpl oAuthClientEntryImpl = new OAuthClientEntryImpl();

		oAuthClientEntryImpl.setMvccVersion(getMvccVersion());
		oAuthClientEntryImpl.setOAuthClientEntryId(getOAuthClientEntryId());
		oAuthClientEntryImpl.setCompanyId(getCompanyId());
		oAuthClientEntryImpl.setUserId(getUserId());
		oAuthClientEntryImpl.setUserName(getUserName());
		oAuthClientEntryImpl.setCreateDate(getCreateDate());
		oAuthClientEntryImpl.setModifiedDate(getModifiedDate());
		oAuthClientEntryImpl.setAuthRequestParametersJSON(
			getAuthRequestParametersJSON());
		oAuthClientEntryImpl.setAuthServerWellKnownURI(
			getAuthServerWellKnownURI());
		oAuthClientEntryImpl.setClientId(getClientId());
		oAuthClientEntryImpl.setInfoJSON(getInfoJSON());
		oAuthClientEntryImpl.setTokenRequestParametersJSON(
			getTokenRequestParametersJSON());

		oAuthClientEntryImpl.resetOriginalValues();

		return oAuthClientEntryImpl;
	}

	@Override
	public OAuthClientEntry cloneWithOriginalValues() {
		OAuthClientEntryImpl oAuthClientEntryImpl = new OAuthClientEntryImpl();

		oAuthClientEntryImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		oAuthClientEntryImpl.setOAuthClientEntryId(
			this.<Long>getColumnOriginalValue("oAuthClientEntryId"));
		oAuthClientEntryImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		oAuthClientEntryImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		oAuthClientEntryImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		oAuthClientEntryImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		oAuthClientEntryImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		oAuthClientEntryImpl.setAuthRequestParametersJSON(
			this.<String>getColumnOriginalValue("authRequestParametersJSON"));
		oAuthClientEntryImpl.setAuthServerWellKnownURI(
			this.<String>getColumnOriginalValue("authServerWellKnownURI"));
		oAuthClientEntryImpl.setClientId(
			this.<String>getColumnOriginalValue("clientId"));
		oAuthClientEntryImpl.setInfoJSON(
			this.<String>getColumnOriginalValue("infoJSON"));
		oAuthClientEntryImpl.setTokenRequestParametersJSON(
			this.<String>getColumnOriginalValue("tokenRequestParametersJSON"));

		return oAuthClientEntryImpl;
	}

	@Override
	public int compareTo(OAuthClientEntry oAuthClientEntry) {
		long primaryKey = oAuthClientEntry.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof OAuthClientEntry)) {
			return false;
		}

		OAuthClientEntry oAuthClientEntry = (OAuthClientEntry)object;

		long primaryKey = oAuthClientEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<OAuthClientEntry> toCacheModel() {
		OAuthClientEntryCacheModel oAuthClientEntryCacheModel =
			new OAuthClientEntryCacheModel();

		oAuthClientEntryCacheModel.mvccVersion = getMvccVersion();

		oAuthClientEntryCacheModel.oAuthClientEntryId = getOAuthClientEntryId();

		oAuthClientEntryCacheModel.companyId = getCompanyId();

		oAuthClientEntryCacheModel.userId = getUserId();

		oAuthClientEntryCacheModel.userName = getUserName();

		String userName = oAuthClientEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			oAuthClientEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			oAuthClientEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			oAuthClientEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			oAuthClientEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			oAuthClientEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		oAuthClientEntryCacheModel.authRequestParametersJSON =
			getAuthRequestParametersJSON();

		String authRequestParametersJSON =
			oAuthClientEntryCacheModel.authRequestParametersJSON;

		if ((authRequestParametersJSON != null) &&
			(authRequestParametersJSON.length() == 0)) {

			oAuthClientEntryCacheModel.authRequestParametersJSON = null;
		}

		oAuthClientEntryCacheModel.authServerWellKnownURI =
			getAuthServerWellKnownURI();

		String authServerWellKnownURI =
			oAuthClientEntryCacheModel.authServerWellKnownURI;

		if ((authServerWellKnownURI != null) &&
			(authServerWellKnownURI.length() == 0)) {

			oAuthClientEntryCacheModel.authServerWellKnownURI = null;
		}

		oAuthClientEntryCacheModel.clientId = getClientId();

		String clientId = oAuthClientEntryCacheModel.clientId;

		if ((clientId != null) && (clientId.length() == 0)) {
			oAuthClientEntryCacheModel.clientId = null;
		}

		oAuthClientEntryCacheModel.infoJSON = getInfoJSON();

		String infoJSON = oAuthClientEntryCacheModel.infoJSON;

		if ((infoJSON != null) && (infoJSON.length() == 0)) {
			oAuthClientEntryCacheModel.infoJSON = null;
		}

		oAuthClientEntryCacheModel.tokenRequestParametersJSON =
			getTokenRequestParametersJSON();

		String tokenRequestParametersJSON =
			oAuthClientEntryCacheModel.tokenRequestParametersJSON;

		if ((tokenRequestParametersJSON != null) &&
			(tokenRequestParametersJSON.length() == 0)) {

			oAuthClientEntryCacheModel.tokenRequestParametersJSON = null;
		}

		return oAuthClientEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<OAuthClientEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<OAuthClientEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OAuthClientEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(OAuthClientEntry)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<OAuthClientEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<OAuthClientEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OAuthClientEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((OAuthClientEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, OAuthClientEntry>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					OAuthClientEntry.class, ModelWrapper.class);

	}

	private long _mvccVersion;
	private long _oAuthClientEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _authRequestParametersJSON;
	private String _authServerWellKnownURI;
	private String _clientId;
	private String _infoJSON;
	private String _tokenRequestParametersJSON;

	public <T> T getColumnValue(String columnName) {
		Function<OAuthClientEntry, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((OAuthClientEntry)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("mvccVersion", _mvccVersion);
		_columnOriginalValues.put("oAuthClientEntryId", _oAuthClientEntryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put(
			"authRequestParametersJSON", _authRequestParametersJSON);
		_columnOriginalValues.put(
			"authServerWellKnownURI", _authServerWellKnownURI);
		_columnOriginalValues.put("clientId", _clientId);
		_columnOriginalValues.put("infoJSON", _infoJSON);
		_columnOriginalValues.put(
			"tokenRequestParametersJSON", _tokenRequestParametersJSON);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("oAuthClientEntryId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("userId", 8L);

		columnBitmasks.put("userName", 16L);

		columnBitmasks.put("createDate", 32L);

		columnBitmasks.put("modifiedDate", 64L);

		columnBitmasks.put("authRequestParametersJSON", 128L);

		columnBitmasks.put("authServerWellKnownURI", 256L);

		columnBitmasks.put("clientId", 512L);

		columnBitmasks.put("infoJSON", 1024L);

		columnBitmasks.put("tokenRequestParametersJSON", 2048L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private OAuthClientEntry _escapedModel;

}