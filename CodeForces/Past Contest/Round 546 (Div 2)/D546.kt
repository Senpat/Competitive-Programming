import java.io.*
import java.util.*
import kotlin.math.*
//WA fail
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	val array = f.readLine().split(" ").map{it.toInt()}

	val indexof = IntArray(n+1)
	for(k in 0 until n){
		indexof[array[k]] = k
	}

	val utov = Array(n+1){HashSet<Int>()}
	val vtou = Array(n+1){HashSet<Int>()}

	for(k in 0 until m){
		val (a,b) = f.readLine().split(" ").map{it.toInt()}

		utov[a].add(b)
		vtou[b].add(a)
	}

	if(n==1){
		println(0)
		return
	}

	//returns highest index it goes to
	fun getlongestchain(v : Int) : Int{
		var i = indexof[v]
		while(i < n-2){
			//check i+1
			if(utov[v].contains(array[i+1])) i++
			else break
		}
		return i
	}

	val peaks = IntArray(n){0}

	for(v in vtou[array[n-1]]){
		peaks[getlongestchain(v)]++
		//println("$v ${getlongestchain(v)}")
	}

	var sumencountered = peaks[n-2]
	var i = n-1

	while(sumencountered > 0 && i >= 1){
		i--
		if(i-1 >= 0) sumencountered += peaks[i-1]
		sumencountered--
	}

	val answer = n-1-i
	println(answer)


}
