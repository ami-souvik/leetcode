rm -rf bin/*
rm -rf executables/Solution.java
javac -d bin/ GenerateExec.java
for i in $(ls ./problems);
    do java -cp bin/ GenerateExec $i;
done

javac -d bin/ executables/*.java LeetCode.java
java -cp bin/ LeetCode