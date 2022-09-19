//make sure to make new file!
import java.io.*;
import java.util.*;

public class D809b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] array = new int[n];
         st = new StringTokenizer(f.readLine());
         int min = Integer.MAX_VALUE;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            min = Math.min(min,array[k]);
         }
         
         HashSet<Integer> hset = new HashSet<Integer>();
         hset.add(0);
         hset.add(min);
         for(int k = 0; k < n; k++){
            for(int j = 1; array[k]/j < min; j++){
               hset.add(array[k]/j);
            }
         }
         //min
         
         int answer = Integer.MAX_VALUE;
         for(int k : hset){
            int max = 0;
            for(int j = 0; j < n; j++){
               int p;
               if(k != 0){
                  p = Math.min(m,array[j]/k);
               } else {
                  p = Math.min(m,array[j]+1);
               }
               max = Math.max(max,array[j]/p);
            }
            answer = Math.min(answer,max-k);
         }
         
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}