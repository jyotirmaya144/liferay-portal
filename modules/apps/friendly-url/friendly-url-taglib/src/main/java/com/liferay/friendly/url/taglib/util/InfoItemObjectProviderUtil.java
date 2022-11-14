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

package com.liferay.friendly.url.taglib.util;

import com.liferay.info.item.InfoItemServiceRegistry;
import com.liferay.info.item.provider.InfoItemObjectProvider;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Adolfo Pérez
 */
@Component(service = {})
public class InfoItemObjectProviderUtil {

	public static Object getInfoItem(String className, long classPK) {
		try {
			if (_infoItemServiceRegistry == null) {
				return null;
			}

			InfoItemObjectProvider<Object> infoItemObjectProvider =
				_infoItemServiceRegistry.getFirstInfoItemService(
					InfoItemObjectProvider.class, className);

			return infoItemObjectProvider.getInfoItem(classPK);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}

			return null;
		}
	}

	@Reference(policy = ReferencePolicy.DYNAMIC)
	protected void setInfoItemServiceRegistry(
		InfoItemServiceRegistry infoItemServiceRegistry) {

		_infoItemServiceRegistry = infoItemServiceRegistry;
	}

	protected void unsetInfoItemServiceRegistry(
		InfoItemServiceRegistry infoItemServiceRegistry) {

		_infoItemServiceRegistry = null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		InfoItemObjectProviderUtil.class);

	private static volatile InfoItemServiceRegistry _infoItemServiceRegistry;

}