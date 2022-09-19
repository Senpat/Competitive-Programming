//make sure to make new file!
import java.io.*;
import java.util.*;

public class CSB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      if(m <= 2){
         out.println(m);
         out.close();
         System.exit(0);
      }
      
      char[] input = f.readLine().toCharArray();
      int[] array = new int[n];
      int l = n+1;
      int r = -1;
      
      for(int k = 0; k < n; k++){
         array[k] = Character.getNumericValue(input[k]);
         if(array[k] == 1){
            l = Math.min(l,k);
            r = Math.max(r,k);
         }
      }
      
      int answer = 0;
      while(l < r){
         //do l
         answer++;
         if(array[l+1] == 1){
            if(l+2 < n){
               array[l+1] = 0;
               array[l+2] = 1;
               l = l+2;
            } else {
               l = l+1;
            }
         } else {
            //go until next 1 (there should be guaranteed to be one)
            int i = l+1;
            while(i < n && array[i] == 0) i++;
            if(i >= n){
               //shouldn't happen
               break;
            }
            
            l = i;
         }
         //do r
         answer++;
         if(array[r-1] == 1){
            if(r-2 >= 0){
               array[r-1] = 0;
               array[r-2] = 1;
               //move l if necessary
               if(l == r-1) l = r-2;
               r = r-2;
            } else {
               r = r-1;
            }
         } else {
            //go until next 1 (there should be guaranteed to be one)
            int i = r-1;
            while(i >= 0 && array[i] == 0) i--;
            if(i < 0){
               //shouldn't happen
               break;
            }
            
            r = i;
         }
      }   
      
      if(l == r) answer++;
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}