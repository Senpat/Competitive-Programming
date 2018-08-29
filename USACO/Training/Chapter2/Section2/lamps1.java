/*
USER: pgz11901
TASK: lamps
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class lamps1{

   public static ArrayList<Integer> on, off;
   public static ArrayList<String> result;
   
   public static int c;

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("lamps.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      c = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      on = new ArrayList<Integer>();
      while(st.hasMoreTokens()){
         int i = Integer.parseInt(st.nextToken());
         if(i!=-1) on.add(i);
      }
      
      st = new StringTokenizer(f.readLine());
      off = new ArrayList<Integer>();
      while(st.hasMoreTokens()){
         int i = Integer.parseInt(st.nextToken());
         if(i!=-1) on.add(i);
      }
      
      result = new ArrayList<String>();
      
      int count = 0;
      String lamp = "";
      for(int k = 0; k < n; k++){
         lamp+="1";
      }
      
      dothing(lamp,0,true,1);
      dothing(lamp,0,false,1);
      
      if(result.size() == 0){
         System.out.println("IMPOSSIBLE");
         out.println("IMPOSSIBLE");
      } else {
         String[] array = new String[result.size()];
         result.toArray(array);
         Arrays.sort(array);
         for(int k = 0; k < array.length; k++){
            System.out.println(array[k]);
            out.println(array[k]);
         }
      }
      
      out.close();
      
   }
   
   public static void dothing(String s, int count, boolean bool, int index){
      if(result.contains(s)) return;
      if(count>=c || index==4){
         for(int i : on){
            if(s.charAt(i-1)=='0') return;
         }
         for(int i : off){
            if(s.charAt(i-1)=='1') return;
         }
         result.add(s);
         return;
      }
      if(!bool){
         dothing(s,count,true,index+1);
         dothing(s,count,false,index+1);
         return;
      }
      switch(index){
         case 1: dothing(do1(s),count+1,true,index+1);dothing(do1(s),count+1,false,index+1);break;
         case 2: dothing(do2(s),count+1,true,index+1);dothing(do2(s),count+1,false,index+1);break;
         case 3: dothing(do3(s),count+1,true,index+1);dothing(do3(s),count+1,false,index+1);break;
         case 4: dothing(do4(s),count+1,true,index+1);dothing(do4(s),count+1,false,index+1);break;
      }
   }
   
   
   public static String do1(String s){
      String str = "";
      for(int k = 0; k < s.length(); k++){
         if(s.charAt(k) == '0') str+="1";
         else str+="0";
      }
      return str;
   }
   public static String do2(String s){
      String str = "";
      for(int k = 0; k < s.length(); k++){
         if(k%2==0){
            if(s.charAt(k) == '0') str+="1";
            else str+="0";
         } else {
            str+=s.charAt(k);
         }
      }
      return str;
   }
   public static String do3(String s){
      String str = "";
      for(int k = 0; k < s.length(); k++){
         if(k%2==1){
            if(s.charAt(k) == '0') str+="1";
            else str+="0";
         } else {
            str+=s.charAt(k);
         }
      }
      return str;
   }
   public static String do4(String s){
      String str = "";
      for(int k = 0; k < s.length(); k++){
         if(k%3==0){
            if(s.charAt(k) == '0') str+="1";
            else str+="0";
         } else {
            str+=s.charAt(k);
         }
      }
      System.out.println(s + " " + str);
      return str;
   }
   
   
   
}
      