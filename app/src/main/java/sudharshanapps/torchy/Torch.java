package sudharshanapps.torchy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;


//Main Class
public class Torch extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Passing values to super class(Activity)
        super.onCreate(savedInstanceState);

        //Setting View (Relative layout + Image button) to layout
        setContentView(R.layout.activity_torch);

        //Creating an object of attributes which we use
        Attributes attributes = new Attributes();

        //Retrieving Image button from resources and setting its value
        attributes.setImageButton((ImageButton)findViewById(R.id.button));

        //Check Camera or Device status
        attributes.checkCameraStatus(Torch.this);

        //Creating Dialog
        if(!attributes.getCameraStatus())
        attributes.createDialog(Torch.this);


    }

}
