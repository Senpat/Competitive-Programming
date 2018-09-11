//Gambling
import java.io.*;
import java.util.*;

public class C508{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      PriorityQueue<Long> pqa = new PriorityQueue<Long>(n, Collections.reverseOrder());
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         pqa.add(Long.parseLong(st.nextToken()));
      }
      
      PriorityQueue<Long> pqb = new PriorityQueue<Long>(n, Collections.reverseOrder());
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         pqb.add(Long.parseLong(st.nextToken()));
      }
      
      long a = 0L;
      long b = 0L;
      while(!pqa.isEmpty() || !pqb.isEmpty()){
         //a's turn
         if(pqb.isEmpty()){
            a+=pqa.poll();
         } else if (pqa.isEmpty()){
            pqb.poll();
         } else if (pqa.peek() > pqb.peek()){
            a+=pqa.poll();
         } else {
            pqb.poll();
         }
         
         //b's turn
         if(pqa.isEmpty()){
            b+=pqb.poll();
         } else if (pqb.isEmpty()){
            pqa.poll();
         } else if (pqb.peek() > pqa.peek()){
            b+=pqb.poll();
         } else {
            pqa.poll();
         }
      }
      
      out.println(a-b);
         
      out.close();
   }
   
}