# Fixing Google Sign-In in Production

Google Sign-In works in Android Studio / internal testing but **fails once the app
is live on Google Play**. This is a configuration problem, **not a code bug** — the
sign-in code in `SignIn.java` is correct.

---

## Why it happens

When you publish through Google Play you use **Play App Signing** (mandatory for new
apps). This means:

1. You sign the `.aab` you upload with your **upload key**.
2. Google **re-signs** the installed app with a *different* key — the **app signing
   key** that Google holds.

So the app your users install is signed with **Google's key**, whose SHA-1
fingerprint is different from your debug key and your upload key.

Google Sign-In and Firebase authorize a request by matching the app's
**package name + SHA-1 fingerprint** against what is registered in Firebase.
If the **app signing key's SHA-1 is not registered**, Google returns:

```
ApiException: 10  (DEVELOPER_ERROR)
```

…and Firebase rejects the ID token. Because it only fails for the *Google-signed*
build, everything looks fine in debug and breaks only in production.

> The app now shows this status code in the error message on the sign-in screen,
> so you can confirm it on a real device:
> - **`(10)`** → the SHA-1 issue described here (most common)
> - **`(12500)`** → configuration missing / `google-services.json` out of date
> - **`(12501)`** → the user simply cancelled (not an error)

---

## The fix (step by step)

### 1. Get the App signing key fingerprints from Play Console

1. Go to **[Google Play Console](https://play.google.com/console)** → select your app
   (**Guess The Flag**, `com.orhava.trivia2`).
2. Left menu → **Test and release → Setup → App integrity**.
3. Open the **App signing** tab.
4. Copy **both** fingerprints under **"App signing key certificate"**:
   - **SHA-1 certificate fingerprint**
   - **SHA-256 certificate fingerprint**
5. While you are there, also copy the **SHA-1** and **SHA-256** under
   **"Upload key certificate"** (register those too, so uploads and internal
   testing keep working).

### 2. Add the fingerprints to Firebase

1. Go to **[Firebase Console](https://console.firebase.google.com)** → your project.
2. Click the **gear icon → Project settings**.
3. Scroll to **Your apps** and select the Android app `com.orhava.trivia2`.
4. Click **Add fingerprint**.
5. Paste the **App signing key SHA-1**, save. Repeat for the **SHA-256**, and for the
   **Upload key SHA-1 / SHA-256** from step 1.

You should end up with all four fingerprints registered (app signing + upload,
SHA-1 + SHA-256).

### 3. Download the updated `google-services.json`

1. Still in **Project settings → Your apps → Android app**, click
   **Download google-services.json**.
2. Replace the file in the project at:
   ```
   app/google-services.json
   ```
3. (The new file now contains the added fingerprints under the `oauth_client`
   entries.)

### 4. Verify the Web client ID

Google Sign-In requests an **ID token** using the **Web client (server) ID**, read in
`SignIn.java` as:

```java
.requestIdToken(getString(R.string.Google_id))
```

- Make sure the value of the string resource **`Google_id`** matches the
  **OAuth 2.0 Web application client ID** for this project.
- You can find it in `app/google-services.json` as the entry with
  `"client_type": 3`, or in
  **[Google Cloud Console](https://console.cloud.google.com/apis/credentials) →
  APIs & Services → Credentials → OAuth 2.0 Client IDs → "Web client"**.
- It looks like `xxxxxxxxxxxx-xxxxxxxx.apps.googleusercontent.com`.

### 5. Confirm the Google provider is enabled

Firebase Console → **Authentication → Sign-in method → Google** must be **Enabled**,
with a support email set.

### 6. Rebuild and upload

```bash
# from the project root
./gradlew clean
./gradlew bundleRelease      # produces the .aab to upload
```

Upload the new bundle to a **testing track** (Internal testing is fastest), install
it **from the Play Store link** (not a local build), and test Google Sign-In.

> Test with the **Play Store build**, not a locally installed APK. Only the
> Play-distributed build is signed with the app signing key, which is exactly the
> case that was broken.

---

## Quick checklist

- [ ] App signing key **SHA-1** added to Firebase
- [ ] App signing key **SHA-256** added to Firebase
- [ ] Upload key **SHA-1 / SHA-256** added to Firebase
- [ ] Fresh **`google-services.json`** downloaded and placed in `app/`
- [ ] `Google_id` string = the **Web client ID** (`client_type: 3`)
- [ ] **Google** provider enabled in Firebase Authentication
- [ ] Tested with a **Play Store** build (internal testing track)

---

## Still failing?

| Symptom | Likely cause | Action |
|---|---|---|
| Error `(10)` in production | App signing SHA-1 missing | Redo steps 1–3 |
| Error `(10)` everywhere | `google-services.json` stale, or wrong Web client ID | Redo steps 3–4 |
| Error `(12500)` | Config mismatch / OAuth consent screen not set | Check steps 4–5 + OAuth consent screen in Cloud Console |
| Error `(12501)` | User cancelled | Not a bug |
| Token accepted but no user | Google provider disabled | Step 5 |

Give it a few minutes after changing Firebase settings — propagation is not always
instant.
