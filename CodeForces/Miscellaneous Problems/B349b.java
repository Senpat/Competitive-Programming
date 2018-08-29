//Color the Fence
import java.io.*;
import java.util.*;
//self, wrong
public class B349b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
      
      int[] array = new int[10];
      
      int min = Integer.MAX_VALUE;
      
      int maxd = 0;
      int maxdi = 0;
      for(int k = 1; k <= 9; k++){
         int i = Integer.parseInt(st.nextToken());
         map.put(i,k);
         array[k] = i;
         min = Math.min(min,i);
         
         if(n/i >= maxd){
            maxd = n/i;
            maxdi = k;
         }
      }
      
      if(n<min){
         out.println(-1);
         out.close();
         System.exit(0);
      }
      
      StringBuilder sb = new StringBuilder("");
      
      for(int k = 0; k < maxd; k++){
         sb.append(maxdi);
      }
      
      int remain = n%maxd;
      
      boolean bool = true;
      int digit = 0;
      while(bool && remain > 0 && digit<sb.length()){
         bool = false;
         for(int k = array.length-1; k > maxdi && !bool; k--){
            if(array[k]-array[maxdi] <= remain){
               bool = true;
               sb.replace(digit,digit+1,String.valueOf(k));
               remain-=array[k]-array[maxdi];
            }
         }
         digit++;
      }
      
      out.println(sb);
      
      out.close();
   }

   
}