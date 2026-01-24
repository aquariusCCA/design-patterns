# 題目描述

你要做一個「每日報表」服務：產出資料後匯出成檔案。
目前系統只支援 Excel，但未來可能要加上 PDF、CSV 等。
目標：改成只要新增一個 exporter，不需要改 `ReportService` 的程式碼（同時也符合 DIP）。

# 你要完成的 good 版要求

* `ReportService` 不得依賴 `ExcelExporter`
* `ReportService` 只能依賴一個抽象（interface）
* `App` 負責決定用哪個 exporter，並注入到 `ReportService`
