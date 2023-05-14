//make sure to make new file!
import java.io.*;
import java.util.*;

public class CNWR{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long nl = (long)n;
         int x = Integer.parseInt(st.nextToken());
         long p = Long.parseLong(st.nextToken());
         
         if(x != 0) x = n-x;
         long xl = (long)x;
         
         //do first n
         long[] first = new long[n];        //first time each mod is reached
         Arrays.fill(first,-1);
         int cur = 0;
         for(int k = 1; k <= n; k++){
            cur = (cur + k)%n;
            if(first[cur] == -1) first[cur] = (int)k;
         }
         
         //do chunks of n
         long rep = 1;
         while(rep <= n && first[x] == -1){
            int prev = (int)(((xl-rep*(long)cur)%nl + nl)%nl);
            if(first[prev] != -1){
               first[x] = first[prev] + rep*(long)n;
               break;
            }
            
            rep++;
         }
         
         
         
         if(first[x] != -1 && first[x] <= p){
            out.println("Yes");
         } else {
            out.println("No");
         }
         

      }
      
      
      
      
      out.close();
   }
   
      
}