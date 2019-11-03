//Alice and the Unfair Game
import java.io.*;
import java.util.*;

public class E1236{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int[] array = new int[m];
      for(int k = 0; k < m; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      if(n==1){
         out.println("0");
         out.close();
         System.exit(0);
      }
      
      
      //getting greater
      HashMap<Integer,LList> down = new HashMap<Integer,LList>();
      for(int k = 1; k <= n; k++){
         down.put(k+m+1,new LList(new Node(k,null),null));
         down.get(k+m+1).tail = down.get(k+m+1).head;
      }
      
      for(int k = 0; k < m; k++){
         //affects array[k] + m - k
         if(!down.containsKey(array[k]+m-k)) continue;
         
         if(down.containsKey(array[k]+m-k-1)){
            down.get(array[k]+m-k-1).add(down.get(array[k]+m-k));
         } else {
            down.put(array[k]+m-k-1,down.get(array[k]+m-k));
         }
         down.remove(array[k]+m-k);
      }
      
      long answer = 0;
      for(int i : down.keySet()){
         Node no = down.get(i).head;
         answer += (long)(Math.min(n,i)-no.i);
         while(no.next != null){
            no = no.next;
            answer += (long)(Math.min(n,i)-no.i);
         }
      }
      //out.println(answer);
      //getting smaller 
      HashMap<Integer,LList> up = new HashMap<Integer,LList>();
      for(int k = 1; k <= n; k++){
         up.put(k-m-1,new LList(new Node(k,null),null));
         up.get(k-m-1).tail = up.get(k-m-1).head;
      }
      
      for(int k = 0; k < m; k++){
         //affects array[k] - m + k
         if(!up.containsKey(array[k]-m+k)) continue;
         
         if(up.containsKey(array[k]-m+k+1)){
            up.get(array[k]-m+k+1).add(up.get(array[k]-m+k));
         } else {
            up.put(array[k]-m+k+1,up.get(array[k]-m+k));
         }
         up.remove(array[k]-m+k);
      }
      
      for(int i : up.keySet()){
         Node no = up.get(i).head;
         answer += (long)(no.i-Math.max(1,i));
         while(no.next != null){
            no = no.next;
            answer += (long)(no.i-Math.max(1,i));
         }
      }
      //out.println(answer);
      answer += (long)n;
      
      
      
      
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   public static class Node{
      int i;
      Node next;
      public Node(int a, Node b){
         i = a;
         next = b;
      }
   }
   
   public static class LList{
      Node head;
      Node tail;
      public LList(Node a, Node b){
         head = a;
         tail = b;
      }
      public void add(LList L){
         tail.next = L.head;
         tail = L.tail;
      }
   }
   
      
}