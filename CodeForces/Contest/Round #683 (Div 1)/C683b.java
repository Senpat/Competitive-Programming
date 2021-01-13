//make sure to make new file!
import java.io.*;
import java.util.*;

public class C683b{

   public static Long[] array;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      array = new Long[n];
      
      ArrayList<Integer> sections = new ArrayList<Integer>();
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         if(array[k] == 0L) sections.add(1);
      }
      
      for(int k = 0; k <= 30; k++){
         long i = 1L << k;
         int sum = 0;
         for(int j = 0; j < n; j++){
            if(array[j] >= i && array[j] < 2L*i) sum++;
         }
         if(sum > 0) sections.add(sum);
      }
      
      int all1 = n-sections.size();
      int answer = all1;
      
      //keep 1st one
      answer = Math.min(answer,all1-(Math.min(2,sections.get(0)-1)));
      //keep 2nd
      if(sections.size() >= 2) answer = Math.min(answer,all1-(Math.min(2,sections.get(1)-1)));
      
      for(int k = 2; k < sections.size(); k++){
         answer = Math.min(answer,all1-(Math.min(2,sections.get(k)-1))+k-1);
      }
      
      out.println(answer);
      
      
      
      
      
      out.close();
   }
   
   
      
      
}