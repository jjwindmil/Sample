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

		    // ���μ��� ������ ���Ͽ� �ܺ� ���α׷� ����
		    process = new ProcessBuilder(cmd).start();

		    // �ܺ� ���α׷��� ǥ����� ���� ���ۿ� ����
		    BufferedReader stdOut = new BufferedReader( new InputStreamReader(process.getInputStream()) );
		   
		    // ǥ����� ���¸� ���
		    while( (str = stdOut.readLine()) != null ) {
		        System.out.println(str);
		    }
		   
		} catch (IOException e) {
		    e.printStackTrace();

		}
	}

}
