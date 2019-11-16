package com.globalhitss.claropay.cercademi.job.networkgeolocation;

import com.globalhitss.claropay.cercademi.job.networkgeolocation.config.AppProperties;
import com.globalhitss.claropay.cercademi.job.networkgeolocation.service.ETLServiceGeoLite;
import com.globalhitss.claropay.cercademi.job.networkgeolocation.service.ETLServiceIp2Location;

/**
 * This class represents the main flow of ETL process.
 * 
 * @author Ricardo Bermúdez Bermúdez
 * @version 1.0.0, Nov 4th, 2019.
 */
public class App 
{
    public static void main( String[] args )
    {
        try{
            AppProperties.load();
            
            new ETLServiceGeoLite().run();
            new ETLServiceIp2Location().run();
        }
        catch(Exception e){}
    }
}
