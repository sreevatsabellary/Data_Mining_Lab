data(iris)
names(iris)
head(iris)
attributes(iris)

print(iris$Sepal.Length)
print(iris$Sepal.Width)

quantile(iris$Sepal.Length)
var(iris$Sepal.Length)

hist(iris$Sepal.Length)
plot(density(iris$Sepal.Length))
table(iris$Sepal.Length)
table(iris$Species)
pie(table(iris$Species))
pie(table(iris$Sepal.Length))
barplot(table(iris$Species))