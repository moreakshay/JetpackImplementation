package moreakshay.com.tmdb

class Test {

    fun test(a: Int?){
        a.toString() //I am not forcing compiler to compile this line
        a!!.toString() // I am forcing compiler to execute this line, and it may fail if a is null
        // correct ?
        a?.toString() //
        // Kashyap

    }

}
