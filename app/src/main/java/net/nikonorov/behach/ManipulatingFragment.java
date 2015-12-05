package net.nikonorov.behach;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
    ImageButton buttonLower;
    ImageButton buttonMusic;

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
        buttonLower = (ImageButton) view.findViewById(R.id.lower);
        buttonMusic = (ImageButton) view.findViewById(R.id.music);

        buttonUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(getActivity(), "Up Click", Toast.LENGTH_SHORT).show();
                        sendMessageToMinion("<forward>");
                        break;
                    case MotionEvent.ACTION_UP:
                        sendMessageToMinion("</forward>");
                        break;
                }
                return false;
            }
        });

        buttonDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(getActivity(), "Down Click", Toast.LENGTH_SHORT).show();
                        sendMessageToMinion("<backward>");
                        break;
                    case MotionEvent.ACTION_UP:
                        sendMessageToMinion("</backward>");
                        break;
                }
                return false;
            }
        });

        buttonLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(getActivity(), "Left Click", Toast.LENGTH_SHORT).show();
                        sendMessageToMinion("<left>");
                        break;
                    case MotionEvent.ACTION_UP:
                        sendMessageToMinion("</left>");
                        break;
                }
                return false;
            }
        });

        buttonRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(getActivity(), "Right Click", Toast.LENGTH_SHORT).show();
                        sendMessageToMinion("<right>");
                        break;
                    case MotionEvent.ACTION_UP:
                        sendMessageToMinion("</right>");
                        break;
                }
                return false;
            }
        });

        buttonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Photo Click", Toast.LENGTH_SHORT).show();
                sendMessageToMinion("<photo>");
            }
        });

        buttonLower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Lower Click", Toast.LENGTH_SHORT).show();
                sendMessageToMinion("<lower>");
            }
        });

        buttonMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Music Click", Toast.LENGTH_SHORT).show();
                sendMessageToMinion("<music>");
            }
        });

        return view;
    }

    public void sendMessageToMinion(String data){
        MainActivity activity = (MainActivity) getActivity();
        activity.sendMessageToMinion(data);
    }
}
