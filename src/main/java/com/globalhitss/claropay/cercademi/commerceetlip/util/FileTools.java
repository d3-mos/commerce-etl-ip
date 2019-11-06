package com.globalhitss.claropay.cercademi.commerceetlip.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


/**
 * https://www.baeldung.com/java-compress-and-uncompress
 * https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-io/src/main/java/com/baeldung/unzip/UnzipFile.java
 */
public class FileTools
{
  
  /** */
  public void download(String fileURI, String fileName)
    throws IOException
  { 
    BufferedInputStream in = new BufferedInputStream(new URL(fileURI).openStream());
    FileOutputStream fileOutputStream = new FileOutputStream(fileName);
    byte dataBuffer[] = new byte[1024];
    int bytesRead;
    
    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
      fileOutputStream.write(dataBuffer, 0, bytesRead);
    }
  }

  /** */
  public void unzip(final String fileZip, final String path)
    throws IOException 
  {
    final File destDir = new File(path);
    final byte[] buffer = new byte[1024];
    final ZipInputStream zis = new ZipInputStream(
      new FileInputStream(fileZip)
    );
    
    ZipEntry zipEntry = zis.getNextEntry();
    while (zipEntry != null) {
      final File newFile = newFile(destDir, zipEntry);
      new File(newFile.getParent()).mkdirs(); // [1]
      final FileOutputStream fos = new FileOutputStream(newFile);
      
      int len;
      while ((len = zis.read(buffer)) > 0) {
          fos.write(buffer, 0, len);
      }
      fos.close();
      zipEntry = zis.getNextEntry();
    }
    zis.closeEntry();
    zis.close();
  }
    
  /**
   * @see https://snyk.io/research/zip-slip-vulnerability
   */
  public File newFile(File destinationDir, ZipEntry zipEntry)
    throws IOException
  {
    File destFile = new File(destinationDir, zipEntry.getName());
    String destDirPath  = destinationDir.getCanonicalPath();
    String destFilePath = destFile.getCanonicalPath();

    if (!destFilePath.startsWith(destDirPath + File.separator)) {
      throw new IOException("File outside of the target dir.");
    }
    return destFile;
  }
}