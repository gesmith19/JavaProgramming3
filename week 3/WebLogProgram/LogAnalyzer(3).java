
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
      /*
       * open log file and read in all lines and populte the LogEntry arraylist
       */  
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource( filename );
        
         for ( String line : fr.lines() ) {
            LogEntry rec = WebLogParser.parseEntry( line );
             records.add( rec );
            }
        
     }
     /*
      * Return the number of unique IP addresses in the log file
      */
     public int countUniqueIPs(){
         
         // create empty list to store the unique IP addresses
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         
         // for each record
         for ( LogEntry le : records ) {
             //get the IP address
             String ipAddr = le.getIpAddress();
             // if it's not in the unique IP list, add it
             if ( !uniqueIPs.contains( ipAddr ) ) {
                 uniqueIPs.add( ipAddr );
             }
         }
         // return the size of unique IPs
         return uniqueIPs.size();
     }
     
      /*
      * print all the records that have a status code > num
      */
     public void printAllHigherThanNum( int num ){
              
         // for each record
         for ( LogEntry le : records ) {
             
             if ( ( le.getStatusCode() ) > num ) {
                 System.out.println( le );
             }
         }
     }
     /*
      * Returns list of unique IP addresses on given day in format of "MMM DD"
      */
     public ArrayList<String> uniqueiIPVisitsOnDay( String someday) {
          // create empty list to store the unique IP addresses
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         
         // for each record
         for ( LogEntry le : records ) {
             
             // get the date and convert into a string
             Date aTime = le.getAccessTime();
             String dString = aTime.toString();

             // format of dString is DDD MMM DD HH:MM:SS BST YYYY
             //split the string using space as delimiter
             String[] sp = dString.split( " " );
             // concatenate MMM and DD
             String ds = sp[1] + " " + sp[2];

             if ( someday.equals( ds ) ) {
                 
                //get the IP address
                String ipAddr = le.getIpAddress();
                // if it's not in the unique IP list, add it
                if ( !uniqueIPs.contains( ipAddr ) ) {
                    uniqueIPs.add( ipAddr );
                }
             }
         }
         // return the  unique IPs
       
         return uniqueIPs;
     }
     
    /*
      * Returns list of unique IP addresses that have status in range from low to high inclusive
      */
     public ArrayList<String> countUniqueIPsinRange( int low, int high) {
          // create empty list to store the unique IP addresses
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         
         // for each record
         for ( LogEntry le : records ) {
             
             // get the status code
             int status = le.getStatusCode();

             // check if its in the rang
             if ( status >= low && status <= high ) {
                 
                //get the IP address
                String ipAddr = le.getIpAddress();
                // if it's not in the unique IP list, add it
                if ( !uniqueIPs.contains( ipAddr ) ) {
                    uniqueIPs.add( ipAddr );
                }
             }
         }
         // return the  unique IPs
       
         return uniqueIPs;
     } 
     /*
      * returns hashmap that maps an IP address to the # of times that IP address appears in the log
      */
     public HashMap<String, Integer> countVisitsPerIP() {
         // create an empty hashmap
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         
         // for each log entry in records
         for ( LogEntry le: records ) {
             // get the ip address
             String ip = le.getIpAddress();
             
             //check if ip is in counts
             if ( ! counts.containsKey( ip ) ) {
                 //If not, add ip to counts with a value of 1
                 counts.put( ip, 1 );
             }
             else {
                 // ip already present, add 1 to the current value
                 counts.put( ip, counts.get( ip ) + 1 );
             }
         }
         
         //return counts
         return counts;
     }
     
     /*
      * returns the maximum number of visits to this website by a single IP address
      */
     public int mostNumberVisitsByIP( HashMap<String, Integer> counts ) {
         
         int max = 0;
         
         for ( String s : counts.keySet() ) {
             int val = counts.get( s );
             
             if ( val > max ) {
                 max = val;
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits( HashMap<String, Integer> counts ) {
         
         ArrayList<String> ips = new ArrayList<String>(); 
         int max = mostNumberVisitsByIP( counts );
         
         for ( String s : counts.keySet() ) {
             int val = counts.get( s );
             
             if ( max == val ) {
                 ips.add( s );
                }
        }
        
        return ips;
    }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
