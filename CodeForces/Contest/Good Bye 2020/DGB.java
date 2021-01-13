//make sure to make new file!
import java.io.*;
import java.util.*;

public class DGB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] weights = new long[n+1];
         
         long answer = 0L;
         for(int k = 1; k <= n; k++){
            weights[k] = Long.parseLong(st.nextToken());
            answer += weights[k];
         }
         
         int[] degree = new int[n+1];
         for(int k = 0; k < n-1; k++){
            st = new StringTokenizer(f.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            degree[a]++;
            degree[b]++;
         }
         
         StringJoiner sj = new StringJoiner(" ");
         sj.add("" + answer);
         
         //compare by weights in backwards order
         PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
               if(weights[(int)a] < weights[(int)b]) return 1;
               if(weights[(int)a] > weights[(int)b]) return -1;
               return 0;
            }
         });
         
         for(int k = 1; k <= n; k++) if(degree[k] > 1) pq.add(k);
         
         for(int k = 2; k < n; k++){
            int i = pq.poll();
            
            answer += weights[i];
            sj.add("" + answer);
            
            degree[i]--;
            if(degree[i] > 1) pq.add(i);
         }
         
         out.println(sj.toString());
            
         

      }
      
      
      
      
      out.close();
   }
   
      
}