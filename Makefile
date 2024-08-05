# Define the Java compiler
JAVAC = javac

# Define the Java runtime
JAVA = java

# Define the source file directory
SRC_DIR = src

# Define the class file directory
BIN_DIR = bin

# Define the source files
SRCS = $(wildcard $(SRC_DIR)/**/*.java)

# Define the class files
CLASSES = $(SRCS:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)

# Default target
all: $(CLASSES)

# Compile Java source files
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(dir $@)
	$(JAVAC) -d $(BIN_DIR) $<

# Run the Java program
run: all
	$(JAVA) -cp $(BIN_DIR) Main

# Clean up generated files
clean:
	rm -rf $(BIN_DIR)

# Phony targets
.PHONY: all run clean
