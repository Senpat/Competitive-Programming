//make sure to make new file!
import java.io.*;
import java.util.*;

public class C142{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int l = 1;
         int r = n/2;
         int ans = n/2;
         
         while(l <= r){
            int mid = l + (r-l)/2;
            
            if(check(array,mid,n-mid+1)){
               ans = mid-1;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
      
         out.println(ans);
      }
      
      
      
      
      out.close();
   }
   
   //check that [l,r] is in order in array
   public static boolean check(int[] array, int l,int r){
      int i = l;
      for(int k = 0; k < array.length; k++){
         if(array[k] == i) i++;
      }
      return i > r;
   }
   
      
}