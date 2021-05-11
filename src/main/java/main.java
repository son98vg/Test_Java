
import commandLine.TestCommandLine;
import helloworld.Client;
import rtsp.rtspCapture;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        boolean mIsStop = false;
        rtspCapture rtspCapture = new rtspCapture();
        rtspCapture.start();
//        TestCommandLine testCmd = new TestCommandLine();
//        testCmd.testAdb();



    }

}
