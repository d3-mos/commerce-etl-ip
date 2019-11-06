package com.globalhitss.claropay.cercademi.commerceetlip.etl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import static java.lang.Double.parseDouble;


import com.globalhitss.claropay.cercademi.commerceetlip.appservice.AppProperties;
import com.globalhitss.claropay.cercademi.commerceetlip.dao.IPLocationDao;
import com.globalhitss.claropay.cercademi.commerceetlip.model.IPLocation;
import static com.globalhitss.claropay.cercademi.commerceetlip.util.IPTools.generateIpRange;


/** */
public class ETLGeoLite extends ETL<String, IPLocation>
{

  /** */
  public ETLGeoLite()
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
  public List<IPLocation> transform(List<String> rows)
  {
    return rows.stream().map( row -> {
      try {
        String[] rowFields = row.split(",");
        long[]   iprange   = generateIpRange(rowFields[0].split("/"));

        return new IPLocation(
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
  public void load(List<IPLocation> locations)
  {
    new IPLocationDao().insert(locations);
  }
}