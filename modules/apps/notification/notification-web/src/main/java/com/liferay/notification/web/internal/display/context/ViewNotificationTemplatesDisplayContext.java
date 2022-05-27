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

package com.liferay.notification.web.internal.display.context;

import com.liferay.frontend.data.set.model.FDSActionDropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.notification.constants.NotificationActionKeys;
import com.liferay.notification.model.NotificationTemplate;
import com.liferay.notification.web.internal.constants.NotificationWebKeys;
import com.liferay.notification.web.internal.display.context.helper.NotificationRequestHelper;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Arrays;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.WindowStateException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Gabriel Albuquerque
 */
public class ViewNotificationTemplatesDisplayContext {

	public ViewNotificationTemplatesDisplayContext(
		HttpServletRequest httpServletRequest,
		ModelResourcePermission<NotificationTemplate>
			notificationTemplateModelResourcePermission) {

		_notificationTemplateModelResourcePermission =
			notificationTemplateModelResourcePermission;

		_notificationRequestHelper = new NotificationRequestHelper(
			httpServletRequest);
	}

	public String getAPIURL() {
		return "/o/notification/v1.0/notification-templates";
	}

	public CreationMenu getCreationMenu() {
		CreationMenu creationMenu = new CreationMenu();

		if (!_hasAddNotificationTemplatePermission()) {
			return creationMenu;
		}

		creationMenu.addDropdownItem(
			dropdownItem -> {
				dropdownItem.setHref("addNotificationTemplate");
				dropdownItem.setLabel(
					LanguageUtil.get(
						_notificationRequestHelper.getRequest(),
						"add-notification-template"));
				dropdownItem.setTarget("event");
			});

		return creationMenu;
	}

	public List<FDSActionDropdownItem> getFDSActionDropdownItems()
		throws Exception {

		return Arrays.asList(
			new FDSActionDropdownItem(
				getAPIURL() + "/{id}", "trash", "delete",
				LanguageUtil.get(
					_notificationRequestHelper.getRequest(), "delete"),
				"delete", "delete", "async"),
			new FDSActionDropdownItem(
				_getPermissionsURL(), null, "permissions",
				LanguageUtil.get(
					_notificationRequestHelper.getRequest(), "permissions"),
				"get", "permissions", "modal-permissions"));
	}


	public PortletURL getPortletURL() throws PortletException {
		return PortletURLUtil.clone(
			PortletURLUtil.getCurrent(
				_notificationRequestHelper.getLiferayPortletRequest(),
				_notificationRequestHelper.getLiferayPortletResponse()),
			_notificationRequestHelper.getLiferayPortletResponse());
	}

	private String _getPermissionsURL() throws Exception {
		PortletURL portletURL = PortletURLBuilder.create(
			PortalUtil.getControlPanelPortletURL(
				_notificationRequestHelper.getRequest(),
				"com_liferay_portlet_configuration_web_portlet_" +
					"PortletConfigurationPortlet",
				ActionRequest.RENDER_PHASE)
		).setMVCPath(
			"/edit_permissions.jsp"
		).setRedirect(
			_notificationRequestHelper.getCurrentURL()
		).setParameter(
			"modelResource", NotificationTemplate.class.getName()
		).setParameter(
			"modelResourceDescription", "{name}"
		).setParameter(
			"resourcePrimKey", "{id}"
		).buildPortletURL();

		try {
			portletURL.setWindowState(LiferayWindowState.POP_UP);
		}
		catch (WindowStateException windowStateException) {
			throw new PortalException(windowStateException);
		}

		return portletURL.toString();
	}

	private boolean _hasAddNotificationTemplatePermission() {
		PortletResourcePermission portletResourcePermission =
			_notificationTemplateModelResourcePermission.
				getPortletResourcePermission();

		return portletResourcePermission.contains(
			_notificationRequestHelper.getPermissionChecker(), null,
			NotificationActionKeys.ADD_NOTIFICATION_TEMPLATE);
	}

	private final NotificationRequestHelper _notificationRequestHelper;
	private final ModelResourcePermission<NotificationTemplate>
		_notificationTemplateModelResourcePermission;

}