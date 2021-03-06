package sudharshanapps.torchy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.widget.ImageButton;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;

import java.util.List;
import java.util.logging.Handler;

//This class is a place holder for all attributes which we use for this app
class Attributes{

    //Holds reference to image being used on layout(As a switch)
    private ImageButton button;

    //Holds reference to Camera Device
    private CameraManager cameraManager;

    //Checking whether torch is on or not
    private boolean isFlashOn;

    //Checking whether device supports flash or not
    private boolean hasFlash;

    //Holding Parameters of Camera
    CameraCharacteristics parameters;

    //Assigning reference to Image
    public void setImageButton(ImageButton button){
        this.button=button;
    }

    //Returning Image reference
    public ImageButton getButton(){
        return button;
    }

    /*//Returning Camera Reference
    public CameraManager getCameraDevice(){
        return cameraManager;
    }*/

    //Returning torch status
    public boolean getFlashStatus(){
        return isFlashOn;
    }

    //Returning Camera Status
    public boolean getCameraStatus(){
        return hasFlash;
    }

    public void createDialog(Activity activity_main){

        //Final variables are only accessible from anonymous innerclass
        final Activity activity = activity_main;

        //Creating New Alert Box
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);

        //Assigning Title
        alert.setTitle("Error");

        //Setting Message
        alert.setMessage("Sorry, your device doesn't support flash light!");

        //Adding a button
        alert.setPositiveButton("OK",new DialogInterface.OnClickListener() {

            //Adding listener on click of OK button
            public void onClick(DialogInterface dialog, int which) {
                // closing the application
                activity.finish();
            }
        });

        //Displaying button
        alert.show();
    }

    //Checking Device Flash features and assigning values(true = flash support exists, false = no support for flash)
    public void checkCameraStatus(Activity activity){

        //Checking Features of Device
        hasFlash = activity.getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    public void setFlashOn(Activity activity){

        //Obtain current camera Manager reference
        cameraManager = (CameraManager) activity.getSystemService(Context.CAMERA_SERVICE);

        try {

            String[] cameraIDList = cameraManager.getCameraIdList();

            for(String camera : cameraIDList){
                cameraManager.openCamera(camera,new CameraDevice.StateCallback() {
                    @Override
                    public void onOpened(CameraDevice camera) {
                        //camera.createCaptureRequest()
                    }

                    @Override
                    public void onDisconnected(CameraDevice camera) {

                    }

                    @Override
                    public void onError(CameraDevice camera, int error) {

                    }
                },null);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}