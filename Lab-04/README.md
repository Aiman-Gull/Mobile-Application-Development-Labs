## CSC303 Lab 04 — Kotlin Fundamentals and Java Interoperability

### Mixed-Language Design
- `MainActivity.java` — UI layer (Java)
- `LegacyDeviceRepository.java` — data source (Java)
- `DeviceReading.kt`, `DeviceSummary.kt`, `DeviceAnalyzer.kt` — processing layer (Kotlin)

### Data Rules
- Valid battery range: 0..100
- Low battery threshold: < 20
- Null/blank names → "Unnamed device"
- Null battery → "unknown"
- Out-of-range battery → "invalid (value)"

### Expected Summary (5 records)
Total=5, Valid=3, Low=1, Invalid/Missing=2, Average=46.7%

### T1–T8 Results
All 8 verification cases passed. See report for details.

### Diagnosed Issue
The project was initially created with the "Empty Activity" (Compose) template,
which ignores `res/layout/*.xml`. This caused edits to `activity_main.xml` to
have no effect. Fixed by recreating the project with "Empty Views Activity"
(Java + XML layout).