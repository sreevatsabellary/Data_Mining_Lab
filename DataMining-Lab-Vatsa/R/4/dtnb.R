library(party)
library(datasets)

str(iris)
set.seed(1234)

index<-sample(2,nrow(iris),replace=TRUE,prob=c(0.7,0.3))
trainData<-iris[index==1,]
testData<-iris[index==2,]

myFormula<-Species~Sepal.Length+Sepal.Width+Petal.Length+Petal.Width
iris_ctree<-ctree(myFormula,data=trainData)

plot(iris_ctree)
plot(iris_ctree,type="simple")

testPred<-predict(iris_ctree,newdata=testData)
table(testPred,testData$Species)
print(iris_ctree)

library(e1071)

res<-naiveBayes(Species~.,data=trainData,laplace=1)
print(res)

testP<-predict(res,newdata=testData)
t<-table(testP,testData$Species)
t

summary(testP)

x<-sum(t)
d<-diag(t)
n<-sum(d)/x
n
