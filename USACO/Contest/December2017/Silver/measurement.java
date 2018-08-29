/*
ID: patrickgzhang
LANG: JAVA
TASK: measurement
*/

import java.io.*;
import java.util.*;

class measurement{
   
   public static MergeSort ms = new MergeSort();
   public static int begin;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("measurement.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
      
      StringTokenizer inst = new StringTokenizer(f.readLine());
      int num = Integer.parseInt(inst.nextToken());
      begin = Integer.parseInt(inst.nextToken());
      
      
      int currenthigh = begin;
      int count = 0;
      
      
      //int[] sorting = new int[num]
      String[] array = new String[num];
      for(int k = 0; k < num; k++){
         array[k] = f.readLine();
         //sorting[k] = Integer.parseInt(array[k].split(" ")[0]);
      }
      ms.sort(array);
      
      int[][] matrix = new int[num][2];
      for(int k = 0; k < num; k++){
         matrix[k][0] = Integer.parseInt(array[k].split(" ")[1]);
         matrix[k][1] = begin;
      }
      
      
      
      
      int whilecount = 0;
      int counter = 1;
      while(whilecount < num){
         StringTokenizer st = new StringTokenizer(array[whilecount]);
         int i = Integer.parseInt(st.nextToken());
         if(i == counter){
            String s = st.nextToken();            
            int diff = Integer.parseInt(st.nextToken());
            int indexmatrix = indexofmatrix(matrix, Integer.parseInt(s));
            
            matrix[indexmatrix][1] += diff;
            //int value = matrix[indexmatrix][1];
            
            if(matrix[indexmatrix][1] > currenthigh && matrix[indexmatrix][1]-diff == currenthigh && noties(matrix,currenthigh)){
               System.out.println(1);
               currenthigh = matrix[indexmatrix][1];
            } else if(matrix[indexmatrix][1]-diff == currenthigh && ntgreater(matrix,indexmatrix,currenthigh)){
               /*if(matrix[indexmatrix][1] < begin){
                  currenthigh = begin;
               } else {*/
                  System.out.println(2);
                  currenthigh = matrix[indexmatrix][1];
               //}
            } else if(matrix[indexmatrix][1] > currenthigh){
               System.out.println(3);
               currenthigh = matrix[indexmatrix][1];
               count++;
            } else if(matrix[indexmatrix][1] - diff == currenthigh){
               System.out.println(4);
               currenthigh = Math.max(begin,findmax(matrix));
               count++;
            } else {
               System.out.println(5);
            }
            
                        
            whilecount++;
         }
         counter++;
      }
         
      out.println(count);
      out.close();
        
         
   }



   public static int indexof(String[] array, int x){
      for(int k = 0; k < array.length; k++){
         if(Integer.parseInt(array[k].split(" ")[0]) == x){
            return k;
         }
         //System.out.println(array[k] + " " + x);
      }
      //System.out.println(x);
      return -1;
   }
   
   public static int indexofmatrix(int[][] matrix, int i){
      for(int k = 0; k < matrix.length; k++){
         if(matrix[k][0] == i){
            return k;
         }
      }
      return -1;
      
   }
   
   public static boolean noties(int[][] matrix, int i){
      for(int k = 0; k < matrix.length; k++){
         if(matrix[k][1] == i){
            return false;
         }
      }
      if(i == begin){
         return false;
      }
      return true;
   }
   
   public static int findmax(int[][] matrix){
      int max = 0;
      for(int k = 0; k < matrix.length; k++){
         max = Math.max(matrix[k][1],max);
      }
      return max;
   }
   
   public static boolean ntgreater(int[][] matrix, int i, int ch){
      for(int k = 0; k < matrix.length; k++){
         if(matrix[k][1] == ch){
            return false;
         }
         if(k!=i){
            
            if(matrix[i][1] <= matrix[k][1]){
               return false;
            }
         }
      }
      
      return true;
   }





}

class MergeSort
{

   public static void sort(String[] array)
   { 
      String[] copyBuffer = new String[array.length];
      mergeSortHelper(array, copyBuffer, 0, array.length - 1);
      
      
   }
   private static void mergeSortHelper(String[] array, String[] copyBuffer,
                                                      int low, int high)
   {  
 
      if (low < high)
      {
         int middle = (low + high) / 2;
         mergeSortHelper(array, copyBuffer, low, middle);
         mergeSortHelper(array, copyBuffer, middle + 1, high);
         merge(array, copyBuffer, low, middle, high);
      }
   }
   

   public static void merge(String[] array, String[] copyBuffer,
                                   int low, int middle, int high)
   
   {

      int c1 = low;
      int c2 = middle+1;
      int count = low;
      
      while(c1 <= middle && c2 <= high){
         if(Integer.parseInt(array[c1].split(" ")[0]) <= Integer.parseInt(array[c2].split(" ")[0])){
            
            copyBuffer[count] = array[c1];
            c1++;
         } else {
            
            copyBuffer[count] = array[c2];
            c2++;
         }
         count++;
      }
      while (c1 <= middle){
         copyBuffer[count] = array[c1];
         c1++;
         count++;
      }
      while(c2 <= high){
         copyBuffer[count] = array[c2];
         c2++;
         count++;
      }

   	for(int j = low; j <= high; j++){
         array[j] = copyBuffer[j];
      }
   }		
}

