SHELL := /bin/bash
JAVA = java
MAVEN = mvn
SOUNDBOARD = soundboard
SERVER = server
TARGET = $(SOUNDBOARD)/target/soundboard-*-jar-with-dependencies.jar
DUMMY = $(SERVER)/target/server-*-jar-with-dependencies.jar
RELEASE = RELEASES/


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


.PHONY: deploy
deploy: v?=
deploy:
	@if test -z "$$v"; then\
		echo Missing 'v=' to specifiy version.;\
		exit 1;\
	fi;
	@echo "Updating pom versioning.."
	@$(MAVEN) versions:set -DnewVersion=$(v) --projects $(SOUNDBOARD)/pom.xml > /dev/null
	@echo "Rebuilding jar..."
	@$(MAVEN) clean package --projects $(SOUNDBOARD)  > /dev/null
	@if ! test -d $(RELEASE);then mkdir $(RELEASE) & echo "Created $(RELEASE) directory."; fi;
	@$(MAKE) clean_release
	@echo "Copying latest jar to $(RELEASE)."
	@cp $(TARGET) $(RELEASE)


.PHONY: clean
clean:
	$(MAKE) shutdown_dummy
	$(MAVEN) clean
	$(MAKE) clean_release


.PHONY: clean_release
clean_release:
	@if test -d $(RELEASE);then\
		echo "$(RELEASE) dir exists.";\
		if compgen -G "$(RELEASE)*.jar" > /dev/null; then\
			echo "Removing outdated jars in $(RELEASE).";\
			rm $(RELEASE)*.jar;\
		fi;\
	fi;
