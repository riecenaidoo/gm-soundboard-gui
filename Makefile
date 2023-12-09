SHELL := /bin/bash
# Executables
JAVA = java
MAVEN = mvn
# Artifact IDs
SOUNDBOARD = soundboard
SERVER = server
# Compile Targets
TARGET = $(SOUNDBOARD)/target/soundboard-*-jar-with-dependencies.jar
DUMMY = $(SERVER)/target/server-*-jar-with-dependencies.jar
# Dev Folders
RELEASE = RELEASES/


$(TARGET):
	$(MAVEN) package --projects $(SOUNDBOARD)


$(DUMMY):
	$(MAVEN) package --projects $(SERVER)


.PHONY: run
run: ARGS?=
run: $(TARGET)
	$(JAVA) -jar $(TARGET) $(ARGS)


.PHONY: run_dummy
run_dummy: ARGS?=
run_dummy: $(DUMMY)
	@$(MAKE) stop_dummy
	@echo "Starting dummy server..."
	@$(JAVA) -jar $(DUMMY) $(ARGS) & echo $$! > server.PID&


.PHONY: stop_dummy
stop_dummy:
	@if test -f "server.PID";then\
		echo "Checking status of dummy server..";\
		if ps -p `cat server.PID`;then\
			echo "Shutting down running dummy server process...";\
			kill `cat server.PID`;\
		fi;\
		echo "Removing PID cache file..";\
		rm -f server.PID;\
	fi;


# Runs a clean snapshot of the Soundboard against a dummy server instance.
.PHONY: snapshot
snapshot:
	@echo "Rebuilding $(SOUNDBOARD)..."
	@$(MAVEN) clean compile exec:java --projects $(SOUNDBOARD)
	@$(MAKE) run_dummy
	@$(MAKE) stop_dummy


.PHONY: clean_deploy
clean_deploy:
	@if test -d $(RELEASE);then\
		if compgen -G "$(RELEASE)*.jar" > /dev/null; then\
			echo "Removing jars in $(RELEASE).";\
			rm $(RELEASE)*.jar;\
		fi;\
	fi;


.PHONY: clean
clean:
	@$(MAKE) stop_dummy
	@echo Cleaning object code..
	@$(MAVEN) clean
	@$(MAKE) clean_deploy


.PHONY: deploy
deploy: v?=
deploy:
	@if test -z "$$v"; then\
		echo Missing 'v=' to specifiy version.;\
		exit 1;\
	fi;
	@echo "Updating pom versioning.."
	@$(MAVEN) versions:set -DnewVersion=$(v) --projects $(SOUNDBOARD)/pom.xml > /dev/null
	@echo "Repackaging jar..."
	@$(MAVEN) clean package --projects $(SOUNDBOARD)  > /dev/null
	@if ! test -d $(RELEASE);then mkdir $(RELEASE) & echo "Created $(RELEASE) directory."; fi;
	@$(MAKE) clean_deploy
	@echo "Copying latest jar to $(RELEASE)."
	@cp $(TARGET) $(RELEASE)
