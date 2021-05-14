package rtsp;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.javacv.OpenCVFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public  class rtspCapture extends Thread {
    private boolean mIsStop = false;

    public synchronized void run() {
        System.out.println("check 1 -----");
        final String rtsp= "rtsp://admin:12345678a@@192.168.0.122/fhd";
        FFmpegFrameGrabber streamGrabber = new FFmpegFrameGrabber(rtsp);
        streamGrabber.setFormat("RTSP");
        streamGrabber.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        streamGrabber.setOption("rtsp_transport", "tcp");
        streamGrabber.setOption("hwaccel", "nvdec");
        streamGrabber.setImageHeight(720);
        streamGrabber.setImageWidth(1280);
        streamGrabber.setOption("tune", "zerolatency");
        streamGrabber.setOption("an", "");
        streamGrabber.setOption("sn", "");
        streamGrabber.setOption("dn", "");

        streamGrabber.setFrameRate(30);// 30//15
//            streamGrabber.setPixelFormat(AV_PIX_FMT_RGB24);// AV_PIX_FMT_RGBA);
        // set timeout
        streamGrabber.setOption("stimeout", "1000000");
        // init converter
        OpenCVFrameConverter.ToMat convToMat = new OpenCVFrameConverter.ToMat();

        OpenCVFrameConverter.ToMat converter1 = new OpenCVFrameConverter.ToMat();
        OpenCVFrameConverter.ToOrgOpenCvCoreMat converter2 = new OpenCVFrameConverter.ToOrgOpenCvCoreMat();

        try {
            streamGrabber.start();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
        org.bytedeco.javacv.Frame frame = null;
        while (!mIsStop) {
            try {
                if ((frame = streamGrabber.grabImage()) !=null) {
                    BufferedImage bufferImage = Java2DFrameUtils.toBufferedImage(frame);
                    //                        opencv_core.Mat mat = convToMat.convert(frame);
//                    System.out.println("bufferImage ----" + bufferImage);
                    System.out.println(frame.getTypes());
                    File outputfile = new File("/media/minhhoang/Data/son/test.jpg");
//                    ImageIO.write(bufferImage, "jpg", outputfile);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ProcessBuilder pb = null;
//        Process pc = null;
//        pb = new ProcessBuilder("adb", "logcat", "-s 192.168.0.242", "RetinaDetector");
//        try {
//            pc =  pb.start();
//            StringBuilder output = new StringBuilder();
//
//            BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(pc.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                output.append(line + "\n");
//                System.out.println(output);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

}
