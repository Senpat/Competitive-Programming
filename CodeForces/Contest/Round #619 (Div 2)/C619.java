//make sure to make new file!
import java.io.*;
import java.util.*;

public class C619{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long n = Long.parseLong(st.nextToken());
         long m = Long.parseLong(st.nextToken());
         
         long answer = c2(n+1);
         
         if(m>n/2){
            answer-=(n-m);
         } else if(m==0){
            answer = 0; 
         } else {
            //answer-=c2((n-m)%(m+1)+1);
            answer-=(n-m)%(m+1)*((n-m)/(m+1)+1);
            answer-=(m+1)*c2((n-m)/(m+1)+1);
            
         
         }
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
   public static long c2(long i){
      return (i*(i-1))/2;
   }
      
}