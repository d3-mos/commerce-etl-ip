package com.globalhitss.claropay.cercademi.commerceetlip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ip_cities_final")
public class IPLocation
{
  @Column(name="ip_from")
  public long   ipFrom     = 0;
  
  @Column(name="ip_to")
  public long   ipTo       = 0;

  @Column(name="latitude")
  public double latitude   = 0.0;

  @Column(name="longitude")
  public double longitude  = 0.0;

  @Column(name="zip_code")
  public String zipCode    = "";

  public IPLocation(){}

  public IPLocation(
    long ipFrom,
    long ipTo,
    double latitude,
    double longitude,
    String zipCode
  ) {
      this.ipFrom    = ipFrom;
      this.ipTo      = ipTo;
      this.latitude  = latitude;
      this.longitude = longitude;
      this.zipCode   = zipCode;
  }

  public long getIpFrom() { return ipFrom; }
  public void setIpFrom(long ipFrom){ this.ipFrom = ipFrom;}

  public long getIpTo() { return ipTo; }
  public void setIpTo(long ipTo){ this.ipTo = ipTo;}

  public double getLatitude() { return latitude; }
  public void setLatitude(double latitude){ this.latitude = latitude;}

  public double getLongitude() { return longitude; }
  public void setLongitude(double longitude){ this.longitude = longitude;}

  public String getZipCode() { return zipCode; }
  public void setZipCode(String zipCode){ this.zipCode = zipCode;}
}