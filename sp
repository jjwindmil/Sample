jetty

https://blog.naver.com/PostView.naver?blogId=ambidext&logNo=222434508009&categoryNo=60&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView

```java
public class DateClient {
  public static void main(String[] args) throws IOException {
    Socket s = new Socket("127.0.0.1", 9090);
    BufferedReader input = new BufferedReader(new ItemStreamReader(s.getInputStream()));
    String answer = input.readLine();
    System.out.println(answer);
  }
}
```java
public class DateServer {
  public static void main(String[] args) throws IOException {
    ServerSocket listener = new ServerSocket(9090);
    try{
      while(true) {
        Socket socket = listener.accept();
        try{
          PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
          out.println(new Date().toString());
        } finally {
          socket.close();
        }
      }
    } finally {
      listener.close();
    }
  }
}

1. 파일읽기
	// 경로에 있는 파일 기준으로 파일에 데이터 읽어들인후 List반환
  parameter path: "./폴더/파일이름"
  ```java
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
  
  2. 파일에서 라인별로 구분자를 거쳐 명령
  // List내에 파일에서 읽어들인 List에서 구분자를 파라메터로 받아 명령 수행
	public static void CommandProcessList(List<String> targets, String seperator) {
		for( int i=0; i<targets.size(); i++) {
			String[] target = targets.get(i).split(seperator);
			String command = target[0]; //명령어
			if(command.equals("SEND")) {
				//처리 
			}else {
				//처리
			}
		}
	}
  
  3. 파일쓰기
  // List내에 String 을 parameter filename에 쓰기
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
  
  4. 디렉토리 검색
  public static String Listfile(String rootPath, String fileName) {
		// 디렉토리
        File dir = new File(rootPath);
        
        // 디렉토리 내에 파일 리스트
        File[] fList = dir.listFiles();
        
        String rtnPath = "";
        for(int i=0; i<fList.length; i++) {
        	if( fList[i].isFile() ) {
        		System.out.println("Path : "+ fList[i].getPath()+" fileName : "+ fList[i].getName()); // 파일의 FullPath 출력
        		if(fileName.equals(fList[i].getName())){
        			return fList[i].getPath();
        		}
        	} else if( fList[i].isDirectory() ) {
        		rtnPath = Listfile( fList[i].getPath(), fileName ); // 재귀함수 호출
        		if(rtnPath!="") {
        			return rtnPath;
        		}
        	}


        }
		return rtnPath;
	}
  5. 
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
  
6. Client Jetty
public class ClientJetty {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        HttpClient httpClient = new HttpClient();

        httpClient.start();

        ContentResponse contentRes = 

                httpClient.newRequest("http://127.0.0.1:8080/requestDate").method(HttpMethod.GET).send();

        System.out.println(contentRes.getContentAsString());

        httpClient.stop();

    
	}
}
7. Server
public class RunManager {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
        Server jettyServer = new Server();
        ServerConnector http = new ServerConnector(jettyServer);
        http.setHost("127.0.0.1");
        http.setPort(8080);
        jettyServer.addConnector(http);
        jettyServer.setStopAtShutdown(true); //graceful shutdown
        jettyServer.setStopTimeout(5000);
        
		ServletContextHandler serveltContextHandler = new ServletContextHandler();
        serveltContextHandler.addEventListener(new CustomerListener()); // manage event on server startup and stop
        serveltContextHandler.setAttribute("server",jettyServer); //for stop on servlet
        
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(MessageServlet.class, "/");
        serveltContextHandler.setHandler(servletHandler);
        
		jettyServer.setHandler(serveltContextHandler);
        
		//ServletHandler handler1 = new ServletHandler();
        //RequestHandler handler2 = new RequestHandler();
        //jettyServer.setHandler(handler);
        //jettyServer.setHandler(handler2);
        
        //handler.addServletWithMapping(MessageServlet.class, "/*");
        
//        HttpConfiguration httpConfig = new HttpConfiguration();
//        httpConfig.setOutputBufferSize(1024);
//        ServerConnector httpConnector = new ServerConnector(jettyServer,
//                new HttpConnectionFactory(httpConfig));
        jettyServer.start();
        jettyServer.join();
        
	}
}
8. Message Servlet
public class MessageServlet extends HttpServlet{
	
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String [] uri = request.getRequestURI().split("/");
    	String command = uri[1];
    	String queueName = uri[2];
		Gson gson = new Gson();
		String body = getBody(request);
    	//Arrays.asList(uri).forEach(System.out::println);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
    	if(command.equals("CREATE")) {    		
    		if(QueueMap.getInstance().isExist(queueName)) {

		        response.getWriter().println(QueueMap.getInstance().queueExist());
    		}else {
    			Map<String, Double> map = gson.fromJson(body, Map.class);
        		QueueObject queue = new QueueObject(queueName, map.get("QueueSize").intValue(), map.get("ProcessTimeout").intValue(),map.get("MaxFailCount").intValue(),map.get("WaitTime").intValue());
        		QueueMap.getInstance().queueMap.put(queueName, queue);
        		//System.out.println(QueueMap.getInstance().queueMap.get(queueName).list.toString() +" " + QueueMap.getInstance().isExist(queueName));
		        response.getWriter().println(QueueMap.getInstance().queueMap.get(queueName).createOk());
    		}

    		

    	}else if(command.equals("SEND")) {
    		/*
    		if(QueueMap.getInstance().queueMap.get(queueName).isFull()) {
		        response.getWriter().println(QueueMap.getInstance().queueMap.get(queueName).queueFull());
    		}else {
    			Map<String, String> map = gson.fromJson(body, Map.class);
    			String msg = map.get("Message");
    			QueueMap.getInstance().queueMap.get(queueName).list.add(msg);
    			
		        response.getWriter().println(QueueMap.getInstance().queueMap.get(queueName).sendOk());
    		}
    		*/
    		Map<String, String> map = gson.fromJson(body, Map.class);
			String msg = map.get("Message");
    		response.getWriter().println(QueueMap.getInstance().queueMap.get(queueName).sendOk(msg));
    		
    	}else if(command.equals("ACK")) {
    		int id = Integer.parseInt(uri[3]);
	        response.getWriter().println(QueueMap.getInstance().queueMap.get(queueName).ackOk(request.getRequestURI(),id));
    	}else if(command.equals("FAIL")) {
    		int id = Integer.parseInt(uri[3]);
    		response.getWriter().println(QueueMap.getInstance().queueMap.get(queueName).failOk(request.getRequestURI(),id));
    	}
    	
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String [] uri = request.getRequestURI().split("/");
    	String command = uri[1];
    	String queueName = uri[2];
    	//Arrays.asList(uri).forEach(System.out::println);
		response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
    	if(command.equals("RECEIVE")) {
	        response.getWriter().println(QueueMap.getInstance().queueMap.get(queueName).rcvOk(request.getRequestURI()));
    	}else if(command.equals("DLQ")) {
	        response.getWriter().println(QueueMap.getInstance().queueMap.get(queueName).dlqOk(request.getRequestURI()));
    	}else if(command.equals("SHUTDOWN")) {

	        //response.getWriter().println(QueueMap.getInstance().queueMap.get(queueName).shutDownOk());
    	}
    	
    }
    
    public static String getBody(HttpServletRequest request) throws IOException {
  	  
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
 
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
 
        body = stringBuilder.toString();
        return body;
    }

}
9. CustomerListenr
public class CustomerListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("servlet contextDestroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("servlet ContextInitialized");		
	}
}


10. Singleton Object
// 싱글턴 접근하려는 객체
public class QueueMap {
	Map<String, QueueObject> queueMap ; 
	Gson gson = new Gson();
	private QueueMap() {
		this.queueMap=new HashMap<>();
	}
	
	private static class LazyHolder{
		private static final QueueMap INSTANCE = new QueueMap();
	}
	
	public static QueueMap getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public boolean isExist(String queueName) {
		
		return queueMap.containsKey(queueName); 
	}
	
	public String queueExist(){
		Map<String,String> rtn = new HashMap<>(); 
		rtn.put("Result", "Queue Exist");
		return gson.toJson(rtn);
	}
}
10.1 Queue Object

public class QueueObject {
	//private Queue<QueueItem> queue  ;
	private Queue<Integer> queueIndex  ;
	private ArrayList<Integer> queueIndexList;
	//private ConcurrentHashMap<Integer,QueueItem> queue;
	private ConcurrentSkipListMap<Integer, QueueItem> queue;
	private int size;
	private int processTimeout;
	private int maxFailCount;
	private int waitTime;
	private ConcurrentHashMap<Integer,QueueItem> rcvMap; 
	ConcurrentHashMap<Integer, Boolean> timerMap ;
	Queue<String> deadLetterQueue ;
	public ConcurrentHashMap<Integer, Integer>  failCountMap;
	public ConcurrentHashMap<Integer, String> failMap;
	String queueName ;
	
	Gson gson = new Gson();
	QueueObject(String queueName , int size, int processTimeOut, int maxFailCount, int waitTime){
		//this.queue = new ConcurrentLinkedQueue<QueueItem>();
		//this.queue = new ConcurrentHashMap<Integer,QueueItem>();
		this.queue = new ConcurrentSkipListMap<Integer, QueueItem>();
		this.queueIndex=new ConcurrentLinkedQueue<Integer>();
		this.queueIndexList=new ArrayList<Integer>();
		this.rcvMap = new ConcurrentHashMap<Integer,QueueItem>();
		this.timerMap  = new ConcurrentHashMap<Integer,Boolean>();
		this.failCountMap = new ConcurrentHashMap<Integer,Integer>();
		this.failMap = new ConcurrentHashMap<Integer,String>();
		this.size=size;	
		this.processTimeout=processTimeOut;
		this.maxFailCount=maxFailCount;
		this.waitTime=waitTime;
		this.queueName= queueName;
		this.deadLetterQueue= new LinkedList<>();
		
		
	}
	public boolean isFull() {
		
		return queue.size()==size;
	}
	public String queueFull(){
		Map<String,String> rtn = new HashMap<>(); 
		rtn.put("Result", "Queue Full");
		return gson.toJson(rtn);
	}
	public String createOk(){
		Map<String,String> rtn = new HashMap<>(); 
		rtn.put("Result", "OK");
		return gson.toJson(rtn);
	}
	public String sendOk(String msg){
		Map<String,String> rtn = new HashMap<>();
		if(isFull()) {
			rtn.put("Result", "Queue Full");	
		}else {
			int id = Integer.parseInt(msg.split("#")[1]) ;
			QueueItem item = new QueueItem(id, msg);

			this.queue.put(id, item);
			rtn.put("Result", "OK");
			
		}
		System.out.println("SEND QueueName : "+queueName+ " "+queue.toString());
		return gson.toJson(rtn);
	}
	public String rcvOk(String uri){
		LocalDateTime startTime = LocalDateTime.now();
		
		Map<String,String> rtn = new HashMap<>();
		int id=-1;
		System.out.println(uri+" "+queueName+ 
				" QUEUE :  "+queue.toString()+ 
				" RCV MAP : "+ rcvMap.toString());
		Entry<Integer,QueueItem> item =  queue.pollFirstEntry();
		//¼ö½Å ´ë±â
		if(this.waitTime>0) {
			while(item==null) {
				Duration duration = Duration.between(startTime, LocalDateTime.now());
				if(duration.getSeconds() >=this.waitTime) {
					break;
				}
			}
		}

		if(item != null ) {
			
			rtn.put("Message", item.getValue().getMsg());
			rtn.put("MessageID", String.valueOf(item.getValue().getId()) );
			rtn.put("Result", "OK");
			rcvMap.put(item.getValue().getId(), item.getValue());
			item.getValue().setProcessing(true);
			id= item.getValue().getId();
		}else {
			rtn.put("Result", "No Message");	
		}
		if(id>=0) {
			this.timerTaskTest(id);
		}
		
		
		return gson.toJson(rtn);
	}
	public  String ackOk(String uri, int id){
		Map<String,String> rtn = new HashMap<>();
		QueueItem item = rcvMap.get(id);
		
		if(item !=null) {
			rcvMap.remove(id);
			rtn.put("Result", "OK");

		}else {
			rtn.put("Result", "No Message");	
		}
		System.out.println(uri+" "+"ACK QueueName : "+queueName+ " QUEUE :  "+queue.toString()+ " RCV MAP : "+ rcvMap.toString());

		return gson.toJson(rtn);
	}
	public String dlqOk(String uri){
		Map<String,String> rtn = new HashMap<>();
		String msg = deadLetterQueue.poll();
		
		if( msg!=null) {
			int id = Integer.parseInt(msg.split("#")[1]);
			rtn.put("Result", "OK");
			rtn.put("MessagID", String.valueOf(id));
			rtn.put("Message", msg);
			//ackOk( uri, id );
		}else {
			rtn.put("Result", "No Message");
		}
		System.out.println(uri+" "+">>>>DLQ QueueName : "+queueName+ " QUEUE :  "+queue.toString()+ " FailCount : "+ failCountMap.toString() +" DEADLETTER : "+deadLetterQueue.toString());
		
		return gson.toJson(rtn);
	}
	public void recoveryQueue(int id) {

		queue.put(id, rcvMap.get(id));
		//rcvMap.remove(id);
	}
	public String failOk(String uri, int id){
		Map<String,String> rtn = new HashMap<>();
		QueueItem item = rcvMap.get(id);
		
		if(failCountMap.get(id)!=null) {
			failCountMap.put(id, failCountMap.get(id)+1);	
		}else {
			failCountMap.put(id, 1);
		}
		if(item !=null) {
			failMap.put(id, item.getMsg());
			if(failCountMap.get(id)>this.maxFailCount) {
				if(!deadLetterQueue.contains(item.getMsg())) {
					deadLetterQueue.add(failMap.get(id));
				}
					
				
				rtn.put("Result", "OK");
			}
			
			recoveryQueue(id);
			rtn.put("Result", "OK");
		}else {
			rtn.put("Result", "No Message");	
		}
//		if(failCountMap.get(id)>this.maxFailCount) {
//			
//			deadLetterQueue.add(failMap.get(id));
//			rtn.put("Result", "OK");
//		}else {
//
//		}

		System.out.println(uri+" "+"FAIL QueueName : "+queueName+ " id : "+ id+" QUEUE :  "+queue.toString()+ " RCV MAP : "+ rcvMap.toString() + " FAIL MAP : "+failMap.toString()+ " DEADLETTER : "+deadLetterQueue.toString());

		return gson.toJson(rtn);
	}
	public void timerTaskTest(int id) {
		
		int timeout = QueueMap.getInstance().queueMap.get(queueName).processTimeout;
		
		
		if(timeout>0) {
			QueueMap.getInstance().queueMap.get(queueName).timerMap.put(id, false);
			//System.out.println("queueName : "+ queueName +" TimeoutÁ¸Àç ");
			//TimerRunnable runnable = new TimerRunnable(timeout, queueName, id );
			TimerTaskTest task = new TimerTaskTest(timeout, queueName, id);
	        //Thread thread = new Thread(runnable);
	        //thread.start();
			//executor.execute(runnable);
			Timer timer = new Timer("Timer");
			long delay = 0L;
			long period = 1000L;
			timer.schedule(task, delay, period);

		}else {
			//System.out.println("queueName : "+ queueName +" Timeout ¾øÀ½ ");
		}
	}
}
10.2 QueueItem
public class QueueItem {
	int id;
	String msg;
	boolean processing;
	QueueItem(){
		
	}
	QueueItem(int id , String msg){
		this.id = id;
		this.msg=msg;
		this.processing=false;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isProcessing() {
		return processing;
	}
	public void setProcessing(boolean processing) {
		this.processing = processing;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "{id=" + id + ", msg=" + msg  + "}";
	}

	
	
}

11. 외부 프로그램 호출
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        str= br.readLine();
        String[] strNums = str.split(" ");
        
        Process process = null;
        
        
        String[] cmd = new String[] {"sum.exe", strNums[0], strNums[1]};

        try {
            process = new ProcessBuilder(cmd).start();

            BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ( (str= stdOut.readLine()) != null ) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
12. Reflection 
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
			//TargetClass target = new TargetClass(); //해당 클래스의 인스턴스 생성 중요!!!!!!!!
			
			Class targetClass = Class.forName("cok.kr.javastudy/reflection.TargetClass");
			// java.lang.Class의 forName()메소드를 통해 클래스를 찾음
			
			Method methods[] = targetClass.getDeclaredMethods();
			for(int i=0; i<methods.length; i++) {
				System.out.println(methods[i].toString());
				String findMethod = methods[i].getName();
				if(findMethod.equals("Method이름")) {
					//methods[i].invoke(target, "parameter");
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

13. Runtime Process
    public static void main(String[] args) throws IOException {
        Runtime runTime = Runtime.getRuntime();

        Process p = runTime.exec("notepad.exe");
    }

14. TimerTask

public class TimerTaskTest extends TimerTask {
	int time ;
	String queueName;
	LocalDateTime startTime;
	int id;
	TimerTaskTest(int time, String queueName, int id){
		this.time=time;
		this.queueName=queueName;
		this.startTime=LocalDateTime.now();
		this.id=id;
	}
	public void check() {
		LocalTime currentTime = LocalTime.now();
		int start = currentTime.getSecond();
		
	}
    @Override
    public void run()  {
    	boolean isOk =false;
		Duration duration = Duration.between(this.startTime, LocalDateTime.now());
		if(duration.getSeconds() >=time) {
			System.out.println(queueName+ " timeout... : "+startTime+ " "+LocalDateTime.now()+ " "+time+ " duration : "+ duration.getSeconds() +
					" " + QueueMap.getInstance().queueMap.get(queueName).timerMap.toString());
			 
			 QueueMap.getInstance().queueMap.get(queueName).failOk("ProcessTimeout Fail ",id);
			 cancel();
			
		}else {
			if(QueueMap.getInstance().queueMap.get(queueName).timerMap.get(id)) {
				
				System.out.println(queueName+ " Check... : "+startTime+ " "+LocalDateTime.now()+ " "+time+ " duration : "+ duration.getSeconds() +
						" " + QueueMap.getInstance().queueMap.get(queueName).timerMap.toString());
				isOk =true;
//				if(QueueMap.getInstance().queueMap.get(queueName).ackMap.containsKey(id)) {
//					QueueMap.getInstance().queueMap.get(queueName).ackOk(id);
//				}else {
//					QueueMap.getInstance().queueMap.get(queueName).failOk(id);
//				}
				cancel();
			}
		}

        String result = queueName + " Called at " + LocalTime.now()+" is OK"+isOk;
        
        
    }
}


15. Jetty File Client
public class FileClient {
    public static void main(String[] args) throws Exception {

        String strFileList = getFileList();


        HttpClient httpClient = new HttpClient();

        httpClient.start();

        Request request = httpClient.newRequest("http://127.0.0.1:8080/fileList").method(HttpMethod.POST);

        request.header(HttpHeader.CONTENT_TYPE, "application/json");

        request.content(new StringContentProvider(strFileList,"utf-8"));

        ContentResponse contentRes = request.send();

        System.out.println(contentRes.getContentAsString());

        httpClient.stop();

    }


    private static String getFileList() {

        Gson gson = new Gson();

        JsonObject jo = new JsonObject();

        File directory = new File("./Input");

        jo.addProperty("Folder", "Input");

        JsonArray jarr = new JsonArray();

        File[] fList = directory.listFiles();

        for (File file : fList) {

            jarr.add(file.getName());

        } 

        jo.add("FILES", jarr);

        String res = jo.toString();

        return res; 

    }

}

16. URL Classloader

    public static void main(String[] args) throws Exception {
         
        // Getting the jar URL which contains target class
        URL[] classLoaderUrls = new URL[]{new URL("file:///home/ashraf/Desktop/simple-bean-1.0.jar")};
         
        // Create a new URLClassLoader 
        URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);
         
        // Load the target class
        Class<?> beanClass = urlClassLoader.loadClass("com.jcg.Bean");
         
        // Create a new instance from the loaded class
        Constructor<?> constructor = beanClass.getConstructor();
        Object beanObj = constructor.newInstance();
         
        // Getting a method from the loaded class and invoke it
        Method method = beanClass.getMethod("sayHello");
        method.invoke(beanObj);
 
    }

