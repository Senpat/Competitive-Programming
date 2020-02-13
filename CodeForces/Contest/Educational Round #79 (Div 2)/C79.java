//make sure to make new file!
import java.io.*;
import java.util.*;

public class C79{
   
   public static int[] bits;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] array1 = new int[n];
         int[] array2 = new int[m];
         
         st = new StringTokenizer(f.readLine());
         int[] indexof = new int[n+1];
         for(int k = 0; k < n; k++){
            array1[k] = Integer.parseInt(st.nextToken());
            indexof[array1[k]] = k+1;
         }
         
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < m; k++){
            array2[k] = Integer.parseInt(st.nextToken());
         }
         
         bits = new int[n+1];
         
         long answer = 0L;
         int sortedind = 0;
         for(int k = 0; k < m; k++){
            int i = array2[k];
            
            if(indexof[i] < sortedind){
               update(indexof[i],1);
               answer++;
            } else {
               sortedind = indexof[i];
               
               answer += (long)(2*(indexof[i]-psum(indexof[i])-1)+1);
               update(indexof[i],1);
            }
         }
         
         out.println(answer);
            

      }
      
      
      
      
      out.close();
   }
   
      
   public static void update(int i, int x){
      for(; i <= n; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static int psum(int i){
      int sum = 0;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum += bits[i];
      }
      return sum;
   
   }

   
      
}