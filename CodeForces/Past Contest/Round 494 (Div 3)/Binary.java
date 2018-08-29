//Round #494 (Div 3) B
//Binary String Constructing

import java.io.*;
import java.util.*;

public class Binary{
   
   public static void main(String[] args)throws IOException{
      Scanner sc = new Scanner(System.in);
      
      StringTokenizer st = new StringTokenizer(sc.nextLine());
      int zero = Integer.parseInt(st.nextToken());
      int one = Integer.parseInt(st.nextToken());
      int finallen = zero+one;
      
      int dif = Integer.parseInt(st.nextToken());
      
      StringBuilder sb = new StringBuilder("");
      
      char pastChar;
      if(one>zero){
         sb.append('1');
         pastChar = '1';
         one--;
      } else {
         sb.append('0');
         pastChar = '0';
         zero--;
      }
         
      while(sb.length() < finallen){
         if(dif == 0){
            int cur;
            if(pastChar == '0') cur = zero;
            else cur = one;
            for(int k = 0; k < cur; k++) sb.append(pastChar);
         } else if(dif == 1){
            int cur;
            int next;
            char nowChar;
            
            if(pastChar == '0'){
               cur = zero;
               next = one;
               nowChar = '1';
            } else {
               cur = one;
               next = zero;
               nowChar = '0';
            }
            
            for(int k = 0; k < cur; k++) sb.append(pastChar);
            for(int k = 0; k < next; k++) sb.append(nowChar);
         } else {
            if(pastChar == '0'){
               sb.append('1');
               pastChar = '1';
               one--;
            } else {
               sb.append('0');
               pastChar = '0';
               zero--;
            }
            dif--;
         }
      }
      
      System.out.println(sb);

   }
}