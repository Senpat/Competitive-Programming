//make sure to make new file!
import java.io.*;
import java.util.*;

public class BAF20{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int a = -1;
      for(int k = 2; k < n; k++){
         if(n%k == 0){
            a = k;
            break;
         }
      }
      
      out.print(a);
      out.println(n/a);
      
      

      
      
      
      
      
      out.close();
   }
   
      
}