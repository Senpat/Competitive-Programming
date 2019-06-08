//make sure to make new file!
import java.io.*;
import java.util.*;

public class D561{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      long[] pow2 = new long[51];
      pow2[0] = 1;
      for(int k = 1; k < pow2.length; k++){
         pow2[k] = pow2[k-1]*2;
      }
      
      for(int q = 0; q < t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         long m = Long.parseLong(st.nextToken());
         
         ArrayList<Long> max = new ArrayList<Long>();
         ArrayList<Long> min = new ArrayList<Long>();
         
         max.add(a);
         min.add(a);
         
         if(b < a){
            out.println(-1);
            continue;
         }
         if(b == a){
            out.println("1 " + a);
            continue;
         }
         
         int i = 1;
         while(max.get(max.size()-1) < b){
            max.add(pow2[i-1]*(a+m));
            min.add(pow2[i-1]*(a+1));
            i++;
         }
         
         int s = max.size()-1;
         if(min.get(s) > b){
            out.println(-1);
            continue;
         }
         
         out.print(i);
         out.print(" " + a);
         for(int k = 1; k <= s; k++){
            long answer = min.get(k) + 1 + 1L*(max.get(k)-min.get(k))*(b-min.get(s))/(max.get(s)-min.get(s));
            out.print(" " + answer);
         }
         out.println();
         
         
      
      }
      
      
      
      out.close();
   }
   
      
}