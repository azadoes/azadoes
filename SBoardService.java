package singha.com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import singha.com.dao.SBoardDAO;
import singha.com.util.SPageUtil;
import singha.com.vo.SBoardVO;

public class SBoardService {
	
	@Autowired
	private SBoardDAO sBdao;
	
	//글삭제 sboard
	public int deleteSBoard(SBoardVO vo) {
		int cnt = sBdao.deleteSBoard(vo);
		return cnt;
	}	
	//수정처리 sboard
	public void updateSBoard(SBoardVO vo) {
		sBdao.updateSBoard(vo);
		System.out.println("sBdao.updateSBoard(vo)");
	}	
	//조회수증가
	public void sUpdateHit(int sForiNo,HttpSession session) {
		boolean issHit = false;
		
		//세션 정보를 토대로하여 조회수 증가여부 결정
		ArrayList list =
				(ArrayList)session.getAttribute("SHITLIST");
		//list가 존재하지 않으면 게시물상세보기를 한적이 없다
		if( list==null || list.size()==0) {
			issHit = true;
			list = new ArrayList();
			list.add(sForiNo);
			session.setAttribute("SHITLIST",  list );		
		}else if( !list.contains(sForiNo) ){ //list가 존재하면 게시물상세보기를 한적이 있다.
			//기록된 번호에 지금 조회한 게시글 번호가 없는지 확인 
			issHit = true;
			list.add(sForiNo);
			session.setAttribute("SHITLIST",  list );	
		}else {//상세보기를 한적있음. 게시글 번호 조회한 적 있음
			issHit = false;
		}		
		if(issHit==true) { //조회수 증가 해야함 경우
			sBdao.sUpdateHit(sForiNo);
		}
			
	}	
	//sboard에서 데이터조회
	public SBoardVO getSBoardView(int sForiNo) {
		SBoardVO vo = sBdao.getSBoardView(sForiNo);
		return vo;
	}
	//해당게시물의 첨부파일정보를 SFileInfo에서 테이터 조회
	public List<SBoardVO> getSFileInfo(int sForiNo){
		System.out.println("서비스  getSFileInfo함수 sForiNo="+sForiNo);
		List<SBoardVO>	list = sBdao.getSFileInfo(sForiNo);
		System.out.println("서비스   list.size()="+list.size());
		return list;
	}
	//글(sboard)입력
	public void sInsertBoard(SBoardVO vo, 
			HttpSession session, List list) {
		vo.setSwriter((String)session.getAttribute("MID"));
		
		sBdao.sInsertBoard(vo,"sboard");
		System.out.println("넘버"+vo.getSno());
		System.out.println("확인하기   vo.getSno(="+vo.getSno());
		
		for(int i=0; i<list.size(); i++) { //첨부파일의 개수만큼 반복	
		
			HashMap<String,Object> map = (HashMap)list.get(i);
			vo.setsForiNo( vo.getSno() );
			vo.setsFpath((String)map.get("sFpath"));
			vo.setsForiName((String)map.get("sForiName") );
			vo.setsFsaveName((String)map.get("sFsaveName") );
			System.out.println("(Long)map.get('sFlength')"+map.get("sFlength") );
			vo.setsFlength( (Long)map.get("sFlength") );
			System.out.println("서비스~~~~~~~~~~~~~ vo="+vo);
			sBdao.sInsertBoard(vo,"sfinfo");
			}
	}
	//검색에 따른 페이징 처리
	public SPageUtil  getssearchInfo(int nowPage,SBoardVO vo) {
		
		int totalCount = sBdao.ssearchCnt(vo);
		
		SPageUtil pageInfo
			= new SPageUtil(nowPage,totalCount,8,3); 
				
		return pageInfo;
	}
	//검색목록 조회
	public ArrayList<SBoardVO> getssearchList(SPageUtil pageInfo, 
			String starget, String skeyword) {
				  
				int start = (pageInfo.getNowPage()-1)*pageInfo.getLineCount()+1;
				int end   = start + pageInfo.getLineCount()-1; //5+2-1
				SBoardVO vo = new SBoardVO();
				vo.setStart(start);
				vo.setEnd(end);
				vo.setStarget(starget);
				vo.setSkeyword(skeyword);
				ArrayList<SBoardVO> result = sBdao.getssearchList(vo);
				
				return result;
			}	
	//페이징 처리
	public SPageUtil  getPageInfo(int nowPage) {
		int totalCount = sBdao.getSTotalCnt();
		SPageUtil pageInfo
			= new SPageUtil(nowPage,totalCount,8,3); 
		//한 페이지당 8건의 게시물 출력 하도록
		//페이지 3개씩 출력 -> [1]-[2]-[3]
		return pageInfo;
	}
	//글목록 조회
	public List<SBoardVO>  getSBoardList(SPageUtil pageInfo) {
		System.out.println("Service 글목록 조회-getSBoardList()");
		  
		int start = (pageInfo.getNowPage()-1)*pageInfo.getLineCount()+1;
		int end   = start + pageInfo.getLineCount()-1; //5+2-1
		SBoardVO vo = new SBoardVO();
		vo.setStart(start);
		vo.setEnd(end);
		System.out.println("vo.getStart00()/vo.getEnd()="+vo.getStart()+"/"+vo.getEnd());
		List<SBoardVO> result = sBdao.getSBoardList(vo);
		System.out.println("result="+result);
		return result;
	}
}