//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1644{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      long[] array = new long[n];
      long[] psums = new long[n+1];
      psums[0] = 0L;
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         psums[k+1] = psums[k] + array[k];
      }
      
      long answer = Long.MIN_VALUE;
      
      //monotonic queue, find the minimum in the window
      //stores elements in increasing order
      Deque<Element> dq = new ArrayDeque<Element>();
      
      for(int k = 1; k <= n; k++){
         if(k-a < 0) continue;
         //add to queue
         while(!dq.isEmpty() && dq.peekLast().val >= psums[k-a]){
            dq.pollLast();
         }
         
         dq.addLast(new Element(psums[k-a],k-a));
         
         //get min
         while(dq.peekFirst().i < k-b){
            dq.pollFirst();
         }
         
         long min = dq.peekFirst().val;
         answer = Math.max(answer,psums[k]-min);
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Element{
      long val;
      int i;
      public Element(long a, int b){
         val = a;
         i = b;
      }
   }
      
}