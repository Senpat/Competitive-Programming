//make sure to make new file!
import java.io.*;
import java.util.*;
//solves second version
public class C672b{
   
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         long initial = 0L;
         if(n==1){
            initial = array[0];
         } else {
            for(int k = 0; k < n; k++){
               if(checkadd(k,array)) initial += array[k];
               else if(checksubtract(k,array)) initial -= array[k];
            }
         }
         
         out.println(initial);
         
         for(int q1 = 0; q1 < m; q1++){
            st = new StringTokenizer(f.readLine());
            int l = Integer.parseInt(st.nextToken())-1;
            int r = Integer.parseInt(st.nextToken())-1;
            if(n == 1){
               out.println(initial);
               continue;
            }
            HashSet<Integer> hset = new HashSet<Integer>();
            for(int k = l-1; k <= l+1; k++) if(in(k)) hset.add(k);
            for(int k = r-1; k <= r+1; k++) if(in(k)) hset.add(k);
            
            for(int i : hset){
               if(checkadd(i,array)) initial -= array[i];
               if(checksubtract(i,array)) initial += array[i];
            }
            
            //swap
            long temp = array[l];
            array[l] = array[r];
            array[r] = temp;
            
            for(int i : hset){
               if(checkadd(i,array)) initial += array[i];
               if(checksubtract(i,array)) initial -= array[i];
            }
            
            out.println(initial);
         }
         
      

      }
      
      
      
      
      out.close();
   }
   
   public static boolean in(int x){
      return x >= 0 && x < n;
   }
   
   public static boolean checkadd(int x, long[] array){
      if(x == 0) return array[0] > array[1];
      if(x == n-1) return array[n-1] > array[n-2];
      return array[x] > array[x-1] && array[x] > array[x+1];
   }
   
   public static boolean checksubtract(int x, long[] array){
      if(x == 0 || x == n-1) return false;
      return array[x] < array[x-1] && array[x] < array[x+1];
   }
      
}