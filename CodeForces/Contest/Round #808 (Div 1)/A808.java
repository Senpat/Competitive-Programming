//make sure to make new file!
import java.io.*;
import java.util.*;

public class A808{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] array = new int[n];
         int[] belowleft = new int[n];
         st = new StringTokenizer(f.readLine());
         
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(k > 0){
               belowleft[k] = belowleft[k-1];
            }
            if(array[k] <= m){
               belowleft[k]++;
            }
         }
         
         int l = 0;
         int r = n-1;
         int ans = n;
         
         while(l <= r){
            int mid = l + (r-l)/2;
            
            if(check(array,mid,n,m)){
               r = mid-1;
               ans = mid;
            } else {
               l = mid+1;
            }
         
         
         }
         
         int[] answer = new int[n];
         
         for(int k = 0; k < ans; k++){
            if(array[k] <= m) answer[k] = 1;
         }
         for(int k = ans; k < n; k++) answer[k] = 1;
         
         StringJoiner sj = new StringJoiner("");
         for(int k = 0; k < n; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
   
   public static boolean check(int[] array, int x, int n, int m){
      
      int i = m;
      for(int k = x; k < n; k++){
         if(i == 0) return false;
         if(array[k] > i) i--;
      }
      
      return true;
   
   }
      
}