package com.globalhitss.claropay.cercademi.commerceetlip.etl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

import com.globalhitss.claropay.cercademi.commerceetlip.appservice.AppProperties;
import com.globalhitss.claropay.cercademi.commerceetlip.dao.IPLocationDao;
import com.globalhitss.claropay.cercademi.commerceetlip.model.IPLocation;

public class ETLIp2Location
{
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


  public List<IPLocation> transform(List<String> rows)
  {
    return rows.stream().map( row -> {
      try {
        String[] rowFields = row.split(",");

        return new IPLocation(
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

  public void load(List<IPLocation> locations)
  {
    new IPLocationDao().insert(locations);
  }

  public void run()
  {
    try{
      System.out.println("Inicia ETL");
      List<String> rows = extract(AppProperties.get("file.ip2location_database"));
      System.out.println(" - Termina extracción: " + rows.size());
      List<IPLocation> objs = transform(rows);
      System.out.println(" - Termina transformación: " + objs.size());
      load(objs);
      System.out.println(" - Termina carga");
      
    }catch(Exception e){}
  }
}