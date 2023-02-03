//make sure to make new file!
import java.io.*;
import java.util.*;

public class C{
   
   public static int[] parent;
   public static int[] sizes;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int M = 500005;
      
      parent = new int[M];
      for(int k = 0; k < M; k++){
         parent[k] = k;
      }
      
      sizes = new int[M];
      Arrays.fill(sizes,1);
      
      int numused = 0;
      int answer = 0;
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int ni = Integer.parseInt(st.nextToken());
         
         ArrayList<Integer> alist = new ArrayList<Integer>();
         
         boolean noneused = true;
         int curnumused = 0;
         
         HashMap<Integer,Integer> parentfreqs = new HashMap<Integer,Integer>();
         
         for(int j = 0; j < ni; j++){
            int i = Integer.parseInt(st.nextToken());
            alist.add(i);
            
            int p = find(i);
            if(parentfreqs.containsKey(p)){
               parentfreqs.put(p,parentfreqs.get(p)+1);
            } else {
               parentfreqs.put(p,1);
            }
         }
         
         boolean fail = false;
         
         for(int p : parentfreqs.keySet()){
         
            if(parentfreqs.get(p) != sizes[find(p)]){
               fail = true;
               break;
            }
         }
         
         if(!fail){
            answer++;
            for(int j = 0; j < ni-1; j++){
               union(alist.get(j),alist.get(j+1));
            }
         }
        
      }
      
      out.println(answer);
      
      
      
      
      
      out.close();
   }
   
   
   public static void union(int u, int v){
      int findv = find(v);
      int findu = find(u);
      
      if(findv == findu) return;
      
      sizes[findv] += sizes[findu];
      parent[findu] = findv;
   }
   
   public static int find(int v){
      if(parent[v] == v) return v;
      else{
         parent[v] = find(parent[v]);
         return parent[v];
      }
   }
      
}