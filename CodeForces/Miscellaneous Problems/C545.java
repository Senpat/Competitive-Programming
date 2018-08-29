//Woodcutters
import java.io.*;
import java.util.*;

public class C545{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Tree[] trees = new Tree[n];
      
      ArrayList<Integer> alist = new ArrayList<Integer>();
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         trees[k] = new Tree(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
         alist.add(trees[k].x);
      }
      
      //first fall left
      int count = 1;
      
      int curind = 1;
      for(int k = 1; k < n-1; k++){
         //fall left if can
         if(alist.get(curind-1) < trees[k].x-trees[k].h){
            count++;
         } 
         else {
            //fall right if can
            if(alist.get(curind+1) > trees[k].x+trees[k].h){
               count++;
               alist.add(curind+1,trees[k].x+trees[k].h);
               curind++;
            }
         }
         curind++;
      }
      
      //last fall right
      if(n>1) count++;
      
      out.println(count);
      
      
      
      out.close();
   }
   
   public static class Tree{
      int x;
      int h;
      public Tree(int a, int b){
         x = a;
         h = b;
      }
   }
   
}