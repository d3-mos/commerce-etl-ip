package com.globalhitss.claropay.cercademi.commerceetlip.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ip_cities_final")
public class IPLocation implements Serializable
{
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name="ip_from")
  public long   ipFrom     = 0;
  
  @Id
  @Column(name="ip_to")
  public long   ipTo       = 0;

  @Column(name="latitude")
  public double latitude   = 0.0;

  @Column(name="longitude")
  public double longitude  = 0.0;

  @Column(name="zip_code")
  public String zipCode    = "";

  @Id
  @Column(name="datasource")
  public String datasource = "";

  public IPLocation(){}

  public IPLocation(
    long ipFrom,
    long ipTo,
    double latitude,
    double longitude,
    String zipCode,
    String datasource
  ) {
      this.ipFrom     = ipFrom;
      this.ipTo       = ipTo;
      this.latitude   = latitude;
      this.longitude  = longitude;
      this.zipCode    = zipCode;
      this.datasource = datasource;
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

  public String getDataSource() { return datasource; }
  public void setDataSource(String datasource){ this.datasource = datasource;}
}