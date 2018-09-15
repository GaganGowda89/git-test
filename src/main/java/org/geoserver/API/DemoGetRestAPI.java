/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geoserver.API;

import org.geoserver.API.User;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.*;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import org.geotools.geodemo.GeoAPIs;
import org.junit.Test;


/**
 *
 * @author GaganMGowda
 */
public class DemoGetRestAPI {
    
    public static void demoGetRESTAPI() throws Exception
    {
    DefaultHttpClient httpClient = new DefaultHttpClient();
    try
    {
        //Define a HttpGet request; You can choose between HttpPost, HttpDelete or HttpPut also.
        //Choice depends on type of method you will be invoking.
        HttpGet getRequest = new HttpGet("http://localhost:8080/geoserver/rest/about/version");
         
        //Set the API media type in http accept header
        //getRequest.addHeader("accept", "text/html");
          
        //Send the request; It will immediately return the response in HttpResponse object
        HttpResponse response = httpClient.execute(getRequest);
         
        //verify the valid error code first
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200)
        {
            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
        }
         
        //Now pull back the response object
        HttpEntity httpEntity = response.getEntity();
        String apiOutput = EntityUtils.toString(httpEntity);
         
        //Lets see what we got from API
        System.out.println(apiOutput); 

       /*
       <user id="admin">
           ﻿<UserName>admin</UserName>
            <Passwd>geoserver</Passwd>
       ﻿</user>
       */ 
        //In realtime programming, you will need to convert this http response to some java object to re-use it.
        //Lets see how to jaxb unmarshal the api response content

        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        User user = (User) jaxbUnmarshaller.unmarshal(new StringReader(apiOutput));
         
        //Verify the populated object
       // System.out.println(user.getId());
        //System.out.println(user.getRESTUSER());
    }
    finally
    {
        //Important: Close the connect
        httpClient.getConnectionManager().shutdown();
    }
}
    
    public static void main(String args[]) throws Exception
    {
    

        DefaultHttpClient httpclient = new DefaultHttpClient();

        String username = "admin";
        String password = "geoserver";
        String host = "localhost";
        String uri = "http://localhost:8080/geoserver/rest/about/version";
        String Posturi = "http://localhost:8080/geoserver/rest/workspaces";
        
        User user = new User();

        try
        {
            httpclient.getCredentialsProvider().setCredentials(new AuthScope(host, AuthScope.ANY_PORT), new UsernamePasswordCredentials(user.RESTUSER, user.RESTPW)); 
//            HttpGet dhttpget = new HttpGet(uri);
//            System.out.println("executing request " + dhttpget.getRequestLine());
//            HttpResponse dresponse = httpclient.execute(dhttpget);
//
//            System.out.println(dresponse.getStatusLine());
            
            System.out.println("--------Picture Abhi Baaki hai mere Dost !! Wait for POST method-----");
            
            HttpPost dhttpost = new HttpPost(Posturi);
            ContentType contentType = ContentType.APPLICATION_XML
               .withCharset("utf-8");
            String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
               + "<workspace><name>Kaufmann_From_Code</name></workspace>";
            dhttpost.setEntity(new ByteArrayEntity(data
               .getBytes(contentType.getCharset()), contentType));
            System.out.println("The url appended is:"+ dhttpost);
            HttpResponse startStubResponse = httpclient.execute(dhttpost);
            
             int startStubStatusCode = startStubResponse.getStatusLine()
               .getStatusCode();
            if (startStubStatusCode < 200 || startStubStatusCode >= 300) {
            // Handle non-2xx status code
            return;
         }
         // If you want to check the status of the stub that is starting, you
         // can use the response data to get the stub instance URI and poll it
         // for updates
         System.out.println(startStubStatusCode);
         String startStubResponseBody = EntityUtils.toString(startStubResponse
               .getEntity());
         System.out.println(startStubResponseBody);
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            httpclient.getConnectionManager().shutdown();
        }

    }
    

    
//public static void demoPostRESTAPI() throws Exception
//{
//    DefaultHttpClient httpClient = new DefaultHttpClient();
//     
//   // User isa class having getter setters 
//    User user = new User();
//    user.setId(100);
//    user.setFirstName("Gagan");
//    user.setLastName("Gowda");
//    user.setRESTUSER("admin");
//    user.setRESTPW("geoserver");
//     
//    StringWriter writer = new StringWriter();
//    JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
//    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//    jaxbMarshaller.marshal(user, writer);
//     
//    try
//    {
//        //Define a postRequest request
//        HttpPost postRequest = new HttpPost("http://localhost:8080/geoserver/rest/workspaces");
//         
//        //Set the API media type in http content-type header
//        postRequest.addHeader("content-type", "application/xml");
//         
//        //Set the request post body
//        StringEntity userEntity = new StringEntity(writer.getBuffer().toString());
//        postRequest.setEntity(userEntity);
//          
//        //Send the request; It will immediately return the response in HttpResponse object if any
//        HttpResponse response = httpClient.execute(postRequest);
//         
//        //verify the valid error code first
//        int statusCode = response.getStatusLine().getStatusCode();
//        if (statusCode != 201)
//        {
//            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
//        }
//    }
//    finally
//    {
//        //Important: Close the connect
//        httpClient.getConnectionManager().shutdown();
//    }
//}    
//      
}
