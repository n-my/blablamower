Here is a bit of context on this assignment.

### Why Scala?

I'm mostly experienced in OOP, but I wanted to take the opportunity of this assignment to learn more about functional programming.
So I wrote this application in Scala, using as much as I could a functional programming style.

Another reason to use Scala was how easy it is to package the application into a JAR that can be run from anywhere
as long as there is a JVM 1.8 accessible.

### Why isn't the parser more robust/unit tested?

This assignment is time bounded!
I chose to spend most of my time on the business logic part of the app (what is inside the `com.blablamower.domain` package)
and I quickly implemented the reader and parser at the end. If this app were to go to production, I would have spent more
time validating the input file content to prevent from edge cases.

Apart from that I would like to improve the unit testing coverage, and implement a proper exception handling rather than
simply throwing `RuntimeException` when things go bad.

### Why the mowers aren't moved in parallel rather than sequentially?

The assignment instructions say `Your simulation will be run on a machine with multiple CPUs so multiple mowers should beprocessed
simultaneously in order to speed up the overall execution time.` So why does this app process one mower at a time?

While processing one mower at time doesn't prevent the application to use multiple CPUs, it is correct that the
application doesn't meet this requirement. We decided to process the mowers sequentially to keep the application simple.
However, mower parallel processing is on the specifications of the V2! 
