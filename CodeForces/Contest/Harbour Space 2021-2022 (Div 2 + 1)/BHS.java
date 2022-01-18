//make sure to make new file!
import java.io.*;
import java.util.*;

public class BHS{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] a = f.readLine().toCharArray();
         char[] b = f.readLine().toCharArray();
         
         int na = a.length;
         int nb = b.length;
         
         boolean[][][] dp = new boolean[nb][na][2];            //where on b string, where on a string, if you've moved left
         
         for(int k = 0; k < na; k++){
            if(a[k] == b[0]) dp[0][k][0] = true;
         }
      
         for(int k = 0; k < nb-1; k++){
            for(int j = 0; j < na; j++){
               for(int h = 0; h < 2; h++){
                  if(!dp[k][j][h]) continue;
                  
                  if(h == 0){
                     //move right
                     if(j < na-1 && b[k+1] == a[j+1]) dp[k+1][j+1][0] = true;
                  }
                  
                  //move left
                  if(j > 0 && b[k+1] == a[j-1]) dp[k+1][j-1][1] = true;
               }
            }
         }
         
         boolean found = false;
         for(int k = 0; k < na; k++){
            if(dp[nb-1][k][0] || dp[nb-1][k][1]){
               found = true;
               break;
            }
         }
         
         if(found){
            out.println("YES");
         } else {
            out.println("NO");
         }
      }
      
      
      
      
      
      out.close();
   }
   
      
}