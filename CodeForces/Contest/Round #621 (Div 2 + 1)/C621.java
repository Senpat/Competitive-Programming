//make sure to make new file!
import java.io.*;
import java.util.*;

public class C621{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] array = f.readLine().toCharArray();
      
      int n = array.length;
      
      long[] freq = new long[26];
      
      long max = 0L;
      for(int k = 0; k < n; k++){
         freq[array[k]-'a']++;
         max = Math.max(freq[array[k]-'a'],max);
      }
      
      
      long answer = Math.max(max,(max*(max-1))/2);
      //1 letter or 2 of same letter
      
      
      for(int k = 0; k < 26; k++){
         //find pairs that end in 'a'+k
         
         long[] prenums = new long[26];
         long[] pairnum = new long[26];
         max = 0;
         for(int j = 0; j < n; j++){
            if(array[j] == 'a'+k){
               for(int h = 0; h < 26; h++){
                  pairnum[h] += prenums[h];
                  max = Math.max(pairnum[h],max);
               }
            } else {
               prenums[array[j]-'a']++;
            }
         }
         
         answer = Math.max(max,answer);
               
      }
      
      out.println(answer);
      
      

      
      
      
      
      
      out.close();
   }
   
      
}