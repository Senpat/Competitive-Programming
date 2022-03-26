//make sure to make new file!
import java.io.*;
import java.util.*;

public class B767{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      boolean[][] string2 = new boolean[26][26];
      boolean[][][] string3 = new boolean[26][26][26];
      boolean[][] string32 = new boolean[26][26];
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
         String[] array = new String[n];
         
         boolean trivial = false;
         for(int k = 0; k < n; k++){
            array[k] = f.readLine();
            if(array[k].length() == 1){
               trivial = true;
            } else if(array[k].length() == 2){
               if(array[k].charAt(0) == array[k].charAt(1)){
                  trivial = true;
               } else {
                  string2[array[k].charAt(0)-'a'][array[k].charAt(1)-'a'] = true;
               }
            } else {                                                                      //length is 3
               if(array[k].charAt(0) == array[k].charAt(2)){
                  trivial = true;
               } else {
                  string3[array[k].charAt(0)-'a'][array[k].charAt(1)-'a'][array[k].charAt(2)-'a'] = true;
                  string32[array[k].charAt(0)-'a'][array[k].charAt(1)-'a'] = true;
               }
            }
            
            
            //nontrivial cases
            if(array[k].length() == 2){
               //look for 2+2, 3+2
               if(string2[array[k].charAt(1)-'a'][array[k].charAt(0)-'a'] || string32[array[k].charAt(1)-'a'][array[k].charAt(0)-'a']){
                  trivial = true;
               }
            } else if(array[k].length() == 3){
               //look for 2+3, 3+3
               int a = array[k].charAt(0)-'a';
               int b = array[k].charAt(1)-'a';
               int c = array[k].charAt(2)-'a';
               if(string2[c][b] || string3[c][b][a]){
                  trivial = true;
               }
            } 
            
            
            
         }
         
         if(trivial){
            out.println("YES");
         } else {
            out.println("NO");
         }
         /*
         //all nontrivial 2s and 3s
         boolean found = false;
         for(int k = 0; k < n; k++){
            if(array[k].length() == 2){
               //look for 2+2
               if(string2[array[k].charAt(1)-'a'][array[k].charAt(0)-'a']){
                  found = true;
                  break;
               }
            } else {
               //look for 2+3, 3+2, 3+3
               int a = array[k].charAt(0)-'a';
               int b = array[k].charAt(1)-'a';
               int c = array[k].charAt(2)-'a';
               if(string2[c][b] || string2[b][a] || string3[c][b][a]){
                  found = true;
                  break;
               }
            }  
         }
         
         if(found){
            out.println("YES");
         } else {
            out.println("NO");
         }
         */
         for(int k = 0; k < n; k++){
            if(array[k].length() == 2){
               string2[array[k].charAt(0)-'a'][array[k].charAt(1)-'a'] = false;
            } else if(array[k].length() == 3){
               string3[array[k].charAt(0)-'a'][array[k].charAt(1)-'a'][array[k].charAt(2)-'a'] = false;
               string32[array[k].charAt(0)-'a'][array[k].charAt(1)-'a'] = false;
            }
         }
      
      
      
      }
      
      
      
      
      out.close();
   }
   
      
}