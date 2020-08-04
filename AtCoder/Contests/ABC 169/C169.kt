import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))
	/*
	val line = f.readLine().split(" ")
	println(line)
	val a = line[0].toLong()
	println(a)
	System.out.flush()
	val b = (line[1].toDouble()*100.0).toLong()
	println(a*b/100)
*/
/*
	val st = StringTokenizer(f.readLine())
	val a = st.nextToken().toLong()
	val b = (st.nextToken().toDouble()*100).toLong()
	println(a*b/100)*/

	val (a,b) = f.readLine().split(" ").map{it.toBigDecimal()}
	val prod = a.times(b)
	val string = prod.toPlainString()
	//println(string)
	//System.out.flush()
	if(string.indexOf('.') != -1){
		println(string.substring(0,string.indexOf('.')))
	} else {
		println(string)
	}
}
