package com.example.unittestingexample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


//First add the dependencies for Junit and Mockito
    //If you are creating a new project then Junit dependency will be there by default
//Where to write Test Cases:
   //--> you write test cases inside java->package name (Test)->ExampleUnitTest.kt file
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //This method takes some String and makes its each word's first letter uppercase and remaining letters
    //lower case
    fun makeFirstLetterCapital(str:String,stringOp:StringOperation):String
    {
        var resultStr:String=""
        var finalStr:String=""
        val capitalizedList:List<String> =str.split(" ")
        capitalizedList.forEach {
             resultStr+=it[0].toUpperCase()+it.substring(1 until it.length).toLowerCase()+" "
        }

        //Suppose makeCopy() function of StringOperation class takes some input from the user and returns that to
        //this function and this function makes those many repetetion of the string
        // --> Here We don't need to worry about the makeCopy() functions implmentation because suppose
        //     makeCopy() function has been developed by some other developer and you have to test only
        //     the function made by you which is makeFirstLetterCapital() in this case.
        // --> But your function result is dependent on makeCopy() function that's why we use "Mockito"
        //     which mocks that function.
        //-->  Means we don't care about what makeCopy() function's implementation is rather we want
        //     the value which makeCopy() function returns.So using Mockito we can generate those
        //     values that's why we don't care about whether the function on which our function
        //     is dependent is ready or not or it is doing its duty properly or not.


        //Here the implementation of makeCopy() function is returning 0 but while making Test Cases
        //inside ExampleUnitTest class using "Mockito" we can decide what value it should return.
        //By doing this we can test the output of our function (makeFirstLetterCapital()) based on the different-
        //different values returned by makeCopy() function
        val noOfCopy:Int = stringOp.makeCopy()

        for (i in 0 until noOfCopy)
            finalStr+=resultStr
        return finalStr.trim()
        /**
         * Check ExampleUnitTest.kt also
         */
    }
}
