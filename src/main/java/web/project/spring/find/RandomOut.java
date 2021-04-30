package web.project.spring.find;

public class RandomOut {

	public static void main(String[] args) {
		System.out.println(getRandomStr(6));
	}

	public static String getRandomStr(int size) {
		if(size > 0) {
			char[] tmp = new char[size];
			for(int i=0; i<tmp.length; i++) {
				int div = (int) Math.floor( Math.random() * 2 );
				
				if(div == 0) { // 0�̸� ���ڷ�
					tmp[i] = (char) (Math.random() * 10 + '0') ;
				}else { //1�̸� ���ĺ�
					tmp[i] = (char) (Math.random() * 26 + 'A') ;
				}
			}
			return new String(tmp);
		}
		return "ERROR : Size is required."; 
	}
}