//make sure to make new file!
import java.io.*;
import java.util.*;

public class A603{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         int z = Integer.parseInt(st.nextToken());
         
         int a = Math.min(x,Math.min(y,z));
         int c = Math.max(x,Math.max(y,z));
         int b = x+y+z-a-c;
         
         int answer = 0;
         int d1 = Math.min(a,c-b);
         a-=d1;
         c-=d1;
         answer += d1;
         
         if(a>0){
            answer += a;
            
            c-=Math.ceil(a/2.0);
            b-=Math.floor(a/2.0);
         }
         
         answer += Math.min(c,b);
         
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}