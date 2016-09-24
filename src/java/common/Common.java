/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jacob
 */
public class Common {
   public static String getMD5(String input){
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] output = md.digest();
            for (int i = 0; i < output.length; i++) {
                sb.append(Integer.toString((output[i] & 0xff) + 0x100, 
                16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
        }
        return sb.toString();
    }
   /**
    * send request
    * @param moId
    * @param userName
    * @param key
    * @param sender
    * @param serviceId
    * @param receiver
    * @param contentType
    * @param messageType
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
            String receiver,
            String contentType,
            String messageType,
            String messageIndex,
            String message,
            String operator,
            String commandCode
                    ) {     
        Date d = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            WebResource webResource;
            Client client = Client.create();
            webResource = client.resource("http://service.ahp.vn:6688/service/ahp/mt6x88");
            /**
             *  .setParameter("mo_id", moId)
                .setParameter("userName", Config.USER)
                .setParameter("key", Common.getMD5(Config.USER+"|"+moId+"|"+Config.PASSWORD))
                .setParameter("sender", sender)
                .setParameter("serviceId", serviceId)
                .setParameter("receiver", receiver)
                .setParameter("contentType",contentType)
                .setParameter("messageType",messageType)
                .setParameter("messageIndex",messageIndex)
                .setParameter("requestTime",fm.format(d))
                .setParameter("message", message)
                .setParameter("operator", operator)
                .setParameter("commandCode", commandCode)
             * 
             * 
             */
            
            ClientResponse response =  
                            webResource
                            .queryParam("mo_id", moId)
                            .queryParam("userName", Config.USER)
                            .queryParam("key", Common.getMD5(Config.USER+"|"+moId+"|"+Config.PASSWORD))
                            .queryParam("sender", sender)
                            .queryParam("serviceId", serviceId)
                            .queryParam("receiver", receiver)
                            .queryParam("contentType",contentType)
                            .queryParam("messageType",messageType)
                            .queryParam("messageIndex",messageIndex)
                            .queryParam("requestTime",fm.format(d))
                            .queryParam("message", message)
                            .queryParam("operator", operator)
                            .queryParam("commandCode", commandCode)
                            .accept(MediaType.MULTIPART_FORM_DATA)
                            .get(ClientResponse.class);
       
        return response.getEntity(String.class);
    }
  
}