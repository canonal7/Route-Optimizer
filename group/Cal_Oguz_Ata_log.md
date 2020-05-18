# CS102 ~ Personal Log page ~
****
## Oğuz Ata Çal
****

On this page I will keep a weekly record of what I have done for the CS102 group project. This page will be submitted together with the rest of the repository, in partial fulfillment of the CS102 course requirements.

### ~ 27.04.2020 - 03.05.2020 ~
	
23.04.2020
	-Had a group meeting and decided who whould do what part of the project.
	-Övgüm and I are doing the algorithm part.	

24.04.2020
	-I made some of the classes we need and made a GUI that draws the path for a given array of points and also calculates the total distance of this path.
	-I did this to test and implement the algorithm easier by checking the path that the algoritm will create visiually.
	-Övgüm and I also talked about how we were going to do the algorithm and decided on making a "line" class that has an start and enpoint as well as a distance.
	-I sent this GUI to övgüm for him to work on the algorithm.

29.04.2020 
	-We had a meeting with harun and övgüm, övgüm had done the algorithm on his part, but it was not working as intended and the code was a little bit of a mess
	 so after trying to understand what was going wrong, we decided to write the neigherest neighbor algorithm again. Hopefully more organized

30.04.2020
	-Wrote the algorithm to nearest neighbor in a kind of efficient way 

1.05.2020
	-Added some comments
	-Tested some cases with övgüm and it seems to be working

02.05.2020
	- We had a meeting and showed each other what we did, but since we all had eng102 deadlines for the next 2 days we decided we would meet and put the pieces together on a later date

### ~ 04.05.2020 - 11.05.2020 ~

04.05.2020
	-Wrote some comments and made some convenience changes like changing variable names to more meaningful ones
	-Uploaded it to git-hub and sent it to övgüm for him to write the cristophides algorithm ( after which we decided not to write for now )

05.05.2020
	-Separated the GUI from the algorithm to a separate project
	-Designed a method that reads nodes from a txt file so we can do some testing with bigger number of nodes	
	-Added a timer to see how much the algorithm takes to complete

06.05.2020
	-Tried to write a code for randomized nearest neighbor but it got very complicated and decided to delete it for now
	-Tested some cases from 29 to 10000 nodes and kept track of the calculations wiht percentage errors in a results.txt file
	-Uploaded it to git-hub for övgüm to work on 2-opt

07.05.2020
	-group meeting: everyone was present, looked at what everyone did, how to connect the map and the algorithm, thought about some of the problems about the map
	-we debugged övgüms 2-opt algorithm after he wrote it, and turns out the algorithm was correct but the distance calculator method was wrong

### ~ 12.05.2020 - 19.05.2020 ~

12.05.2020
	-started integrating 2-opt that övgüm made into the just algorithm project
	-started creating java docs like övgüm
	-fixed some of the methods of övgüm ( return types, parameters, static )
	-added some convenience methods

14.05.2020
	-met up with harun an övgüm and discussed ideas about the phone GUI, ways to improve it and such
	-planned a meeting tomorrow to discuss pc ui

15.05.2020
	-added interfaces
	-extracted a jar file
	-ran some tests with 2-opt algorithm and updated results.txt
	-uploaded it to git-hub

16.05.2020
	-copied what saad did with java fx on swing so putting the map is easier because can and cenk designed the map in a jpanel
	-added background and some aesthetics

17.05.2020
	-Had a meeting with övgüm, cenk and harun. We worked on the pc ui and started integrating the map on it

18.05.2020
	-Had a meeting with övgüm and can. We continued integrating the map on the pc ui and fixed some of the issues.


