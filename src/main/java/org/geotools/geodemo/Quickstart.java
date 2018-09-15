/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geotools.geodemo;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.data.JFileDataStoreChooser;

/**
 *
 * @author GaganMGowda
 */
public class Quickstart {

    /**
     * GeoTools Quickstart demo application. Prompts the user for a shapefile and displays its
     * contents on the screen in a map frame
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // display a data store file chooser dialog for shapefiles
            ProcessBuilder pb =
                   new ProcessBuilder("/bin/sh", "-c", "shp2pgsql -s 4326 /Users/GaganMGowda/Desktop/Kaufmann/kaufmann_boundary.shp | psql -U postsdsd -h localhost -p 5432 -d testdraw_ol");
//              Process p = pb.start();
//              
//              System.out.println("Process alive : "+ p.isAlive());
//              System.out.println("Process result:"+ p.getInputStream().toString());

              //ProcessBuilder pb = new ProcessBuilder("echo", "This is helloworld example from Gagan using ProcessBuilder Method");
              System.out.println("Executing the command .....");
              Process process = pb.start();
              int errorCode = process.waitFor();
              
              System.out.println("Echo command executed, any errors ? "+ (errorCode==0?"No":"Yes"));
              //Check output result
              System.out.println("Echo Result:  "+ output(process.getInputStream()));         
              
  }

    private static String output(InputStream inputStream) 
        throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try{
            br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while((line = br.readLine())!= null){
            sb.append(line+System.getProperty("line.seperator"));
          }  
        }
           finally {
            br.close();
        }
        return sb.toString();
    }
}