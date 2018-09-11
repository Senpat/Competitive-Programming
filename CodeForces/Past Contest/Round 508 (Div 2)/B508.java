//Non-Coprime Partition
import java.io.*;
import java.util.*;

public class B508{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      if(n<=2){
         out.println("No");
      } else if (n==3){ 
         out.println("Yes\n1 2\n2 1 3");
      }else {
         out.println("Yes");
         if(n%2==0){
            out.print(n/2 + " ");
            for(int k = 1; k <= n; k+=2){
               out.print(k + " ");
            }
            out.print("\n" + (n/2) + " ");
            for(int k = 2; k <= n; k+=2){
               out.print(k + " ");
            }
         } else {
            out.println("1 " + n);
            out.print(n-1 + " ");
            for(int k = 1; k < n; k++){
               out.print(k + " ");
            }
         }
      }
      
      
      out.close();
   }
   
}