package com.globalhitss.claropay.cercademi.commerceetlip.etl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

import com.globalhitss.claropay.cercademi.commerceetlip.appservice.AppProperties;

public class ETLGeoLite
{


  public Stream<String> extract(String path)
    throws IOException
  {
      BufferedReader csvFile = new BufferedReader( new FileReader(path) );
      Stream<String> lines = csvFile.lines();
      csvFile.close();

      return lines;
  }

  public void transform(Stream<String> rows)
  {
    rows.map( row -> {
       return row;
    });
  }

  public void run()
  {
    try{
      Stream<String> rows = extract(AppProperties.get("file.geolite_database"));
      transform(rows);
    }
    catch(IOException e){}
  }
}