# CS102 ~ Personal Log page ~
****
## Övgüm Can Sezen
****

On this page I will keep a weekly record of what I have done for the CS102 group project. 
This page will be submitted together with the rest of the repository, in partial fulfillment of the CS102 course requirements.

### ~ 24.04.2020 - 01.05.2020 ~
	-Decided who does which part of the project. Oğuz & I are doing the 
	part calculating the route. Harun is doing the Android GUI, Saad is doing
	the GUI for computers. Cenk & Can are doing the map.
	
	-Read some pdf's to help us decide what we were going to do to calculate
	an approximation. We decided that we would do Nearest Neighbor since it
	seemed the most straight up one. We read that it doesn't always give an 
	acceptable route so we decided to make it better by running the 2-opt on
	it. 

	-We decided that we should have four objects; a point (with x&y values),
	a list which stores the points, a line (which has start & end points
	and a length) and a list which stores the lines.

	-Oğuz created an interface for us to test the results of our algorithms,
	I tried to do the nearest neighbor thing, and I think I got the logic
	of things right, but it had bugs in it and didn't work properly.
	I shouldn't have done the monkeys with typwriters thing. I will prepare
	properly for every little thing I will write from now on.

	-Oğuz fixed the nearest neighbor, the general code was organized terribly 
	and lacked comments. We fixed it all.
	
	-We found some values whichs optimal length is known and tested the NN. 
	It was alright it was a step forward from random but it needs to get
	better. We decided that we should definitely implement 2-opt and even
	maybe something else (maybe Christofides) to generate the initial tour.

### ~ 02.05.2020 - 08.05.2020 ~
	-Everyone showed their work in a meeting. We are all in the same English
	class and had work due in a short time so we postponed the integration.
	
	-Fixed the variables names from stuff like x,y to meaningful ones.
	We decided to not implement Christofides for now, as we thought we should 
	first put everything together and make it work.
	
	-Added som methods to test better we were afraid that things would take
	too much time. However, it was not the case, NN was pretty quick.
	We only tested for 900 something points. It doesn't work due to memory
	after 10.000 points. We decided Ankara doesn't have 10.000 points to visit
	so we just went with what we have.
	
	-I started writing the 2-opt algorithm. It went well, better than
	my trial of NN as I knew what should I do. It gave a result but it
	was actually longer than the only NN version. Tried to find the bug
	in the 2-opt couldn't do it.

	-Had a meeting with Harun and Oğuz, we checked what was wrong with it.
	We found out that the problem was in the distance calculating method.
	We forgot to empty the shopping cart so the new, sorter distance was added
	to the first one and it returned that. After we fixed that it was working
	and made the route usable as it was meaningful now.

### ~ 09.05.2020 - 16.05-2020 ~

	-We didn't do anything for 2-3 days.

	-After that, Harun finished his part and started woring on implementing 
	the algorithm thing to the whole GUI and Map thing Harun had. Added the
	necessary comments needed. The methods were working, but some were a
	little broken in terms of the way they work, fixed them as mush as we can.
	Oğuz added some methods to make the integration easier.

	-Harun, Oğuz & I met again to talk about how the pc side of the project
	was going and who were going to help which part of it. (Map and GUI). 
	Oğuz was going to help the GUI and I was going to help the map.
	
### ~ 17.05.2020 - 24.05.2020 ~
	
	-Talked with Can & Cenk, who were previously working with the map to 
	decide how the relationship of the map will be done with the algorithm.
	Javascript was supposed to create a txt files with the selected points.
	Algorithm were supposed to read it, do it's thing an return a txt file of
	ordered points. Javascript was supposed to read it and add it to the line
	drawing method to create the route.
	
	-Turns out it is not very prefferable and easy to access local files 
	using javascript so we needed to  find an alternative way. The format
	the txt files were supposed to be like;
	x_1 y_1
	x_2 y_2
	.
	.
	.
	But it was like;
	(x_1, y_1)
	(x_2, y_2)
	.
	.
	.
	We didn't want to write the whole filereading methods over so we just 
	fixed the thing.

	-We added the part which was supposed to edit the javascript to the UI 
	itself. It directly reads the list of points returned and changes the
	the file itself.

	-Harun, Oğuz, Cenk & I had a meeting. We worked on putting the pieces
	together, I think it went well as it was working. It had some minor issues
	to be solved like the markers were not visible in the map with the route
	drawn.

	-Oğuz, Can and I met and continued the integration. Solved some parts of
	the problem. UML of the algorithm was wrong so we did it from the start.
	 