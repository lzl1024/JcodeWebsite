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
        int id;

        synchronized(idSet) {
            Random r = new Random();
            id = r.nextInt(Integer.MAX_VALUE);
            while(idSet.contains(id)) {
                id = r.nextInt(Integer.MAX_VALUE);
            }
            idSet.add(id);
        }

        source = path + "/source/src" + Integer.toString(id);
        result = path + "/result/res" + Integer.toString(id); 
        
        try{
        	// Create Source file 
        	PrintWriter writer = new PrintWriter(source + ".java", "UTF-8");
        	writer.println(code);
        	writer.close();
        }catch (Exception e){//Catch exception if any
        	System.err.println("Error: " + e.getMessage());
        }

        
        ConThread t = new ConThread(source, result, policy, timeout, path);
        t.run();
        /* read result file */
        String output = readFile(result);

        synchronized(idSet) {
            idSet.remove(id);
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


    private static class ConThread implements Runnable {
        private String src;         //the source code file name
        private String res;         //the output message file name
        private String policy;      //the security policy file
        private String timeout;     //timeout seconds
        private String path;		//real directory path

        public ConThread(String src, String res, String policy, String timeout, String path) {
            this.src = src;
            this.res = res;
            this.policy = policy;
            this.timeout = timeout;
            this.path = path;
        }

        public void run() {
        	System.out.println(path);
            Runtime r = Runtime.getRuntime();
            String[] cmd = {path + "/run", src, res, policy, timeout};
            try {
                Process proc = r.exec(cmd);
                proc.waitFor();

            } catch(Exception e) {
                System.out.println("An error happened while running the script");
            }


        }
    }
}
