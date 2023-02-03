#include <bits/stdc++.h>
//c++ rule of 5 practice
//class just contains a pointer
using namespace std;

class Class{
   public:
   int* ptr = new int(0);
   Class(int i){
      cout << "Constructor" << endl;
      cout << *ptr << endl;
      *ptr = i;
   }
   
   Class(Class& a){
      cout << "Copy Constructor" << endl;
      *ptr = *a.ptr;
   }
   
   Class& operator=(const Class& a){
      cout << "Copy Assignment Operator" << endl;
      *ptr = *a.ptr;
      return *this;
   }
   
   Class(Class&& a){
      cout << "Move Constructor" << endl;
      ptr = a.ptr;
      a.ptr = new int(0);
   }
   
   Class& operator=(Class&& a){
      cout << "Move Assignment Operator" << endl;
      ptr = a.ptr;
      a.ptr = new int(0);
      return *this;
   }
   
   
   ~Class(){
      cout << "Destructor" << endl;
      delete ptr;
   }
};

template<class T>
class ClassT{
   public:
   T val;
   ClassT(T arg){
      val = arg;
   }
   void p();
};

template<class T>
void ClassT<T>::p(){
   cout << ClassT<T>::val << endl;
}

int main(){
   //test Class (ptr)
   /*
   Class a(5);
   cout << *a.ptr << endl;
  
   Class b = a;
   cout << *b.ptr << endl;
   *b.ptr = 6;
   cout << *a.ptr << endl;
   cout << *b.ptr << endl;
   
   b = a;
   cout << *b.ptr << endl;
   *b.ptr = 6;
   cout << *a.ptr << endl;
   cout << *b.ptr << endl;
   
   Class c(10);
   Class d = move(c);
   cout << *c.ptr << endl;
   cout << *d.ptr << endl;
   *c.ptr = 11;
   cout << *c.ptr << endl;
   cout << *d.ptr << endl;
   
   d = move(c);
   cout << *c.ptr << endl;
   cout << *d.ptr << endl;
   *c.ptr = 12;
   cout << *c.ptr << endl;
   cout << *d.ptr << endl;*/
   
   //test ClassT (template and primitive type)
   cout << "Constructor" << endl;
   ClassT<int> at(5);
   at.p();
   
   cout << "Copy Constructor" << endl;
   ClassT<int> bt = at;
   bt.p();
   bt.val = 10;
   at.p();
   bt.p();
   
   cout << "Copy Assignment Operator" << endl;
   bt = at;
   bt.p();
   bt.val = 10;
   at.p();
   bt.p();
   
   cout << "Move Constructor" << endl;
   ClassT<int> ct(5);
   ClassT<int> dt = move(ct);
   dt.p();
   ct.val = 10;
   ct.p();
   dt.p();
   
   cout << "Move Assignment Operator" << endl;
   ct.val = 5;
   dt = move(ct);
   dt.p();
   ct.val = 10;
   ct.p();
   dt.p();
   
   
   
   return 0;
}