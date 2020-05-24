//make sure to make new file!
import java.io.*;
import java.util.*;

public class CM13{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         char[] a = st.nextToken().toCharArray();
         char[] b = st.nextToken().toCharArray();
         
         if(check(a,b)){
            out.println("YeS");
         } else {
            out.println("nO");
         }

      }
      
      
      
      
      out.close();
   }
   //switched b and a because i switched them in the function on accident
   public static boolean check(char[] b, char[] a){
      int an = a.length;
      int bn = b.length;
      
      int bp = 0;
      
      for(int k = 0; k < an; k++){
         while(bp < bn && b[bp] != a[k]){
            bp++;
         }
         if(bp == bn) return false;
         bp++;
      }
      
      return true;
   }
      
}