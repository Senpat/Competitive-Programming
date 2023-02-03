//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, kmp practice
public class E134{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] s = f.readLine().toCharArray();
      int n = s.length;
      
      int[] si = new int[n];
      for(int k = 0; k < n; k++){
         si[k] = s[k]-'a';
      }
      
      int[] kmp = new int[n];
      
      for(int k = 1; k < n; k++){
         int j = kmp[k-1];
         while(j > 0 && s[k] != s[j]){
            j = kmp[j-1];
         }
         if(s[k] == s[j]){
            j++;
         }
         kmp[k] = j;
      }
      
      //precompute kmp jumps
      //jump[x][c] is the kmp value if you jump to xth value (in while loop) with the character c
      //now the while loop os O(1)
      int[][] jump = new int[n][26];
      
      jump[0][si[0]] = 1;
      
      for(int k = 1; k < n; k++){
         for(int j = 0; j < 26; j++){
            jump[k][j] = jump[kmp[k-1]][j];
         }
         jump[k][si[k]] = k+1;
      }
      
      int q = Integer.parseInt(f.readLine());
      StringJoiner answer = new StringJoiner("\n");
      for(int qq = 0; qq < q; qq++){
         char[] t = f.readLine().toCharArray();
         int tn = t.length;
         
         int[] ti = new int[tn];
         for(int k = 0; k < tn; k++){
            ti[k] = t[k]-'a';
         }
         
         int[] tkmp = new int[tn];
         
         tkmp[0] = jump[kmp[n-1]][ti[0]];
         
         for(int k = 1; k < tn; k++){
            int j = tkmp[k-1];
            //do normal kmp in t
            while(j >= n && t[j-n] != t[k]){
               if(j == n) {
                  j = kmp[n-1];
                  break;
               }
               j = tkmp[j-n-1];
            }
            if(j >= n && t[j-n] == t[k]){
               tkmp[k] = j+1;
               continue;
            }
            
            tkmp[k] = jump[j][ti[k]];
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < tn; k++){
            sj.add("" +tkmp[k]);
         }
         answer.add(sj.toString());
      }
      
      out.println(answer.toString());
      
      
      
      
      out.close();
   }
   
      
}