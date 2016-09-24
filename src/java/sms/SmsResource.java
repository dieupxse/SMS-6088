/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sms;

import bean.SMS;
import bean.User;
import bo.SmsBO;
import bo.UserBO;
import common.Common;
import common.Config;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

/**
 * REST Web Service
 *
 * @author jacob
 */
//@Stateless
@Path("sms")
public class SmsResource {
    private static Logger logger = Logger
	        .getLogger(SmsResource.class);
    @Context
    private UriInfo context;
    private UserBO userBO = new UserBO();
    private SmsBO smsBO = new SmsBO();
    /**
     * Creates a new instance of SmsResource
     */
    public SmsResource() {
    }

    /**
     * Retrieves representation of an instance of sms.SmsResource
     * @param moId
     * @param sender
     * @param serviceId
     * @param message
     * @param operator
     * @param commandCode
     * @param key
     * @param username
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces("text/plain")    
    public Response getText(
            @DefaultValue("") @QueryParam("mo_id") String moId,
            @DefaultValue("") @QueryParam("sender") String sender,
            @DefaultValue("") @QueryParam("serviceId") String serviceId,
            @DefaultValue("") @QueryParam("message") String message,
            @DefaultValue("") @QueryParam("operator") String operator,
            @DefaultValue("") @QueryParam("commandCode") String commandCode,
            @DefaultValue("") @QueryParam("key") String key,
            @DefaultValue("") @QueryParam("username") String username
    ) throws Exception {
        return postText(moId, key, sender, serviceId, message, operator, commandCode, username);
    }

    /**
     *
     * @param moId
     * @param sender
     * @param serviceId
     * @param message
     * @param operator
     * @param commandCode
     * @param key
     * @param username
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @POST
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postText(
            @DefaultValue("") @QueryParam("mo_id") String moId,
            @DefaultValue("") @QueryParam("key") String key,
            @DefaultValue("") @QueryParam("sender") String sender,
            @DefaultValue("") @QueryParam("serviceId") String serviceId,
            @DefaultValue("") @QueryParam("message") String message,
            @DefaultValue("") @QueryParam("operator") String operator,
            @DefaultValue("") @QueryParam("commandCode") String commandCode,
            @DefaultValue("") @QueryParam("username") String username
    ) throws Exception {
        User user = userBO.getUser(username);
        String token = Common.getMD5(user.getUsername()+"|"+moId+"|"+user.getPassword());
        System.out.println("token: "+token);
        System.out.println("key: "+key);
        System.err.println("moid = "+moId);
        if(key.equals(token)) {
            if("".equals(moId) || "".equals(sender) || "".equals(serviceId) || "".equals(message) || "".equals(operator) || "".equals(commandCode)) {
                return Response.status(200).entity("2").build(); // thieu truong du lieu
            } else {
                SMS sms = new SMS();
                sms.setCommandCode(commandCode);
                sms.setIsProcess(0);
                sms.setMessage(message);
                sms.setMoId(moId);
                sms.setOperator(operator);
                sms.setSender(sender);
                sms.setServiceId(serviceId);
                sms.setUsername(username);
                String return_msg = "";
                String[] dv = message.split(" ");
                Common cm = new Common();
                if(dv.length <2) {
                    if(smsBO.insert_error(sms)) {
                    return_msg = "Cu phap khong dung. Vui long soan ON<daucach>Madichvu gui 6088. Thong tin chi tiet tai dichvudidong.vn. Tran trong !";
                    String sendRequests = cm.sendRequests(
                                moId, //moid
                                sender,//sender
                                serviceId,//service id
                                sender,//Receiver
                                "0",//content Type 
                                "1",//message type 
                                "1",//message index
                                return_msg,//message
                                operator,//operator
                                "ON"//command code
                        );
                        String[] str = sendRequests.split("-");
                        if(str.length<2) return Response.status(200).entity("4").build();//khong goi tin MT duoc
                        return Response.status(200).entity(str[0]).build();//return code
                    } else {
                        return Response.status(200).entity("3").build();//khong goi tin MT duoc
                    }
                } else {
                    if(smsBO.insert(sms)) {
                        
                            if(operator.equals("GPC")) {
                                return_msg = "Yeu cau dang ky dich vu "+dv[1].toUpperCase()+" da duoc gui di, vui long doi tin nhan tu tong dai 1543 va lam theo huong dan. Cam on ban da su dung dich vu cua Vinaphone!";
                            } else {
                                return_msg = "Yeu cau dang ky dich vu "+dv[1].toUpperCase()+" da duoc gui di, vui long doi tin nhan tu tong dai va lam theo huong dan. Dichvudidong.vn cam on ban!";
                            }

                        String sendRequests = cm.sendRequests(
                                moId, //moid
                                sender,//sender
                                serviceId,//service id
                                sender,//Receiver
                                "0",//content Type 
                                "1",//message type 
                                "1",//message index
                                return_msg,//message
                                operator,//operator
                                "ON"//command code
                        );
                        System.out.println("Result send MT: "+sendRequests);
                        String[] str = sendRequests.split("-");
                        
                        if(str.length<2) return Response.status(200).entity("4").build();//khong goi tin MT duoc
                        
                        return Response.status(200).entity(str[0]).build();//return code 
                        
                        //return Response.status(200).entity("0").build();//return code
                        
                    } else {
                        return  Response.status(200).entity("3").build();//Loi he thong
                    }
                }
                
                /* tam ngung he thong
                String return_msg = "Hien nay VINAPHONE tam ngung kenh dang ky dich vu nay de nang cap. Mong quy khach thong cam.";
                Common cm = new Common();
                String sendRequests = cm.sendRequests(
                                moId, //moid
                                sender,//sender
                                serviceId,//service id
                                sender,//Receiver
                                "0",//content Type 
                                "1",//message type 
                                "1",//message index
                                return_msg,//message
                                operator,//operator
                                "ON"//command code
                        );
                return Response.status(200).entity("0").build();
                //end tam ngung he thong
                */
            }
        }else {
            System.out.println("Token khong dung");
            return Response.status(200).entity("1").build();//token khong dung
        }
    }
    /**
     * PUT method for updating or creating an instance of SmsResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    public Response putText(
        @DefaultValue("") @QueryParam("mo_id") String moId,
        @DefaultValue("") @QueryParam("key") String key,
        @DefaultValue("") @QueryParam("sender") String sender,
        @DefaultValue("") @QueryParam("serviceId") String serviceId,
        @DefaultValue("") @QueryParam("message") String message,
        @DefaultValue("") @QueryParam("operator") String operator,
        @DefaultValue("") @QueryParam("commandCode") String commandCode,
        @DefaultValue("") @QueryParam("username") String username
    ) throws Exception{
        return postText(moId, key, sender, serviceId, message, operator, commandCode, username);
    }
}
