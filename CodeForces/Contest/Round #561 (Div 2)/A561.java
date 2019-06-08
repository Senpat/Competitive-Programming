//make sure to make new file!
import java.io.*;
import java.util.*;

public class A561{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[] freq = new int[26];
      
      for(int k = 0; k < n; k++){
         char c = f.readLine().charAt(0);
         freq[c-'a']++;
      }
      
      int answer = 0;
      for(int k = 0; k < 26; k++){
         answer += calc(freq[k]/2) + calc(freq[k]-freq[k]/2);
      }
      
      out.println(answer);
      

      
      
      
      
      out.close();
   }
   
   public static int calc(int i){
      return i*(i-1)/2;
   }
      
}