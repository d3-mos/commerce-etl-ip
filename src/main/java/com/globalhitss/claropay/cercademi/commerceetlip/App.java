package com.globalhitss.claropay.cercademi.commerceetlip;

import com.globalhitss.claropay.cercademi.commerceetlip.appservice.AppProperties;
import com.globalhitss.claropay.cercademi.commerceetlip.etl.ETLGeoLite;
import com.globalhitss.claropay.cercademi.commerceetlip.etl.ETLIp2Location;

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
            
            new ETLGeoLite().run();
            //new ETLIp2Location().run();
        }
        catch(Exception e){}
    }
}
