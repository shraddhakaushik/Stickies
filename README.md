# CPSC210 Personal Project

## Sticky Notes Application

The concept that I am choosing to pursue for my *CPSC 210* personal project is that of a **sticky
note software** with numerous useful features that give the user control over their virtual sticky notes.
As a student, my laptop's inbuilt Stickies application is one that I use for nearly everything; whether it is to 
note down reminders, a to-do list, note down my thoughts, or work out my day's schedule, the app is always so 
convenient.

However, there are certain things that the desktop app does not do that I would find really useful in such a software, 
so 
I will be instead creating my own sticky note application with features that I think would make the app incredibly 
useful to me,
as well as to other students and professionals working centrally with their computers.


**USER STORIES:**

*IMPLEMENTED*:
- As a user, I want to be able to create a new sticky note at any point
- As a user, I want to be able to add any number of notes to a sticky note
- As a user, I want to be able to save any number of sticky notes to a list of saved notes
- As a user, I want to be able to name my sticky note
- As a user, I want to be able to type on my sticky note
- As a user, I want to be able to clear my notes from a sticky note
- As a user, I want to be able to find, access, and view any saved sticky note from my list of saved notes
- As a user, I want to be able to quit my note at any point
- As a user, I want to be able to save my notes to a file system to exist even after the program has terminated
- As a user, I want to be able to access my saved notes from a file 
- As a user, I want to be able to access, edit, and continue to take notes on a saved note
- As a user, I want my sticky note to actually resemble a sticky note
- As a user, I want to be able to customise the colour of my sticky note
- As a user, I want to be able to change my typing font
- As a user, I want my note to play a sound every time I open a new note
- As a user, I want to be able to change the size of my font
- As a user, I want to be able to italicize or bold my sticky note
- As a user, I want to be able to save a note temporarily 
- As a user, I want my main saved menu to update alongside the individual note's saved menu

*GOALS*:
- As a user, I want to be able to create my own typing shortcuts
- As a user, I want my sticky note to be able to perform simple arithmetic calculations
- As a user, I want my sticky note to be able to keep track of the time at which I have created the note
- As a user, I want to be able to input images onto my sticky note


*Phase 4: Task 2*:

For phase 4, I implemented a bi-directional association between StickyNoteApp and NotesApp. NotesApp 
refers to StickyNoteApp to get the saved menu that lives within StickyNoteApp, as well as to load saved notes 
from a file and set as a parent every time a NewNote object is created. StickyNoteApp knows of NotesApp to be able 
to update the menu in NotesApp every time the menu in StickyNoteApp is updated, as well as to feed the NotesApp object 
as a parameter for some of StickyNoteApp's other associated classes.

*Phase 4: Task 3*:
- The current design involves a lot of coupling between classes which, ideally, I would be able to spend quite a bit of 
  refactoring and fixing. There is especially quite a bit of intricate coupling in the UI class from before I learnt it 
  was an issue. 
- The EditNote class is not very cohesive, as it has font editing, colour editing, and renaming all in one class with 
  multiple action listener classes within. With more time, I would extract separate classes for font and colour editing.
- Some subtypes of the Options abstract class violate Liskov's substitution principle in terms of taken
  parameters, so I would introduce the extra parameters to the Options class so that none of the subclasses would be
  adding any extra params, and the classes that don't require the params to do anything special won't have to change
  much about their implementations either
  