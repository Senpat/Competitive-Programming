//template metaprogramming practice

#include <bits/stdc++.h>

using namespace std;

//calculate fibonacci
template <int x>
class fibo{
   public:
   static constexpr int val = fibo<x-1>::val + fibo<x-2>::val;
   
};


template <>
class fibo<0>{
   public:
   static constexpr int val = 0;
};

template <>
class fibo<1>{
   public:
   static constexpr int val = 1;
};


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   cout << fibo<10>::val << endl;
   
   return 0;
}