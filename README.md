<<<<<<< HEAD
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

## State and event map
| State | Displayed by | Changed by |
|---|---|---|
| deviceName | Device name field and result | onDeviceNameChange and Clear |
| batteryText | Battery field, validation and result | onBatteryChange and Clear |
| networkAvailable | Switch and readiness decision | onNetworkChange and Clear |
| submitted | Error visibility and result visibility | Evaluate, any edit and Clear |

## Test results (T1 to T8)
| ID | Input / action | Expected | Actual | Pass/Fail |
|---|---|---|---|---|
| T1 | Clean build and launch | Material 3 screen opens | | |
| T2 | Evaluate blank form | Both errors, no result | | |
| T3 | Battery text, -1, 101 | Battery error shown | | |
| T4 | Battery 0, 29, 30, 100 | 0/29 attention, 30/100 ready | | |
| T5 | Battery 80, toggle network off | Changes only after Evaluate | | |
| T6 | Evaluate, then edit field | Old result disappears | | |
| T7 | Rotate the phone | Values restored | | |
| T8 | Large font + TalkBack | Reachable and announced clearly | | |

## Accessibility review
Write: the TalkBack focus order, that every field has a visible label,
that errors are text and not only colour, what the switch announces,
and how the result card is read out.

## UI test result
Both Compose UI tests passed on INFINIX Infinix X6831 (Android 13).
=======
# Mobile-Application-Development-Labs
Lab tasks and projects for Mobile Application Development course.
>>>>>>> 53548fd523ed13e6e62b285f2f1f94db176a6ba2
