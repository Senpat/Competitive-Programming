//Fox and Names
//implement topological sort
//doesnt work for test 12
import java.io.*;
import java.util.*;

public class C510b{

   public static StringBuilder sb;
   public static HashSet<Character> set;
   public static Letter[] letters;
   public static PrintWriter out;
   public static Stack stk;
   public static HashSet<Character> seen;
   public static boolean printm = false;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String[] array = new String[n];
      
      for(int k = 0; k < n; k++) array[k] = f.readLine();
      
      letters = new Letter[26];
      
      for(int k = 0; k < 26; k++) letters[k] = new Letter(new HashSet<Letter>(),(char)(k+97));
      
      String pre = array[0];
      for(int k = 1; k < n; k++){
         int index = 0;
         String cur = array[k];
         //if(cur.equals("xgpuamkxkszhkbpphykinkezplvfjaqmopodotkrjzrimlvumuarenexcfycebeurgvjyospdhv")) printm = true;
         
         while(index < pre.length() && index < cur.length()){
            if(pre.charAt(index)!=cur.charAt(index)) break;
            index++;
         }
         
         if(index >= pre.length()){
            pre = cur;
            continue;
         } else if(index >= cur.length()){
            if(printm) System.out.println("index >= cur.length()");
            out.println("Impossible");
            out.close();
            System.exit(0);
         } else {
            letters[(int)pre.charAt(index)-97].alist.add(letters[(int)cur.charAt(index)-97]);
            
            pre = cur;
         }
      }
      
      set = new HashSet<Character>();
      for(int k = 0; k < 26; k++) set.add((char)(k+97));
      
      stk = new Stack<Character>();
      seen = new HashSet<Character>();
      for(int k = 0; k < 26; k++){
         char cur = (char)(k+97);
         if(!seen.contains(cur) && letters[k].alist.size()>0){
            tsort(letters[k],new HashSet<Character>());
         }
      }
      
      sb = new StringBuilder("");
      while(!stk.empty()){
         char c = (char)stk.pop();
         sb.append(c);
         set.remove(c);
      }
      
      for(char c : set){
         sb.append(c);
      }
      
      out.println(sb);  
      
      out.close();
   }
   
   public static void tsort(Letter let,HashSet<Character> curset){
      if(curset.contains(let.let)){
         //if(printm) System.out.println("curset.contains(let.let)");
         out.println("Impossible");
         out.close();
         System.exit(0);
      } else {
         if(seen.contains(let.let)) return;
         curset.add(let.let);
         seen.add(let.let);
         for(Letter nei : let.alist){
            tsort(nei,curset);
         }
         stk.push(let.let);
                     
      }
   }
   
   static class Letter{
      HashSet<Letter> alist;
      char let;
      boolean isRoot = true;
      
      public Letter(HashSet<Letter> a, char c){
         alist = a;
         let = c;
      }
   }
      
   
}