//make sure to make new file!
import java.io.*;
import java.util.*;

public class B67{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      String s1 = f.readLine();
      
      ArrayList<ArrayList<Integer>> alist = new ArrayList<ArrayList<Integer>>(26);
      for(int k = 0; k < 26; k++) alist.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n; k++){
         alist.get(s1.charAt(k)-'a').add(k+1);
      }
      
      int m = Integer.parseInt(f.readLine());
      
      for(int t = 0; t < m; t++){
         String s = f.readLine();
      
         int[] freq = new int[26];
         
         int max = 0;
         for(int k = 0; k < s.length(); k++){
            int i = s.charAt(k)-'a';
            max = Math.max(max,alist.get(i).get(freq[i]));
            freq[i]++;
         }
         
         out.println(max);
       }

      
      
      
      
      out.close();
   }
   
      
}