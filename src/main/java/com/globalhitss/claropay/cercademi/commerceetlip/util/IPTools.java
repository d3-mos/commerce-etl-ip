package com.globalhitss.claropay.cercademi.commerceetlip.util;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Integer.toBinaryString;


/** */
public class IPTools
{

  /** */
  private static String completeByte(String byteStr)
  {
    int loop = 8 - byteStr.length();
    String resByteStr = byteStr;

    for (int i=0; i<loop; i++) { resByteStr = "0" + resByteStr; }

    return resByteStr;
  }

  /** */
  private static String ip2ipBin(String ip)
  {
    List<String> octets = new LinkedList<String>();
    
    for (String octet : ip.split("\\.")) {
      octets.add( completeByte( toBinaryString( parseInt(octet) ) ) );
    }
    
    return String.join("", octets);
  }

  /** */
  private static long ipBin2ipNum(String ipBin) { return parseLong(ipBin, 2); }

  /** */
  private static String fullHost(String ipBin, int cidr, char digit)
  {
    StringBuilder ipBuild = new StringBuilder(ipBin);

    for (int i=cidr; i<32; i++) { ipBuild.setCharAt(i, digit); }

    return ipBuild.toString();
  }


  /** */
  public static long[] generateIpRange(String ip, int cidr)
  {
    String ipbin  = ip2ipBin(ip);
    long minrange = ipBin2ipNum(fullHost(ipbin, cidr, '0'));
    long maxrange = ipBin2ipNum(fullHost(ipbin, cidr, '1'));

    return new long[] {minrange, maxrange};
  }

  /** */
  public static long[] generateIpRange(String[] network)
  {
    return generateIpRange(network[0], parseInt(network[1]));
  }
}