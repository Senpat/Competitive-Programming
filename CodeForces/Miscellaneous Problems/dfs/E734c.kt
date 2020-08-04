import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))


}
import java.io.*
import java.util.*
import kotlin.math.*
//also wrong logic
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = arrayOf(1) + f.readLine().split(" ").map{it.toInt()}

	val adj = Array(n+1){mutableListOf<Int>()}

	for(k in 2..n){
		val (a,b) = f.readLine().split(" ").map{it.toInt()}
		adj[a].add(b)
		adj[b].add(a)
	}



}
