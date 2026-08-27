# Word Guess OOP Project

## Sprint 1 Checklist

- Are you in a Group?  
  no

- If so, who else is in your group?  
  n/a

- What programming language are you selecting? Which version?  
  java 21

- Do you have your GitHub account set up?  
  yes my username is JoshuaHaynesCoding

- Do you have a public repository for your Project?  
  yes

- What is the link to your GitHub repository?  
  https://github.com/JoshuaHaynesCoding/word-guess-oop

- If you are in a group, does everyone have access to the github repo?  
  n/a

- Do you have a “Hello World” program that compiles and runs?  
  yes

- Where is the entry point to your project?  
  src/main/Main.java

## Project Structure

word-guess-oop/
- README.md
- src/
  - main/
    - Main.java
- lib/

## Project Description

i am planning to make a Java based word guessing game think like Wordle basically. the goal is to build a modular oop that can later demonstrate custom design patterns. i want to be able to expand and grow on this project later as i become a bette programmer

## Sprint 2 Project Decision

### Project Chosen

i am going to build a wordle clone called Word Opps. think like a word guessing game, but i want to expand with topic ideas, hints, etc. i want to inject some militaryness with the color pallete and fonts (think like an ammo crate, camoflauge, ghillie netting, olive green or khaki color, etc) the game is inspired by wordle, but it will have its own personality and structure. the player will have a limited number of guesses to figure out a hidden five-letter word. after each guess, the program will give feedback showing which letters are correct, misplaced, or not in the word.

### Why This Project Is Large Enough

so the project is small enough for me to realistically complete before the end of the term, but large enough to demonstrate oop and custom design patterns. the final version can be split into multiple classes, such as a game controller, word provider, guess evaluator, feedback result, player statistics, and user interface (and whatever else i will need i suspect i will need more then that) later sprints can include design patterns such as Singleton, Factory, Strategy, Observer, State, Command, and Facade

### Programming Language

java 21.0.11

### Libraries

no outside libraries are planned atm i think i am only going to use the standard ones, this may change though

### Sprint 2 Goal

reate starter code for a working console version of the game. the program should be able to run, choose a word, accept guesses, and show a little feedback. at this moment i just want to lay the bones down
-----------------------------------------------------
## Sprint 3 Progress

for sprint 3 i refactored the Word Ops project to include two design patterns from class: the Strategy Pattern and the Factory Pattern.

### Strategy Pattern

 Strategy Pattern is used for guess evaluation. instead of hardcoding one type of feedback directly inside the game loop, the project  has a `GuessEvaluationStrategy` interface. Different feedback styles can implement this interface!

Current strategy classes:

- `MilitaryEvaluationStrategy`
- `ClassicEvaluationStrategy`

The `GuessEvaluator` class uses a `GuessEvaluationStrategy` object to evaluate guesses. This makes the feedback system easier to change or extend without rewriting the main game logic.

### Factory Pattern

The Factory Pattern is used for creating game modes. The `GameModeFactory` class creates a `GameConfig` object based on the player's selected mode.

Current game modes:

- Training : 6 guesses with military-style feedback
- Hard : 4 guesses with military-style feedback
- Classic: 6 guesses with classic Wordle-style feedback this mode is the most familiar

This keeps mode creation logic out of `Main.java` and makes it easier to add new modes later.

##  Submission Plan

 The user will be able to select a game mode, guess a hidden 4-letter word, and receive feedback based on the selected mode. I also want to explain how the Strategy Pattern makes feedback styles interchangeable and how the Factory Pattern organizes game mode creation maybe

##  Problems / Risks

 I had to refactor the original simple version of the project so the patterns are actually used by the game. Another possible issue is that the project is still a small console program, so I need to make sure the UML diagram and README clearly explain why these patterns improve the structure. the last time i did a uml diagram was either like on a test or for giant projects like for the capstone class or managning global systems, so i wonder if making one for a tiny project like this one right now will be hard.
----------------------------------------------------------------------------
 ## sprint 4 progress

for sprint 4, i fixed the factory issue from the previous sprint and added two more design patterns that are actually used 

### fixed pattern: factory method pattern

in the last sprint, my original `GameModeFactory` was closer to a simple factory, not a true factory method or abstract factory. for this sprint, i refactored that design into a factory method pattern

the project now uses an abstract creator class called `GameModeCreator` and concrete creator classes called `TrainingModeCreator`, `HardModeCreator`, and `ClassicModeCreator`

each creator class overrides `createGameConfig()` to create its own version of a `GameConfig`. the `GameModeSelector` chooses which creator should be used based on the player's menu choice, and then `Main` calls the factory method to create the selected game mode.

this makes game mode creation easier to extend because i can add a new mode by creating a new creator class instead of putting all object creation logic into one large factory class.

### new pattern: command pattern

the command pattern is used for player actions. instead of the game loop directly handling every possible input, player input is parsed into command objects.

the command related classes are `GameCommand`, `GuessCommand`, `HelpCommand`, `QuitCommand`, `CommandParser`, `CommandResult`, and `GameContext`.

for example, when the player types a guess, the game creates a `GuessCommand`. when the player types `help`, the game creates a `HelpCommand`. when the player types `quit`, the game creates a `QuitCommand`.

this makes the input system more modular because new commands can be added without rewriting the main game loop.

### new pattern: observer pattern

the observer pattern is used for game events. the game now has an event manager that can notify listener classes when important events happen.

the observer related classes are `GameEventListener`, `GameEventManager`, `ScoreTracker`, and `ConsoleGameLogger`.

the `ScoreTracker` listens for game events such as valid guesses, invalid guesses, wins, and losses. at the end of the game, it prints a mission summary. this keeps score tracking separate from the main game logic.

### current design patterns used

at this point, the project uses four custom design patterns:

1. strategy pattern
2. factory method pattern
3. command pattern
4. observer pattern

## updated final submission plan

for the final submission, i want to make word ops feel more like a real playable game instead of only a terminal program. i plan to add a pretty java gui with a themed background, buttons, a guess input box, a feedback history area, and a more polished military or intelligence style. think like the matrix green numbers, camoflauge, computers, stuff like that

i had to make a gui for my capstone class, we made a full tetris clone, so i want to apply some of that experience here too. the goal is to keep the current game logic working underneath, but put a better looking interface on top of it for the final demo.

i may also add an optional ai powered word source in the future. the offline version would use the built in word list, while an online version could generate themed words related to military, intelligence, cybersecurity, or software. i would keep the offline version working so the project can still be demonstrated without internet access.

## sprint 4 problems / risks

one issue this sprint was that my previous factory implementation did not count as a true factory method or abstract factory, so i had to refactor it. i also had to make sure the command and observer patterns were actually used by the playable game instead of just existing as extra unused classes.

another risk is that the project is still terminal based, so the final version needs more visual polish. i want to add a gui, but i need to make sure the design patterns stay clear and easy to explain instead of getting buried under the interface work.
----------------------------------------------------------------
## final sprint 

for this sprint, i implemented the remaining two design patterns needed to bring the total to 6. the two new patterns added this sprint are the singleton pattern and the adapter pattern

### new pattern: singleton pattern

the singleton pattern is used in `GameSettings`.

`GameSettings` stores shared settings for the game, such as the word length, theme name, and debug mode. the constructor is private, so other classes cannot create new settings objects directly. the only way to access the settings is through `GameSettings.getInstance()`

this gives the project one shared place for game settings instead of hardcoding values like the word length in multiple classes. `Game` uses it to print the theme and word length, and `WordProvider` uses it when checking if a guess is valid.

### new pattern: adapter pattern

the adapter pattern is used for game display output.

the project now has a `GameDisplay` interface that the game can use for showing messages and prompts. `TerminalOutput` represents the existing terminal output behavior, and `ConsoleDisplayAdapter` adapts that terminal output to match the `GameDisplay` interface

this makes the project easier to extend later because a future gui display can use the same game logic without rewriting the game itself. instead of the game being directly tied to `System.out.println`, the game now talks to a display interface

### current design patterns used

the project uses six custom design patterns:

1. strategy pattern
2. factory method pattern
3. command pattern
4. observer pattern
5. singleton pattern
6. adapter pattern

### how the patterns are used together

the strategy pattern is used to switch between different guess feedback styles. the factory method pattern is used to create different game modes. the command pattern is used to handle player actions like guessing, help, and quitting. the observer pattern is used to track game events and update the score tracker. the singleton pattern is used for shared game settings. the adapter pattern is used to separate game output from the terminal display.

## updated final submission plan

for the final submission, i want to show word ops as a complete playable word guessing game. the current version works in the terminal, but i also want to build a pretty java gui on top of it. i had to make a gui for my capstone class like a I mentioned before, so i want to apply some of that experience here too.

the gui version could include a themed background, buttons, a guess input box, a feedback history area, sound effects, and a more polished military intelligence style. the current design should make that easier because the game logic is separated from parts of the display, command handling, game mode creation, and settings.

i may also add an optional ai powered word source later, but the main goal is to keep the offline version working reliably for the final demo. the offline version will continue using the built in word list so the project can be demonstrated without internet access.

## final sprint problems / risks

one risk is making sure the singleton and adapter patterns are actually used by the program instead of only existing as extra classes. to avoid that, `GameSettings` is used by `Game` and `WordProvider`, and the display adapter is used by `Game` and `Main`.

another risk is that adding a gui could take more time than expected. because of that, i want to keep the terminal version working as a backup while building the gui separately. i am aimint to be done with this totally by 06/06 in case I run into any hiccups.

another issue is that i am still not great at using the uml website. the current uml diagram includes the required classes and patterns, but it is not as clean visually as i want it to be. for the final commit, i plan to make a proper polished uml diagram that is easier to read, possibly color coordinated and maybe made with a different tool.

## final submission notes

word ops is now a fully playable five letter word guessing game with a classified military terminal themed gui. the player can choose a mission mode, enter guesses, receive colored feedback, use the intel/help command, reset the mission, or abort the mission.

i also finally got a decent looking uml.

the final version uses six custom design patterns:

1. strategy pattern
2. factory method pattern
3. command pattern
4. observer pattern
5. singleton pattern
6. adapter pattern

the project can be compiled and run with:


java -cp src/main Main (terminal version) or


```bash
javac src/main/*.java
java -cp src/main WordOpsGui

