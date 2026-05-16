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