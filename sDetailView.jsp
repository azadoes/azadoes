<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

//Tiles 구조 정의로 공통 레이아웃 작성

<style>
	th {
	  text-align: center;
	  height: 35px;
	}
	td {
  height: 35px;
	}
	#freehr{
    background-color:#7c86f7;
    height:1px;
    }
</style>
<script>
	$(document).ready(function(){
		//id="listViewBtnS" value="목록보기"
		$("#applyBtn").on("click",function(){
			$(location).attr("href","http://bit.ly/2N7MdqD");
		});
		
		$("#listViewBtnS").on("click",function(){
			$(location).attr("href","../sBoard/sList.do?nowPage=${nowPage}");
		});
		
		// id="modifyBtn"   value="수 정"
		$("#modifyBtnA").on("click",function(){
			$(location).attr("href",
					"../sBoard/sModifyFrm.do?sForiNo=${SBOARD.sno}&nowPage=${nowPage}");
		});
		
		// id="delBtnS"   value="삭 제"
		$("#delBtnWriter").on("click",function(){
			var spass = prompt("삭제를 위한 비밀번호를 입력하세요","");
			$("#delSpass").val(spass);
			$("#delFrm").attr("action","../sBoard/deleteSBoard.do");
			$("#delFrm").submit();			
		});
		
		$("#delBtnAdmin").on("click",function(){
			alert("관리자 모드로 삭제하시겠습니까?");
			alert("정말로 삭제하시겠습니까?");
			var spass = "${SBOARD.spass}"
			$("#delSpass").val(spass);
			$("#delFrm").attr("action","../sBoard/deleteSBoard.do");
			$("#delFrm").submit();
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
 <div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-3">
				</div>
				<div class="col-md-6">
					<div class="row">
						<div class="col-md-12">
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<form>	
							<table align="center" width="800">
									<tbody>
										<tr>
											<th align="center" colspan="2" height="30">
												<h2>${SBOARD.stitle}</h2>
											</th>
										</tr>
										<tr">
											<td align="center" colspan="2" height="17">
											</td>
										</tr>
										<tr>
											<th>모임 제목 </th>
											<td>
												${SBOARD.stitle}
											</td>
										</tr>
										<tr>
											<th width="150">작성자</th>
											<td width="650">
											${SBOARD.swriter}
											</td>
										</tr>
										<tr>
											<th>모임 종류</th>
											<td>
							  				${SBOARD.stype}
							  			</td>
										</tr>
										<tr>
											<th>모임 내용</th>
											<td>
												${SBOARD.scontent}
											</td>
										</tr>
										<tr>
											<th>모임 비용</th>
											<td>
							  				${SBOARD.scost}
							  			</td>
										</tr>
										<tr>
											<th>모임 지역</th>
											<td>
												${SBOARD.splace}
											</td>
										</tr>
										<tr>
											<th>모임 장소</th>
											<td>
												${SBOARD.sdplace}
											</td>
										</tr>
										<tr>
											<th>모임 날짜</th>
											<td>
												${SBOARD.sday}
											</td>
										</tr>
										<tr>
											<th>모임 시간</th>
											<td>
												${SBOARD.sdday}
											</td>
										</tr>
										<tr>
											<th>모임 이미지</th>
											<td>
												<img src="${SBOARD.simglink}" />
											</td>
										</tr>
						 				<tr>
											<td align="center" colspan="2">
												
											</td>
										</tr>
									<c:forEach items="${SFILIST}" var="temp">
										<tr>
											<th>첨부파일</th>
											<td>${temp.sForiName}(${temp.sFlength} byte)</td>
										</tr>
									</c:forEach>
									
									<c:if test="${sessionScope.MID ne null }">
									  <tr>
											<td align="center" colspan="2">
												<input type="button" class="btn btn-primary" value="모임참여 신청" id="applyBtn"/>
											</td>
										</tr>
									</c:if>	
									</tbody>
								</table>
								</form>
								<br/>
								<%-- 기타기능 - 목록보기, 수정하기, 삭제하기 --%>
							<table align="center" width="800">
								<tr align="center">
									<td>
										<input type="button" id="listViewBtnS" value="목록보기"/>
										<%-- 글작성한 유저만 수정/삭제 권한 가지고 있다 --%>
										<c:choose>
										<c:when test="${sessionScope.MID eq SBOARD.swriter}">
											<input type="button" id="modifyBtnA"   value="수 정"/>
											<input type="button" id="delBtnWriter" value="삭 제"/>
										</c:when>
										<c:when test="${sessionScope.MID eq 'admin'}">
											<input type="button" id="modifyBtnA"   value="수 정"/>
											<input type="button" id="delBtnAdmin"  value="삭 제"/>
										</c:when>
										<c:otherwise>
										</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</table>		
						</div>
					</div>
				</div>
				<div class="col-md-3">
				</div>
			</div>
		</div>
	</div>
</div>
<%-- 삭제를 위한 임시폼  --%>
<form id="delFrm" action="../sBoard/deleteSBoard.do" method="POST">
	<input type="hidden" name="spass" id="delSpass" />
	<input type="hidden" name="sForiNo" id="delsForiNo" value="${SBOARD.sno}" />
	<input type="hidden" name="nowPage" id="delNowPage" value="${nowPage}" />
</form>	
</body>
</html>
							
														
											
