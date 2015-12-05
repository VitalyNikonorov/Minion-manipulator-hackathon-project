package net.nikonorov.behach;

/**
 * Created by vitaly on 05.12.15.
 */

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by vitaly on 17.11.15.
 */
public class NetService extends Service {

    private int PORT = 53000;
    boolean isWork = true;

    private InputStream is;
    private BufferedOutputStream bout;

    private Socket socket;

    final String LOG_TAG = "myLogs";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "doSomeCommand");
        switch (intent.getIntExtra("type", -1)) {
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

    private void sendMessage(Intent intent) {
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

    public void task() {
        Log.d(LOG_TAG, "TASK");
        new Connector().start();
    }

    private class Connector extends Thread {

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

    public class Reader extends Thread {

        Reader() {
        }

        @Override
        public void run() {
            int readBytes = 0;
            byte[] buffer = new byte[1024];
            byte[] accumulator = new byte[0];
            while (isWork) {
                try {
                    readBytes = is.read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (readBytes != -1) {
                    Log.d("Read bytes", "Count: " + String.valueOf(readBytes));
                    byte[] newbuf = new byte[buffer.length + accumulator.length];
                    System.arraycopy(accumulator, 0, newbuf, 0, accumulator.length);
                    System.arraycopy(buffer, 0, newbuf, accumulator.length, buffer.length);
                    accumulator = newbuf;

                    ByteBuffer wrapped = ByteBuffer.wrap(Arrays.copyOfRange(accumulator, 0, 2));
                    short photoLength = wrapped.getShort();

                    savePhoto(Arrays.copyOfRange(accumulator, 2, accumulator.length));
                    /*
                    byte[] c = new byte[a.length + b.length];
                    System.arraycopy(a, 0, c, 0, a.length);
                    System.arraycopy(b, 0, c, a.length, b.length);
                    temp = temp + new String(buffer);
                    */
                }
            }
        }

        public boolean isExternalStorageWritable() {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                return true;
            }
            return false;
        }

        public File getAlbumStorageDir(Context context, String albumName) {
            // Get the directory for the app's private pictures directory.
            File root = Environment.getExternalStorageDirectory();
            File file = new File(root, albumName);
            if (!file.mkdirs()) {
                Log.e("PictureDemo", "Directory not created");
            }
            return file;
        }

        public File savePhoto(byte[] jpegBytes) {
            String fname = String.format("Selfie-%d.jpg", new Date().getTime());

            File photo = new File(getAlbumStorageDir(getApplicationContext(), "Minion"), fname);

            if (photo.exists()) {
                photo.delete();
            }

            try {
                Log.d("Photo", photo.getPath());
                FileOutputStream fos = new FileOutputStream(photo.getPath());
                Log.d("Photo", String.valueOf(jpegBytes.length));
                fos.write(jpegBytes, 0, jpegBytes.length);
                fos.close();
            } catch (java.io.IOException e) {
                Log.e("PictureDemo", "Exception in photoCallback", e);
            }
            return (null);
        }
    }
}
