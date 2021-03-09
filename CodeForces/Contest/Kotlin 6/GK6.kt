import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val numdivs = IntArray(n+1){0}
	val nummuls = IntArray(n+1){0}

	for(k in 1..n){
		for(j in (2*k)..n step k){
			numdivs[j]++
			nummuls[k]++
		}
	}

	val array = (1..n).sortedWith(compareBy{numdivs[it]-nummuls[it]}).reversed()

	val answer = IntArray(n){0}
	answer[0] = numdivs[array[0]]-nummuls[array[0]]

	for(k in 1 until n){
		answer[k] = answer[k-1] + numdivs[array[k]]-nummuls[array[k]]
	}

	println(answer.joinToString(" "))
}
