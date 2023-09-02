//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2215{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         if(m*m < n){
            out.println("IMPOSSIBLE");
            continue;
         }
         
         ArrayList<Integer> answer = new ArrayList<Integer>();
         int k = n-m+1;
         for(; k >= 1; k -= m){
            for(int j = k; j < k+m; j++){
               answer.add(j);
            }
         }
         
         for(int j = 1; j <= k+m-1; j++){
            answer.add(j);
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int i : answer){
            sj.add("" + i);
         }
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}