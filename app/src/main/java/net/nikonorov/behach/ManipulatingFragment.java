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

//        buttonDown.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Down Click", Toast.LENGTH_SHORT).show();
//                sendMessageToMinion("<down></down>");
//            }
//        });

        buttonDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    Toast.makeText(getActivity(), "Down Downed", Toast.LENGTH_SHORT).show();
                    sendMessageToMinion("<down>");
                }else if (event.getAction() == MotionEvent.ACTION_UP){
                    Toast.makeText(getActivity(), "Down Uped", Toast.LENGTH_SHORT).show();
                    sendMessageToMinion("</down>");
                }
                return false;
            }
        });

        buttonUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Toast.makeText(getActivity(), "Up Downed", Toast.LENGTH_SHORT).show();
                    sendMessageToMinion("<up>");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Toast.makeText(getActivity(), "Up Uped", Toast.LENGTH_SHORT).show();
                    sendMessageToMinion("</up>");
                }
                return false;
            }
        });

        buttonLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    Toast.makeText(getActivity(), "Left Downed", Toast.LENGTH_SHORT).show();
                    sendMessageToMinion("<left>");
                }else if (event.getAction() == MotionEvent.ACTION_UP){
                    Toast.makeText(getActivity(), "Left Uped", Toast.LENGTH_SHORT).show();
                    sendMessageToMinion("</left>");
                }
                return false;
            }
        });

        buttonRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    Toast.makeText(getActivity(), "Right Downed", Toast.LENGTH_SHORT).show();
                    sendMessageToMinion("<right>");
                }else if (event.getAction() == MotionEvent.ACTION_UP){
                    Toast.makeText(getActivity(), "Right Uped", Toast.LENGTH_SHORT).show();
                    sendMessageToMinion("</right>");
                }
                return false;
            }
        });




        buttonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Photo Click", Toast.LENGTH_SHORT).show();
                sendMessageToMinion("<take picture></take picture>");
            }
        });

        return view;
    }

    public void sendMessageToMinion(String data){
        MainActivity activity = (MainActivity) getActivity();
        activity.sendMessageToMinion(data);

    }
}
