# Any-Angle-Path-Planning

Hello
----------------------------------------------------------------------------------------
TO LOAD GRID:
To load a new text file, for testing it must be located in resources and named grid0.txt
To generate a grid specified to 100 x 50 cells with random blocked cells of 10% the total cell count, run CreateInput.java

TO RUN APPLICATION:
Compile ArtificalIntel and run Assignment1.java
*A grid file named grid0.txt must be in the resources folder

UPON RUNNING:
A prompt asking for either 1 or 0 will appear to run A* or Theta* respectively
A window with a grid will then appear, clicking the grid will result in the running of the algorithm
and resizing the window allows for the path to appear. 
To run another algorithm you must rerun Assignment1.java and choose the appropriate option.
The algo will run and then a visualization of the graph should appear.
It will not focus on the start or goal so you will have to scroll to the respective vertices.
Clicking on the body of the window will result in a text file with huristic information for the path.
A console output will display if a path was found.