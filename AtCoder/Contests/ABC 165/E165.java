//make sure to make new file!
import java.io.*;
import java.util.*;

public class E165{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      HashSet<Integer> hset = new HashSet<Integer>();
      
      Pair[] answer = new Pair[m];
      
      for(int k = 1; k <= m; k++){
         int i = n-k+1;
         while(hset.contains(i-k) || hset.contains(n-(i-k)) || i-k == (n-(i-k))){
            i--;
         }
         hset.add(i-k);
         hset.add(n-(i-k));
         answer[k-1] = new Pair(k,i);
      }
      
      for(int k = 0; k < m; k++){
         out.println(answer[k].a + " " + answer[k].b);
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Pair{
      int a;
      int b;
      public Pair(int c, int d){
         a = c;
         b = d;
      }
   }
   
      
}