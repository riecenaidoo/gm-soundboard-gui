JAVA = java
MAVEN = mvn
SOUNDBOARD = soundboard
TARGET = $(SOUNDBOARD)/target/soundboard-*-SNAPSHOT-jar-with-dependencies.jar



$(TARGET):
	$(MAVEN) -f $(SOUNDBOARD) package


.PHONY: run
run: $(TARGET)
	$(JAVA) -jar $(TARGET)


# TODO: Start the Dummy Server, exec: without packaging.
.PHONY: debug
debug:
	$(MAKE) clean
	$(MAKE) run


.PHONY: clean
clean:
	$(MAVEN) -f $(SOUNDBOARD) clean
