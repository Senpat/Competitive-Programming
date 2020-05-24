//make sure to make new file!
import java.io.*;
import java.util.*;

public class C{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int sum = m;
      
      int i = 0;
      
      for(int k = 0; k < n; k++){
         if(m >= 9){
            out.print(9);
            m-=9;
            
         } else if( m > 0){
            out.print(m);
            m=0;
         } else {
            out.print(0);
         }
      
      }
      
      out.println();
      
      out.close();
   }
   
      
}