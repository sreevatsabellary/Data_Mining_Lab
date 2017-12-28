library(factoextra)
library(datasets)
library(cluster)

iris$Species<-NULL

d<-scale(dist(iris,method="euclidian"))

kfit<-kmeans(d,3)
hfit<-hkmeans(d,3)

fviz_cluster(kfit,iris,geom="point")
fviz_cluster(hfit,iris,geom="point")