//make sure to make new file!
import java.io.*;
import java.util.*;

public class D582a{

   public static int MAX = 200005;
   
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
      
      
      ArrayList<PriorityQueue<Integer>> alist = new ArrayList<PriorityQueue<Integer>>(MAX);
      for(int k = 0; k < MAX; k++) alist.add(new PriorityQueue<Integer>());
      
      for(int k = 0; k < n; k++){
         int i = array[k];
         int count = 0;
         while(i >= 1){
            alist.get(i).add(count);
            count++;
            i/=2;
         }
      }
      
      int answer = Integer.MAX_VALUE;
      for(int k = 1; k < MAX; k++){
         if(alist.get(k).size() < m) continue;
         int count = 0;
         for(int j = 0; j < m; j++){
            count+=alist.get(k).poll();
         }
         answer = Math.min(count,answer);
      }
      
      out.println(answer);
      
      
      
      out.close();
   }
   
      
}