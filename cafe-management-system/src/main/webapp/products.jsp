<%@ page contentType="text/html;charset=UTF-8" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head><title>Products</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head><body><jsp:include page="nav.jsp"/>
<main class="container"><h1>Products</h1><c:if test="${param.success == '1'}"><p class="success">Product saved successfully.</p></c:if>
<section class="panel"><h2>Add product</h2><form method="post" action="${pageContext.request.contextPath}/products" class="form-grid">
<input name="name" placeholder="Product name" required maxlength="100"><input name="category" placeholder="Category" required maxlength="60">
<input name="price" type="number" min="0.01" step="0.01" placeholder="Price in ₹" required><label class="check"><input type="checkbox" name="available" checked> Available</label><button>Add product</button></form></section>
<section class="panel"><h2>Product list</h2><div class="table-wrap"><table><thead><tr><th>ID</th><th>Name</th><th>Category</th><th>Price</th><th>Available</th><th>Action</th></tr></thead><tbody>
<c:forEach var="p" items="${products}"><tr><td>${p.id}</td><td><c:out value="${p.name}"/></td><td><c:out value="${p.category}"/></td><td>₹ ${p.price}</td><td>${p.available ? 'Yes' : 'No'}</td><td class="row-actions">
<form method="post" action="${pageContext.request.contextPath}/products" class="edit-form">
<input type="hidden" name="id" value="${p.id}">
<input name="name" value="<c:out value='${p.name}'/>" required aria-label="Product name">
<input name="category" value="<c:out value='${p.category}'/>" required aria-label="Category">
<input name="price" type="number" min="0.01" step="0.01" value="${p.price}" required aria-label="Price">
<label class="check"><input type="checkbox" name="available" ${p.available ? 'checked' : ''}> Available</label>
<button>Update</button></form>
<form method="post" action="${pageContext.request.contextPath}/products" onsubmit="return confirm('Delete this product?')"><input type="hidden" name="action" value="delete"><input type="hidden" name="id" value="${p.id}"><button class="danger">Delete</button></form></td></tr></c:forEach>
<c:if test="${empty products}"><tr><td colspan="6">No products found.</td></tr></c:if></tbody></table></div><p class="muted">You can add, edit and delete products. A product already used in an order cannot be deleted because the order history references it.</p></section></main></body></html>