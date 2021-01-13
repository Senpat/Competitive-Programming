import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}
		val array = f.readLine().split(" ").map{it.toInt()}.toIntArray()

		val psum = IntArray(n+1){0}
		psum[0] = 0
		for(k in 1..n){
			psum[k] = psum[k-1]+array[k-1]
		}

		fun calc(x : Int) : Int{
			val numfree = (n-x)/m
			return psum[x+numfree]-psum[x]
		}

		var answer = 0
		for(k in 0 until n){
			answer = max(answer,calc(k))
		}

		println(answer)
	}
}
