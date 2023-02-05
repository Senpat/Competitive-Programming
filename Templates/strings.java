//make sure to make new file!
import java.io.*;
import java.util.*;

public class strings{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      int[] array = new int[s.length()];
      for(int k = 0; k < s.length(); k++) array[k] = ctoi(s.charAt(k));
      int[] kmp = getkmp(array);
      
      for(int i : kmp){
         out.println(i + " ");
      }
      out.println();
      
      
      
      
      out.close();
   }
   
   //kmp:
   //abcabcd -> 0 0 0 1 2 3 0
   public static int[] getkmp(int[] s){
      int n = s.length;
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
      
      return kmp;
   }
   
   
   //z function:
   //aaabaab -> 0 2 1 0 2 1 0
   public static int[] getz(int[] s){
      int n = s.length;
      int[] z = new int[n];
      int l = -1;
      int r = -1;
      for(int i = 1; i < n; i++){
         z[i] = i >= r ? 0 : Math.min(r - i, z[i - l]);
         while (i + z[i] < s.length && s[i + z[i]] == s[z[i]])
            z[i]++;
         if (i + z[i] > r)
            l = i;
         r = i + z[i];
      
      }
      
      return z;
   }
   
   public static int ctoi(char ch){
      return ch-'a';
   }

   
      
}