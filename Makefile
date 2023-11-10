JAVA = java
MAVEN = mvn
SOUNDBOARD = soundboard
SERVER = server
TARGET = $(SOUNDBOARD)/target/soundboard-*-jar-with-dependencies.jar
DUMMY = $(SERVER)/target/server-*-jar-with-dependencies.jar


$(TARGET):
	$(MAVEN) -f $(SOUNDBOARD) package


$(DUMMY):
	$(MAVEN) -f $(SERVER) package


.PHONY: run
run: ARGS?=
run: $(TARGET)
	$(JAVA) -jar $(TARGET) $(ARGS)


.PHONY: dummy
dummy: ARGS?=
dummy: $(DUMMY)
	$(MAKE) shutdown_dummy
	$(JAVA) -jar $(DUMMY) $(ARGS) & echo $$! > server.PID&


.PHONY: shutdown_dummy
shutdown_dummy:
	@if test -f "server.PID";then\
		echo "Checking status of dummy server..";\
		if ps -p `cat server.PID`;then\
			echo "Shutting down running dummy server process..";\
			kill `cat server.PID`;\
		fi;\
		echo "Removing PID cache file..";\
		rm -f server.PID;\
	fi;


# Rebuilds & runs the Soundboard against a dummy server.
.PHONY: debug
debug:
	$(MAVEN) -f $(SOUNDBOARD) clean
	$(MAKE) dummy
	$(MAKE) run
	$(MAKE) shutdown_dummy


.PHONY: clean
clean:
	$(MAKE) shutdown_dummy
	$(MAVEN) -f $(SOUNDBOARD) clean
	$(MAVEN) -f $(SERVER) clean
