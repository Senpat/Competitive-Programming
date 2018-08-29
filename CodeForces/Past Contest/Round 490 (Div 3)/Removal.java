//Round #490 (Div 3) C
//Alphabetic Removal

import java.io.*;
import java.util.*;
import java.math.*;

public class Removal{
   
   public static void main(String[] args)throws IOException{
      Scanner sc = new Scanner(System.in);
      
      StringTokenizer st = new StringTokenizer(sc.nextLine());
      
      int n = Integer.parseInt(st.nextToken());
      int i = Integer.parseInt(st.nextToken());
      
      char[] array = sc.nextLine().toCharArray();
      
      int count = 0;
      
      for(char c = 'a'; c<='z'; c++){
         for(int k = 0; k < array.length; k++){
            if(array[k] == c){
               array[k] = 'L';
               count++;
               if(count>=i){
                  c='z'+1;
                  break;
               }
            }
            
         }
      }
      
      StringBuilder sb = new StringBuilder("");
      
      for(int k = 0; k < array.length; k++){
         if(array[k]!='L'){
            sb.append(array[k]);
         }
      }
      System.out.println(sb);
      
      
      
   }
}