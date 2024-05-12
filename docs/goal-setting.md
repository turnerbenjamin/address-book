# Goal Setting

## The Goal

I can't see how to access the smart goal I submitted, but it was along the lines of:

>Improve confidence in using debugging processes by using debugging for each failing test 
and recording how this went after testing each class. 

If this is the case, then I need to make an edit and record the process
after each user story. This is because I am only writing the tests required
for the user story I am currently working on. 

## After User Story 1

I did not perform any debugging during these tests. This was because
I had forgotten that I had set the goal. 

## After User Story 2

I started debugging after writing each failing test. The tests were all
trivial, but it was useful to get familiar with the interface. 

## After User Story 3

This User Story was essentially the same as the above - However, it involved two tests which
were slightly more complex as they involved regex. The debugging here was useful, and I set a watch value
on the results of matching to the expressions.

## After User Story 4

This story involves searching by name. Debugging was generally unhelpful, beyond getting used to the interface. 
However, I did find it useful when debugging one test (AB10); here I assumed an issue with the logic, but I had
just passed the wrong variable to contains - The debugger made the issue here very apparent. 

I have also been trying to use the keyboard shortcuts to speed up the process; though I am still having to keep 
double-checking these.

## After User Story 5

No real progress here - But continued using shortcuts when debugging. Again, the story was trivial. 

## After User Story 6

Found debugging helpful here - There was an error in a test re prevention of duplicates
I had added the duplicate but not the original. Debugging revealed this error as I noticed
that the relevant method was invoked just once. 

I also noticed that the moment there is an unexplained fail I am instinctively starting
the debugging process. 

## After User Story 7

I stopped debugging for every test because I am feeling comfortable with the process. I did make
fairly heavy use of debugging in this user story, particularly the tests for the AddressBook class. 

For example, I was getting an error with AB26 because I was checking equals on null. The debugger was useful here
as I could step into invoked methods to see where null was coming from.

## After User Story 8

No debugging, the tests were very basic and did not require any change in implementation. Note: I did
run each test with false expected values to ensure that the tests would fail.

## After User Story 8
Debugging used quite a bit here - For the ConsoleInterface, debugging was used to look at String values
where ANSI codes used. 

For the AddressBookApp tests debugging was used to fix tests after a loop was added to get user input. 

I have not had the impulse to log any values to the console for the purposes of debugging. 

## After User Story 9
Debugging used to understand how ArgumentCaptor from mockito was working. I had expected
arguments captured to be ordered by method invocation - But they seem to be ordered first by method,
i.e. arguments passed to the first method verify is used on come first, and then invocation order. 

I am more used to using step into and step over correctly. I have not been using step-out but will make
an effort to use that in the next story.