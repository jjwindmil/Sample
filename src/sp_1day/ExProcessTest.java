package sp_1day;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExProcessTest {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String str;
	    str = br.readLine();
	    String [] strNums = str.split(" ");
		Process process = null;
		String[] cmd = new String[] {"sum.exe", strNums[0], strNums[1]};

		try {

		    // 프로세스 빌더를 통하여 외부 프로그램 실행
		    process = new ProcessBuilder(cmd).start();

		    // 외부 프로그램의 표준출력 상태 버퍼에 저장
		    BufferedReader stdOut = new BufferedReader( new InputStreamReader(process.getInputStream()) );
		   
		    // 표준출력 상태를 출력
		    while( (str = stdOut.readLine()) != null ) {
		        System.out.println(str);
		    }
		   
		} catch (IOException e) {
		    e.printStackTrace();

		}
	}

}
