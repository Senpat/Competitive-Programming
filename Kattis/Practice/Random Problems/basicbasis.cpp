#include <bits/stdc++.h>
//bug: handle zero (problem requires non-empty subset)
using namespace std;

#define B 160

vector<string> fromhex = {
   "0000",
   "0001",
   "0010",
   "0011",
   "0100",
   "0101",
   "0110",
   "0111",
   "1000",
   "1001",
   "1010",
   "1011",
   "1100",
   "1101",
   "1110",
   "1111"
};

int toindex(char c){
   if(c >= 'a') return (int)(c-'a') + 10;
   else return c-'0';
}

bitset<B> gen(string s){
   bitset<B> ret;
   
   for(int k = 0; k < s.length(); k++){
      string cur = fromhex[toindex(s[k])];
      for(int j = 0; j < 4; j++){
         if(cur[j] == '1'){
            ret.set(k*4+j);  
         }
      }
   }
   
   return ret;
}

int msb(bitset<B> bset){
   for(int k = 0; k < B; k++){
      if(bset[k]) return k;
   }
   return -1;
}

vector<bitset<B>> copyb(const vector<bitset<B>>& v){
   vector<bitset<B>> ret(v.size());
   for(int k = 0; k < v.size(); k++){
      ret[k] = v[k];
   }
   return ret;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,m,x;
   cin >> n >> m >> x;
   
   vector<bitset<B>> a(n);
   for(int k = 0; k < n; k++){
      string s;
      cin >> s;
      a[k] = gen(s);
   }
   
   vector<bitset<B>> q(m);
   for(int k = 0; k < m; k++){
      string s;
      cin >> s;
      q[k] = gen(s);
   }
   
   vector<vector<bitset<B>>> bases;
   vector<int> indeces;             //the index of the vector that added to that basis
   
   vector<bitset<B>> initial(160);
   int zero = -1;
   int msb0 = msb(a[0]);
   if(msb0 != -1){
      initial[msb0] = a[0];
   } else {
      zero = 1;
   }
   bases.push_back(initial);
   indeces.push_back(0);
   
   //create bases
   for(int k = 1; k < n; k++){
      //lower index is more significant in bitsets
      bool found = false;
      for(int b = 0; b < B; b++){
         if(!a[k][b]) continue;
         
         if(bases.back()[b].none()){
            //add to basis
            //copy over old one
            vector<bitset<B>> newb = copyb(bases.back());       //copy assignment
            
            newb[b] = a[k];
            
            bases.push_back(newb);
            indeces.push_back(k);
            
            found = true;
            break;
         }
         
         //kill bit (make a[k] smaller)
         a[k] ^= bases.back()[b];
      }
      
      if(!found && zero == -1){
         zero = k+1;
      }
   }
   
   
   for(int t = 0; t < m; t++){
      if(q[t].none()){
         cout << zero << endl;
         continue;
      }
      
      //binary search
      int l = 0;
      int r = bases.size()-1;
      int ans = -1;
      
      while(l <= r){
         int mid = l + (r-l)/2;
      
         bitset<B> bset = q[t];   
         //see if bset is in bases[mid]
         for(int b = 0; b < B; b++){
            if(bases[mid][b].any() && bset[b]) bset ^= bases[mid][b];
         }
         
         if(bset.any()){
            //fail
            l = mid+1;
         } else {
            r = mid-1;
            ans = indeces[mid]+1;
         }
      }
      
      cout << ans << endl;
   }
   
   
   
   return 0;
}