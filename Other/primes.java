
class primes{
   
   public static void main(String[] args){
      
   int count = 2; //including 2 and 3
   
   int num = 3;
   
   while(count<27332){
      num+=2;
      if(prime(num)){
         count++;
      }
      
   }
   
   System.out.println("The Answer is: " + num);    
      
      
   }

   public static boolean prime(int x){
      for(int k = 3; k <= (int)Math.sqrt(x); k++){
         if(x%k == 0){
            return false;
         }
      }
      return true;
   }

}