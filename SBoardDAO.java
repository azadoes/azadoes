package singha.com.dao;

import java.util.ArrayList;
import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import singha.com.vo.SBoardVO;

//이 클래스는 사교모임 게시판 DB관련작업
public class SBoardDAO extends SqlSessionDaoSupport {
	
	@Autowired
	private SqlSessionTemplate session;
		
	//검색에 따른 전체게시물 수 조회
	public int ssearchCnt(SBoardVO vo) {
		int cnt = session.selectOne("ssearchCnt",vo);
		return cnt;
	}	
	//검색
	public ArrayList<SBoardVO> getssearchList(SBoardVO vo) {
		ArrayList<SBoardVO> list = (ArrayList)session.selectList("sboard.sboardSearch", vo);
		return list;
	}	
	//글삭제 sboard
	public int deleteSBoard(SBoardVO vo) {
		int cnt = session.update("sboard.deletesboard", vo);
		
		return cnt;
	}	
	//수정처리 sboard
	public void updateSBoard(SBoardVO vo) {
		session.update("sboard.updatesboard", vo);
		System.out.println("aaaaa"+vo);
	}	
	//조회수증가
	public void sUpdateHit(int sForiNo) {
		session.update("sboard.supdatehit", sForiNo);
		
	}	
	//sboard에서 데이터조회
	public SBoardVO getSBoardView(int sForiNo) {
		SBoardVO vo = (SBoardVO)session.selectOne("sboard.getSBoardView", sForiNo);	
		return vo;
		}	
	//해당게시물의 첨부파일정보를 SFileInfo에서 테이터 조회
	public List<SBoardVO> getSFileInfo(int sForiNo){
	System.out.println("dao  getSFileInfo()의  sForiNo="+sForiNo);
	List<SBoardVO> list = session.selectList("sboard.getSFileInfo",sForiNo);
	System.out.println("dao  getSFileInfo()의 list="+list);
	return list;
	}	
	//글(sboard)입력
	public void sInsertBoard(SBoardVO vo,String kind) {
		
		if(kind.equals("sboard")) {
			//Sboard테이블에 글쓴이,제목,내용,비번 입력
			session.insert("sboard.sInsertBoard", vo);
		}else if(kind.equals("sfinfo")){
			//SfileInfo테이블에 첨부파일정보 입력
			session.insert("sboard.sInsertFileInfo", vo);
		}
	}
	//전체게시물수조회
	public int getSTotalCnt() {
		int cnt = session.selectOne("sboard.stotalCnt");
		return cnt;
	}
	//글목록 조회
	public List<SBoardVO> getSBoardList(SBoardVO vo) {
		System.out.println("DAO 사교모임게시판 글목록 조회 getSBoardList()");
		//SqlSession session = this.getSqlSession();
		 List<SBoardVO> list =session.selectList("sboard.sboardlist",vo);
		 System.out.println("DAO 리스트 개수="+ list.size()); 
		 return list;
	}
	
}