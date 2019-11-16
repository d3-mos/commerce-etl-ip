package com.globalhitss.claropay.cercademi.job.networkgeolocation.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.globalhitss.claropay.cercademi.job.networkgeolocation.config.AppProperties;
import com.globalhitss.claropay.cercademi.job.networkgeolocation.dao.NetworkGeolocationSQLDao;
import com.globalhitss.claropay.cercademi.job.networkgeolocation.model.NetworkGeolocation;

import static com.globalhitss.claropay.cercademi.job.networkgeolocation.util.IPTools.generateIpRange;
import static java.lang.Double.parseDouble;


/** */
public class ETLServiceGeoLite extends ETLService<String, NetworkGeolocation>
{

  /** */
  public ETLServiceGeoLite()
  {
    super(
      AppProperties.get("file.geolite_database"),
      "GeoLite2-City-Blocks-IPv4.csv"
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
      .filter(line -> line.matches(".*3996063.*"))
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
        long[]   iprange   = generateIpRange(rowFields[0].split("/"));

        return new NetworkGeolocation(
          iprange[0],
          iprange[1],
          parseDouble(rowFields[7]),
          parseDouble(rowFields[8]),
          rowFields[6],
          "geolite"
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
    new NetworkGeolocationSQLDao().upgrade(locations, "geolite");
  }
}