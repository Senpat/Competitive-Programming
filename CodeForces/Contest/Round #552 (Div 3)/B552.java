//make sure to make new file!
import java.io.*;
import java.util.*;

public class B552{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      
      int[] array = new int[n];
      HashSet<Integer> hset = new HashSet<Integer>();
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         hset.add(array[k]);
      }
      
      if(hset.size() == 1){
         out.println(0);
      }else if(hset.size()==2){
         int a = -1;
         int b = -1;
         for(int i : hset){
            if(a != -1){
               b = i;
            } else {
               a = i;
            }
         }
         
         int dif = Math.abs(a-b);
         if(dif%2 == 0){
            out.println((dif/2));
         } else {
            out.println(dif);
         }
      } else if(hset.size() == 3){
         int a = -1;
         int b = -1;
         int c = -1;
         int max = -1;
         int min = 105;
         for(int i : hset){
            if(b != -1){
               c = i;
            } else if(a != -1) {
               b = i;
            } else {
               a = i;
            }
            max = Math.max(max,i);
            min = Math.min(min,i);
         }
         
         int mdif = max-min;
         if(mdif % 2 == 0 && hset.contains(min + (mdif/2))){
            out.println(mdif/2);
         } else {
            out.println(-1);
         }
      } else {
         out.println(-1);
      }
         
         
         
      
      

      
      
      
      
      out.close();
   }
   
      
}