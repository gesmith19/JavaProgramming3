
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        
        //read in short log and print it out
        la.readFile( "short-test_log" );
        
        la.printAll();
        
        
    }
    
    public void testUniqueIp() {
         LogAnalyzer la = new LogAnalyzer();
        
        //read in short log and print it out
        la.readFile( "short-test_log" );
        
        int count = la.countUniqueIPs();
        System.out.println( "Count of unique IPs is: " + count + "\n");
        
        la.printAll();
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        
        //read in short log and print it out
        la.readFile( "short-test_log" );
        la.printAll();
        System.out.println( "\nCalling printAllHigherThanNum with 200\n");
         
        la. printAllHigherThanNum( 200 );

    }
    
    public void testUniqueiIPVisitsOnDay() {
        
        LogAnalyzer la = new LogAnalyzer();
        
        //read in short log and print it out
        la.readFile( "weblog-short_log" );
        la.printAll();
        System.out.println( "\nCalling uniquie ips on Sep 14\n" );
        ArrayList<String> uniques = la.uniqueIPVisitsOnDay( "Sep 14" );
        System.out.println( "number of uniques is: " + uniques.size() );
        for (int i = 0; i < uniques.size(); i++ ) {
            System.out.println( uniques.get(i) );
        }
        
        System.out.println( "\nCalling uniquie ips on Sep 30\n" );
        uniques.clear();
        uniques = la.uniqueIPVisitsOnDay( "Sep 30" );
        System.out.println( "number of uniques is: " + uniques.size() );
        for (int i = 0; i < uniques.size(); i++ ) {
            System.out.println( uniques.get(i) );
        }
        
    }
    
    public void testCountUniqueIPsinRange() {
        
        LogAnalyzer la = new LogAnalyzer();
        
        //read in short log and print it out
        la.readFile( "short-test_log" );
        la.printAll();
        System.out.println( "\nCalling countUniqueIPsinRange with 200 and 299\n" );
        ArrayList<String> uniques = la.countUniqueIPsinRange( 200, 299 );
        System.out.println( "number of uniques is: " + uniques.size() );
        for (int i = 0; i < uniques.size(); i++ ) {
            System.out.println( uniques.get(i) );
        }
        
        System.out.println( "\nCalling countUniqueIPsinRange with 300 and 399\n" );
        uniques.clear();
        uniques = la.countUniqueIPsinRange( 300, 399 );
        System.out.println( "number of uniques is: " + uniques.size() );
        for (int i = 0; i < uniques.size(); i++ ) {
            System.out.println( uniques.get(i) );
        }
        
    }
    
    public void quiz1_q2() {
        LogAnalyzer la = new LogAnalyzer();
        
        //read in short log and print it out
        la.readFile( "weblog1_log" );
        // la.printAll();
        System.out.println( "\nCalling printAllHigherThanNum with 400\n");
         
        la. printAllHigherThanNum( 400 );

    }
    
    public void quiz1_q3() {
        
        LogAnalyzer la = new LogAnalyzer();
        
        //read in short log and print it out
        la.readFile( "weblog1_log" );
        // la.printAll();
        System.out.println( "\nCalling uniquie ips on Mar 17\n" );
        ArrayList<String> uniques = la.uniqueIPVisitsOnDay( "Mar 17" );
        System.out.println( "number of uniques is: " + uniques.size() );
        for (int i = 0; i < uniques.size(); i++ ) {
            System.out.println( uniques.get(i) );
        }
    }
    
    public void quiz1_q4() {
        
        LogAnalyzer la = new LogAnalyzer();
        
        //read in short log and print it out
        la.readFile( "weblog1_log" );
        // la.printAll();
        System.out.println( "\nCalling countUniqueIPsinRange with 300 and 399\n" );
        ArrayList<String> uniques = la.countUniqueIPsinRange( 300, 399 );
        System.out.println( "number of uniques is: " + uniques.size() );
        for (int i = 0; i < uniques.size(); i++ ) {
            System.out.println( uniques.get(i) );
        }
    }
    
    public void testCountVisitsPerIP() {
        
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println( counts );
    }
    
    public void testMostNumberVisitsByIP() {
        
        LogAnalyzer la = new LogAnalyzer();
        la.readFile( "weblog3-short_log" );
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        int max = la.mostNumberVisitsByIP( counts );
        System.out.println( "Max # visit by a single IP in weblog3-short_log:" + max );
    }
    
    public void testIPsMostVisits() {
        
        LogAnalyzer la = new LogAnalyzer();
        la.readFile( "weblog3-short_log" );
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        // public ArrayList<String> iPsMostVisits( HashMap<String, Integer> counts )
        ArrayList<String> ips = la.iPsMostVisits( counts );
        System.out.println("ips which have max visit are: " + ips);
       
    }
    
    public void testIPsForDays() {
        
        LogAnalyzer la = new LogAnalyzer();
        la.readFile( "weblog3-short_log" );
        HashMap<String,ArrayList<String>> myMap = la.iPsForDays();
        System.out.println( myMap );
        
    }
    
    public void testDayWithMostIPVisits() {
        
        LogAnalyzer la = new LogAnalyzer();
        la.readFile( "weblog3-short_log" );
        HashMap<String,ArrayList<String>> myMap = la.iPsForDays();
        String maxDay = la.dayWithMostIPVisits( myMap );
        System.out.println( "Day with most IP Visits in weblog3-short_log is: " + maxDay );
        
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile( "weblog3-short_log" );
        HashMap<String,ArrayList<String>> myMap = la.iPsForDays();
        
        ArrayList<String> myIPs = la.iPsWithMostVisitsOnDay( myMap, "Sep 30" );
        System.out.println( myIPs );
    }
    
    public void q3_q1() {
        
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        int max = la.mostNumberVisitsByIP( counts );
        System.out.println( "Max # visit by a single IP in weblog1_log:" + max );
        
        
    }
    
    public void q3_q2() {
        
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        ArrayList<String> ips = la.iPsMostVisits( counts );
        System.out.println("ips which have max visit are: " + ips);
    }
    
    public void q3_q3() {
        
        LogAnalyzer la = new LogAnalyzer();
        la.readFile( "weblog1_log" );
        HashMap<String,ArrayList<String>> myMap = la.iPsForDays();
        String maxDay = la.dayWithMostIPVisits( myMap );
        System.out.println( "Day with most IP Visits in weblog1_log is: " + maxDay );
        
    }
    
    public void q3_q4() {
        
        LogAnalyzer la = new LogAnalyzer();
        la.readFile( "weblog1_log" );
        HashMap<String,ArrayList<String>> myMap = la.iPsForDays();
       
        ArrayList<String> myIPs = la.iPsWithMostVisitsOnDay( myMap, "Mar 17" );
        System.out.println( myIPs );
    }
    
    /*
     * run countUniqueIPs on the file weblog2_log
     */
    public void ass_q4() {
        
        LogAnalyzer la = new LogAnalyzer();
        
        //read in short log and print it out
        la.readFile( "weblog2_log" );
        
        int count = la.countUniqueIPs();
        System.out.println( "Count of unique IPs is: " + count + "\n");
    }
    
    /*
     * run uniquieIPVisitsOnDay with day Sep 24 on seblog2_log
     */
    public void ass_q5() {
        
        LogAnalyzer la = new LogAnalyzer();
        
        //read in short log and print it out
        la.readFile( "weblog2_log" );
        
        ArrayList<String> uniques = la.uniqueIPVisitsOnDay( "Sep 24" );
        System.out.println( "number of uniques is: " + uniques.size() );
        //for (int i = 0; i < uniques.size(); i++ ) {
        //    System.out.println( uniques.get(i) );
        //}
 
    }
    
    /*
     * run countUniqueIPsInRange(200, 299) on the file weblog2_log
     */
    public void ass_q6() {
        LogAnalyzer la = new LogAnalyzer();
        
        //read in short log and print it out
        la.readFile( "weblog2_log" );
        ;
        System.out.println( "\nCalling countUniqueIPsinRange with 200 and 299\n" );
        ArrayList<String> uniques = la.countUniqueIPsinRange( 200, 299 );
        System.out.println( "number of uniques is: " + uniques.size() );
        
    }
    
    /*
     * 
     */
    public void ass_q7() {
         LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        int max = la.mostNumberVisitsByIP( counts );
        System.out.println( "Max # visit by a single IP in weblog2_log:" + max );
        
        
    }
    
    /*
     * run methd iPsMostVisits after a HashMap has been created from the method 
     * countVisitsPerIP on the file weblog2_log
     */
    public void ass_q8() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile( "weblog2_log" );
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        // public ArrayList<String> iPsMostVisits( HashMap<String, Integer> counts )
        ArrayList<String> ips = la.iPsMostVisits( counts );
        System.out.println("ips which have max visit are: " + ips);
       
    }
    
    /*
     * run the method dayWithMostIPVisits with a HasMap has been created from the method
     * iPsForDays on the file weblog2_log
     */
    public void ass_q9() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile( "weblog2_log" );
        HashMap<String,ArrayList<String>> myMap = la.iPsForDays();
        String maxDay = la.dayWithMostIPVisits( myMap );
        System.out.println( "Day with most IP Visits in weblog3-short_log is: " + maxDay );
        
    }
    
    /*
     * run the method iPsWithMostVisitsOnDay with 2 paramters - 1 - a HashMap that has been created
     * from the method countVisitsPerIp on the file weblog2_log and 2 the day "Sep 29"
     */
    public void ass_q10() {
        
        LogAnalyzer la = new LogAnalyzer();
        la.readFile( "weblog2_log" );
        HashMap<String,ArrayList<String>> myMap = la.iPsForDays();
        
        ArrayList<String> myIPs = la.iPsWithMostVisitsOnDay( myMap, "Sep 29" );
        System.out.println( myIPs );
    }
       
}
        

