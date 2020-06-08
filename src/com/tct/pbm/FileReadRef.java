package com.tct.pbm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReadRef {
	public static ArrayList<String> FileReader(String path) throws IOException {
		String str = "";
		File file = new File(path);
		ArrayList<String> result = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((str=br.readLine())!=null) {
				result.add(str);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public static void fileWriter(ArrayList<String> strList, String filename) throws IOException {
		String rootPath = "./OUTFILE";
		String path = rootPath+"/"+filename;
		File file = new File(path);
		
		if(strList!=null) {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			strList.forEach((item)->{
				System.out.println(item);
				try {
					bw.write(item);
					bw.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			});
			bw.close();
		}
	}
	
	public static String Listfile(String rootPath, String fileName) {
        File dir = new File(rootPath);
        File[] fList = dir.listFiles();
        String rtnPath = "";
        for(int i=0; i<fList.length; i++) {
        	if( fList[i].isFile() ) { 
        		System.out.println( fList[i].getPath() ); // 파일의 FullPath 출력
        		System.out.println(fList[i].getName());
        		if(fileName.equals(fList[i].getName())){
        			return fList[i].getPath();
        		}
        	} else if( fList[i].isDirectory() ) {
        		rtnPath = Listfile( fList[i].getPath(), fileName ); // 재귀함수 호출 }
        		if(rtnPath!="") {
        			return rtnPath;
        		}
        	}


        }
		return rtnPath;
	}
	
	public static String inputScanner() {
		Scanner scanner = new Scanner(System.in);
		String line;
		while ((line = scanner.nextLine()) != null) {
			if (line.equals("QUIT")) {
				//cardSever.close();
				break;
			} else if (line.equals("REPORT")) {
				//
				break;
			} else if (line.contains(".TXT")){ // Date
				break;
			} else {
				
			}
		}
		return line;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Scanner Start !!!");
		String rootReadPath = "./INFILE/";
		String rootWritePath = "./OUTFILE/";
		
		String rtnFileName = inputScanner();
		System.out.println(">>>> : " + rtnFileName);
		String fullPath = rootReadPath + rtnFileName;
		ArrayList<String> rtnRead=FileReader(fullPath);
		System.out.println(rtnRead.toString());
		
		System.out.println("Depth BigFile Scanning !!!");
		String rootBigFilePath = "./BIGFILE";
		
		String rtnDepthFileName = inputScanner();
		String rtnBigFilePath = Listfile(rootBigFilePath, rtnDepthFileName);
		System.out.println(">>>> : " + rtnBigFilePath);
	}

}
