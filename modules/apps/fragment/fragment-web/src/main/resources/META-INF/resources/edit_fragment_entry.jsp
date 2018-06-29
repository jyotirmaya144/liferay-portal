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
FragmentEntry fragmentEntry = fragmentDisplayContext.getFragmentEntry();

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(fragmentDisplayContext.getRedirect());

String title = fragmentDisplayContext.getFragmentEntryTitle();

if (WorkflowConstants.STATUS_DRAFT == fragmentEntry.getStatus()) {
	title = fragmentDisplayContext.getFragmentEntryTitle() + " (" + LanguageUtil.get(request, WorkflowConstants.getStatusLabel(fragmentEntry.getStatus())) + ")";
}

renderResponse.setTitle(title);
%>

<liferay-ui:error exception="<%= FragmentEntryContentException.class %>">

	<%
	FragmentEntryContentException fece = (FragmentEntryContentException)errorException;
	%>

	<c:choose>
		<c:when test="<%= Validator.isNotNull(fece.getMessage()) %>">
			<%= fece.getMessage() %>
		</c:when>
		<c:otherwise>
			<liferay-ui:message key="fragment-html-is-invalid" />
		</c:otherwise>
	</c:choose>
</liferay-ui:error>

<portlet:actionURL name="/fragment/edit_fragment_entry" var="editFragmentEntryURL" />

<aui:form action="<%= editFragmentEntryURL %>" enctype="multipart/form-data" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= fragmentDisplayContext.getRedirect() %>" />
	<aui:input name="fragmentEntryId" type="hidden" value="<%= fragmentDisplayContext.getFragmentEntryId() %>" />
	<aui:input name="fragmentCollectionId" type="hidden" value="<%= fragmentDisplayContext.getFragmentCollectionId() %>" />
	<aui:input name="cssContent" type="hidden" value="" />
	<aui:input name="htmlContent" type="hidden" value="" />
	<aui:input name="jsContent" type="hidden" value="" />
	<aui:input name="status" type="hidden" value="<%= fragmentEntry.getStatus() %>" />

	<aui:model-context bean="<%= fragmentEntry %>" model="<%= FragmentEntry.class %>" />

	<aui:input autoFocus="<%= false %>" name="name" placeholder="name" type="hidden" />

	<div id="<portlet:namespace />fragmentEditor"></div>
</aui:form>

<liferay-portlet:renderURL plid="<%= fragmentDisplayContext.getRenderLayoutPlid() %>" var="renderFragmentEntryURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcRenderCommandName" value="/fragment/render_fragment_entry" />
	<portlet:param name="fragmentEntryId" value="<%= String.valueOf(fragmentDisplayContext.getFragmentEntryId()) %>" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL plid="<%= fragmentDisplayContext.getRenderLayoutPlid() %>" var="previewFragmentEntryURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcRenderCommandName" value="/fragment/preview_fragment_entry" />
</liferay-portlet:renderURL>

<aui:script require="fragment-web/js/FragmentEditor.es as FragmentEditor, metal-dom/src/all/dom as dom, frontend-js-web/liferay/toast/commands/OpenToast.es as toastCommands">
	var cssInput = document.getElementById('<portlet:namespace />cssContent');
	var htmlInput = document.getElementById('<portlet:namespace />htmlContent');
	var jsInput = document.getElementById('<portlet:namespace />jsContent');
	var wrapper = document.getElementById('<portlet:namespace />fragmentEditor');

	var fragmentEditor = new FragmentEditor.default(
		{
			currentURL: '<%= currentURL %>',
			draft: <%= WorkflowConstants.STATUS_DRAFT == fragmentEntry.getStatus() %>,
			fragmentDisplayContextFragmentCollectionId: '<%= fragmentDisplayContext.getFragmentCollectionId() %>',
			fragmentDisplayContextFragmentEntryId: '<%= fragmentDisplayContext.getFragmentEntryId() %>',
			fragmentDisplayContextRedirect: '<%= fragmentDisplayContext.getRedirect() %>',
			fragmentEntryStatus: '<%= fragmentEntry.getStatus() %>',
			initialCSS: '<%= HtmlUtil.escapeJS(fragmentDisplayContext.getCssContent()) %>',
			initialHTML: '<%= HtmlUtil.escapeJS(fragmentDisplayContext.getHtmlContent()) %>',
			initialJS: '<%= HtmlUtil.escapeJS(fragmentDisplayContext.getJsContent()) %>',
			namespace: '<portlet:namespace />',
			previewFragmentEntryURL: '<%= previewFragmentEntryURL %>',
			renderFragmentEntryURL: '<%= renderFragmentEntryURL %>',
			spritemap: '<%= themeDisplay.getPathThemeImages() %>/lexicon/icons.svg'
		},
		wrapper
	);

	var publishButtonClickHandler = dom.delegate(
		document.body,
		'click',
		'#<portlet:namespace />publishButton',
		function(event) {
			event.preventDefault();

			if (!fragmentEditor.isHtmlValid()) {
				toastCommands.openToast(
					{
						message: '<liferay-ui:message key="fragment-html-is-invalid" />',
						title: '<liferay-ui:message key="error" />',
						type: 'danger'
					}
				);

				return;
			}

			dom.toElement('#<portlet:namespace />status').value = '<%= WorkflowConstants.STATUS_APPROVED %>';

			var content = fragmentEditor.getContent();

			cssInput.value = content.css;
			htmlInput.value = content.html;
			jsInput.value = content.js;

			submitForm(document.querySelector('#<portlet:namespace />fm'));
		}
	);

	function destroyFragmentEditor () {
		fragmentEditor.dispose();
		publishButtonClickHandler.removeListener();

		Liferay.detach('destroyPortlet', destroyFragmentEditor);
	}

	Liferay.on('destroyPortlet', destroyFragmentEditor);
</aui:script>