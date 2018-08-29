//Round #486 (Div 3) B
//Substrings Sort
import java.io.*;
import java.util.*;
import java.math.*;

public class Subsort{
   
   public static void main(String[] args)throws IOException{
      Scanner sc = new Scanner(System.in);
      
      int n = Integer.parseInt(sc.nextLine());
      
      String[] array = new String[n];
      
      for(int k = 0; k < n; k++){
         array[k] = sc.nextLine();
      }
      PriorityQueue<Str> pq = new PriorityQueue<Str>(n,Collections.reverseOrder());
      
      for(int k = 0; k < n; k++){
         pq.add(new Str(array[k]));
      }
      //System.out.println(pq);
      String[] answer = new String[n];
      answer[0] = pq.poll().s;
      //System.out.println(answer[0]);
      for(int k = 1; k < n; k++){
         String cur = pq.poll().s;
         if(!issub(answer[k-1],cur)){
            System.out.println("NO");
            System.exit(0);
         }
         answer[k] = cur;
      }
      
      System.out.println("YES");
      for(String s : answer){
         System.out.println(s);
      }
   }
   
   public static boolean issub(String a, String b){//check is a is a substring of b
      if(a.length() > b.length()){
         return false;
      }
      
      for(int k = 0; k <= b.length()-a.length(); k++){
         if(b.substring(k,k+a.length()).equals(a)){
            return true;
         }
      }
      return false;
   }
   
   public static class Str implements Comparable<Str>{
      String s;
      
      public Str(String a){
         s = a;
      }
      
      public int compareTo(Str st){
         
         if(st.s.equals(s)) return 0;
         //System.out.println(s + " " + st.s + " " + issub(st.s,s)); 
         if(issub(st.s,s)) 
            return -1;
         
         return 1;
      }
      
      public String toString(){
         return s;
      }
   }
} 
