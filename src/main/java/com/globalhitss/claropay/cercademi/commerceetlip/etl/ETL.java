package com.globalhitss.claropay.cercademi.commerceetlip.etl;

import java.io.IOException;
import java.util.List;

import static com.globalhitss.claropay.cercademi.commerceetlip.util.FileTools.downloadAndUnzip;
import static com.globalhitss.claropay.cercademi.commerceetlip.util.FileTools.deleteJunkFiles;

abstract public class ETL<Origin, Destination>
{
  private String uriFile          = "";
  private String etlClassName     = "";
  private String searchedFileName = "";

  public ETL(String uriFile, String searchedFileName)
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

