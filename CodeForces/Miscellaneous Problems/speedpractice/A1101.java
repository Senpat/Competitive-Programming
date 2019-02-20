//make sure to make new file!
import java.io.*;
import java.util.*;

public class A1101{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      for(int k = 0; k < n; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         int d = Integer.parseInt(st.nextToken());
         
         if(d < l){
            out.println(d);
         } else if( d > r){
            out.println(d);
         } else {
            out.println((r/d+1)*d);
         }
         
      }
      
      
      
      
      out.close();
   }
   
      
}