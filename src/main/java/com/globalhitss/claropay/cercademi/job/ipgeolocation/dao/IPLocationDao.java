package com.globalhitss.claropay.cercademi.job.ipgeolocation.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.globalhitss.claropay.cercademi.job.ipgeolocation.config.HibernateConf;
import com.globalhitss.claropay.cercademi.job.ipgeolocation.model.IPLocation;

/** */
public class IPLocationDao
{

  /** */
  public Session getSession()
    throws HibernateException
  {
    return HibernateConf.getSessionFactory().openSession();
  } 
  

  /** */
  public List<IPLocation> insert(List<IPLocation> ipList)
  {
    try (Session session = getSession()) {
      try {
        session.beginTransaction();
        ipList.forEach( ipLocation -> session.save(ipLocation));
        session.getTransaction().commit();
      } catch (Exception e) {
        if (session.getTransaction() != null) { 
          session.getTransaction().rollback();
        }
      }
    }
    catch(Exception e){ }
    
    return ipList;
  }
}