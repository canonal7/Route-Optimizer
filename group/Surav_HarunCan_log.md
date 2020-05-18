# CS102 ~ Personal Log page ~
****
## Harun Can Surav
****

On this page I will keep a weekly record of what I have done for the CS102 group project. This page will be submitted together with the rest of the repository, in partial fulfillment of the CS102 course requirements.

### ~ 04.27.2020 - 05.03.2020 ~
Today I've created an Android Studio Project which is the foundation of mobile app.
I've added Main Screen, Settings and Enter Locations "Activities", which are the panels which the user will interact with.
I've decided to edit landscape layout in the final product because it borns from vertical layout but becomes completely disconnected from it after being born.
I've learned how to add listeners to buttons in Android Studio (touch more visual than that of Javas).
I've learned how to manipulate the manifest file of an android app.
I've played with the layout in order to improve the user experience.

// 04.27.2020
I've added an interface where the user can enter new locations by its X and Y coordinates, which will remain in the final product but probably be used mainly for testing purposes.
I've learned how to edit TextView's from java code in Android Studio.
I've tested whether X, Y Coordinate boxes work by printing the locations to terminal. 

// 04.28.2020
I helped Övgüm and Oğuz on algorithm, which they completely write from scratch after.

// 05.03.2020
All the group members (except Saad because he was sleeping) demonstrated what they were doing.

### ~ 05.04.2020 - 05.10.2020 ~

// 05.05.2020
I've learned how to read the context of a file without using a command prompt on Android Studio(by using InputStream and assets folder) and on PC Java(by using Scanner)
I've grasped the idea of catching errors by using try {} / catch {}.
I've decided to store map location into a text file which can be reached from anywhere.

// 05.08.2020
I've imported the most current version of the Route Optimizer algorithm created by Övgüm and Oğuz by creating a .jar file of their project.
Implemented their algorithm into the android app, now the app can show the order of the locations but routes are still not available.
Learned how to show an array of locations visually by using a Scroller class (basically a view that shows the contents of an array) and an ArrayAdapter.

// 05.09.2020
Added the Edit menu where the user can see the array of locations that the user entered as well as the ability to removing unwanted locations with a "Remove" button.
I've connected the text file responsible for just addition of locations into the edit section by using FileInputStream which reads the content of a file and a FileOutputStream which can write on a file by tokenizing the wanted string(getBytes).

### ~ 05.11.2020 - 05.17.2020~

// 05.11.2020
Changed the app icon to a funny looking image.

// 05.13.2020
App can now create the necessary text files if its the first time the app starts or user manually erased data.
Learned how to implement a Google Maps map into the app from reading Google's own documentation and watching some helpful videos.
App can now optimize the locations in the Enter Locations screen and then upload the locations in a route-optimized order to a text file named optimizedList.txt.
Added the Google Maps interface into the app, user can now see the locations on a world map graphically but the actual lines between the nodes are not done yet.
Updated the README and updated the gradle build version to 3.6.3 from 3.6.1 as it was recommended by the IDE.
Finally, the app can draw visual routes between the Markers, completing the core function of the app.
Customized the route color to black to learn the customization process.

// 05.14.2020
Created another Map for user to select locations on the map, which considerably eases use and the user is no longer needs to know the coordinate system to use the app.
Select From Map can now overwrite and add a new location to locationList.txt file which used to be accessible only from Edit screen and Enter Coordinates screen.
User can add new locations from the map by dragging a draggable marker by pressing on it for a couple of seconds, and then draging it to the wanted location.
Implemented the functionality of pinging users live location into the Route screen from Google, which further improves the functionality of the application by turning the Routes screen into a somewhat decent navigation screen.
The program can now ask for the permission to access users location data provided by GPS and Wi-Fi data which is more accurate than using only the Base Station data.
Corrected some bugs due to language (e.g: Implements - İmplements & ındogo - indigo)

// 05.15.2020
Deleted the terminal outputs used for creation of the app and are now rendered useless.

// 05.16.2020
Added a fragment for user to edit the selected locations more easily by either showing the selected location on the map or selecting the location directly off the map.
Recently added locations can now be seen at Select From Map screen, eliminating the chance of adding a similar or same location.
Changed the general theme to a less flashy blue-ish theme, you will be missed dizzy background.
Custom map that fits better with the general theme of the map.
Custom markers that looks better on the new map design.
Cleared up the code and created various methods so the source can look neat and tidier than before.
Added a short tutorial section for the user to grasp the idea of how to user the app.
Modified the "extends XClass" type to AppCompatActivity in the classes which has a map so the back button on the top of the activity is visible and functional.

// 05.17.2020
Updated the color of the text of Select button in SelectFromMapActivity from black to white so that the particular screen looks prettier.
Deleted the Settings button in the main menu since there will not be any use for Settings in this version of app but the code is still remains in the app for future development.
Cleared up the code to satisfy courses code writing conventions.
****