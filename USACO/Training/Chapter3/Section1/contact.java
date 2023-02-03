/*
TASK: contact
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class contact{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("contact.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
      
      int N = 200000;
      int M = 12;
      
      int[] pow2 = new int[M+1];
      pow2[0] = 1;
      for(int k = 1; k <= M; k++){
         pow2[k] = 2*pow2[k-1];
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      
      StringJoiner insj = new StringJoiner("");
      while(true){
         String s = f.readLine();
         if(s == null) break;
         
         insj.add(s);
      }
      
      String s = insj.toString();
      int sn = s.length();
      
      int[] array = new int[sn];
      for(int k = 0; k < sn; k++){
         array[k] = Character.getNumericValue(s.charAt(k));
      }
      
      ArrayList<ArrayList<String>> freqs = new ArrayList<ArrayList<String>>(N+1);
      for(int k = 0; k <= N; k++) freqs.add(new ArrayList<String>());
      
      for(int k = a; k <= b; k++){
         if(k > sn) break;
         
         HashMap<Integer,Integer> hmap = new HashMap<Integer,Integer>();
         
         int cur = 0;
         for(int j = 0; j < k; j++){
            cur += array[j] * pow2[k-j-1];
         }
         
         hmap.put(cur,1);
         
         for(int j = k; j < sn; j++){
            if(cur >= pow2[k-1]) cur -= pow2[k-1];
            cur <<= 1;
            cur += array[j];
            
            if(hmap.containsKey(cur)) hmap.put(cur,hmap.get(cur)+1);
            else hmap.put(cur,1);
         }
         
         for(int i : hmap.keySet()){
            String add = "";
            int x = i;
            for(int j = k-1; j >= 0; j--){
               if(x >= pow2[j]){
                  add += "1";
                  x -= pow2[j];
               } else {
                  add += "0";
               }
            }
            
            freqs.get(hmap.get(i)).add(add);
         }
         
      }
      
      StringJoiner sj = new StringJoiner("\n");
      int added = 0;
      for(int k = N-1; k >= 1 && added < n; k--){
         if(freqs.get(k).size() != 0){
            sj.add("" + k);
            StringJoiner line = new StringJoiner(" ");
            int lineadded = 0;
            for(String curs : freqs.get(k)){
               line.add(curs);
               lineadded++;
               if(lineadded%6 == 0){
                  sj.add(line.toString());
                  line = new StringJoiner(" ");
               }
            }
            if(lineadded % 6 != 0) sj.add(line.toString());
            added++;
         }
      }
      
      out.println(sj.toString());
        
      out.close();
   }
      
}