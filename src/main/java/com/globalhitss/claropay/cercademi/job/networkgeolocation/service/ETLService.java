package com.globalhitss.claropay.cercademi.job.networkgeolocation.service;

import static com.globalhitss.claropay.cercademi.job.networkgeolocation.util.FileTools.deleteJunkFiles;
import static com.globalhitss.claropay.cercademi.job.networkgeolocation.util.FileTools.downloadAndUnzip;

import java.io.IOException;
import java.util.List;

abstract public class ETLService<Origin, Destination>
{
  private String uriFile          = "";
  private String etlClassName     = "";
  private String searchedFileName = "";

  public ETLService(String uriFile, String searchedFileName)
  {
    this.uriFile = uriFile;
    this.searchedFileName = searchedFileName;
    this.etlClassName      = getClass().getSimpleName();
  }

  public void log(String message)
  {
    System.out.println(etlClassName + " - " + message + ".");
  }

  /** */
  public void run()
  {
    try {
      log("Inicia");

      String path = downloadAndUnzip(uriFile, etlClassName, searchedFileName);
      log("Termina descarga.");

      List<Origin> rows = extract(path);
      log("Termina extracción: " + rows.size());

      List<Destination> objs = transform(rows);
      log("Termina transformación: " + objs.size());
      
      int transformErrors = (rows.size() - objs.size());
      log("Registros con errores al tranformar: " + transformErrors);

      load(objs);
      log("Termina carga");
    }
    catch(Exception e) { e.printStackTrace(); }
    
    try {
      deleteJunkFiles(etlClassName);
      log("Archivos temporales eliminados");
    }
    catch(Exception e) { e.printStackTrace(); }
  }

  /** */
  abstract public List<Origin> extract(String fileContentsPath)
    throws IOException;

  /** */
  abstract public List<Destination> transform(List<Origin> data);

  /** */
  abstract public void load(List<Destination> data);
}

