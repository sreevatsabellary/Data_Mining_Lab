library(party)
library(datasets)
library(TH.data)

str(bodyfat)
set.seed(1234)

index<-sample(2,nrow(bodyfat),replace=TRUE,prob=c(0.7,0.3))
trainData<-bodyfat[index==1,]
testData<-bodyfat[index==2,]

myFormula<-DEXfat ~ age + waistcirc + hipcirc + elbowbreadth + kneebreadth
iris_ctree<-ctree(myFormula,data=trainData)

plot(iris_ctree)
plot(iris_ctree,type="simple")

testPred<-predict(iris_ctree,newdata=testData)
t<-table(testPred,testData$DEXfat)
print(iris_ctree)
print(sum(diag(t))/sum(t))
