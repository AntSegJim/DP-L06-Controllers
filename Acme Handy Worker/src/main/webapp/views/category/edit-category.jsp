<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<security:authorize access="hasRole('ADMIN')">

<form:form action="category/administrator/edit.do" modelAttribute="category">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:label path="name">
	<spring:message code="category.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />
	
	<form:label path="parent">
	<spring:message code="category.parent"/>:
	</form:label>
	<form:select path="parent">
		<form:options items="${categories}" itemLabel="name" itemValue="id"/>
	</form:select>
	<br />
	
	<form:label path="soon">
	<spring:message code="category.son"/>:
	</form:label>
	<display:table name="${category.soon }" id="row">
	<display:column property="name" titleKey="category.name" />
	</display:table>
	
	<br />
	
	<input type="submit" name="save" 
	value="<spring:message code="category.save" />" />

<input type="button" name="cancel" value="<spring:message code="category.cancel" />"
			onclick="javascript: relativeRedir('category/administrator/list.do');" />

</form:form>

</security:authorize>
