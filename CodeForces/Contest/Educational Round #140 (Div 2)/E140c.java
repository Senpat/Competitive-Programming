//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, solve minimum vertex cover using maximum clique of inverted graph
//based on E140b, test max clique template
public class E140c{

   public static int m;

   public static int[] costs;

   public static int[][] iadj;
   
   public static HashMap<Long,Integer> mem;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      st = new StringTokenizer(f.readLine());
      int sumcosts = 0;
      costs = new int[m+1];
      for(int k = 1; k <= m; k++){
         costs[k] = Integer.parseInt(st.nextToken());
         sumcosts += costs[k];
      }
      
      //inverse adjacency matrix
      iadj = new int[m+1][m+1];
      for(int k = 0; k <= m; k++) Arrays.fill(iadj[k],1);
      
      HashSet<Integer> doubles = new HashSet<Integer>();
      doubles.add(array[0]);
      doubles.add(array[n-1]);
      for(int k = 0; k < n-1; k++){
         if(array[k] == array[k+1]) doubles.add(array[k]);
         iadj[array[k]][array[k+1]] = 0;
         iadj[array[k+1]][array[k]] = 0;
      }
      
      for(int k = 0; k <= m; k++) iadj[k][k] = 0;
      
      long mask = 0L;
      long i = 1L;
      for(int k = 0; k < m; k++){
         mask += i;
         i <<= 1;
      }
      
      int answer = 0;
      //some colors must be taken (if they appear twice in a row), and the colors on the first and last platform
      for(int d : doubles){
         for(int k = 1; k <= m; k++){
            iadj[d][k] = 0;
            iadj[k][d] = 0;
         }
         sumcosts -= costs[d];
         answer += costs[d];
         mask -= (1L << (d-1));
      }
      
      mem = new HashMap<Long,Integer>();
      
      answer += sumcosts - maxclique(mask);
      
      out.println(answer);
      
      
      out.close();
   }
   public static int maxclique(long mask){
      if(mask == 0L) 
         return 0;
      
      if(mem.containsKey(mask)) 
         return mem.get(mask);
      
      int i = m;
      
      while((mask & (1L << i)) == 0){
         i--;
      }
      
      int a1 = maxclique(mask ^ (1L << i));
      i++;
      long mask2 = 0;
      for(int nei = 1; nei <= m; nei++){
         if(iadj[i][nei] == 1){
            mask2 += (1L << nei);
         }
      }
      
      int a2 = costs[i]+maxclique(mask & mask2);
      
      mem.put(mask,Math.max(a1,a2));
      return Math.max(a1,a2);
   }
   
      
}