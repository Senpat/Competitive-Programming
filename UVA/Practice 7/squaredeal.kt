import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val p = Array<Pair<Int,Int>>(3){
		val (a,b) = f.readLine().split(" ").map{it.toInt()}
		Pair(a,b)
	}

	var found = false
	for(k in 0 until 3){
		//check horizontal, side length is a[k].first
		var work = true
		var sidelength = p[k].second
		for(j in 0 until 3){
			if(k==j) continue
			if(p[j].first == p[k].first || p[j].second == p[k].first) sidelength += p[j].first+p[j].second-p[k].first
			else{
				work = false
				break
			}
		}

		if(work && sidelength == p[k].first){
			found = true
			break
		}

		work = true
		sidelength = p[k].first

		for(j in 0 until 3){
			if(k==j) continue
			if(p[j].first == p[k].second || p[j].second == p[k].second) sidelength += p[j].first+p[j].second-p[k].second
			else{
				work = false
				break
			}
		}

		if(work && sidelength == p[k].second){
			found = true
			break
		}


		val big = max(p[k].first,p[k].second)
		val small = min(p[k].first,p[k].second)
		val dif = big-small
		work = true
		sidelength = 0
		for(j in 0 until 3){
			if(k==j) continue
			if(p[j].first == dif || p[j].second == dif) sidelength += p[j].first + p[j].second - dif
			else{
				work = false
				break
			}
		}

		if(work && sidelength == big){
			found = true
		}
	}

	if(found){
		println("YES")
	} else {
		println("NO")
	}
}
