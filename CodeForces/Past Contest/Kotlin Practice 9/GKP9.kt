import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	fun ctoi(ch : Char) : Int{
		return ch.toInt()-'a'.toInt()
	}

	val n = f.readLine().toInt()

	val a = f.readLine().toCharArray()
	val b = f.readLine().toCharArray()

	val answer = Array<Char>(n){'?'}

	val odd = Array<Boolean>(n){false}

	for(k in 0 until n){
		odd[k] = ((ctoi(a[k]) + ctoi(b[k]))%2 == 1)
	}

	for(k in (n-1) downTo 0){
		var midint = ((ctoi(a[k]) + ctoi(b[k]))/2) + 'a'.toInt()
		if(odd[k] && ctoi(answer[k+1])>=13){
			midint++
		}
		answer[k] = midint.toChar()
	}

	for(k in 1 until n){
		if(odd[k-1]){
			//flip
			answer[k] = ((ctoi(answer[k])+13)%26 + 'a'.toInt()).toChar()
		}
	}

	println(answer.joinToString(""))




}
