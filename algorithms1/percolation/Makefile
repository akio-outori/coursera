SIZE="20"
FILE="percolation-submit.zip"

all:
	wget https://algs4.cs.princeton.edu/code/algs4.jar
	jar -xf algs4.jar

compile:
	javac Percolation.java
	javac PercolationStats.java
	javac PercolationTest.java

run: compile
	java Percolation $(SIZE)
	java PercolationStats $(SIZE) $(SIZE)
	java PercolationTest $(SIZE) $(SIZE)

clean:
	find . -type f -not -name Percolation.java -not -name PercolationStats.java -not -name PercolationTest.java -not -name Makefile -delete
	rm -rf META-INF
	rm -rf edu

submission: clean
	zip $(FILE) Percolation.java PercolationStats.java
