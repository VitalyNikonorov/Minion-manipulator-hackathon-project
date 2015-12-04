package net.nikonorov.behach;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.utils.UrlBeaconUrlCompressor;

import java.util.Collection;


/**
 * Created by vitaly on 05.12.15.
 */
public class AndroidBeaconLibraryActivity extends AppCompatActivity implements BeaconConsumer, RangeNotifier {

        private static String TAG = "MyActivity";
        private BeaconManager mBeaconManager;

        @Override
        public void onResume() {
            super.onResume();
            mBeaconManager = BeaconManager.getInstanceForApplication(this.getApplicationContext());
            // Detect the URL frame:
            mBeaconManager.getBeaconParsers().add(new BeaconParser().
                    setBeaconLayout("s:0-1=feaa,m:2-2=10,p:3-3:-41,i:4-20v"));
            mBeaconManager.bind(this);
        }

        public void onBeaconServiceConnect() {
            Region region = new Region("all-beacons-region", null, null, null);
            try {
                mBeaconManager.startRangingBeaconsInRegion(region);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mBeaconManager.setRangeNotifier(this);
        }

        @Override
        public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
            for (Beacon beacon: beacons) {
                if (beacon.getServiceUuid() == 0xfeaa && beacon.getBeaconTypeCode() == 0x10) {
                    // This is a Eddystone-URL frame
                    String url = UrlBeaconUrlCompressor.uncompress(beacon.getId1().toByteArray());
                    Log.d(TAG, "I see a beacon transmitting a url: " + url +
                            " approximately " + beacon.getDistance() + " meters away.");
                }
            }
        }

        @Override
        public void onPause() {
            super.onPause();
            mBeaconManager.unbind(this);
        }


}
