package com.devdungeon.tools.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.*;


/**
 * @author nanodano@devdungeon.com
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("Not implemented.");
    }

    public static void downloadURL(String url, String filepath) {
        System.out.println("Downloading " + url + " to " + filepath);

        HttpClient client = new DefaultHttpClient();

        HttpParams params = client.getParams();
        HttpConnectionParams.setConnectionTimeout(params, 1000 * 5);
        HttpConnectionParams.setSoTimeout(params, 1000 * 5);

        HttpGet request = new HttpGet(url);

        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch (IOException ex) {
            // Ignore
        }
        BufferedReader rd;
        String line = null;
        try {
            rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            FileWriter fileWriter = new FileWriter(filepath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            while ((line = rd.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch (IOException ex) {
            // Ignore
        } catch (UnsupportedOperationException ex) {
            // Ignore
        }
    }
}
