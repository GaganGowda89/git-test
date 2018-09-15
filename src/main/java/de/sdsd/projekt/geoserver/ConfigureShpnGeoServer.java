/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sdsd.projekt.geoserver;

import java.io.IOException;

/**
 *
 * @author GaganMGowda
 */
public class ConfigureShpnGeoServer {	
	public Boolean run(String filePath) throws IOException, InterruptedException{
		
		//1. STORE THE SHAPE FILES TO POSTGIS
		StoreShapetoPostGis store = new StoreShapetoPostGis();
		
		//2
		ShapeDataSetupnGeoServer conf = new ShapeDataSetupnGeoServer();
		
		Boolean flag = store.Storeshape(filePath);
		Boolean flag_checker;
		if (!flag)
		{
			System.out.println("Ooooh...Something didn't go well...Please try again with a new Shape File");
			flag_checker = false;
		}
		else{
			System.out.println("The Shape file is successfully added into our repository!!!");
			
			
			//2. CONFIGURE THE POSTGIS DATA IN GEOSERVER
			flag_checker = conf.configure();
			if(flag_checker)
				System.out.println("Successfully configured the shapefiles in GeoServer");
			else
				System.out.println("Something went wrong while configuring the shapefiles in GeoServer...");
		}
		
		return flag_checker;
	}
}
