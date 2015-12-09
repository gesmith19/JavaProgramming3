
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
      * Convert Date into string of MMM DD and covert it
      *
      */
     private String dateToMMMDD( LogEntry le ) {
         
         // get the date and convert into a string
             Date aTime = le.getAccessTime();
             String dString = aTime.toString();

             // format of dString is DDD MMM DD HH:MM:SS BST YYYY
             //split the string using space as delimiter
             String[] sp = dString.split( " " );
             // concatenate MMM and DD
             String ds = sp[1] + " " + sp[2];
             return ds;
     }
        
     /*
      * Returns list of unique IP addresses on given day in format of "MMM DD"
      */
     public ArrayList<String> uniqueIPVisitsOnDay( String someday) {
          // create empty list to store the unique IP addresses
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         
         // for each record
         for ( LogEntry le : records ) {
             
             // get the date and convert into a string
            // Date aTime = le.getAccessTime();
             //String dString = aTime.toString();

             // format of dString is DDD MMM DD HH:MM:SS BST YYYY
             //split the string using space as delimiter
            // String[] sp = dString.split( " " );
             // concatenate MMM and DD
            // String ds = sp[1] + " " + sp[2];
             
            String ds = dateToMMMDD( le );
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
    
    /*
     * Returns map and ip address that occurred on that day
     */
    public HashMap<String, ArrayList<String>> iPsForDays() {
        
        // create an empty HashMap
        HashMap<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();
        
        //go through the logentry records
        for ( LogEntry le : records ) {
            
            
            String ds = dateToMMMDD( le );
            String thisIP = le.getIpAddress();
            
             if ( !myMap.containsKey( ds )  ){
                 // date not in the map so add it
                 ArrayList<String> ips = new ArrayList<String>();
                 ips.add( thisIP );
                 myMap.put( ds, ips);
                }
            else {
                // add the ip entry to the already created map entry for this date
                myMap.get( ds ).add( thisIP );
            }
            
         }
         return myMap;
        
    }
    /*
     * returns the String MMM DD which has the most Ip address visits.  If a tie, return any such day
     * the HashMap maps day (key) with list of IP address for that day
     */
    public String dayWithMostIPVisits( HashMap<String, ArrayList<String>> myMap ) {
        
        String maxDay = "";
        int maxIP = 0;
        
        //iterate through the HashMap
        for ( String day : myMap.keySet() ) {
            // see is number of IP address for this day is greater than maxDay
            if (( myMap.get( day ).size() ) > maxIP ) {
                // store the details in ax variables
                maxDay = day;
                maxIP = myMap.get( day ). size();
            }
        }
        return maxDay;
        
    }
    /*
     * Returns an ArrayList of IP addresses that had the most accesses on the given day
     */
    public ArrayList<String> iPsWithMostVisitsOnDay( HashMap<String, ArrayList<String>> myMap, String day ) {
        
        // create HashMap to hold the counts of ip addresses
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        
        
        //the array list of ips for the given day
        ArrayList<String> mapIPs = myMap.get( day );
        
        //build the counts HashMap
        for (int i = 0; i< mapIPs.size(); i ++ ) {
            String thisIP = mapIPs.get( i );
            //check if ip is in counts
             if ( ! counts.containsKey( thisIP ) ) {
                 //If not, add ip to counts with a value of 1
                 counts.put( thisIP, 1 );
             }
             else {
                 // ip already present, add 1 to the current value
                 counts.put( thisIP, counts.get( thisIP ) + 1 );
             }
        }
        return iPsMostVisits( counts );

    }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
