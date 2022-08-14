/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import ClayForm from '@clayui/form';
import {FieldArray} from 'formik';
import {useEffect, useMemo, useState} from 'react';
import i18n from '../../../../../../../../../../../common/I18n';
import {
	Button,
	Input,
	Select,
} from '../../../../../../../../../../../common/components';
import Layout from '../../../../../../../../../../../common/containers/setup-forms/Layout';
import {useAppPropertiesContext} from '../../../../../../../../../../../common/contexts/AppPropertiesContext';
import {
	createAdminLiferayExperienceCloud,
	createLiferayExperienceCloudEnvironment,
	getListTypeDefinitions,
	updateAccountSubscriptionGroups,
} from '../../../../../../../../../../../common/services/liferay/graphql/queries';
import {
	LIST_TYPES,
	STATUS_TAG_TYPE_NAMES,
} from '../../../../../../../../../utils/constants';
import getInitialLxcAdmins from '../../utils/getInitialLxcAdmins';
import AdminInputs from './components/AdminsInput';

const INITIAL_SETUP_ADMIN_COUNT = 1;
const listType = LIST_TYPES.lxcPrimaryRegion;

const SetupLiferayExperienceCloudPage = ({
	errors,
	handleChangeForm,
	leftButton,
	project,
	setIsVisibleSetupLxcModal,
	subscriptionGroupLxcId,
	touched,
	values,
}) => {
	const [baseButtonDisabled, setBaseButtonDisabled] = useState(true);
	const [listItem, setListItem] = useState([]);
	const {client} = useAppPropertiesContext();

	useEffect(() => {
		const hasTouched = !Object.keys(touched).length;
		const hasError = Object.keys(errors).length;

		setBaseButtonDisabled(hasTouched || hasError);
	}, [touched, errors]);

	useEffect(() => {
		const fetchListPrimaryRegions = async () => {
			const {data} = await client.query({
				query: getListTypeDefinitions,
				variables: {filter: `name eq '${listType}'`},
			});
			const items = data?.listTypeDefinitions?.items[0].listTypeEntries;
			setListItem(items);
		};
		fetchListPrimaryRegions();
	}, [client]);

	const primaryRegionList = useMemo(
		() => listItem.map(({name}) => ({label: name, value: name})) || [],
		[listItem]
	);

	const handleSubmitLxcEnvironment = async () => {
		const lxcActivationFields = values?.lxc;

		const {data} = await client.mutate({
			mutation: createLiferayExperienceCloudEnvironment,
			variables: {
				LiferayExperienceCloudEnvironment: {
					accountKey: project.accountKey,
					incidentManagementEmailAddress:
						lxcActivationFields.incidentManagementEmail,
					incidentManagementFullName:
						lxcActivationFields.incidentManagementfullName,
					primaryRegion: lxcActivationFields.primaryRegion,
					projectId: lxcActivationFields.projectId,
				},
			},
		});

		if (data) {
			const liferayExperienceCloudEnvironmentId =
				data.c?.createLiferayExperienceCloudEnvironment
					?.liferayExperienceCloudEnvironmentId;

			await client.mutate({
				mutation: updateAccountSubscriptionGroups,
				variables: {
					accountSubscriptionGroup: {
						accountKey: project.accountKey,
						activationStatus: STATUS_TAG_TYPE_NAMES.inProgress,
					},
					id: subscriptionGroupLxcId,
				},
			});

			await Promise.all(
				lxcActivationFields?.admins?.map(
					({email, fullName, github}) => {
						return client.mutate({
							mutation: createAdminLiferayExperienceCloud,
							variables: {
								AdminLiferayExperienceCloud: {
									emailAddress: email,
									fullName,
									githubUsername: github,
									liferayExperienceCloudEnvironmentId,
								},
							},
						});
					}
				)
			);
		}

		handleChangeForm(true);
	};

	return (
		<Layout
			className="pt-1 px-3"
			footerProps={{
				leftButton: (
					<Button
						borderless
						onClick={() => setIsVisibleSetupLxcModal(false)}
					>
						{leftButton}
					</Button>
				),
				middleButton: (
					<Button
						disabled={baseButtonDisabled}
						displayType="primary"
						onClick={() => handleSubmitLxcEnvironment()}
					>
						{i18n.translate('submit')}
					</Button>
				),
			}}
			headerProps={{
				helper: i18n.translate(
					'we-ll-need-a-few-details-to-finish-creating-your-liferay-experience-cloud-workspace'
				),
				title: i18n.translate('set-up-liferay-experience-cloud'),
			}}
		>
			<FieldArray
				name="lxc.admins"
				render={({pop, push}) => (
					<>
						<div className="d-flex justify-content-between mb-2 pb-1 pl-3">
							<div className="mr-4 pr-2">
								<label>
									{i18n.translate('organization-name')}
								</label>

								<p className="dxp-cloud-project-name text-neutral-6 text-paragraph-lg">
									<strong>{project.name}</strong>
								</p>
							</div>
						</div>
						<ClayForm.Group className="mb-0">
							<ClayForm.Group className="mb-0 pb-1">
								<Input
									groupStyle="pb-1"
									helper={i18n.translate(
										'lowercase-letters-and-numbers-only-project-ids-cannot-be-changed'
									)}
									label={i18n.translate('project-id')}
									name="lxc.projectId"
									required
									type="text"
								/>

								<Select
									groupStyle="mb-0"
									key={primaryRegionList}
									label={i18n.translate('primary-region')}
									name="lxc.primaryRegion"
									options={primaryRegionList}
									required
								/>
							</ClayForm.Group>

							<ClayForm.Group className="mb-0">
								{values.lxc.admins.map((admin, index) => (
									<AdminInputs
										admin={admin}
										id={index}
										key={index}
									/>
								))}
							</ClayForm.Group>
						</ClayForm.Group>

						{values?.lxc?.admins?.length >
							INITIAL_SETUP_ADMIN_COUNT && (
							<Button
								className="ml-3 my-2 text-brandy-secondary"
								displayType="secondary"
								onClick={() => {
									pop();
									setBaseButtonDisabled(false);
								}}
								prependIcon="hr"
								small
							>
								{i18n.translate('remove-project-admin')}
							</Button>
						)}

						<Button
							className="cp-btn-add-dxp-cloud ml-3 my-2 rounded-xs"
							onClick={() => {
								push(getInitialLxcAdmins(values?.lxc?.admins));
								setBaseButtonDisabled(true);
							}}
							prependIcon="plus"
							small
						>
							{i18n.translate('add-another-admin')}
						</Button>

						<hr />

						<ClayForm.Group className="mb-0">
							<Input
								groupStyle="pb-1"
								label={i18n.translate(
									'incident-management-contacts-first-and-last-name'
								)}
								name="lxc.incidentManagementFullName"
								required
								type="text"
							/>

							<Input
								groupStyle="pb-1"
								helper={i18n.translate(
									'lowercase-letters-and-numbers-only-project-ids-cannot-be-changed'
								)}
								label={i18n.translate(
									'incident-management-contacts-email-address'
								)}
								name="lxc.incidentManagementEmail"
								required
								type="text"
							/>
						</ClayForm.Group>
					</>
				)}
			/>
		</Layout>
	);
};

export default SetupLiferayExperienceCloudPage;
