# Google Play Compliance — Guess The Flag

This document tracks the fixes for the Play Console policy warnings and what still
has to be done **by hand in the Play Console** (things that are not in the code).

## 1. Google Play Billing Library 7.0.0+ ✅ (fixed in code)

- `app/build.gradle`: `billing` dependency bumped `6.1.0` → **`7.1.1`**.
- Billing 7 **removed** the no-argument `enablePendingPurchases()`. Updated both
  call sites to the required form:
  ```java
  .enablePendingPurchases(
          PendingPurchasesParams.newBuilder()
                  .enableOneTimeProducts()
                  .build())
  ```
  Files: `MainMenu.java`, `BuyPremiumAvatars.java`.
- The rest of the billing code already used the modern `ProductDetails` /
  `queryProductDetailsAsync` API, so no other changes were needed.

## 2. Target Android 15 / API 35 ✅ (fixed in code)

- `app/build.gradle`: `compileSdk` and `targetSdk` → **35**; `versionCode` → 27,
  `versionName` → "27" (a new upload needs a higher versionCode).
- Toolchain bumped so SDK 35 actually compiles:
  - Android Gradle Plugin `8.0.1` → **`8.6.0`** (`build.gradle`)
  - Gradle wrapper `8.0` → **`8.7`** (`gradle/wrapper/gradle-wrapper.properties`)
  - Kotlin plugin/BOM `1.8.0` → **`1.9.24`**
- **Edge-to-edge:** API 35 forces edge-to-edge (content draws behind the status /
  navigation bars). To keep the existing fixed game layouts correct, added
  `android:windowOptOutEdgeToEdgeEnforcement=true` in `res/values-v35/themes.xml`.
  This is Google's supported opt-out for apps targeting 35. Note: the opt-out is
  **not** available once you target API 36 — at that point the layouts should be
  updated to apply `WindowInsets` padding instead.
- Verified: `./gradlew :app:compileDebugJavaWithJavac` → **BUILD SUCCESSFUL**.

## 3. Data safety section — ⚠️ MUST be done in the Play Console (not code)

"Invalid Data safety form" means the form in the Console is missing/inconsistent
with what the app actually collects. Fill it in at:

**Play Console → App content → Data safety.**

This app bundles SDKs that collect user data, so you cannot answer "No data
collected". Based on the SDKs in `app/build.gradle`, declare at least:

| SDK in the app | Data types to declare |
|---|---|
| Firebase Auth / Google Sign-In | Name, Email address, User IDs |
| Firebase Realtime Database | Whatever you store (player name, scores) → "App info & performance" / "Personal info" |
| Firebase Analytics | App interactions, Device or other IDs, Crash logs, Diagnostics |
| Google Mobile Ads (AdMob) | Device or other IDs (Advertising ID), App interactions — used for **Advertising/marketing** |
| Play Games v2 | User IDs, App interactions |

For each type, set: **Collected = Yes**, whether it is **shared** with third
parties (AdMob = yes, ads/analytics), whether it is **encrypted in transit**
(yes — all these SDKs use HTTPS), and whether the user can **request deletion**.

Also required:
- A **privacy policy URL** (Console → App content → Privacy policy). Firebase +
  AdMob both require one.
- The **Advertising ID** declaration (App content → Advertising ID: "Yes, uses
  advertising ID"), which must match the `AD_ID` permission the ads SDK adds.

After filling the form, click **Save** then **Submit for review**. The
"Invalid form" error clears once every collected type has consistent answers.

## Manifest / cleanup fixes done along the way

- Fixed malformed `com.google.android.gms.permission.AD_ID.` (trailing dot removed
  → `AD_ID`) so the ads advertising-ID permission is actually declared.
- Removed the bogus `com.google.android.play.billingclient.version` *uses-permission*
  (it is a metadata key, not a permission).
- Removed 8 committed JVM crash/replay logs (`hs_err_pid*.log`, `replay_pid*.log`)
  from the repo and added them to `.gitignore`.

## Google Sign-In fails in production — ⚠️ mostly a Console fix

The sign-in **code is correct** (`SignIn.java` requests an ID token with the web
client ID and calls `signInWithCredential`). "Works in debug / internal testing but
fails once live" is the classic **Play App Signing** fingerprint problem:

- When you enrolled in **Play App Signing**, Google re-signs your app with *their*
  key. The installed production app therefore reports the **App signing key's**
  SHA-1 — **not** your upload key or debug key SHA-1.
- If that SHA-1 is not in your Firebase project, Google Sign-In returns
  **status code 10 (DEVELOPER_ERROR)** and Firebase rejects the token.

**Fix:**
1. Play Console → your app → **Setup → App integrity → App signing**. Copy the
   **SHA-1** *and* **SHA-256** of the **App signing key certificate** (and, to be
   safe, also the **Upload key certificate**).
2. Firebase Console → **Project settings → Your apps → (Android app) → Add
   fingerprint**. Paste each SHA-1 and SHA-256.
3. Download the refreshed **`google-services.json`** and replace
   `app/google-services.json`.
4. Confirm the OAuth 2.0 **Web client ID** used in
   `R.string.Google_id` matches the `client_type: 3` entry in that file.
5. Rebuild and upload.

I also updated `SignIn.java` to show the failing status code in the error message,
so you can confirm on a real device: if you see `(10)`, it is the SHA-1 issue above;
`(12500)` usually means the SHA/config is missing entirely; `(12501)` is just the
user canceling.

## Before you upload

1. Build a signed release **App Bundle** (`.aab`): Android Studio → Build →
   Generate Signed Bundle, or `./gradlew bundleRelease`.
2. Upload to the Play Console (Production or a testing track).
3. Complete the **Data safety** form (section 3) and submit.
