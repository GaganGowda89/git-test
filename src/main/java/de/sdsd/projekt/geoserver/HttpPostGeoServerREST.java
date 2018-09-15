/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sdsd.projekt.geoserver;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author GaganMGowda
 */
public class HttpPostGeoServerREST {
	
    Userdetails userGet = new Userdetails();
    HttpGeoRESTConnection http = new HttpGeoRESTConnection();
    boolean flag = false;
    String layerName = "Freese_hinter_der_Bahn_1_workareas";
    String dataStoreName = "Freese_hinter_der_Bahn_1_workareas_ds";
    
    public boolean PostGeoWorkspace() throws IOException                    
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
				 flag = false;
         }
			else{
				flag = true;
			}
         // If you want to check the status of the stub that is starting, you
         // can use the response data to get the stub instance URI and poll it
         // for updates
         System.out.println(startStubStatusCode);
         String startStubResponseBody = EntityUtils.toString(startStubResponse
               .getEntity());
         System.out.println(startStubResponseBody);     
       return flag;
    }
    
    
    public boolean PostGeoDataStore() throws IOException                    
    {
        
       String uri = userGet.geoURI+"/workspaces/"+userGet.workspace+"/datastores"; 
       System.out.println("The URI is"+ uri);
       HttpPost dhttpost = new HttpPost(uri);
       
            ContentType contentType = ContentType.APPLICATION_XML
               .withCharset("utf-8");
            String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
               + "<dataStore>\n" +
                "  <name>"+dataStoreName+"</name>\n" +
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
				 flag = false;
            }
			else {
				flag = true;
			}
         // If you want to check the status of the stub that is starting, you
         // can use the response data to get the stub instance URI and poll it
         // for updates
         System.out.println(startStubStatusCode);
         String startStubResponseBody = EntityUtils.toString(startStubResponse
               .getEntity());
         System.out.println(startStubResponseBody);
		 
         
       return flag;
    }
   
    public boolean PostGeoLayer() throws IOException                    
    {
         
       String uri = userGet.geoURI+"/workspaces/"+userGet.workspace+"/datastores/"+dataStoreName+"/featuretypes"; 
       System.out.println("The URI is"+ uri);
       HttpPost dhttpost = new HttpPost(uri);
       
            ContentType contentType = ContentType.APPLICATION_XML
               .withCharset("utf-8");
            String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
               + "<featureType><name>"+layerName+"</name><nativeCRS>EPSG:4326</nativeCRS><srs>EPSG:4326</srs><enabled>true</enabled></featureType>";
            dhttpost.setEntity(new ByteArrayEntity(data
               .getBytes(contentType.getCharset()), contentType));
            System.out.println("The url appended is:"+ dhttpost);
            HttpResponse startStubResponse = http.HttpClientConnection().execute(dhttpost);
            
            int startStubStatusCode = startStubResponse.getStatusLine()
               .getStatusCode();
            if (startStubStatusCode < 200 || startStubStatusCode >= 300) {
            // Handle non-2xx status code
                 System.out.println("Something is not right...Handle it !!");
				 flag = false;
         } else{
				flag = true;
			}
         // If you want to check the status of the stub that is starting, you
         // can use the response data to get the stub instance URI and poll it
         // for updates
         System.out.println(startStubStatusCode);
         String startStubResponseBody = EntityUtils.toString(startStubResponse
               .getEntity());
         System.out.println(startStubResponseBody); 
         
         return flag;
    }
	
}
