package com.globalhitss.claropay.cercademi.commerceetlip.model;

public class IPLocation
{
    public long ipFrom       = 0;
    public long ipTo         = 0;
    public double latitude  = 0.0;
    public double longitude = 0.0;
    public String zipCode   = "";

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