<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>List Customers</title>
<link type="text/css"
	rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

	<div id="wrappper">
		<div id="header">
			<h2>CRM - Customer Relationship Management</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		
			<input type="button" value="Add Customer"
				onClick="window.location.href='showFormForAdd'; return false;"
				class="add-button"
			/>
				
			<!--  add a search box -->
			<form:form action="search" method="POST">
				Search customer: <input type="text" name="theSearchName" />
				
				<input type="submit" value="Search" class="add-button" />
			</form:form>
			
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
			
				<c:forEach var="tmpCustomer" items="${customers}" > 
				
				<!-- This will construct a URL embedding the customer id -->
				<c:url var="updateLink" value="/customer/showFormForUpdate">
					<c:param name="customerId" value="${tmpCustomer.id}"></c:param>
				</c:url>
				
				<c:url var="deleteLink" value="/customer/delete">
					<c:param name="customerId" value="${tmpCustomer.id}"></c:param>
				</c:url>
				
				<tr> 
					<td>${tmpCustomer.firstName}</td>
					<td>${tmpCustomer.lastName}</td>
					<td>${tmpCustomer.email}</td>
					<td>
						<a href="${updateLink}">Update</a> 
						| 
						<a href="${deleteLink}"
							onClick="return confirm('Are you sure you want to delete this customer?');">
							Delete</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			
		</div>
	</div>

</body>
</html>