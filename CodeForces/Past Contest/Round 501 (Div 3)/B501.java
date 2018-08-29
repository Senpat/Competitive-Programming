//Obtaining the String
import java.io.*;
import java.util.*;

public class B501{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      int n = Integer.parseInt(f.readLine());
      String s = f.readLine();
      String t = f.readLine();
      
      int[] arrays = new int[26];
      int[] arrayt = new int[26];
      
      for(int k = 0; k < n; k++){
         arrays[s.charAt(k)-97]++;
         arrayt[t.charAt(k)-97]++;
      }
      
      if(!Arrays.equals(arrays,arrayt)){
         System.out.println(-1);
         System.exit(0);
      }
      
      int count = 0;
      StringBuilder ans = new StringBuilder("");
      while(s.compareTo(t)!=0){
         int index = 0;
         while(s.charAt(index)==t.charAt(index)){
            index++;
         }
         
         int curind = s.indexOf(t.charAt(index),index);
         
         for(int k = curind-1; k >= index; k--){
            s = swap(s,k);
            count++;
            ans.append(k+1 + " ");
         }
      }
      
      System.out.println(count);
      System.out.println(ans);
         
      
      
      
      
   }

   public static String swap(String s, int a){
      return s.substring(0,a) + s.charAt(a+1) + s.charAt(a) + s.substring(a+2);
   }

}