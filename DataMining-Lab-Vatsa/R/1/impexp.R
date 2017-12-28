var1<-sample(5)
var2<-var1/10
var3<-c("My","Name","Is","Anthony","Gonsalvis")

res<-data.frame(var1,var2,var3)
names(res)<-c("var Int","var Float","var String")
write.csv(res,"imp.csv",row.names=FALSE)

res1<-read.csv("imp.csv")
print(res1)