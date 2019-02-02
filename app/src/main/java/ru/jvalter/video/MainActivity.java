package ru.jvalter.video;

import android.graphics.Rect;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Camera camera;
    private File file;
    private SurfaceView preview;
    private SurfaceHolder surfaceHolder;
    private int previewSurfaceWidth ;
    private int previewSurfaceHeight;
    private boolean recStarted;
    private MediaRecorder recorder;


    ViewGroup.LayoutParams lp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        preview = (SurfaceView) findViewById(R.id.surfaceView);
////        previewSurfaceWidth = preview.getWidth();
////        previewSurfaceHeight = preview.getHeight();
////        lp = preview.getLayoutParams();
////        recorder = new MediaRecorder();
////
////        surfaceHolder = preview.getHolder();
////        camera= Camera.open();
////
////        camera.getParameters().setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
////        List<Camera.Area> meteringAreas = new ArrayList<Camera.Area>();
////        Rect areaRect1 = new Rect(-100, -100, 100, 100);    // specify an area in center of image
////        meteringAreas.add(new Camera.Area(areaRect1, 600)); // set weight to 60%
////        Camera.Parameters par =  camera.getParameters();
////        par.setMeteringAreas(meteringAreas);
////        camera.setParameters(par);
////
//////        float aspect = (float) par.getPictureSize().width / par.getPictureSize().height;
//////        camera.setDisplayOrientation(0);
//////        lp.width = previewSurfaceWidth;
//////        lp.height = (int) (previewSurfaceWidth / aspect);
////
////        try
////        {
////            camera.setPreviewDisplay(surfaceHolder);
////        } catch (IOException e) { }
////        camera.startPreview();


        Button btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    preview = (SurfaceView) findViewById(R.id.surfaceView);
                    surfaceHolder = preview.getHolder();
                    camera = Camera.open();
                    camera.setDisplayOrientation(90);
                    camera.getParameters().setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                    List<Camera.Area> meteringAreas = new ArrayList<Camera.Area>();

                    Rect areaRect1 = new Rect(-100, -100, 100, 100);    // specify an area in center of image
                    meteringAreas.add(new Camera.Area(areaRect1, 600)); // set weight to 60%
                    Camera.Parameters par = camera.getParameters();
                    par.setMeteringAreas(meteringAreas);
                    camera.setParameters(par);
                    camera.setPreviewDisplay(surfaceHolder);
                    camera.startPreview();

                } catch (IOException e) {
                }
            }
        });
        recorder = new MediaRecorder();
        recStarted =false;
        Button btn1 = (Button) findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!recStarted )
                {
                    //camera.stopPreview();
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);

                    recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    recorder.setVideoEncodingBitRate(2000000);
                    recorder.setAudioEncodingBitRate(48000);
                    recorder.setAudioSamplingRate(48000);
                    recorder.setAudioChannels(1);
  //                  recorder.setVideoFrameRate(30);
//                    recorder.setVideoSize(720, 540);
                    File filesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                    File videoFile= null;
//                    try {
//                        videoFile = File.createTempFile("video",".mp4",filesDir);
//                        FileOutputStream fos= new FileOutputStream(videoFile);
//                        FileDescriptor fd= fos.getFD();
//                        recorder.setOutputFile(fd);
//                        recorder.setPreviewDisplay(preview.getHolder().getSurface());
//                        recorder.prepare();
//                        recorder.start();
//                        recStarted =true;
////
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        Toast.makeText(getApplicationContext(),
//                                "Видео не записалось", Toast.LENGTH_SHORT);
//                    }
//

                }
                else
                {
                    recorder.stop();
                    recStarted =false;
                }
            }
        });

    }
}

