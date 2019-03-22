all:
	wget https://algs4.cs.princeton.edu/code/algs4.jar
	jar -xf algs4.jar

compile:
	javac Percolation.java

run: compile
	java Percolation

clean:
	find . -type f -not -name Percolation.java -not -name Makefile -delete
	rm -rf META-INF
	rm -rf edu
