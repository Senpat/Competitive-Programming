//Round #490 (Div 3) B
//Reversing Encryption

import java.io.*;
import java.util.*;
import java.math.*;

public class Reverse{
   
   public static void main(String[] args)throws IOException{
      Scanner sc = new Scanner(System.in);
      
      int n = Integer.parseInt(sc.nextLine());
      
      String s = sc.nextLine();
      
      int cur = 2;
      while(cur<=n/2){
         if(n%cur==0){
            s = reverse(s.substring(0,cur))+s.substring(cur,s.length());
         }
         cur++;
      }
      
      s = reverse(s);
      
      System.out.println(s);
   }
   
   public static String reverse(String s){
      String r = "";
      for(int k = s.length()-1; k>=0; k--){
         r+=s.charAt(k);
      }
      return r;
   }
}