//make sure to make new file!
import java.io.*;
import java.util.*;

public class D229{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      char[] array = f.readLine().toCharArray();
      int n = array.length;
      
      int x = Integer.parseInt(f.readLine());
      
      int l = 1;
      int r = n;
      int ans = 0;
      
      while(l <= r){
         int mid = l + (r-l)/2;
         
         if(check(array,x,mid)){
            ans = mid;
            l = mid+1;
         } else {
            r = mid-1;
         }
      }
      
      out.println(ans);
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean check(char[] array, int x, int w){
      //initial
      int numd = 0;
      for(int k = 0; k < w; k++){
         if(array[k] == '.') numd++;
      }
      
      if(numd <= x) return true;
      
      for(int k = w; k < array.length; k++){
         if(array[k-w] == '.') numd--;
         if(array[k] == '.') numd++;
         if(numd <= x) return true;
      }
      return false;
   }
      
}