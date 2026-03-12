# StudySessionTracker
Simple StudySessionTracker application on Java console  

## Features:  
- Start and stop study sessions
- Track study time for today
- Track study time for the last 7 days
- View statistics by subject
- Automatic session saving
- Data persistence using file serialization

## Technologies:
- Java core
- ArrayList
- HashMap
- LocalDateTime API
- Object Serialization

## Project Structure
StudySessionTracker  
|  
|--- Main.java  
|--- SessionManager.java  
|--- Session.java  
|--- sessions.dat  

- Main.java — contains the main program loop and user menu.
- SessionManager.java — handles all core logic such as starting/stopping sessions, statistics calculation, and data persistence.
- Session.java — represents a study session object with subject, start time, and end time.
- sessions.dat — automatically created file that stores saved sessions.
  
## What I Learned
- While developing this project I practiced several core Java concepts:
- Working with object-oriented design (separating logic into classes like Session, SessionManager, and Main)
- Using Java collections such as ArrayList and HashMap
- Handling dates and time with the java.time API (LocalDateTime, Duration, ChronoUnit)
- Implementing data persistence using Java serialization (ObjectOutputStream / ObjectInputStream)
- Building a console-based interactive menu using Scanner
- Designing simple program architecture for a small CLI application
