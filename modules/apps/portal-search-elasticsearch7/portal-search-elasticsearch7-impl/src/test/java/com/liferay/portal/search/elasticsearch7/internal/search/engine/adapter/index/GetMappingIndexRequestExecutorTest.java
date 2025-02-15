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

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.index;

import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.engine.adapter.index.GetMappingIndexRequest;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.elasticsearch.client.indices.GetMappingsRequest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Dylan Rebelak
 */
public class GetMappingIndexRequestExecutorTest {

	@ClassRule
	public static LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		_elasticsearchFixture = new ElasticsearchFixture();

		_elasticsearchFixture.setUp();
	}

	@After
	public void tearDown() throws Exception {
		_elasticsearchFixture.tearDown();
	}

	@Test
	public void testIndexRequestTranslation() {
		GetMappingIndexRequest getMappingIndexRequest =
			new GetMappingIndexRequest(
				new String[] {_INDEX_NAME}, _MAPPING_NAME);

		GetMappingIndexRequestExecutorImpl getMappingIndexRequestExecutorImpl =
			new GetMappingIndexRequestExecutorImpl();

		ReflectionTestUtil.setFieldValue(
			getMappingIndexRequestExecutorImpl, "_elasticsearchClientResolver",
			_elasticsearchFixture);

		GetMappingsRequest getMappingsRequest =
			getMappingIndexRequestExecutorImpl.createGetMappingsRequest(
				getMappingIndexRequest);

		Assert.assertArrayEquals(
			new String[] {_INDEX_NAME}, getMappingsRequest.indices());
	}

	private static final String _INDEX_NAME = "test_request_index";

	private static final String _MAPPING_NAME = "testMapping";

	private ElasticsearchFixture _elasticsearchFixture;

}