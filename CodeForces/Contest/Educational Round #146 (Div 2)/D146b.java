//make sure to make new file!
import java.io.*;
import java.util.*;

public class D146b{
   
   public static int n;
   public static long m;
   public static Weapon[] weapons;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         n = Integer.parseInt(st.nextToken());
         m = Long.parseLong(st.nextToken());
         
         StringTokenizer stf = new StringTokenizer(f.readLine());
         StringTokenizer std = new StringTokenizer(f.readLine());
         
         weapons = new Weapon[n];
         HashMap<Long,Integer> powerfreq = new HashMap<Long,Integer>();
         HashSet<Long> fset = new HashSet<Long>();
         for(int k = 0; k < n; k++){
            long fi = Long.parseLong(stf.nextToken());
            long di = Long.parseLong(std.nextToken());
            weapons[k] = new Weapon(fi,di);
            
            if(powerfreq.containsKey(weapons[k].p)){
               powerfreq.put(weapons[k].p,powerfreq.get(weapons[k].p)+1);
            } else {
               powerfreq.put(weapons[k].p,1);
            }
            
            //if fi <= m+1, then all ranges of length m contain a multiple of fi
            if(fi > m+1) fset.add(fi);
         }
         
         
         int answer = n;
         
         for(int k = 0; k < n; k++){
            //fix that point
            long l = Math.max(1,weapons[k].p-m);
            long r = weapons[k].p;
            
            //precompute for l
            ArrayList<ArrayList<Long>> fcount = new ArrayList<ArrayList<Long>>((int)(2*m+1));
            for(int j = 0; j < 2*m+1; j++) fcount.add(new ArrayList<Long>());
            
            int curin = 0;
            for(long j = 0; j <= m; j++){
               if(powerfreq.containsKey(l+j)) curin += powerfreq.get(l+j);
            }
            
            int fcountrange = 0;
            for(long fi : fset){
               long ceil = (l + fi-1) / fi;
               int findex = (int)(ceil*fi - l);
               if(findex < fcount.size()){
                  fcount.get(findex).add(fi);
                  if(findex <= m) fcountrange++;
               }
               
            }
            
            //initial answer
            if(fcountrange == fset.size()){
               answer = Math.min(answer,n-curin);
            }
            
            for(long j = l+1; j <= r; j++){
               int index = (int)(j-l);
               //remove j-1 / index-1
               if(powerfreq.containsKey(j-1)) curin -= powerfreq.get(j-1);
               for(long fi : fcount.get(index-1)){
                  int newindex = (int)(index-1 + fi);
                  if(newindex < fcount.size()) fcount.get(newindex).add(fi);
                  
                  fcountrange--;
                  
               }
               
               //add j+m / index+m
               if(powerfreq.containsKey(j+m)) curin += powerfreq.get(j+m);
               fcountrange += fcount.get((int)(index+m)).size();
               
               if(fcountrange == fset.size()) answer = Math.min(answer,n-curin);
            }
            
         }
         
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
   public static class Weapon implements Comparable<Weapon>{
      long f;
      long d;
      long p;
      public Weapon(long a, long b){
         f = a;
         d = b;
         p = f*d;
      }
      
      //sort in decreasing order of f
      public int compareTo(Weapon w){
         if(f > w.f) return -1;
         if(f < w.f) return 1;
         return 0;
      }
   }
}