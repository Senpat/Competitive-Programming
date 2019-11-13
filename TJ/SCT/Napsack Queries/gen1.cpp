#include "testlib.h"
using namespace std;

int main(int argc, char* argv[]) {
   registerGen(argc, argv, 0);
   int q = rnd.next(15, 100000);
   int p = rnd.next(1,5);
   cout << q << " " << p << endl;
   set<int> hset;
   while(hset.size() < p){
      hset.insert(rnd.next(1,10000));
   }
   vector<int> ps;
   for(int i : hset){
      ps.push_back(i);
   }
   
   sort(ps.begin(),ps.end());
   
   for(int k = 0; k < p; k++){
      cout << ps[k];
      if(k < p-1) cout << "  ";
   }
   cout << endl;
   
   for(int k = 0; k < q; k++){
      int f = rnd.next(1,100000);
      int d = rnd.next(0,f);
      
      cout << f << " " << d << endl;
   }
   
   
}