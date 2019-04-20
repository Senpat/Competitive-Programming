//Foregone
import java.io.*;
import java.util.*;

public class Solution{
   
   public static void main(String[] args)throws IOException{
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
      
        int t = Integer.parseInt(f.readLine());
      
        //StringTokenizer st = new StringTokenizer(f.readLine());
        
        for(int q = 1; q <= t; q++){
            String s = f.readLine();
            int i1 = Integer.parseInt(s);
            int i2 = 0;
            for(int k = 0; k < s.length(); k++){
                if(s.charAt(k) == '4'){
                   i2 += (int)Math.pow(10,s.length()-k-1); 
                }
            }
            out.println("Case #" + q + ": " + (i1-i2)+ " " + i2);
          
        }
      
      
      
      
      out.close();
   }
   
      
}