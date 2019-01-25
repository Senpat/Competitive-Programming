//make sure to make new file!
import java.io.*;
import java.util.*;

public class B532{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      
      int[] array = new int[m];
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < m; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] freq = new int[n+1];
      ArrayList<HashSet<Integer>> alist = new ArrayList<HashSet<Integer>>(m+1);
      for(int k = 0; k <= m; k++) alist.add(new HashSet<Integer>());
      
      StringBuilder sb = new StringBuilder("");
      
      for(int k = 0; k < m; k++){
         alist.get(freq[array[k]]).add(array[k]);
         if(alist.get(freq[array[k]]).size() == n){
            sb.append("1");
         } else {
            sb.append("0");
         }
         freq[array[k]]++;
      }      
      out.println(sb.toString());
      
      
      out.close();
   }
   
}