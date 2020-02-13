//make sure to make new file!
import java.io.*;
import java.util.*;

public class ILoveSuchet1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String suchet = f.readLine();
      String iscool = f.readLine();
      
      int suchetisawesome = 0;
      for(int k = 0; k < n; k++){
         if(suchet.charAt(k) == iscool.charAt(k)){
            suchetisawesome++;
          }
      }
      
      out.println(suchetisawesome);
      

      
      
      
      
      
      out.close();
   }
   
      
}