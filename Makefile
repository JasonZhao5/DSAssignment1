# 定义变量
JAVAC = javac
SRC_DIR = src
TEST_DIR = test
BIN_DIR = bin

# 定义目标
all: compile

# 编译源文件
compile:
	$(JAVAC) -d $(BIN_DIR) $(SRC_DIR)/client/*.java \
	$(SRC_DIR)/Factory/impl/*.java \
	$(SRC_DIR)/po/*.java \
	$(SRC_DIR)/server/*.java \
	$(SRC_DIR)/service/impl/*.java \
	$(SRC_DIR)/util/*.java \
	$(TEST_DIR)/*.java

# 清理生成的文件
clean:
	rm -rf $(BIN_DIR)/*.class
