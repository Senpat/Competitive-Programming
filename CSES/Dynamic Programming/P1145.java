//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1145{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      ArrayList<Integer> lis = new ArrayList<Integer>();
      lis.add(array[0]);
      
      for(int k = 1; k < n; k++){
         int l = 0;
         int r = lis.size()-1;
         
         int ans = -1;
         
         while(l <= r){
            int mid = (l+r)/2;
            
            if(lis.get(mid) >= array[k]){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
         
         if(ans == -1){
            lis.add(array[k]);
         } else {
            lis.set(ans,array[k]);
         }
         
      }
      
      out.println(lis.size());
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}