//package com.chanpay.androidknowledge.camera;
//
//import android.Manifest;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.graphics.ImageFormat;
//import android.graphics.SurfaceTexture;
//import android.hardware.camera2.CameraAccessException;
//import android.hardware.camera2.CameraCaptureSession;
//import android.hardware.camera2.CameraCharacteristics;
//import android.hardware.camera2.CameraDevice;
//import android.hardware.camera2.CameraManager;
//import android.hardware.camera2.CaptureRequest;
//import android.hardware.camera2.params.StreamConfigurationMap;
//import android.media.Image;
//import android.media.ImageReader;
//import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.Surface;
//import android.view.TextureView;
//
//import com.chanpay.androidknowledge.R;
//
//import java.nio.ByteBuffer;
//import java.util.Arrays;
//
//public class CameraActivity extends AppCompatActivity {
//
//    private TextureView textureView;
//    private TextureView.SurfaceTextureListener textureListener;
//    private String mCameraId;
//    private CameraCaptureSession mPreviewSession;
//    private CaptureRequest.Builder mCaptureRequestBuilder;
//    private CaptureRequest mCaptureRequest;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_camera);
//
//        textureView = (TextureView) findViewById(R.id.textureView);
//
//        textureListener = new TextureView.SurfaceTextureListener() {
//            @Override
//            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
//                //当SurefaceTexture可用的时候，设置相机参数并打开相机
//                setupCamera(width, height);
//                openCamera();
//            }
//
//            @Override
//            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
//
//            }
//
//            @Override
//            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
//                return false;
//            }
//
//            @Override
//            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
//
//            }
//        };
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        textureView.setSurfaceTextureListener(textureListener);
//    }
//
//
//    private void setupCamera(int width, int height) {
//        //获取摄像头的管理者CameraManager
//        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
//        try {
//            //遍历所有摄像头
//            for (String cameraId: manager.getCameraIdList()) {
//                CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
//                //默认打开后置摄像头
//                if (characteristics.get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_FRONT)
//                    continue;
//                //获取StreamConfigurationMap，它是管理摄像头支持的所有输出格式和尺寸
//                StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
//                //根据TextureView的尺寸设置预览尺寸
//                mPreviewSize = getOptimalSize(map.getOutputSizes(SurfaceTexture.class), width, height);
//                mCameraId = cameraId;
//                break;
//            }
//        } catch (CameraAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private void openCamera() {
//        //获取摄像头的管理者CameraManager
//        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
//        //检查权限
//        try {
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
//            //打开相机，第一个参数指示打开哪个摄像头，第二个参数stateCallback为相机的状态回调接口，第三个参数用来确定Callback在哪个线程执行，为null的话就在当前线程执行
//            manager.openCamera(mCameraId, stateCallback, null);
//        } catch (CameraAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private CameraDevice mCameraDevice;
//    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
//        @Override
//        public void onOpened(CameraDevice camera) {
//            mCameraDevice = camera;
//            //开启预览
//            startPreview();
//        }
//
//        @Override
//        public void onDisconnected( CameraDevice camera) {
//
//        }
//
//        @Override
//        public void onError( CameraDevice camera, int error) {
//
//        }
//    };
//
//
//    private void startPreview() {
//        SurfaceTexture mSurfaceTexture = textureView.getSurfaceTexture();
//        //设置TextureView的缓冲区大小
//        mSurfaceTexture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());
//        //获取Surface显示预览数据
//        Surface mSurface = new Surface(mSurfaceTexture);
//        try {
//            //创建CaptureRequestBuilder，TEMPLATE_PREVIEW比表示预览请求
//            mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
//            //设置Surface作为预览数据的显示界面
//            mCaptureRequestBuilder.addTarget(mSurface);
//            //创建相机捕获会话，第一个参数是捕获数据的输出Surface列表，第二个参数是CameraCaptureSession的状态回调接口，当它创建好后会回调onConfigured方法，第三个参数用来确定Callback在哪个线程执行，为null的话就在当前线程执行
//            mCameraDevice.createCaptureSession(Arrays.asList(mSurface), new CameraCaptureSession.StateCallback() {
//                @Override
//                public void onConfigured(CameraCaptureSession session) {
//                    try {
//                        //创建捕获请求
//                        mCaptureRequest = mCaptureRequestBuilder.build();
//                        mPreviewSession = session;
//                        //设置反复捕获数据的请求，这样预览界面就会一直有数据显示
//                        mPreviewSession.setRepeatingRequest(mCaptureRequest, mSessionCaptureCallback, null);
//                    } catch (CameraAccessException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onConfigureFailed(CameraCaptureSession session) {
//
//                }
//            }, null);
//        } catch (CameraAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
