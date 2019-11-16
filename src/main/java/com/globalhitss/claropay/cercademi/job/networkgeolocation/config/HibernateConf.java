package com.globalhitss.claropay.cercademi.job.networkgeolocation.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.globalhitss.claropay.cercademi.job.networkgeolocation.model.NetworkGeolocation;

public class HibernateConf
{

  private static SessionFactory sessionFactory;

  /** */
  public static Properties getSettings()
  {
    Properties settings = new Properties();
    settings.put(Environment.DRIVER,  AppProperties.get("datasource.driver"));
    settings.put(Environment.URL,     AppProperties.get("datasource.url"));
    settings.put(Environment.USER,    AppProperties.get("datasource.username"));
    settings.put(Environment.PASS,    AppProperties.get("datasource.password"));
    settings.put(Environment.DIALECT, AppProperties.get("hibernate.dialect"));

    settings.put(Environment.SHOW_SQL, AppProperties.get("hibernate.show_sql"));
    settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
    // settings.put("hibernate.id.new_generator_mappings", "false");
    // Uncomment the below line to create database with ETL job procedure.
    // settings.put(Environment.HBM2DDL_AUTO, "create-drop");

    return settings;
  }

  /** */
  public static void addAnnotatedClasses(Configuration conf)
  {
    conf.addAnnotatedClass(NetworkGeolocation.class);
  }

  /** */
  public static SessionFactory getSessionFactory() {
    if (sessionFactory==null) {

      try {
        Configuration conf = new Configuration();
        conf.setProperties( getSettings() );
        addAnnotatedClasses( conf );

        sessionFactory = conf.buildSessionFactory(
          new StandardServiceRegistryBuilder()
          .applySettings( conf.getProperties() ).build()
        );
      }
      catch(Exception e){ e.printStackTrace(); }
    }
    
    return sessionFactory;
  }
}