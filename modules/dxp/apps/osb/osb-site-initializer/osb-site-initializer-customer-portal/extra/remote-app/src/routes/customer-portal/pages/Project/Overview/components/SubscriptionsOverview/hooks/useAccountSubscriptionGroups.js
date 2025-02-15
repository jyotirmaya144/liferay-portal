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

import {useEffect, useState} from 'react';
import {useGetAccountSubscriptionGroups} from '../../../../../../../../common/services/liferay/graphql/account-subscription-groups';

export default function useAccountSubscriptionGroups(
	accountKey,
	koroneikiAccountLoading
) {
	const [
		lastAccountSubcriptionGroup,
		setLastAccountSubscriptionGroup,
	] = useState();

	const {data, loading} = useGetAccountSubscriptionGroups({
		filter: `accountKey eq '${accountKey}'`,
		skip: koroneikiAccountLoading,
		sort: 'tabOrder:asc',
	});

	const accountSubscriptionGroups = data?.c.accountSubscriptionGroups.items;

	useEffect(() => {
		if (!loading && !!accountSubscriptionGroups?.length) {
			setLastAccountSubscriptionGroup(accountSubscriptionGroups[0]);
		}
	}, [accountSubscriptionGroups, loading]);

	return [
		{lastAccountSubcriptionGroup, setLastAccountSubscriptionGroup},
		{
			data,
			loading: koroneikiAccountLoading || loading,
		},
	];
}
