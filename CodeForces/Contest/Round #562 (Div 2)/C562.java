//make sure to make new file!
import java.io.*;
import java.util.*;

public class C562{

   public static int m;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      

      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());

      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int l = 0;
      int r = m-1;
      int mid;
      
      
      int answer = m;
      while(l<=r){
         mid = (l+r)/2;
         //out.println(mid);
         if(check(array,mid)){
            r=mid-1;
            answer = mid;
         } else {
            l=mid+1;
         }
      }
      
      out.println(answer);
      
      
      out.close();
   }
   
   public static boolean check(int[] array, int i){
      int prev = array[0];
      if(m-array[0] <= i) prev = 0;
      
      for(int k = 1; k < array.length; k++){
         if(array[k] < prev){
            if(prev-array[k] > i) return false;
            //if <= move array[k] to prev, do nothing
         } else {
            //see if you can move array lower, to = prev
            if(m-array[k] + prev > i){
               prev = array[k];
            }
            //if <= move array[k] to prev, do nothing
         }
       }
       
       return true;
    }
            
               
   
   
}