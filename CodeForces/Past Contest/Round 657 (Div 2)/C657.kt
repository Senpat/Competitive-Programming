import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		if(q > 1) f.readLine()
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		val array = Array<Pair<Long,Long>>(m){
			val (a,b) = f.readLine().split(" ").map{it.toLong()}
			Pair(a,b)
		}

		//biggest a to smallest a
		val sorta = Array<Pair<Long,Long>>(m){j -> array[j]}.sortedWith(compareBy({-it.first}))

		val psum = LongArray(m+1){0L}

		psum[0] = 0L
		for(k in 0 until m){
			psum[k+1] = psum[k] + sorta[k].first
		}


		fun find(x : Long) : Int{
			var l = 0
			var r = m-1

			var ans = -1
			while(l <= r){
				val mid = l + (r-l)/2
				if(sorta[mid].first >= x){
					ans = mid
					l = mid+1
				} else {
					r = mid-1
				}
			}

			return ans

		}


		var answer = 0L
		for(k in 0 until m){
			//max out this one
			//find number of pairs whose a > this b

			val i = find(array[k].second)
			//println("$k $i")
			if(i == -1){
				//no a's are bigger than this b
				answer = max(answer,array[k].first + (n-1).toLong() * array[k].second)
			} else if(i+1 >= n){
				answer = max(answer,psum[n])
			} else {
				//i+1 use a

				if(array[k].first < sorta[i].first){

					answer = max(answer,psum[i+1] + array[k].first + (n-(i+1)-1).toLong() * array[k].second)
					//println("${psum[i+1] + array[k].first + (n-(i+1)-1).toLong() * array[k].second}")
				} else {
					answer = max(answer,psum[i+1] + (n-(i+1)).toLong() * array[k].second)
					//println("${psum[i+1] + (n-(i+1)).toLong() * array[k].second}")

				}


			}
		}

		println(answer)


	}
}
