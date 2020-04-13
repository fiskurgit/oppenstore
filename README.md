# ÖppenStore

I've given up on Google (in general), and the Play Store (so many reasons). All future non-commercial projects will available through the ÖppenStore Android app.

## Projects

Source code should be publically available for all projects. No project should make use of any Google Services API or remote Google service. All projects should run without issue on [LineageOS](https://lineageos.org/) or any other de-Googled Android fork.

Projects currently available in ÖppenStore:

### Android
* Redaktör: [github.com/fiskurgit/oppen_redaktor](https://github.com/fiskurgit/oppen_redaktor)
* Fotoram: [github.com/fiskurgit/oppen_fotoram](https://github.com/fiskurgit/oppen_fotoram)

### Progressive Web Apps
* EnkelDraw [oppenlab.net/pwa/enkel_draw](https://oppenlab.net/pwa/enkel_draw)

<hr>

## Project Listing Format

* **oppen_id** - unique identifier, for Android APKs use app package name
* **title** - project title
* **type** - currently `apk` or `pwa`, `desktop_pwa` may appear in the future
* **version** - this will drive app update logic for APKs
* **web_url** - optional web page for the project
* **image** - main banner image for the project
* **url** - download URL of the APK, or web url for a PWA
* **short_description** - single line overview of the project
* **content** - an array of content blocks, current types: `text` and `image`


Example entry:
```json
{
            "oppen_id": "oppen.android.redaktor",
            "title": "Redaktör",
            "type": "apk",
            "version": "1.0.0",
            "web_url": "https://oppenlab.net",
            "image": "https://oppenlab.net/services/oppenstore/assets/redaktor/store_image_redaktor.png",
            "url": "https://oppenlab.net/services/oppenstore/assets/apk/opeen_redaktor_release_1_0_0.apk",
            "short_description": "A text editor designed for use with Termux and external keyboards",
            "content": [
                {
                    "type": "text",
                    "text": "A text editor designed for use with Termux and external keyboards, Redaktör uses Androids 'Storage Access Framework' which allows read/write access to files in device storage, Termux, Google Drive and other services. When using with a physical keyboard standard shortcuts are available (ctrl-o, ctrl-s, ctrl-n for open/save/new)."
                },
                {
                    "type": "text",
                    "text": "A floating settings screen includes dark and light themes and font choices"
                },
                {
                    "type": "image",
                    "image": "https://oppenlab.net/services/oppenstore/assets/redaktor/oppen_redactor_controls.png"
                },
                {
                    "type": "text",
                    "text": "Markdown support includes syntax highlighting and a preview screen"
                },
                {
                    "type": "image",
                    "image": "https://oppenlab.net/services/oppenstore/assets/redaktor/oppen_redactor_markdown.png"
                }
            ]
        }
```

The URL is packaged with the app, but accessed remotely: [fiskurgit.github.io/oppenstore/app/src/main/assets/oppenstore.json](https://fiskurgit.github.io/oppenstore/app/src/main/assets/oppenstore.json) - this enables local testing of updates while debugging the Android app.

