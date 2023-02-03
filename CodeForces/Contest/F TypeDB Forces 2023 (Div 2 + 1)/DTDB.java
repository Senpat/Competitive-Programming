//make sure to make new file!
import java.io.*;
import java.util.*;

public class DTDB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n+1];
         for(int k = 1; k <= n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         //get path starting at 1
         boolean[] seen = new boolean[n+1];
         ArrayList<Integer> path1 = new ArrayList<Integer>();
         
         int i = 1;
         while(1 <= i && i <= n && !seen[i]){
            seen[i] = true;
            path1.add(i);
            i += array[i];
         }
         
         boolean success = !(1 <= i && i <= n);
         
         
         int[] path1index = new int[n+1];
         Arrays.fill(path1index,-1);
         int[] path1freq = new int[path1.size()];
         int totalfreq = 0;
         if(success){
            for(int k = 0; k < path1.size(); k++){
               path1index[path1.get(k)] = k;
            }
         }
         
         //for all other nodes, see if they eventually lead out
         HashSet<Integer> leadsout = new HashSet<Integer>();
         
         for(int k = 1; k <= n; k++){
            if(seen[k]) continue;
            int x = k;
            ArrayList<Integer> curpath = new ArrayList<Integer>();
            while(1 <= x && x <= n && !seen[x]){
               seen[x] = true;
               curpath.add(x);
               x += array[x];
            }
            
            if(leadsout.contains(x) || x < 1 || x > n){
               for(int y : curpath){
                  leadsout.add(y);
               }
            } else if(path1index[x] != -1){
               //leads to path1
               for(int y : curpath){
                  totalfreq++;
                  path1freq[path1index[x]]++;
                  path1index[y] = path1index[x];
               }
            }
            
            
         }
         
         if(!success){
            //path failed
            
            long answer = 0L;
            
            for(int k = 0; k < path1.size(); k++){
               //jump out or anywhere in leadsout
               answer += (long)(n+1 + leadsout.size());
            }
            
            out.println(answer);
            
         } else {
            //path succeeded
            
            long answer = 0L;
            
            //nodes not in path (can jump anywhere)
            answer += (long)(n-path1.size()) * (long)(2*n+1);
            
            for(int k = 0; k < path1.size(); k++){
               //jump out, anywhere in leadsout, or some future or current point in the path
               answer += (long)(n + leadsout.size() + path1.size()-k);
               //or nodes that lead to a future point in the path
               totalfreq -= path1freq[k];
               answer += totalfreq;
            }
            
            out.println(answer);
         }

      }
      
      
      
      
      out.close();
   }
   
      
}