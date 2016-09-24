/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

import bean.SMS;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import org.apache.log4j.Logger;
import sms.SendRequest;

/**
 *
 * @author jacob
 */
public class test {
    private static Logger logger = Logger
	        .getLogger(test.class);
    
    public static void main(String[] args) throws UnsupportedEncodingException, IOException, URISyntaxException {
        Common cm = new Common();
        /* String sendRequests = cm.sendRequests(
                                "933119", //moid
                                "84903555974",//sender
                                "6088",//service id
                                "84903555974",//Receiver
                                "0",//content Type 
                                "1",//message type 
                                "1",//message index
                                "test",//message
                                "VMS",//operator
                                "ON"//command code
                        );
        */
        SendRequest sr = new SendRequest();
        //String rs = sr.sendRequests("123456", "84903555974", "6088", "test msg", "VMS", "ON", "ctnet");
        //System.out.println(rs);
        System.out.println(Common.getMD5("ctnet|933119|ctnet2015"));
}
}
