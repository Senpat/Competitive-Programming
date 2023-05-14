//make sure to make new file!
import java.io.*;
import java.util.*;

public class CTONc{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long c = Long.parseLong(st.nextToken());
         long d = Long.parseLong(st.nextToken());
         
         ArrayList<Long> alist = new ArrayList<Long>();
         HashSet<Long> hset = new HashSet<Long>();
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            long i = Long.parseLong(st.nextToken());
            if(!hset.contains(i)){
               hset.add(i);
               alist.add(i);
            }
         }
         
         Collections.sort(alist);
         
         long answer = c * (long)n + d;            //remove everything and add 1
         for(int k = 0; k < alist.size(); k++){
            //make a permutation with alist.get(k) as the biggest value
            answer = Math.min(answer,d * (alist.get(k)-(k+1)) + c * (long)(n-k-1));

         }
         
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
      
}