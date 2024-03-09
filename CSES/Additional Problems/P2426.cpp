#include <bits/stdc++.h>
//greedy
//MAJOR BUG: compare was often equal so duplicates were not handled correctly
using namespace std;

//(a,b,index)
using T = tuple<long long, long long, int>;



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   auto compa = [](T a, T b){
      if(get<0>(a) == get<0>(b) && get<1>(a) == get<1>(b)) return get<2>(a) < get<2>(b);
      if(get<0>(a) == get<0>(b)) return get<1>(a) < get<1>(b);
      return get<0>(a) < get<0>(b);};
   auto compb = [](T a, T b){
      if(get<0>(a) == get<0>(b) && get<1>(a) == get<1>(b)) return get<2>(a) < get<2>(b);
      if(get<1>(a) == get<1>(b)) return get<0>(a) < get<0>(b);
      return get<1>(a) < get<1>(b);};
   auto compab = [](T a, T b){
      if(get<0>(a)-get<1>(a) == get<0>(b)-get<1>(b)) return get<2>(a) < get<2>(b);
      return get<0>(a)-get<1>(a) < get<0>(b)-get<1>(b);};
   auto compba = [](T a, T b){
      if(get<1>(a)-get<0>(a) == get<1>(b)-get<0>(b)) return get<2>(a) < get<2>(b);
      return get<1>(a)-get<0>(a) < get<1>(b)-get<0>(b);};
   
   
   int a,b,n;
   cin >> a >> b >> n;
   
   priority_queue<T,vector<T>,decltype(compa)> pq1(compa);
   for(int k = 0; k < n; k++){
      long long a,b;
      cin >> a >> b;
      pq1.push({a,b,k});
   }
   
   //sort by gain from switching from a to b
   set<T,decltype(compba)> s1ba(compba);
   set<T,decltype(compa)> s1a(compa);
   for(int k = 0; k < a; k++){
      s1ba.insert(pq1.top());
      s1a.insert(pq1.top());
      pq1.pop();
   }
   
   priority_queue<T,vector<T>,decltype(compb)> pq2(compb);
   while(!pq1.empty()){
      pq2.push(pq1.top());
      pq1.pop();
   }
   
   set<T,decltype(compab)> s2ab(compab);
   set<T,decltype(compb)> s2b(compb);
   for(int k = 0; k < b; k++){
      s2ab.insert(pq2.top());
      s2b.insert(pq2.top());
      pq2.pop();
   }
   
   set<T,decltype(compa)> seta(compa);
   set<T,decltype(compb)> setb(compb);
   
   auto remandadd = [&](T arem, T aadd, T brem, T badd){
      s1ba.erase(arem);
      s1a.erase(arem);
      s2ab.erase(brem);
      s2b.erase(brem);
      
      s1ba.insert(aadd);
      s1a.insert(aadd);
      s2ab.insert(badd);
      s2b.insert(badd);
   };
   
   while(!pq2.empty()){
      seta.insert(pq2.top());
      setb.insert(pq2.top());
      pq2.pop();
   }
   
   if(a > 0 && b > 0){
      while(true){
         auto p1 = *s1ba.rbegin();
         auto p2 = *s2ab.rbegin();
         
         //cout << get<0>(p1) << " " << get<1>(p2) << endl;
         
         //switch p1 and p2
         if(get<1>(p1) + get<0>(p2) > get<0>(p1) + get<1>(p2)){
            remandadd(p1,p2,p2,p1);
            continue;
         }
         
         if(a+b < n){
            //set has some elements
            auto nona = *seta.rbegin();
            auto nonb = *setb.rbegin();
            //cout << get<0>(nona) << endl;
            auto pa = *s1a.begin();
            auto pb = *s2b.begin();
            
            //try switch pa and nona
            if(get<0>(nona) > get<0>(pa)){
               s1ba.erase(pa);
               s1a.erase(pa);
               
               s1ba.insert(nona);
               s1a.insert(nona);
               
               seta.erase(nona);
               setb.erase(nona);
               
               seta.insert(pa);
               setb.insert(pa);
               continue;
            }
            
            //try switch pb and nonb
            if(get<1>(nonb) > get<1>(pb)){
               s2ab.erase(pb);
               s2b.erase(pb);
               
               s2ab.insert(nonb);
               s2b.insert(nonb);
               
               seta.erase(nonb);
               setb.erase(nonb);
               
               seta.insert(pb);
               setb.insert(pb);
               continue;
            }
            
            //try a -> b -> none
            if(get<0>(nona) + get<1>(p1) > get<0>(p1) + get<1>(pb)){
               remandadd(p1,nona,pb,p1);
               
               seta.erase(nona);
               setb.erase(nona);
               seta.insert(pb);
               setb.insert(pb);
               
               continue;
            }
            
            //try b -> a -> none
            if(get<0>(p2) + get<1>(nonb) > get<0>(pa) + get<1>(p2)){
               remandadd(pa,p2,p2,nonb);
               
               seta.erase(nonb);
               setb.erase(nonb);
               seta.insert(pa);
               setb.insert(pa);
               
               continue;
            }
            
         }
         
         break;
      }
   }
   
   long long answer = 0LL;
   for(T tup : s1a){
      answer += get<0>(tup);
   }
   for(T tup : s2b){
      answer += get<1>(tup);
   }
   
   cout << answer << endl;
   
   
   
   
   
   return 0;
}