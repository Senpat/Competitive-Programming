//make sure to make new file!
import java.io.*;
import java.util.*;

public class B774{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         Long[] array = new Long[n];
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         Arrays.sort(array);
         
         int l = 1;
         int r = n-1;
         long red = 0L;
         long blue = array[0];
         boolean found = false;
         while(l < r){
            red += array[r];
            blue += array[l];
            if(red > blue){
               found = true;
               break;
            }
            l++;
            r--;
         }
         if(found){
            out.println("YES");
         } else {
            out.println("NO");
         }
      
      

      }
      
      
      
      
      out.close();
   }
   
      
}