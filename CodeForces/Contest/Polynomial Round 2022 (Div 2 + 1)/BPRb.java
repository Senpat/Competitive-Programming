//make sure to make new file!
import java.io.*;
import java.util.*;

public class BPRb{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         
         Integer[] array = new Integer[m];
         for(int k = 0; k < m; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         Arrays.sort(array);
         
         PriorityQueue<Long> ends = new PriorityQueue<Long>(10,Collections.reverseOrder());
         
         for(int k = 0; k < m; k++){
            if(array[k] == 1) continue;
            ends.add(1L + (long)x*(long)array[k]);
         }
         
         boolean fail = false;
         int sum = 0;
         while(!ends.isEmpty()){
            sum++;
            long i = ends.poll();
            
            if(sum > n-i+1){
               fail = true;
               break;
            }
         }
         
         if(fail){
            out.println("NO");
         } else {
            out.println("YES");
         }
      }
      
      
      
      
      out.close();
   }
   
      
}