package com.devdungeon.tools.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

/**
 * @author nanodano@devdungeon.com
 */
public class Client {

    /**
     * Get an HttpResponse for an HTTP URL
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static HttpResponse getUrl(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        return httpClient.execute(request);
    }

    /**
     * Save the contents of an HttpResponse to a file on disk
     *
     * @param response
     * @param filepath
     * @throws IOException
     */
    public static void saveResponseToFile(HttpResponse response, String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        FileWriter writer = new FileWriter(filepath);
        try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            String line;
            while ((line = reader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        }
    }

    /**
     * Provide the contents of an HttpResponse as a byte array
     *
     * @param response
     * @return
     * @throws IOException
     */
    public static byte[] convertResponseToBytes(HttpResponse response) throws IOException {
        return IOUtils.toByteArray(response.getEntity().getContent());
    }

    /**
     * Convert the contents of an HttpResponse to base64 encoded byte array
     *
     * @param response
     * @return
     * @throws IOException
     */
    public static byte[] convertResponseToBase64(HttpResponse response) throws IOException {
        return Base64.encodeBase64(convertResponseToBytes(response));
    }

    /**
     * Download an HTTP URL to a file on the filesystem
     *
     * @param url
     * @param filepath
     * @throws IOException
     */
    public static void downloadUrl(String url, String filepath) throws IOException {
        Client.saveResponseToFile(getUrl(url), filepath);

    }

    /**
     * Provide a URL and get the contents as a base64 encoded string
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String getUrlAsBase64(String url) throws IOException {
        return new String(convertResponseToBase64(getUrl(url)), StandardCharsets.UTF_8);
    }

}
