import java.io.*
import java.util.*

fun main(){
    val f = BufferedReader(InputStreamReader(System.`in`))
    val t = f.readLine().toInt()

    for(q in 1..t){
        val st = StringTokenizer(f.readLine())

        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val answer = a+b

        println(answer)
    }
}