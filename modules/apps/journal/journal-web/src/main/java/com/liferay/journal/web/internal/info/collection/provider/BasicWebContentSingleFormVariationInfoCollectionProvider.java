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

package com.liferay.journal.web.internal.info.collection.provider;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.info.collection.provider.CollectionQuery;
import com.liferay.info.collection.provider.ConfigurableInfoCollectionProvider;
import com.liferay.info.collection.provider.FilteredInfoCollectionProvider;
import com.liferay.info.collection.provider.InfoCollectionProvider;
import com.liferay.info.collection.provider.SingleFormVariationInfoCollectionProvider;
import com.liferay.info.field.InfoField;
import com.liferay.info.field.type.SelectInfoFieldType;
import com.liferay.info.field.type.TextInfoFieldType;
import com.liferay.info.filter.InfoFilter;
import com.liferay.info.filter.KeywordsInfoFilter;
import com.liferay.info.form.InfoForm;
import com.liferay.info.localized.InfoLocalizedValue;
import com.liferay.info.localized.SingleValueInfoLocalizedValue;
import com.liferay.info.pagination.InfoPage;
import com.liferay.info.pagination.Pagination;
import com.liferay.info.sort.Sort;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.web.internal.util.JournalSearcherUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portlet.asset.util.comparator.AssetTagNameComparator;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	enabled = false, immediate = true, service = InfoCollectionProvider.class
)
public class BasicWebContentSingleFormVariationInfoCollectionProvider
	implements ConfigurableInfoCollectionProvider<JournalArticle>,
			   FilteredInfoCollectionProvider<JournalArticle>,
			   SingleFormVariationInfoCollectionProvider<JournalArticle> {

	@Override
	public InfoPage<JournalArticle> getCollectionInfoPage(
		CollectionQuery collectionQuery) {

		List<JournalArticle> articles =
			JournalSearcherUtil.searchJournalArticles(
				searchContext -> _populateSearchContext(
					collectionQuery, searchContext));

		return InfoPage.of(
			articles, collectionQuery.getPagination(), articles.size());
	}

	@Override
	public InfoForm getConfigurationInfoForm() {
		return InfoForm.builder(
		).infoFieldSetEntry(
			_getAssetTagsInfoField()
		).infoFieldSetEntry(
			InfoField.builder(
			).infoFieldType(
				TextInfoFieldType.INSTANCE
			).namespace(
				StringPool.BLANK
			).name(
				Field.TITLE
			).labelInfoLocalizedValue(
				InfoLocalizedValue.localize(getClass(), "title")
			).localizable(
				true
			).build()
		).build();
	}

	@Override
	public String getFormVariationKey() {
		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		DDMStructure ddmStructure = _ddmStructureLocalService.fetchStructure(
			serviceContext.getScopeGroupId(),
			_portal.getClassNameId(JournalArticle.class.getName()),
			"BASIC-WEB-CONTENT", true);

		return String.valueOf(ddmStructure.getStructureId());
	}

	@Override
	public String getLabel(Locale locale) {
		return _language.get(locale, "basic-web-content");
	}

	@Override
	public List<InfoFilter> getSupportedInfoFilters() {
		return Arrays.asList(new KeywordsInfoFilter());
	}

	private InfoField<?> _getAssetTagsInfoField() {
		List<SelectInfoFieldType.Option> options = new ArrayList<>();

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		List<AssetTag> assetTags = new ArrayList<>(
			_assetTagLocalService.getGroupTags(
				serviceContext.getScopeGroupId()));

		assetTags.sort(new AssetTagNameComparator(true));

		for (AssetTag assetTag : assetTags) {
			options.add(
				new SelectInfoFieldType.Option(
					new SingleValueInfoLocalizedValue<>(assetTag.getName()),
					assetTag.getName()));
		}

		InfoField.FinalStep<?> finalStep = InfoField.builder(
		).infoFieldType(
			SelectInfoFieldType.INSTANCE
		).namespace(
			StringPool.BLANK
		).name(
			Field.ASSET_TAG_NAMES
		).attribute(
			SelectInfoFieldType.MULTIPLE, true
		).attribute(
			SelectInfoFieldType.OPTIONS, options
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(getClass(), "tag")
		).localizable(
			true
		);

		return finalStep.build();
	}

	private SearchContext _populateSearchContext(
		CollectionQuery collectionQuery, SearchContext searchContext) {

		searchContext.setAndSearch(true);
		searchContext.setAttributes(
			HashMapBuilder.<String, Serializable>put(
				Field.STATUS, WorkflowConstants.STATUS_APPROVED
			).put(
				"ddmStructureKey", "BASIC-WEB-CONTENT"
			).put(
				"head", true
			).put(
				"latest", true
			).build());

		Optional<Map<String, String[]>> configurationOptional =
			collectionQuery.getConfigurationOptional();

		Map<String, String[]> configuration = configurationOptional.orElse(
			Collections.emptyMap());

		String[] assetTagNames = configuration.get(Field.ASSET_TAG_NAMES);

		if (ArrayUtil.isNotEmpty(assetTagNames) &&
			Validator.isNotNull(assetTagNames[0])) {

			searchContext.setAssetTagNames(assetTagNames);
		}

		String[] title = configuration.get(Field.TITLE);

		if (ArrayUtil.isNotEmpty(title) && Validator.isNotNull(title[0])) {
			searchContext.setAttribute(
				Field.getLocalizedName(
					LocaleUtil.getSiteDefault(), Field.TITLE),
				title[0]);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		searchContext.setCompanyId(serviceContext.getCompanyId());

		Pagination pagination = collectionQuery.getPagination();

		searchContext.setEnd(pagination.getEnd());

		searchContext.setGroupIds(
			new long[] {serviceContext.getScopeGroupId()});

		Optional<KeywordsInfoFilter> keywordsInfoFilterOptional =
			collectionQuery.getInfoFilterOptional(KeywordsInfoFilter.class);

		if (keywordsInfoFilterOptional.isPresent()) {
			KeywordsInfoFilter keywordsInfoFilter =
				keywordsInfoFilterOptional.get();

			searchContext.setKeywords(keywordsInfoFilter.getKeywords());
		}

		Optional<Sort> sortOptional = collectionQuery.getSortOptional();

		if (sortOptional.isPresent()) {
			Sort sort = sortOptional.get();

			searchContext.setSorts(
				new com.liferay.portal.kernel.search.Sort(
					sort.getFieldName(),
					com.liferay.portal.kernel.search.Sort.LONG_TYPE,
					sort.isReverse()));
		}
		else {
			searchContext.setSorts(
				new com.liferay.portal.kernel.search.Sort(
					Field.MODIFIED_DATE,
					com.liferay.portal.kernel.search.Sort.LONG_TYPE, true));
		}

		searchContext.setStart(pagination.getStart());

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		return searchContext;
	}

	@Reference
	private AssetTagLocalService _assetTagLocalService;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

}