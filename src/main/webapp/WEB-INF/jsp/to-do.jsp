<%@ include file = "common/header.jspf" %>
<%@ include file = "common/navigation.jspf" %>
<div class = "container">
<table class = "table table-striped">
<caption>List of your To-Do's</caption>
	<thead>
		<tr>
		<th>Description</th>
		<th>TargetDate</th>
		<th>Is it Done?</th>
		<th>Update</th>
		<th>Delete</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach  var = "item" items = "${todo}">
		<tr>
		<td><c:out value="${item.desc}" /></td>
		<td><fmt:formatDate value = "${item.targetDate}" pattern = "dd/MM/yyyy" /></td>
		<td><c:out value="${item.done}"/></td>
		<td><a type = "button" class = "btn btn-success" href = "/update-todo?id=${item.id}">Update</a></td>
		<td><a type = "button" class = "btn btn-warning" href = "/delete-todo?id=${item.id}">Delete</a></td>
		</tr>
		</c:forEach>
	</tbody>	
</table>
<br>
<br>
<a href = "add-todo"> ADD TODO </a>

</div>
<%@ include file = "common/footer.jspf" %>