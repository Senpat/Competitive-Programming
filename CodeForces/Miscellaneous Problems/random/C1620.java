//BA-String
import java.io.*;
import java.util.*;

public class C1620{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long m = Long.parseLong(st.nextToken());
         long x = Long.parseLong(st.nextToken());
         
         String s = f.readLine();
         char[] array = s.toCharArray();
         
         ArrayList<Long> alist = new ArrayList<Long>();
         ArrayList<Character> alist2 = new ArrayList<Character>();                //collapse * to 1
         for(int k = 0; k < n; k++){
            if(array[k] == '*'){
               if(k == 0 || array[k-1] != '*'){
                  alist.add(1L);
                  alist2.add('*');
               }
               else alist.set(alist.size()-1,alist.get(alist.size()-1)+1L);
            } else {
               alist2.add('a');
            }
         }
         
         if(alist.size() == 0){
            out.println(s);
            continue;
         }
         
         if(m == 0){
            StringJoiner sj = new StringJoiner("");
            for(int k = 0; k < n; k++) if(array[k] == 'a') sj.add("a");
            out.println(sj.toString());
            continue;
         }
         
         long[] muls = new long[alist.size()];
         Arrays.fill(muls,Long.MAX_VALUE);
         muls[alist.size()-1] = 1L;
         long multiplier = alist.get(alist.size()-1)*m+1;
         for(int k = alist.size()-2; k >= 0; k--){
            muls[k] = multiplier;
            multiplier *= alist.get(k)*m+1;
            if(multiplier > x || multiplier < 0){
               break;
            }
         }
         
         long[] bs = new long[alist.size()];
         
         for(int k = 0; k < alist.size()-1; k++){
            if(muls[k] > x) continue;
            bs[k] = (x-1)/muls[k];
            x-=bs[k]*muls[k];
         }
         bs[alist.size()-1] = x-1;
         
         int bi = 0;
         
         StringJoiner sj = new StringJoiner("");
         for(int k = 0; k < alist2.size(); k++){
            if(alist2.get(k) == 'a'){
               sj.add("a");
            } else {
               for(int j = 0; j < bs[bi]; j++){
                  sj.add("b");
               }
               bi++;
            }
         }
         
         out.println(sj.toString());
            
         
         
         
      }
      
      
      
      
      out.close();
   }
   
      
}