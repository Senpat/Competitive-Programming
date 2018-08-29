import java.io.*;
import java.util.*;

class lemonade{
  
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("lemonade.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>(n,Collections.reverseOrder());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         pq.add(Integer.parseInt(st.nextToken()));
      }
      
      int count = 0;
      boolean done = false;
      while(!done && !pq.isEmpty()){
         if(pq.poll()<count){
            done = true;
         } else {
            count++;
         }
      }
      
      System.out.println(count);
      out.println(count);
      out.close();
   }
}