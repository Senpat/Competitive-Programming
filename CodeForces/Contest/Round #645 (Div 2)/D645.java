//make sure to make new file!
import java.io.*;
import java.util.*;

public class D645{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      long[] array = new long[n*2];
      long[] msum = new long[n*2];                 //sum of month
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         array[k+n] = array[k];
         msum[k] = sum(array[k]);
         msum[k+n] = sum(array[k+n]);
      }
      
      long[] psum = new long[n*2+1];
      psum[0] = 0L;
      for(int k = 1; k <= n*2; k++){
         psum[k] = psum[k-1]+msum[k-1];
      }
      
      
      long answer = 0L;
      
      //check beginning
      int lm = 0;
      long ld = array[lm]-1;
      
      //find initial rm and rd
      long i = 1;
      int rm = 1;
      long rd = 0;
      
      while(i < m){
         i += array[rm];
         rm++;
      }
      
      if(i > m){
         rm--;
         rd = array[rm] - (i-m);
      }
      
      //calc
      answer = Math.max(answer,calc(lm,ld,rm,rd,psum));
      
      for(int k = 1; k < n; k++){
         long add = array[k];
         lm = k;
         ld = array[lm]-1;
         
         //find new rm and rd
         i = array[rm]-rd;
         rm++;
         rd = 0;
         while(i < add){
            i += array[rm];
            rm++;
         }
         
         if(i > add){
            rm--;
            rd = array[rm]-(i-add);
         }
         
         answer = Math.max(answer,calc(lm,ld,rm,rd,psum));
      
      }
      
      
      //check end
      rm = 2*n-1;
      rd = array[rm];
      
      lm = rm-1;
      ld = array[lm];
      
      i = array[rm];
      
      //find initial lm and ld
      while (i < m){
         i += array[lm];
         lm--;
         ld = array[lm];
      }
      
      if(i > m){
         lm++;
         ld = i-m;
      }
      
      answer = Math.max(answer,calc(lm,ld,rm,rd,psum));
      
      for(int k = 2*n-2; k >= n; k--){
         long add = array[k+1];
         rm = k;
         rd = array[rm];
         
         i = ld;
         lm--;
         ld = array[lm];
         
         while(i < add){
            i += ld;
            lm--;
            ld = array[lm];
         }
         
         if(i > add){
            lm++;
            ld = i-add;
         }
         
         answer = Math.max(answer,calc(lm,ld,rm,rd,psum));
      }
         
         
      
      
      
      
      
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   public static long calc(int lm, long ld, int rm, long rd, long[] psum){
      long ret = psum[rm]-psum[lm];
      ret += sum(rd)-sum(ld);
      return ret;
   }
   
   public static long sum(long x){
      return x*(x+1)/2L;
   }
      
}