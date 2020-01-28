package singha.com.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import singha.com.service.SBoardService;
import singha.com.util.SFileUtil;
import singha.com.util.SPageUtil;
import singha.com.vo.SBoardVO;

//사교모임 게시판 Controller
@Controller
public class SBoardController {

	@Autowired
	private SBoardService sboardS;
	
	//조회수증가
	@RequestMapping("/sBoard/sUh.do")
	public ModelAndView sUpdateHit(@RequestParam("sForiNo") int sForiNo,
			@RequestParam("nowPage") int nowPage,
			ModelAndView mv,HttpSession session) {
		System.out.println("요청함수 sUpdateHit()");
		//1.
		System.out.println("파라미터 sForiNo="+sForiNo);
		//2.
		sboardS.sUpdateHit(sForiNo, session);
		//3.
		//4.
		RedirectView rv = new RedirectView("../sBoard/sDetailView.do");
		rv.addStaticAttribute("sForiNo", sForiNo);
		rv.addStaticAttribute("nowPage", nowPage);
		
		mv.setView(rv);
		return mv;
	}
	
	//검색
	@RequestMapping("/sBoard/searchSBoard.do")
	public ModelAndView searchSBoard(HttpServletRequest req,
			@RequestParam(value="nowPage", defaultValue="1") int nowPage,
			ModelAndView mv){
		System.out.println("searchSBoard() 목록보기 요청함수");
		//1.
		String starget = req.getParameter("starget");
		String skeyword = req.getParameter("skeyword");
		
		//2.
		//2-1.페이징처리 필수정보-총게시물수, 유저가 보고싶은페이지(nowPage)
		SBoardVO vo = new SBoardVO();
		vo.setStarget(starget);
		vo.setSkeyword(skeyword);
		SPageUtil pageInfo = sboardS.getssearchInfo(nowPage, vo);
		//2-2.검색목록 조회
		List<SBoardVO> list = sboardS.getssearchList(pageInfo, starget, skeyword);
		System.out.println("abc");
		//3.
		req.setAttribute("starget", starget);
		req.setAttribute("skeyword", skeyword);
		req.setAttribute("SLIST", list); //사교 게시판 목록
		req.setAttribute("PINFO",pageInfo);	//페이지정보
		//4.
		mv.setViewName("sBoard/searchsList");
		return mv;
	} 
	
	//글삭제
	@RequestMapping("/sBoard/deleteSBoard.do")
	public ModelAndView deleteSBoard(ModelAndView mv,
			@RequestParam(value="sForiNo") int sForiNo,
			@RequestParam(value="spass") String spass,
			@RequestParam(value="nowPage") int nowPage) {
			System.out.println("요청함수 deleteSBoard()"+spass);
		//1.
		//2.
		SBoardVO vo = new SBoardVO();
		vo.setsForiNo(sForiNo);
		vo.setSpass(spass);
		int cnt = sboardS.deleteSBoard(vo);	
		//3.
		mv.addObject("sForiNo", sForiNo);
		mv.addObject("nowPage", nowPage);
		//4.
		RedirectView rv = null;
		if(cnt == 0) {
			rv = new RedirectView("../sBoard/sDetailView.do");
		}else {
			rv = new RedirectView("../sBoard/sList.do");
		}
			
		mv.setView(rv);
		return mv;
	}
	
	//수정 폼 보여주기
	@RequestMapping("/sBoard/sModifyFrm.do")
	public String sModifyFrm(HttpServletRequest request) {
		System.out.println("수정 폼 보여주기 sModifyFrm()");
		
		String strsForiNo  = request.getParameter("sForiNo");
		int sForiNo = Integer.parseInt(strsForiNo);
		String nowPage  = request.getParameter("nowPage");
		System.out.println("수정 폼 보여주기 sModifyFrm() sForiNo nowPage"+sForiNo+nowPage);
		SBoardVO vo = sboardS.getSBoardView(sForiNo);
		
		request.setAttribute("SBOARD", vo);
		request.setAttribute("nowPage", nowPage);
		
		return "sBoard/sModifyFrm";
	}
	//수정처리
	@RequestMapping("/sBoard/sModifyProc.do")
	public ModelAndView sModifyProc(SBoardVO vo, 
			ModelAndView mv) {
		System.out.println("요청함수 sModifyProc() vo="+ vo);
		System.out.println("파라미터 sForiNo=" + vo.getsForiNo());
		System.out.println("파라미터 nowpage="+vo.getNowPage());
		
		sboardS.updateSBoard(vo);
		
		RedirectView rv = new RedirectView("../sBoard/sDetailView.do");
		rv.setUrl("../sBoard/sDetailView.do");
		
		rv.addStaticAttribute("sForiNo", vo.getsForiNo());
		rv.addStaticAttribute("nowPage", vo.getNowPage());
						
		mv.setView(rv);
		return mv;
	}	
	//상세보기
	@RequestMapping("/sBoard/sDetailView.do")
	public ModelAndView sDetailView(ModelAndView mv,
			@RequestParam("sForiNo") int sForiNo,
			@RequestParam("nowPage") int nowPage) {
		System.out.println("상세보기 요청함수-detailView()   sForiNo="+sForiNo);
		
		SBoardVO vo = sboardS.getSBoardView(sForiNo);
		
		List<SBoardVO>	list = sboardS.getSFileInfo(sForiNo);
		
		//for(SBoardVO i: list) {
		System.out.println("컨트롤  list="+list.size());
		//}
		mv.addObject("SBOARD", vo);
		mv.addObject("SFILIST", list);
		mv.addObject("nowPage", nowPage);
		System.out.println("ABC="+list);
		mv.setViewName("sBoard/sDetailView");
		return mv;
	}

	//글쓰기
	@RequestMapping("/sBoard/sWriteProc.do")
	public ModelAndView sWriteProc(ModelAndView mv, 
			SBoardVO vo, HttpSession session) {
		System.out.println("글쓰기력폼 요청함수 sWriteProc()");
		//1.파라미터 받기
		//stitle=제목
		//scontent=내용
		//swriter=작성자명 => 세션이용 vo 세팅해야한다.
		//spass=비번, files=첨부파일 예정
		
		System.out.println("SBoardVO="+vo);
		
		//2.비즈니스로직
		//컨트롤러끝나면 임시폴더에 저장된 내용을 삭제하게 되므로
		//강제로 복사를 해둔다.
		//강제 복사 방법
		//1. 복사대상파일을 sFile클래스 만들기
		String sFpath = "e:\\upload";
		
		List list = new ArrayList(); //첨부파일정보를 담기 위한 변수
				
		for(int i=0; i<vo.getFiles().length; i++) {	
			try {
				
				String sForiName =vo.getFiles()[i].getOriginalFilename();
								
				String sFsaveName = SFileUtil.renameTo(sFpath, sForiName);
						
				File file = new File(sFpath, sFsaveName );
				
				vo.getFiles()[i].transferTo(file);
				
				//업로드된 파일의 정보를  Map으로 묶음
				HashMap<String,Object>  map = new HashMap();
				System.out.println("컨트롤러 sForiName="+sForiName);
				System.out.println("컨트롤러 file.length()="+file.length());
				map.put("sForiNo", vo.getSno());
				map.put("sFpath", sFpath);
				map.put("sForiName", sForiName);
				map.put("sFsaveName", sFsaveName);
				map.put("sFlength", file.length());
				
				System.out.println("컨트롤러 map.get(\"sForiNo\")="+map.get("sForiNo"));
				System.out.println("컨트롤러 map.get(\"sForiName\")="+map.get("sForiName"));
				System.out.println("컨트롤러 map.get(\"sFlength\")="+map.get("sFlength"));
				
				list.add(map);
				
			}catch(Exception e) {
				System.out.println("파일복사관련에러"+e);
				//e.printStackTrace();
			}//catch 마지막
			
		}//for문 마지막
		//글(sboard)입력
		sboardS.sInsertBoard(vo, session, list);
		
		//3.Model
		
		//4.View 
		
		//rv.setUrl("../sBoard/sList.do");
		//mv.setView(rv);
				
		RedirectView rv = new RedirectView("../sBoard/sList.do");
		
		mv.setView(rv); 
		return mv;
	}	
	//글입력 폼 보여주기
	@RequestMapping("/sBoard/sWriteFrm.do")
	public String sWriteFrm() {
		System.out.println("글입력폼 요청함수 sWriteFrm()");
		return "sBoard/sWriteFrm";
	}
	// 목록보기
	@RequestMapping("/sBoard/sList.do")
	public void sList(HttpServletRequest req,
			@RequestParam(value="nowPage", defaultValue="1") int nowPage){
		System.out.println("sList() 목록보기 요청함수");
		//1.파라미터받기

		
		//2.비즈니스로직 <-> Service <-> DAO <-> DB
		//2-1.페이징처리.. 필수정보-총게시물수, 유저가 보고싶은페이지(nowPage)
		SPageUtil pageInfo = sboardS.getPageInfo(nowPage);
		
		//2-2.글목록 조회
		List<SBoardVO> list = sboardS.getSBoardList(pageInfo);
		System.out.println("abc");
		//3.Model
		req.setAttribute("SLIST", list); //사교 게시판 목록
		req.setAttribute("PINFO",pageInfo);	//페이지정보
	}	
}