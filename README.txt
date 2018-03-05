To Do
-----
-> Handle rotation at splash screen
-> same set of new arrivals should be displayed if app is started multiple times (assuming, no new book is added)
-> Add e-resources
-> Don't hardcode urls
-> Update Android Studio on it's startup
-> Latest GUIs : https://medium.com/@mmbialas/30-new-android-libraries-and-projects-released-in-summer-2017-which-should-catch-your-attention-d3702bd9bdc6
-> Top 9 UI Design Trends for Mobile Apps in 2018: https://medium.com/life-never-dead-and-gone/top-9-ui-design-trends-for-mobile-apps-in-2018-645993c802d4


Tutorials
---------
-> Get book cover from ISBN : https://stackoverflow.com/questions/14422528/how-to-get-book-cover-from-isbn-using-google-book-api
-> Recycler View : https://medium.com/@harivigneshjayapalan/android-implementing-custom-recycler-view-part-i-9ce5e9af7fea
-> layout_weight : https://stackoverflow.com/a/3996104/8279892
-> Splash screen with animation : https://www.youtube.com/watch?v=h_hTuaEpc-8
-> Acivity transition animations : https://www.youtube.com/watch?v=0s6x3Sn4eYo
-> Fragments : Slidenerd, Youtube
-> Navigation Drawer for multiple activities : https://stackoverflow.com/questions/27797906/navigation-drawer-with-multiple-activities-nullpointerexception
-> Add multiple resolutions of an image : https://www.youtube.com/watch?v=bbFLDR6X2D8
-> Instead of writing the same property for all the children in xml, set a theme in parent which points to a style
-> Java Coding Conventions -> https://google.github.io/styleguide/javaguide.html

Colors
------
<color name="colorPrimary">#32a7a0</color>
<color name="colorPrimaryDark">#30968f</color>
<color name="colorAccent">#FFFFFF</color>
<color name="white">#FFFFFF</color>

Database - Table vs Description ~ 173 tables
--------------------------------------------
1.	Table -> borrowers
   	Description ->  a user
   	command -> select * from borrowers where borrowernumber=1645;

2. 	Table -> biblio
	Description -> a book; it can have multiple copies; each copy is an item.
	command -> select * from biblio where biblionumber=6618;

3. 	Table -> items
	Description -> A copy of a book
	command -> select * from items where itemnumber=8172
			   select * from items where biblionumber=6610;

4.	Table -> virtualShelves
	Description -> No idea
	command -> select * from virtualshelves where shelfnumber=101;

.	Table -> statistics
	Description -> 
	Command -> select * from statistics where borrowernumber=750;

Database queries - library
--------------------------
1. Get timestamp of 10 most recent books : select timestamp from biblio order by timestamp limit 10;
2. ISBN & timestamp of 10 most recent books : select isbn, timestamp from biblioitems where isbn>999999999 order by timestamp limit 10;

Database queries - general
--------------------------
1. Procedures
	inside sample.sql
		CREATE PROCEDURE account_count()
		SELECT COUNT(*) FROM borrowers;
	MySQL Command line
		SOURCE sample.sql;
		CALL account_count();
		DROP PROCEDURE account_count;

2. Variables
	inside sample.sql
		CREATE PROCEDURE foo()
		SET @size = (SELECT COUNT(*) FROM borrowers);
		SELECT @size
	MySQL Command line
		DROP PROCEDURE foo;SOURCE sample.sql;CALL foo;

3. if else
	inside sample.sql
		CREATE PROCEDURE foo()
		SET @size = (SELECT COUNT(*) FROM borrowers);
		SELECT CASE
			WHEN (@size=0) THEN (SELECT "Empty")
			ELSE (SELECT "Non-empty")
		END;
	MySQL Command line
		 DROP PROCEDURE foo;SOURCE sample.sql;CALL foo;







