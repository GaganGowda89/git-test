/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sdsd.projekt.geoserver;

import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.encoder.GSLayerEncoder;
import it.geosolutions.geoserver.rest.encoder.GSResourceEncoder;
import it.geosolutions.geoserver.rest.encoder.feature.GSFeatureTypeEncoder;
import it.geosolutions.geoserver.rest.*;
import it.geosolutions.geoserver.rest.decoder.RESTDataStore;
import it.geosolutions.geoserver.rest.manager.GeoServerRESTStoreManager;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * @author GaganMGowda
 */
public  class ShapeDataSetupnGeoServer {
	
	
	
	//Configure in GeoServer iteratively
	public Boolean configure() throws MalformedURLException, IOException
	{
		Userdetails user = new Userdetails();
		HttpPostGeoServerREST geo = new HttpPostGeoServerREST();
		
		
		System.out.println("Configuring the data in GeoServer step by step");
		
		//1. Create a new or a common workspace (Assuming different workspace for all user)
		String RESTUSER = user.RESTUSER;
                String RESTPW   = user.RESTPW;
		String RESTURL  = user.geoURI;
		String wsName   = user.workspace;
                String layerName = "Freese_hinter_der_Bahn_1_workareas";
	        String storeName = "Freese_hinter_der_Bahn_1_workareas_ds";
		//String storeName = user.getdataStoreName();
		//String layerName = user.getlayerName();
		
		GeoServerRESTReader reader = new GeoServerRESTReader(RESTURL, RESTUSER, RESTPW);
                GeoServerRESTPublisher publisher = new GeoServerRESTPublisher(RESTURL, RESTUSER, RESTPW);
       
		//1. Check if the workspace already exists or create a new one !!!
		boolean existsws = reader.existsWorkspace(wsName);
                 if(existsws){
                      System.out.println("Workspace already exists");
                 }
                 else {
                        boolean created = publisher.createWorkspace(wsName);
                       if (created)
                        {
                          System.out.println("Successfully created the workspace");
                        }
                 }
				 
	    //2. Check if the workspace contains the datastore or create a new datastore !!!
	    boolean existsdb = reader.existsDatastore(wsName, storeName); 
                  
                  if(existsdb)
                  {
                      System.out.println("DataStore already exists");
                      
				  } else {
					  //Call the HttpREST method for creating the DataStore
					  boolean created = geo.PostGeoDataStore();
					  if (created)
                        {
                          System.out.println("Successfully created the DataStore from PostGis");
                        }
				  }
         //3.  Check if the workspace contains the layerName or create a new layername based on the above datastore !!!       			 
		 boolean existslayer = reader.existsLayer(wsName,layerName);
                    
                    if(existslayer){
                        System.out.println("Layer already exists");
                    }
                    else{
                         boolean created = geo.PostGeoLayer();
						 if (created)
                        {
                          System.out.println("Successfully created the DataStore from PostGis");
                        }else
						 {
							 System.out.println("Seems the layer already exists....Check the logs");
							 
						 }
		            
					}
		return (existsws && existsdb && existslayer );
	}
	
}
