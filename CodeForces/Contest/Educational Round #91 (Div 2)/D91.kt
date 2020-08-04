import java.io.*
import java.util.*
import kotlin.math.*
//read problem wrong
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val (x,dl,y) = f.readLine().split(" ").map{it.toLong()}
	val d = dl.toInt()

	val a = f.readLine().split(" ").map{it.toInt()}
	val b = f.readLine().split(" ").map{it.toInt()}

	val indexofa = IntArray(n+1)
	for(k in 0 until n) indexofa[a[k]] = k

	val bset = b.toHashSet()

	fun checkpossible() : Boolean{
		if(m > n) return false

		for(k in 1 until m){
			if(indexofa[b[k]] < indexofa[b[k-1]]) return false
		}

		//find the gap that the highest number is a part of
		if(bset.contains(n)) return true

		var i = indexofa[n]
		//go left until you reach a b
		var l = i
		while(l > 0 && !bset.contains(a[l-1])) l--
		var r = i
		while(r < n-1 && !bset.contains(a[r+1])) r++

		return r-l+1 >= d

	}

	if(!checkpossible()){
		println(-1)
		return
	}

	//for every gap
	var gapstart = -1
	var answer = 0L
	for(k in 0 until n){
		if(bset.contains(a[k])){
			if(gapstart == -1) continue
			//delete from gapstart to k-1
			if(indexofa[n] >= gapstart && indexofa[n] < k){
				val dist = k-gapstart
				answer += min((dist-d).toLong()*y + x, (dist/d).toLong()*x + (dist%d).toLong()*y)


			} else {
				val dist = k-gapstart
				answer += min(dist.toLong()*y,(dist/d).toLong()*x + (dist%d).toLong()*y)
			}
			gapstart = -1

		} else {
			if(gapstart == -1) gapstart = k
		}
	}

	if(gapstart != -1){
		if(indexofa[n] >= gapstart){
			val dist = n-gapstart
			answer += min((dist-d).toLong()*y + x, (dist/d).toLong()*x + (dist%d).toLong()*y)

		} else {
			val dist = n-gapstart
			answer += min(dist.toLong()*y,(dist/d).toLong()*x + (dist%d).toLong()*y)
		}
	}

	println(answer)
}
