/*
TASK: heritage
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class heritage{

   public static char[] a,b;
   
   public static int[] indexofb;
   
   public static ArrayList<Character> answer;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("heritage.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("heritage.out")));
      
      a = f.readLine().toCharArray();
      b = f.readLine().toCharArray();
      
      int n = a.length;
      
      indexofb = new int[26];
      
      for(int k = 0; k < n; k++){
         indexofb[b[k]-'A'] = k;
      }
      
      answer = new ArrayList<Character>();
      
      dothing(0,n-1);
      
      for(char c : answer){
         out.print(c);
      }
      out.println();
      
        
        
      out.close();
   }
   
   public static void dothing(int l, int r){
      if(l > r){
         return;
      } else if(l == r){
         answer.add(a[l]);
      } else {
         //get min indexof
         int min = l;
         for(int k = l+1; k <= r; k++){
            if(indexofb[a[min]-'A'] > indexofb[a[k]-'A']){
               min = k;
            }
         }
         
         dothing(l,min-1);
         dothing(min+1,r);
         
         answer.add(a[min]);
      }
   }
      
}