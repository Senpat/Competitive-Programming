//make sure to make new file!
import java.io.*;
import java.util.*;

public class A610{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a1 = Integer.parseInt(st.nextToken());
         int b1 = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         
         int a = Math.min(a1,b1);
         int b = Math.max(a1,b1);
         
         int answer = b-a;
         
         if(c < b &&  c > a){
            if(c+r > b){
               answer -= (b-c);
            } else {
               answer -= r;
            }
            if(c-r < a){
               answer -= (c-a);
            } else {
               answer -= r;
            }
         }
         if( c >= b){
            answer -= Math.max(0,b-(c-r));
         }
         
         if( c <= a){
            answer -= (Math.max(0,c+r-a));
         }
        
         
         out.println(Math.max(0,answer));
      
      }
      
      
      out.close();
   }
   
      
}