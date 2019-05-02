# Project5
![](UML.jpg)
## Problem Solving Approach:
1. After looking at the main and MesoStation classes, I determined what
would need to be done for the abstract and inherited classes
    - Inherit class would need to extend the abstract class
    - Inherit class would only have a constructor and implementation of 
	the abstract methods
    - Abstract class would hold abstract methods
    - Abstract class would have calAverage and letterAverage
        methods


### Analysis of Implemented Code:
1. MesoInherit class:
    - Created a private string variable that would hold the String
        STID
    - Constructor used a mesostation object as a parameter and
        assigned the string value (STID) to the declared private string
        variable
    - calAverage method returned a 3 value int array with necessary
        values
      - for loop summed up the ASCII values of the 4 letters
                by casting to char and int
      - average variable then found the average of the 4
                letters
      - calAverage[0] is the ceil, so math.ceil was used on
                average
      - calAverae[1] is floor, so math.floor was used on
                average
      - calAverage[2] is the average, if greater than 0.5 it
                was ceil, less than 0.5 was floor so math.round was used
    - letterAverage method returned a char value that was the ASCII
        equivalent int value
      - used same sum and average variables and for loop to
                find the ASCII total as in calAverage
      - used math.round to find the average
      - the int value was then casted to char to find the
                ASCII equivalent
      - returned the char value that was gained by casting
2. MesoAbstract class:
    - Has abstract methods used  by inherit
  

