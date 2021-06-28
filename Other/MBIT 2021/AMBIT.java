//make sure to make new file!
import java.io.*;
import java.util.*;

public class AMBIT{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] array = f.readLine().toCharArray();
      int n = array.length;
      
      int[] freq = new int[26];
      for(int k = 0; k < n; k++){
         freq[array[k]-'a']++;
      }
      
      int gcd = -1;
      for(int k = 0; k < 26; k++){
         if(freq[k] > 0){
            if(gcd == -1) gcd = freq[k];
            else {
               gcd = gcd(gcd,freq[k]);
            }
         }
      }
      
      if(gcd == 1){
         out.println("IMPOSSIBLE");
      } else {
         StringJoiner sj = new StringJoiner("");
         for(int k = 0; k < 26; k++){
            for(int j = 0; j < freq[k]/gcd; j++){
               sj.add(""+(char)(k+97));
            }
         }
         
         String sjstr = sj.toString();
         StringBuilder sb = new StringBuilder("");
         for(int k = 0; k < gcd; k++){
            sb.append(sjstr);
         }
         out.println(sb.toString());
      }
      
      
      
      
      out.close();
   }
   
   public static int gcd(int a, int b){
      if(a < b){
         if(a == 0) return b;
         return gcd(b%a,a);
      }
      if(b < a){
         if(b == 0) return a;
         return gcd(a%b,b);
      }
      return a;
   }
   
      
}