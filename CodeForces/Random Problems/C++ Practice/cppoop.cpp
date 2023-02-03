#include <bits/stdc++.h>

using namespace std;

class Base{
   public:
   
   Base() {
      cout << "base constructor" << endl;
   }
   
   Base(Base& base){
      cout << "base copy constructor" << endl;
   }
   
   Base& operator=(const Base& base){
      cout << "base copy assignment" << endl;
      return *this;
   }
   
   Base(Base&& base){
      cout << "base move constructor" << endl;
   }
   
   Base& operator=(Base&& base){
      cout << "base move assignment" << endl;
      return *this;
   }
   
   ~Base() {
      cout << "base destructor" << endl;
   }
   
};

class Derived : public Base{
   public:

   Derived() {
      cout << "derived constructor" << endl;
   }
   
   Derived(Derived& derived){
      cout << "derived copy constructor" << endl;
   }
   
   Derived& operator=(const Derived& derived){
      cout << "derived copy assignment" << endl;
      return *this;
   }
   
   Derived(Derived&& derived){
      cout << "derived move constructor" << endl;
   }
   
   Derived& operator=(Derived&& derived){
      cout << "derived move assignment" << endl;
      return *this;
   }
   
   ~Derived() {
      cout << "derived destructor" << endl;
   }
   
};


   

int main(){
   
   Base base;
   Derived derived;
   
   Base test = (Base)derived;


   return 0;
}