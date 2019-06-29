//make sure to make new file!
import java.io.*;
import java.util.*;
//same exact thing as H570
public class E570{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      String s = f.readLine();
      
      char[] c = s.toCharArray();
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = c[k]-'a';
      }
      
      
      
      
      
      long answer = 0L;
      for(int k = n; k >= 0; k--){
         long i = numsubseq(array,k);
         if(i < m){
            answer += i*(n-k);
            m-=i;
         } else {
            answer += m*(n-k);
            m-=m; //lol
            break;
         }
      }
      
      if(m > 0){
         out.println(-1);
      } else {
         out.println(answer);
      }

      
      
      
      
      out.close();
   }
   
   public static long numsubseq(int[] a, int L){
      if(L == 0) return 1;
      
      int n = a.length;
      
      
      //stores number of distinct subsequences in suffix
      long[] array = new long[n];                 //array at l
      long[] array1 = new long[n];                //array at l-1
   
      //assuming max num is 26
      long[] overcount = new long[26];             //stores how much a number gets to avoid overcounting
      overcount[a[n-1]] = 1;
      
      //fill array1, l = 1
      array1[n-1] = 1;
      for(int k = n-2; k >= 0; k--){
         if(overcount[a[k]] == 1){
            array1[k] = array1[k+1];
         } else {
            array1[k] = array1[k+1]+1;
            overcount[a[k]] = 1;
            
         }
      }
      
      if(L == 1) return array1[0];
      
      for(int k = 2; k <= L; k++){
         Arrays.fill(array,0L);
         Arrays.fill(overcount,0L);
         for(int j = n-k; j >= 0; j--){
            array[j] = array[j+1] + array1[j+1];
            array[j] -= overcount[a[j]];
            overcount[a[j]] = array1[j+1];
         }
         
         for(int j = 0; j < n; j++){
            array1[j] = array[j];
         }
      }
      
      return array[0];
      
   
   }
   
      
}