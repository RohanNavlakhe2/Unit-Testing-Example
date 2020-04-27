package com.example.unittestingexample

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/** How to Run the Test:
 *  1.Click on the app box beside run button
 *  2.click on 'Edit Configuration'
 *  3.Now Click on the + icon at the top left of the dialog
 *  4.Now Click on the Android JUnit
 *  5.You will see a dialog inside that dialog select your testing class (ExampleUnitTest.kt) and select
 *    app module.You can give name to the test also, in this case it is (My Test)
 *  6. Click on apply and then ok
 *  7. finally change app to My Test beside run button
 *  8. Now Run the Test by Clicking on the run button
 */

//This line is required if you are using Mockito
//This line creates a mock object for the class which instance we're creating below under @Mock annotation
   //If your function is not dependent on any other method then you don't need to use 'Mockito'
   //In this Example our function makeFirstLetterCapital() is dependent on makeCopy() function that's why
   //we're using 'Mockito'.
@RunWith(MockitoJUnitRunner::class)
 class ExampleUnitTest {

    @Mock
    //This is the Mock Instance of the class StringOperation means we don't need to initialize it
    //because mockito is doing this for us
    private lateinit var stringOperation: StringOperation

    private lateinit var context:MainActivity

    //The function which we define under @Before annotation is executed before the function
    //written under @Test annotation that's why we do the setup work inside this function
    @Before fun setUp()
    {
        //In this case we're defining MainActivity instance so that we can call its methods
        context = MainActivity()
    }


    //functions defined under @Test annotation are called Test cases,We can create any number of Test cases.
    //Test Case:1
    @Test
     fun makeFirstLetterTest()
    {
        val input="coDInG iS FuN"
        val expectedOutput="Coding Is Fun Coding Is Fun"

        //This below line says that when makeCopy() function is called then what should it return.
            //in this case we want this function to return 2

        //Note that we're calling makeCopy() function on the Mock object created under @Mock annotation
        //not on the original instance of StringOperation because if we do so it will always return 0
        //because its original implementation returns 0 (You can consider that makeCopy() function
        // is not completed).

        //So by calling makeCopy() function on mock object we can return any value from makeCopy()
        //function and test our function makeFirstLetterCapital() against different-different values
        //returned by makeCopy().
        Mockito.`when`(stringOperation.makeCopy()).thenReturn(2)

        //assertEquals() function compares our expected result with actual result
           //It can take 4 arguments.
           // 1. Some Message 2. your expected output
           // 3. actual output (which is returned by the method call)
           // 4. the last one can be a float value which is precision
                  //For example if your expected output is 10 and the actual output is 9.99 but
                  //you are satisfied with 9.99 and now you don't want the test case to fail so
                  //as the last argument you can pass 0.1 which says JUnit that if therse is
                  //0.1 diffrence between expected and actual output then it's fine.So even if
                  //expected and actual output are different test case will be true.
          // --> But in this example we don't need to pass fourth argument.


        //Note that we're passing stringOperation "Mock" object
        assertEquals("Make First Letter Capital Test",
            expectedOutput,context.makeFirstLetterCapital(input,stringOperation))
    }

    //Test Case:2
    @Test
    fun makeFirstLetterTest2()
    {
        val input="i RUN vErY faST"
        val expectedOutput="I Run Very Fast I Run Very Fast I Run Very Fast"

        Mockito.`when`(stringOperation.makeCopy()).thenReturn(3)
        assertEquals("Make First Letter Capital Test",
            expectedOutput,context.makeFirstLetterCapital(input,stringOperation))
    }

    //Test Case:3
    //This test case will become fail because makeCopy() will return 3 but expected output has only 1 repetetion
    @Test
    fun makeFirstLetterTest3()
    {
        val input="do meDitaTion eveYDay"
        val expectedOutput="Do Meditation Everyday"

        Mockito.`when`(stringOperation.makeCopy()).thenReturn(3)
        assertEquals("Make First Letter Capital Test",
            expectedOutput,context.makeFirstLetterCapital(input,stringOperation))
    }
}
