FILE="queues-submit.zip"

all:
	wget https://algs4.cs.princeton.edu/code/algs4.jar
	jar -xf algs4.jar

compile:
	javac Deque.java
#	javac RandomizedQueue.java
#	javac Permutation.java

run: compile
	java Deque
#	java RandomizedQueue
#	java Permutation

clean:
	find . -type f -not -name Deque.java -not -name RandomizedQueue.java -not -name Permutation.java -not -name Makefile -delete
	rm -rf META-INF
	rm -rf edu

submission: clean
	zip $(FILE) Deque.java RandomizedQueue.java Permutation.java
