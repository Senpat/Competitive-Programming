import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(FileReader("A.in"))
	val out = PrintWriter(BufferedWriter(FileWriter("A.out")))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val i = f.readLine().toCharArray()
		val o = f.readLine().toCharArray()

		val answer = Array(n){CharArray(n)}

		fun check(s : Int, e : Int) : Boolean{
			if(s==e) return true
			if(s > e){
				for(k in s downTo e+1){
					if(o[k] == 'N' || i[k-1] == 'N') return false
				}

			} else {
				for(k in s until e){
					if(o[k] == 'N' || i[k+1] == 'N') return false
				}

			}

			return true
		}

		for(k in 0 until n){
			for(j in 0 until n){
				if(check(k,j)) answer[k][j] = 'Y'
				else answer[k][j] = 'N'
			}
		}

		out.println("Case #$q:")
		for(k in 0 until n) out.println(answer[k].joinToString(""))
	}

	out.close()
}
