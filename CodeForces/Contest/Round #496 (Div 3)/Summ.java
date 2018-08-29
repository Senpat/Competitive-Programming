//Round 496 (Div 3) C
//Summarize the Power of 2

import java.util.*;
import java.io.*;

public class Summ{

   public static void main(String[] args) throws IOException{
      Scanner sc = new Scanner(System.in);
      
      int n = Integer.parseInt(sc.nextLine());
      
      
      
      int[] array = new int[n];
      
      StringTokenizer st = new StringTokenizer(sc.nextLine());
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      
      shuffleArray(array);
      Arrays.sort(array);
      
      if(n==1){
         System.out.println(1);
         System.exit(0);
      }
      
      boolean[] answers = new boolean[n];
      
      for(int k = 0; k < n; k++){
         if(!answers[k]){
            int cur = array[k];
            
            if((cur & (cur-1)) == 0){
               int f = freq(array,k);
               if(f > 1){
               
                  for(int x = 0; x < f; x++){
                     answers[k+x] = true;
                  }
               } 
            }
            for(int j = 0; Math.pow(2,j) <= array[n-1] + array[n-2]; j++){
               if(Math.pow(2,j) >= cur && Math.pow(2,j)!=2*cur){
                  int test2 = Arrays.binarySearch(array,(int)Math.pow(2,j)-cur);
                  if(test2>=0&&test2!=k){
                     answers[test2] = true;
                     answers[k] = true;
                  }
               }
            }
            
         }
         //System.out.print(k+ " ");
         //p(answers);
      }
      
      //p(answers);
      
      int count = 0;
      for(boolean b : answers){
         if(!b) count++;
      }
      
      System.out.println(count);
      
   }
   
   public static int freq(int[] array, int index){
      int cur = array[index];
      int c = 0;
      int i = index;
      
      while(i<array.length && array[i]==cur){
         c++;
         i++;
      }
      return c;
   }
   
   public static void p(boolean[] array){
      for(int k = 0; k < array.length; k++){
         System.out.print(array[k] + "\t");
      }
      System.out.println();
   }
   
   public static void shuffleArray(int[] arr){
      int n = arr.length;
      Random rnd = new Random();
      for(int i=0; i<n; ++i){
         int tmp = arr[i];
         int randomPos = i + rnd.nextInt(n-i);
         arr[i] = arr[randomPos];
         arr[randomPos] = tmp;
      }   
   }
}