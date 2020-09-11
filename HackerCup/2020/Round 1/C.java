import java.io.*;
import java.util.*;

class C{

   public static char[] rolls;
   public static ArrayList<ArrayList<Integer>> adj;
   public static long size;
   public static int[] component;
   public static ArrayList<Long> compsizes;
   
   public static long[] subsum;
   public static long[] subsummax;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("C.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C.out")));
      
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         rolls = f.readLine().toCharArray();
         
         int[] edgestart = new int[n+1];
         
         st = new StringTokenizer(f.readLine());
         for(int k = 2; k <= m+1; k++){
            edgestart[k] = Integer.parseInt(st.nextToken());
         }
         
         st = new StringTokenizer(f.readLine());
      
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         long c = Long.parseLong(st.nextToken());
         
         for(int k = m+2; k <= n; k++){
            edgestart[k] = (int)(((long)edgestart[k-1]*a + (long)edgestart[k-2]*b + c)%((long)k)) + 1;
         }
         
         adj = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
         
         for(int k = 2; k <= n; k++){
            adj.get(k).add(edgestart[k]);
            adj.get(edgestart[k]).add(k);
         }
         
         component = new int[n+1];
         Arrays.fill(component,-1);
         compsizes = new ArrayList<Long>();
         
         long curanswer = 0L;
         for(int k = 1; k <= n; k++){
            if(component[k] != -1 || rolls[k-1] == '#') continue;
            size = 0L;
            dfs(k);
            compsizes.add(size);
            curanswer += c2(size);
            System.out.println(size);
         }
         
         long answer1 = 0L;
         long answer2 = 0L;
         
         long max1 = -1L;
         long max2 = -1L;
         long nummax1 = 0L;
         long nummax2 = 0L;
         
         for(int k = 0; k < compsizes.size(); k++){
            long i = compsizes.get(k);
            if(i > max1){
               max2 = max1;
               nummax2 = nummax1;
               max1 = i;
               nummax1 = 1L;
            } else if(i == max1){
               nummax1++;
            } else if(i > max2){
               max2 = i;
               nummax2 = 1L;
            } else if(i == max2){
               nummax2++;
            }
            
            
         }
         
         if(max1 == -1L){
            //no components, answer1 = 0L
            //get subtree counts
            subsum = new long[n+1];
            dfssub(1,-1);
            
            for(int k = 2; k <= n; k++){
               answer2 += subsum[k]*(subsum[1]-subsum[k]);
            }
         } else if(max2 == -1L && nummax1 == 1L){
            //one component, answer1 = curanswer
            answer1 = curanswer;
            
            subsummax = new long[n+1];
            dfssubmax(1,-1, max1);
            
            for(int k = 2; k <= n; k++){
               if(component[k] >= 0 && component[edgestart[k]] >= 0 && compsizes.get(component[k]) == max1 || compsizes.get(component[edgestart[k]]) == max1){
                  answer2 += subsummax[k]*(subsummax[1]-subsummax[k]);
               }
            }
         } else if(nummax1 > 1L){
            //multiple components of the biggest size
            answer1 = curanswer - 2L*c2(max1) + c2(2L*max1);
            
            subsummax = new long[n+1];
            dfssubmax(1,-1, max1);
            
            for(int k = 2; k <= n; k++){
               if(component[k] == -1 || component[edgestart[k]] == -1 || compsizes.get(component[k]) < max1 || compsizes.get(component[edgestart[k]]) < max1){
                  answer2 += subsummax[k]*(subsummax[1]-subsummax[k]);
               }
            }
            
         } else {
            //one component of max1 size, nummax2 components of size max2
            answer1 = curanswer - c2(max1) - c2(max2) + c2(max1+max2);
            
            answer2 = -1;
            
            
         }
            
         
         
         out.println("Case #" + q + ": " + answer1 + " " + answer2);
         
         
      }
      
        
        
      out.close();
   }
   
   public static void dfssubmax(int v, int p, long curmax){
      if(component[v] >= 0 && compsizes.get(component[v]) == curmax) subsummax[v] = 1L;
      else subsummax[v] = 0L;
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfssubmax(nei,v,curmax);
         subsummax[v] += subsummax[nei];
      }
   }
   
   public static void dfssub(int v, int p){
      subsum[v] = 1L;
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfssub(nei,v);
         subsum[v] += subsum[nei];
      }
   }
   
   public static long c2(long x){
      return x*(x-1)/2;
   }
   
   public static void dfs(int v){
      size++;
      component[v] = compsizes.size();
      for(int nei : adj.get(v)){
         if(component[nei] != -1 || rolls[nei-1] == '#') continue;
         dfs(nei);
      }
   }
      
}