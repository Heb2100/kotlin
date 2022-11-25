package com.example.marathon

import android.text.method.MetaKeyKeyListener

//  2-1. lamda
//  람다란 value 처럼 다룰 수 있는 익명함수이다.
//  1) val 처럼 메소드의 파라미터로 넘겨줄 수 있다.
//  2) return 값으로 사용할 수가 있다.
//  3) val lamdaname : Type = {arglist -> codebody}

fun main(){
    println(square(12))
    println(nameage(25, "HB"))
    val a = "HB said"
    val b = "KK said"
    println(a.pizzaisgreat())
    println(b.pizzaisgreat())
    println(introducemyself(23, "KK"))
    println(calculategrade(10))

    val lamda : (Double) -> Boolean = {number : Double ->
        number == 4.3234
    }
    println(invokelamda(lamda))
    println(invokelamda { true })
    println(invokelamda ({it < 3.22}))
    println(invokelamda {it < 3.22})

}



val square : (Int) -> Int = { number : Int -> number*number}

val nameage : (Int, String) -> String = {age : Int, name : String ->
    "my name is $name and I'm $age years old"
}
//  2-2. 확장함수
val pizzaisgreat : String.() -> String = {
    this + "pizza is great!"
}

fun introducemyself(age : Int, name : String) : String{
    val introdemo : String.(Int) -> String = {"I am $this and $it years old"}
    return name.introdemo(age)
}

val calculategrade : (Int) -> String = {
    when(it){
        in 0..40 -> "fail"
        in 40..80 -> "okay"
        in 80..100 -> "perfect"
        else -> "error"
    }
}

fun invokelamda(lamda : (Double) -> Boolean): Boolean {
    return lamda(5.323)
}