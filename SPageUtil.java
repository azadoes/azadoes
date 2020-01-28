package singha.com.util;

//페이징 처리 관련 기능 제공 클래스
public class SPageUtil {

	//변수
	private int nowPage;	//보고싶은 페이지
	private int totalCount;	//총 게시물수
	
	private int lineCount;	//한 페이지당 보여주고 싶은 게시물 수
	private int pageGroup;	//페이지 이동 수
	
	//계산에 의해서 만들어지는 데이터를 담기 위한 변수
	private int totalPage;	//총 페이지 수
	private int startPage;	//시작 페이지
	private int endPage;	// 끝 페이지
	
	//생성자  
	public SPageUtil(int nowPage, int totalCount) {
		this(nowPage,totalCount,5,3);
	}
	
	//외부에서 new PageUtil(nowPage,totalCount,5,3)한다면
	//한 페이지당 5건의 게시물
	//페이지는 3개씩 출력 예) [1][2][3]
	public SPageUtil(int nowPage, int totalCount, 
			        int lineCount, int pageGroup) {
		this.nowPage    = nowPage;   //보고싶은 페이지
		this.totalCount = totalCount;//총 게시물수
		this.lineCount = lineCount;	 //한 페이지당 보여주고 싶은 게시물 수
		this.pageGroup = pageGroup;  //페이지 이동 수
		calcTotalPage();
		calcStartPage();
		calcEndPage();
	}
	
	//함수
	//총 페이지 수 계산
	public void calcTotalPage() {
		//나머지 %
		//총페이지수=나머지가 0인경우의 몫
		//총페이지수=나머지가 0이 아닌 경우+1
		//변수=(조건)? 참값:거짓값;
		totalPage = (totalCount%lineCount==0)? 
				totalCount/lineCount : totalCount/lineCount+1;
		//totalPage = totalCount/lineCount; // 100/10
	}
	
	//시작 페이지 계산
	public void calcStartPage() {
		//nowPage를 가운데 두고 처리
		//페이지는 3개씩 출력 
		//[1][2][3] nowPage가 2이면   시작페이지는1
		//[4][5][6] nowPage가 5        시작페이지는4
				
		startPage = nowPage - (pageGroup/2);
		      //   1 = 2-(3/2)   2-(1)
		      //  -1 = 1-(5/2)   1-(2)   -1=1-2
		if(startPage<=0) {
			startPage = 1;
		}
	}
	
	//끝 페이지 계산
	public void calcEndPage() {
		endPage = startPage+(pageGroup-1);
		// 3=1+(3-1)
		// 5=1+(5-1)
		      
		if(endPage >= totalPage) {
			endPage = totalPage;
		}
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getLineCount() {
		return lineCount;
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}

	public int getPageGroup() {
		return pageGroup;
	}

	public void setPageGroup(int pageGroup) {
		this.pageGroup = pageGroup;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	
}









