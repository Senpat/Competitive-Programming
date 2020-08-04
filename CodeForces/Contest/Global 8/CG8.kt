import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val answer = mutableListOf<Pair<Int,Int>>()

	fun makebox(x : Int, y : Int){
		answer.add(Pair(x+1,y+1))
		answer.add(Pair(x+1,y))
		answer.add(Pair(x+1,y-1))
		answer.add(Pair(x,y-1))
		answer.add(Pair(x,y+1))
		answer.add(Pair(x-1,y+1))
		answer.add(Pair(x-1,y))
	}

	answer.add(Pair(-1,-1))
	makebox(0,0)

	for(k in 1..n){
		makebox(k*2,k*2)
	}

	println(answer.size)
	val sb = StringBuilder()
	for(k in 0 until answer.size){
		sb.appendln("${answer[k].first} ${answer[k].second}")
	}
	println(sb)

}
