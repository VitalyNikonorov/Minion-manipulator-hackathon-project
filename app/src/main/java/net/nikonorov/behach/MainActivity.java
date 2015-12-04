package net.nikonorov.behach;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.Collection;


/**
 * Created by vitaly on 04.12.15.
 */
public class MainActivity extends AppCompatActivity {

    String TAG = "myLog";

    ImageButton buttonUp;
    ImageButton buttonDown;
    ImageButton buttonLeft;
    ImageButton buttonRight;
    ImageButton buttonPhoto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        beaconManager = new BeaconManager(this);

        buttonDown = (ImageButton)findViewById(R.id.arrow_down);
        buttonUp = (ImageButton)findViewById(R.id.arrow_up);
        buttonLeft = (ImageButton)findViewById(R.id.arrow_left);
        buttonRight = (ImageButton)findViewById(R.id.arrow_right);
        buttonPhoto = (ImageButton)findViewById(R.id.photo);


        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Down Click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private BeaconManager beaconManager;
    private String scanId;

    @Override
    protected void onStart() {
        super.onStart();
        // Should be invoked in #onStart.
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                scanId = beaconManager.startEddystoneScanning();
                Log.d(TAG, scanId);

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Should be invoked in #onStop.
        beaconManager.stopEddystoneScanning(scanId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // When no longer needed. Should be invoked in #onDestroy.
        beaconManager.disconnect();
    }
//
//        @Override
//    public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
//        for (Beacon beacon: beacons) {
//            if (beacon.getServiceUuid() == 0xfeaa && beacon.getBeaconTypeCode() == 0x00) {
//                // This is a Eddystone-UID frame
//                Identifier namespaceId = beacon.getId1();
//                Identifier instanceId = beacon.getId2();
//                Log.d("RangingActivity", "I see a beacon transmitting namespace id: " + namespaceId +
//                        " and instance id: " + instanceId +
//                        " approximately " + beacon.getDistance() + " meters away.");
//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        ((TextView)MainActivity.this.findViewById(R.id.tv_status)).setText("Hello world, and welcome to Eddystone!");
//                    }
//                });
//            }
//        }
//    }

}
