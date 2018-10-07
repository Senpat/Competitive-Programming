//Cashier
import java.io.*;
import java.util.*;

public class A514b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      
      Queue<Integer> q1 = new LinkedList<Integer>();
      Queue<Integer> q2 = new LinkedList<Integer>();
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         
         int c = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         q1.add(c);
         q2.add(c+b);
      }
      
      
      int smokes = 0;
      int counter = 0;
      
      int cur = 0;
      int next;
      int nextend;
      while(!q1.isEmpty()){
         next = q1.poll();
         nextend = q2.poll();
      
         smokes += (next-cur)/a;
         
         cur = nextend;
      }
      
      smokes += (m-cur)/a;
      
      out.println(smokes);
      
      
      out.close();
   }
   
}