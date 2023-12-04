JAVA = java
MAVEN = mvn
SOUNDBOARD = soundboard
SERVER = server
TARGET = $(SOUNDBOARD)/target/soundboard-*-jar-with-dependencies.jar
DUMMY = $(SERVER)/target/server-*-jar-with-dependencies.jar


$(TARGET):
	$(MAVEN) package --projects $(SOUNDBOARD)


$(DUMMY):
	$(MAVEN) package --projects $(SERVER)


.PHONY: run
run: ARGS?=
run: $(TARGET)
	$(JAVA) -jar $(TARGET) $(ARGS)


.PHONY: start_dummy
start_dummy: ARGS?=
start_dummy: $(DUMMY)
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


# Runs a clean snapshot of the Soundboard against a dummy server instance.
.PHONY: debug
debug:
	$(MAVEN) clean compile exec:java --projects $(SOUNDBOARD)
	$(MAKE) start_dummy
	$(MAKE) shutdown_dummy


.PHONY: clean
clean:
	$(MAKE) shutdown_dummy
	$(MAVEN) clean
