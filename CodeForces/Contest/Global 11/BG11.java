//make sure to make new file!
import java.io.*;
import java.util.*;

public class BG11{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         char[] array = f.readLine().toCharArray();
         
         
         int score = 0;
         int numl = 0;
         for(int k = 0; k < n; k++){
            if(array[k] == 'W'){
               score++;
               if(k > 0 && array[k-1] == 'W') score++;
            } else numl++;
            
            
         }
         
         if(m == 0){
            out.println(score);
            continue;
         }
         
         if(score == 0){
            int answer = (m-1)*2+1;
            out.println(answer);
            continue;
         }   
         
         m = Math.min(m,numl);
         //fill in as many middle gaps as you can
         PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
         int start = 0;
         for(int k = 0; k < n; k++){
            if(array[k] == 'W'){
               if(start != 0 && start != k) pq.add(k-start);
               start = k+1;
            }
         }
         
         while(!pq.isEmpty()){
            int i = pq.poll();
            if(i <= m){
               score += i*2+1;
            } else {
               break;
            }
            m-=i;
         }
         
         score += 2*m;
         out.println(score);
      }
      
      
      
      
      out.close();
   }
   
      
}