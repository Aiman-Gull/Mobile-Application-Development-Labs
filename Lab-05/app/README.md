# Lab 05 – Device Readiness Checker

## Rules
| Field or decision | Rule |
|---|---|
| Device name | Required after trimming; blank input is invalid |
| Battery percentage | Whole number 0 to 100; missing, non-numeric, out-of-range is invalid |
| Network available | Boolean selected through a Material Switch |
| Ready | Valid form, battery at least 30 and network available |
| Needs attention | Valid form but battery below 30 or network unavailable |
| After input changes | Hide the previous result so it cannot describe stale values |