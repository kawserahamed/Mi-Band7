package com.ahamed.miband6.utils;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class FileDownloader {

    int x = 1;
    public void downloadImages(List<String> files) {
        for (String file : files) {
            new FileDownloader.download(x).execute(file);
            x++;
        }
    }
    private static class download extends AsyncTask<String, Void, Void>{
        int x;

        public download( int x) {

            this.x = x;
        }


        @Override
        protected Void doInBackground(String... params) {
            String url = params[0];
            String folderPath = Environment.getExternalStorageDirectory() + "/Band4Data/";
            try {
                // Create the folder if it doesn't exist
                File folder = new File(folderPath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                URL imageUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                conn.connect();

                InputStream inputStream = conn.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

                // Get the file name from the URL
                 String fileName = url.substring(url.lastIndexOf("/") + 1);
               // String fileName = "image" + x + ".gif";

                // Create the file in the specified folder
                File outputFile = new File(folderPath, fileName);

                FileOutputStream fileOutputStream = new FileOutputStream(outputFile);

                byte[] buffer = new byte[1024];
                int len;
                while ((len = bufferedInputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                }

                fileOutputStream.flush();
                fileOutputStream.close();
                bufferedInputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
