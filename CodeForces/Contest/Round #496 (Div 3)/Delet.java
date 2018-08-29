//Round 496 (Div 3) B
//Delete from Left

import java.util.*;
import java.io.*;

public class Delet{

   public static void main(String[] args) throws IOException{
      Scanner sc = new Scanner(System.in);
      
      String a = sc.nextLine();
      String b = sc.nextLine();
      
      int alen = a.length();
      int blen = b.length();
      
      int min = Math.min(alen,blen);
      
      for(int k = 0; k <= min; k++){
         if(!a.substring(alen-k).equals(b.substring(blen-k))){
            System.out.println(alen-k + blen-k+2);
            System.exit(0);
         }
      }
      if(alen<=blen && b.substring(blen-alen).equals(a)){
         System.out.println(blen-alen);
      } 
      else if(blen < alen && a.substring(alen-blen).equals(b)){
         System.out.println(alen-blen);
      } 
      else {
         System.out.println(alen+ blen);
      }
   }
}
