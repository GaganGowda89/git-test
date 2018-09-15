/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geoserver.API;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.geoserver.API.User;

/**
 *
 * @author GaganMGowda
 */
public class PostGeoAPI {
    
    User userGet = new User();
    HttpGeoRESTConnection http = new HttpGeoRESTConnection();
    
    String workspace = "Wesseler_from_POSTCall_Creation";
    String datastore = "kaufmann_range";
    String layername = "kaufmann_through_java"; //Note that it should match the Table name from PostGiS
    
    public HttpResponse PostGeoWorkspace() throws IOException                    
    {
       String uri = userGet.geoURI+"/workspaces"; 
       HttpPost dhttpost = new HttpPost(uri);
       String create_new_workspace = "Wesseler_from_POSTCall_Creation";
            ContentType contentType = ContentType.APPLICATION_XML
               .withCharset("utf-8");
            String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
               + "<workspace><name>"+create_new_workspace+"</name></workspace>";
            dhttpost.setEntity(new ByteArrayEntity(data
               .getBytes(contentType.getCharset()), contentType));
            System.out.println("The url appended is:"+ dhttpost);
            HttpResponse startStubResponse = http.HttpClientConnection().execute(dhttpost);
            
             int startStubStatusCode = startStubResponse.getStatusLine()
               .getStatusCode();
            if (startStubStatusCode < 200 || startStubStatusCode >= 300) {
            // Handle non-2xx status code
                 System.out.println("Something is not right...Handle it !!");
         }
         // If you want to check the status of the stub that is starting, you
         // can use the response data to get the stub instance URI and poll it
         // for updates
         System.out.println(startStubStatusCode);
         String startStubResponseBody = EntityUtils.toString(startStubResponse
               .getEntity());
         System.out.println(startStubResponseBody); 
         
         return startStubResponse;
    }
    
    
    public HttpResponse PostGeoDataStore() throws IOException                    
    {
        
       String create_new_workspace = "Himmer_Waldhofstr"; 
       String uri = userGet.geoURI+"/workspaces/"+create_new_workspace+"/datastores"; 
       System.out.println("The URI is"+ uri);
       HttpPost dhttpost = new HttpPost(uri);
       
            ContentType contentType = ContentType.APPLICATION_XML
               .withCharset("utf-8");
            String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
               + "<dataStore>\n" +
                "  <name>kaufmann_range</name>\n" +
                "  <connectionParameters>\n" +
                "  <host>localhost</host>\n" +
                "  <port>5432</port>\n" +
                "  <database>testdraw_ol</database>\n" +
                "  <user>postsdsd</user>\n" +
                "  <dbtype>postgis</dbtype>\n" +
                "  </connectionParameters>\n" +
                "</dataStore>";
            dhttpost.setEntity(new ByteArrayEntity(data
               .getBytes(contentType.getCharset()), contentType));
            System.out.println("The url appended is:"+ dhttpost);
            HttpResponse startStubResponse = http.HttpClientConnection().execute(dhttpost);
            
            int startStubStatusCode = startStubResponse.getStatusLine()
               .getStatusCode();
            if (startStubStatusCode < 200 || startStubStatusCode >= 300) {
            // Handle non-2xx status code
                 System.out.println("Something is not right...Handle it !!");
         }
         // If you want to check the status of the stub that is starting, you
         // can use the response data to get the stub instance URI and poll it
         // for updates
         System.out.println(startStubStatusCode);
         String startStubResponseBody = EntityUtils.toString(startStubResponse
               .getEntity());
         System.out.println(startStubResponseBody); 
         
         return startStubResponse;
    }
   
    public HttpResponse PostGeoLayer() throws IOException                    
    {
        
       String create_new_workspace = "Himmer_Waldhofstr"; 
       String uri = userGet.geoURI+"/workspaces/"+create_new_workspace+"/datastores/"+datastore+"/featuretypes"; 
       System.out.println("The URI is"+ uri);
       HttpPost dhttpost = new HttpPost(uri);
       
            ContentType contentType = ContentType.APPLICATION_XML
               .withCharset("utf-8");
            String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
               + "<featureType><name>"+layername+"</name><nativeCRS>EPSG:4326</nativeCRS><srs>EPSG:4326</srs><enabled>true</enabled></featureType>";
            dhttpost.setEntity(new ByteArrayEntity(data
               .getBytes(contentType.getCharset()), contentType));
            System.out.println("The url appended is:"+ dhttpost);
            HttpResponse startStubResponse = http.HttpClientConnection().execute(dhttpost);
            
            int startStubStatusCode = startStubResponse.getStatusLine()
               .getStatusCode();
            if (startStubStatusCode < 200 || startStubStatusCode >= 300) {
            // Handle non-2xx status code
                 System.out.println("Something is not right...Handle it !!");
         }
         // If you want to check the status of the stub that is starting, you
         // can use the response data to get the stub instance URI and poll it
         // for updates
         System.out.println(startStubStatusCode);
         String startStubResponseBody = EntityUtils.toString(startStubResponse
               .getEntity());
         System.out.println(startStubResponseBody); 
         
         return startStubResponse;
    }
    
}
