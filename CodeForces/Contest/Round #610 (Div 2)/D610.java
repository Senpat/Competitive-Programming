//make sure to make new file!
import java.io.*;
import java.util.*;

public class D610{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      for(int k = 0; k < 300; k++){
         out.print('a');
      }
      out.println();
      out.flush();
      
      int numa = Integer.parseInt(f.readLine());
      if(numa == 0) return;
      numa = 300-numa;
      
      for(int k = 0; k < 300; k++){
         out.print('b');
      }
      out.println();
      out.flush();
      
      int numb = Integer.parseInt(f.readLine());
      if(numb == 0) return;
      numb = 300-numb;
      
      int n = numa+numb;
      char[] array = new char[n];
      int value = numb;
      
      for(int k = 0; k < n; k++){
         array[k] = 'a';
      }
      
      if(value == 0){
         out.println(String.copyValueOf(array));
         out.close();
         return;
      }
      
      int ind = 0;
      
      
      
      
      
      while(value > 0){
         array[ind] = 'b';
      
         out.println(String.copyValueOf(array));
         out.flush();
         int newvalue = Integer.parseInt(f.readLine());

         if(newvalue == value+1){
            array[ind] = 'a';
         } else {
            value--;
         }
         
         ind++;
      }
            
      
      
      
      
      out.close();
   }
   
      
}