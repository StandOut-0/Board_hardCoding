<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<article class="board">
	<!-- 
			<h3>여러분</h3>
			<div class="summary">
				<p>저는 집에 가고 싶습니다.</p>
				<span>오늘 집가서 와인먹을껀데 뭐랑 같이 먹을지 추천해주세요.</span>
			</div> -->

	<form id="searchForm">
		<div id="searchArea" class="input-group mb-3">
		<a href="/board/list.do" class="btn border btn-light ms-2 d-inline ms-auto m-width-fit-content">
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-clockwise" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"/>
  <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"/>
</svg>
		</a>
			<select name="selectField" class="category form-select m-width-fit-content">
				<option value="title">제목</option>
				<option value="text">내용</option>
			</select> <label for="search" class="hidden">검색어 입력</label> <input
				class="form-control" type="text" name="searchText" id="search"
				placeholder="검색어를 입력하세요">

			<!-- 검색 버튼을 클릭 시 Ajax 요청 -->
			<button type="button" class="btn btn-light border" id="searchButton">검색</button>

			<a href="/board/sub.do" class="btn btn-dark ms-2 d-inline ms-auto">새
				글쓰기</a>
		</div>
	</form>
<script>
$(document).ready(function() {
    // 검색 버튼 클릭 시
    $("#searchButton").click(function() {
    	console.log("왜안뜸");
        // 선택된 값과 입력된 값을 가져옴
        var selectField = $("select[name='selectField']").val();
        var searchText = $("input[name='searchText']").val();

        // URL에 파라미터 추가
        var newUrl = window.location.pathname + "?selectField=" + selectField + "&searchText=" + searchText;
	
        // 새로고침
        window.location.href = newUrl;
    });
});
</script>

	<div class="table-container">
		<table class="table table-sm table-hover">
			<caption class="hidden">게시물 목록</caption>
			<thead class="table-light">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>등록일</th>
					<th>조회</th>
				</tr>
			</thead>
			<tbody class="table-group-divider">
				<c:forEach var="item" items="${list}">
					<tr onclick="window.location.href='/board/sub.do?id=' + ${item.id}">
						<td>${item.id}</td>
						<td>${item.title}</td>
						<td>${item.writeDate}</td>
						<td>${item.count}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="pagination d-flex justify-content-center gap-2">
		<span class="prev btn btn-sm btn-dark" onclick="goToPage(${currentPage - 2})">이전</span>

		<c:forEach var="page" begin="1" end="${totalPages}">
			<span class="btn btn-sm btn-light" onclick="goToPage(${page - 1})" id="page${page}">${page}</span>
		</c:forEach>
		
		<span class="next btn btn-sm btn-dark" onclick="goToPage(${currentPage + 1})">다음</span>
	</div>

	<script type="text/javascript">
	// 페이지 클릭 시 해당 페이지로 이동하는 함수
    function goToPage(page) {
		
	    const queryParams = new URLSearchParams(window.location.search);
	    queryParams.delete('currentPage');
	    
	 	page = Math.min(Math.max(page, 0), ${totalPages} - 1);
	    queryParams.set('currentPage', page);
	    const newUrl = "/board/list.do?" + queryParams.toString();
	
	    // 페이지 이동
	    window.location.href = newUrl;
	}
    
    
    // 현재 페이지에 해당하는 버튼에 active 클래스 추가
    $(document).ready(function () {
        const urlParams = new URLSearchParams(window.location.search);
        const currentPage = parseInt(urlParams.get('currentPage')) || 0;
        $(".pagination .btn-light").removeClass("active");
        $("#page"+(currentPage + 1)).addClass("active");
    });

</script>


	<div class="space80"></div>
</article>