package com.globalhitss.claropay.cercademi.job.ipgeolocation.appservice;

import java.io.IOException;
import java.util.Properties;


/**
 * This class manage the key properties set up into config.properties file.
 *
 * @author  Ricardo Bermúdez Bermúdez
 * @version 1.0.0, Oct 24th, 2019.
 * @see     java.util.Properties
 */
public class AppProperties
{
  private static Properties envProps = null;

  /**
   * No-argument constructor, load the properties from config.properties
   * file.
   * 
   * @throws Exception When config.properties is unable to load.
   */
  public static void load() throws Exception
  {
    envProps = new Properties();
    
    try {
      envProps.load(
        AppProperties.class
        .getClassLoader()
        .getResourceAsStream("config.properties")
      );
    } catch (IOException e) {
      throw new Exception("Unable to load properties file.", e);
    }
  }

  /**
   * Retrieve the value of an property.
   *
   * @param  key The key to find property.
   * @return A string with value associated to key property when key isn't
   *         available returns a empty string.
   */
  public static String get(String key){ return envProps.getProperty(key, ""); }
}