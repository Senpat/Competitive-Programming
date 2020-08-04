import java.io.*
import java.util.*
import kotlin.math.*
//read problem wrong
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val (x,dl,y) = f.readLine().split(" ").map{it.toLong()}
	val d = dl.toInt()

	val array = f.readLine().split(" ").map{it.toInt()}
	val barray = f.readLine().split(" ").map{it.toInt()}

	val indexofa = IntArray(n+1)
	for(k in 0 until n) indexofa[array[k]] = k

	val bset = barray.toHashSet()

	fun checkpossible1() : Boolean{
		if(m > n) return false

		for(k in 1 until m){
			if(indexofa[barray[k]] < indexofa[barray[k-1]]) return false
		}

		return true

	}

	if(!checkpossible1()){
		println(-1)
		return
	}

	var answer = 0L
	for(k in 0..m){
		var a = -1
		var b = -1
		var maxab = -1
		if(k == 0){
			a = -1
			b = indexofa[barray[k]]
			maxab = barray[k]
		} else if(k == m){
			a = indexofa[barray[k-1]]
			b = n
			maxab = barray[k-1]
		} else {
			a = indexofa[barray[k-1]]
			b = indexofa[barray[k]]
			maxab = max(barray[k-1],barray[k])
		}

		val dist = b-a-1

		var numabove = 0
		for(j in (a+1)..(b-1)){
			if(array[j] > maxab) numabove++
		}

		if(numabove > 0 && dist < d){
			println(-1)
			return
		}

		if(numabove > 0){
			//need at least 1 fireball
			answer += min((dist-d).toLong()*y + x,(dist/d).toLong()*x + (dist%d).toLong()*y)

		} else {
			answer += min(dist.toLong()*y,(dist/d).toLong()*x + (dist%d).toLong()*y)
		}


	}

	println(answer)

}
