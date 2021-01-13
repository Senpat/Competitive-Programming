//make sure to make new file!
import java.io.*;
import java.util.*;

public class CMBIT{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st1 = new StringTokenizer(f.readLine());
      StringTokenizer st2 = new StringTokenizer(f.readLine());
      
      int[] a = new int[n];
      int[] b = new int[n];
      
      int[] indexofa = new int[n+1];
      
      for(int k = 0; k < n; k++){
         a[k] = Integer.parseInt(st1.nextToken());
         b[k] = Integer.parseInt(st2.nextToken());
         
         indexofa[a[k]] = k;
      }
      
      long numbelow = 0L;
      long numat = 0L;
      long numabove = 0L;
      
      long answer = 0L;
      
      int[] rotfreq = new int[n];
      for(int k = 0; k < n; k++){
         if(k < indexofa[b[k]]){
            numbelow++;
            rotfreq[indexofa[b[k]]-k]++;
         }
         if(k == indexofa[b[k]]) numat++;
         if(k > indexofa[b[k]]){
            numabove++;
            rotfreq[indexofa[b[k]]-k+n]++;
         }
         answer += (long)Math.abs(k-indexofa[b[k]]);
         
          
      }
      
      //out.println(answer);
      
      long prevanswer = answer;
      for(int k = 1; k < n; k++){
         long curanswer = prevanswer;
         curanswer -= numbelow;
         curanswer += (numat + numabove);
         
         numabove += numat;
         numat = rotfreq[k];
         numbelow -= numat;
         
         //update last
         numabove--;
         numbelow++;
         
         curanswer -= (long)Math.abs(n-indexofa[b[n-k]]);
         curanswer += (long)indexofa[b[n-k]];
         
         
         answer = Math.min(answer,curanswer);
         prevanswer = curanswer;
         
         //out.println(curanswer);
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}