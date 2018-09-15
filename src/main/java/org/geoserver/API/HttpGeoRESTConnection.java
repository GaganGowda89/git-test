/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geoserver.API;


import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;


/**
 *
 * @author GaganMGowda
 */
public class HttpGeoRESTConnection {
    
    /**
     *Returns HTTP Connection bases on the static user credentials
     */
    public static HttpClient HttpClientConnection()
    {
    
        String username = "admin";
        String password = "geoserver";
        String host = "localhost";
        String uri = "http://localhost:8080/geoserver/rest/about/version";
        String Posturi = "http://localhost:8080/geoserver/rest/workspaces";
        
        User user = new User();
        
        DefaultHttpClient httpclient = new DefaultHttpClient();
   
         httpclient.getCredentialsProvider().setCredentials(new AuthScope(host, AuthScope.ANY_PORT), new UsernamePasswordCredentials(user.RESTUSER, user.RESTPW)); 
//            HttpGet dhttpget = new HttpGet(uri);
        
       
        return httpclient;
    }
    
}
