<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<h1>${btn[1]}입니다</h1>


<form action="${contextPath}/board/write.do">

	<input type="text" name="title" value="${sub.title}" class="disabled form-control" /> <br>

	<textarea class="disabled form-control" name="text">
${sub.text}
	</textarea>
	<br> <span>작성일: ${sub.writeDate}</span> <br> <span>조회수:
		${sub.count}</span> <br> <a href="${contextPath}/board/list.do">목록</a> <br>

	<%-- <a href="${contextPath}/board/${btn[0]}.do?id=${sub.id}" >${btn[1]}</a> --%>
	<c:choose>
		<c:when test="${btn[0] eq 'write'}">
			<button type="submit" id="writeBtn">글쓰기</button>
		</c:when>

		<c:when test="${btn[0] eq 'save'}">
			<a href="javascript:void(0);" id="saveBtn">저장하기</a>
			<script>
				document
						.getElementById("saveBtn")
						.addEventListener(
								"click",
								function() {
									var title = document
											.querySelector("input[type='text']").value;
									var text = document
											.querySelector("textarea").value;
									var id = ${sub.id};

									$.post("${contextPath}/board/save.do", {
										id : id,
										title : title,
										text : text
									}, function(response) {
										console.log(response);
										$('body').append(response);
									});
								});
			</script>
		</c:when>
		<c:otherwise>
			<a class="btn btn-primary" href="${contextPath}/board/${btn[0]}.do?id=${sub.id}">수정하기</a>
		</c:otherwise>
	</c:choose>
<a href="${contextPath}/board/delete.do?id=${sub.id}"class="btn btn-secondary">삭제하기</a>
</form>






