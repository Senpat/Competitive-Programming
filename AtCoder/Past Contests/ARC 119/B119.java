//make sure to make new file!
import java.io.*;
import java.util.*;

public class B119{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      char[] a = f.readLine().toCharArray();
      char[] b = f.readLine().toCharArray();
      
      int numa0 = 0;
      int numb0 = 0;
      
      int difs = 0;
      
      for(int k = 0; k < n; k++){
         if(a[k] == '0') numa0++;
         if(b[k] == '0') numb0++;
         
         if((a[k] == '0' && b[k] == '1') || (a[k] == '0' && b[k] == '0' && numa0 != numb0)) difs++;
      }
      
      if(numa0 != numb0) out.println(-1);
      else out.println(difs);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}