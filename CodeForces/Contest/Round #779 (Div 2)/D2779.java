//make sure to make new file!
import java.io.*;
import java.util.*;
//danny
public class D2779{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
      
         int n = r-l+1;
         int[] array = new int[n];
         st = new StringTokenizer(f.readLine());
         
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         //fill trie
         
         Trie head = new Trie(-1);
         
         for(int k = 0; k < n; k++){
            Trie cur = head;
            for(int i = (1<<17); i >= 1; i >>= 1){
               if((array[k]&i) == 0){
                  //0
                  if(cur.children[0] == null){
                     cur.children[0] = new Trie(array[k]);
                  }
                  cur = cur.children[0];
               } else {
                  //1
                  if(cur.children[1] == null){
                     cur.children[1] = new Trie(array[k]);
                  }
                  cur = cur.children[1];
               }
            }
         }
         
         int answer = -1;
         
         for(int k = 0; k < n; k++){
            int x = array[k]^l;
            //get min
            Trie cur = head;
            int i = (1<<17);
            while(cur.children[0] != null || cur.children[1] != null){
               int left = (0^Math.min(1,x&i));
               if(cur.children[left] != null){
                  cur = cur.children[left];
               } else {
                  cur = cur.children[1-left];
               }
               i >>= 1;
            }
            
            int min = cur.val^x;
            
            cur = head;
            i = (1<<17);
            while(cur.children[0] != null || cur.children[1] != null){
               int right = (1^Math.min(1,x&i));
               if(cur.children[right] != null){
                  cur = cur.children[right];
               } else {
                  cur = cur.children[1-right];
               }
               i >>= 1;
            }
            
            int max = cur.val^x;
            
            if(l == min && r == max){
               answer = x;
               break;
            }
         }
         
         if(answer == -1){
            out.println(-1);
         } else {
            out.println(answer);
         }

      }
      
      
      
      
      out.close();
   }
   
   public static class Trie{
      int val;                         //doesn't matter if not at the end
      Trie[] children;
      
      public Trie(int a){
         val = a;
         children = new Trie[2];
      }
   }
   
      
}