//make sure to make new file!
import java.io.*;
import java.util.*;

public class C833{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         long[] psum = new long[n];
         
         ArrayList<Integer> zeros = new ArrayList<Integer>();
         
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            if(k == 0) psum[0] = array[0];
            else psum[k] = psum[k-1] + array[k];
            
            if(array[k] == 0) zeros.add(k);
         }
         
         int answer = 0;
         int end1 = n;
         if(zeros.size() >= 1) end1 = zeros.get(0);
         
         for(int k = 0; k < end1; k++){
            if(psum[k] == 0) answer++;
         }
         for(int z = 0; z < zeros.size(); z++){
            int end = n;
            if(z < zeros.size()-1) end = zeros.get(z+1);
            
            HashMap<Long,Integer> hmap = new HashMap<Long,Integer>();
            int num = 0;
            for(int k = zeros.get(z); k < end; k++){
               if(hmap.containsKey(psum[k])){
                  hmap.put(psum[k],hmap.get(psum[k])+1);
               } else {
                  hmap.put(psum[k],1);
               }
               
               num = Math.max(num,hmap.get(psum[k]));
            }
            
            answer += num;
         }
      
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}