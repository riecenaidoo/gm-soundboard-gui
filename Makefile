.PHONY: gui clean

JAVA_HOME = java
TARGET = target/gui-1.0-SNAPSHOT-jar-with-dependencies.jar



$(TARGET):
	mvn package


run: $(TARGET)
	$(JAVA_HOME) -jar $(TARGET)


clean:
	mvn clean