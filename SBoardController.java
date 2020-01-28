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

//�米���� �Խ��� Controller
@Controller
public class SBoardController {

	@Autowired
	private SBoardService sboardS;
	
	//��ȸ������
	@RequestMapping("/sBoard/sUh.do")
	public ModelAndView sUpdateHit(@RequestParam("sForiNo") int sForiNo,
			@RequestParam("nowPage") int nowPage,
			ModelAndView mv,HttpSession session) {
		System.out.println("��û�Լ� sUpdateHit()");
		//1.
		System.out.println("�Ķ���� sForiNo="+sForiNo);
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
	
	//�˻�
	@RequestMapping("/sBoard/searchSBoard.do")
	public ModelAndView searchSBoard(HttpServletRequest req,
			@RequestParam(value="nowPage", defaultValue="1") int nowPage,
			ModelAndView mv){
		System.out.println("searchSBoard() ��Ϻ��� ��û�Լ�");
		//1.
		String starget = req.getParameter("starget");
		String skeyword = req.getParameter("skeyword");
		
		//2.
		//2-1.����¡ó�� �ʼ�����-�ѰԽù���, ������ �������������(nowPage)
		SBoardVO vo = new SBoardVO();
		vo.setStarget(starget);
		vo.setSkeyword(skeyword);
		SPageUtil pageInfo = sboardS.getssearchInfo(nowPage, vo);
		//2-2.�˻���� ��ȸ
		List<SBoardVO> list = sboardS.getssearchList(pageInfo, starget, skeyword);
		System.out.println("abc");
		//3.
		req.setAttribute("starget", starget);
		req.setAttribute("skeyword", skeyword);
		req.setAttribute("SLIST", list); //�米 �Խ��� ���
		req.setAttribute("PINFO",pageInfo);	//����������
		//4.
		mv.setViewName("sBoard/searchsList");
		return mv;
	} 
	
	//�ۻ���
	@RequestMapping("/sBoard/deleteSBoard.do")
	public ModelAndView deleteSBoard(ModelAndView mv,
			@RequestParam(value="sForiNo") int sForiNo,
			@RequestParam(value="spass") String spass,
			@RequestParam(value="nowPage") int nowPage) {
			System.out.println("��û�Լ� deleteSBoard()"+spass);
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
	
	//���� �� �����ֱ�
	@RequestMapping("/sBoard/sModifyFrm.do")
	public String sModifyFrm(HttpServletRequest request) {
		System.out.println("���� �� �����ֱ� sModifyFrm()");
		
		String strsForiNo  = request.getParameter("sForiNo");
		int sForiNo = Integer.parseInt(strsForiNo);
		String nowPage  = request.getParameter("nowPage");
		System.out.println("���� �� �����ֱ� sModifyFrm() sForiNo nowPage"+sForiNo+nowPage);
		SBoardVO vo = sboardS.getSBoardView(sForiNo);
		
		request.setAttribute("SBOARD", vo);
		request.setAttribute("nowPage", nowPage);
		
		return "sBoard/sModifyFrm";
	}
	//����ó��
	@RequestMapping("/sBoard/sModifyProc.do")
	public ModelAndView sModifyProc(SBoardVO vo, 
			ModelAndView mv) {
		System.out.println("��û�Լ� sModifyProc() vo="+ vo);
		System.out.println("�Ķ���� sForiNo=" + vo.getsForiNo());
		System.out.println("�Ķ���� nowpage="+vo.getNowPage());
		
		sboardS.updateSBoard(vo);
		
		RedirectView rv = new RedirectView("../sBoard/sDetailView.do");
		rv.setUrl("../sBoard/sDetailView.do");
		
		rv.addStaticAttribute("sForiNo", vo.getsForiNo());
		rv.addStaticAttribute("nowPage", vo.getNowPage());
						
		mv.setView(rv);
		return mv;
	}	
	//�󼼺���
	@RequestMapping("/sBoard/sDetailView.do")
	public ModelAndView sDetailView(ModelAndView mv,
			@RequestParam("sForiNo") int sForiNo,
			@RequestParam("nowPage") int nowPage) {
		System.out.println("�󼼺��� ��û�Լ�-detailView()   sForiNo="+sForiNo);
		
		SBoardVO vo = sboardS.getSBoardView(sForiNo);
		
		List<SBoardVO>	list = sboardS.getSFileInfo(sForiNo);
		
		//for(SBoardVO i: list) {
		System.out.println("��Ʈ��  list="+list.size());
		//}
		mv.addObject("SBOARD", vo);
		mv.addObject("SFILIST", list);
		mv.addObject("nowPage", nowPage);
		System.out.println("ABC="+list);
		mv.setViewName("sBoard/sDetailView");
		return mv;
	}

	//�۾���
	@RequestMapping("/sBoard/sWriteProc.do")
	public ModelAndView sWriteProc(ModelAndView mv, 
			SBoardVO vo, HttpSession session) {
		System.out.println("�۾������ ��û�Լ� sWriteProc()");
		//1.�Ķ���� �ޱ�
		//stitle=����
		//scontent=����
		//swriter=�ۼ��ڸ� => �����̿� vo �����ؾ��Ѵ�.
		//spass=���, files=÷������ ����
		
		System.out.println("SBoardVO="+vo);
		
		//2.����Ͻ�����
		//��Ʈ�ѷ������� �ӽ������� ����� ������ �����ϰ� �ǹǷ�
		//������ ���縦 �صд�.
		//���� ���� ���
		//1. ������������ sFileŬ���� �����
		String sFpath = "e:\\upload";
		
		List list = new ArrayList(); //÷������������ ��� ���� ����
				
		for(int i=0; i<vo.getFiles().length; i++) {	
			try {
				
				String sForiName =vo.getFiles()[i].getOriginalFilename();
								
				String sFsaveName = SFileUtil.renameTo(sFpath, sForiName);
						
				File file = new File(sFpath, sFsaveName );
				
				vo.getFiles()[i].transferTo(file);
				
				//���ε�� ������ ������  Map���� ����
				HashMap<String,Object>  map = new HashMap();
				System.out.println("��Ʈ�ѷ� sForiName="+sForiName);
				System.out.println("��Ʈ�ѷ� file.length()="+file.length());
				map.put("sForiNo", vo.getSno());
				map.put("sFpath", sFpath);
				map.put("sForiName", sForiName);
				map.put("sFsaveName", sFsaveName);
				map.put("sFlength", file.length());
				
				System.out.println("��Ʈ�ѷ� map.get(\"sForiNo\")="+map.get("sForiNo"));
				System.out.println("��Ʈ�ѷ� map.get(\"sForiName\")="+map.get("sForiName"));
				System.out.println("��Ʈ�ѷ� map.get(\"sFlength\")="+map.get("sFlength"));
				
				list.add(map);
				
			}catch(Exception e) {
				System.out.println("���Ϻ�����ÿ���"+e);
				//e.printStackTrace();
			}//catch ������
			
		}//for�� ������
		//��(sboard)�Է�
		sboardS.sInsertBoard(vo, session, list);
		
		//3.Model
		
		//4.View 
		
		//rv.setUrl("../sBoard/sList.do");
		//mv.setView(rv);
				
		RedirectView rv = new RedirectView("../sBoard/sList.do");
		
		mv.setView(rv); 
		return mv;
	}	
	//���Է� �� �����ֱ�
	@RequestMapping("/sBoard/sWriteFrm.do")
	public String sWriteFrm() {
		System.out.println("���Է��� ��û�Լ� sWriteFrm()");
		return "sBoard/sWriteFrm";
	}
	// ��Ϻ���
	@RequestMapping("/sBoard/sList.do")
	public void sList(HttpServletRequest req,
			@RequestParam(value="nowPage", defaultValue="1") int nowPage){
		System.out.println("sList() ��Ϻ��� ��û�Լ�");
		//1.�Ķ���͹ޱ�

		
		//2.����Ͻ����� <-> Service <-> DAO <-> DB
		//2-1.����¡ó��.. �ʼ�����-�ѰԽù���, ������ �������������(nowPage)
		SPageUtil pageInfo = sboardS.getPageInfo(nowPage);
		
		//2-2.�۸�� ��ȸ
		List<SBoardVO> list = sboardS.getSBoardList(pageInfo);
		System.out.println("abc");
		//3.Model
		req.setAttribute("SLIST", list); //�米 �Խ��� ���
		req.setAttribute("PINFO",pageInfo);	//����������
	}	
}