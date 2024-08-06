rm -rf bin/*
rm -rf executables/Solution.java
javac -cp "lib/json-simple-1.1.1.jar" -d bin/ GenerateExec.java
java -cp lib/json-simple-1.1.1.jar:bin/ GenerateExec exec1161;
for i in $(ls ./solutions);
    do javac -d bin/ ./solutions/$i;
done
javac -d bin/ LeetCode.java
java -cp bin/ LeetCode
# for i in $(ls ./solutions);
#     do java -cp bin/solutions ${i%.*};
# done
