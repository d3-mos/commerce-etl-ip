package com.globalhitss.claropay.cercademi.commerceetlip.etl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

import javax.imageio.IIOException;

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

  public void run()
  {
    try{
      extract(AppProperties.get("file.geolite_database"));
    }
    catch(IOException e){}
  }
}