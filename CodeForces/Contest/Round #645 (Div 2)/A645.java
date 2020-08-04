//make sure to make new file!
import java.io.*;
import java.util.*;

public class A645{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         int answer = -1;
         if(a%2 == 0){
            answer = a/2 * b;
         } else if(b%2 == 0){
            answer = b/2 * a;
         } else {
            answer = (a-1)/2 * (b-1) + b/2 + a/2 + 1;
         }
         
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}