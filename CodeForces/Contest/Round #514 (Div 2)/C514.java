//make sure to make new file!
import java.io.*;
import java.util.*;

public class C514{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      if(n==1) out.print(1);
      else if(n==2) out.print("1 2");
      else if(n==3) out.print("1 1 3");
      else {
      
         int odds = (n+1)/2;
      
         for(int k = 0; k < odds; k++){
            out.print("1 ");
         }
      
         for(int k = 0; k < n-odds-1; k++){
            out.print("2 ");
         }
      
         if(n%2==0){
            out.print(n);
         } else {
            out.print(n-1);
         }
      }
      
      
      out.close();
   }
   
}