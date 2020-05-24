//make sure to make new file!
import java.io.*;
import java.util.*;

public class P3{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         
         Pair[] array = new Pair[n*m];
         
         for(int k = 0; k < m; k++){
            for(int j = 0; j < n; j++){
               array[k*m+j] = new Pair(j+1,k+1);
            }
         }
         
         int i = 0;
         ArrayList<Pair> answer = new ArrayList<Pair>();
         
         while(true){
            boolean found = false;
            
            int on = n*m-i/n;
            int index1 = -1;
            for(int k = 0; k < n*m-1; k++){
               if(array[k].r == on && array[k+1].r < on){
                  found = true;
                  index = k;
               }
            }
            
            if(!found) return;
            
            int index2 = -1;
            for(int k = n*m-1; k >= 0; k--){
               if(
            
            
            
            
         }
         
         out.println("Case #" + q + ": " + i);
         for(Pair p : answer){
            out.println(p.r + " " + p.s);
         }
         
         
         

      }
      
      
      
      
      out.close();
   }
   
   public static class Pair{
      int r;
      int s;
      public Pair(int a, int b){
         r = a;
         s = b;
      }
   }
      
}