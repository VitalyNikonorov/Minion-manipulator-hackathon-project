package net.nikonorov.behach;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by vitaly on 05.12.15.
 */
public class ScanningFragment extends Fragment {

    public ScanningFragment() { }

    TextView scanstatusTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_scanning, null);

        Button connectBtn = (Button) view.findViewById(R.id.connectButton);
        scanstatusTV = (TextView) view.findViewById(R.id.scanstatus);

        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Settings.setAddress(scanstatusTV.getText().toString());
                Toast.makeText(getActivity(), "Connected to " + Settings.getAddress() , Toast.LENGTH_SHORT).show();
                        ((MainActivity) getActivity()).connect();
            }
        });

        return view;

    }

    public void setURL(String url){
        scanstatusTV.setText(url);
    }
}
