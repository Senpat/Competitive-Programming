//make sure to make new file!
import java.io.*;
import java.util.*;

public class A71{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         st = new StringTokenizer(f.readLine());
      
         int ac = Integer.parseInt(st.nextToken());
         int bc = Integer.parseInt(st.nextToken());
      
         if(ac < bc){
         //swap
            int tmp = a;
            a = b;
            b = tmp;
         
            tmp = ac;
            ac = bc;
            bc = tmp;
         }
         int answer = 0;
         int first = Math.min(a,n/2);
         answer += first*ac;
         n -= first*2;
      
         int second = Math.min(b,n/2);
         answer += second*bc;
      
         out.println(answer);
      
      }
      
      
      
      
      out.close();
   }
   
      
}