//make sure to make new file!
import java.io.*;
import java.util.*;

public class B142{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a1 = Integer.parseInt(st.nextToken());
         int a2 = Integer.parseInt(st.nextToken());
         int a3 = Integer.parseInt(st.nextToken());
         int a4 = Integer.parseInt(st.nextToken());
         
         if(a1 == 0){
            out.println(Math.min(1,a1+a2+a3+a4));
            continue;
         }
         
         if(a2 > a3){
            int tmp = a3;
            a3 = a2;
            a2 = tmp;
         }
         
         int answer = 0;
         int mood1 = 0;
         int mood2 = 0;
         //do a1
         answer += a1;
         mood1 += a1;
         mood2 += a1;
         
         //do a2 and a3
         if(a3-a2 <= a1){
            //use all, have mood left over
            answer += a2+a3;
            mood1 = a1-(a3-a2);
         } else {
            //use all
            answer += a2*2+a1;
            mood1 = 0;
         }
         
         //last one
         answer = Math.min(answer+mood1+1,a1+a2+a3+a4);
         
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
      
}