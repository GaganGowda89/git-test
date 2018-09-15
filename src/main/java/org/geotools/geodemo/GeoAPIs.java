/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geotools.geodemo;

import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.encoder.GSLayerEncoder;
import it.geosolutions.geoserver.rest.encoder.GSResourceEncoder;
import it.geosolutions.geoserver.rest.encoder.feature.GSFeatureTypeEncoder;
import it.geosolutions.geoserver.rest.*;
import it.geosolutions.geoserver.rest.decoder.RESTDataStore;
import it.geosolutions.geoserver.rest.manager.GeoServerRESTStoreManager;

import java.net.MalformedURLException;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.stream.Stream;
import org.apache.tools.ant.taskdefs.Length.FileMode;
import static org.junit.Assert.assertTrue;
//import org.springframework.core.io.ClassPathResource;
import it.geosolutions.geoserver.rest.encoder.GSAbstractStoreEncoder;
import it.geosolutions.geoserver.rest.encoder.datastore.GSDirectoryOfShapefilesDatastoreEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author GaganMGowda
 */
public class GeoAPIs {
         
            public static void main(String []args) throws MalformedURLException, FileNotFoundException, URISyntaxException, IOException
            {
                  
                System.out.println("Started creating GeoData configurations......");
                 
                System.out.println("Configuring with user credentials"); 
                
                String RESTURL  = "http://localhost:8080/geoserver";
                String RESTUSER = "admin";
                String RESTPW   = "geoserver";
                String namespace = "GeoREST";
                
                String wsName = "Himmer_Waldhofstr";
                String storeName = "Himmermann_Waldhofstr_workareas";
                String layerName = wsName+":click2shp_out_poly";
                
                URL dsURL = new URL("http://localhost:8080/geoserver/");
                URI sdns = new URI("http://localhost:8080/geoserver/Himmer_Waldhofstr");
                //Setting up the configuration file

                GeoServerRESTReader reader = new GeoServerRESTReader(RESTURL, RESTUSER, RESTPW);
                GeoServerRESTPublisher publisher = new GeoServerRESTPublisher(RESTURL, RESTUSER, RESTPW);
                
                
                GeoServerRESTStoreManager storeManager;
                storeManager = new GeoServerRESTStoreManager(dsURL, RESTUSER, RESTPW);
                
                
                // 1. Creating the workspace
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

                 //2. Creating Namespace
                 boolean exitsnamespace = reader.existsNamespace(namespace);
              if(exitsnamespace){
                      System.out.println("Namespace already exists");
                 }
                 else {
                        boolean createdns = publisher.createNamespace(namespace, sdns);
                       if (createdns)
                        {
                          System.out.println("Successfully created the namespace");
                        }
                 }
                 
               //3.  Create datastore
                  boolean existsdb = reader.existsDatastore(wsName, storeName);
                  
                  
                  if(existsdb)
                  {
                      System.out.println("DataStore already exists");
                      
                    RESTDataStore rds = reader.getDatastore(wsName, storeName);

//                    GSDirectoryOfShapefilesDatastoreEncoder update = new GSDirectoryOfShapefilesDatastoreEncoder(rds);
//                    update.setDescription("SDSD data store"); 
//                    update.setEnabled(true); 
//                    update.setCharset(Charset.forName("UTF-8")); 
//                    update.setCreateSpatialIndex(true);   
//
//                    storeManager.update(wsName, update);
                    System.out.println("Updated data store successfully");
                  }
                  
                  
                // 4. Create a layer from a Shapefile  
                    boolean existslayer = reader.existsLayer(wsName,layerName);
                    
                    if(existslayer){
                        System.out.println("Layer already exists");
                    }
                    else{
                        System.out.println("Creating Layer from Shapefiles");
                        File shapeFile = new File("/Users/GaganMGowda/Downloads/click2shp_out.zip");
                        boolean published = publisher.publishShp(wsName, storeName, layerName, shapeFile);
                        
                      //ZipFile zipFile = new ZipFile("/Users/GaganMGowda/Desktop/Himmermann_Waldhofstr_workareas.zip");

//                         Enumeration<? extends ZipEntry> entries = zipFile.entries();
//
//                          while(entries.hasMoreElements()){
//                              ZipEntry entry = entries.nextElement();
//                              InputStream stream = zipFile.getInputStream(entry);
//                              String result = getStringFromInputStream(stream);
//
//		             System.out.println(result);
//                          }
                             
                        
                        
                         System.out.println("Before Assert  ");
                         assertTrue ("Shapefile read",shapeFile.canRead());
                         System.out.println(shapeFile.list());
                         System.out.println("After Assert ");
//                        URI resource1 = new URI("/Users/GaganMGowda/Desktop/Himmermann_Waldhofstr_workareas.zip");
//                        boolean publishlayer = publisher.publishShpCollection(wsName, storeName,resource1);
                        
                        System.out.println("The createlayer method has value:  "+ published);
                        
                      //  System.out.println("The layer collection method has value:  "+ publishlayer);
                       // assertTrue("publish() failed", published);
               // assertTrue(existsLayer(layerName));
                
                        if(published)
                            System.out.println("Successfully created layer and publised");
                    }  
                  //      if(publishlayer)
                  //          System.out.println("Successfully created layer collection and publised");
                        
                //Creating DataStore
                // creation test
              
               //GSAbstractStoreEncoder geostore = null;
          //        Boolean ds = storeManager.create("Himmermann", geostore.getStoreType());
                
               //   if (ds)
                      //System.out.println("Datastore created");
              
                
                 // 4. Create layer from PostGIS table    
//                boolean ok = publisher.publishDBLayer(wsName, "pg_kids", "kaufmann_through_java", "EPSG:4326", "default_polygon");
//                
//                if (ok)
//                   System.out.println("The layer was created successfully through POSTGIS db");
//                }
                         
//                
                GSFeatureTypeEncoder fte = new GSFeatureTypeEncoder();
                fte.setProjectionPolicy(GSResourceEncoder.ProjectionPolicy.REPROJECT_TO_DECLARED);
                fte.addKeyword("KEYWORD");
                fte.setTitle("Kaufmannds");
                fte.setName("StoreKaufmann");
                fte.setSRS("EPSG:4326");

                final GSLayerEncoder layerEncoder = new GSLayerEncoder();
                layerEncoder.setDefaultStyle("default_polygon");

                boolean ok = publisher.publishDBLayer(wsName, "Kaufmann_boundary123", fte, layerEncoder);
                
                if (ok){
                     System.out.println("Completed creating layer from PostGIS table !!!!!");
                }
               System.out.println("Things..Didn't go well !!!");
          }
//
//    private static boolean existsLayer(String layerName) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    public boolean UploadShapeFile(String workspace, String dsName) throws IOException
//    {
////        String fileUri = zipUri.AbsolutePath;
////        Console.Write(fileUri);
//
//        byte[] localShapeFile = readLocalShapeFile("/Users/GaganMGowda/Desktop/himmerman.zip");
//
//        String sUrl = "http://localhost:8081/geoserver/rest/workspaces/" + 
//                        workspace + "/datastores/" + 
//                        dsName + "/file.shp";
//
//        WebRequest request = WebRequest.Create(sUrl);
//
//        request.ContentType = "application/zip";
//        request.Method = "PUT";
//        request.Credentials = new NetworkCredential("geoserver-username", "passwd");
//
//        Stream requestStream = request.GetRequestStream();
//        requestStream.Write(localShapeFile, 0, localShapeFile.Length);
//        requestStream.Close();
//
//        WebResponse response = request.GetResponse();
//        Console.Write("Response from GeoServer: " + response);
//
//
//        return false;
//    }
//    
//    private static byte[] readLocalShapeFile(String filePath) throws FileNotFoundException, IOException
//    {
//        byte[] buffer;
//        File f = new File(filePath);
//        InputStream fStream = new FileInputStream(f);
//        try {
//            int length = (int)fStream.toString().length();
//            buffer = new byte[length]; 
//            int count;
//            int sum = 0;
//
//            while ((count = fStream.read(buffer, sum, length - sum)) > 0)
//                sum += count;
//        }
//        finally {
//            fStream.close();
//        }
//
//        return buffer;
//    }
//    
//    
//    /**
// * Convert from a filename to a file URL.
// */
//private static String convertToFileURL ( String filename )
//{
//    // On JDK 1.2 and later, simplify this to:
//    // "path = file.toURL().toString()".
//    String path = new File ( filename ).getAbsolutePath ();
//    if ( File.separatorChar != '/' )
//    {
//        path = path.replace ( File.separatorChar, '/' );
//    }
//    if ( !path.startsWith ( "/" ) )
//    {
//        path = "/" + path;
//    }
//    String retVal =  "file:" + path;
//
//    return retVal;
//}
//    

//   private static String getStringFromInputStream(InputStream is) {
//
//		BufferedReader br = null;
//		StringBuilder sb = new StringBuilder();
//
//		String line;
//		try {
//
//			br = new BufferedReader(new InputStreamReader(is));
//			while ((line = br.readLine()) != null) {
//				sb.append(line);
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (br != null) {
//				try {
//					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		return sb.toString();
//
//}

}
