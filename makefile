# 
# makefile
# 
# input file for 'make' build tool ( /usr/bin/make )
# to build https://github.com/JaysGitLab/cs-5666-greeting-bclinthall/projects/1
# following Dr. Fenwick's solution for CS 5666 JUnit sample
# 
# @author B. Clint Hall
# @author Dr Fenwick
# @author Chris Campell
# @version 3/10/2017
#
# Set up locations for the jar files and URIs to fetch them from.
JUNIT_JAR = junit-4.12.jar
JUNIT_URI = https://github.com/junit-team/junit4/releases/download/r4.12/$(JUNIT_JAR)
JUNIT_LOCAL = jars/$(JUNIT_JAR)
HAMCREST_JAR = hamcrest-core-1.3.jar
HAMCREST_URI = http://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/$(HAMCREST_JAR)
HAMCREST_LOCAL = jars/$(HAMCREST_JAR)
JSON_COMMON = commons-lang3-3.4.jar
JSON_GROOVY = groovy-json-2.4.6.jar
GROOVY = groovy-2.4.6.jar
JSON_PATH = json-path-3.0.2.jar
JSON_SIMPLE = json-simple-1.1.1.jar
STYLE_XML = misc/appstate_style.xml
COMMON_LOCAL = jars/$(JSON_COMMON)
JSONGROOVY_LOCAL =jars/$(JSON_GROOVY)
GROOVY_LOCAL = jars/$(GROOVY)
PATH_LOCAL = jars/$(JSON_PATH)
SIMPLE_LOCAL = jars/$(JSON_SIMPLE)
CLASSPATH = -cp .:$(JUNIT_LOCAL):$(COMMON_LOCAL):$(JSONGROOVY_LOCAL):$(GROOVY_LOCAL):$(PATH_LOCAL):$(SIMPLE_LOCAL)
CC = javac $(CLASSPATH) -Xlint:deprecation

# Teach make how to use javac to convert between .java and .class
.SUFFIXES: .java .class
.java.class:
	$(CC) $<

default:
	@echo "usage: make target"
	@echo "available targets: compile, test, clean"

compile: scratchgrader/ScratchLoader.class junit/ScratchLoaderTest.class 
	@echo "compiled"

junit/ScratchLoaderTest.class: $(JUNIT_LOCAL) $(COMMON_LOCAL) $(JSONGROOVY_LOCAL) $(GROOVY_LOCAL) $(PATH_LOCAL) $(SIMPLE_LOCAL)

style:
	checkstyle -c $(STYLE_XML) junit/ScratchLoaderTest.java scratchgrader/ScratchLoader.java

clean:
	rm -f scratchgrader/ScratchLoader.class
	rm -f junit/ScratchLoaderTest.class

test: scratchgrader/ScratchLoader.class junit/ScratchLoaderTest.class $(JUNIT_LOCAL) $(HAMCREST_LOCAL)
	java -cp .:$(JUNIT_LOCAL):$(HAMCREST_LOCAL):$(COMMON_LOCAL):$(JSONGROOVY_LOCAL):$(GROOVY_LOCAL):$(PATH_LOCAL):$(SIMPLE_LOCAL) org.junit.runner.JUnitCore junit.ScratchLoaderTest

jars:
	mkdir jars

# Add makefile targets that download the jars automatically if they
# are not present locally.
$(JUNIT_LOCAL): jars
	curl $(JUNIT_URI) -o $(JUNIT_LOCAL) --silent --location
$(HAMCREST_LOCAL): jars
	curl $(HAMCREST_URI) -o $(HAMCREST_LOCAL) --silent --location


