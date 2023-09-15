# Reflection Questions

Answer these questions thoroughly, using examples from your code. Good answers will be 1-2 paragraphs that cite specific code examples and show a meaningful reflection on how your development went, and how it could be improved in the future.

## Question 1

In part 2, you now have to support two different inputs, CSV and XLSX. Imagine we asked you to also support reading in another file format, such as JSON. How much code would you need to add/change to enable this? Cite specific existing functions/classes that would need to change.

## Answer

I would need to create a `JSONReader` class that implements my `Reader` interface.
I would need to implement the `getStatePopulations()` method for the new `JSONReader`.
The following are trivial changes:
The `JSONReader` class may contain methods that throw unique error messages that I would have
to add to the `ErrorMessages` class.
I would need to change `getReader()` and `isValidExtension()` in `MainTools` to account for
Json files.
Ultimately it would not be that much code as Json files are easier to parse, and thus 
`getStatePopulations()` in `JSONReader` will be short and will require less helper methods.



## Question 2

Looking back, which part was more difficult? Part 1, where you had to start from scratch, or Part 2, where you had to change existing code. Explain your answer, citing any specific challenges. In hindsight, would you do anything differently?

## Answer

Part 2 was more difficult because I decided to refactor most of my code.
My code contained verbose methods and achieved little to no abstraction.
I spent most of my time extracting methods and creating new classes.
For example, my `CSVReader` class contains 11 helper methods when it previously contained 3.
I created a `Reader` interface which helped to simplify this snippet of my main method:<br>
<pre>
var reader = MainTools.getReader(filePath, fileExtension);<br>
var stateData = new StateData(reader.getStatePopulations());<br>
</pre>
I created the `MainTools` class to store miscellaneous methods that are used by my 
`Main` class.
I also started unit testing in Part 2 with my `GetFileExtensionTest` Testing class.
These were things I did not consider in Part 1 it contained less code.
However, as the size of the project grew, I realized I needed to address code
that was not readable, understandable, nor efficient.


