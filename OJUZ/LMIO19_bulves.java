//make sure to make new file!
import java.io.*;
import java.util.*;
//https://usaco.guide/adv/slope-trick?lang=cpp

/*
ex: a-b array is 2 2 2 -5 2 2 2
prefix sums: 2 4 6 1 3 5 7

optimal ending configuration: 2 1 0 0 0 2 2
prefix sums: 2 3 3 3 3 5 7
note that this is nondecreasing because optimal ending configuration has to be nonnegative
cost of this configuration is sum of abs of difference with original prefix sums
the difference is basically the number of fertilizer that has to go over that gap

find the prefix sum of optimal ending configuration
so the solution is to use slope trick to optimize dp[x][d] dp, (first x numbers, min cost to get last value of prefix sum <= d)
transition to d2 is add |pd[x] - d2| -> add pd[x] twice, then get suffix min by removing biggest point
*/

public class LMIO19_bulves{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      long[] d = new long[n+1];
      long[] pd = new long[n+1];
      
      for(int k = 1; k <= n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         
         d[k] = a-b;
         pd[k] = d[k] + pd[k-1];
      }
      
      long x0 = 0L;        //value at 0
      
      PriorityQueue<Long> pq = new PriorityQueue<Long>(10,Collections.reverseOrder());
      for(int k = 1; k <= n; k++){
         if(pd[k] <= 0){
            x0 += -1L * pd[k];
            
            //basically starting at 0, and updating x0
            //slope changing point at 0 is implied, no need to store
            if(!pq.isEmpty()){
               pq.poll();
            }
         } else {
            x0 += pd[k];
            pq.add(pd[k]);
            pq.add(pd[k]);
            
            pq.poll();
         }
      }
      
      //get value at pd[n]
      long answer = x0;
      
      long slope = (long)pq.size();
      //pd[n] is guaranteed to be in the pq, unless it is 0
      if(pd[n] > 0){
         //need to sort elements in pq in the other order
         PriorityQueue<Long> pq2 = new PriorityQueue<Long>();
         while(!pq.isEmpty()) pq2.add(pq.poll());
         
         long i = 0L;
         while(true){
            long top = pq2.poll();
            answer -= (top - i) * slope;
            i = top;
            slope--;
            if(top == pd[n]) break;
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   
      
}