# Game Overview:
## Core Gameplay:
- The player attempts to guess a word, one letter at a time.
- The player can also attempt to guess the entire word directly.
- The word is initially displayed with underscores representing each hidden letter.
- With each incorrect guess, the player loses one of six lives, represented graphically by a hanging figure (the hangman).
- The player wins if they guess the word before losing all lives and loses if they run out of lives.
## Main Features:
### 1. Graphical Display:
- The hangman drawing is updated with each wrong guess. Initially, only the base is shown, and more parts of the hangman (e.g., rope, head, torso, arms, legs) are drawn as the player loses lives.
- The game also includes text fields and buttons for entering guesses.
- The interface uses panels for organization:
  - Upper Panel: Displays the game logo and title.
  - Middle Panel: Displays the hangman drawing and the hidden word with underscores.
  - Bottom Panel: Provides input fields and buttons for guessing.
### 2. User Input:
- The player can input guesses either as individual letters or attempt to guess the full word.
- If the player guesses a letter that is part of the word, the game updates the hidden word display with the correct letter in place of the underscore.
- Repeated letters or incorrect guesses result in warnings or lost lives.
### 3. Audio Feedback:
- Correct Guess: A sound plays when the player guesses a correct letter.
- Incorrect Guess: A sound plays when the player guesses a wrong letter.
- Win/Loss Sounds: Different sounds are played when the player wins or loses the game.
### 4. Life Tracking & Endgame:
- The player starts with 6 lives.
- The player loses a life for each incorrect guess or if it enters a letter twice.
- If the player guesses the entire word correctly, they win.
- The game ends when the player either guesses the word (win) or runs out of lives (loss). The graphical hangman completes at the loss.
- After a win or loss, the player can choose to start a new game or exit.
### 5. Animations & Graphics:
- The game uses custom drawing via the paintComponent method to update the hangman figure based on the remaining lives.
- Different stages of the hangman (base, rope, head, body, arms, legs) are drawn depending on how many lives the player has left.
- Special graphical indications are made for the win (a cheerful hangman) and the loss (X-ed out eyes).
### 6. Customization:
- The player can input their own word for others to guess, allowing for a customizable gaming experience.
## Technical Details:
- GUI Framework: The game uses Java Swing for its GUI components, such as JFrame, JPanel, JButton, JTextField, and JLabel.
- Audio Integration: Sounds are played using Java’s AudioInputStream and Clip classes from the javax.sound.sampled package.
- Word Management: The hidden word is managed as a string and updated progressively with the correct guesses, while underscores are displayed for unguessed letters.
- Event Handling: Buttons and text fields are linked to action listeners to handle user input (e.g., guessing a letter or word).
- This game provides a simple but interactive graphical experience with visual, audio, and input elements combined for an engaging game of hangman. 
