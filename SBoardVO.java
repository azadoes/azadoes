package singha.com.vo;

import java.util.Arrays;
import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

public class SBoardVO {
	//변수
	private int sno;//글번호
	private String stitle; //글제목
	private Date sdate;//작성일
	private int shit;//조회수
	private int nowPage;//현재 페이지
		
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	
	private int     cnt;	//첨부파일수
		
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	private String nick; //회원별명
	private int rno;//정렬번호
	private String sday; //모임일(간단)
	private String splace;//모임장소(간단)
	private String simglink; //이미지링크 
	private int		start;	//시작(정렬글)번호
	private int		end;	//끝(정렬글)번호
	private String swriter; //글 작성자
	
	
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
	
	private String scontent;//모임내용
	private String spass;//비밀번호
	private String scost;//모임비용
	private String sdday;//모임시간(상세)
	private String sdplace;//모임장소(상세)
	private String stype;//모임종류
	
	//private MultipartFile files;//첨부파일 1개의 경우
	private MultipartFile[] files;//첨부파일
	
	private int 			sForiNo;	//원글번호
	private String 			sFpath;		//경로
	private	String			sForiName;	//원래이름
	private String			sFsaveName;	//저장된이름
	private long			sFlength;	//파일용량
	private int 			sFno; 		//파일번호 
	
	private String starget; //검색종류
	private String skeyword; //검색키워드
		
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