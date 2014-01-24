package org.blackbox.execution.routing.c1.model;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;



@CsvRecord( separator = "," )
public class Output {
	
	@DataField(pos = 1, pattern="ddMMyy", required = true)
    public java.util.Date Date; 
	
	@DataField(pos = 2, pattern="HH:mm:ss.SS", required = true)
    public java.util.Date Hour; 

	@DataField(pos = 3, required = true)
    public String Latitude; 

	@DataField(pos = 4, required = true)
    public String Longitude; 
	
	@DataField(pos = 5, required = true)
    public String Altitude; 
	
	@DataField(pos = 6, required = true)
    public Float Vx; 

	@DataField(pos = 7, required = true)
    public Float Vy; 
	
	@DataField(pos = 8, required = true)
    public Float Vz; 
	
	@DataField(pos = 9, required = true)
    public Float RMS_LONG; 
	
	@DataField(pos = 10, required = true)
    public Float RMS_LAT; 
	
	@DataField(pos = 11, required = true)
    public Float RMS_ALT; 
	
	@DataField(pos = 12, required = true)
    public Float RMS_VX; 
	
	@DataField(pos = 13, required = true)
    public Float RMS_VY;
	
	@DataField(pos = 14, required = true)
    public Float RMS_VZ;
	
}
