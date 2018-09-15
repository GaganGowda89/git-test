/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geoserver.API;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.geoserver.API.User;
/**
 *
 * @author GaganMGowda
 */
public class GetGeoAPI {
    
    User userGet = new User();
    HttpGeoRESTConnection http = new HttpGeoRESTConnection();
    
    public  HttpResponse GetGeoServerVersion() throws IOException
    {
        String uri = userGet.geoURI+"/about/version";
  
        HttpGet dhttpget = new HttpGet(uri);
        System.out.println("executing request " + dhttpget.getRequestLine());
        HttpResponse dresponse =  http.HttpClientConnection().execute(dhttpget);
//
        System.out.println(dresponse.getStatusLine());
    
      return dresponse;
    }
    
    public HttpResponse GetListofGeoWorkspace() throws IOException
    {
        HttpGeoRESTConnection http = new HttpGeoRESTConnection();
    
        String uri = userGet.geoURI+"/workspaces";
  
        HttpGet dhttpget = new HttpGet(uri);
        System.out.println("executing request " + dhttpget.getRequestLine());
        HttpResponse dresponse =  http.HttpClientConnection().execute(dhttpget);
//
        System.out.println(dresponse.getStatusLine());
    
       return dresponse;
    }
    
}
