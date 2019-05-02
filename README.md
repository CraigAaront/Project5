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
      - uses gridBagLayout for components
      - jlabel set up for "enter hamming distance"
      - jTextField set up for the box that shows the distance
      - jSlider set up with 4 ticks, that only allow whole #'s 1-4
          - slider value is reflected in jtextfield
          - actionListener set up with slider linked to text field
      - jButton set up "show station"
          - actionlistener set up so that if pressed, it shows all stations within the hammingDistance
            selected by the slider
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
  

