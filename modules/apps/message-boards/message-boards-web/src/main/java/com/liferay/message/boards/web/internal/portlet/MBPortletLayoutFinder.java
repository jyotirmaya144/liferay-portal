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

package com.liferay.message.boards.web.internal.portlet;

import com.liferay.message.boards.constants.MBPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.BasePortletLayoutFinder;
import com.liferay.portal.kernel.portlet.PortletLayoutFinder;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(
	property = {
		"model.class.name=com.liferay.message.boards.model.MBCategory",
		"model.class.name=com.liferay.message.boards.model.MBMessage",
		"model.class.name=com.liferay.message.boards.model.MBThread"
	},
	service = PortletLayoutFinder.class
)
public class MBPortletLayoutFinder extends BasePortletLayoutFinder {

	@Override
	public Result find(ThemeDisplay themeDisplay, long groupId)
		throws PortalException {

		Layout layout = themeDisplay.getLayout();

		if (layout.isTypeControlPanel()) {
			return new ResultImpl(
				themeDisplay.getPlid(), MBPortletKeys.MESSAGE_BOARDS_ADMIN,
				true);
		}

		return super.find(themeDisplay, groupId);
	}

	@Override
	protected String[] getPortletIds() {
		return _PORTLET_IDS;
	}

	// Order is important. See LPS-23770.

	private static final String[] _PORTLET_IDS = {
		MBPortletKeys.MESSAGE_BOARDS_ADMIN, MBPortletKeys.MESSAGE_BOARDS
	};

}