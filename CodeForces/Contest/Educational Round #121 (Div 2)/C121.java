//make sure to make new file!
import java.io.*;
import java.util.*;

public class C121{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         
         long[] time = new long[n];
         long[] health = new long[n];
         
         for(int k = 0; k < n; k++){
            time[k] = Long.parseLong(st1.nextToken());
            health[k] = Long.parseLong(st2.nextToken());
         }
         
         Stack<Start> stack = new Stack<Start>();
         
         for(int k = 0; k < n; k++){
            Start cur = new Start(time[k]-health[k]+1,time[k]);
            if(stack.isEmpty()){
               stack.push(cur);
            } else {
               while(!stack.isEmpty() && stack.peek().start >= cur.start) stack.pop();
               
               if(!stack.isEmpty() && stack.peek().end >= cur.start){
                  cur.start = stack.peek().start;
                  stack.pop();
               }
               
               stack.push(cur);
            }
         }
         
         long answer = 0L;
         
         while(!stack.isEmpty()){
            Start cur = stack.pop();
            long dif = cur.end-cur.start+1;
            
            answer += dif*(dif+1)/2;
         }
         
         out.println(answer);
         
               
      }
      
      
      
      
      out.close();
   }
   
   public static class Start{
      long start;
      long end;
      public Start(long a, long b){
         start = a;
         end = b;
      }
   }
      
}