package singha.com.util;

import java.io.File;

//���ϰ��� ����� �����ϴ� Ŭ����
public class SFileUtil {

	
	
	//�ش� ������ ���� ���
	public static void  createFoler(String path) {
		File file = new File(path);
		file.mkdirs();
	}
	
	//���������̸��� �����ϸ� �����̸��� ����
	public static String renameTo(String path, String name) {//static �ᵵ�ǰ� �Ƚᵵ ��
	
		//�ش� ������ ���� ���
		createFoler(path);
		
		String tempName = name;
		int cnt = 0; //���ϸ�ڿ� ���� ���ڰ� ����� ���� ����
		//���� ������ �̸��� File Ŭ������ ����
		File file= new File(path, tempName);
		
		while(file.exists()){ //���������̸��� ���翩��Ȯ��
			
			int idx = name.lastIndexOf("."); //.�� ��ġ
			//substring(int beginIndex,int endIndex)
			String fileN = name.substring(0,idx);//��)christmas
			String exet = name.substring(idx+1);
			
			cnt =cnt+1; //cnt++
			fileN = fileN+"_"+cnt;
			
			tempName = fileN+"."+exet;
						
			file = new File(path, tempName);
		}
		return tempName;
		
		
		
		
		
		
	}
	
}


