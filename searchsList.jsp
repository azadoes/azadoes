<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

//Tiles 구조 정의로 공통 레이아웃 작성

<style>
img {
	display: block;
  margin-left: auto;
  margin-right: auto;
  width: 75%; 
}
.dtitle {
	color:black;
	font-size: 12px;
  margin: auto;
  width: 75%;
  border:;
  padding: 3px;
  font-weight: bold;
}
.dateplace {
	color:black;
  font-size: 11px;
  margin: auto;
  width: 75%;
  border:;
  padding: 2px;
}
#freehr{
    background-color:#7c86f7;
    height:1px;
    }
</style>	
<script>
	$(document).ready(function(){
		$("#btn1").click(function(){
			$(location).attr("href","../sBoard/sWriteFrm.do");
		});
		//검색하기 클릭시 id="ssearchBtn"
		$("#ssearchBtn").click(function(){
			var word = $("#skeyword").val();
			//필수입력
			if(word.length==0 || word==""){
				alert("검색어를 입력해주세요");
				$("#skeyword").focus();
				return;
			}
			$("#ssearchFrm").submit();
		});
	});
	$(document).ready(function(){
		//id="listViewBtnS" value="목록보기"
		$("#listViewBtnS").on("click",function(){
			$(location).attr("href","../sBoard/sList.do?nowPage=${nowPage}");
		});
 	});
</script>
</head>
<body>
<table class="table table-sm">
<thead>
<h4>모임 / 자기계발</h4>
<hr id="freehr">
<br>
</table>
</br>  
<div class="container-fluid">
	<div class="row">
		<c:forEach items="${SLIST}" var="temp">
		<div class="col-md-3">
			<a href="../sBoard/sDetailView.do?sForiNo=${temp.sno}&nowPage=${PINFO.nowPage}"><img alt="Bootstrap Image Preview" src=${temp.simglink} /></a>
			<div class="dtitle">
				<a href="../sBoard/sDetailView.do?sForiNo=${temp.sno}&nowPage=${PINFO.nowPage}">${temp.stitle}</a>
			</div>
			<div class="dateplace">
				${temp.sday} | ${temp.splace}
				| ${temp.shit} 
			</div>
		</div>
		</c:forEach>
	</div>
</div>	
	<form name="frm1" id="frm1" action="../sBoard/sWriteFrm.do" method="get">
	  <table align="center" width="740">
	  	<tr>
	  		<td align="center">
	  			<input type="button" id="btn1" class="btn btn-primary" value="모임글 작성하기" />
	  		</td>
	  	</tr>
	  </table>
  </form>
  <table align="center" width="740">
  	<tr>
  		<td align="center">
			<%-- 이전버튼 :링크없는 경우--%>
  			<c:if test="${PINFO.nowPage eq 1}">[prev]</c:if>
  			<%-- 이전버튼 :링크있는 경우--%>
  			<c:if test="${PINFO.nowPage ne 1}">
  				<a href="../sBoard/searchSBoard.do?nowPage=${PINFO.nowPage-1}&starget=${starget}&skeyword=${skeyword}">[prev]</a>
  			</c:if>  			
  			<%-- 반복해서 페이지출력 --%> 
  			<c:forEach begin="${PINFO.startPage}"  
  			           end="${PINFO.endPage}" var="page">
	  			<a href="../sBoard/searchSBoard.do?nowPage=${page}&starget=${starget}&skeyword=${skeyword}">[${page}]</a>
  			</c:forEach>
  			<%-- 다음버튼:링크없는 경우 --%>
  			<c:if test="${PINFO.nowPage eq PINFO.totalPage}">[next]</c:if>
  			<%-- 다음버튼:링크있는 경우 --%>
  			<c:if test="${PINFO.nowPage ne PINFO.totalPage}">
  				<a href="../sBoard/searchSBoard.do?nowPage=${PINFO.nowPage+1}&starget=${starget}&skeyword=${skeyword}">[next]</a>
  			</c:if>
  		</td>
  	</tr>
  </table>
 <%-- 검색기능 --%>
  <form name="ssearchFrm" id="ssearchFrm"
        action="../sBoard/searchSBoard.do" method="GET">
	  <table align="center" width="740">
	  	<tr>
	  		<td align="center">
	  			<%-- 검색대상 --%>
	  			<select name="starget" id="starget">
	  				<option value="stitle">제목</option>
	  				<option value="scontent">내용</option>
	  				<option value="swriter">작성자</option>
	  				<option value="both">제목+내용</option>
	  			</select>
	  			<%-- 검색키워드 --%>
	  			<input type="text" name="skeyword" id="skeyword" required="required"/>
	  			<%-- 검색하기버튼 --%>
	  			<input type="button" id="ssearchBtn" value="검색하기" />
	  		</td>
	  	</tr>
	  </table>
	</form>
 <%-- 기타기능 - 목록보기 --%>
	<br/>		
	<table align="center" width="800">
	  <tr align="center">
		<td>
			<input type="button" id="listViewBtnS" value="목록보기"/>
		</td>
	</tr>
	</table>	 
</body>
</html>
