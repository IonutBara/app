package com.mycompany.myapp.web.rest.util;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by ibara on 3/17/2017.
 */
public class RestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

    static {
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509ExtendedTrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s, Socket socket) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s, Socket socket) throws CertificateException {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s, SSLEngine sslEngine) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s, SSLEngine sslEngine) throws CertificateException {
                }
            }
        };

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("NoSuchAlgorithmException while by passing SSL. : {}", e);
        } catch (KeyManagementException e) {
            LOGGER.error("KeyManagementException while by passing SSL. : {}", e);
        }
        assert sslContext != null;
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
    }

    private String username;
    private String passsword;
    private String url;
    private String requestMethod;
    private JSONObject payload;

    private RestClient(String username, String passsword, String url, String requestMethod, JSONObject payload) {
        this.username = username;
        this.passsword = passsword;
        this.url = url;
        this.requestMethod = requestMethod;
        this.payload = payload;
    }

    private RestClient() {
    }

/*    public static void main(String[] args) {
        RestClient c = new RestClient("admin", "admin", "https://10.241.6.108/nos/api/sysinfo/fans", "GET", null);
        c.request();
    }*/

    /**
     * This is a helper Java class for doing GET, POST, PUT, DELETE requests
     * This will be invoked only when the application will do a REST call to a url
     */
    public void request() {
        HttpURLConnection conn = null;
        try {
            URL url1 = new URL(url);
            conn = (HttpURLConnection) url1.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            String authString = username + ":" + passsword;
            String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
            //LOGGER.debug("Base64 encoded auth string: " + authStringEnc);
            conn.setRequestProperty("Authorization", "Basic " + authStringEnc);

            if (conn.getResponseCode() != HttpServletResponse.SC_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode() + conn.getResponseMessage());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

            String output;
            LOGGER.debug("Output from Server ....");
            while ((output = br.readLine()) != null) {
                LOGGER.debug(output);
            }
        } catch (MalformedURLException e) {
            LOGGER.error("MalformedURLException :{}", e);
        } catch (IOException e) {
            LOGGER.error("IOException :{}", e);
        } finally {
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                    LOGGER.error("IOException while closing HttpURLConnection :{}", e);
                }
            }
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }

    public JSONObject getPayload() {
        return payload;
    }

    public void setPayload(JSONObject payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "RestClient{" +
            "username='" + username + '\'' +
            ", passsword='" + passsword + '\'' +
            ", url='" + url + '\'' +
            ", requestMethod='" + requestMethod + '\'' +
            ", payload=" + payload +
            '}';
    }
}
