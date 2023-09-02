#include <bits/stdc++.h>
//suffix array solution, tles -> use aho corasick
using namespace std;





int ctoi(char ch){
   return (int)ch;
}
 
vector<int> make_suffix_array(const string& s){
   int n = s.length();
   int alphabet = 256;
   vector<int> p(n);
   vector<int> c(2*n);
   vector<int> cnt(max(alphabet,n));
   
   for(int k = 0; k < n; k++){
      cnt[ctoi(s[k])]++;
   }
   for(int k = 1; k < alphabet; k++){
      cnt[k] += cnt[k-1];
   }
   for(int k = 0; k < n; k++){
      p[--cnt[ctoi(s[k])]] = k;
   }
   c[p[0]] = 0;
   int classes = 1;
   for(int k = 1; k < n; k++){
      if(s[p[k]] != s[p[k-1]]){
         classes++;
      }
      c[p[k]] = classes-1;
   }
   
   vector<int> pn(n);
   vector<int> cn(2*n);
   
   for(int h = 0; (1 << h) < n; h++){
      for(int k = 0; k < n; k++){
         pn[k] = p[k] - (1 << h);
         if(pn[k] < 0){
            pn[k] += n;
         }
      }
      
      fill(cnt.begin(),cnt.end(),0);
      
      for(int k = 0; k < n; k++){
         cnt[c[pn[k]]]++;
      }
      for(int k = 1; k < classes; k++){
         cnt[k] += cnt[k-1];
      }
      for(int k = n-1; k >= 0; k--){
         p[--cnt[c[pn[k]]]] = pn[k];
      }
      cn[p[0]] = 0;
      classes = 1;
      for(int k = 1; k < n; k++){
         int cur1 = c[p[k]];
         int cur2 = c[p[k] + (1 << h)];
         int prev1 = c[p[k-1]];
         int prev2 = c[p[k-1] + (1 << h)];
         
         if(cur1 != prev1 || cur2 != prev2){
            classes++;
         }
         cn[p[k]] = classes-1;
      }
      
      //swap c and cn
      vector<int> temp = c;
      c = cn;
      cn = temp;
   }
   
   return p;
}
 
vector<int> make_lcp(const string& s, const vector<int>& suffixarray){
   int n = s.length();
   
   vector<int> pos(n);
   for(int k = 0; k < n; k++){
      pos[suffixarray[k]] = k;
   }
   
   vector<int> lcp(n-1);
   int x = 0;
   for(int k = 0; k < n; k++){
      if(pos[k] == n-1){
         x = 0;
         continue;
      }
      
      int j = suffixarray[pos[k]+1];
      while(k+x < n && j+x < n && s[k+x] == s[j+x]){
         x++;
      }
      lcp[pos[k]] = x;
      if(x > 0){
         x--;
      }
   }
   
   return lcp;
}
 
int D = 20;
vector<vector<int>> spt;
vector<int> LOG;

int query(int l, int r){
   int lg = LOG[r-l+1];
   return min(spt[lg][l],spt[lg][r-(1 << lg)+1]); 
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   ifstream fin("P2103test.txt");
   
   string s;
   fin >> s;
   int sn = s.length();
   s += "%";
   int qn;
   fin >> qn;
   vector<string> array(qn);
   vector<int> start(qn);
   vector<int> lens(qn);
   for(int k = 0; k < qn; k++){
      fin >> array[k];
      
      start[k] = s.length();
      s += array[k];
      lens[k] = array[k].length();
      
   }
   s += "$";
   
   int n = s.length();
   
   vector<int> suffixarray = make_suffix_array(s);
   vector<int> lcp = make_lcp(s,suffixarray);
   
   vector<int> saindexof = vector<int>(n);
   for(int k = 0; k < n; k++){
      saindexof[suffixarray[k]] = k;
   }
   
   vector<int> suffpsum(n+1);
   suffpsum[0] = 0;
   for(int k = 1; k <= n; k++){
      suffpsum[k] = suffpsum[k-1];
      if(suffixarray[k-1] < sn){
         suffpsum[k]++;
      }
   }
   
   cout << "done precomp" << endl;
   //make a min sparse table on lcp
   int N = 600005;
   LOG = vector<int>(N);
   LOG[1] = 0;
   for(int k = 2; k < N; k++){
      LOG[k] = LOG[k >> 1]+1;
   }
   
   spt = vector<vector<int>>(D,vector<int>(N,0));
   for(int k = 0; k < n-1; k++){
      spt[0][k] = lcp[k];
   }
   
   for(int d = 1; d < D; d++){
      for(int k = 0; k + (1 << (d-1)) < N; k++){
         spt[d][k] = min(spt[d-1][k],spt[d-1][k+(1 << (d-1))]);
      }
   }
   
   vector<int> answer(qn);
   for(int k = 0; k < qn; k++){
      int initial = saindexof[start[k]];
      //get left
      int l = 0;
      int r = initial-1;
      int left = initial;
      while(l <= r){
         int mid = l + ((r-l) >> 1);
         
         if(query(mid,initial-1) >= lens[k]){
            left = mid;
            r = mid-1;
         } else {
            l = mid+1;
         }
      }
      
      //get right
      l = initial;
      r = n-1;
      int right = initial;
      while(l <= r){
         int mid = l + ((r-l) >> 1);
         //cout << start[k] << " " << mid << " " << query(start[k],mid) << endl;
         if(query(initial,mid) >= lens[k]){
            right = mid+1;
            l = mid+1;
         } else {
            r = mid-1;
         }
      }
      //cout << left << " " << right << endl;
      answer[k] = suffpsum[right+1]-suffpsum[left];
   }
   
   stringstream ss;
   
   for(int k = 0; k < qn; k++){
      ss << answer[k] << "\n";
   }
   
   cout << ss.str() << endl;
   
//    cout << s << endl;
//    cout << "suffix array\n";
//    for(int k = 0; k < n; k++){
//       cout << suffixarray[k] << " ";
//    }
//    cout << endl;
//    cout << "lcp\n";
//    for(int k = 0; k < n-1; k++){
//       cout << lcp[k] << " ";
//    }
//    cout << endl;
   
   return 0;
}