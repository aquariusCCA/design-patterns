# 題目描述

你在批次作業（Batch Job）跑完後要通知結果：成功或失敗。
目前寫死用 Email 發送，但未來可能要加上 LINE、Slack、簡訊。

目標：`BatchJob` 不需要知道 Email/Line 的細節，也不需要為了新增一種通知方式去改它（符合 DIP）。

# 你要完成的 good 版要求

* `BatchJob` 不得 `new EmailNotifier()`
* `BatchJob` 只能依賴抽象（interface）
* `App` 負責組裝：決定 notifier 實作並注入
* 至少提供兩個 notifier 實作（例如 Email + Line 或 Email + Slack）