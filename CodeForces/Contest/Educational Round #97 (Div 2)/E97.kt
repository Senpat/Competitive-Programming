import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val inarray = f.readLine().split(" ").map{it.toInt()}

	val array = IntArray(n){j -> inarray[j]-j}

	fun calc(l : Int, r : Int, min : Int, max : Int) : Int{
		//finds longest increasing subsequence within indeces in [l,r] and with valuees in [min,max]
		if(r < l) return 0
		if(r == l){
			if(array[l] >= min && array[l] <= max) return 0
			return 1
		}

		val lis = mutableListOf<Int>()

		fun bs(x : Int) : Int{
			var bl = 0
			var br = lis.size-1
			var ans = -1
			while(bl <= br){
				val mid = bl + (br-bl)/2
				if(lis[mid]>x){
					ans = mid
					br = mid-1
				} else {
					bl = mid+1
				}
			}
			return ans
		}

		for(k in l..r){
			if(array[k] < min || array[k] > max) continue
			if(lis.size==0){
				lis.add(array[k])
			} else {
				if(array[k] < lis[0]) lis[0] = array[k]
				else if(array[k] >= lis[lis.size-1]){
					lis.add(array[k])
				} else {
					//binary search
					lis[bs(array[k])] = array[k]
				}
			}
		}

		//println("$l $r $min $max ${lis.size}")
		return r-l+1-lis.size

	}

	if(m==0){
		println(calc(0,n-1,Int.MIN_VALUE,Int.MAX_VALUE))
	} else {
		val fixed = f.readLine().split(" ").map{it.toInt()-1}
		var fail = false
		var answer = 0
		answer += calc(0,fixed[0]-1,Int.MIN_VALUE,array[fixed[0]])
		for(k in 1 until m){
			if(array[fixed[k]] < array[fixed[k-1]]){
				fail = true
				break
			}
			answer += calc(fixed[k-1]+1,fixed[k]-1,array[fixed[k-1]],array[fixed[k]])

		}
		if(fail){
			println(-1)
		} else {
			answer += calc(fixed[m-1]+1,n-1,array[fixed[m-1]],Int.MAX_VALUE)
			println(answer)
		}
	}
}
