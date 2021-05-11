package commandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestCommandLine {

    public static void testAdb() {
        String log = "";
        ProcessBuilder processBuilder = new ProcessBuilder();

        String lastLine = "";
        String command = "adb connect "+ "192.168.0.242" ;
        System.out.println(command);
        // Run a shell command
        processBuilder.command("bash", "-c",command);
        processBuilder.command("bash","-c","adb -s"+"192.168.0.242"+" shell logcat | grep FileUpload");

        try {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            int count = 0;
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("mm-dd hh:mm:ss");
            String strDate = dateFormat.format(date);
            long start = System.currentTimeMillis();
            long end = System.currentTimeMillis();
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
                lastLine = line;
                System.out.println(" | ==============================================" + count++ + " | ==============================================" );
                //System.out.println( line);
                end = System.currentTimeMillis();
                if(end - start > 10000){
                    break;
                }
            }
            String[] words = lastLine.split(" ");
            System.out.println(output);
            System.out.println("===========================");
            System.out.println(lastLine);
            System.out.println(words.toString());
            System.out.println(strDate);
            System.out.println(new Date());
            System.out.println("Status" + log);

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.exit(0);
            } else {
                System.out.println("error");
            }
            //StringBuilder output = new StringBuilder();
//                String outputSingle = "";
//                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//                String line;
//                int count = 0;
//                long startTime = System.currentTimeMillis();
//                while ((line = reader.readLine()) != null) {
//                    long timeLoop = System.currentTimeMillis() - startTime;
//                    count += timeLoop;
//                    if (count >= 2000){
//                        outputSingle = line;
//                        //output.append(line + "\n");
//                        System.out.println(outputSingle);
//                        count = 0;
//                    }
//                }
//                System.out.println("Status" + log);
//                int exitVal = process.waitFor();
//                if (exitVal == 0) {
//                    System.out.println("Success!");
//                    System.exit(0);
//                } else {
//                    System.out.println("error");
//                }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

