package com.globalhitss.claropay.cercademi.commerceetlip.config;

import java.util.Properties;

import com.globalhitss.claropay.cercademi.commerceetlip.appservice.AppProperties;
import com.globalhitss.claropay.cercademi.commerceetlip.model.IPLocation;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

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

    //settings.put("hibernate.id.new_generator_mappings", "false");  
    settings.put(Environment.SHOW_SQL, "true");
    settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
    settings.put(Environment.HBM2DDL_AUTO, "create-drop");

    return settings;
  }

  /** */
  public static void addAnnotatedClasses(Configuration conf)
  {
    conf.addAnnotatedClass(IPLocation.class);
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