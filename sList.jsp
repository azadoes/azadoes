<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

//Tiles 구조 정의로 공통 레이아웃 작성

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
			alert("검색어를 입력해 주세요");
			$("#skeyword").focus();
			return;
		}
		
	$("#ssearchFrm").submit();
	});

});
</script>
<style>
	#sImg {
		display: block;
	  	margin-left: auto;
	  	margin-right: auto;
	  	width: 75%; 
	}
	.dt {
		font-size: 14px;
	  	margin: auto;
	  	width: 75%;
	  	border:;
	  	padding: 3px;
	  	font-weight: bold;
	}
	.dp {
		font-size: 12px;
		margin: auto;
	  	width: 75%;		  	
	  	padding: 2px;
	}
	.dp2 {
		font-size: 12px;
		margin: auto;
	  	width: 75%;		  	
	  	padding: 2px;
	}
	#freehr {
	    background-color:#7c86f7;
	    height:1px;
	}
</style>
</head>
<body>	
<table class="table table-sm">	
	<h4>모임 / 자기계발</h4>
	<hr id="freehr"/>
</table>			  
<!-- sList.jsp 바둑판식 배열 : 레이아웃을 가로 4등분 -->
<div class="container-fluid">
	<div class="row">
		<c:forEach items="${SLIST}" var="temp">
			<div class="col-md-3">
				<a href="../sBoard/sUh.do?sForiNo=${temp.sno}&nowPage=${PINFO.nowPage}">
					<img id="sImg" alt="Bootstrap Image Preview" src=${temp.simglink} />
				</a>
				<div class="dt">
				<a href="../sBoard/sUh.do?sForiNo=${temp.sno}&nowPage=${PINFO.nowPage}">
					${temp.stitle}
				</a>
				</div>
				<div class="dp">
					${temp.sday} | ${temp.splace}
					| ◎ ${temp.shit} | ${temp.stype} 
				</div>
				<div class="dp2"></div>
			</div>		
		</c:forEach>		
	</div>
</div>	
<form name="frm1" id="frm1" action="../sBoard/sWriteFrm.do" method="get">
	<table align="center"  width="740">
		<tr>
	  		<td align="center">
	  			<input type="button" id="btn1" class="btn btn-primary" value="모임글 작성하기" />
	  		</td>
		</tr>
	</table>
</form>
<%-- 페이징처리  [prev][1][2][3][next] //페이지정보--%>
 <table align="center" width="740">
 	<tr>
 		<td align="center">
 		  <%-- 이전버튼 :링크없는 경우--%>
 			<c:if test="${PINFO.nowPage eq 1}">[prev]</c:if>
 			<%-- 이전버튼 :링크있는 경우--%>
 			<c:if test="${PINFO.nowPage ne 1}">
 				<a href="../sBoard/sList.do?nowPage=${PINFO.nowPage-1}">[prev]</a>
 			</c:if>
 			<%-- 반복해서 페이지출력 --%> 
 			<c:forEach begin="${PINFO.startPage}"  
 			           end="${PINFO.endPage}" var="page">
         <a href="../sBoard/sList.do?nowPage=${page}">[${page}]</a>
 			</c:forEach>
 			<%-- 다음버튼:링크없는 경우 --%>
 			<c:if test="${PINFO.nowPage eq PINFO.totalPage}">[next]</c:if>
 			<%-- 다음버튼:링크있는 경우 --%>
 			<c:if test="${PINFO.nowPage ne PINFO.totalPage}">
 				<a href="../sBoard/sList.do?nowPage=${PINFO.nowPage+1}">[next]</a>
 			</c:if>
 		</td>
 	</tr>
 </table>  
<%-- 검색기능 --%>
 <form name="ssearchFrm" id="ssearchFrm"
       action="../sBoard/searchSBoard.do" method="GET">
  <table align="center"  width="740">
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
</body>
</html>
