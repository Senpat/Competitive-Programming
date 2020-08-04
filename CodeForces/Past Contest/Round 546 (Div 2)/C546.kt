import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	val a = Array(n){f.readLine().split(" ").map{it.toInt()}.toIntArray()}
	val b = Array(n){f.readLine().split(" ").map{it.toInt()}.toIntArray()}

	val amaps = Array(n+m){HashMap<Int,Int>()}
	val bmaps = Array(n+m){HashMap<Int,Int>()}

	for(k in 0 until n){
		for(j in 0 until m){
			amaps[k+j][a[k][j]] = (amaps[k+j][a[k][j]] ?: 0) + 1
			bmaps[k+j][b[k][j]] = (bmaps[k+j][b[k][j]] ?: 0) + 1
		}
	}

	fun check(hmap1 : HashMap<Int,Int>, hmap2 : HashMap<Int,Int>) : Boolean{
		
		for(i in hmap1.keys){
			if(!hmap2.containsKey(i) || hmap2[i] != hmap1[i]) return false
		}
		return true
	}

	for(k in 0 until n+m){
		if(!check(amaps[k],bmaps[k])){
			println("NO")
			return
		}
	}

	println("YES")
}
