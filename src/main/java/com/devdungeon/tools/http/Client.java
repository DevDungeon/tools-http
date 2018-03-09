package com.devdungeon.tools.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;

/**
 * @author nanodano@devdungeon.com
 */
public class Client {

    public static void main(String[] args) {
        try {
            downloadURL("https://www.devdungeon.com", "test.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downloadURL(String url, String filepath) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);

        BufferedReader reader;
        String line;
        reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        FileWriter fileWriter = new FileWriter(filepath);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        while ((line = reader.readLine()) != null) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }



}
