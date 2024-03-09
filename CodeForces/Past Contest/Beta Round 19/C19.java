//make sure to make new file!
import java.io.*;
import java.util.*;

public class C19{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      HashMap<Integer,ArrayList<Integer>> hmap = new HashMap<Integer,ArrayList<Integer>>();
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         
         if(hmap.containsKey(array[k])){
            hmap.get(array[k]).add(k);
         } else {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(k);
            hmap.put(array[k],temp);
         }
      }
      
      ArrayList<Start> starts = new ArrayList<Start>();
      
      ArrayList<ArrayList<Integer>> startstarts = new ArrayList<ArrayList<Integer>>(n+1);
      ArrayList<HashMap<Integer,Integer>> startindexof = new ArrayList<HashMap<Integer,Integer>>(n+1);
      for(int k = 0; k <= n; k++){
         startstarts.add(new ArrayList<Integer>());
         startindexof.add(new HashMap<Integer,Integer>());
      }
      
      for(int i : hmap.keySet()){
         for(int k = 0; k < hmap.get(i).size(); k++){
            for(int j = k+1; j < hmap.get(i).size(); j++){
               int a = hmap.get(i).get(k);
               int b = hmap.get(i).get(j);
               starts.add(new Start(a,b));
               
               startstarts.get(b-a).add(a);
            }
         }
      }
      
      for(int k = 0; k <= n; k++){
         Collections.sort(startstarts.get(k));
         for(int j = 0; j < startstarts.get(k).size(); j++){
            startindexof.get(k).put(startstarts.get(k).get(j),j);
         }
      }
      
      Collections.sort(starts);
      
      int i = 0;
      
      for(Start s : starts){
         if(s.a >= i && startindexof.get(s.len).containsKey(s.b-1) && startindexof.get(s.len).get(s.b-1) - startindexof.get(s.len).get(s.a) + 1 == s.len){
            i = s.b;
         }
      }
      
      out.println(n-i);
      StringJoiner sj = new StringJoiner(" ");
      for(int k = i; k < n; k++){
         sj.add("" + array[k]);
      }
      out.println(sj.toString());
      
      
      out.close();
   }
   
   public static class Start implements Comparable<Start>{
      int a;
      int b;
      int len;
      public Start(int c, int d){
         a = c;
         b = d;
         len = b-a;
      }
      //sort by length and then by starting point
      public int compareTo(Start s){
         if(len == s.len) return a-s.a;
         return len-s.len;
      }
   }
   
      
}