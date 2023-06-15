//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1622{
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] s = f.readLine().toCharArray();
      int n = s.length;
      
      ArrayList<ArrayList<Integer>> perms = genperms(n);
      
      ArrayList<String> answer = new ArrayList<String>();
      HashSet<String> hset = new HashSet<String>();
      
      for(ArrayList<Integer> perm : perms){
         char[] cur = new char[n];
         for(int k = 0; k < n; k++){
            cur[k] = s[perm.get(k)];
         }
         
         String curs = new String(cur);
         
         if(!hset.contains(curs)){
            answer.add(curs);
            hset.add(curs);
         }
      }
      
      Collections.sort(answer);
      StringJoiner sj = new StringJoiner("\n");
      sj.add(""+answer.size());
      for(String ans : answer){
         sj.add(ans);
      }
      out.println(sj.toString());
      
      
      
      
      out.close();
   }
   
   public static ArrayList<ArrayList<Integer>> genperms(int x){
      if(x == 1){
         ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
         ArrayList<Integer> add = new ArrayList<>();
         add.add(0);
         ret.add(add);
         return ret;
      }
      ArrayList<ArrayList<Integer>> perms = genperms(x-1);
      ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
      
      for(ArrayList<Integer> perm : perms){
         for(int k = 0; k < perm.size()+1; k++){
            ArrayList<Integer> newperm = new ArrayList<>();
            for(int j = 0; j < k; j++){
               newperm.add(perm.get(j));
            }
            newperm.add(x-1);
            for(int j = k; j < perm.size(); j++){
               newperm.add(perm.get(j));
            }
            ret.add(newperm);
         }
      }
      
      return ret;
   }
   
      
}