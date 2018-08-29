//The Phone Number
//bad
import java.io.*;
import java.util.*;

public class C502{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int cur = n-1;
      if(n<=4){
         for(int k = 0; k < n/2; k++){
            out.print(cur+" ");
            out.print(cur+1+" ");
            cur-=2;
         }
         if(n%2==1) out.print(1);
      } 
      else {
         if(n%2==1){
            for(int k = 0; k < n/2-1; k++){
               out.print(cur+" ");
               out.print(cur+1+" ");
               cur-=2;
            }
            out.print("1 2 3");
         } 
         else {
            for(int k = 0; k < n/2-2; k++){
               out.print(cur+" ");
               out.print(cur+1+" ");
               cur-=2;
            }
            out.print("4 1 2 3");
         }
      }
      
      out.close();
   }
   
}