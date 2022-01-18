//make sure to make new file!
import java.io.*;
import java.util.*;

public class C764{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         PriorityQueue<Integer> pq = new PriorityQueue<Integer>(n,Collections.reverseOrder());
         
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            pq.add(array[k]);
         }
         
         int target = n;
         
         while(!pq.isEmpty()){
            int i = pq.poll();
            if(i > target){
               pq.add(i/2);
            } else if(i == target){
               target--;
            } else {
               break;
            }
         }
         
         if(target == 0){
            out.println("YES");
         } else {
            out.println("NO");
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}