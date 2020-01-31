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
 	$(function(){
		$("#sWriteBtn").click(function(){		
			//무결성 검사. 글쓴이, 제목,내용 필수 입력
			var st=$("#stitle").val();
			var sc=$("#scontent").val();
			var sp2=$("#splace").val();
			var sp3=$("#sdplace").val();
			var sp=$("#spass").val();
			var sd=$("#sday").val();
			var sd2=$("#sdday").val();
						
			if(st=="" || st.length==0){
				alert("제목을 입력하세요");
				$("#stitle").focus();
				return;
			}
			if(document.getElementById("stype1").checked != true && document.getElementById("stype2").checked != true 
					&& document.getElementById("stype3").checked != true
					&& document.getElementById("stype4").checked != true){
				alert("모임종류를 선택해주세요.");
				return;
				}						
			if(sc=="" || sc.length==0){
				alert("내용을 입력하세요");
				$("#scontent").focus();
				return;
			}
			if(document.getElementById("sc1").checked != true && document.getElementById("sc2").checked != true){
				alert("무료/유료를 선택해주세요.");
				return;
				}	
			if(sp2=="" || sp2.length==0){
				alert("모임지역을 입력하세요.");
				$("#splace").focus();
				return;
			}
			if(sp3=="" || sp3.length==0){
				alert("모임장소를 입력하세요.");
				$("#sdplace").focus();
				return;
			}
			if(sp=="" || sp.length==0){
				alert("비밀번호를 입력하세요");
				$("#spass").focus();
				return;
			}
			if(sd=="" || sd.length==0){
				alert("날짜를 선택하세요");
				$("#sday").focus();
				return;
			}		
			if(sd2=="" || sd2.length==0){
				alert("시간을 선택하세요");
				$("#sdday").focus();
				return;
			}
			if(document.getElementById("simg1").checked != true && document.getElementById("simg2").checked != true 
					&& document.getElementById("simg3").checked != true){
				alert("이미지 종류를 선택해주세요.");
				return;  
			}
					
			//서브밋
			$("#swritefrm").submit();	 
	});
		//첨부파일갯수제한용도+추가할 때마다 1씩 증가할 변수선언
		var cnt = 1;
		
		//파일찾기 동적추가 id="addBtn" 
		$("#addBtn").click(function(){
			//id는  unique해야한다
			cnt++;
			if(cnt==4){
				alert("첨부파일은 최대 3개까지 입니다");
				cnt=3;
				return;//함수종료
			}
			
			//내용
			var tr ='<tr>';
					tr+='<td colspan="2">';
					tr+='<input type="file" name="files" id="files'+cnt+'"/>';
					tr+='</td>';
					tr+='</tr>';
			
			//특정위치에 추가
			//선택자.before(추가할내용): 선택한 요소의 앞에 (형제/자매)추가
			/*append() - Inserts content at the end of the selected elements
				prepend() - Inserts content at the beginning of the selected elements
				after() - Inserts content after the selected elements
				before() - Inserts content before the selected elements*/
			$("#copy").before(tr);
		});
		
		//파일찾기 동적삭제 id="delBtn"
		$("#delBtn").click(function(){
			//최소한 1개는 유지할 것이므로
			if( cnt==1 ){
				alert("한개는 최소한 있어야 합니다");
				return;
			}
			
			//선택자.remove()는 선택한 요소를 포함한 자손요소를 삭제
 			/*참고	
 				선택자.empty()는 선택한 요소는 제거되지 않고 자식요소를 삭제
 				- Removes the child elements from the selected element
 			*/
 			//선택자.parent()   : 선택자의  direct 부모요소
 			//선택자.parents()  : 선택자의  direct 부모포함한 모든 조상요소
 			//선택자.parents(요소): 선택자의  특정 조상요소
			var tr =  $("#files"+cnt).parents("tr");
			$(tr).remove();
			cnt--; //삭제버튼 클릭할  때 마다 1씩 감소  
		});
	

		$("#listViewBtnS").on("click",function(){
			$(location).attr("href","../sBoard/sList.do?nowPage=${nowPage}");
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
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-3">
				</div>
				<div class="col-md-6">
					<div class="row">
						<div class="col-md-12">
						 <form id="swritefrm" action="../sBoard/sWriteProc.do"
				   encType="multipart/form-data" method="post">
				   
					  		<table align="center" width="800">
									<tbody>
										<tr>
											<th align="center" colspan="2" height="30">
												<h3>모임글 작성 양식</h3>
											</th>
										</tr>
										<tr>
											<td align="center" colspan="2" height="30">												
											</td>
										</tr>
										<tr>
											<th>모임 제목</th>
											<td>
												<input type="text" name="stitle" id="stitle" />
											</td>
										</tr>
										<tr>
											<th width="150">작성자</th>
											<td width="650">
										${ sessionScope.MID }
											</td>
										</tr>
										<tr>
											<th>모임 종류</th>
											<td>
							  				<input type="radio" name="stype" id="stype1" value="소모임/친목행사" />소모임/친목행사
							  				<input type="radio" name="stype" id="stype2" value="취미활동"/>취미활동
							  				<input type="radio" name="stype" id="stype3" value="교육"/>교육
							  				<input type="radio" name="stype" id="stype4" value="기타"/>기타
							  			</td>
										</tr>
										<tr>
											<th>모임 내용</th>
											<td>
												<textarea cols="80" rows="5" name="scontent" id="scontent" ></textarea>
											</td>
										</tr>
										<tr>
											<th>모임 비용</th>
											<td>
							  				<input type="radio" name="scost" id="sc1" value="무료"/>무료
							  				<input type="radio" name="scost" id="sc2" value="유료"/>유료
							  			</td>
										</tr>
										<tr>
											<th>모임 지역</th>
											<td>
												<input type="text" name="splace" id="splace" />
											</td>
										</tr>
										<tr>
											<th>모임 장소</th>
											<td>
												<input type="text" name="sdplace" id="sdplace" />
											</td>
										</tr>
										<tr>
											<th>비밀번호</th>
											<td>
												<input type="password" name="spass" id="spass"  />
											</td>
										</tr>
										<tr>
											<th>모임 날짜</th>
											<td>
												<input type="date" name="sday" id="sday" />
											</td>
										</tr>
										<tr>
											<th>모임 시간</th>
											<td>
												<input type="time" name="sdday" id="sdday" />
											</td>
										</tr>
										<tr>
											<th>썸네일 이미지</th>
											<td>
							  				<input type="radio" name="simglink" id="simg1" value="https://bit.ly/3aehPVh" />아기
							  				<input type="radio" name="simglink" id="simg2" value="https://bit.ly/382sC38" />강아지
							  				<input type="radio" name="simglink" id="simg3" value="https://bit.ly/2NrH0db"/>꽃							  				
							  			</td>
										</tr>
										<tr>
											<th>첨부파일</th>
											<td>
												<input type="button" value="추가" id="addBtn"/>
												<input type="button" value="삭제" id="delBtn"/>
											</td>
										<tr>
											<td colspan="2">
												<input type="file" name="files" id="files" />
											</td>	
										</tr>
										<tr id="copy">
											<td align="center" colspan="2" height="30">
												
											</td>
										</tr>
										<tr>
											<td align="center" colspan="2">
												<input type="button" class="btn btn-primary" value="작성완료" id="sWriteBtn" />
											</td>
										</tr>
										<tr>
											<td align="center" colspan="2" height="30">												
											</td>
										</tr>
										</tbody>
								</table>
							</form>
						</div>
					</div>
				</div>
				<div class="col-md-3">
				</div>
			</div>
		</div>
	</div>
</div>
<%-- 기타기능 - 목록보기%>
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
