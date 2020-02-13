//make sure to make new file!
import java.io.*;
import java.util.*;

public class F598{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         char[] array1 = f.readLine().toCharArray();
         char[] array2 = f.readLine().toCharArray();
         
         
         int[] freq1 = new int[26];
         int[] freq2 = new int[26];
         
         for(int k = 0; k < n; k++){
            freq1[array1[k]-'a']++;
            freq2[array2[k]-'a']++;
         }
         
         boolean fail = false;
         boolean hasrepeat1 = false;
         boolean hasrepeat2 = false;
         for(int k = 0; k < 26; k++){
            if(freq1[k] != freq2[k]){
               fail = true;
            }
            if(freq1[k] >= 2) hasrepeat1 = true;
            if(freq2[k] >= 2) hasrepeat2 = true;
         }
         
         if(fail){
            out.println("NO");
            continue;
         }
         
         if(hasrepeat1 || hasrepeat2){
            out.println("YES");
            continue;
         }
         
         int inv1 = 0;
         int inv2 = 0;
         
         freq1 = new int[26];
         freq2 = new int[26];
         
         for(int k = 0; k < n; k++){
            for(int j = array1[k]-'a'+1; j < 26; j++){
               inv1 += freq1[j];
            }
            for(int j = array2[k]-'a'+1; j < 26; j++){
               inv2 += freq2[j];
            }
            freq1[array1[k]-'a']++;
            freq2[array2[k]-'a']++;
         }
         
         if(inv1 % 2 == inv2 % 2){
            out.println("YES");
         } else {
            out.println("NO");
         }
         
      
      }
      
      
      
      
      out.close();
   }
   
      
}