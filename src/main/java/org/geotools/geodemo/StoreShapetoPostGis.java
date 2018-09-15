/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geotools.geodemo;

import java.io.IOException;


/**
 *
 * @author GaganMGowda
 */
public class StoreShapetoPostGis {
    
    public static void main() throws IOException{
              ProcessBuilder pb =
                     new ProcessBuilder("/bin/sh", "-c", "shp2pgsql -s 4326 `/Users/GaganMGowda/Desktop/Kaufmann/kaufmann_boundary.shp` Kaufmann_boundary | psql -U postsdsd -h localhost -p 5432 -d testdraw_ol");
              Process p = pb.start();
              System.out.println("Process alive : "+ p.isAlive());
           }
}