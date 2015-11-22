
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {
    
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        
        // clear both arraylists
        myWords.clear();
        myFreqs.clear();
        
        // select a file and read it one word at a time
        FileResource resource = new FileResource();
        
        for(String s : resource.words()){
            s = s.toLowerCase();
            // find out if the word already been read in
            int index = myWords.indexOf(s);
            // word not already added
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                // word already ready, so need to find where it is in the frequency array
                // then add 1 to the value already there
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }
    }
    
    /*
     * method returns an int thia is the index location of the largest value in myFreqs.  If there is a 
     * tie, it returns the first such value
     */
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
    
    public void tester(){
        findUnique();
        System.out.println( "# unique words: "+myWords.size() );
        
        for ( int k = 0; k < myWords.size(); k++ ) {
            System.out.println( myFreqs.get( k ) + "\t" + myWords.get( k ) );
        }
        int index = findIndexOfMax();
        System.out.println( "max word/freq: "+myWords.get(index)+" "+myFreqs.get(index) );

    }
}
