import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toInt()}

	val lis = mutableListOf<MutableList<Int>>()
	lis.add(mutableListOf<Int>())
	lis[0].add(0)
	val par = IntArray(n){-1}

	for(k in 1 until n){
		//binary search
		var l = 0
		var r = lis.size-1
		var ans = -1

		while(l <= r){
			val mid = l + (r-l)/2
			if(array[k] <= array[lis[mid].last()]){
				ans = mid
				r = mid-1
			} else {
				l = mid+1
			}
		}

		if(ans != -1 && array[k] == array[lis[ans].last()]){
			continue
		}

		if(ans == -1){
			lis.add(mutableListOf<Int>())
			lis.last().add(k)
			par[k] = lis[lis.size-2].last()
		} else {
			lis[ans][lis[ans].size-1] = k
			if(ans != 0){
				par[k] = lis[ans-1].last()
			}
		}
	}

	var answer = IntArray(n){1}

	var i = lis.last().last()
	while(i != -1){
		answer[i] = 0
		i = par[i]
	}

	//check if 1s are decreasing
	var last = Int.MAX_VALUE
	var fail = false
	for(k in 0 until n){
		if(answer[k] == 1 && array[k] >= last){
			fail = true
			break
		}
		if(answer[k] == 1){
			last = array[k]
		}
	}

	if(fail && lis.size == 1){
		//special case, something like 5 4 3 3 2
		//must be a nonincreasing sequence, check for at most repeat
		var repeat = 0
		var repeati = -1
		for(k in 1 until n){
			if(array[k] == array[k-1]){
				repeat++
				repeati = k
			}
		}

		if(repeat == 1){
			answer = IntArray(n){1}
			answer[repeati] = 0
			println("YES")
			println(answer.joinToString(" "))
		} else {
			println("NO")
		}

	} else if(fail){
		println("NO")
	} else {
		println("YES")
		println(answer.joinToString(" "))
	}

	//println(par.joinToString(" "))

}
