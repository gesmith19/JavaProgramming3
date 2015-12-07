import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    
    /*
     * returns a string consisting of every totalSlices-th character from message,
     * starting at the whichSlice-th character
     */
    public String sliceString(String message, int whichSlice, int totalSlices) {
        
        StringBuilder sb = new StringBuilder();
        
        for ( int i = whichSlice; i < message.length(); i += totalSlices ) {
            sb.append( message.charAt( i ) );
        }
        
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker( mostCommon );
        
        for ( int i = 0; i < klength; i++ ) {
            
            // slice the string
            String slice = sliceString( encrypted, i, klength );
            
            // find the key for this slice of the string and add it to the key array
            int j = cc.getKey( slice );
            key[i] = j;
        }
        return key;
    }

    public void breakVigenere () {
      
        // read in the encrypted message
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        // read in the dictionary of valid words
        FileResource dictRes = new FileResource( "dictionaries/English" );
        HashSet<String> dict = readDictionary( dictRes );
        
   
        String decrypted = breakForLanguage( encrypted, dict );
        System.out.println( decrypted );
    }
    
    /*
     *  method to return a HashSet of words lower-cased and read from the file 
     *  passed in as a parameter
     */
    public HashSet<String> readDictionary( FileResource fr ) {
        
        // create a HashSet which you will be returning
        HashSet<String> dict = new HashSet<String>();
        
        for ( String line : fr.lines() ) {
            
            // convert to lowercase and add to the dictionary
            line = line.toLowerCase();
            dict.add ( line );
            
        }
        return dict;
    }
    
    /*
     * returns # of valid words given message and dictionary against which to check words in
     * the message.  Lowercases the word before checking in dictionary
     */
    public int countWords( String message, HashSet<String> dictionary ) {
        
        int validWords = 0;
        int totalWords = 0;
        // split the message into words
       
       for ( String word : message.split( "\\W" ) ) {
           totalWords++;
           word = word.toLowerCase();
           if ( dictionary.contains( word ) ) {
               validWords++;
            }
        }
        System.out.println("total words: " + totalWords + " valid words: " + validWords);
        return validWords;
    }
    
    /*
     * returns the decrypted string
     */
    public String breakForLanguage( String encrypted, HashSet<String> dictionary ) {
        
        int kLen = 0;
        int vWords = 0;
        String mess = "";
        
        // find the most common character in the dictionary
        char mostCommon = mostCommonCharIn( dictionary );
                
        // arbitrary limit of key length set to 100
        for ( int keyLen = 1; keyLen < 101; keyLen++ ) {
           
            int[] key = tryKeyLength( encrypted, keyLen, mostCommon );
            System.out.println( "Key length is: " + key.length);
            //System.out.print( "Key is: " );
            //for ( int i = 0; i < key.length; i ++ ) {
            //System.out.print( key[i] + "," );
           // }
            //System.out.println();
            // create a VigenereCipher and decrypt the message
            VigenereCipher vc = new VigenereCipher( key );
            String decrypted = vc.decrypt( encrypted );
            int validWords = countWords ( decrypted, dictionary );
            // System.out.println( "vwords: " + vWords + "\tvalidWords: " + validWords );
            if ( validWords > vWords ) {
                kLen = keyLen;
                vWords = validWords;
                mess = decrypted;
               
            }
                
        }
        System.out.println("\nkLen: " + kLen + " vWords: " + vWords);
        return mess;
        
    }
    
    /*
     * Return the index with the most entries
     */
    private int maxIndex( int[] vals ) {
        int maxDex = 0;
        
        for ( int k = 0; k < vals.length; k++ ) {
            if ( vals[k] > vals[maxDex] ) {
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    /*
     * Return the most common character in the words in the dictionary
     */
    public char mostCommonCharIn( HashSet<String> dictionary ) {
        
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        
        for ( String word : dictionary ) {
            for ( int k = 0; k < word.length(); k++ ) {
               // char ch = Character.toLowerCase(message.charAt(k));
                char ch = word.charAt( k );
                int dex = alph.indexOf( ch );
            
                // char is a letter (and not a space or comma etc., etc., )
                if ( dex != -1 ) {
                   counts[dex] += 1;
                }
            } 
        }
        
        int maxDex = maxIndex( counts );
 
        return alph.charAt( maxDex );
    }
    
    /*
     * 
     */
    public void breakForAllLanguages( String encrypted, HashMap<String, HashSet<String>> languages ) {
        
        String decrypted = "";
        String mess = "";
        String vLang = "";
        int vWords = 0;

        
        // iterate through languages
        for ( String lang : languages.keySet() ) {
            
            HashSet<String> dict  = languages.get( lang );
            
            decrypted = breakForLanguage( encrypted,  dict );
            
            int validWords = countWords ( decrypted, dict );
            // System.out.println( "vwords: " + vWords + "\tvalidWords: " + validWords );
            if ( validWords > vWords ) {
                vWords = validWords;
                mess = decrypted;
                vLang = lang;
               
            }
        }
        System.out.println( "\nlanguage: " + vLang + "\tvalid words: " + vWords );
        System.out.println( mess );

    }
    
    /*
     *  method to load the dictionaries in the given directory and return the HashMap
     */
    private HashMap<String, HashSet<String>> loadDictionaries( DirectoryResource dr ) {
        
         HashMap<String, HashSet<String>> dictMap = new HashMap<String, HashSet<String>>();
         
        for ( File f : dr.selectedFiles() ) {
            System.out.println( "loading dictionary: " + f.getName() );
            FileResource fr = new FileResource( f );
            HashSet<String> dict = readDictionary( fr );
            dictMap.put( f.getName(), dict);
            
        }
        
        return dictMap;
        
    }
    
    
    public void testSliceString() {
        
        String ans = "";
        ans = sliceString("abcdefghijklm", 0, 3);
        System.out.println("\n string is <abcdefghijklm" + ">");
        System.out.println("0th slice is: " + ans);
        ans = sliceString("abcdefghijklm", 1, 3);
        System.out.println("\n string is <abcdefghijklm" + ">");
        System.out.println("1th slice is: " + ans);
        ans = sliceString("abcdefghijklm", 2, 3);
        System.out.println("\n string is <abcdefghijklm" + ">");
        System.out.println("2th slice is: " + ans);
        ans = sliceString("abcdefghijklm", 0, 4);
        System.out.println("\n string is <abcdefghijklm" + ">");
        System.out.println("0th slice is: " + ans);
        ans = sliceString("abcdefghijklm", 1, 4);
        System.out.println("\n string is <abcdefghijklm" + ">");
        System.out.println("1th slice is: " + ans);
        ans = sliceString("abcdefghijklm", 2, 4);
        System.out.println("\n string is <abcdefghijklm" + ">");
        System.out.println("2th slice is: " + ans);
        ans = sliceString("abcdefghijklm", 3, 4);
        System.out.println("\n string is <abcdefghijklm" + ">");
        System.out.println("0th slice is: " + ans);
        ans = sliceString("abcdefghijklm", 0, 5);
        System.out.println("\n string is <abcdefghijklm" + ">");
        System.out.println("0th slice is: " + ans);
        ans = sliceString("abcdefghijklm", 1, 5);
        System.out.println("\n string is <abcdefghijklm" + ">");
        System.out.println("0th slice is: " + ans);
        ans = sliceString("abcdefghijklm", 2, 5);
        System.out.println("\n string is <abcdefghijklm" + ">");
        System.out.println("2th slice is: " + ans);
        ans = sliceString("abcdefghijklm", 3, 5);
        System.out.println("\n string is <abcdefghijklm" + ">");
        System.out.println("0th slice is: " + ans);
        ans = sliceString("abcdefghijklm", 4, 5);
        System.out.println("\n string is <abcdefghijklm" + ">");
        System.out.println("0th slice is: " + ans);
        
    }
    
    public void testTryKeyLength() {
        
        FileResource fr = new FileResource("athens_keyflute.txt");
        String encrypted = fr.asString();
        
        int[] key = new int[5];
        key = tryKeyLength( encrypted, 5, 'e' );
        for ( int i = 0; i < 5; i++ ) {
            System.out.println( key[i] );
        }
        
    }
    
    public void quiz1_q1() {
        FileResource fr = new FileResource("../secretmessage1.txt");
        String encrypted = fr.asString();
        
        int[] key = new int[4];
        key = tryKeyLength( encrypted, 4, 'e' );
        for ( int i = 0; i < 4; i++ ) {
            System.out.println( key[i] );
        }
        
    }
    
    public void testReadDictionary() {
        
        FileResource fr = new FileResource( "dictionaries/English" );
        HashSet<String> dict = readDictionary( fr );
        System.out.println( "dict has " + dict.size() + " entries" );
        
        Iterator<String> it = dict.iterator();
        for (int i = 0; i < 25; i++ ) {
            if (it.hasNext() ) {
                System.out.println( i + "\t" + it.next() );
            }
        }
    }
    
    public void testMostCommonCharIn() {
        FileResource fr = new FileResource( "dictionaries/English" );
        HashSet<String> dict = readDictionary( fr );
        System.out.println( "English dict has " + dict.size() + " entries" );
        
        char mostCommon = mostCommonCharIn( dict );
        System.out.println(" most common char: " + mostCommon);
        
        FileResource fr1 = new FileResource( "dictionaries/Spanish" );
        HashSet<String> dict1 = readDictionary( fr1 );
        System.out.println( "Spanish dict has " + dict1.size() + " entries" );
        
        mostCommon = mostCommonCharIn( dict1 );
        System.out.println(" most common char: " + mostCommon);
        
        
    }
    
    public void testBreakForAllLanguages() {
        
        // read in the encrypted message
        FileResource fr2 = new FileResource( "athens_keyflute.txt");
        String encrypted = fr2.asString();
        
        HashMap<String, HashSet<String>> myMap = new HashMap<String, HashSet<String>>();
        
        FileResource fr = new FileResource( "dictionaries/English" );
        HashSet<String> dict = readDictionary( fr );
        
        myMap.put( "English", dict );
        
        FileResource fr1 = new FileResource( "dictionaries/Spanish" );
        HashSet<String> dict1 = readDictionary( fr1 );
        
        myMap.put( "Spanish", dict1 );
        
        breakForAllLanguages( encrypted, myMap ); 

    }
    
    public void testLoadDictionaries() {
        
        DirectoryResource dr = new DirectoryResource();
        
        HashMap<String, HashSet<String>> dicts = new HashMap<String, HashSet<String>>();
        
        
        dicts =  loadDictionaries( dr );
        
        for ( String s : dicts.keySet() ) {
            System.out.println( "language: " + s + "\tentries: " + dicts.get( s ).size() );
        }
        
        
    }
}
