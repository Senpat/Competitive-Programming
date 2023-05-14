//make sure to make new file!
import java.io.*;
import java.util.*;

public class BNWR{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         int d = Integer.parseInt(st.nextToken());
         int w = Integer.parseInt(st.nextToken());
         
         int[] array = new int[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int packs = 0;
         int curend = -1;
         int leftpack = 0;
         
         int i = 0;
         while(i < n){
            if(leftpack > 0 && array[i] <= curend){
               leftpack--;
               i++;
            } else {
               //make new pack
               packs++;
               curend = array[i] + w + d;
               leftpack = m-1;
               i++;
            }
         }
         
         out.println(packs);
         
         
         

      }
      
      
      
      
      out.close();
   }
   
      
}