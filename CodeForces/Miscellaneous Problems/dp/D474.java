//Flowers
import java.io.*;
import java.util.*;

public class D474{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] ql = new int[n];
      int[] qh = new int[n];
      
      int maxq = 0;
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         ql[k] = Integer.parseInt(st.nextToken());
         qh[k] = Integer.parseInt(st.nextToken());
         maxq = Math.max(maxq, qh[k]);
      }
      
      StringBuilder r = new StringBuilder("");
      StringBuilder w = new StringBuilder("");
      
      for(int k = 0; k < m; k++){
         r.append('R');
         w.append('W');
      }
      
      ArrayList<HashSet<StringBuilder>> dp = new ArrayList<HashSet<StringBuilder>>(maxq+1);
      
      for(int k = 0; k <= maxq+1; k++) dp.add(new HashSet<StringBuilder>());
      
      dp.get(1).add(new StringBuilder("R"));
      
      for(int k = 2; k <= maxq+1; k++){
         for(StringBuilder s : dp.get(k-1)){
            StringBuilder temp = new StringBuilder(s.toString());
            dp.get(k).add(temp.append('R'));
            if(temp.length()>=m && temp.substring(temp.length()-m).equals(r.toString())){
               StringBuilder temp2 = new StringBuilder(temp.toString());
               temp2.replace(temp2.length()-m,temp2.length(),w.toString());
               dp.get(k).add(temp2);
            }
         }
      }
      
      for(int k = 0; k < n; k++){
         int count = 0;
         for(int j = ql[k]; j <= qh[k]; j++){
            count+=dp.get(j).size();
         }
         out.println(count);
      }
      
      
      
      out.close();
   }
   
}