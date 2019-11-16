package com.globalhitss.claropay.cercademi.job.networkgeolocation.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.globalhitss.claropay.cercademi.job.networkgeolocation.config.HibernateConf;
import com.globalhitss.claropay.cercademi.job.networkgeolocation.model.NetworkGeolocation;

/** */
public class NetworkGeolocationSQLDao
{
  
  private String datasource = "";
  
  /** */
  public Session getSession()
    throws HibernateException
  {
    return HibernateConf.getSessionFactory().openSession();
  } 
  
  /** */
  public void log(String msg)
  {
    System.out.println(datasource + " - " + msg + ".");
  }
  
  /** */
  public List<NetworkGeolocation> upgrade(List<NetworkGeolocation> networkList, String datasource)
  {
    try (Session session = getSession()) {
      try {
        session.beginTransaction();
        
        log("Eliminando datos antiguos");
        Query<?> query = session.createQuery(
          "delete from NetworkGeolocation where datasource = (:datasource)"
        );
        query.setParameter("datasource", datasource);
        log( "Registros eliminados: " + query.executeUpdate() );
        
        
        log("Cargando datos nuevos");
        networkList.forEach( netLocation -> session.save(netLocation));
        
        session.getTransaction().commit();
        log("Transaccion exitosa");
      }
      catch (Exception e) {
        log("Transaccion fracaso, intentanto restaurar cambios anteriores");
        e.printStackTrace();
        
        if (session.getTransaction() != null) { 
          session.getTransaction().rollback();
          log("Transaccion fracaso, Cambios restaurados");
        }
      }
    }
    catch(Exception e){ 
      e.printStackTrace();
    }
    
    return networkList;
  }
}