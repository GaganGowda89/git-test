/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geoserver.API.TEST;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author GaganMGowda
 */
public class CurlExecution {
    
    public static void main(String arg[]) throws Exception {
    //Process p = Runtime.getRuntime().exec("/bin/sh -c shp2pgsql /Users/GaganMGowda/Desktop/Kaufmann/kaufmann_boundary.shp Kaufmann_boundary | psql -U postsdsd -h localhost -p 5432 -d testdraw_ol");
    String env = "/usr/local/bin/";
    
    ProcessBuilder pb =
                   new ProcessBuilder("/bin/sh", "-c", env +"shp2pgsql -s 4326 /Users/GaganMGowda/Desktop/Kaufmann/kaufmann_boundary.shp Kaufmann_through_Java | "+env+"psql -U postsdsd -h localhost -p 5432 -d testdraw_ol");
    Process p = pb.start();
    showOutput(p.getInputStream(), System.out);
    showOutput(p.getErrorStream(), System.err);

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
}
