//Polycarp's Practice

import java.io.*;
import java.util.*;

public class B498{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      PriorityQueue<Integer> pq = new PriorityQueue(n,Collections.reverseOrder());
      
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         pq.add(array[k]);
      }
      
      ArrayList<Integer> alist = new ArrayList<Integer>();
      int sum = 0;
      for(int k = 0; k < m; k++){
         int i = pq.poll();
         sum+=i;
         alist.add(i);
      }
      System.out.println(sum);
      
      int cur = 0;
      for(int k = 1; k <= n; k++){
         if(alist.size()==1){
            System.out.print(n-cur);
            System.exit(0);
         }
         if(alist.contains(array[k-1])){
         
            System.out.print((k-cur) + " ");
            cur = k;
            alist.remove(Integer.valueOf(array[k-1]));
         }
      }
      
   }
}