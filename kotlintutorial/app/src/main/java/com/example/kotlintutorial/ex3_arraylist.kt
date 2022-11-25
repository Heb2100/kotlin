package com.example.marathon


//  array,list
//  [array] 크기가 정해져있고 처음 할당한다.
//  [list] 크기가 안정해져있다.
//         1. list(수정불가능)  2. mutablelist(수정가능)
//

fun main(){
    val array = arrayOf(1, 2, 3)
    val list = listOf(1, 2, 3)

    val array2 = arrayOf(1, "h", 2.3f)
    val list2 = listOf(1, "d", 2.3)
//    둘 다 다양한 자료형을 담을 수 있다.

    array[0] = 4
//    list[0] = 3
//    list는 수정이 기본적으로 안된다.
    println(array.get(0))
    println(array)

    val arraylist = arrayListOf<Int>()
    arraylist.add(10)
    arraylist.add(20)
    println(arraylist.get(1))
}
