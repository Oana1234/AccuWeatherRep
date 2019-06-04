
dependencies {

    //Android Support Libraries
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation 'com.android.support:appcompat-v7:26.1.0'
    compile 'com.android.support:recyclerview-v7:26.1.0'
    implementation 'com.android.support:cardview-v7:26.1.0'
    implementation 'com.android.support:support-v4:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    implementation 'com.android.support:design:26.1.0'
    implementation 'com.google.android.gms:play-services-location:11.8.0'

    //Database
    implementation "android.arch.persistence.room:runtime:1.0.0"
    annotationProcessor "android.arch.persistence.room:compiler:1.0.0"

    //API
    implementation 'com.squareup.okhttp3:okhttp:3.10.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:3.10.0'

    //Gson
    implementation 'com.google.code.gson:gson:2.8.2'

    //Chuck
    debugCompile 'com.readystatesoftware.chuck:library:1.1.0'
    releaseCompile 'com.readystatesoftware.chuck:library-no-op:1.1.0'

    //Android Architecture Components
    implementation "android.arch.lifecycle:extensions:1.1.1"
}
