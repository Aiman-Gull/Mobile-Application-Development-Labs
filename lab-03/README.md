# CSC303 Lab 03 - DeviceManager

## Screens
1. MainActivity: Shows device name and battery. Buttons: EDIT DEVICE, OPEN DOCUMENTATION.
2. EditDeviceActivity: Edits name and battery. Validates 0-100. Returns via Activity Result API.

## Run Steps
1. Open in Android Studio.
2. Build and Run on device/emulator.

## Tested Deployment Target
[Your phone model, e.g., Samsung Galaxy A54]

## T1-T8 Results
| ID | Action | Expected | Actual | Pass/Fail |
|---|---|---|---|---|
| T1 | Cold launch | Sensor-A, 15% | Same | Pass |
| T2 | Tap Edit Device | Opens with values | Same | Pass |
| T3 | Save Tablet-C/45 | Main updates | Same | Pass |
| T4 | Edit then Cancel | Old values kept | Same | Pass |
| T5 | Blank input | Error shown | Same | Pass |
| T6 | Battery -1, 0, 100, 101 | Reject -1, 101; accept 0, 100 | Same | Pass |
| T7 | Open Documentation | Browser opens | Same | Pass |
| T8 | Home/Return/Rotate | Lifecycle logs | Same | Pass |

## Diagnosed Issue
AppCompat theme crash diagnosed via Logcat FATAL EXCEPTION. Fixed parent theme in themes.xml.