//Two Buttons
import java.io.*;
import java.util.*;

public class B520{

   public static int answer = Integer.MAX_VALUE;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int answer = 0;
      
      while(m!=n){
         if(m<n){
            m++;
         } else {
            if(m%2==0) m/=2;
            else m++;
         }
         answer++;
      }
      
      out.println(answer);
      
      
      
      out.close();
   }
      
      
      
         
   
}