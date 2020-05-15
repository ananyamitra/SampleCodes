package com.test.rest;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class OAuthHandler {

    private static final Pattern pat = Pattern.compile(".*\"access_token\"\\s*:\\s*\"([^\"]+)\".*");
    private static final String clientId = "Put client Id here";//clientId
    private static final String tokenUrl = "Put Token URL here";

    private static String getTokenfromAuthServer() {
        String content = "grant_type=client_credentials&client_id=" + clientId+"&scope=openid%20profile&Client%20Authentication=Send_as_Basic_Auth_header";
        BufferedReader reader = null;
        HttpsURLConnection connection = null;
        String returnValue = "";
        try {
            URL url = new URL(tokenUrl);
            connection = (HttpsURLConnection) url.openConnection();

            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            connection.setSSLSocketFactory(sslsocketfactory);

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept", "application/json");
            PrintStream os = new PrintStream(connection.getOutputStream());
            os.print(content);
            os.close();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            StringWriter out = new StringWriter(connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            String response = out.toString();
            System.out.println(response);
            Matcher matcher = pat.matcher(response);
            if (matcher.matches() && matcher.groupCount() > 0) {
                returnValue = matcher.group(1);
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
            connection.disconnect();
        }
        return returnValue;
    }

    public String getToken() {

        return "Bearer " + getTokenfromAuthServer();

    }
}


