import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val t = IntArray(4*n){0}
		//to call: v=1, tl = 0, tr = n-1
		fun getmax(v : Int, tl : Int, tr : Int, l : Int, r : Int) : Int{
			if (l > r)
				return 0;
			if (l == tl && r == tr) {
				return t[v];
			}
			val tm = (tl + tr) / 2;
			return max(getmax(v*2, tl, tm, l, min(r, tm)),getmax(v*2+1, tm+1, tr, max(l, tm+1), r));
		}

		//to call: v=1, tl = 0, tr = n-1
	   fun update(v  : Int, tl : Int, tr : Int, pos : Int, new_val : Int) {
			if (tl == tr) {
				t[v] = new_val;
			} else {
				val tm = (tl + tr) / 2;
				if (pos <= tm)
					update(v*2, tl, tm, pos, new_val);
				else
					update(v*2+1, tm+1, tr, pos, new_val);
				t[v] = max(t[v*2],t[v*2+1]);
			}
		}



		val array = f.readLine().split(" ").map{it.toInt()}

		val first = IntArray(n+1){-1}
		val second = IntArray(n+1){-1}

		for(k in 0 until n){
			if(first[array[k]] != -1) second[array[k]] = k
			else first[array[k]] = k
		}

		var answer = 1
		for(k in 0 until n){
			if(second[array[k]] == -1){				//only appeared once
				update(1,0,n-1,k,1)
				continue
			}
			if(second[array[k]] == k){
				//second occurance
				var prev = getmax(1,0,n-1,first[array[k]],second[array[k]])
				if(second[array[k]] - first[array[k]] >= 2) prev = max(prev,1)

				answer = max(answer,prev+2)
				update(1,0,n-1,first[array[k]],prev+2)
				//println("$k $answer")
			}
		}

		println(answer)

	}
}
