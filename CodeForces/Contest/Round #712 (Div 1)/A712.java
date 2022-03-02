//make sure to make new file!
import java.io.*;
import java.util.*;

public class A712{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         char[] c = f.readLine().toCharArray();
         int[] array = new int[n];
         int num1 = 0;
         for(int k = 0; k < n; k++){
            array[k] = Character.getNumericValue(c[k]);
            if(array[k] == 1) num1++;
         }
         
         if(num1%2 == 1 || array[0] == 0 || array[n-1] == 0){
            out.println("NO");
            continue;
         }
         
         char[] a = new char[n];
         char[] b = new char[n];
         
         int num1seen = 0;
         int num0seen = 0;
         for(int k = 0; k < n; k++){
            if(array[k] == 1){
               if(num1seen < num1/2){
                  a[k] = '(';
                  b[k] = '(';
               } else {
                  a[k] = ')';
                  b[k] = ')';
               }
               num1seen++;
            } else {
               if(num0seen%2 == 0){
                  a[k] = '(';
                  b[k] = ')';
               } else {
                  a[k] = ')';
                  b[k] = '(';
               }
               num0seen++;
            }
         }
         
         out.println("YES");
         StringJoiner sja = new StringJoiner("");
         StringJoiner sjb = new StringJoiner("");
         for(int k = 0; k < n; k++){
            sja.add(""+a[k]);
            sjb.add(""+b[k]);
         }
         
         out.println(sja.toString());
         out.println(sjb.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}