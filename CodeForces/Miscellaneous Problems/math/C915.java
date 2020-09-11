//make sure to make new file!
import java.io.*;
import java.util.*;

public class C915{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] s1 = f.readLine().toCharArray();
      char[] s2 = f.readLine().toCharArray();
      
      
      int[] a = new int[s1.length];
      int[] freqa = new int[10];
      for(int k = 0; k < s1.length; k++){
         a[k] = Character.getNumericValue(s1[k]);
         freqa[a[k]]++;
      }
      
      int[] b = new int[s2.length];
      int[] freqb = new int[10];
      for(int k = 0; k < s2.length; k++){
         b[k] = Character.getNumericValue(s2[k]);
         freqb[b[k]]++;
      }
      
      if(a.length < b.length){
         //maximize a
         StringJoiner sj = new StringJoiner("");
         for(int k = 9; k >= 0; k--){
            for(int j = 0; j < freqa[k]; j++){
               sj.add("" + k);
            }
         }
         
         out.println(sj.toString());
      } else {
         StringJoiner sj = new StringJoiner("");
         
         int i = 0;
         while(freqa[b[i]] > 0){
            sj.add("" + b[i]);
            freqa[b[i]]--;
            i++;
         }
         out.println(sj.toString());
         for(int k = b[i]; k >= 0; k--){
            if(freqa[k] > 0){
               sj.add("" + k);
               freqa[k]--;
               break;
            }
         }
         out.println(sj.toString());
         for(int k = 9; k >= 0; k--){
            for(int j = 0; j < freqa[k]; j++){
               sj.add("" + k);
            }
         }
         
         out.println(sj.toString());
      }
         
         
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}