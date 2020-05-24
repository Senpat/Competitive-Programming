//make sure to make new file!
import java.io.*;
import java.util.*;

public class G{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());

      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(f.readLine());
      }
      
      PriorityQueue<Item> pq = new PriorityQueue<Item>(n,Collections.reverseOrder());
      pq.add(new Item(array[0],0));
      
      for(int k = 1; k < n; k++){
         pq.add(new Item(array[k]-array[k-1],k));
      }
      
      while(!pq.isEmpty()){
         Item i = pq.poll();
         out.println(i.i + " " + (i.n+1));
      }
      
      

      
      
      
      
      
      out.close();
   }
   
   public static class Item implements Comparable<Item>{
      long i;
      int n;
      
      public Item(long a, int b){
         i = a;
         n = b;
      }
      
      public int compareTo(Item I){
         if(i > I.i) return 1;
         if(i < I.i) return -1;
         return 0;
      }
   }
      
}