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

package com.liferay.commerce.pricing.internal.upgrade.registry;

import com.liferay.commerce.pricing.internal.upgrade.v2_1_0.CommercePricingConfigurationUpgradeProcess;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.upgrade.CTModelUpgradeProcess;
import com.liferay.portal.kernel.upgrade.DummyUpgradeProcess;
import com.liferay.portal.kernel.upgrade.MVCCVersionUpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeProcessFactory;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true, service = UpgradeStepRegistrator.class
)
public class CommercePricingServiceUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"1.0.0", "1.1.0",
			UpgradeProcessFactory.alterColumnTypes(
				"CommercePricingClass", "TEXT", "title", "description"));

		registry.register(
			"1.1.0", "2.0.0",
			new com.liferay.commerce.pricing.internal.upgrade.v2_0_0.
				CommercePricingClassUpgradeProcess(
					_resourceActionLocalService, _resourceLocalService));

		registry.register(
			"2.0.0", "2.0.1",
			UpgradeProcessFactory.runSQL(
				"update CommercePriceModifier set target = 'product-groups' " +
					"where target = 'pricing-classes'"),
			UpgradeProcessFactory.runSQL(
				"update CommercePriceModifier set modifierType = " +
					"'fixed-amount' where modifierType = 'absolute'"),
			UpgradeProcessFactory.runSQL(
				"update CommercePriceModifier set modifierType = 'replace' " +
					"where modifierType = 'override'"));

		registry.register("2.0.1", "2.0.2", new DummyUpgradeProcess());

		registry.register(
			"2.0.2", "2.1.0",
			new CommercePricingConfigurationUpgradeProcess(
				_configurationProvider));

		registry.register(
			"2.1.0", "2.2.0",
			new MVCCVersionUpgradeProcess() {

				@Override
				protected String[] getModuleTableNames() {
					return new String[] {
						"CPricingClassCPDefinitionRel", "CommercePriceModifier",
						"CommercePriceModifierRel", "CommercePricingClass"
					};
				}

			});

		registry.register(
			"2.2.0", "2.3.0",
			new CTModelUpgradeProcess(
				"CPricingClassCPDefinitionRel", "CommercePriceModifier",
				"CommercePriceModifierRel", "CommercePricingClass"));
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private ResourceActionLocalService _resourceActionLocalService;

	@Reference
	private ResourceLocalService _resourceLocalService;

}