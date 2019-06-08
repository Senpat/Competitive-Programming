//make sure to make new file!
import java.io.*;
import java.util.*;

public class A66{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long n = Long.parseLong(st.nextToken());
         long k = Long.parseLong(st.nextToken());
         
         if(k==1){
            out.println(n);
         } else {
            long answer = 0L;
            
            while(n > 0){
               if(n%k == 0){
                  n/=k;
                  answer++;
               }
               else{
                  answer += n-n/3*3;
                  n=n/3*3;
               }
               
            }
            
            out.println(answer);
         }
      
      }
      
      
      
      
      out.close();
   }
   
      
}