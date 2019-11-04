package com.globalhitss.claropay.cercademi.commerceetlip.etl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import static java.lang.Double.parseDouble;

import com.globalhitss.claropay.cercademi.commerceetlip.appservice.AppProperties;
import com.globalhitss.claropay.cercademi.commerceetlip.model.IPLocation;

import static com.globalhitss.claropay.cercademi.commerceetlip.util.IPTools.generateIpRange;

/** */
public class ETLGeoLite
{

  /** */
  public Stream<String> extract(String path)
    throws IOException
  {
      BufferedReader csvFile = new BufferedReader( new FileReader(path) );
      Stream<String> lines = csvFile.lines().skip(1);
      csvFile.close();

      return lines;
  }

  /** */
  public Stream<IPLocation> transform(Stream<String> rows)
  {
    return rows.map( row -> {
      String[] rowFields = row.split(",");
      System.out.println(rowFields[0]);
      long[] iprange = generateIpRange(rowFields[0].split("/"));

      return new IPLocation(
        iprange[0],
        iprange[1],
        parseDouble(rowFields[7]),
        parseDouble(rowFields[8]),
        rowFields[7]
      );
    });
  }

  /** */
  public void run()
  {
    try {
      System.out.println("Inicia ETL");
      Stream<String>     rows = extract(AppProperties.get("file.geolite_database"));
      Stream<IPLocation> objs = transform(rows);
    }
    catch(Exception e) { 
      e.printStackTrace();
    }
  }
}