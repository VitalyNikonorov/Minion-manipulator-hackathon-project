package net.nikonorov.behach;

/**
 * Created by vitaly on 05.12.15.
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by vitaly on 17.11.15.
 */
public class NetService extends Service {

    private String                  HOST    = "188.166.49.215";
    private int                     PORT    = 53000;
    boolean                         isWork  = true;

    private InputStream is;
    private BufferedOutputStream bout;

    private Socket socket;

    final String                    LOG_TAG = "myLogs";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");

        new Connector().start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "doSomeCommand");
        switch (intent.getIntExtra("type", -1)){
            case (TaskType.CONNECT): {
                task();
                break;
            }
            case (TaskType.SEND):
                sendMessage(intent);
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void sendMessage(Intent intent){
        try {
            Log.d(LOG_TAG, "Try to send");
            Log.d(LOG_TAG, intent.getStringExtra("data"));
            bout.write(intent.getStringExtra("data").getBytes());
            bout.flush();
            Log.d(LOG_TAG, "gone");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void task(){
        Log.d(LOG_TAG, "TASK");
    }

    private class Connector extends Thread{

        @Override
        public void run() {
            super.run();

            Log.d(LOG_TAG, "Run Connector");
            try {
                socket = new Socket(Settings.getAddress(), PORT);
                is = socket.getInputStream();

                OutputStream os = socket.getOutputStream();
                bout = new BufferedOutputStream(os);
            } catch (IOException e) {
                e.printStackTrace();
            }

            new Reader().start();

        }
    }

    public class Reader extends Thread{

        Reader(){}

        @Override
        public void run() {
            int readBytes = 0;
            byte[] buffer = new byte[2];
            String temp = null;
            int bracketCount = 0;

            StringBuilder sb = new StringBuilder();
            while (isWork) {
                try {
                    readBytes = is.read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (readBytes != -1) {

                    temp = new String(buffer);

                    if (temp.lastIndexOf("{") != -1){
                        if (temp.lastIndexOf("{") != temp.indexOf("{")) {
                            bracketCount += 2;
                        }else{
                            bracketCount++;
                        }
                    }

                    if (temp.lastIndexOf("}") != -1){
                        if (temp.lastIndexOf("}") != temp.indexOf("}")) {
                            bracketCount -= 2;
                        }else{
                            bracketCount--;
                        }
                    }

                    int index = temp.lastIndexOf("}");

                    if (index == -1 ){
                        sb.append(temp);
                        //Log.d(LOG_TAG, sb.toString());
                    }else{
                        if( bracketCount == 0 ){
                            sb.append(temp.substring(0, index + 1));
                            Log.d(LOG_TAG, sb.toString());
//                            Intent intent = new Intent(ActivityBase.BROADCAST_EVENT);
//                            intent.putExtra("data", sb.toString());
//                            sendBroadcast(intent);
                            sb.setLength(0);
                        }else{
                            sb.append(temp);
                            //Log.d(LOG_TAG, sb.toString());
                        }
                    }
                }
            }
        }
    }

}
