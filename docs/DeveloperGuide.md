---
layout: page
title: Developer Guide
---

The aim of this guide is to help the reader to understand how the system and components of Fitbot is
designed, implemented and tested. In the same time, this developer guide also serves to help developers who are interested in understanding the architecture
of Fitbot and some design considerations.
[Don't know about Fitbot? Click here to find more.](#https://ay2122s1-cs2113t-f14-2.github.io/tp/UserGuide.html)

## Content page
[Acknowledgements](#acknowledgements)

[Design](#design)
- [Architecture](#architecture)
- [Data Component (Profile)](#data-component-profile)
- [Data Component (ItemBank and Item)](#data-component-itembank-and-item)
  - [ItemBank](#itembank-class)
  - [Item](#item-class)
- [Logic Component](#logic-component)
- [Storage Component](#storage-component)
- [Implementation](#implementation)
   - [Add Food Item Feature](#proposed-add-a-food-item-feature)
   - [Design Considerations](#design-considerations)
- [Product Scope](#product-scope)
   - [Target User Profile](#target-user-profile)
   - [Value Proposition](#value-proposition)
- [User Stories](#user-stories)
- [Non-functional Requirements](#non-functional-requirements)
- [Glossary](#glossary)
- [Instruction for Manual Testing](#instructions-for-manual-testing)
  - [Launch and Shut Down](#launch-and-shut-down)
  - [Manipulating Data](#manipulating-data)
  - [Saving Data](#saving-data)

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}


## Design 

### Architecture

<p align="center" width="100%">
  <image width="70%" src="images/ArchitectureDiagram.png" alt="Architecture Diagram"/>
</p>

`Main` class is the component that interacts with all the necessary classes.
The `Main` class consists of the few components as shown below:
- `Ui`: The interaction between user and application
- `Logic`: Parse commands and execute them respectively
- `Data`: allow users to perform CRUD operations on the data in the application
- `Storage`: stores all data in the application. Saves a copy of data in relevant files.
  Data will be retrieved from storage upon starting of application.

Upon launching of application:
- The application will check if there are files that are already stored in the respective folder.
  If there is such files, the contents of the files will be loaded to the data section of the application.
  Instances of profile, data(e.g. FoodList, ExerciseList, FutureExerciseList, ItemBank) and storage will be created


Upon exiting of application:
- The application will save all data into the files created. All instance of components will be cleared automatically.

Class diagram of Main

<p align="center" width="100%">
  <img width="100%" src="images/MainClass.png" alt="Main Class Diagram"/>
</p>

When _Fitbot_ is being started, the above instances are being created in the main class. 
All the classes are marked as 1 as the main class require these instance to perform successful operations.
ItemBank contains 2 instances as 1 is required for foodBank while the other is for exerciseBank.

- Main class start of by running the `start()` function which loads all information using StorageManager class to the 
Profile, FoodList, ExerciseList,ItemBank(foodBank, exerciseBank).
-Next main class will check if user contains the profile using the `checkAndCreateProfile()`. If user does not have
a profile, the application will assist user to create a profile by prompting questions.
- `loadsFutureExercisesToList()` loads future exercises to FutureExerciseList.
- After loading and creating profile, the main program will enter `enterTaskModeUntlByeCommand()` for user
to interact with the application
- Once user has keyed in the command `bye`, the main program will exit out of the `enterTaskModeUntlByeCommand()`
and run the `exit()` command to exit the application.



Interaction between the classes could be shown by the uml sequence diagram below.

<p align="center" width="100%">
  <img width="100%" src="images/Architecture.png" alt="Architecture Sequence Diagram"/>
</p>


-When there is an input, the Ui class will retrieve the information from the user.
-Once the Main class receives the input, it creates a new Parser class to parse the commands.
-Depends on the method, in this case add food command, main class will execute the command class(not shown)
based on the command the parser detects.
- The command class will add the food item into the `Data` and updates the `storage` instance accordingly.
- when all the operations above are completed, the Main class will pass a message to Ui class.
- Ui class wll then format the message and print it to console for the user.

### Data Component (Profile)

<p align="center" width="100%">
  <img width="90%" src="images/ProfileClassDiagram.png" alt="Architecture Sequence Diagram"/>
</p>

A `Profile` class has various attributes such as `Name`, `Height`, `Weight`, `Gender`, `Age`, `CalorieGoal` and `ActivityFactor`

- Using these attributes it is able to calculate an estimated Basal Metabolic Rate (BMR) using the Harris-Benedict Equation based on your activity levels. Therefore, while calculating your net calories for the day, your BMR is factored in to give you an estimated calculation of your net calorie.

- All the attributes inherit a `Verifiable` interface to enable us to check if the attributes are valid. This is important for the setting up of profile or the loading of profile from storage to ensure data integrity of the user's attributes.


### Data Component (ItemBank and Item)

<p align="center" width="100%">
  <img width="90%" src="images/ItemBankAndItemClassDiagram.png" alt="ItemBank And Item Class Diagram"/>
</p>

The `Data` component is responsible to perform operations such as data modification and query in the code. It receives the commands from the `Logic` component, execute the 
correct operations, and finally return the command result back to `Logic` component.\
\
Above is a high-level **_class diagram_** for the `ItemBank` and `Item` classes in `Data` component. Note that since `Main` and `Logic` components have accessed to some classes
in `Data` component, they form **_dependencies_** with those classes.
The main purpose of having `ItemBank` and `Item` classes is to allow user to perform writing, reading, editing and deleting operations in the program.

#### ItemBank class
`ItemBank` is the **_highest [superclass](#_superclass_)_** that contains one attribute called `internalItems` which is an _array list_ of `item`.\
`ItemList` being the **[_subclass_](#_subclass_)** of `ItemBank` and **_[superclass](#_superclass_)_** of `FoodList` and `ExerciseList`, which inherits all the methods available from `ItemBank`, with additional methods that form a [dependency](#_dependency_) on `Item` class.\
`FoodList` and `ExerciseList` are **[_subclass_](#_subclass_)** that inherit all the methods available from `ItemList`, while each of them also contains more methods that form a [dependency](#_dependency_)
on `Food` class and `Exercise` class respectively.\
`FutureExerciseList` is a **[_subclass_](#_subclass_)** that inherit all the methods available from `ExerciseList` and contains other methods that form a [dependency](#_dependency_)
on `Exercise` class.

#### Item class
An `Item` class contains two attributes, `name` which represents the name of the item, and `calories` which represents the calorie intake/burnt from the item.\
`Food` and `Exercise` are the only two **_subclasses_** inherit the `Item` class. \
`Food` class has two extra attributes called `dateTime` and `timePeriod`, the former stores the consumed food date and time, while the latter compute the time period 
(only value such as **`Morning`, `Afternoon`, `Evening`** and **`Night`** as shown in the enumeration class `TimePeriod`) of the food consumed time. Note that the `timePeriod` 
value must present when a `Food` object is created.\
`Exercise` class has one extra attribute called `date` which stores the date of the exercise taken.\
\
Classes such as `ItemList` and `Item` are _**[abstract class](#_abstract-class_)**_, because they do not add meaningful value to the user if one tries to create them.



### Ui Component

The `Ui` component interacts with the user. It reads in input from the user and prints messages on the console.
Below shows a class diagram of how `Ui` component interacts with the rest of the application.


<p align="center" width="100%">
  <img width="50%" src="images/Ui.png" alt="Ui Class Diagram"/>
</p>





### Logic Component
 
The `Logic` component is responsible for making sense of user input.

Below is a high level class diagram of the `Logic` component, which shows how it interacts with other components 
like `Main` and `Data`.

<p align="center" width="100%">
  <img width="60%" src="images/LogicClassDiagram.png" alt="Logic Class Diagram"/> 
</p>

The general workflow of the `Logic` component is as follows:
1. After `Main`  receives user input, it feeds this user input to the `ParserManager`.
2. The `ParserManager` parses the user input and creates an `Command` object.
   - More specifically, it creates a `XYZCommand` object, where `XYZ` is a placeholder for the 
      specific command type, e.g `AddFoodCommand`, `UpdateProfileCommand`, etc.
   - `XYZCommand` class inherits from the abstract class `Command`, which is used to represent all executable commands in the application.
3. `ParserManager` returns the `Command` object to `Main`, which then executes the `Command`.
4. After execution, all `Command` objects stores the result of the execution in a `CommandResult` object. 
This `CommandResult` object is then returned to `Main`.

Here is a more detailed class diagram of the `Logic` component.

<p align="center" width="100%">
  <img width="80%" src="images/ParserClassDiagram.png" alt="Parser Class Diagram">
</p>

Taking a closer look into the parsing process, the `ParserManager` actually does not do most of the parsing itself.
Instead, `ParserManager` creates `XYZCommandParser`,  which is then responsible 
for creating the specific `XYZCommand`. All `XYZCommandParser` classes implement the interface `Parser`, which dictates that 
they are able to parse user inputs. They also make use of utility methods stored in `ParserUtils` to extract 
all the parameters relevant to the command. After parsing the input, `XYZCommandParser` returns `XYZCommand` to `ParserManager`,
which then returns the same `XYZCommand` to `Main`.

[comment]: <> (@@author @tlyi)

The sequence diagram below models the interactions between the different classes within the Logic component.
This particular case illustrates how a user input add f/potato c/20 is parsed and process to execute the appropriate actions.

<p align="center" width="100%">
  <img width="100%" src="images/LogicSequenceDiagram.png" alt="Logic Sequence Diagram"/>
</p>

[comment]: <> (@@author)

### Storage component

<p align="center" width="100%">
  <img width="90%" src="images/StorageManagerClassDiagram.png" alt="Architecture Sequence Diagram"/>
</p>

`StorageManager` initializes all `Storage` subclasses with their respective paths. 
Acting as a medium, it then interacts with each of the respective `Storage` subclasses. 
This design declutters the code in Main and provides a platform to ensure each of the subclasses were utilized. 
It is also implemented with all of the `StorageInterface` interfaces to enforce methods of loading and saving to be fully implemented into the code.

The `StorageManager` component loads and saves:

- your profile: name, height, weight, gender, age, calorie goal and activity factor
- list of exercises done: including date performed
- list of food consumed: including date and time of consumption
- upcoming exercises: recurring exercises that are scheduled in the future
- food and exercise banks: names and calories of relevant item

Each `Storage` subclass is able to decode/encode details from the bot and is designed this way (Using ProfileStorage as an example)

<p align="center" width="100%">
  <img width="50%" src="images/ProfileStorageClassDiagram.png" alt="Architecture Sequence Diagram"/>
</p>

The `ProfileStorage` inherits an abstract class of `Storage` which contains protected attributes of `fileName` and `filePath`.
After inheritance, it then implements loading and saving methods interfaced by `ProfileStorageInterface` to ensure reading and writing operations.

where:
- ProfileEncoder encodes the list to the profile.txt file.
- ProfileDecoder decodes the list from profile.txt file and inputs into the bot.
- ProfileStorage utilizes the static methods in encoder and decoder for loading or saving operations.

This way ensures that each class has a _single responsibility_ with little coupling between each other.

## Implementation

This section describes some noteworthy details on how certain features are implemented
and some design considerations.


#### Parsing of Commands
The sequence diagram below models the interactions between the different classes within the Logic component.
This particular case illustrates how a user input add f/potato c/20 is parsed and process to execute the appropriate actions.

<p align="center" width="100%">
  <img width="100%" src="images/LogicSequenceDiagram.png" alt="Logic Sequence Diagram"/>
</p>

#### [Proposed] Add a Food Item Feature

![Add Food Item Sequence Diagram](images/AddFoodItemSequenceDiagram.png)

The purpose of this feature is to allow the user to add food item to the food list. The above diagram shown is the 
sequence diagram of the process of adding the food item. 

When the user gives an input, the `parser` from the `Logic` component will try to read the input, and then call the correct
command. In this case we assume that the correct format of **Add Food** input is given and the AddFoodCommand has already been
called and created.

Step 1: When the `execute` method in the `AddFoodCommand` is being called, it will first check that if the `isCalorieFromBank`
condition is `true`, meaning that the description of the input food item can be found in the `FoodBank` object, 
as shown in the `alt` frames of the sequence diagram. In each alternative paths, a new `Food` class object will be created
by using the `Food` constructor.

Step 2: When the `Food` constructor is called, it will perform a [self-invocation](#_self-invocation_)`setTimePeriod` to set the enum value `timePeriod`
of the Food. After that, it returns the Food object to the `AddFoodCommand`.

Step 3: The `AddFoodCommand` then calls the method `addItem` from the `FoodList` object, which performs the add food operation in the
`Food List`. After the new `Food` Item is added, it will perform a [self-invocation](#_self-invocation_) `sortList` to sort the `FoodList`. Since the 
`addItem` method is void type, nothing is returned to `AddFoodCommand`.

Step 4: After the `addItem` method is executed without giving any error, the `AddFoodCommand` then calls a `CommandResult` object.
This object will return and output the message indicates that the `AddFoodCommand` is executed without any error. At this
stage, the `AddFoodCommand` is successfully ended.

#### Design considerations:

The current data structure used in `FoodList` is [Array List](#_array-list_). The rationale of choosing an array list implementation is because
it supports resizability and random accessibility. However, the drawback of such an array list is that sorting requires 
O(n<sup>2</sup>), which slows down the code efficiency. In the future increment, alternative data structures such as
[Priority Queue](#_priority-queue_) and [Min Heap](#_min-heap_) can be implemented to achieve O(logn) addition and they are
naturally sorted and thus no additional sorting required.

The same reasoning for the class `ItemBank`, which is the superclass of `FoodList` and `ExerciseList`,the current implementation
data structure is also an [Array List](#_array-list_). In the future increment, since the `ItemBank` need to perform query 
operation frequently and the items inside need to be sorted alphabetically, the data structure of the attribute will be changed 
to [TreeMap](#_tree-map_) to achieve O(1) query time.

<p align="center" width="100%">
  <img width="60%" src="images/ItemBankCodeSnippet.png" alt="Item Bank Code Snippet"/>
</p>

#### [Proposed] Add a Recurring Exercise Feature

![Add Recurring Exercise Sequence Diagram](images/AddRecurringExerciseSequenceDiagram.png)

The purpose of this feature is to allow the user to add recurring exercises to the future exercise list. The above diagram 
is the sequence diagram of adding recurring exercises to the future exercise list.

Step 1: The `parser` from the `Logic` component parses the input given by the user and calls the `execute` method in
`AddRecurringExerciseCommand`. The condition `isCalorieFromBank` is checked to see if the user input any calories for
the recurring exercise. If it is `true`, it means that no calories input is detected and the method 
`getCaloriesOfItemWithMatchingName` in class `ItemBank` is called, and it will return an int value of `calories`.

Step 2: Within `execute` method, `addRecurringItem` method in `FutureExerciseList` is also called. This method will 
iteratively call the constructor for `Exercise` class and add the exercises into `FutureExerciseList` via the self-invocation
`addItem` method. This iteration will end when all exercises on `dayOfTheWeek` between `startDate` and `endDate` are added.

Step 3: After `addRecurringExercises` method is executed, `AddRecurringExerciseCommand` calls a `CommandResult` object.
This object outputs a message and `AddRecurringExerciseCommand` will return `commandResult`, indicating that
`AddRecurringExerciseCommand` is successfully executed and ended.

## Product scope
### Target user profile

University students who are looking to keep track of their calorie consumption and calorie outputs.

### Value proposition

During these restricted COVID-19 times, we are confined to home-based learning. As a result, we tend to be less active and have fewer opportunities to stay active. This app aims to help you to gain or lose weight based on your goal of implementing a calorie deficit or calorie surplus.

Its overview shows your progress over the weeks, indicating whether or not you have hit your daily calorie goal target for the past 7 days.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v1.0|new user|want to store food records|track my food intake|
|v1.0|new user|want to store exercise records| track my exercises|
|v1.0|new user|store all records|refer to them whenever needed|
|v2.0|new user|have a profile| to keep track of all information to calculate my net calories|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|
|v2.0|user|have a summary|see my calorie targets
|v2.0|user|have a history|spend less time typing all the requirements to store items|
|v2.0|user|have an exercise list that update itself|have more time for exercises|
|v2.0|user|have a sorted food list|see what I have eaten on different times of the day|
|v2.0|user|have a delete all command|start afresh|


## Non-Functional Requirements

1. Should work on any OS as long as it has Java 11 or above installed on their PC.
2. Should be able to hold up to at least a year of data without a slowdown of performance in daily use.
3. Any user that is comfortable with typing of speeds >55 words per minute would be able to accomplish these tasks faster than if they used a mouse to navigate.
## Glossary
#### _dependency_ 
In UML diagram, dependency is a directed relationship which is used to show that some elements or a set of elements requires, 
needs or depends on other model elements for specification or implementation.
#### _superclass_
A class from which other classes inherit its code. The class that inherits its code will be able to access some/all 
functionalities from the superclass.
#### _subclass_  
A class that inherits code from the other classes. Such class will be able to access some/all functionalities from its superclass, 
but not vice versa.
#### _abstract class_
A class that cannot be created using constructor. Usually such class is a superclass, and it does not give meaningful 
value if one tries to construct it.
#### _self invocation_
In UML sequence diagram, a method that does a calling to another of its own methods is called self-invocation. 
#### _array list_
A linear data structure that inherits Java `List` implementation and `Array` implementation. It behaves like a normal array,
except that it is resizable. Moreover, the amount of time taken for reallocation the elements when capacity grows is a constant
time. In Java, array list can be implemented using `ArrayList` in `Collection`.
#### _priority queue_
An abstract data type similar to a regular queue or stack data structure in which elements in priority queue are ordered
and have "priority" associated with each element. The priority can be defined by the coder. In the case of `FoodList`, the
priority will be defined as earlier date and time will have higher priority.
#### _min heap_
The implementation of min heap is almost the same as priority queue, in which it is sorted according to some "priority" 
constraint. In addition, a min heap can be modelled as a [binary tree](#https://en.wikipedia.org/wiki/Binary_tree) structure
having all the parent nodes smaller or equal to its children nodes. 
#### _tree map_
A tree map is a combination of tree structure and hash map structure. In Java, tree map is implemented using a self-balancing
[Red-Black tree](https://www.geeksforgeeks.org/red-black-tree-set-1-introduction-2/) structure and it is sorted according
to the natural order of its keys. In the case of `ItemBank`, the key should be the String type of the `Item` description, 
which will be sorted lexicographically. \
(more coming in the future...)

## Instructions for manual testing

Given below are some instructions that can be used to test the application manually. 

### Recording Food Items:

#### Adding a new Food Item

1. Adding a new Food Item when the Food List is empty:
   - Prerequisite: Checks if the food list is empty using `view f/`. An output message showing that
   the current food list is empty is expected.
   - Test case: `add f/chicken rice c/607` \
   Expected: New Food Item is added to the Food List. A message telling the user that a new food item has been added will show up. 
   The date and time are the date and time when the user call this command.
   - Test case: `add f/chicken rice c/607 d/10-10-2021 t/1200` \
   Expected: No Food Item is added to the Food List. A message will show up and tell the user that 
   the date must be within 7 days of today.
   - (more test cases )
2. Viewing a new Food Item:
   - Test case: `view f/` when the Food List is empty\
   Expected: No food item shown. 

### Launch and shut down
1. Initial launch
   1. Download the jar file and copy into an empty folder
   2. Go to your command prompt, and go into your directory.
   3. Run the command `java -jar Fitbot.jar`.
   
   Expected: a data folder will be created in the file that contain Fitbot.jar.


2. Setting Up Profile
   1. Delete profile.txt if present.
   2. Run _Fitbot_ using `java -jar Fitbot.jar`.
   
   Expected: _Fitbot_will prompt for your name upon start up.
   

### Manipulating data

1. Data is saved whenever data is manipulated.
   1. run the application
   2. add a food entry into the application.
   3. Exit the application.
   4. The file food_list.txt should have one entry.
   5. Run the application and delete the entry.
   6. Exit the application again.
   
   Expected: food_list.txt should be empty.

### Saving Data

1. Saving data in file
   1. After exiting the application, change the values saved in the file.
   2. Upon start-up, all valid values will be changed in the application.
   3. Replace one of the text file generated by the application with lorem ipsum.
   
   Expected: The application will be able to pick it up and ignore invalid data in relevant files.
