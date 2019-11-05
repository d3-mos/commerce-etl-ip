package com.globalhitss.claropay.cercademi.commerceetlip.dao;

import java.util.List;
import java.util.stream.Stream;

import com.globalhitss.claropay.cercademi.commerceetlip.config.HibernateConf;
import com.globalhitss.claropay.cercademi.commerceetlip.model.IPLocation;

import org.hibernate.HibernateException;
import org.hibernate.Session;

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