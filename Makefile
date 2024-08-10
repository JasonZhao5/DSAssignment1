# 定义变量
SRC_DIR := src
CLASSES_DIR := classes
JAR_NAME := client.jar

# 编译规则
$(CLASSES_DIR)/%.class: $(SRC_DIR)/%.java
    javac -d $(CLASSES_DIR) $<

# 打包规则
$(JAR_NAME): $(CLASSES_DIR)/client/*.class
    jar cvf $(JAR_NAME) $(CLASSES_DIR)/client/*.class

# 默认目标
all: $(JAR_NAME)

# 清除目标
clean:
    rm -rf $(CLASSES_DIR) $(JAR_NAME)
