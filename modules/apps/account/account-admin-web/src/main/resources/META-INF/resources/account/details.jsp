<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
AccountDisplay accountDisplay = (AccountDisplay)request.getAttribute(AccountWebKeys.ACCOUNT_DISPLAY);

String backURL = ParamUtil.getString(request, "backURL", String.valueOf(renderResponse.createRenderURL()));

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(backURL);

renderResponse.setTitle((accountDisplay == null) ? LanguageUtil.get(request, "add-account") : LanguageUtil.format(request, "edit-x", accountDisplay.getName(), false));
%>

<portlet:actionURL name="/account_admin/edit_account" var="editAccountURL" />

<liferay-frontend:edit-form
	action="<%= editAccountURL %>"
>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (accountDisplay == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="accountEntryId" type="hidden" value='<%= (accountDisplay == null) ? "0" : String.valueOf(accountDisplay.getAccountId()) %>' />

	<liferay-frontend:edit-form-body>
		<h2 class="sheet-title">
			<%= LanguageUtil.get(request, "information") %>
		</h2>

		<liferay-frontend:fieldset-group>
			<liferay-util:include page="/account/account_display_data.jsp" servletContext="<%= application %>" />

			<liferay-util:include page="/account/domains.jsp" servletContext="<%= application %>" />
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" />

		<aui:button href="<%= backURL %>" type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>