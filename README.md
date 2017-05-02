# Tf-idf
Calculate Tf-idf using 2D arrays, and make use of Java 8 stream API which is able to handle large documents.

The whole idea is that we construct a fixed 2D array of Terms and Documents, which will look like this

        Doc1  Doc2  Doc3  Doc4
TermA   0     0     0     0
TermB   0     0     0     0
TermC   0     0     0     0

Because it is an array, so it is very quick to find and update elements inside.
The process consists four steps:

Let's take an example like this.

Doc1: "I love Apple"
Doc2: "Apple is great, Apple iphone is the best"
Doc3: "Microsoft is horrible"
Doc4: "Microsoft is beaten by apple"

STEP 1: Get all words in the corpus
Find all of the terms in the given corpus, so we will have something like
"I" "love" "Apple"
"Apple" "is" "great", etc

STEP 2: Find unique words
Put all of those words into a Set, which will remove all duplicates and we got a unique terms set.
A Set doesn't provide ability to find an index by Object (String here), so we need to convert it back to a List.

STEP 3: Iterate all of the words, and increment them.
Iterate all of the words in STEP 1, search for its index in the list in STEP 2, increment by 1 if found. 
So the 2D array now becomes:

          Doc1  Doc2  Doc3  Doc4
Apple     1     2     0     0  // Note that my implementation is case-sensitive so "apple" won't be picked up
Microsoft 0     0     1     1
others    .     .     .     .  // Other words, you get the idea

STEP 4: Calculate necessary data
With that table filled in, it's easy to extract the other data: tf, df, tf-idf, or even average tf-idf
