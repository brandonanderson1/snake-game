# Snake

A grid-based Snake game built in Java on an instructor-provided game engine base, featuring a level system with multiple apples, collision-safe spawning, and a death counter.

## Features

- **Classic Snake gameplay** — directional movement, growing snake body, wall and self-collision detection
- **Level system** — levels increase with score, spawning additional apples (up to 3 at once) as levels rise
- **Collision-safe apple spawning** — apples spawn only on open tiles, avoiding the snake's body and other apples
- **Score & stats tracking** — live score, level, and death count displayed on screen
- **Start & game-over screens** — a start prompt before the game begins, and a game-over overlay with a restart prompt
- **Grid-based board** — 15x15 tile board built from individual `Tile` objects

## Tech Stack

- **Language:** Java
- **Libraries:** Java AWT/Swing (rendering, input handling)

## How to Run

1. Clone the repo
2. Compile the `.java` files
3. Run `SnakeRunner`

## Project Structure

- `GDV5` — game engine base (provided as part of a course), handling the game loop, rendering, and input; also used in my [Pong](../pong) project
- `SnakeRunner` — main game loop, level logic, apple spawning, and start/game-over states
- `Snake` — snake body, movement, direction handling, and collision/growth logic
- `Apple` — apple position and rendering
- `Tile` — individual grid tile, used to build the board and track the snake's body
- `Score` — score, level, and death-count tracking and display
