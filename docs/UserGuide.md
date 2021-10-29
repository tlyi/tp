---
layout: page
title: User Guide
---

## **Introducing _Fitbot_**
_Fitbot_ is a **desktop app** that helps university students who are looking to
**keep track of their calorie consumption and calorie output** with the speed and convenience of
**command-line based** tools, especially in times of online school.

_Fitbot_ can be used across all operating systems such as Windows, Mac OS X, Linux and Unix. It is optimised for use via 
a [Command-Line Interface (CLI)](https://en.wikipedia.org/wiki/Command-line_interface), so it would be especially beneficial for fast
typers and people who enjoy a clean and simple app interface.


If you type fast, and you need an easy and quick way to record your calories, _Fitbot_ is the app for you! 💪💯

## **About This User Guide**
This guide explains how you can use all the features available on _Fitbot_ and
maximise your user experience.

To preface, _Fitbot_ was developed with modern-day university students in mind as our target audience. As a result, we do expect users to have a basic level 
of comfort with using computers, and it would be even better if you are familiar with CLI. However, if you do not, do not worry as we
have provided a comprehensive set of instructions to [get started](#1-quick-start)!


Throughout this guide, we will be using some special formatting and symbols to bring your attention to certain aspects:


| Formatting | Meaning |
|--------|-------|
_italics_ | Text that has been _italicised_ indicates that it is a term specific to _Fitbot_.
**bold** | Text that has been **bolded** indicates that it is important. 
`abc` | Text with a `highlight` indicates that it is code that can be typed by you into the command line or displayed by _Fitbot_.
ℹ️ |This symbol indicates important information. 
❗| This symbol indicates important rules to follow. Make sure you **pay extra attention** to the information, else _Fitbot_ will fail to execute certain functions! 
💡|This symbol indicates tips and tricks that you can use to make your _Fitbot_ experience even smoother.





## **Content Page**

[1. Quick Start](#1-quick-start)

[2. Set Up Profile](#2-set-up-profile)

[3. Terminology](#3-terminology)

[4. Features](#4-features)
- [4.1 Customising Your Profile](#41-customising-your-profile)
  - [4.1.1 Setting Up Your Profile](#411-setting-up-your-profile)
  - [4.1.2 Viewing Profile `profile`](#412-viewing-profile-profile)
  - [4.1.3 Updating Profile Attributes](#413-updating-profile-attributes)
  - [4.1.4 Calculating BMI `bmi`](#414-calculating-bmi-bmi)
- [4.2 Recording Your Food Consumption](#42-recording-your-food-consumption)
  - [4.2.1 Adding Food Items `add f/`](#421-adding-food-items-add-f) 
  - [4.2.2 Viewing Food List `view f/`](#422-viewing-food-list-view-f)
  - [4.2.3 Deleting Food Items `delete f/`](#423-deleting-food-items-delete-f)
- [4.3 Recording Your Exercises](#43-recording-your-exercises)
  - [4.3.1 Adding Exercise Items `add e/`](#431-adding-exercise-items-add-e) 
  - [4.3.2 Viewing Exercise Items `view e/` `view u/`](#432-viewing-exercise-items-view-e-view-u)
  - [4.3.3 Deleting Exercises `delete e/`](#433-deleting-exercises-delete-e)
- [4.4 Scheduling Your Exercises](#44-scheduling-your-exercises)
  - [4.4.1 Adding Upcoming Exercise Items `add e/`](#441-adding-upcoming-exercise-items-add-e)
  - [4.4.2 Adding Recurring Exercise Items `add r/`](#442-adding-recurring-exercise-items-add-r)
  - [4.4.3 Viewing Upcoming Exercise List `view u/`](#443-viewing-upcoming-exercise-items-view-u)
  - [4.4.4 Editing Upcoming Exercise Items `edit u/`](#444-editing-upcoming-exercise-items-edit-u)
  - [4.4.5 Deleting Upcoming Exercise Items `delete u/`](#445-deleting-upcoming-exercise-items-delete-u)
- [4.5 Building Your Food Bank](#45-building-your-food-bank)
  - [4.5.1 Adding Food Bank Items `add fbank/`](#451-adding-food-bank-items-add-fbank)
  - [4.5.2 Viewing Food Bank `view fbank/`](#452-viewing-food-bank-view-fbank)
  - [4.5.3 Editing Food Bank Items `edit fbank/`](#453-editing-food-bank-items-edit-fbank)
  - [4.5.4 Deleting Food Bank Items `delete fbank/`](#454-deleting-food-bank-items-delete-fbank)
- [4.6 Building Your Exercise Bank](#46-building-your-exercise-bank)
  - [4.6.1 Adding Exercise Bank Items `add ebank/`](#461-adding-exercise-bank-items-add-ebank)
  - [4.6.2 Viewing Exercise Bank `view ebank/`](#462-viewing-exercise-bank-view-ebank)
  - [4.6.3 Editing Exercise Bank Items `edit ebank/`](#463-editing-exercise-bank-items-edit-ebank)
  - [4.6.4 Deleting Exercise Bank Items `delete ebank/`](#464-deleting-exercise-bank-items-delete-ebank)
- [4.7 Viewing Your Calorie Summary `overview`](#47-viewing-your-calorie-summary-overview)
- [4.8 Viewing Help `help`](#48-viewing-help-help)
- [4.9 Exiting Program `bye`](#49-exiting-program-bye)

[5. Data Management](#5-data-management)
- [5.1 Saving Data](#51-saving-the-data)
- [5.2 Editing Data](#52-editing-the-data-file)

[6. FAQ](#6-faq)

[7. Command Summary](#7-command-summary)




## **1. Quick Start**

1. Ensure you have Java 11 or above installed in your computer.  If not, install Java 11 [here](https://www.oracle.com/java/technologies/downloads/).
2. Download the latest version of [Fitbot.jar](https://github.com/AY2122S1-CS2113T-F14-2/tp/releases/tag/v1.0) from the cloud.
3. Copy the file to the folder you want to use as the home folder for your _Fitbot_.
4. Go to command prompt and change the directory to the file's location. [Not sure how to do this?](https://www.groovypost.com/howto/open-command-window-terminal-window-specific-folder-windows-mac-linux/)
5. Type `java -jar Fitbot.jar` into the command prompt and press enter to start the program.

If successfully loaded, you will see this logo:

![Start Up Screen](./images/StartupLogo.png)

You are now all good to start using _Fitbot_! 

## **2. Set Up Profile**

_Fitbot_ will detect if there is a profile present in the application. If you have not set up a profile,
_Fitbot_ will ask you for your particulars.


Particulars required include name, height, weight, age, gender, calorie goal and activity factor. The application will prompt for your particulars again if your input is incorrect.

Below are the questions that you will encounter.

**Name Input**


```text
Fitbot realised that your profile has not been created. Let's start creating a profile below!
__________________________________________________________________________________________________________
What's your name?
__________________________________________________________________________________________________________
```
You will need to input your name without special characters of '/' and '|' as your name.

**Height Input**
```
__________________________________________________________________________________________________________
Nice name you have there! Hello user.
What's your height? (in cm)
__________________________________________________________________________________________________________
```
You will need to input your height as positive numbers in centimetres . Decimals are accepted(e.g. 170.1) and 
you do not need to input units. 

```text
__________________________________________________________________________________________________________
Nice name you have there! Hello user.
What's your height? (in cm)
__________________________________________________________________________________________________________
```

**Weight Input**
```text
__________________________________________________________________________________________________________
Your height is 170.1cm.
What's your weight? (in kg)
__________________________________________________________________________________________________________
```
Similar to height, the weight input accepts positive numbers including decimals in kilograms(e.g. 65.0) 

**Gender Input**
```text
__________________________________________________________________________________________________________
Your weight is 65.0kg.
What is your gender?(If you are a male, type 'm', if you are a female , type 'f')
__________________________________________________________________________________________________________
```
You can input the letter 'm' if you are a male and 'f' if you are a female (e.g m).

**Age Input**

```text
__________________________________________________________________________________________________________
You are a male.
How old are you?
__________________________________________________________________________________________________________
```
You can input your age with the nearest positive whole number. (e.g. 22)

**Calorie Goal**
```text
__________________________________________________________________________________________________________
You are 22 years old.
Please input your net calorie goal.
__________________________________________________________________________________________________________
```
You can input your calorie goal with whole number range from -2500 to 2500 (e.g 500). The net calories is calculated 
based on total calories gained from food subtracting the calories burnt from exercise and basal metabolic rate(BMR).

**Activity Factor**
```text
__________________________________________________________________________________________________________
You calorie goal is 500 cal.
In terms of activity level, how active are you?
Based on the rubics below, please key in 1 to 5 based on how active you are.
1 -> Sedentary - Little or no exercise
2 -> Lightly Active - Light exercise or sports, around 1-3 days a week
3 -> Moderately Active - Regular exercise or sports, around 3-5 days a week
4 -> Very Active - Frequent exercise or sports, around 6-7 days a week
5 -> If you are extra active - Sports or exercising is your passion and a physical jobscope.
__________________________________________________________________________________________________________
```
You can input 1 to 5 based on the activity level description as shown above.

Once you are done with the particulars above, you could see the following message below:
```text
__________________________________________________________________________________________________________
Profile created successfully
You can start by typing a command or view the list of available commands by typing "help".
__________________________________________________________________________________________________________
```


After setting up the profile, you can start recording your food and exercises with the commands below.


## **3. Terminology**

***Calorie*** - A unit used to measure the energy of an item. One calorie is the amount of energy required to raise the
temperature of one gram of water by one degree Celsius. On average, a male will require approximately 2500 cal consumed
from food per day while a female will require around 2000 cal intake per day.\
\
***Calorie goal*** - The amount of calorie you wish to achieve per day. It is the value you want to achieve from your 
calorie intake minus the calorie burnt naturally and physically per day. If you are planning to lose weight, it is 
recommended to set your daily calorie goal as negative value. On the other hand, positive value calorie goal may mean that 
you are going to increase or maintain your body weight.

***Net Calorie*** - The difference between calorie goal and the total calorie burnt from exercising and 
[body metabolism](https://www.news-medical.net/life-sciences/What-is-Metabolism.aspx).
For your information, we use [basal metabolic rate (BMR)](https://en.wikipedia.org/wiki/Basal_metabolic_rate) to compute
the rate of calorie consumed by body metabolic activity.\
\
***Activity factor*** - A measure of the level of activity. The value is used in the calculation of BMR. In our application, 
we use an integer ranged from 1 to 5 to measure the activity factor:
- Integer 1: Little to no exercise (0-1 day of exercises a week)
- Integer 2: Lightly active (1-3 days of exercises a week)
- Integer 3: Moderately active (3-5 days of exercises a week)
- Integer 4: Very active (6-7 days of exercises a week)
- Integer 5: Extremely active (when sports is your passion and have a very physical job scope)

***Body Mass Index (BMI)*** - A measure to evaluate if the body weight is healthy. BMI is computed by using the body
weight(in kg) divided by the square of the body height(in m). \
\
***Item*** - We use the term _Item_ to represent the items that can be stored in _Fitbot_. The available items
are _Food Item_ and _Exercise Item_.\
\
***Upcoming exercise*** - We define the exercise with date after today's date as an Upcoming Exercise. This kind of exercise
will be handled specifically. It will not be added to the Exercise List but will be saved internally in the storage file. More
details of the process can be found at [here](#442-adding-recurring-exercise-items-add-r).\
\
***Item Bank*** - An item storage that is capable of storing the Food or Exercise Item with its respective calorie. This is
to help you to store the calorie intake of a specific Food or the calorie burnt from a specific Exercise for future use. More details
of the item bank can be found at [here](#451-adding-food-bank-items-add-fbank).\
\
***Parameters*** - Parameters are values in the command format that _Fitbot_ expects you to provide.


## **4. Features**

ℹ️ ***Command Format***


- Commands are not case-sensitive (e.g. `help`, `HELP`,`hElP` are all able to execute the `help` command)

- Words in upper case (e.g. `UPPER_CASE`) are used to signify parameters.

- Parameters in curly brackets `{}` are optional.

  e.g. `bmi {h/HEIGHT_IN_CM w/WEIGHT_IN_KG}` can be `bmi` or `bmi h/150 w/70`

- The order of the parameters do not matter.

  e.g. `add f/potato c/200` or `add c/200 f/potato` both adds a Food Item called "potato" with 200 calories.


❗ Please do not use the characters `/` and `|` in your input other than to specify parameters!


### **4.1 Customising Your Profile**

You can customise your profile with the following steps.

#### 4.1.1 Setting Up Your Profile

Refer to [2. Set Up Profile](#2-set-up-profile) to set up your profile.

#### 4.1.2 Viewing Profile: `profile`

Typing `profile` lets you view your name, height, weight, gender, age, calorie goal and activity factor.

Example:

```text
profile 
__________________________________________________________________________________________________________ 
Hello hi! This is your profile: 
*====================================== 
	Name 			Lisa 
	Height 			159cm 
	Weight 			50.0kg 
	Gender 			F 
	Age 			21 
	Calories goal 		1500 cal 
	Activity factor 	2 
======================================* 
__________________________________________________________________________________________________________ 
```
#### 4.1.3 Updating Profile Attributes 

Change your profile attributes with the following command: \
`profile {n/NAME} {h/HEIGHT} {w/WEIGHT} {s/GENDER} {a/AGE} {g/CALORIE_GOAL} {x/ACTIVITY_FACTOR}` \
\
where : 
\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; n/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; name \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; h/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; height in cm \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; w/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;weight in kg \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; a/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; age \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; g/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; calorie goal \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; s/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; gender (M or F) \
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; x/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; activity factor (1: Sedentary - 5: Extremely Active)

where you will require at least 1 of the optional parameters (in no particular order) to change your attribute!

#### 4.1.4 Calculating BMI `bmi`

You can calculate your Body Mass Index (BMI) using the command `bmi {h/HEIGHT_IN_CM} {w/WEIGHT_IN_KG}`

If you do not provide the two optional parameters of height and weight, the BMI will be computed based on the user's current height and weight in the profile. It also outputs the status of your BMI *(underweight, healthy, overweight, obese)* based on the calculated BMI.

Example:
- `bmi` calculates the BMI value based on the user's height and weight in his or her profile.
- `bmi h/170 w/65` calculates the BMI value based on height 170cm and weight 65cm.

```text  
bmi
__________________________________________________________________________________________________________
Your BMI value according to your current profile is:
	15.5 (Underweight)
__________________________________________________________________________________________________________
bmi h/170 w/65  
__________________________________________________________________________________________  
The calculated BMI value is 20.1 (Healthy)
__________________________________________________________________________________________  
```  

### 4.2 Recording your food consumption

You can record all the food items consumed within a week into the Food List. This allows you
to keep track of your food calorie consumption and gain a better understanding of your diet habit. 

#### 4.2.1 Adding Food Items `add f/`

You can record food items consumed on a specific date and time with a specific calorie value into the Food List. 
This command allows you to add a new Food Item to the Food List.

Format: `add f/ITEM {c/CALORIES} {d/DD-MM-YYYY} {t/HHMM}` adds a Food Item consumed with its respective calories on the given
date (`DD-MM-YYYY`) and time (`HHMM`).

Example: 
- `add f/chicken rice c/607 d/21-10-2021 t/1400` adds record of food consumed: chicken rice with 607 calories gained on 21 Oct 2021 1400 to the food list.

```text
add f/chicken rice c/607 d/21-10-2021 t/1400
__________________________________________________________________________________________
A food item has been added:
    chicken rice (607 cal) @ 14:00, 21 Oct 2021
__________________________________________________________________________________________
```
💡 **Tip:** If you do not specify the date and time of the Food Item, it is assumed that the date and time is based on the date and time of input.

💡 **Tip:** It is possible to add Food Item without providing the calorie of it. You can do this by saving the corresponding 
Food Item into the Food Bank. More details can be found at [Section 4.5](#45-building-your-food-bank).

❗ The input date `DD-MM-YYYY` must within 7 days before today. For example, if today date is `20-10-2021`, then the input
date must be within `13-10-2021` to `20-10-2021`.

#### 4.2.2 Viewing Food List `view f/`

You may want to view how many calories you have taken in this week. This command will show a list of Food Items and 
their calories added within 7 days (including today) from the list.

Format: `view f/`

Example: 

```text
view f/
__________________________________________________________________________________________________________
Here is a summary of all the food items you have consumed in the past week:
..........................................................................................................
You have consumed 3 food item(s) on Friday (22 Oct 2021):
In the morning:
	1. donut x2 (607 cal) @ 10:00
In the afternoon:
	1. chicken rice (607 cal) @ 14:00
In the evening:
	1. yong tau foo (560 cal) @ 19:20
Total calories consumed in the day: 1774 cal
..........................................................................................................
You have consumed 4 food item(s) on Saturday (23 Oct 2021):
In the morning:
	1. butter bread x2 (418 cal) @ 08:30
In the afternoon:
	1. penang laksa (377 cal) @ 13:00
In the evening:
	1. sliced fish bee hoon (349 cal) @ 18:40
At night:
	1. roti prata x3 (507 cal) @ 23:50
Total calories consumed in the day: 1651 cal
..........................................................................................................
Total number of food consumed in this week: 7
Total calories consumed in this week: 3425 cal
__________________________________________________________________________________________________________
```

#### 4.2.3 Deleting Food Items `delete f/`

When you mistakenly add a wrong Food Item inside your Food List, there is no need to worry! This command allows you to 
remove a specific Food Item in the Food List.

Format: `delete f/LIST_NO d/DD-MM-YYYY t/HHMM` deletes the *n<sup>th</sup>* Food Item in the Food List which has the date (`DD-MM-YYYY`)
and time (`HHMM`), where *n* is the index of the Food to delete.

❗ `LIST_NO` must be a positive integer within the range of the number of Items in the list.


Example:

```text
delete f/1 d/22-10-2021 t/1000
__________________________________________________________________________________________________________
A food item has been deleted:
	donut x2 (607 cal) @ 10:00, 22 Oct 2021
__________________________________________________________________________________________________________
```
💡  **Tip:** If you wish to remove all the Food Items from the list, there is a shortcut command: `delete f/all`.
```
delete f/all
__________________________________________________________________________________________________________
All food items have been removed.
__________________________________________________________________________________________________________
```

### **4.3 Recording Your Exercises**

Besides Food Items, you may also record Exercises that you have taken into the Exercise List. This will allow you to keep
track of total calories burnt and check whether you have done sufficient exercises. 

#### 4.3.1 Adding Exercise Items `add e/`

By using this command, you can add any Exercise Items done into the Exercise List.

Format: `add e/ITEM {c/CALORIES} {d/DD-MM-YYYY}` adds an Exercise Item with its respective calories burnt on the given date
(`DD-MM-YYYY`).

ℹ️ If the date `DD-MM-YYYY` provided is in the future, this exercise will be treated as an Upcoming Exercise Item, 
and it will be added to the [Upcoming Exercise List](#441-adding-upcoming-exercise-items-add-e) instead.

💡 **Tip:** Similar to [Food Item](#421-adding-food-items-add-f), if you do not specify the date and time of the Exercise Item, 
it is assumed that the date and time is based on the date and time of input.

💡 **Tip:** It is possible to add Exercise Item without providing the calorie burnt on it. You can do this by saving the corresponding
Exercise Item into the Exercise Bank. More details can be found at [here](#46-building-your-exercise-bank).

❗ The input date `DD-MM-YYYY` must within 7 days before today. For example, if today date is `20-10-2021`, then the input
date must be within `13-10-2021` to `20-10-2021`.

Example:
- `add e/hiit c/290 d/21-10-2021` adds record of exercise done: hiit with 290 calories burnt on 21 Oct 2021 to the Exercise List.

```text
add e/hiit c/290 d/ d/21-10-2021
__________________________________________________________________________________________
An exercise has been added:
    hiit (290 cal) @ 21 Oct 2021
__________________________________________________________________________________________
```

#### 4.3.2 Viewing Exercise Items `view e/` `view u/`

It is possible to view and check all the exercises you have added. This command is for you to view all the exercises taken 
and the calories that are added within the past 7 days (including today) from the list.

Format: `view e/`

Example: 

```text
view e/
__________________________________________________________________________________________________________
Here is a summary of all the exercises you have done in the past week:
..........................................................................................................
You have done 1 exercise(s) on Friday (22 Oct 2021):
	1. biking (500 cal)
Total calories burnt in the day: 500 cal
..........................................................................................................
You have done 1 exercise(s) on Sunday (24 Oct 2021):
	1. hiit (290 cal)
Total calories burnt in the day: 290 cal
..........................................................................................................
Total exercises done in this week: 2
Total calorie burnt in the week: 790 cal
__________________________________________________________________________________________________________
```
ℹ️ It is also possible to view all the Upcoming Exercises from the Upcoming Exercise List that you have added before. More
details can be found [here](#443-viewing-upcoming-exercise-items-view-u).

#### 4.3.3 Deleting Exercises `delete e/`

This command allows you to remove any Exercise from the Exercise List just in case you add the wrong Exercise. 

Format: `delete e/LIST_NO d/DD-MM-YYYY` deletes the *n<sup>th</sup>* exercise Item in the Exercise List which contains the date (`DD-MM-YYYY`),
where *n* is the index of the exercise to delete.

❗ `LIST_NO` must be a positive integer within the range of the number of Items in the list.

Example:
```
delete e/1 d/24-10-2021
__________________________________________________________________________________________________________
You have removed the exercise:
    hiit (290 cal) @ 24 Oct 2021
__________________________________________________________________________________________________________
```

💡   **Tip:** If you wish to remove all the Exercise Items from the Exercise List, there is a shortcut command: `delete e/all`.

```
delete e/all
__________________________________________________________________________________________________________
All exercise items have been removed.
__________________________________________________________________________________________________________
```

### **4.4 Scheduling Your Exercises**

This feature allows you to record all your Upcoming Exercises into the Upcoming Exercise List.

#### 4.4.1 Adding Upcoming Exercise Items `add e/`

This command adds an Upcoming Exercise into the Upcoming Exercise List.

Format:`add e/ITEM {c/CALORIES} {d/DD-MM-YYYY}` adds an Upcoming Exercise with its respective calories burnt on the given date (`DD-MM-YYYY`}.


❗️ The date `DD-MM-YYYY` provided must be in the future. Otherwise, if it is within 7 days before today, it will be
  added to the [Exercise List](#431-adding-exercise-items-add-e) instead.

`add e/hiit c/290` adds record of exercise done: hiit with 290 calories burnt to the upcoming exercise list.
```text
add e/hiit c/290 d/01-01-2022
__________________________________________________________________________________________
An exercise item for the future has been added:
    hiit (290 cal)
   ```

#### 4.4.2 Adding Recurring Exercise Items `add r/`

This command is used to schedule exercises that you do on a regular basis. This can be particularly useful if you have weekly trainings to record!

Format: `add r/ITEM c/CALORIES :/START_DATE -/END_DATE @/DAY_OF_THE_WEEK` adds recurring exercise with its respective calories burnt to the upcoming list.\
❗ `START_DATE` and `END_DATE` must be in the future.\
❗ `START_DATE` must be before `END_DATE`.\
❗ `START_DATE` and `END_DATE` must follow `DD-MM-YYYY` format.
```text
add r/running c/200 :/01-11-2021 -/30-11-2021 @/14
__________________________________________________________________________________________
Recurring exercise item for the future has been added
__________________________________________________________________________________________
```

#### 4.4.3 Viewing Upcoming Exercise Items `view u/`

This command is used to view all exercises and the calories added that are scheduled to happen on a future date.
Format: `view u/`
```text
view u/
__________________________________________________________________________________________
You have 2 upcoming exercise(s):
    1. hiit (200 cal) (Saturday 01 Jan 2022)
    2. running (300 cal) (Sunday 02 Jan 2022)
__________________________________________________________________________________________
```

#### 4.4.4 Editing Upcoming Exercise Items `edit u/`

This command is used to edit the exercises that are schedule to happen on a future date.
Format: `edit u/LIST_NO {n/NEW_NAME} {c/NEW_CALORIES} {d/NEW_DATE}` edits the n<sup>th</sup> item in the Upcoming Exercise List, where *n* is the index of the Exercise to edit.

❗ `LIST_NO` must be a positive integer within the range of the number of Items in the list.```text
edit u/1 c/150 d/30-11-2021
```text
__________________________________________________________________________________________
Exercise item number 1 has been changed to:
  trg (150cal)
__________________________________________________________________________________________
```

#### 4.4.5 Deleting Upcoming Exercise Items `delete u/`

This command is used to delete an exercise that is scheduled to happen on a future date.
Format: `delete u/LIST_NO` deletes the n<sup>th</sup> Upcoming Exercise Item in the Upcoming Exercise List, where *n* is the index of the exercise to delete.

❗ `LIST_NO` must be a positive integer within the range of the number of Items in the list.

Example:
```
delete u/1
__________________________________________________________________________________________
An exercise item for the future has been deleted:
    running (300 cal)
Number of exercise item(s) left: 1
__________________________________________________________________________________________
```
💡   **Tip:** If you wish to remove all the Exercise Items from the Upcoming Exercise List, there is a shortcut command: `delete u/all`.
```
delete u/all
__________________________________________________________________________________________________________
All future exercise items have been removed.
__________________________________________________________________________________________________________
```

### **4.5. Building your Food Bank**
Do you have certain dishes or snacks that you frequently consume? Instead of having to key in their calories everytime you want to
record them into the Food List, you can add it into the Food Bank once so that the next time you want to record this item in the Food List,
you would only have to provide the name of the item!

#### 4.5.1 Adding Food Bank Items `add fbank/`
You can add a Food Item into the Food Bank using this command.

Format: `add fbank/ITEM c/CALORIES`
```
add fbank/McSpicy Meal c/1081 
__________________________________________________________________________________________________________
A food item has been added to the food bank:
	McSpicy Meal (1081 cal)
__________________________________________________________________________________________________________
```

❗️ The Food Bank cannot have duplicate item names as _Fitbot_ needs to be able to uniquely identify the item calories. Note that
the match for item name is **case-insensitive**. For example, if an item named `potato` already exists in the Food Bank, an item named `POTATO` also cannot be added in.

After adding the item to the Food Bank, you can now add the item into the Food List whenever you want without specifying the calories. 
Simply provide the name of the item and _Fitbot_ will search up the corresponding calorie value for the item with the same name for you from the Food Bank! As before, note that the matching of names is 
**case-insensitive**.

```
add f/mcspicy meal
__________________________________________________________________________________________________________
A food item has been added:
mcspicy meal (1081 cal) @ 02:32, 29 Oct 2021
__________________________________________________________________________________________________________
```

#### 4.5.2 Viewing Food Bank `view fbank/`
You can view all the Items in the Food Bank using this command.

Format: `view fbank/`

```
view fbank/
__________________________________________________________________________________________________________
You have 2 food(s) in your food bank:
	1. Baked Potato (300 cal)
	2. McSpicy Meal (1081 cal)
__________________________________________________________________________________________________________
```

#### 4.5.3 Editing Food Bank Items `edit fbank/`
If you have entered any wrong information or wish to update existing Items in the Food Bank, you can use this command to do so.

Format:  `edit fbank/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES}` edits the n<sup>th</sup> Item in the Food Bank, where *n* is the index of the Food Item to edit.

❗ `LIST_NO` must be a positive integer within the range of the number of Items in the list.

Example:
```
edit fbank/1 c/350
__________________________________________________________________________________________________________
Item number 1 in the food bank has been changed to:
	Baked Potato (350 cal) @ 02:24
__________________________________________________________________________________________________________

```

❗️ Though the parameters are optional, do provide at least one of them so that _Fitbot_ knows what you want to update about the Item! 

#### 4.5.4 Deleting Food Bank Items `delete fbank/`
You can delete an Item from the Food Bank using this command.

Format: `edit fbank/LIST_NO.` deletes the n<sup>th</sup> Item in the Food Bank, where *n* is the index of the Food Item to edit.

❗ `LIST_NO` must be a positive integer within the range of the number of Items in the list.

Example:
```
delete fbank/2
__________________________________________________________________________________________________________
An food item has been deleted from the food bank:
	McSpicy Meal (1081 cal) @ 02:25
Number of food item(s) left in the food bank: 1
__________________________________________________________________________________________________________
```

💡   **Tip:** If you wish to remove all the Food Items from the Food Bank, there is a shortcut command: `delete fbank/all`.


### **4.6. Building your Exercise Bank**
Have a standard workout routine that you use? You can add it into the Exercise Bank for _Fitbot_ to remember the amount of calories you 
burn from it!



#### 4.6.1 Adding Exercise Bank Items `add ebank/`
You can add an Exercise Item into the Exercise Bank using this command.

Format: `add ebank/ITEM c/CALORIES`
```
add ebank/30 min jog c/450
__________________________________________________________________________________________________________
An exercise item has been added to the exercise bank:
	30 min jog (450 cal)
__________________________________________________________________________________________________________
```

❗️ The Exercise Bank cannot have duplicate item names as _Fitbot_ needs to be able to uniquely identify the item calories. Note that
the match for item name is **case-insensitive**. For example, if an item named `jogging` already exists in the Exercise Bank, an item named `Jogging` also cannot be added in.

Similar to the Food Bank, after adding the item to the Exercise Bank, the next time you want to add this item to your Exercise List, you can just provide its name
and _Fitbot_ will search up the corresponding calorie value for the item with the same name for you from the Exercise Bank! As before, note that the matching of names is
**case-insensitive**.

```
add e/30 min jog
__________________________________________________________________________________________________________
An exercise item has been added:
	30 min jog (450 cal) @ 29 Oct 2021
__________________________________________________________________________________________________________
```



Format: `edit u/LIST_NO {n/NEW_NAME} {c/NEW_CALORIES} {d/NEW_DATE}` edits the n<sup>th</sup> item in the Upcoming Exercise List, where *n* is the index of the Exercise to edit.

❗ `LIST_NO` must be a positive integer within the range of the number of Items in the list.

#### 4.6.2 Viewing Exercise Bank `view ebank/`
You can view all the Items in the Exercise Bank using this command.


Format: `view ebank/`

```
view ebank/
__________________________________________________________________________________________________________
You have 1 exercise(s) in your exercise bank:
	1. 30 min jog (450 cal)
__________________________________________________________________________________________________________

```


#### 4.6.3 Editing Exercise Bank Items `edit ebank/`
If you have entered any wrong information or wish to update existing Items in the Exercise Bank, you can use this command to do so.

Format:  `edit ebank/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES}` edits the n<sup>th</sup> Item in the Exercise Bank, where *n* is the index of the Exercise Item to edit.

❗ `LIST_NO` must be a positive integer within the range of the number of Items in the list.

Example:


``` 
edit ebank/1 n/35 min jog
__________________________________________________________________________________________________________
Item number 1 in the exercise bank has been changed to:
	35 min jog (450 cal)
__________________________________________________________________________________________________________

```
❗️ Though the parameters are optional, do provide at least one of them so that _Fitbot_ knows what you want to update about the Item!


#### 4.6.4 Deleting Exercise Bank Items `delete ebank/`
You can delete an Item from the Exercise Bank using this command.

Format: `delete ebank/LIST_NO.` deletes the n<sup>th</sup> Item in the Exercise Bank, where *n* is the index of the Exercise Item to edit.

❗ `LIST_NO` must be a positive integer within the range of the number of Items in the list.

Example:
```
delete ebank/1
__________________________________________________________________________________________________________
An exercise item has been deleted from the exercise bank:
	35 min jog (450 cal)
Number of exercise item(s) left in the exercise bank: 0
__________________________________________________________________________________________________________
```

💡   **Tip:** If you wish to remove all the Exercise Items from the Exercise Bank, there is a shortcut command: `delete ebank/all`.


### **4.7 Viewing Your Calorie Summary** `overview`
This command is used to view the summary of the calories gained from food, calories burnt from exercises as well as
net calories after including BMR.

Format: `overview`

Example:
```text
overview
-*WEEKLY OVERVIEW*-
Hi qwe, this is your calorie summary for the week.

Food:
You have consumed 14600 cal this week from 23-Oct to 29-Oct.
Calorie gained from food (Daily)
23-Oct   █████████████████    2600
24-Oct   █████████████    1900
25-Oct   ██████████████████████████████    4500
26-Oct   ████████████████    2400
27-Oct   █████████████████████    3200
28-Oct       0
29-Oct       0

Exercise:
You have burnt 3090 cal this week from 23-Oct to 29-Oct.
Calorie burnt from exercise (Daily)
23-Oct   ██████    230
24-Oct   ██████████████████████████████    1200
25-Oct   ████████████████████    780
26-Oct   ██████████████    540
27-Oct   █████████    340
28-Oct       0
29-Oct       0

Daily net calories**:
23-Oct :   -1130
24-Oct :   -2800
25-Oct :   220
26-Oct :   -1640
27-Oct :   -640
28-Oct :   -3500
29-Oct :   -3500

Number of supper meals this week: 1

** Net calories = Food consumed - Exercise output - your basal metabolic rate, where 
your basal metabolic rate is a factor of your age, gender, height and weight retrieved from your profile.
All calculations are done in calories.
__________________________________________________________________________________________________________
This is your calorie overview for today:
Your calorie gained from food is: 0
Your calorie lost from exercise is: 0
Your net calorie intake is: -3500
Your calorie goal is: 123
You are 3623 cal away from your goal
__________________________________________________________________________________________________________
```

### **4.8 Viewing Help** `help`

This command shows a list of commands recognised by _Fitbot_ and their usage.

Format: `help`

Example:

```text
help
__________________________________________________________________________________________________________
Welcome to the help page.
Below are the commands to get you started.
More details could be found on: 
https://tinyurl.com/fitbot-user-guide

In the formats of the command, prefixes wrapped in curly brackets{} means that they are optional.

add -- Add food or exercise record to the current list.
      Add Food Item
      Format: add f/ITEM {c/CALORIES} {d/DD-MM-YYYY} {t/HHMM}
        Prefix               Input
          f/             Description of the food item
          c/             Calories of the food
          d/             Date of food in DD-MM-YYYY
          t/             Time of food in HHMM

      Add Exercise Item
      Format: add f/ITEM {c/CALORIES} {d/DD-MM-YYYY} {t/HHMM}
        Prefix               Input
          e/             Description of exercise
          c/             Calories burnt from exercise
          d/             Date of exercise in DD-MM-YYYY

      Add Recurring Exercise to Upcoming Exercise List
      Format: add r/ITEM c/CALORIES :/START_DATE -/END_DATE @/DAY_OF_THE_WEEK
      Format: delete e/LIST_NO. d/DD-MM-YYYY
        Prefix               Input
          r/             Description of upcoming exercise
          c/             Calories burnt from exercise
          :/             Start date of exercise in DD-MM-YYYY
          -/             End date of exercise in DD-MM-YYYY
          @/             Workout days of the week

      Add Food Item in Food Bank
      Format: add fbank/ITEM c/CALORIES
        Prefix               Input
          fbank/         Description of food
          c/             Calories burnt from exercise

      Add Exercise Item in ExerciseBank
      Format: add ebank/ITEM c/CALORIES
        Prefix               Input
          fbank/         Description of food
          c/             Calories burnt from exercise

bmi -- Calculate the Body-Mass-Index of user
      Format: bmi {h/HEIGHT_IN_CM w/WEIGHT_IN_KG}
        Prefix          Input 
	      h/             Height of user in cm
	      w/             Weight of user in kg
      If no identifiers are given, bmi will be calculated using the height and weight in the profile.

bye -- Exit the program.
      Format: bye

delete -- Delete entry of food or exercise added from a list.
      Deleting food items
      Format: delete f/LIST_NO d/DD-MM-YYYY t/HHMM
      Identifier         Input
           f/           Index of food in food list within the given date
           d/           Date of food in DD-MM-YYYY
           t/           Time of food in HHMM

      Delete exercise items
      Format: delete e/LIST_NO d/DD-MM-YYYY
      Identifier        Input
          e/            Description of exercise
          d/            Date of exercise in DD-MM-YYYY
	
      Delete Upcoming Exercise Item from Upcoming Exercise List
      Format: delete u/LIST_NO
      Identifier        Input
          u/        The index of the item with in the upcoming exercise list

      Delete food items from food bank
      Format: delete fbank/LIST_NO {n/NEW_NAME} {c/NEW_CALORIES}
      Identifier        Input
          fbank/        The index of the item with in the food bank

      Delete exercise items from exercise bank
      Format: delete ebank/LIST_NO {n/NEW_NAME} {c/NEW_CALORIES}
      Identifier        Input
          ebank/        The index of the item with in the exercise bank

edit -- Edit entry of food or exercise added from a list.
      Edit FoodBank
      Format: edit fbank/LIST_NO {n/NEW_NAME} {c/NEW_CALORIES}
      Identifier        Input
          fbank/        The index of the item with in the food bank
          n/            New description of food name
          c/            Calories of food
	 
      Edit ExerciseBank
      Format: edit ebank/LIST_NO {n/NEW_NAME} {c/NEW_CALORIES}
      Identifier        Input
          ebank/        The index of the item with in the exercise bank
          n/            New description of exercise name
          c/            Calories burnt from exercise

     Edit Upcoming Exercise List
     Format: edit u/LIST_NO {n/NEW_NAME} {c/NEW_CALORIES}
     Identifier        Input
          u/        The index of the item with in the upcoming exercise list

      Deleting Food Item
      Format: delete f/LIST_NO. d/DD-MM-YYYY t/HHMM
        Prefix               Input
          f/             Index of food in food list within the given date
          d/             Date of food in DD-MM-YYYY
          t/             Time of food in HHMM

      Delete Exercise Item
      Format: delete e/LIST_NO. d/DD-MM-YYYY
        Prefix               Input
          e/             Description of exercise
          d/             Date of exercise in DD-MM-YYYY
	
      Delete Upcoming Exercise Items from Upcoming Exercise List
      Format: delete u/LIST_NO.
        Prefix               Input
          u/             The index of the item with in the upcoming exercise list

      Delete Food Item from Food Bank
      Format: delete fbank/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES}
        Prefix               Input
          fbank/         The index of the item with in the food bank

      Delete Exercise Item from Exercise Bank
      Format: delete ebank/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES}
        Prefix               Input
          ebank/         The index of the item with in the exercise bank

edit -- Edit entry of food or exercise added from a list.
      Edit Food Bank
      Format: edit fbank/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES}
        Prefix               Input
          fbank/         The index of the item with in the food bank
          n/             New description of food name
          c/             Calories of food
	 
      Edit Exercise Bank
      Format: edit ebank/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES}
        Prefix               Input
          ebank/         The index of the item with in the exercise bank
          n/             New description of exercise name
          c/             Calories burnt from exercise

     Edit Upcoming Exercise List
     Format: edit u/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES}
       Prefix               Input
          u/            The index of the item with in the upcoming exercise list
          n/            New description of exercise name
          c/            Calories burnt from exercise

help -- View help for commands
      Format: help

profile -- View or modify profile details
      Format: profile n/NAME h/HEIGHT(CM) w/WEIGHT(KG) a/AGE g/CALORIEGOAL s/GENDER(M/F) x/ACTIVITYFACTOR(1-5)
        Prefix                Input 
	      n/             Name of user
	      h/             Height of user in cm
	      w/             Weight of user in kg
	      s/             Gender of user, m for male, f for female
	      a/             Age of user
	      g/             Net calorie goal of user. (Net calorie is based on calorie of food consumed
	                       - calories burnt from exercise and bmr
	      x/             Activity factor of user, range 1 to 5
      If no identifiers are given, user can view the profile particulars.

overview -- View weekly and daily summary of calories
      Format: overview

view -- View all the food and/or exercises added.

      Viewing Food List
      Format: view f/

      View Exercise List
      Format: view e/

      View Upcoming Exercise List
      Format: view u/

      View Exercise Bank
      Format: view e/

      View Food Bank
      Format: view f/
__________________________________________________________________________________________________________
```



### **4.9. Exiting Program** `bye`

This command is used to exit _Fitbot_.

Format: `bye`

```text  
bye  
_________________________________________________________________________________________________________
Exiting Fitbot....  
Bye! Hope to see you again soon!!  
_________________________________________________________________________________________________________  
```  
### 5. Data Management


#### 5.1. Saving The Data

There is no need to save manually. Any updates made to the data will be automatically stored into the local drive and reloaded when _Fitbot_ is restarted.

#### 5.2 Editing The Data File

_Fitbot_ data files are saved as .text files `<JAR file location>/data/<text file name>.txt`. \
Advanced users are welcome to update data directly by editing the data files. 

❗ If your changes to the data files format are invalid, _Fitbot_ will skip the wrongly formatted line when it loads in the data.

❗ Ensure that you quit _Fitbot_ if you would like to edit the files. Edits made directly to files when _Fitbot_ is running will not be saved.

### **6. FAQ**

**Q:** How do I transfer my data to another computer?\
**A:** Zip the folder with _Fitbot_ and its data files, and transfer to the new computer. Extract the zipped folder onto your new computer and follow steps 1, 4 and 5 in [Quick Start](#1-quick-start) to get your _Fitbot_ running on your new computer.

**Q:** How many profiles can I create?\
**A:** _Fitbot_ only supports 1 profile. If you need to make any changes to your current profile, you can refer to [Updating Profile Attributes](#413-updating-profile-attributes).

**Q:**  Why does _Fitbot_ need so many personal particulars?\
**A:** _Fitbot_ needs your height, weight, age, gender and activity factor so that we can calculate your [BMR](https://en.wikipedia.org/wiki/Basal_metabolic_rate).
Calorie goal is required to help you check how close or how far you are away from your calorie goal.
_Fitbot_ needs to know your name to address you.

### **7. Command Summary**


| Action | Format | Examples |
|--------|-------|----------|
add|`add f/ITEM {c/CALORIE} {d/DD-MM-YYYY} {t/HHMM}` <br>`add e/ITEM {c/CALORIES} {d/DD-MM-YYYY}` <br> `add r/ITEM c/CALORIES :/START_DATE -/END_DATE @/DAY_OF_THE_WEEK` <br>  `add fbank/ITEM c/CALORIES` <br>  `add ebank/ITEM c/CALORIES`| `add f/chicken rice c/607 d/20-10-2021`, <br>`add e/hiit c/290 d/23-10-2021` <br> `add r/handball training c/432 :/24-10-2021 -/11-11-2021 @/13` <br>  `add fbank/chicken rice c/667`<br> `add ebank/5km run c/478`
bmi|`bmi h/HEIGHT_IN_CM w/WEIGHT_IN_KG` <br> `bmi`|`bmi h/170 w/65` , <br> `bmi`
bye|`bye`|
delete|`delete f/LIST_NO d/DD-MM-YYYY t/HHMM` <br> `delete e/LIST_NO d/DD-MM-YYYY` <br> `delete f/all` <br> `delete e/all` |`delete f/1`, <br> `delete e/2`, <br> `delete f/all`, <br>  `delete e/all`
edit| `edit u/LIST_NO {n/NEW_NAME} {c/NEW_CALORIES} {d/NEW_DATE}` <br> `edit fbank/LIST_NO {n/NEW_NAME} {c/NEW_CALORIES}` <br> `edit ebank/LIST_NO {n/NEW_NAME} {c/NEW_CALORIES}`| `edit u/1 n/volleyball training c/560 d/24-10-2021` <br> `edit fbank/1 n/2.4km run c/267` <br> `edit ebank/1 n/chicken rice c/800`
delete|`delete f/LIST_NO. d/DD-MM-YYYY t/HHMM` <br> `delete e/LIST_NO. d/DD-MM-YYYY` <br> `delete f/all` <br> `delete e/all` |`delete f/1`, <br> `delete e/2`, <br> `delete f/all`, <br>  `delete e/all`
edit| `edit u/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES} {d/NEW_DATE}` <br> `edit fbank/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES}` <br> `edit ebank/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES}`| `edit u/1 n/volleyball training c/560 d/24-10-2021`, <br> `edit fbank/1 n/2.4km run c/267`, <br> `edit ebank/1 n/chicken rice c/800`
help | `help`|
overview|`overview`|
profile|`profile {h/HEIGHT_IN_CM} {w/WEIGHT_IN_KG} {n/NAME} {s/GENDER} {a/AGE} {g/CALORIE_IN_CAL} {x/ACTIVITY_FACTOR}` <br> `profile`|`profile h/170 w/65 n/John a/22 s/M g/500 x/2`, <br> `profile`
view|`view`|`view e/`,  <br> `view f/`, <br>  `view fbank/`, <br> `view ebank/`, <br> `view u/`




