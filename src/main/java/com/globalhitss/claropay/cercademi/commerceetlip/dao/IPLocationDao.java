package com.globalhitss.claropay.cercademi.commerceetlip.dao;

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
  public Stream<IPLocation> insert(Stream<IPLocation> ipList)
  {
    try (Session session = getSession()) {
      ipList.forEach( ipLocation -> session.persist(ipLocation) );
    }
    catch(Exception e){ e.printStackTrace(); }
    
    return ipList;
  }
}