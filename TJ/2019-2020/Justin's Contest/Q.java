//make sure to make new file!
import java.io.*;
import java.util.*;

public class Q{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      Deque<Integer> dq = new LinkedList<Integer>();
      
      ArrayList<Integer> answer = new ArrayList<Integer>();
      //add m
      for(int k = 0; k < m; k++){
         while(!dq.isEmpty() && dq.getLast() > array[k]){
            dq.pollLast();
         }
         dq.addLast(array[k]);
      }
      
      answer.add(dq.getFirst());
      
      for(int k = 0; k < n-m; k++){
         //remove
         if(dq.getFirst() == array[k]){
            dq.pollFirst();
         }
         
         //add
         while(!dq.isEmpty() && dq.getLast() > array[k+m]){
            dq.pollLast();
         }
         dq.addLast(array[k+m]);
         answer.add(dq.getFirst());
      }
      
      for(int i : answer){
         out.print(i + " ");
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}