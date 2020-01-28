package singha.com.vo;

import java.util.Arrays;
import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

public class SBoardVO {
	//����
	private int sno;//�۹�ȣ
	private String stitle; //������
	private Date sdate;//�ۼ���
	private int shit;//��ȸ��
	private int nowPage;//���� ������
		
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	
	private int     cnt;	//÷�����ϼ�
		
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	private String nick; //ȸ������
	private int rno;//���Ĺ�ȣ
	private String sday; //������(����)
	private String splace;//�������(����)
	private String simglink; //�̹�����ũ 
	private int		start;	//����(���ı�)��ȣ
	private int		end;	//��(���ı�)��ȣ
	private String swriter; //�� �ۼ���
	
	
	public String getSwriter() {
		return swriter;
	}
	public void setSwriter(String swriter) {
		this.swriter = swriter;
	}
	public String getScontent() {
		return scontent;
	}
	public void setScontent(String scontent) {
		this.scontent = scontent;
	}
	public String getScost() {
		return scost;
	}
	public void setScost(String scost) {
		this.scost = scost;
	}
	public String getSdday() {
		return sdday;
	}
	public void setSdday(String sdday) {
		this.sdday = sdday;
	}
	public String getSdplace() {
		return sdplace;
	}
	public void setSdplace(String sdplace) {
		this.sdplace = sdplace;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
	
	private String scontent;//���ӳ���
	private String spass;//��й�ȣ
	private String scost;//���Ӻ��
	private String sdday;//���ӽð�(��)
	private String sdplace;//�������(��)
	private String stype;//��������
	
	//private MultipartFile files;//÷������ 1���� ���
	private MultipartFile[] files;//÷������
	
	private int 			sForiNo;	//���۹�ȣ
	private String 			sFpath;		//���
	private	String			sForiName;	//�����̸�
	private String			sFsaveName;	//������̸�
	private long			sFlength;	//���Ͽ뷮
	private int 			sFno; 		//���Ϲ�ȣ 
	
	private String starget; //�˻�����
	private String skeyword; //�˻�Ű����
		
	public String getStarget() {
		return starget;
	}
	public void setStarget(String starget) {
		this.starget = starget;
	}
	public String getSkeyword() {
		return skeyword;
	}
	public void setSkeyword(String skeyword) {
		this.skeyword = skeyword;
	}
	public int getsFno() {
		return sFno;
	}
	public void setsFno(int sFno) {
		this.sFno = sFno;
	}
	public int getsForiNo() {
		return sForiNo;
	}
	public void setsForiNo(int sForiNo) {
		this.sForiNo = sForiNo;
	}
	public String getsFpath() {
		return sFpath;
	}
	public void setsFpath(String sFpath) {
		this.sFpath = sFpath;
	}
	public String getsForiName() {
		return sForiName;
	}
	public void setsForiName(String sForiName) {
		this.sForiName = sForiName;
	}
	public String getsFsaveName() {
		return sFsaveName;
	}
	public void setsFsaveName(String sFsaveName) {
		this.sFsaveName = sFsaveName;
	}	
	public long getsFlength() {
		return sFlength;
	}
	public void setsFlength(long sFlength) {
		this.sFlength = sFlength;
	}
	public String getSpass() {
		return spass;
	}
	public void setSpass(String spass) {
		this.spass = spass;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public int getShit() {
		return shit;
	}
	public void setShit(int shit) {
		this.shit = shit;
	}
	public String getSimglink() {
		return simglink;
	}
	public void setSimglink(String simglink) {
		this.simglink = simglink;
	}
	public String getStitle() {
		return stitle;
	}
	public void setStitle(String stitle) {
		this.stitle = stitle;
	}
	public String getSday() {
		return sday;
	}
	public void setSday(String sday) {
		this.sday = sday;
	}
	public String getSplace() {
		return splace;
	}
	public void setSplace(String splace) {
		this.splace = splace;
	}
	@Override
	public String toString() {
		return "SBoardVO [sno=" + sno + ", stitle=" + stitle + ", sdate=" + sdate + ", shit=" + shit + ", nowPage="
				+ nowPage + ", cnt=" + cnt + ", nick=" + nick + ", rno=" + rno + ", sday=" + sday + ", splace=" + splace
				+ ", simglink=" + simglink + ", start=" + start + ", end=" + end + ", swriter=" + swriter
				+ ", scontent=" + scontent + ", spass=" + spass + ", scost=" + scost + ", sdday=" + sdday + ", sdplace="
				+ sdplace + ", stype=" + stype + ", files=" + Arrays.toString(files) + ", sForiNo=" + sForiNo
				+ ", sFpath=" + sFpath + ", sForiName=" + sForiName + ", sFsaveName=" + sFsaveName + ", sFlength="
				+ sFlength + ", sFno=" + sFno + "]";
	}
}