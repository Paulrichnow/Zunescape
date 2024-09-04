[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=10548910&assignment_repo_type=AssignmentRepo)
# Zunescape
An exploration into wireframing a software architecture.

## Instructions
The instructions for this project have been provided in the README included in your repository. They’ve also been restated here for your convenience.

### Part 1: The Entry Point
The beginning of every project starts with three things: (i) the project hierarchy, (ii) the build process, and (iii) the entry point. We’ll be ignoring (ii) the build process for this project since we’re not intending to include any third-party libraries or unit tests. If you are interested in putting together a build process, you’re more than welcome to. The common build processes for Java are configured using Maven or Gradle. I have provided a build process that you can use in the form of a build.sh (for Mac and Linux), build.ps1 (for Windows), or Makefile (for Mac and Linux). You may instead prefer to execute the application from within an IDE, which is also fine for this project.

#### Project Hierarchy
Let’s start with the project hierarchy. Since we’re working in Java, we’ll starting by creating the following project path inside our project directory (Zunescape). *Zunescape/src/main/java/com/zunescape/app*

If that seems too long, welcome to Java! From here on forward, every new folder (a.k.a., package) that we add to this project will go into the zunescape folder and will be a sibling package to app.

#### Entry Point
Next, let’s configure our entry point. Every project needs one! An entry point in Java is a class with a main method. We’ll execute this class to run our app. Let’s create a new class called App inside of zunescape/app. This class should be public and should be part of package com.zunescape.app;

Then, define the standard main method. Our project currently has five workflows. Our goal is just to wireframe the architecture, so we’ll use a simple console interface to work through each of these flows.
 
This simple interface will use a while (true) loop to provide the user with a prompt and get input. The user should be provided with the following prompt:

```
Welcome to Ze Wilds of Zunescape!
What would you like to do?
1)	Battle
2)	Forge
3)	Purchase
4)	Trade
5)	Accessorize
6)	Quit
```

The interface should then wait for user input, handle the input, and then prompt the user again to determine what they would like to do. This process should be repeated indefinitely until the player selects 6) Quit. At which point, the application will exit. The player may either type 6 or Quit. You may decide to support either or both.

*Note: If you want an easy to support console input, define a public static String getInput() on App that uses a static Scanner or static BufferedReader to return a single line of console input. Then use App.getInput() throughout your wireframe to get user input.*

#### Test
Once the console interface is in place, the only thing it can do is prompt the user, accept input, and exit. We don’t have any workflows, yet. We do have enough for the moment though. Let’s go ahead and run our application to see that our console interface is working as expected.
Once you’re able to see the prompt and exit, you’ve completed the first part. You should stage, commit, and push your changes.
 
### Part 2: The Battle Royale
Next, we’re going to wireframe the primary workflow of our application: the Battle Royale. To do this, we’ll first create a new package zunescape/royale. In this package, we’ll create the BattleQueue, BattleMatcher, and BattleRoyale classes. Each of these classes belong to package com.zunescape.royale;

#### BattleQueue Constructor
Let’s start with the BattleQueue. According to our architecture, the BattleQueue depends on the Matcher, the Battle Royale, and the Arenas. As such, we’ll define a single constructor in BattleQueue that takes three parameters, which include an Arenas object, a BattleMatcher object, and a BattleRoyale object. The constructor will map these arguments to private instance fields on the class BattleQueue, which should be declared just above the constructor within the scope of the class.

We don’t have an Arenas class yet, so we’ll need to now add a new package zunescape/arenas to our project. We’ll then create the Arenas class which will belong to package com.zunescape.arenas; Then import Arenas inside of the BattleQueue file.

#### Queueing
Next, we need to wire everything together. When a user wishes to battle, they must first enter the queue. Let’s define a single public void enqueue(Contestant contestant) method on the BattleQueue, which we’ll use to add players to the battle queue. Now, we have to consider our parameter(s). According to our requirements, a player should be matched up with other players of roughly equal skill. As such, we’ll need at least a unique player ID to identify our player and an integer representing their skill level, or ELO.

To do this in the Java-esque way, we’ll combine these two values into a composite data type called Contestant. The enqueue method should have a single parameter for a Contestant object.

#### Contestant
Since there is no Contestant class, we’ll create the zunescape/royale/util package and create the Contestant class which will belong to package com.zunescape.royale.util; Then import Contestant from within the BattleQueue file.

Util packages, like java.util, are a common convention for housing composite data types and containers. We could have even created a zunespace/util package, but this Contestant composite type is specifically for the Battle Royale workflow. We shouldn’t pollute our project with custom data types that are only needed in one workflow.

The Contestant class needs a constructor, which will take a String playerId and an int elo. It should store these on two private instance fields, and we should define a getter for each.

#### Arena Selection
Great! So now we have a way for the BattleQueue to get a Contestant that needs to be queued for a Battle Royale. Next, the player needs to select an arena. So let’s define a private String selectArena() method to handle this operation. This method should prompt the user to select an available arena and then return the unique ID of the arena as a String. The prompt should read as follows:

```
Please select an arena from among the following:
```

This prompt should be followed by the names of the available arenas. For now, we’ll use the arena names as their unique IDs.

But how do we get the names of the available arenas. That’s where the Arenas object comes in. Let’s assume that the Arenas object has a public Set<String> getArenas() method that can provide a Set of all available arena names. We can then print the Set and then accept user input from console. The user should input the selected arena, which will then be returned by the selectArena method.

Don’t forget that Arenas has no such method for getArenas(). Let’s jump over to the Arenas class and define that method. You can have it return Set.of(…) and just provide three names which you think sound cool as possible arena locations.

*We should now call selectArena as the first step of enqueue.*

#### Matching Contestants
We now have a Contestant and the arena that they’ve selected. Next, we need to match them against other players. This is where the BattleMatcher comes in. Contestants need to be matched for the same arena according to the requirements. Let’s assume that BattleMatcher has a public Set<Contestant> findContestants(Contestant primary, String arena) that we can use to match our contestants against other contestants of equal skill. We can use that method to get the set of Contestants that will be facing off in a Battle Royale. With that set and the selected arena, we have everything we need to start a match.

The BattleMatcher has no such method, so we’ll need to jump over to the class to define that method. For now, it’s fine to have the method return a Set.of(…) with three new Contestants as well as the primary contestant. You can give each Contestant a name that you think sounds fun as well as the same elo as our primary Contestant.

*We should call findContestants as the second step of enqueue.*

#### Battle Royale
We’re ready to start a match. Let’s assume that the BattleRoyale class has the method public void battle(Set<Contestant> contestants, String arena) which takes a set of contestants and pits them against each other in the chosen arena. The BattleRoyale class will be responsible for handling the rest, so that concludes everything that we need to do with our BattleQueue.

Jump over to the BattleRoyale class and implement the stated method. It doesn’t need to do anything at the moment. We’ll implement more in just a bit.

We should call battle as the last step of enqueue. Then go back to App and add the enqueue to our available workflows. It should be called when the user inputs 1 or Battle, depending on which you would like to support.

#### Test
We’ve hit our next stopping point. Run the application again and ensure that you can get through selecting an arena. If you’d like, you can also add print statements along the way to ensure that everything is wired the way you think it is. Once you’ve checked your progress, stage, commit, and push your changes.

### Part 3: The Forge
Next, it’s time to forge our destiny, or our own arenas in this case. According to our architecture, we need an Arenas Forge component, which depends on Arenas and Tourneys. Let’s use the zunescape/arenas package for this, and create the Forge class in that package, which will belong to package com.zunescape.arenas;

The constructor for this class will take an Arenas object and a Tourneys object. We haven’t created the Tourneys class, so let’s do that now by adding the Tourneys class to the zunescape/arenas package. It should also belong to package com.zunescape.arenas;

#### Enter the Forge
The Forge is responsible for creating new arenas and invitation only tourneys. First, let’s define a public void enter() method on the Forge, so that we can enter the forge from our entry point. This should be called from App as a part of the Forge workflow.

Then we should define a public void addArena(String arena) to the Arenas class that will allow us to add new arenas that can be queued as a part of the BattleQueue workflow. We should also add a public String addTourney(String tourney) to the Tourneys class that will allow us to add new tourneys. This method returns a String, which represents the invite associated with the tourney that can be given out to other players.

The enter method should begin by prompting the user to create an Arena or a Tourney and then accept the name of the Arena or Tourney.

*We then call addArena if an arena is being created or addTourney if a tourney is being created (and print the invite).*

#### Adding Arenas
To add the Arena to our workflow, we need to modify our Arenas class so that it can maintain a Set of arenas in memory. We can add any new arenas to this set and return this set as a part of the getArenas method. Lastly, we’ll need to ensure that both the BattleQueue and the Forge use the same Arenas object.
 
#### Adding Tourneys
Next, we’ll add Tourneys. Since, we’re going to have Tourneys be invite only, let’s create a map that can be used to map Tourneys, which are currently Strings, by the invite that we create for them. The addTourney method will put new tournaments into this map.

To create the invite for the tourney, let’s just use the hash of the tourney name. Every String in Java has the hashCode method, which can be used to return the integer hash of the String. We’ll use that as a String for the invite.

This allows us to create new tournaments but doesn’t add the tournaments to our workflow. If we look closely at the architecture, we can see that the BattleQueue component doesn’t depend on Tourneys, which means that we can’t currently queue into a tourney. There are two things we can do. We can either (i) refactor the current architecture or (ii) bypass the workflow. In this case, we’re going to opt for the latter.

The standard queueing process has been put in place to ensure that players get queued against other players of equivalent skill in an arena of their choice. As of right now, none of this relates to invite-only tournaments. Rather than refactor the BattleQueue component to support tourneys, which would overload it, let’s instead alter the arena selection process to allow players to join a tourney instead. In which case, they’ll be directed to the Battle Royale and will skip the queueing process altogether.

To do this, we’ll first modify our interface for the BattleQueue so that users are also given a choice to enter a tourney code before they select an arena. The user should type in Tourney and then the invite that we printed as a part of the workflow to create a tourney. We can then call battle passing the tourney invite as the arena argument, and we should probably change the name of that parameter so that it’s clear that it can be an arena ID or a tourney invite.

#### Test
We’ve hit our next stopping point. Run the application again and ensure that you can create new arenas. If you’d like, you can also add print statements along the way to ensure that everything is wired the way you think it is. Once you’ve checked your progress, stage, commit, and push your changes.
 
### Part 4: The Battle Royale
We’re finally ready to look at the Battle Royale class a bit more closely. It currently supports battle, which doesn’t do anything. We’ll add a little bit more to our workflow and finish wiring the Battle Royale component, but that’s about it. 

To start, we should note that the Battle Royale component depends on the Arenas Component, the Tourneys component, and the Chat component, but the class itself does not include any of those. Let’s correct that.

We should add a constructor to the BattleRoyale class that takes an Arenas object, a Tourneys object, and a Chat object. The Arenas and Tourneys object needs to be the same object that we use in the Forge.

The Chat object doesn’t currently exist. Let’s create one. In this case, we’re going to do something a bit different and add the chat package to zunescape/royale/chat. In our current architecture, chat exists solely within a Battle Royale. As such, it should be housed within that package, where it would logically have access to everything that Battle Royale does and vice versa. The Chat class should be added to this package, and it should belong to package com.zunescape.royale.chat;

#### Loading
The battle method of BattleRoyale can accept a String that is either an arena or a tourney invite. To determine which, let’s use the Tourneys class to first determine if our String input is an invite. We’ll add a public boolean hasInvite(String invite) to Tourneys, which should return true if the invite is currently associated with a tourney. Otherwise, it returns false.

If the String we’ve been given is an invite, let’s load the tourney using the Tourneys class. We’ll implement a public String loadTourney(String invite) on the Tourneys class, which should just return the tourney associated with the invite. From there, we can print a “Welcome to the Tournament <Name>”, where <Name> is the name of the tournament.

Otherwise, if the String is an arena, let’s use the Arenas class to load the arena. We’ll add a public boolean loadArena(String arena) which won’t do anything for the time being other than return true. We should then print “Welcome to <Name>”, where <Name> is the name of the arena.

#### Chat
Next, let’s pull in the Chat component in by having each contestant fire off a “Hello, World!”. We’ll add a public void post(Set<Contestant> Contestants, Contestant speaker, String msg) to the Chat class. This method should print the provided message to those that are within range.

For this project, we’ll just have the message be posted if at least one Contestant is within range, which will always be true. To determine if a Contestant is within range, the Chat needs assistance from the Range component. Let’s add a RangeValidator class to the package zunescape/royale/util which will belong to package com.zunescape.royale.util;

The RangeValidator class will have a single method public Set<Contestant> withinRange(Set<Contestant> contestants, Contestant primary) that returns a Set of Contestants that are within range of our primary. For now, let’s just have it return a Set of all contestants.

If the Set returned is not empty, which it won’t be, then we print the message passed to the post method. That concludes the Chat component.

#### Finishing the Battle Royale
We’re just about done with the Battle Royale. The last thing we’ll do is determine a winner. In the case of the tourney, we’ll have the contestant win by default by printing “You Win!”. In the case of an arena, after everyone has fired off their “Hello, World!”, we’ll randomly select one of the contestants to win, and print “<Name> wins!”, where <Name> is the playerId of the Contestant.

#### Test
We’ve hit our next stopping point. Run the application again and ensure that you can move through the entire Battle Royale workflow. If you’d like, you can also add print statements along the way to ensure that everything is wired the way you think it is. Once you’ve checked your progress, stage, commit, and push your changes.

### Part 5: The Store and Exchange
Our next two workflows are going to move rather quick. We’re going to build the in-game store and the player exchange, which are both dependent on a persistent database holding player account information. To do this, we’ll first add a new package zunescape/market. We’ll create the classes GameStore and PlayerExchange, which will both belong to package com.zunescape.market;

#### Player Database
These two classes will both have a single constructor which takes a PlayerDb object. This object doesn’t exist, so we’ll need to create it. In this case, let’s go ahead and create the package zunescape/util to house our PlayerDb class since we’ll likely be using this as a database abstraction through our implementation at some point in the future.

#### Purchases
Next, we’ll add a public void enter() to both the GameStore and the PlayerExchange. In both cases, the enter method should prompt the player to select something for purchase. Include a few items that you think sound interesting for a fantasy MMORPG battle royale. The method should then accept user input, and add the input item to the user’s account.

We’ll do so by using a public boolean addItem(String item) to the PlayerDb class. This method does not exist yet, so let’s go ahead and create it. All it will do for now is return true.

#### Characters
We’re not yet able to see the items that we’ve purchased, so let’s go ahead and create our Character component by creating a new package zunescape/character. The class CharacterMenu should belong to package com.zunescape.character;

Next, we’ll add a public void open() to open the character menu. At which point, the character menu should prompt the user to select what items that would like to equip from the items they own. To do so, it will need to first fetch the items from the PlayerDb. Since it depends on the PlayerDb, the CharacterMenu class will require a constructor that takes a PlayerDb object, which should be the same PlayerDb object used by the GameStore and PlayerExchange. We’ll add a public Set<String> getItems() to the PlayerDb, which should return a Set of the item IDs that the player owns.

Once we’ve got the item IDs, we can print the items and collect user input. For each input, we’ll simply print “Equipped <Item>” where <Item> is the item ID of the item that’s been equipped.

#### Add to Workflow
The last thing to do is to add the GameStore, PlayerExchange, and CharacterMenu to our workflow inside of App. Upon doing so, we’ve completed wireframing our project.
Congratulations!

#### Test
We’ve hit our final stopping point. Run the application again and ensure that you can purchase and equip items. If you’d like, you can also add print statements along the way to ensure that everything is wired the way you think it is. Once you’ve checked your progress, stage, commit, and push your changes.
