import java.io.*;
import java.util.*;


public class Console {
    private final static String POLICY = "test.policy";
    private final static String TIMEOUT = "10"; 

    private static Integer numOfThread = new Integer(0);        //INCORRECT!!!!!!

    private String src;
    private String res;
    private String policy;
    private String timeout;

    public static void main(String[] args) {
        String src = "security";
        String res = "result";
        Console con = new Console(src, res, "test.policy", "10");
        System.out.println(con.compileRun());

    }

    public Console(String src, String res, String policy, String timeout) {
        this.src = src;
        this.res = res;
        this.policy = POLICY;
        this.timeout = TIMEOUT;
        numOfThread = 0;
    }

    public String compileRun() {
        int id;
        synchronized(numOfThread) {
            id = numOfThread;
            numOfThread++;
        }

        String source = src + id;
        String result = src + id; 
        
        ConThread t = new ConThread(source, result, policy, timeout);
        t.run();
        /* read result file */
        String output = readFile(result);
        synchronized(numOfThread) {
            numOfThread--;
        }

        return output;
   }

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

        public ConThread(String src, String res, String policy, String timeout) {
            this.src = src;
            this.res = res;
            this.policy = policy;
            this.timeout = timeout;
        }

        public void run() {
            Runtime r = Runtime.getRuntime();
            String[] cmd = {"./run", src, res, policy, timeout};
            try {
                Process proc = r.exec(cmd);
                int exitVal = proc.waitFor();
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                while((line = br.readLine())!=null){
                    System.out.println(line);
                }

            } catch(Exception e) {
                System.out.println("An error happened while running the script");
            }


        }
    }
}
