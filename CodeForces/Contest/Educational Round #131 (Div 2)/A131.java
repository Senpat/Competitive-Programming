//make sure to make new file!
import java.io.*;
import java.util.*;

public class A131{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a1 = Integer.parseInt(st.nextToken());
         int a2 = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
      
         int a3 = Integer.parseInt(st.nextToken());
         int a4 = Integer.parseInt(st.nextToken());
         
         if(a1+a2+a3+a4 == 4){
            out.println(2);
         } else if(a1+a2+a3+a4 == 0){
            out.println(0);
         } else {
            out.println(1);
         }
            

      }
      
      
      
      
      out.close();
   }
   
      
}