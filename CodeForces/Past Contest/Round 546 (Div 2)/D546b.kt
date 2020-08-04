import java.io.*
import java.util.*
import kotlin.math.*
//semi-t
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	val array = f.readLine().split(" ").map{it.toInt()}

	val indexof = IntArray(n+1)
	for(k in 0 until n){
		indexof[array[k]] = k
	}

	val utov = Array(n+1){HashSet<Int>()}

	for(k in 0 until m){
		val (a,b) = f.readLine().split(" ").map{it.toInt()}

		utov[a].add(b)
	}

	if(n==1){
		println(0)
		return
	}

	val needflip = HashSet<Int>()
	needflip.add(array[n-1])

	fun check(set : HashSet<Int>) : Boolean{
		var count = 0
		for(i in set){
			if(i in needflip) count++
		}

		return count == needflip.size
	}

	for(k in n-2 downTo 0){
		if(!check(utov[array[k]])){
			needflip.add(array[k])
		}
	}

	val answer = n-needflip.size
	println(answer)




}
