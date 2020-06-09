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
				break;
				
			}
		}
		return line;
	}
	//중복라인 제거 함수
	public static ArrayList<String> lineConverting(ArrayList<String> arr) {
		ArrayList<String> rtn = new ArrayList<>();
        for(int i=0; i<arr.size();i++) {
            String t = arr.get(i);
            int count =0;
            for(int j=i; j<arr.size()-1; j++){

                if(arr.get(j).equals(arr.get(j+1))){
                    count++;
                }else {
                    break;
                }
            }
            if(count>0) {
                i+=count;
                rtn.add((count+1)+"#"+arr.get(i));
                System.out.println("연속중복 : "+count);
                System.out.println("연속중복 => "+arr.get(i));
            }else {
                rtn.add(arr.get(i));
            }
        }
		System.out.println(rtn.toString());
		return rtn;
	}
	public static ArrayList<String> charConverting(ArrayList<String> arr){
		ArrayList<String> rtn = new ArrayList<>();
		arr.forEach((item)->{
			String diffTarget= item;
			//System.out.println(item);
			String[] ch = item.split("");
			ArrayList<String> rt = new ArrayList<String>();
			String rtString="";
			for(int i=0 ; i<ch.length; i++) {
				String comp = "";
				String temp="";
				int cnt=0;
				for(int j=i; j<ch.length; j++) {
					
					comp+= ch[i];
					if(diffTarget.contains(comp)) {
						temp=comp;
						cnt++;
					}else {

						//System.out.println("j==>"+ (j-1));
						break;
					}

				}
				if(cnt >= 3) {
					//System.out.println(comp);
					//System.out.println("indexOf==>"+ item.indexOf(temp));
					
					rt.add(temp.length()+ch[i]);
					rtString+=temp.length()+ch[i];
					i=item.indexOf(temp)+temp.length()-1;
					diffTarget=diffTarget.substring(diffTarget.indexOf(temp)+temp.length() );
				}else {
					rt.add(ch[i]);
					rtString+=ch[i];
				}
			//System.out.println(rt.toString());
			}
			rtn.add(rtString);
		});
		System.out.println(rtn.toString());
		return rtn;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Scanner Start !!!");
		String rootReadPath = "./INFILE/";
		String rootWritePath = "./OUTFILE/";
		
		String rtnFileName = inputScanner();
		if(!rtnFileName.isEmpty()) {
			System.out.println(">>>> : " + rtnFileName);
			String fullPath = rootReadPath + rtnFileName;
			ArrayList<String> rtnRead=FileReader(fullPath);
			System.out.println(rtnRead.toString());
		}

		System.out.println("Depth BigFile Scanning !!!");
		String rootBigFilePath = "./BIGFILE";
		
		String rtnDepthFileName = inputScanner();
		if(!rtnDepthFileName.isEmpty()) {
			String rtnBigFilePath = Listfile(rootBigFilePath, rtnDepthFileName);
			ArrayList<String> rtnBigFileRead=FileReader(rtnBigFilePath);
			System.out.println(">>>> : " + rtnBigFilePath);
			
			//문자열 Line 중복
			ArrayList<String> rtnLine = lineConverting(rtnBigFileRead);
			charConverting(rtnLine);
		}

		
	}

}
