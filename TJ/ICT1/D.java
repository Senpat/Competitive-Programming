//make sure to make new file!
import java.io.*;
import java.util.*;

public class D{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] pre = new int[n+1];
      pre[1] = array[0];
      int[] maxes = new int[n];
      maxes[0] = array[0];
      
      for(int k = 1; k < n; k++){
         pre[k+1] = pre[k] + array[k];
         maxes[k] = Math.max(maxes[k-1],array[k]);
      }
      
      int min = Integer.MAX_VALUE;
      int amax = array[0];
      int h = array[0];
      int a = array[0];
      
      for(int k = 0; k < n; k++){
         int havish = pre[k+1]-maxes[k];
         int arvind = pre[n]-pre[k+1];
         if(min > Math.abs(havish-arvind)){
            min = Math.abs(havish-arvind);
            amax = k+1;
            h = havish;
            a = arvind;
         }
         
      }
      
      out.println(amax + " " + h + " " + a);
      
      
      
      out.close();
   }
   
}