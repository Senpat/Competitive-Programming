//make sure to make new file!
import java.io.*;
import java.util.*;

public class D635{

   public static int[] answer;
   public static int[] t;
   public static int[] s;
   public static int n;
   
   public static boolean found = false;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      t = new int[n+1];
      s = new int[n+1];
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      t[0] = Integer.parseInt(st.nextToken());
      s[0] = Integer.parseInt(st.nextToken());
      
      answer = new int[n+1];
      Arrays.fill(answer,-1);
      HashMap<Integer,Integer> hmap = new HashMap<Integer,Integer>();
      hmap.put(1,2);
      int prev = 1;
      for(int k = 2; k <= 105; k++){
         prev += k;
         hmap.put(prev,k+1);
      }
      
      for(int k = 1; k <= n; k++){
         out.println("+ " + k);
         out.flush();
         st = new StringTokenizer(f.readLine());
      
         t[k] = Integer.parseInt(st.nextToken());
         s[k] = Integer.parseInt(st.nextToken());
      
         if(t[k]-t[k-1]>0){
            answer[k] = hmap.get(t[k]-t[k-1]);
         }
      }
      
      
      dfs(1);
      
      StringJoiner sj = new StringJoiner(" ");
      sj.add("!");
      for(int k = 1; k <= n; k++){
         sj.add("" + answer[k]);
      }
      
      out.println(sj.toString());
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void dfs(int i){
      if(found) return;
      
      if(i >= 5){
         //check
         int a = answer[i-4]+1;
         int b = answer[i-3]+1;
         int d = answer[i-1];
         int e = answer[i];
         if(s[i-2]-s[i-3] != (a*b + b*d + d*e)){
            return;
         }
      } else if(i == 4){
         int a = answer[i-3]+1;
         int c = answer[i-1];
         int d = answer[i];
         if(s[i-2]-s[i-3] != (a*c + c*d)){
            return;
         }
      } else if(i == 3){
         int b = answer[i-1];
         int c = answer[i];
         if(s[i-2]-s[i-3] != b*c){
            return;
         }
      } 
      
      
      if(i==n){
         //check for i-1 and i
         
         //i-1
         int a = answer[i-3]+1;
         int b = answer[i-2]+1;
         int c = answer[i-1]+1;
         int d = answer[i];
         
         if(s[i-1]-s[i-2] != (a*b+b*d)){
            return;
         }
         
         if(s[i]-s[i-1] != (b*c)){
            return;
         }
         
         found = true;
         return;
      }
      
      if(answer[i+1] == -1){
         answer[i+1] = 0;
         dfs(i+1);
         if(found) return;
         answer[i+1] = 1;
         dfs(i+1);
         if(found) return;
      } else {
         dfs(i+1);
      }
   }
      
      
}