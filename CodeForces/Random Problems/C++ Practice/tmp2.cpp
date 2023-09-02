//make a hash function
//if the type can do mod, do mod
//if not, call std::hash on it
#include <bits/stdc++.h>

using namespace std;

template <class T, class = void>
struct can_mod : false_type{};

template <class T>
struct can_mod<T, void_t<decltype(declval<T&>() % 100)>> : true_type{};

template <class T, class = void>
int get_hash(T x){
   return hash(x);
}

template <class T>
int get_hash<T, void_t<decltype(declval<T&>() % 100)>>(T x){
   return x % 100;
} 

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   cout << get_hash(105) << endl;
   cout << get_hash("hi") << endl;
   
   return 0;
}