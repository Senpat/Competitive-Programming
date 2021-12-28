//make sure to make new file!
import java.io.*;
import java.util.*;

public class A1617{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         char[] array = f.readLine().toCharArray();
         
         String T = f.readLine();
         
         Arrays.sort(array);
         int[] freq = new int[26];
         for(int k = 0; k < array.length; k++){
            freq[array[k]-'a']++;
         }
         if(T.equals("abc") && (freq[0] > 0 && freq[1] > 0 && freq[2] > 0)){
            
            for(int k = 0; k < freq[0]; k++) out.print("a");
            for(int k = 0; k < freq[2]; k++) out.print("c");
            for(int k = 0; k < freq[1]; k++) out.print("b");
            
            for(int k = 3; k < 26; k++) for(int j = 0; j < freq[k]; j++) out.print((char)('a'+k));
            out.println();
         } else {
            for(int k = 0; k < array.length; k++){
               out.print(array[k]);
            }
            out.println();
         }
      
      
      }
      
      
      
      
      out.close();
   }
   
      
}