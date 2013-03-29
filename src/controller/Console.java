package controller;

import java.io.*;
import java.util.*;


public class Console {
    private final static String POLICY = "test.policy";
    private final static String TIMEOUT = "10"; 

    /* singleton instance */
    private static final Console instance = new Console();


    private String policy;
    private String timeout;
    private HashSet<Integer> idSet;

 
    public static Console getInstance() {
        return instance;
    }


    public Console() {
        this.policy = POLICY;
        this.timeout = TIMEOUT;
        idSet = new HashSet<Integer>();
    }

    public String compileRun(String code, String path) {
        String source;
        String result;
        File dir = null;
        int id;

        synchronized(idSet) {
        	//random pick an ID number
            Random r = new Random();
            id = r.nextInt(Integer.MAX_VALUE);
            while(idSet.contains(id)) {
                id = r.nextInt(Integer.MAX_VALUE);
            }
            idSet.add(id);
        }

        source = path + "/" + Integer.toString(id) + "/Source";
        result = path + "/" + Integer.toString(id) + "/Result"; 
        
        try{
        	// Create new directory
        	dir = new File(path + "/" + Integer.toString(id));
        	dir.mkdir();
        	
        	// Create Source file 
        	PrintWriter writer = new PrintWriter(source + ".java", "UTF-8");
        	writer.println(code);
        	writer.close();
        }catch (Exception e){//Catch exception if any
        	System.err.println("Error: " + e.getMessage());
        }

        
        ConThread t = new ConThread("Source", "Result", policy, timeout, path, path+"/"+id);
        t.run();
        /* read result file */
        String output = readFile(result);

        synchronized(idSet) {
            idSet.remove(id);
            deleteDirectory(dir);
        }

        return output;
   }

    @SuppressWarnings("resource")
	private String readFile(String file) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader (file));
            String line = null;
            
            String ls = System.getProperty("line.separator");

            while((line = reader.readLine()) != null ) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
        } catch(IOException e) {
            System.out.println("error in reading file");
        }

        return stringBuilder.toString();
    
    }
    
    private boolean deleteDirectory(File dir) {
        if(dir.exists()) {
          File[] files = dir.listFiles();
          for(int i=0; i<files.length; i++) {
             if(files[i].isDirectory()) {
               deleteDirectory(files[i]);
             }
             else {
               files[i].delete();
             }
          }
        }
        return(dir.delete() );
      }


    private static class ConThread implements Runnable {
        private String src;         //the source code file name
        private String res;         //the output message file name
        private String policy;      //the security policy file
        private String timeout;     //timeout seconds
        private String path;		//real directory path
        private String dir;			//user's dir

        public ConThread(String src, String res, String policy, String timeout, String path, String dir) {
            this.src = src;
            this.res = res;
            this.policy = policy;
            this.timeout = timeout;
            this.path = path;
            this.dir = dir;
        }

        public void run() {
        	System.out.println(path);
            Runtime r = Runtime.getRuntime();
            String[] cmd = {path + "/run", dir, src, res, policy, timeout};
            try {
            	System.out.println(Arrays.toString(cmd));
                Process proc = r.exec(cmd);
                proc.waitFor();

            } catch(Exception e) {
                System.out.println("An error happened while running the script");
            }


        }
    }
}
