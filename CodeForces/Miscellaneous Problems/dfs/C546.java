//Soldier and Cards
import java.io.*;
import java.util.*;

public class C546{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      int factorial = 1;
      for(int k = 2; k <= n; k++) factorial*=k;
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      Queue<Integer> q1 = new LinkedList<Integer>();
      
      int n1 = Integer.parseInt(st.nextToken());
      
      for(int k = 0; k < n1; k++) q1.add(Integer.parseInt(st.nextToken()));
      
      st = new StringTokenizer(f.readLine());
      
      Queue<Integer> q2 = new LinkedList<Integer>();
      
      int n2 = Integer.parseInt(st.nextToken());
      
      for(int k = 0; k < n2; k++) q2.add(Integer.parseInt(st.nextToken()));
      
      int moves = 0;
      
      while(!q1.isEmpty() && !q2.isEmpty() && moves < factorial){
         if(q1.peek() > q2.peek()){
            q1.add(q2.poll());
            q1.add(q1.poll());
         } else {
            q2.add(q1.poll());
            q2.add(q2.poll());
         }
         
         moves++;
      }
      
      if(q1.isEmpty()){
         out.println(moves + " " + 2);
      } else if(q2.isEmpty()){
         out.println(moves + " " + 1);
      } else {
         out.println("-1");
      }
      
      
      
      out.close();
   }
   
}