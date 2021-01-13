import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	var (n,x,m) = f.readLine().split(" ").map{it.toLong()}

	val values = mutableListOf<Long>()
	values.add(x)

	val hmap = HashMap<Long,Int>()
	hmap.put(x,0)
	var start = -1

	while(true){
		val add = (values[values.size-1]*values[values.size-1])%m
		//println(add)
		if(hmap.containsKey(add)){
			start = hmap.get(add)!!
			break
		}
		hmap.put(add,values.size)
		values.add(add)
	}

	if(n <= values.size){
		var answer = 0L
		for(k in 0 until n.toInt()){
			answer += values[k]
		}
		println(answer)
	} else {

		var answer = 0L
		for(k in 0 until start){
			answer += values[k]
		}

		//println(answer)

		var sum = 0L

		for(k in start until values.size){
			sum += values[k]
		}

		//println(sum)
		n-=start
		answer += sum*(n/(values.size-start))

		var remain : Long = 0L
		for(k in 0 until ((n%(values.size-start)))){
			remain += values[start+k.toInt()]
		}

		//println(remain)

		answer += remain
		println(answer)

	}
}
