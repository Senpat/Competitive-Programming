//make sure to make new file!
import java.io.*;
import java.util.*;

public class B573{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      StringTokenizer st = new StringTokenizer(s);
      int[] ctoi = new int[26];
      ctoi['s'-'a'] = 0;
      ctoi['m'-'a'] = 1;
      ctoi['p'-'a'] = 2;
      
      int max = 0;
      int[][] freq = new int[10][3];
      for(int k = 0; k < 3; k++){
         String cur = st.nextToken();
         int i = Character.getNumericValue(cur.charAt(0));
         char c = cur.charAt(1);
         freq[i][ctoi[c-'a']] ++;
         max = Math.max(freq[i][ctoi[c-'a']],max);
      }
      
      for(int k = 2; k <= 8; k++){
         int counts = 0;
         int countm = 0;
         int countp = 0;
         for(int j = k-1; j <= k+1; j++){
            if(freq[j][ctoi['s'-'a']] > 0) counts++;
            if(freq[j][ctoi['m'-'a']] > 0) countm++;
            if(freq[j][ctoi['p'-'a']] > 0) countp++;
         }
         
         max = Math.max(max,counts);
         max = Math.max(max,countm);
         max = Math.max(max,countp);
      }
      
      //check for 0
      int answer = 3-max;
      
      out.println(answer);
      

      
      
      
      
      out.close();
   }
   
      
}