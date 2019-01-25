//make sure to make new file!
import java.io.*;
import java.util.*;

public class B533{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      String s = f.readLine();
      
      int[] counts = new int[26];
      
      for(int k = 0; k < n;){
         
         char cur = s.charAt(k);
         int index = k;
         while(index < n && s.charAt(index) == cur){
            index++;
         }
         
         if(index - k >= m){
            counts[cur-'a'] += (index-k)/m;
         }
         
         k = index;
      }
      
      int max = 0;
      for(int k = 0; k < 26; k++){
         max = Math.max(counts[k],max);
      }
      
      out.println(max);
      
      
      out.close();
   }
   
}