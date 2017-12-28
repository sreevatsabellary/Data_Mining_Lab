library("arules")

data=read.transactions("apriori.csv",format="basket",sep=',')
inspect(data)

data_rules<-apriori(data,parameter=list(sup=0.3,conf=0.6,target="rules"))
inspect(data_rules)

itemFrequencyPlot(data)

rules_sorted<-sort(data_rules,by="confidence")
inspect(rules_sorted)