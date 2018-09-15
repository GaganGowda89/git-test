/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geoserver.API;

/**
 *
 * @author GaganMGowda
 */
public class User {

    private String getRESTUSER() {
        return RESTUSER;
    }

    public void setRESTUSER(String RESTUSER) {
        this.RESTUSER = RESTUSER;
    }

    private String getRESTPW() {
        return RESTPW;
    }

    public void setRESTPW(String RESTPW) {
        this.RESTPW = RESTPW;
    }
    
                String RESTUSER = "admin";
                String RESTPW   = "geoserver";

    public String getGeoURI() {
        return geoURI;
    }
                String geoURI = "http://localhost:8080/geoserver/rest";
}
