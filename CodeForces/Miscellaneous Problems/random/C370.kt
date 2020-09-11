import java.io.*
import java.util.*
import kotlin.math.*
//lockout, not done
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val array = f.readLine().split(" ").map{it.toInt()}.toMutableList()

	val freq = IntArray(m+1){0}

	var maxfreq = 0
	for(k in 0 until n){
		freq[array[k]]++
		maxfreq = max(maxfreq,freq[array[k]])
	}

	array.sortWith(compareBy{freq[it]})

	val answer = mutableListOf<Pair<Int,Int>>()
	var num = 0
	for(k in 0 until n){
		if(array[k] != array[(k+maxfreq+n)%n]) num+=2
		answer.add(Pair(array[k],array[(k+maxfreq+n)%n]))
	}

	val sb = StringBuilder()
	sb.appendln(num)
	for(p in answer){
		sb.appendln("${p.first} ${p.second}")
		sb.appendln("${p.second} ${p.first}")
	}

	println(sb)
}
