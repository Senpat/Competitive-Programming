//make sure to make new file!
import java.io.*;
import java.util.*;

public class D85b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         StringJoiner sj = new StringJoiner(" ");
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long n = Long.parseLong(st.nextToken());
         long l = Long.parseLong(st.nextToken());
         long r = Long.parseLong(st.nextToken());
         
         if(l == (n*(n-1)) + 1){
            out.println(1);
            continue;
         }
         
         long seg = 1;
         long i = 1;
         long interval = (n-seg)*2;
         
         //find start
         while(i <= l){
            i+=interval;
            seg++;
            interval = (n-seg)*2;
         }
         
         seg--;
         interval = (n-seg)*2;
         i-=interval;
         
         while(l <= r && seg < n){
            
            for(long k = l-i; k < interval && l <= r; k++){
               if(k%2 == 0){
                  sj.add("" + seg);
               } else {
                  sj.add("" + ((k/2) + seg + 1));
               }
               l++;
            }
            
            seg++;
            i+=interval;
            interval = (n-seg)*2;
         }
         
         if(l == r) sj.add("" + 1);
         
         
         out.println(sj.toString());
               
         
      }
      
      
      
      
      out.close();
   }
   
      
}