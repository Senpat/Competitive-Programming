//make sure to make new file!
import java.io.*;
import java.util.*;

public class A562{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken())-1;
      int x = Integer.parseInt(st.nextToken())-1;
      int b = Integer.parseInt(st.nextToken())-1;
      int y = Integer.parseInt(st.nextToken())-1;
      
      
      int d = a;
      int v = b;
      
      while(true){
         if(d==v){
            out.println("YES");
            out.close();
            System.exit(0);
         }
         
         if(d==x) break;
         if(v==y) break;
         
         d = (d+1)%n;
         v = v-1;
         if(v < 0) v+=n;
         
      }
      
      out.println("NO");

      
      
      
      
      out.close();
   }
   
      
}