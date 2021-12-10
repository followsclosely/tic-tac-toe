# Tic-Tac-Toe AI Challenge

## Getting Started

### Clone the tic-tac-toe Repository

To get started you will need to first download the [code](https://github.com/followsclosely/tic-tac-toe). If you are
using IntelliJ IDEA detailed instructions can be found on
[IntelliJ website](https://www.jetbrains.com/help/idea/manage-projects-hosted-on-github.html).

### Create a Subproject

To get started building your own AI you will need to copy the "entries/copy-me-to-get-started" directory. For the sake
of this Getting Started guide we will assume that you copied the directory to entries/my-ai.

This new entries/my-ai directory needs to be registered as a
[subproject](https://docs.gradle.org/current/userguide/multi_project_builds.html)
in Gradle. To register your new directory as a subproject add the following line to the root
[settings.gradle](https://github.com/followsclosely/connect-four/blob/master/settings.gradle) file.

```properties
include 'entries/my-ai'
```

After adding the entries/my-ai directory as a subproject you will have to
[refresh](https://www.jetbrains.com/help/idea/work-with-gradle-projects.html#gradle_refresh_project)
Gradle.

### Start Authoring Your AI

The directory you copied already has a class named
[YourCustomAI](https://github.com/followsclosely/tic-tac-toe/blob/master/entries/copy-me-to-get-started/src/main/java/io/github/followsclosely/YourCustomAI.java)
that extends the
[AbstractAI.java](https://github.com/followsclosely/tic-tac-toe/blob/master/core/src/main/java/io/github/followsclosely/ttt/ai/AbstractAI.java)
class. The AbstractAI.java implements the
[ArtificialIntelligence](https://github.com/followsclosely/tic-tac-toe/blob/master/core/src/main/java/io/github/followsclosely/ttt/ArtificialIntelligence.java)
interface.

[YourCustomAI](https://github.com/followsclosely/tic-tac-toe/blob/master/entries/copy-me-to-get-started/src/main/java/io/github/followsclosely/YourCustomAI.java)

```java
public class YourCustomAI extends AbstractAI {

    /**
     * This method is called by the Engine when it is "your" turn to play.
     * It should return the position to draw your X.
     *
     * @param board The current state of the game.
     * @return The Position you want to play your X
     */
    @Override
    public Coordinate yourTurn(Board board) {

        //Your shape is this.shape
        //Your opponent is this.opponent

        //Your logic replaces this line below.
        return new DummyAI().yourTurn(board);
    }
}
```

### Testing Your AI (Swing)

You can test your AI using a graphical interface:
[SwingLauncher.java](https://github.com/followsclosely/tic-tac-toe/blob/master/entries/copy-me-to-get-started/src/main/java/io/github/followsclosely/SwingLauncher.java)

```java
public class SwingLauncher {
    public static void main(String[] args) {
        new SwingSupport().setArtificialIntelligence(new YourCustomAI()).run();
    }
}
```

### Testing Your AI (Command Line)

You can test your AI using by running 1,000s of simulations:
[SwingLauncher.java](https://github.com/followsclosely/tic-tac-toe/blob/master/entries/copy-me-to-get-started/src/main/java/io/github/followsclosely/ShellLauncher.java)

```java
public class ShellLauncher {
    public static void main(String[] args) {
        new Simulation()
                .number(10000)
                .ai1(new YourCustomAI())
                .run()
                .printSummary();
    }
}
```

## Current Implementations

### The win percentage of current AI implementations:

| | Class Name |  #0 |  #1 |  #2 |  #3 | 
| ---: | :--- |  :---: |  :---: |  :---: |  :---: | 
| #0 |  io.github.followsclosely.ttt.ai.DummyAI  |  -  |  %0.47  |  %0.0  |  %0.0  | 
| #1 |  class io.github.followsclosely.ttt.stink.StinkAI  |  %93.14  |  -  |  %0.0  |  %0.0  | 
| #2 |  class io.github.followsclosely.ttt.mm.MiniMax  |  %93.72  |  %50.0  |  -  |  %0.0  | 
| #3 |  class io.github.followsclosely.ttt.mm.MiniMaxWithAlphaBeta  |  %93.64  |  %50.0  |  %0.0  |  -  | 

### Wins or Ties

| | Class Name |  #0 |  #1 |  #2 |  #3 | 
| ---: | :--- |  :---: |  :---: |  :---: |  :---: | 
| #0 |  io.github.followsclosely.ttt.ai.DummyAI  |  -  |  %6.53  |  %6.6200004  |  %6.91  | 
| #1 |  io.github.followsclosely.ttt.stink.StinkAI  |  %99.61  |  -  |  %50.0  |  %50.0  | 
| #2 |  io.github.followsclosely.ttt.mm.MiniMax  |  %100.0  |  %100.0  |  -  |  %100.0  | 
| #3 |  io.github.followsclosely.ttt.mm.MiniMaxWithAlphaBeta  |  %100.0  |  %100.0  |  %100.0  |  -  | 

### Performance (in millis)

| | Class Name | Performance |
| ---: | :--- | :---: |
| 1 |  io.github.followsclosely.ttt.ai.DummyAI | 2
| 2 |  io.github.followsclosely.ttt.stink.StinkAI | 481
| 3 |  io.github.followsclosely.ttt.mm.MiniMax | 274932
| 4 |  io.github.followsclosely.ttt.mm.MiniMaxWithAlphaBeta | 38548
