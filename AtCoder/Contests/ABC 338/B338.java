//make sure to make new file!
import java.io.*;
import java.util.*;

public class B338{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] array = f.readLine().toCharArray();
      int n = array.length;
      int[] freq = new int[26];
      for(int k = 0; k < n; k++){
         freq[array[k]-'a']++;
      }
      
      int maxi = 0;
      for(int k = 1; k < 26; k++){
         if(freq[k] > freq[maxi]){
            maxi = k;
         }
      }
      
      out.println((char)(maxi + 'a'));
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}