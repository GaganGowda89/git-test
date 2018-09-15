package de.sdsd.projekt.geoserver;


import de.sdsd.projekt.geoserver.ConfigureShpnGeoServer;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GaganMGowda
 */
public class main {


    public static void main(String args[]) throws IOException, InterruptedException
    {
        String FilePathofShape = "/Users/GaganMGowda/Desktop/Kaufmann/Freese_hinter_der_Bahn_1_workareas.shp";
		
		System.out.println("Configuring the shapefile in GeoServer... Please Wait...!!");
		ConfigureShpnGeoServer conf = new ConfigureShpnGeoServer();
		if(conf.run(FilePathofShape))
			System.out.println("Successfully Uploaded Shape File and Configured GeoServer...!!!");
		else
			System.out.println("Try again..Check if the file alredy exists in GeoServer or PostGIS");
	}
    }
    

