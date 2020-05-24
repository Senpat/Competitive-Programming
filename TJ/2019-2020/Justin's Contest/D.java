//make sure to make new file!
import java.io.*;
import java.util.*;

public class D{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
   
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      PriorityQueue<Item> pq = new PriorityQueue<Item>();
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         pq.add(new Item(a,b));
      }
      
      int answer = 0;
      while(!pq.isEmpty()){
         Item i = pq.poll();
         
         if(i.i*i.n <= n){
            answer += i.n;
            n-=i.i*i.n;
         } else {
            answer += n/i.i;
            break;
         }
      }
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   public static class Item implements Comparable<Item>{
      int i;
      int n;
      
      public Item(int a, int b){
         i = a;
         n = b;
      }
      
      public int compareTo(Item I){
         return i-I.i;
      }
   }
   
      
}