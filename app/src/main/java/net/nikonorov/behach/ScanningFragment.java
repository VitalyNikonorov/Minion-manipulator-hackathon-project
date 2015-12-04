package net.nikonorov.behach;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by vitaly on 05.12.15.
 */
public class ScanningFragment extends Fragment {

    public ScanningFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_scanning, null);

        Button connectBtn = (Button) view.findViewById(R.id.connectButton);

        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Connected", Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }
}
