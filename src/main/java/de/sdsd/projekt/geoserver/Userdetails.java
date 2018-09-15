/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sdsd.projekt.geoserver;

/**
 *
 * @author GaganMGowda
 */
public class Userdetails {
	
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
	
	  private String getworkspace() {
        return workspace;
    }
	   

    public void setworkspace(String workspace) {
        this.workspace = workspace;
    }
	
	public String getlayerName() {
        return layerName;
    }
	
    public void setlayerName(String layerName) {
        this.layerName = layerName;
    }
	
    public String getdataStoreName() {
        return dataStoreName;
    }
	
    public void setdataStoreName(String dataStoreName) {
        this.dataStoreName = dataStoreName;
    }
                                String geoURI    = "http://localhost:8080/geoserver";
				String workspace = "SDSDWorkspace";
				String layerName = "Freese_hinter_der_Bahn";
				String dataStoreName = "Freese_hinter_der_Bahn_ds";
}

	
