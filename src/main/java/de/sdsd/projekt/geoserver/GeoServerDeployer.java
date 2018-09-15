/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sdsd.projekt.geoserver;


import de.sdsd.projekt.geoserver.main;
import java.io.File;
import org.apache.catalina.Context;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.scan.StandardJarScanner;

/**
 *
 * @author GaganMGowda
 */
public class GeoServerDeployer {
	
	
	public final static File GEO_SERVER_JAR = new File("geoserver.war");
	
//	public File deploy(ServerManager serverManager, int port) throws Exception {
//		//Wikinormia
//		
//		//install
//		
//		FileUtils.deleteQuietly(new File("geoserver.log"));
//		
//		File tomcatFolder = new File("tomcat." + port);
//		FileUtils.deleteQuietly(tomcatFolder);
//		
//		File webappsFolder = new File(tomcatFolder, "webapps");
//		webappsFolder.mkdirs();
//		
//		//copy war
//		FileUtils.copyInputStreamToFile(Main.class.getResourceAsStream("/geodata/geoserver.war"), GEO_SERVER_JAR);		
//		//deploy
//		Context ctx = serverManager.getTomcatServer().getTomcat().addWebapp("/geoserver", GEO_SERVER_JAR.getAbsolutePath());
//		
//		//faster ignore class path
//		((StandardJarScanner) ctx.getJarScanner()).setScanClassPath(false);
//		
//		return new File(webappsFolder, "geoserver");
//	}
	
}

