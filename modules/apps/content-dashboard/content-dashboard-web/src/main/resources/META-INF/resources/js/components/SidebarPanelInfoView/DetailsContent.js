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

import ClayLabel from '@clayui/label';
import PropTypes from 'prop-types';
import React from 'react';

import CollapsibleSection from './CollapsibleSection';
import ItemLanguages from './ItemLanguages';
import ItemVocabularies from './ItemVocabularies';
import PreviewActionsDescriptionSection from './PreviewActionsDescriptionSection';
import SpecificFields from './SpecificFields';
import {
	getCategoriesCountFromVocabularies,
	groupVocabulariesBy,
} from './utils/taxonomiesUtils';

const DetailsContent = ({
	classPK,
	createDate,
	description,
	downloadURL,
	fetchSharingButtonURL,
	formatDate,
	handleError,
	languageTag = 'en',
	modifiedDate,
	preview,
	specificFields,
	tags = [],
	title,
	viewURLs = [],
	vocabularies,
}) => {
	const specificItems = Object.values(specificFields);

	const [publicVocabularies, internalVocabularies] = groupVocabulariesBy({
		array: Object.values(vocabularies),
		key: 'isPublic',
		value: true,
	});

	const internalCategoriesCount = getCategoriesCountFromVocabularies(
		internalVocabularies
	);

	const publicCategoriesCount = getCategoriesCountFromVocabularies(
		publicVocabularies
	);

	const showTaxonomies =
		!!internalCategoriesCount || !!publicCategoriesCount || !!tags?.length;

	return (
		<>
			{Liferay.FeatureFlags['LPS-161013'] && (
				<>
					<PreviewActionsDescriptionSection
						description={description}
						downloadURL={downloadURL}
						fetchSharingButtonURL={fetchSharingButtonURL}
						handleError={handleError}
						preview={preview}
						title={title}
					/>

					{showTaxonomies && (
						<CollapsibleSection
							expanded={true}
							title={Liferay.Language.get('categorization')}
						>
							{!!publicCategoriesCount && (
								<ItemVocabularies
									title={Liferay.Language.get(
										'public-categories'
									)}
									vocabularies={publicVocabularies}
								/>
							)}

							{!!internalCategoriesCount && (
								<ItemVocabularies
									cssClassNames="c-mt-4"
									title={Liferay.Language.get(
										'internal-categories'
									)}
									vocabularies={internalVocabularies}
								/>
							)}

							{!!tags.length && (
								<div className="c-mb-4 sidebar-dl sidebar-section">
									<h5 className="c-mb-1 font-weight-semi-bold">
										{Liferay.Language.get('tags')}
									</h5>

									<p>
										{tags.map((tag) => (
											<ClayLabel
												className="c-mb-2 c-mr-2"
												displayType="secondary"
												key={tag}
												large
											>
												{tag}
											</ClayLabel>
										))}
									</p>
								</div>
							)}
						</CollapsibleSection>
					)}
				</>
			)}

			<CollapsibleSection title={Liferay.Language.get('details')}>
				<div className="sidebar-section">
					<SpecificFields
						fields={specificItems}
						languageTag={languageTag}
					/>

					<div
						className="c-mb-4 sidebar-dl sidebar-section"
						key="creation-date"
					>
						<h5 className="c-mb-1 font-weight-semi-bold">
							{Liferay.Language.get('creation-date')}
						</h5>

						<p className="text-secondary">
							{formatDate(createDate, languageTag)}
						</p>
					</div>

					<div
						className="c-mb-4 sidebar-dl sidebar-section"
						key="modified-date"
					>
						<h5 className="c-mb-1 font-weight-semi-bold">
							{Liferay.Language.get('modified-date')}
						</h5>

						<p className="text-secondary">
							{formatDate(modifiedDate, languageTag)}
						</p>
					</div>

					<div className="c-mb-4 sidebar-dl sidebar-section" key="id">
						<h5 className="c-mb-1 font-weight-semi-bold">
							{Liferay.Language.get('id')}
						</h5>

						<p className="text-secondary">{classPK}</p>
					</div>
				</div>

				{!!viewURLs.length && <ItemLanguages urls={viewURLs} />}
			</CollapsibleSection>
		</>
	);
};

DetailsContent.defaultProps = {
	languageTag: 'en-US',
};

DetailsContent.propTypes = {
	classPK: PropTypes.string.isRequired,
	createDate: PropTypes.string.isRequired,
	formatDate: PropTypes.func.isRequired,
	modifiedDate: PropTypes.string.isRequired,
	specificFields: PropTypes.object.isRequired,
	viewURLs: PropTypes.array.isRequired,
};

export default DetailsContent;
