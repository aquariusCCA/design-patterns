> **參考文章：**
> - [Java的Record：简化数据类的利器](https://juejin.cn/post/7372441501610311706 "Java的Record：简化数据类的利器")

# 背景

在 Java 项目中，我们经常需要定义一些只包含数据的类，这些类通常被称为数据传输对象（DTO）或值对象（VO）。

传统的做法是手动编写这些类，包括字段、构造函数、访问器（getter）方法、equals()、hashCode() 和 toString() 方法。这种重复的代码不仅繁琐，而且容易出错。

为了解决这个问题，Java 在 14 版本中引入了 record，一种新的语法结构，用于简化数据类的定义。

# 什么是Record？

record 是一种特殊的类，它主要用于存储数据。

使用 record，我们可以用最少的代码定义一个数据类，并且自动生成常见的方法，如构造函数、equals()、hashCode() 和 toString()。

# 语法示例

假设我们需要定义一个表示二维点的类，传统的做法如下：

```java
public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                "y=" + y +
                '}';
    }
}
```

使用 record 可以简化为：

```java
public record Point(int x, int y) {}
```

# 如何使用 Record

定义 Record：使用 record 关键字定义一个数据类，并指定其字段。

```java
public record Point(int x, int y) {}
```

创建 Record 实例：使用与普通类相同的语法创建 record 实例。

```java
Point point = new Point(1, 2);
```

访问字段：record 会自动生成访问器方法，方法名与字段名相同。

```java
int x = point.x(); // 访问x字段
int y = point.y(); // 访问y字段
```

自动生成的方法：record 自动生成 equals()、hashCode() 和 toString() 方法。

```java
Point point1 = new Point(1, 2);
Point point2 = new Point(1, 2);
 
System.out.println(point1.equals(point2)); // 输出：true
System.out.println(point1.hashCode()); // 输出：一个基于x和y的哈希值
System.out.println(point1); // 输出：Point[x=1, y=2]
```

# Record 的好处

- 减少样板代码：record 自动生成构造函数、访问器方法、equals()、hashCode() 和 toString() 方法，极大地减少了样板代码。
- 提高可读性：代码更加简洁明了，容易阅读和维护。
- 不可变性：record 的字段是 final 的，确保数据的不可变性，有助于编写线程安全的代码。
- 自动生成方法：自动生成的 equals()、hashCode() 和 toString() 方法是基于所有字段的，这减少了人为错误的可能性。

# 最佳实践

- 使用 record 来表示简单的数据载体：如果一个类只是用来存储数据，没有复杂的行为逻辑，那么使用 record 是一个很好的选择。
- 避免过度使用：record 适用于简单的数据类，不适合有复杂逻辑或需要继承其他类的情况。
- 组合使用 record 和接口：虽然 record 不能继承其他类，但可以实现接口。通过接口，我们可以为 record 添加更多的行为。

# 示例

以下是一个使用record和接口的示例：

```java
public interface Shape {
    double area();
}

public record Circle(double radius) implements Shape {
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

public record Rectangle(double width, double height) implements Shape {
    @Override
    public double area() {
        return width * height;
    }
}

public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);

        System.out.println("Circle area: " + circle.area());
        System.out.println("Rectangle area: " + rectangle.area());
    }
}
```

在这个示例中，Circle和Rectangle都是使用record定义的，并且实现了Shape接口。这样，我们既享受了record带来的简洁性，又实现了多态性。

# 结论

Java 的 record 特性为我们提供了一种简洁、高效的方式来定义数据类。通过减少样板代码，提高代码的可读性和可维护性，record 使得 Java 在处理简单数据载体时变得更加方便。掌握record的使用，可以让我们在日常开发中更加高效和愉快。
