#include <bits/stdc++.h>

using namespace std;


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int nt,ns,rmax;
   cin >> nt >> ns >> rmax;
   
   vector<pair<int,int>> total;
   for(int k = 0; k < nt; k++){
      int x,y;
      cin >> x >> y;
      total.push_back(make_pair(x,y));
   }
   
   vector<pair<int,int>> sense;
   for(int k = 0; k < ns; k++){
      int x,y;
      cin >> x >> y;
      sense.push_back(make_pair(x,y));
   }
   
   sort(total.begin(),total.end());
   sort(sense.begin(),sense.end());
   
   int ax = -1;
   int ay = -1;
   bool mul = false;
   for(int d = 0; d < 4; d++){
      for(int k = 0; k < nt; k++){
         int curx = total[k].first - sense[0].first;
         int cury = total[k].second - sense[0].second;
         
         bool fail = false;
         int si = 0;
         for(int j = 0; j < nt; j++){
            //see if it is in range
            if(abs(total[j].first - curx) + abs(total[j].second - cury) <= rmax){
               if(si >= ns || total[j].first != curx + sense[si].first || total[j].second != cury + sense[si].second){
                  fail = true;
                  break;
               }
               si++;
            }
         }
         
         if(si < ns){
            fail = true;
         }
         
         if(!fail){
            if(ax == -1){
               ax = curx;
               ay = cury;
            } else {
               mul = true;
               break;
            }
         }
         
         
      }
      
      if(mul) break;
      
      //change direction
      for(int k = 0; k < ns; k++){
         sense[k] = make_pair(sense[k].second,-sense[k].first);
         //cout << d << " " << sense[k].first << " " << sense[k].second << endl;
      }
      sort(sense.begin(),sense.end());
      
   }
   
   if(ax == -1){
      cout << "Impossible\n";
   } else if(mul){
      cout << "Ambiguous\n";
   } else {
      cout << ax << " " << ay << endl;
   }
   
   return 0;
}