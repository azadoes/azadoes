package singha.com.util;

import java.io.File;

//파일관련 기능을 제공하는 클래스
public class SFileUtil {

	
	
	//해당 폴더가 없는 경우
	public static void  createFoler(String path) {
		File file = new File(path);
		file.mkdirs();
	}
	
	//동일파일이름이 존재하면 파일이름을 변경
	public static String renameTo(String path, String name) {//static 써도되고 안써도 됨
	
		//해당 폴더가 없는 경우
		createFoler(path);
		
		String tempName = name;
		int cnt = 0; //파일명뒤에 붙일 숫자가 저장될 변수 선언
		//현재 저장할 이름의 File 클래스를 생성
		File file= new File(path, tempName);
		
		while(file.exists()){ //동일파일이름이 존재여부확인
			
			int idx = name.lastIndexOf("."); //.의 위치
			//substring(int beginIndex,int endIndex)
			String fileN = name.substring(0,idx);//예)christmas
			String exet = name.substring(idx+1);
			
			cnt =cnt+1; //cnt++
			fileN = fileN+"_"+cnt;
			
			tempName = fileN+"."+exet;
						
			file = new File(path, tempName);
		}
		return tempName;
		
		
		
		
		
		
	}
	
}


