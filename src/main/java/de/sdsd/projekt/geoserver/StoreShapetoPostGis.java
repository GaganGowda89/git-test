/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sdsd.projekt.geoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author GaganMGowda
 */
class StoreShapetoPostGis {

 public Boolean Storeshape(String filePath) throws IOException, InterruptedException {

     
     System.out.println("Entering storeshape method");
	Userdetails user = new Userdetails();
    //set the env path for executing shp2pgsql 	 
    String env = "/usr/local/bin/";
    
    System.out.println("value of layername:"+ user.getlayerName());
    System.out.println("value of datastorename:"+ user.getdataStoreName());
    System.out.println("Vlaue of FilePath:" + filePath);
    //Extract the datastore Name
    user.setdataStoreName(getFileName(filePath)+"_ds");
	//Extract the Layer Name == FileName
    user.setlayerName(getFileName(filePath));
    
	//Trial check 
	System.out.println("Checking the URL is fine or not...: /bin/sh -c"+ env +"shp2pgsql -s 4326 "+ filePath +" "+ user.getlayerName() +" | "+env+"psql -U postsdsd -h localhost -p 5432 -d testdraw_ol");
	
    ProcessBuilder pb =
                   new ProcessBuilder("/bin/sh", "-c", env +"shp2pgsql -s 4326 "+ filePath +" "+user.getlayerName() +" | "+env+"psql -U postsdsd -h localhost -p 5432 -d testdraw_ol");
    Process p = pb.start();
    showOutput(p.getInputStream(), System.out);
    showOutput(p.getErrorStream(), System.err);
    
	int errorCode = p.waitFor();
              
    System.out.println("Does the command executed, has any errors ? "+ (errorCode==0? "No":"Yes"));
			  
  return (errorCode==0);
}

private static void showOutput(final InputStream src, final PrintStream dest) {
    new Thread(new Runnable() {
        public void run() {
            Scanner sc = new Scanner(src);
            while (sc.hasNextLine()) {
                dest.println(sc.nextLine());
            }
        }
    }).start();
 }

	private String getFileName(String filePath) {
		
        int index = filePath.lastIndexOf("\\");
        String fileName = filePath.substring(index + 1);
	  return fileName;
	}
}
