#include <bits/stdc++.h>

using namespace std;

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   string s;
   cin >> s;
   int n = s.length();
   
   vector<int> freq(26,0);
   
   for(int k = 0; k < n; k++){
      freq[s[k]-'A']++;
   }
   
   vector<int> chars;
   priority_queue<pair<int,int>> pq;
   for(int k = 0; k < 26; k++){
      if(freq[k] > 0){
         pq.push(make_pair(freq[k],k));
         chars.push_back(k);
      }
   }
   
   if(pq.top().first > (n+1)/2){
      cout << "-1\n";
      return 0;
   }
   
   vector<int> answer(n);
   
   int ch1 = 0;
   int ch2 = 1;
   
   bool do2 = false;
   
   for(int i = 0; i < n; i++){
      //cout << i << " " << ch1 << " " << ch2 << endl;
      //see if you have to do one of them
      while(true){
         auto [fr,x] = pq.top();
         if(freq[x] == fr) break;
         pq.pop();
         pq.push(make_pair(freq[x],x));
      }
      
      auto [top_freq,top_ch] = pq.top();
      if(i%2 != n%2 && top_freq == (n-1 - i + 1 + 1)/2){
         //do top_ch
         answer[i] = top_ch;
         pq.pop();
         freq[top_ch]--;
         if(freq[top_ch] > 0){
            pq.push(make_pair(freq[top_ch],top_ch));
         }
        
         do2 = (top_ch == chars[ch1]);
      } else {
         if(do2){
            answer[i] = chars[ch2];
            freq[chars[ch2]]--;
         } else {
            answer[i] = chars[ch1];
            freq[chars[ch1]]--;
         }
      }
      
      //update ch1 and ch2
      while(ch1 < chars.size() && freq[chars[ch1]] == 0){
         ch1++;
      }
      ch2 = ch1+1;
      while(ch2 < chars.size() && freq[chars[ch2]] == 0){
         ch2++;
      }
      
      do2 = (answer[i] == chars[ch1]);
   }
   
   for(int k = 0; k < n; k++){
      cout << (char)('A'+answer[k]);
   }
   
   
   return 0;
}