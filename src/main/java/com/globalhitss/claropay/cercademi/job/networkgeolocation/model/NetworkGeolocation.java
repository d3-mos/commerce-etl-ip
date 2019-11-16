package com.globalhitss.claropay.cercademi.job.networkgeolocation.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "CAT_NETWORK_GEOLOCATION")
public class NetworkGeolocation implements Serializable
{
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name="IP_FROM")
  private long   ipFrom     = 0;
  
  @Id
  @Column(name="IP_TO")
  private long   ipTo       = 0;

  @Column(name="LATITUDE")
  private double latitude   = 0.0;

  @Column(name="LONGITUDE")
  private double longitude  = 0.0;

  @Column(name="ZIP_CODE")
  private String zipCode    = "";

  @Id
  @Column(name="DATASOURCE")
  private String datasource = "";

  @Column(name="NO_NODES")
  private long   noNodes    = 0;
  
  @Column(name="MODIFY_TS")
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date modifyTs;

  public NetworkGeolocation(){}

  public NetworkGeolocation(
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
      this.noNodes    = ipTo - ipFrom;
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

  public long getNoNodes() { return noNodes; }
  public void setNoNodes(long noNodes){ this.noNodes = noNodes;}
  
  public Date getModifyTs() { return modifyTs; }
  public void setModifyTs(Date modifyTs) { this.modifyTs = modifyTs; }
}