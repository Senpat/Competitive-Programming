//make sure to make new file!
import java.io.*;
import java.util.*;

public class B21{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int a1 = Integer.parseInt(st.nextToken());
      int b1 = Integer.parseInt(st.nextToken());
      int c1 = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int a2 = Integer.parseInt(st.nextToken());
      int b2 = Integer.parseInt(st.nextToken());
      int c2 = Integer.parseInt(st.nextToken());
      
      if((a1 == 0 && b1 == 0 && c1 != 0) || (a2 == 0 && b2 == 0 && c2 != 0)){          //impossible
         out.println(0);
      } else if(a1*b2 == b1*a2 && a1*c2 == c1*a2 && b1*c2 == c1*b2){        //same line
         out.println(-1);
      } else if(a1*b2 == b1*a2){                   //parallel
         out.println(0);   
      } else {
         out.println(1);
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}