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

import {useCallback, useMemo} from 'react';
import {Liferay} from '../../../common/services/liferay';
import {useGetKoroneikiAccounts} from '../../../common/services/liferay/graphql/koroneiki-accounts';
import {useGetUserAccount} from '../../../common/services/liferay/graphql/user-accounts';
import {getAccountBriefsFilter} from '../utils/getAccountBriefsFilter';
import useSearchTerm from './useSearchTerm';

export default function useKoroneikiAccounts() {
	const {
		data: userAccountData,
		loading: userAccountLoading,
	} = useGetUserAccount(Liferay.ThemeDisplay.getUserId());

	const userAccount = userAccountData?.userAccount;

	const filterKoroneikiAccounts = useMemo(
		() =>
			!userAccount?.isLiferayStaff
				? `accountKey in ${getAccountBriefsFilter(
						userAccount?.accountBriefs
				  )}`
				: '',
		[userAccount?.accountBriefs, userAccount?.isLiferayStaff]
	);

	const {
		data,
		fetchMore,
		loading: koroneikiAccountsLoading,
		refetch,
	} = useGetKoroneikiAccounts({
		filter: filterKoroneikiAccounts,
		skip: userAccountLoading,
	});

	const getFilterKoroneikiAccountsBySearch = useCallback(
		(searchTerm) => {
			if (searchTerm) {
				const searchByName = `contains(name, '${searchTerm}')`;

				return filterKoroneikiAccounts
					? `${filterKoroneikiAccounts} and ${searchByName}`
					: searchByName;
			}

			return filterKoroneikiAccounts;
		},
		[filterKoroneikiAccounts]
	);

	const search = useSearchTerm(
		(variables) => refetch(variables),
		(searchTerm) => getFilterKoroneikiAccountsBySearch(searchTerm)
	);

	return {
		data,
		fetchMore,
		loading: koroneikiAccountsLoading || userAccountLoading,
		refetch,
		search,
	};
}
