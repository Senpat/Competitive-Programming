//make sure to make new file!
import java.io.*;
import java.util.*;

public class HackTest{
   
   public static void main(String[] args)throws IOException{
      PrintWriter out = new PrintWriter(System.out);
      
      int n = 200000;
      
      out.println("200000 1000000000");
      for(int k = n; k >= 1; k--){
         out.print(k + " ");
      }
      
      
      
      out.close();
   }
   
}