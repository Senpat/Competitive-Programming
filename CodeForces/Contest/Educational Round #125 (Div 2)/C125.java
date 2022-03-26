//make sure to make new file!
import java.io.*;
import java.util.*;

public class C125{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         char[] array = f.readLine().toCharArray();
         
         int ops = 0;
         
         int i = 0;
         while(i < n-1){
            if(array[i] == ')' && array[i+1] == '('){
               int k = i+2;
               while(k < n && array[k] != ')'){
                  k++;
               }
               
               if(k < n){
                  i = k+1;
                  ops++;
               } else {
                  break;
               }
            } else {
               ops++;
               i+=2;
            }
         }
         
         out.println(ops + " " + (n-i));
      

      }
      
      
      
      
      out.close();
   }
   
      
}