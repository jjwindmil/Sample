package sp_1day;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class FileTest {
	
	static Map<String, Integer> FileDirList() {
		Map<String, Integer> rtn = new HashMap<>();
		File directory = new File("./INPUT");
		File[] fList = directory.listFiles();
		
		for(File file : fList) {
			if(file.isDirectory()) {
				System.out.println("["+file.getName()+"]");
			}else {
				System.out.println(file.getName()+ "/"+ file.length());
				if(file.length()> 2000) {
					rtn.put(file.getName(), (int) file.length());
					MyCopyFile(file.getName());
				}
			}
		}
		return rtn;

	}
	
	static void MyCopyFile(String filename)
	{
		final int BUFFER_SIZE = 512;
        int readLen;
        try {
    		// Create Folder
    		File destFolder = new File("./OUTPUT");
    		if(!destFolder.exists()) {
    			destFolder.mkdirs(); 
    		}        	
        	
    		// Copy File
    	
    			 
            InputStream inputStream = new FileInputStream("./INPUT/"+filename);
            OutputStream outputStream = new FileOutputStream("./OUTPUT/"+filename);
 
            byte[] buffer = new byte[BUFFER_SIZE];
 
            while ((readLen = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, readLen);
            }
            
            inputStream.close();
            outputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Map<String, Integer> fileList = FileDirList();
		 System.out.println(fileList);

	}

}
