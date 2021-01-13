//make sure to make new file!
import java.io.*;
import java.util.*;

public class farmingmars{
   
   public static int BUCKET;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n+1];
      
      TreeSet<Value> tset = new TreeSet<Value>();
      HashSet<Integer> hset = new HashSet<Integer>();
      ArrayList<Integer> compresslist = new ArrayList<Integer>();
      for(int k = 1; k <= n; k++){
         array[k] = (int)(Double.parseDouble(f.readLine())*10000000.0);
         tset.add(new Value(array[k],0));
         if(!hset.contains(array[k])){
            compresslist.add(array[k]);
            hset.add(array[k]);
         }
      }
      
      HashMap<Integer,Integer> compressmap = new HashMap<Integer,Integer>();
      for(int k = 0; k < compresslist.size(); k++){
         compressmap.put(compresslist.get(k),k);
      }
      
      int[] compress = new int[n+1];
      for(int k = 1; k <= n; k++){
         compress[k] = compressmap.get(array[k]);
      }
      
      BUCKET = 100;
      
      boolean[] answer = new boolean[m];
      Query[] queries = new Query[m];
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         queries[k] = new Query(l,r,k);
      }
      
      Arrays.sort(queries);
      
      int[] freqs = new int[hset.size()];
      
      int l = queries[0].l;
      int r = queries[0].r;
      
      int[] freqfreq = new int[n+1];
      freqfreq[0] = hset.size();
      
      int max = 0;
      for(int k = l; k <= r; k++){
         int freq = freqs[compress[k]];
         freqfreq[freq]--;
         freqfreq[freq+1]++;
         if(max == freq) max++;
         freqs[compress[k]]++;
      }
      
      for(int k = 0; k < m; k++){
         while(l > queries[k].l){
            //add
            l--;
            int freq = freqs[compress[l]];
            freqfreq[freq]--;
            freqfreq[freq+1]++;
            if(max == freq) max++;
            freqs[compress[l]]++;
            
         }
         
         while(r < queries[k].r){
            //add
            r++;
            int freq = freqs[compress[r]];
            freqfreq[freq]--;
            freqfreq[freq+1]++;
            if(max == freq) max++;
            freqs[compress[r]]++;
            
         }
         
         while(l < queries[k].l){
            //subtract
            int freq = freqs[compress[l]];
            freqfreq[freq]--;
            freqfreq[freq-1]++;
            if(max == freq && freqfreq[freq] == 0) max--;
            freqs[compress[l]]--;
            l++;
         }
         
         
      
         while(r > queries[k].r){
            //subtract
            int freq = freqs[compress[r]];
            freqfreq[freq]--;
            freqfreq[freq-1]++;
            if(max == freq && freqfreq[freq] == 0) max--;
            freqs[compress[r]]--;
            r--;
         }
         
         //out.println(tset.last().v + " " + tset.last().f);
         answer[queries[k].i] = max > ((queries[k].r-queries[k].l+1)/2);
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < m; k++){
         if(answer[k]) sj.add("usable");
         else sj.add("unusable");
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Value implements Comparable<Value>{
      int v;
      int f;
      
      public Value(int a, int b){
         v = a;
         f = b;
      }
      
      public int compareTo(Value val){
         if(f == val.f) 
            return v-val.v;
         return f-val.f;
      }
   }
   
   public static class Query implements Comparable<Query>{
      int l;
      int r;
      int i;
      public Query(int a, int b, int c){
         l = a;
         r = b;
         i = c;
      }
      public int compareTo(Query q){
         if(l/BUCKET == q.l/BUCKET){
            return r-q.r;
         }
         return l/BUCKET - q.l/BUCKET;
      }
   }
   
      
}