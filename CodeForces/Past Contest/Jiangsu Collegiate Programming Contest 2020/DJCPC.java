//make sure to make new file!
import java.io.*;
import java.util.*;

public class DJCPC{
   
   public static boolean[] primes;
   public static int[] pbefore;                    //stores number of primes before it (inclusive)
   
   public static ArrayList<ArrayList<Integer>> plevels;
   
   public static int[] levelof;
   public static int[] indexof;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      //sieve
      int N = 1000005;
      primes = new boolean[N+1];
      Arrays.fill(primes,true);
      
      for(int k = 2; k*k <= N; k++){
         if(!primes[k]) continue;
         
         for(int j = k*k; j <= N; j+=k) primes[j] = false;
      }
      
      primes[1] = true;
      
      pbefore = new int[N+1];
      pbefore[1] = 1;
      for(int k = 2; k <= N; k++){
         pbefore[k] = pbefore[k-1];
         if(primes[k]) pbefore[k]++;
      }
      
      plevels = new ArrayList<ArrayList<Integer>>();
      
      int level = 0;
      ArrayList<Integer> curlist = new ArrayList<Integer>();
      for(int k = 1; k <= N; k++) curlist.add(k);
      
      levelof = new int[N+1];
      indexof = new int[N+1];
      
      while(curlist.size() > 0){
         plevels.add(new ArrayList<Integer>());
         ArrayList<Integer> newlist = new ArrayList<Integer>();
         
         for(int k = 0; k < curlist.size(); k++){
            if(primes[k+1]){
               plevels.get(plevels.size()-1).add(curlist.get(k));
               
               levelof[curlist.get(k)] = level;
               indexof[curlist.get(k)] = plevels.get(plevels.size()-1).size()-1;
            } else {
               newlist.add(curlist.get(k));
            }
               
         }
         
         curlist = newlist;
         level++;
      }
      
      //out.println(level);
      
      int tt = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= tt; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int t = Integer.parseInt(st.nextToken());
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         if(t == 1){
            out.println(q1(n,m));
         } else if(t == 2){
            out.println(q2(n,m,0));
         }

      }
      
      
      
      
      out.close();
   }
   
   public static int q1(int n, int m){
      if(primes[m]) return indexof[m]+1;
      return q1(n-pbefore[n],m-pbefore[m])+pbefore[n];
   }
   
   public static int q2(int n, int m, int level){
      if(m <= pbefore[n]) return plevels.get(level).get(m-1);
      return q2(n-pbefore[n],m-pbefore[n],level+1);
   }
   
}