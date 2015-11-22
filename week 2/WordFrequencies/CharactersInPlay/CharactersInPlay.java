
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CharactersInPlay {
    
    private ArrayList<String> myCharacters;
    private ArrayList<Integer> myFreqs;
    
    public CharactersInPlay() {
        myCharacters = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    private void update( String person ) {
        
        // make name all lower case
    //    String name = person.toLowerCase();
    
        String name = person;
        
        // find out if the person is already in the list
        int index = myCharacters.indexOf( name );
        
        // character not in list
        if ( index == -1 ) {
            myCharacters.add ( name );
            myFreqs.add( 1 );
        }
        else {
            // person already in list, need to find it and add one to corresponding frequency
            int freqs = myFreqs.get( index );
            myFreqs.set( index, freqs + 1);
        }

    }
    
    public int findIndexOfMax() {
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for( int k = 0; k < myFreqs.size(); k++ ){
            if ( myFreqs.get(k) > max ){
                max = myFreqs.get( k );
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    
    public void findAllCharacters() {
        
        // clear the arraylists
        myCharacters.clear();
        myFreqs.clear();
        
        // select a file and read it one line at a time
        FileResource fr = new FileResource();
        
        for ( String line : fr.lines() ) {
            
           // find out if there is a '.' in the line
           int index = line.indexOf('.');
           
           if ( index != -1 ) {
               // we've found a '.'
               update( line.substring(0, index) );
            }
               
        }
        
    }
    
    public void charactersWithNumParts(int num1, int num2 ) {
        int count = 0;
        for ( int i = 0; i < myCharacters.size(); i++ ) {
             count = myFreqs.get( i );
             if ( count >= num1 && count <= num2) {
                 System.out.println( myCharacters.get( i ) + "\t" + myFreqs.get( i ) );
                }
        }
    }
    
    public void testSubstring(){
        
        String name = "Macbeth.  hello world";
        
        int index = name.indexOf('.');
        System.out.println("indexof returned: " + index);
        
        String s1 = name.substring(0, index);
        System.out.println("sl is: " + s1);
    }
    
    
    public void tester() {
        
        findAllCharacters();
        
        charactersWithNumParts(10, 15);
        
        System.out.println("\n\nnumber of characters is: " + myCharacters.size() );
        int max = findIndexOfMax();
        System.out.println("max occurrences : " + myFreqs.get( max) + 
                           " spoken by: " + myCharacters.get( max ) );
        
        for ( int i = 0; i < myCharacters.size(); i++ ) {
            if ( myFreqs.get( i ) > 30) {
               System.out.println( myCharacters.get( i ) + "\t" + myFreqs.get( i ) );
            }
        }
    }

}
