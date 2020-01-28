package singha.com.dao;

import java.util.ArrayList;
import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import singha.com.vo.SBoardVO;

//�� Ŭ������ �米���� �Խ��� DB�����۾�
public class SBoardDAO extends SqlSessionDaoSupport {
	
	@Autowired
	private SqlSessionTemplate session;
		
	//�˻��� ���� ��ü�Խù� �� ��ȸ
	public int ssearchCnt(SBoardVO vo) {
		int cnt = session.selectOne("ssearchCnt",vo);
		return cnt;
	}	
	//�˻�
	public ArrayList<SBoardVO> getssearchList(SBoardVO vo) {
		ArrayList<SBoardVO> list = (ArrayList)session.selectList("sboard.sboardSearch", vo);
		return list;
	}	
	//�ۻ��� sboard
	public int deleteSBoard(SBoardVO vo) {
		int cnt = session.update("sboard.deletesboard", vo);
		
		return cnt;
	}	
	//����ó�� sboard
	public void updateSBoard(SBoardVO vo) {
		session.update("sboard.updatesboard", vo);
		System.out.println("aaaaa"+vo);
	}	
	//��ȸ������
	public void sUpdateHit(int sForiNo) {
		session.update("sboard.supdatehit", sForiNo);
		
	}	
	//sboard���� ��������ȸ
	public SBoardVO getSBoardView(int sForiNo) {
		SBoardVO vo = (SBoardVO)session.selectOne("sboard.getSBoardView", sForiNo);	
		return vo;
		}	
	//�ش�Խù��� ÷������������ SFileInfo���� ������ ��ȸ
	public List<SBoardVO> getSFileInfo(int sForiNo){
	System.out.println("dao  getSFileInfo()��  sForiNo="+sForiNo);
	List<SBoardVO> list = session.selectList("sboard.getSFileInfo",sForiNo);
	System.out.println("dao  getSFileInfo()�� list="+list);
	return list;
	}	
	//��(sboard)�Է�
	public void sInsertBoard(SBoardVO vo,String kind) {
		
		if(kind.equals("sboard")) {
			//Sboard���̺� �۾���,����,����,��� �Է�
			session.insert("sboard.sInsertBoard", vo);
		}else if(kind.equals("sfinfo")){
			//SfileInfo���̺� ÷���������� �Է�
			session.insert("sboard.sInsertFileInfo", vo);
		}
	}
	//��ü�Խù�����ȸ
	public int getSTotalCnt() {
		int cnt = session.selectOne("sboard.stotalCnt");
		return cnt;
	}
	//�۸�� ��ȸ
	public List<SBoardVO> getSBoardList(SBoardVO vo) {
		System.out.println("DAO �米���ӰԽ��� �۸�� ��ȸ getSBoardList()");
		//SqlSession session = this.getSqlSession();
		 List<SBoardVO> list =session.selectList("sboard.sboardlist",vo);
		 System.out.println("DAO ����Ʈ ����="+ list.size()); 
		 return list;
	}
	
}