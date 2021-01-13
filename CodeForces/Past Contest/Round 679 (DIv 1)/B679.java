//make sure to make new file!
import java.io.*;
import java.util.*;

public class B679{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String[] queries = new String[2*n];
      for(int k = 0; k < 2*n; k++){
         queries[k] = f.readLine();
      }
      
      int[] array = new int[n];
      Stack<Integer> stk = new Stack<Integer>();
      int i = 0;
      for(int k = 0; k < 2*n; k++){
         if(queries[k].charAt(0) == '+'){
            stk.add(i);
            i++;
         } else {
            int x = Integer.parseInt(queries[k].substring(2));
            if(stk.isEmpty()){
               out.println("NO");
               out.close();
               return;
            }
            
            array[stk.pop()] = x;
         }
      }
      
      //check
      boolean fail = false;
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
      i = 0;
      for(int k = 0; k < 2*n; k++){
         if(queries[k].charAt(0) == '+'){
            pq.add(array[i]);
            i++;
         } else {
            int x = Integer.parseInt(queries[k].substring(2));
            if(pq.isEmpty() || pq.peek() != x){
               fail = true;
               break;
            }
            
            pq.poll();
         }
      }
      
      if(fail){
         out.println("NO");
      } else {
         out.println("YES");
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sj.add("" + array[k]);
         }
         out.println(sj.toString());
      }
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}