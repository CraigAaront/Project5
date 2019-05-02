# Project5
![](UML.jpg)
## Problem Solving Approach:
1. After looking at the demo, I determined what
would need to be done for GUI, what calculations would need to be made, and how to set it all up
    - Main method would only construct the frame
    - Constructor would make the GUI and implement action listeners
    - Calculations occur in separate classes
    - AsciiAverage calculation has its own class
    - finding hamming distances and stations with the same average has its own class


### Analysis of Implemented Code:
1. Main Class:
    - Created a private string variable that would hold the String
        STIDlist
    - Constructor constructs the GUI frame
    - setUpGui creates all the components of the GUI
      - 
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
  

