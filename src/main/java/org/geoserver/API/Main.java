/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geoserver.API;

import java.io.IOException;
import org.geoserver.API.*;
import org.apache.http.client.HttpClient;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author GaganMGowda
 */
public class Main {
    
    public static void main(String [] args) throws IOException
    {
        
        //Call the HTTPGet, HTTPost, HTTPDelete methods on GeoServer
        
      //  try{
        HttpGeoRESTConnection http = new HttpGeoRESTConnection();
        System.out.println("Get the GeoServer Versions");
        PostGeoAPI post = new PostGeoAPI();
        GetGeoAPI a = new GetGeoAPI();
        a.GetGeoServerVersion();
       // System.out.println("Creating datastore...!!!!!");
        
        //Check if it already exists !!
        //post.PostGeoDataStore();
        //System.out.println("Datastore created...!!!!!");
        
        //Creating Layer
        System.out.println("Creating Layer...!!!!!");
        post.PostGeoLayer();
        System.out.println("Layer created...!!!!!");
       // }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        // finally
        //{
            http.HttpClientConnection().getConnectionManager().shutdown();
       // }
 
    }
}
    
 
