#include <bits/stdc++.h>
//bug: forgot x ^= last
using namespace std;

struct Member{
   int id;
   long long y;
   vector<int> obs;
   int on;
   
   int alarmlevel;
   long long alarmstarts[3];
   
   Member(int id_, long long y_, vector<int> obs_) : id(id_), y(y_), obs(obs_),
                                                     on(obs_.size()), alarmlevel(0){
      for(int k = 0; k < 3; k++){
         alarmstarts[k] = 0LL;
      }                          
   }
};

struct Alarm{
   int id;
   long long target;
   long long start;        //how many the observatory had when the alarm was placed
   
   int level;              //used to see if that alarm has been replaced
   
   Alarm(int id_, long long target_, long long start_, int level_) : id(id_), target(target_),
                                                                     start(start_), level(level_) {}
};

class AlarmCompare{
   public:
   bool operator()(const Alarm& below, const Alarm& above){
      return below.target > above.target; 
   }
};

struct Obs{
   priority_queue<Alarm,vector<Alarm>,AlarmCompare> pq;
   long long totaladded;
   Obs() : totaladded(0LL){}
};


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n,q;
   cin >> n >> q;
   
   int last = 0;
   
   vector<Member> members;
   int mid = 0;
   
   vector<Obs> obs;
   for(int k = 0; k <= n; k++) obs.push_back(Obs());
   
   for(int t = 0; t < q; t++){
      int qt;
      cin >> qt;
      
      if(qt == 1){
         int yin, on;
         cin >> yin >> on;
         long long y = (long long)(yin ^ last);
         
         vector<int> qi(on);
         for(int k = 0; k < on; k++){
            int qx;
            cin >> qx;
            qi[k] = qx ^ last;
         }
         
         Member member(mid,y,qi);
         
         //add alarms
         for(int k = 0; k < on; k++){
            long long tot = obs[qi[k]].totaladded;
            Alarm alarm(mid,tot + (y+on-1)/on,tot,0);
            
            //cout << "pushing: " << mid << endl;
            obs[qi[k]].pq.push(alarm);
            member.alarmstarts[k] = tot;
         }
         
         members.push_back(member);
         
         mid++;
            
      } else if(qt == 2){
         int x, yin;
         cin >> x >> yin;
         x ^= last;
         long long y = (long long)(yin ^ last);
         
         obs[x].totaladded += y;
         
         vector<int> answer;
         
         //cout << "pq size: " << obs[x].pq.size() << endl;
         if(!obs[x].pq.empty())
         //cout << "pq top: " << obs[x].pq.top().target << endl;
         
         while(!obs[x].pq.empty() && obs[x].pq.top().target <= obs[x].totaladded){
            Alarm alarm = obs[x].pq.top();
            obs[x].pq.pop();
            
            int id = alarm.id;
            //cout << "alarm id: " << id << " target: " << alarm.target << endl;
            
            if(alarm.level != members[id].alarmlevel) continue;
            members[id].alarmlevel++;
            
            //see how many were used in all observatories
            for(int k = 0; k < members[id].on; k++){
               long long used = obs[members[id].obs[k]].totaladded - members[id].alarmstarts[k];
               members[id].y -= used;
            }
            
            if(members[id].y <= 0){
               answer.push_back(id);
               continue;
            }
            
            //add new alarms
            int mon = members[id].on;
            for(int k = 0; k < mon; k++){
               long long tot = obs[members[id].obs[k]].totaladded;
               Alarm newalarm(id,tot + (members[id].y + mon - 1)/mon,tot,members[id].alarmlevel);
               obs[members[id].obs[k]].pq.push(newalarm);
               members[id].alarmstarts[k] = tot;
            }
            
         }
         
         last = answer.size();
         sort(answer.begin(),answer.end());
         cout << answer.size();
         for(int i : answer) cout << " " << i+1;
         cout << "\n";
      }
   }
   
   return 0;
}