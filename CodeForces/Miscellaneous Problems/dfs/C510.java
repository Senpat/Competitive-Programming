//Fox and Names
//wrong
import java.io.*;
import java.util.*;

public class C510{

   public static StringBuilder sb;
   public static HashSet<Character> set;
   public static Letter[] letters;
   public static PrintWriter out;
   
   
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
         
         while(index < pre.length() && index < cur.length()){
            if(pre.charAt(index)!=cur.charAt(index)) break;
            index++;
         }
         
         if(index >= pre.length()){
            pre = cur;
            continue;
         } else if(index >= cur.length()){
            //System.out.println("index >= cur.length()");
            out.println("Impossible");
            out.close();
            System.exit(0);
         } else {
            letters[(int)pre.charAt(index)-97].alist.add(letters[(int)cur.charAt(index)-97]);
            letters[(int)cur.charAt(index)-97].isRoot = false;
            
            pre = cur;
         }
      }
      
      set = new HashSet<Character>();
      for(int k = 0; k < 26; k++) set.add((char)(k+97));
      
      int startind = -1;
      for(int k = 0; k < 26; k++){
         if(letters[k].alist.size()>0 && letters[k].isRoot){
            startind = k;
            break;
         }
      }
      
      if(startind == -1){
         //System.out.println("startind == -1");
         out.println("Impossible");
         out.close();
         System.exit(0);
      }
      
      sb = new StringBuilder("");
      for(int k = startind; k < 26+startind; k++){
         int i = k%26;
         if(set.contains((char)(i+97))){
            bfs(letters[i],new HashSet<Character>());
         }
      }
      
      for(char c : set){
         sb.append(c);
      }
      
      out.println(sb);  
      
      out.close();
   }
   
   public static void bfs(Letter let,HashSet<Character> cur){
      if(cur.contains(let.let)){
         //System.out.println("!set.contains(let.let)");
         out.println("Impossible");
         out.close();
         System.exit(0);
      } else {
         cur.add(let.let);
         if(set.contains(let.let)) sb.append(let.let);
         if(set.contains(let.let)) set.remove(let.let);
         for(Letter nei : let.alist){
            bfs(nei,cur);
         }
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