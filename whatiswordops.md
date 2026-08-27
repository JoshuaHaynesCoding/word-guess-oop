# What Is Word Ops?

Word Ops is a Java word-guessing game with a military intelligence theme. The player acts like a field agent using a classified terminal to identify a hidden five-letter challenge phrase. Each guess gives feedback about which letters are correct, misplaced, or not in the word, similar to classic word puzzle games, but presented through a custom "mission terminal" style interface.

The game includes multiple mission modes: Boot Camp, Standard Mission, and Clandestine Op. Each mode changes the difficulty by adjusting things like the number of allowed guesses and the feedback style. Players can also use built-in commands such as `INTEL` for help, `ABORT` to quit the current mission, and `RESET` to start over.

Behind the scenes, Word Ops is built with object-oriented Java. The project separates the game logic, command handling, display output, scoring events, game modes, and word selection into different classes. That makes the code easier to extend, so new modes, themes, commands, or word sources could be added without rewriting the whole game.

I built Word Ops as both a playable desktop GUI and an object-oriented programming project. The goal was to make something that demonstrates software design concepts while still feeling like an actual game someone could open, play, and understand quickly.
