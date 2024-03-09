import java.io.*;
import java.util.*;

class B{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("b1_full.txt"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("b1_full_out.txt")));
      
      int M = 41;
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
       
         ArrayList<Integer> divs = new ArrayList<Integer>();
         
         for(int i = 1; i*i <= n; i++){
            if(i*i == n) divs.add(i);
            else{
               if(n%i == 0){
                  divs.add(i);
                  divs.add(n/i);
               }
            }
         }
         
         Collections.sort(divs);
         
         ArrayList<Integer> smalldivs = new ArrayList<Integer>();
         HashMap<Integer,Integer> indexof = new HashMap<Integer,Integer>();
         
         int d = divs.size();
         for(int k = 0; k < d; k++){
            indexof.put(divs.get(k),k);
            if(divs.get(k) <= M) smalldivs.add(divs.get(k));
         }
         
         int[][] minstep = new int[d][M+1];     //minstep[x][y] = min # of steps to get to the xth divisor with sum y
         int[][] parent = new int[d][M+1];
         for(int k = 0; k < d; k++){
            Arrays.fill(minstep[k],Integer.MAX_VALUE);
            Arrays.fill(parent[k],-1);
         }
         
         for(int div : smalldivs){
            if(indexof.containsKey(div)){
               minstep[indexof.get(div)][div] = 1;
            }
         }
         
         for(int k = 0; k < d; k++){
            for(int j = 0; j <= M; j++){
               if(minstep[k][j] == Integer.MAX_VALUE) continue;
               
               for(int div : smalldivs){
                  int next = divs.get(k) * div;
                  if(j + div <= M && indexof.containsKey(next)){
                     int ni = indexof.get(next);
                     if(minstep[ni][j+div] > minstep[k][j]+1){
                        minstep[ni][j+div] = minstep[k][j]+1;
                        parent[ni][j+div] = div;
                     }
                  }
               }
            }
         }
         
         //System.out.println(minstep[d-1][M]);
         
         if(minstep[d-1][M] == Integer.MAX_VALUE){
            out.println("Case #" + q + ": -1");
         } else {
            ArrayList<Integer> answer = new ArrayList<Integer>();
            
            int i = d-1;
            int sum = M;
            while(parent[i][sum] != -1){
               int p = parent[i][sum];
               answer.add(p);
               sum -= p;
               i = indexof.get(divs.get(i)/p);
            }
            answer.add(sum);
            
            StringJoiner sj = new StringJoiner(" ");
            sj.add("" + answer.size());
            for(int a : answer){
               sj.add("" + a);
            }
            
            out.println("Case #" + q + ": " + sj.toString());
         }
         
      }
      
        
      out.close();
   }
      
}