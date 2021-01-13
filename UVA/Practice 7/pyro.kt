import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val input = mutableListOf<Int>()
	while(true){
		val i = f.readLine().toInt()
		if(i == -1) break
		input.add(i)
	}

	val n = input.size

	val answer = IntArray(n){0}

	val pow2 = IntArray(19){0}
	pow2[0] = 1
	for(k in 1 until 19){
		pow2[k] = pow2[k-1]*2
	}

	val hset = HashSet<Int>()
	for(k in 0 until 19){
		for(j in k+1 until 19){
			hset.add(pow2[k] + pow2[j])
		}
		hset.add(pow2[k])
	}

	val added = HashSet<Int>()
	for(k in n-1 downTo 0){
		for(adder in hset){
			if(input[k] xor adder in added) answer[k]++
		}

		added.add(input[k])
	}

	for(k in 0 until n){
		println("${input[k]}:${answer[k]}")
	}
}
