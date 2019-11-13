//make sure to make new file!
import java.io.*;
import java.util.*;

public class EK2{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      int MAX = 200005;
      int[] freq = new int[MAX];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         
         freq[array[k]]++;
      }
      
      //find min without exactly 2
      int min = -1;
      for(int k = 1; k < MAX; k++){
         if(freq[k] != 2){
            min = k;
            break;
         }
      }
      
      if(min == 1){
         for(int k = 0; k < n; k++) out.print("B");
         out.close();
         System.exit(0);
      }
      
      out.println(min);
      
      int l = 1;
      int r = min-1;
      int mid;
      
      
      int answer = -1;
      while(l<=r){
         mid = (l+r)/2;
         if(check(array,mid)){
         //if(mid*a+(n-mid)*b < c){
            l=mid+1;
            answer = mid;
         } else {
            r=mid-1;
         }
      }
      
      min = answer;
      out.println(min);
      
      
      
      boolean[] bool = new boolean[MAX];
      for(int k = 0; k < MAX; k++) bool[k] = false;
      
      for(int k = 0; k < n; k++){
         if(array[k] >= min) out.print("B");
         else if(bool[array[k]]) out.print("G");
         else{
            out.print("R");
            bool[array[k]] = true;
         }
      }
      
      

      
      
      
      
      
      out.close();
   }
   
   public static boolean check(int[] array, int i){
      Queue<Integer> q = new LinkedList<Integer>();
      HashSet<Integer> hset = new HashSet<Integer>();
      
      for(int k = 0; k < array.length; k++){
         if(array[k] >= i) continue;
         if(hset.contains(array[k])){
            if(q.peek() != array[k]) return false;
            q.poll();
         } else {
            hset.add(array[k]);
            q.add(array[k]);
         }
      }
      return true;
   }
      
   
      
}