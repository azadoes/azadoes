package singha.com.util;

//����¡ ó�� ���� ��� ���� Ŭ����
public class SPageUtil {

	//����
	private int nowPage;	//������� ������
	private int totalCount;	//�� �Խù���
	
	private int lineCount;	//�� �������� �����ְ� ���� �Խù� ��
	private int pageGroup;	//������ �̵� ��
	
	//��꿡 ���ؼ� ��������� �����͸� ��� ���� ����
	private int totalPage;	//�� ������ ��
	private int startPage;	//���� ������
	private int endPage;	// �� ������
	
	//������  
	public SPageUtil(int nowPage, int totalCount) {
		this(nowPage,totalCount,5,3);
	}
	
	//�ܺο��� new PageUtil(nowPage,totalCount,5,3)�Ѵٸ�
	//�� �������� 5���� �Խù�
	//�������� 3���� ��� ��) [1][2][3]
	public SPageUtil(int nowPage, int totalCount, 
			        int lineCount, int pageGroup) {
		this.nowPage    = nowPage;   //������� ������
		this.totalCount = totalCount;//�� �Խù���
		this.lineCount = lineCount;	 //�� �������� �����ְ� ���� �Խù� ��
		this.pageGroup = pageGroup;  //������ �̵� ��
		calcTotalPage();
		calcStartPage();
		calcEndPage();
	}
	
	//�Լ�
	//�� ������ �� ���
	public void calcTotalPage() {
		//������ %
		//����������=�������� 0�ΰ���� ��
		//����������=�������� 0�� �ƴ� ���+1
		//����=(����)? ����:������;
		totalPage = (totalCount%lineCount==0)? 
				totalCount/lineCount : totalCount/lineCount+1;
		//totalPage = totalCount/lineCount; // 100/10
	}
	
	//���� ������ ���
	public void calcStartPage() {
		//nowPage�� ��� �ΰ� ó��
		//�������� 3���� ��� 
		//[1][2][3] nowPage�� 2�̸�   ������������1
		//[4][5][6] nowPage�� 5        ������������4
				
		startPage = nowPage - (pageGroup/2);
		      //   1 = 2-(3/2)   2-(1)
		      //  -1 = 1-(5/2)   1-(2)   -1=1-2
		if(startPage<=0) {
			startPage = 1;
		}
	}
	
	//�� ������ ���
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









