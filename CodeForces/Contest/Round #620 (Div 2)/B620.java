//make sure to make new file!
import java.io.*;
import java.util.*;

public class B620{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      String[] array = new String[n];
      
      for(int k = 0; k < n; k++){
         array[k] = f.readLine();
      }
      
      HashSet<String> hs = new HashSet<String>();
      HashMap<String,Integer> middles = new HashMap<String,Integer>();
      
      ArrayList<Pair> pairs = new ArrayList<Pair>();
      
      for(int k = 0; k < n; k++){
         String rev = new StringBuilder(array[k]).reverse().toString();
         
         if(rev.equals(array[k])){
            if(middles.containsKey(array[k])){
               middles.put(array[k],middles.get(array[k])+1);
            } else {
               middles.put(array[k],1);
            }
            
         } else if(hs.contains(rev)){
            pairs.add(new Pair(rev,array[k]));
            hs.remove(rev);
         } else {
            hs.add(array[k]);
         }
         
      }
      
      
      String middle = "";
      for(String s : middles.keySet()){
         if(middles.get(s)%2 == 1){
            middle = s;
         }
         for(int k = 0; k < middles.get(s)/2; k++){
            pairs.add(new Pair(s,s));
         }
      }
      
      
      Stack<String> back = new Stack<String>();
      
      StringJoiner sj = new StringJoiner("");
      for(Pair p : pairs){
         sj.add(p.a);
         back.add(p.b);
      }
      
      sj.add(middle);
      
      while(!back.isEmpty()){
         sj.add(back.pop());
      }
      
      out.println(sj.length());
      out.println(sj.toString());        
         
         
            
         
      
      
   
      
      
      
      
      
      out.close();
   }
   
   public static class Pair{
      String a;
      String b;
      public Pair(String c, String d){
         a = c;
         b = d;
      }
   }
   
   
   
}