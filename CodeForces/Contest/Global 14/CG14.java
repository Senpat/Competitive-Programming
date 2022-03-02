//make sure to make new file!
import java.io.*;
import java.util.*;

public class CG14{
   
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
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
               return array[(int)i1] - array[(int)i2];
            }
         });
         
         for(int k = 0; k < n; k++){
            pq.add(k);
         }
         
         int i = 0;
         int[] answer = new int[n];
         
         while(!pq.isEmpty()){
            int num = pq.poll();
            answer[num] = i%m + 1;
            
            
            i++;
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sj.add("" + answer[k]);
         }
         
         out.println("YES");
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}