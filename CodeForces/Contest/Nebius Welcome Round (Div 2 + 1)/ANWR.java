//make sure to make new file!
import java.io.*;
import java.util.*;

public class ANWR{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Math.abs(Integer.parseInt(st.nextToken()));
         int b = Math.abs(Integer.parseInt(st.nextToken()));
         
         if(a > b){
            int temp = a;
            a = b;
            b = temp;
         }
         
         int answer = 2*a;
         if(b-a > 0){
            answer += 2*(b-a)-1;
         }
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}