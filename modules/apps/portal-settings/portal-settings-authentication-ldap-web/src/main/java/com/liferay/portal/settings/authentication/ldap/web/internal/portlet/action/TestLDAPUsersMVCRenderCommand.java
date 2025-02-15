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

package com.liferay.portal.settings.authentication.ldap.web.internal.portlet.action;

import com.liferay.configuration.admin.constants.ConfigurationAdminPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.constants.MVCRenderConstants;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.util.Portal;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Philip Jones
 */
@Component(
	property = {
		"javax.portlet.name=" + ConfigurationAdminPortletKeys.INSTANCE_SETTINGS,
		"mvc.command.name=/portal_settings_authentication_ldap/test_ldap_users"
	},
	service = MVCRenderCommand.class
)
public class TestLDAPUsersMVCRenderCommand
	extends BasePortalSettingsMVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			AuthTokenUtil.checkCSRFToken(
				_portal.getOriginalServletRequest(
					_portal.getHttpServletRequest(renderRequest)),
				getClass().getName());

			return super.render(renderRequest, renderResponse);
		}
		catch (PrincipalException principalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to test LDAP connection: " +
						principalException.getMessage(),
					principalException);
			}
			else if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to test LDAP connection: " +
						principalException.getMessage());
			}
		}

		return MVCRenderConstants.MVC_PATH_VALUE_SKIP_DISPATCH;
	}

	@Override
	protected String getJspPath() {
		return _JSP_PATH;
	}

	private static final String _JSP_PATH =
		"/com.liferay.portal.settings.web/test_ldap_users.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		TestLDAPUsersMVCRenderCommand.class);

	@Reference
	private Portal _portal;

}