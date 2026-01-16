# 推薦的 Maven 多模組切法（學設計模式用）

### 1) `design-patterns`（父工程，packaging = pom）

用途：

* 統一 Java 版本（你用 JDK 1.8 就鎖 `maven-compiler-plugin`）
* 統一依賴版本（JUnit、AssertJ…）
* 統一 plugin 設定（Surefire、Failsafe、Enforcer）

> 這個模組不放任何程式碼，只做管理。

---

### 2) `patterns-common`（共用領域模型/工具）

用途：

* 放少量「跨模式共用」的 domain，例如：`Order`、`PaymentRequest`、`Notification`
* 放測試工具：`TestFixtures`、假資料產生器

**警告**：common 放太多會把每個模式的差異磨平，導致你看不出模式的價值。
原則：**只放「中性的資料結構」**，不要放「帶有某個模式味道的抽象」。

---

### 3) 三大分類模組（最實用的學習單位）

* `patterns-creational`
* `patterns-structural`
* `patterns-behavioral`

用途：

* 每個模組內用 package 分 pattern，例如：

    * `creational.factorymethod.*`
    * `structural.decorator.*`
    * `behavioral.strategy.*`

這樣的好處是：

* 模組數量不爆炸（你不會有 23 個 module）
* 依賴清楚：三大模組只依賴 `patterns-common`
* 你仍然可以在模組內做「同模式的不同寫法」比較（例如策略模式：if-else vs map vs DI）

---

### 4) `patterns-app`（統一執行入口 / demo 集成）

用途：

* 一個地方跑所有示例，不要每個 module 都搞一個 main
* 你可以定義一個介面，例如 `PatternDemo { String name(); void run(); }`
* 各 pattern 提供實作，`patterns-app` 透過註冊表或 `ServiceLoader` 集中展示

這個模組依賴：

* `patterns-common`
* `patterns-creational`
* `patterns-structural`
* `patterns-behavioral`

---