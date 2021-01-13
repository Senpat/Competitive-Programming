//make sure to make new file!
import java.io.*;
import java.util.*;

public class C683{

   public static Long[] array;
   public static PriorityQueue<Integer> borders;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      array = new Long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      Arrays.sort(array);
      
      borders = new PriorityQueue<Integer>();
      borders.add(0);
      borders.add(n);
      
      calc(0,n,1L << 31);
      
      ArrayList<Integer> sections = new ArrayList<Integer>();
      
      while(borders.size() >= 2){
         int b1 = borders.poll();
         int size = borders.peek()-b1;
         sections.add(size);
      }
      
      int maxsize = sections.get(0);
      if(sections.size() >= 2) maxsize = Math.max(maxsize,sections.get(1));
      
      int answer = n-sections.size()-(maxsize-1);
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void calc(int l, int r, long i){
      if(i == 0 || r <= l+3) return;
      
      //count number of 0s and 1s
      int num0 = 0;
      int num1 = 0;
      
      for(int k = l; k < r; k++){
         if((array[k] & i) == 0L){
            num0++;
         } else {
            num1++;
         }
      }
      
      if(num0 == 0 || num1 == 0){
         calc(l,r,i/2L);
      } else if(num1 == 1){
         calc(l,r-1,i/2L);
      } else if(num0 == 1){
         calc(l+1,r,i/2L);
      } else {
         borders.add(l+num0);
         calc(l,l+num0,i/2L);
         calc(l+num0,r,i/2L);
      }
   }
      
      
}