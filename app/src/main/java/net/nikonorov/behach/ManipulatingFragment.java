package net.nikonorov.behach;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by vitaly on 05.12.15.
 */
public class ManipulatingFragment extends Fragment {

    public ManipulatingFragment() {  }

    ImageButton buttonUp;
    ImageButton buttonDown;
    ImageButton buttonLeft;
    ImageButton buttonRight;
    ImageButton buttonPhoto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manipulation, null);

        buttonDown = (ImageButton) view.findViewById(R.id.arrow_down);
        buttonUp = (ImageButton) view.findViewById(R.id.arrow_up);
        buttonLeft = (ImageButton) view.findViewById(R.id.arrow_left);
        buttonRight = (ImageButton) view.findViewById(R.id.arrow_right);
        buttonPhoto = (ImageButton) view.findViewById(R.id.photo);

        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Down Click", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
