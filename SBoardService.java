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
	
	//�ۻ��� sboard
	public int deleteSBoard(SBoardVO vo) {
		int cnt = sBdao.deleteSBoard(vo);
		return cnt;
	}	
	//����ó�� sboard
	public void updateSBoard(SBoardVO vo) {
		sBdao.updateSBoard(vo);
		System.out.println("sBdao.updateSBoard(vo)");
	}	
	//��ȸ������
	public void sUpdateHit(int sForiNo,HttpSession session) {
		boolean issHit = false;
		
		//���� ������ �����Ͽ� ��ȸ�� �������� ����
		ArrayList list =
				(ArrayList)session.getAttribute("SHITLIST");
		//list�� �������� ������ �Խù��󼼺��⸦ ������ ����
		if( list==null || list.size()==0) {
			issHit = true;
			list = new ArrayList();
			list.add(sForiNo);
			session.setAttribute("SHITLIST",  list );		
		}else if( !list.contains(sForiNo) ){ //list�� �����ϸ� �Խù��󼼺��⸦ ������ �ִ�.
			//��ϵ� ��ȣ�� ���� ��ȸ�� �Խñ� ��ȣ�� ������ Ȯ�� 
			issHit = true;
			list.add(sForiNo);
			session.setAttribute("SHITLIST",  list );	
		}else {//�󼼺��⸦ ��������. �Խñ� ��ȣ ��ȸ�� �� ����
			issHit = false;
		}		
		if(issHit==true) { //��ȸ�� ���� �ؾ��� ���
			sBdao.sUpdateHit(sForiNo);
		}
			
	}	
	//sboard���� ��������ȸ
	public SBoardVO getSBoardView(int sForiNo) {
		SBoardVO vo = sBdao.getSBoardView(sForiNo);
		return vo;
	}
	//�ش�Խù��� ÷������������ SFileInfo���� ������ ��ȸ
	public List<SBoardVO> getSFileInfo(int sForiNo){
		System.out.println("����  getSFileInfo�Լ� sForiNo="+sForiNo);
		List<SBoardVO>	list = sBdao.getSFileInfo(sForiNo);
		System.out.println("����   list.size()="+list.size());
		return list;
	}
	//��(sboard)�Է�
	public void sInsertBoard(SBoardVO vo, 
			HttpSession session, List list) {
		vo.setSwriter((String)session.getAttribute("MID"));
		
		sBdao.sInsertBoard(vo,"sboard");
		System.out.println("�ѹ�"+vo.getSno());
		System.out.println("Ȯ���ϱ�   vo.getSno(="+vo.getSno());
		
		for(int i=0; i<list.size(); i++) { //÷�������� ������ŭ �ݺ�	
		
			HashMap<String,Object> map = (HashMap)list.get(i);
			vo.setsForiNo( vo.getSno() );
			vo.setsFpath((String)map.get("sFpath"));
			vo.setsForiName((String)map.get("sForiName") );
			vo.setsFsaveName((String)map.get("sFsaveName") );
			System.out.println("(Long)map.get('sFlength')"+map.get("sFlength") );
			vo.setsFlength( (Long)map.get("sFlength") );
			System.out.println("����~~~~~~~~~~~~~ vo="+vo);
			sBdao.sInsertBoard(vo,"sfinfo");
			}
	}
	//�˻��� ���� ����¡ ó��
	public SPageUtil  getssearchInfo(int nowPage,SBoardVO vo) {
		
		int totalCount = sBdao.ssearchCnt(vo);
		
		SPageUtil pageInfo
			= new SPageUtil(nowPage,totalCount,8,3); 
				
		return pageInfo;
	}
	//�˻���� ��ȸ
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
	//����¡ ó��
	public SPageUtil  getPageInfo(int nowPage) {
		int totalCount = sBdao.getSTotalCnt();
		SPageUtil pageInfo
			= new SPageUtil(nowPage,totalCount,8,3); 
		//�� �������� 8���� �Խù� ��� �ϵ���
		//������ 3���� ��� -> [1]-[2]-[3]
		return pageInfo;
	}
	//�۸�� ��ȸ
	public List<SBoardVO>  getSBoardList(SPageUtil pageInfo) {
		System.out.println("Service �۸�� ��ȸ-getSBoardList()");
		  
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