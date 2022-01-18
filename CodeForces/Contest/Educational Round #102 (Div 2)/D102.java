//make sure to make new file!
import java.io.*;
import java.util.*;

public class D102{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         char[] array = f.readLine().toCharArray();
         
         int[] pmax = new int[n];
         int[] pmin = new int[n];
         int[] at = new int[n];
         
         if(array[0] == '+'){
            at[0] = 1;
            pmax[0] = 1;
            pmin[0] = 0;
         } else {
            at[0] = -1;
            pmax[0] = 0;
            pmin[0] = -1;
         }
         
         for(int k = 1; k < n; k++){
            if(array[k] == '+'){
               at[k] = at[k-1]+1;
            } else {
               at[k] = at[k-1]-1;
            }
            pmax[k] = Math.max(at[k],pmax[k-1]);
            pmin[k] = Math.min(at[k],pmin[k-1]);
         }
         
         
         int[] smax = new int[n];
         int[] smin = new int[n];
         
         if(array[n-1] == '+'){
            smax[n-1] = 1;
            smin[n-1] = 0;
         } else {
            smax[n-1] = 0;
            smin[n-1] = -1;
         }
         
         for(int k = n-2; k >= 0; k--){
            if(array[k] == '+'){
               smax[k] = Math.max(1,smax[k+1]+1);
               smin[k] = Math.min(0,smin[k+1]+1);
            } else {
               smax[k] = Math.max(0,smax[k+1]-1);
               smin[k] = Math.min(-1,smin[k+1]-1);
            }
         }
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
            
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            
            int max = 0;
            int min = 0;
            int curat = 0;
            
            if(a > 0){
               max = pmax[a-1];
               min = pmin[a-1];
               curat  = at[a-1];
            }
            
            if(b < n-1){
               max = Math.max(max,curat+smax[b+1]);
               min = Math.min(min,curat+smin[b+1]);
            }
            
            out.println(max-min+1);
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}