//Two Strings Swaps

import java.io.*;
import java.util.*;

public class D498{
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      int n = Integer.parseInt(f.readLine());
      String a = f.readLine();
      String b = f.readLine();
      
      int count = 0;
      for(int k = 0; k < n/2; k++){
         count+=dothing(k,a,b);
      }
      
      if(n%2==1 && a.charAt(n/2)!=b.charAt(n/2)) count++;
      
      System.out.println(count);
   }
   
   
   public static int dothing(int i, String a, String b){
      HashSet<Character> hs = new HashSet<Character>();
      HashSet<Character> hs2 = new HashSet<Character>();
      if(hs.contains(a.charAt(i))) hs2.add(a.charAt(i));
      else hs.add(a.charAt(i));
      if(hs.contains(a.charAt(a.length()-i-1))) hs2.add(a.charAt(a.length()-i-1));
      else hs.add(a.charAt(a.length()-i-1));
      if(hs.contains(b.charAt(i))) hs2.add(b.charAt(i));
      else hs.add(b.charAt(i));
      if(hs.contains(b.charAt(b.length()-i-1))) hs2.add(b.charAt(b.length()-i-1));
      else hs.add(b.charAt(b.length()-i-1));
      
      if(hs.size()==1) return 0;
      if(hs.size()==4) return 2;
      if(hs.size()==3){
         if(a.charAt(i) == a.charAt(a.length()-i-1)) return 2;
         else return 1;
      }
      if(hs.size()==2){
         if(hs2.size()==1) return 1;
         else return 0;
      }
      System.out.println("???");
      return 0;
   }
}
      