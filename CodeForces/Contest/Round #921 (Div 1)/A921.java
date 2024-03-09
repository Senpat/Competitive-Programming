//make sure to make new file!
import java.io.*;
import java.util.*;

public class A921{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int m = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
         int n = Integer.parseInt(st.nextToken());
      
         char[] s = f.readLine().toCharArray();
         
         int[][] next = new int[n][x];
         for(int k = 0; k < x; k++){
            if(s[n-1]-'a' == k){
               next[n-1][k] = n-1;
            } else {
               next[n-1][k] = -1;
            }
            
            for(int j = n-2; j >= 0; j--){
               if(s[j]-'a' == k){
                  next[j][k] = j;
               } else {
                  next[j][k] = next[j+1][k];
               }
            }
         }
         
         int letters = 0;
         ArrayList<Character> path = new ArrayList<Character>();
         
         int i = 0;
         while(i < n){
            //get next jump
            int maxi = 0;
            boolean fail = false;
            for(int k = 0; k < x; k++){
               if(next[i][k] == -1){
                  fail = true;
                  maxi = k;
                  break;
               }
               if(next[i][k] > next[i][maxi]){
                  maxi = k;
               }
            }
            
            path.add((char)(maxi+'a'));
            if(fail) break;
            
            letters++;
            i = next[i][maxi]+1;
         }
         
         if(letters >= m){
            out.println("YES");
         } else {
            out.println("NO");
            StringJoiner sj = new StringJoiner("");
            for(char c : path){
               sj.add(""+c);
            }
            for(int k = path.size(); k < m; k++){
               sj.add("a");
            }
            out.println(sj.toString());
         }
      }
      
      
      
      
      out.close();
   }
   
      
}