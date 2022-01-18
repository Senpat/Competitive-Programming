//make sure to make new file!
import java.io.*;
import java.util.*;

public class Ib{

   public static BufferedReader f;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());

         queries = new char[2*n+1][2*n+1];
      
         HashSet<Integer> top = new HashSet<Integer>();
         HashSet<Integer> bot = new HashSet<Integer>();
         
         int[] gettop = new int[2*n+1];
         Arrays.fill(gettop,-1);
         
         for(int k = 1; k <= n; k++){
            int a = k*2-1;
            int b = k*2;
            
            if(query(a,b) == '<'){
               top.add(b);
               bot.add(a);
               gettop[a] = b;
            } else {
               top.add(a);
               bot.add(b);
               gettop[b] = a;
            }
         }
         
         //get order of bottoms (from worse to best)
         int[] bots = new int[n];
         
         for(int i : bot){
            int numbetter = 0;
            for(int j : bot){
               if(i == j) continue;
               if(query(i,j) == '>') numbetter++;
            }
            bots[numbetter] = i;
         }
         
         //eliminate tops based on location of floor(n/2)th bot
         ArrayList<Integer> lowtops = new ArrayList<Integer>();
         for(int i : top){
            if(query(i,bots[(n+1)/2-1]) == '<'){
               lowtops.add(i);
            }
         }
         for(int i : lowtops) top.remove(i);
         
         //tops where the bottom have been eliminated
         HashSet<Integer> middle = new HashSet<Integer>();
         for(int k = 0; k < (n+1)/2; k++){
            if(top.contains(gettop[bots[k]])){
               top.remove(gettop[bots[k]]);
               middle.add(gettop[bots[k]]);
            }
         }
         
         //repeat until all but n players eliminated: get worst (compare worst in middle with worst remaining bot)
         int boti = (n+1)/2;
         
         int worstmiddle = -1;
         
         while(top.size() + middle.size() + (n-boti) > n){
            if(worstmiddle == -1){
               //get worst middle
               for(int i : middle){
                  if(worstmiddle == -1){
                     worstmiddle = i;
                  } else if(query(i,worstmiddle) == '<'){
                     worstmiddle = i;
                  }
               }
            }
            
            //compare worst middle with worst remaining bot
            if(query(worstmiddle,bots[boti]) == '<'){
               //worst middle is worse
               middle.remove(worstmiddle);
               worstmiddle = -1;
            } else {
               top.remove(gettop[bots[boti]]);
               middle.add(gettop[bots[boti]]);
               boti++;
            }
                  
         }
         
         out.println("!");
         out.flush();
         
      }
      
      
      
      
      out.close();
   }
   
   public static int next(int i){
      if(i % 2 == 1) return i+1;
      else return i-1;
   }
   
   public static char[][] queries;
   
   public static char query(int a, int b) throws IOException{
      if(queries[a][b] == '>' || queries[a][b] == '<') return queries[a][b];
      out.println("? " + a + " " + b);
      out.flush();
      
      String s = f.readLine();
      queries[a][b] = s.charAt(0);
      queries[b][a] = s.charAt(0) == '>' ? '<' : '>';
      return s.charAt(0);
   }
   
      
}