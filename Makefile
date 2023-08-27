.PHONY: gui clean debug

JAVA_HOME = java
TARGET = target/soundboard-1.0-SNAPSHOT-jar-with-dependencies.jar



$(TARGET):
	mvn package


run: $(TARGET)
	$(JAVA_HOME) -jar $(TARGET)


debug:
	$(MAKE) clean
	$(MAKE) run


clean:
	mvn clean