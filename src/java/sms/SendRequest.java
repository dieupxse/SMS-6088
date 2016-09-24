/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sms;

import common.Common;
import common.Config;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author jacob
 */
public class SendRequest {
    /**
     * 
     * @param moId
     * @param sender
     * @param serviceId
     * @param receiver
     * @param contentType
     * @param messageType
     * @param username
     * @param messageIndex
     * @param message
     * @param operator
     * @param commandCode
     * @return
     * @throws URISyntaxException
     * @throws IOException 
     */
    public String sendRequests(String moId, 
            String sender, 
            String serviceId,
            String message,
            String operator,
            String commandCode,
            String username
                    ) throws URISyntaxException, IOException  {     
        Date d = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        URI uri = new URIBuilder()
        .setScheme("http")
        .setHost("222.255.167.6:8080")
        .setPath("/SMS/Ctnet/sms")
        .setParameter("mo_id", moId)
        .setParameter("username", username)
        .setParameter("key", Common.getMD5(username+"|"+moId+"|ctnet2015"))
        .setParameter("sender", sender)
        .setParameter("serviceId", serviceId)
        .setParameter("message", message)
        .setParameter("operator", operator)
        .setParameter("commandCode", commandCode)
        .build();
        HttpGet httpPost = new HttpGet(uri);
        httpPost.addHeader("content-type","application/x-www-form-urlencoded");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(httpPost);
        
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                long len = entity.getContentLength();
                if (len != -1 && len < 2048) {
                    return EntityUtils.toString(entity);
                } else {
                    // Stream content out
                }
            }
        } finally {
            response.close();
        }
       return "";
    }
}
