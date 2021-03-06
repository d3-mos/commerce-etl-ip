package com.globalhitss.claropay.cercademi.job.networkgeolocation.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.globalhitss.claropay.cercademi.job.networkgeolocation.config.AppProperties;
import com.globalhitss.claropay.cercademi.job.networkgeolocation.dao.NetworkGeolocationSQLDao;
import com.globalhitss.claropay.cercademi.job.networkgeolocation.model.NetworkGeolocation;

import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;


/** */
public class ETLServiceIp2Location extends ETLService<String, NetworkGeolocation>
{

  /** */
  public ETLServiceIp2Location()
  {
    super(
      AppProperties.get("file.ip2location_database"),
      "IP2LOCATION-LITE-DB9.CSV"
    );
  }

  /** */
  public List<String> extract(String path)
    throws IOException
  {
    BufferedReader csvFile = new BufferedReader( new FileReader(path) );

    List<String> lines = csvFile
      .lines()
      .skip(1)
      .filter(line -> line.matches(".*\"MX\".*"))
      .map(line -> line.replaceAll("\"", ""))
      .collect(Collectors.toList());

    csvFile.close();

    return lines;
  }

  /** */
  public List<NetworkGeolocation> transform(List<String> rows)
  {
    return rows.stream().map( row -> {
      try {
        String[] rowFields = row.split(",");

        return new NetworkGeolocation(
          parseLong(rowFields[0]),
          parseLong(rowFields[1]),
          parseDouble(rowFields[6]),
          parseDouble(rowFields[7]),
          rowFields[8],
          "ip2location"
        );
      } catch(Exception e){}

      return null;
    })
    .filter(location -> location!=null)
    .collect(Collectors.toList());
  }

  /** */
  public void load(List<NetworkGeolocation> locations)
  {
    new NetworkGeolocationSQLDao().upgrade(locations, "ip2location");
  }
}