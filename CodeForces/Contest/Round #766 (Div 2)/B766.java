//make sure to make new file!
import java.io.*;
import java.util.*;

public class B766{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
         
         for(int k = 1; k <= n; k++){
            for(int j = 1; j <= m; j++){
               pq.add(getdist(k,j,n,m));
            }
         }
         
         StringJoiner sj = new StringJoiner(" ");
         while(!pq.isEmpty()){
            sj.add("" + pq.poll());
         }
         out.println(sj.toString());
         
      }
      
      
      
      
      out.close();
   }
   
   public static int getdist(int a, int b, int n, int m){
      int a1 = Math.abs(a-1) + Math.abs(b-1);
      int a2 = Math.abs(a-n) + Math.abs(b-1);
      int a3 = Math.abs(a-1) + Math.abs(b-m);
      int a4 = Math.abs(a-n) + Math.abs(b-m);
      
      return Math.max(Math.max(a1,a2),Math.max(a3,a4));
   }
      
}