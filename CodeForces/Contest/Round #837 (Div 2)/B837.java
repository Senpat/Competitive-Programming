//make sure to make new file!
import java.io.*;
import java.util.*;

public class B837{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         ArrayList<ArrayList<Integer>> ends = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++) ends.add(new ArrayList<Integer>());
         
         TreeSet<Integer> tset = new TreeSet<Integer>();
         int[] freq = new int[n+1];
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
            
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            
            if(r < l){
               int temp = l;
               l = r;
               r = temp;
            }
            
            ends.get(l).add(r);
            
            freq[r]++;
            tset.add(r);
         }
         
         long answer = 0L;
         for(int k = 1; k <= n; k++){
            
            if(tset.isEmpty()){
               answer += (long)(n-k+1);
            } else {
               answer += (long)(tset.first()-k);
            }
            
            for(int r : ends.get(k)){
               freq[r]--;
               if(freq[r] == 0){
                  tset.remove(r);
               }
            }
         }
         
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
   public static class Pair{
      int l;
      int r;
      public Pair(int a, int b){
         l = a;
         r = b;
      }
   }
      
}