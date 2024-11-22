### build/repro steps

- add local.properties for `github.user`, `github.token`
- install app
- open app
- check logs

On MainActivity create, a Zenoh session is opened.

On FirstFragment start, a subscriber is declared that starts listening for `crash/my/zenoh`. (To represent my production app, I used the same structure of declaring and storing a subscriber in my own ZSub wrapper.)

On app close, subscriber is undeclared and session is closed.

Works on tablet.
Crashes on emulator.

### videos

#### success on physical tablet

https://github.com/user-attachments/assets/bfd0ad75-dfe2-43df-bcb1-e1d9a4c2a395

#### crash on emulator (Pixel 5, Android 11.0 x86)

https://github.com/user-attachments/assets/782f18b7-6d2e-4ba2-9fef-118f8eba955e

### original sanitized crash log

```
java_vm_ext.cc:577] JNI DETECTED ERROR IN APPLICATION: use of invalid jobject 0x2dfdb718
java_vm_ext.cc:577]     from long io.zenoh.jni.JNISession.declareKeyExprViaJNI(long, java.lang.String)
java_vm_ext.cc:577] JNI DETECTED ERROR IN APPLICATION: use of invalid jobject 0x2dfdb718
java_vm_ext.cc:577]     from long io.zenoh.jni.JNISession.declareKeyExprViaJNI(long, java.lang.String)
java_vm_ext.cc:577] JNI DETECTED ERROR IN APPLICATION: use of invalid jobject 0x2dfdb718
java_vm_ext.cc:577]     from long io.zenoh.jni.JNISession.declareKeyExprViaJNI(long, java.lang.String)
runtime.cc:655]   native: #19 pc 01317263  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/lib/x86/libzenoh_jni.so (jni::wrapper::jnienv::JNIEnv::get_string::hee0db855e37a8fb2+547)
runtime.cc:655]   native: #20 pc 0083b0a2  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/lib/x86/libzenoh_jni.so (???)
runtime.cc:655]   native: #21 pc 00835395  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/lib/x86/libzenoh_jni.so (Java_io_zenoh_jni_JNISession_declareKeyExprViaJNI+53)
runtime.cc:655]   native: #22 pc 001422b2  /apex/com.android.art/lib/libart.so (art_quick_generic_jni_trampoline+82)
runtime.cc:655]   native: #23 pc 0013baa2  /apex/com.android.art/lib/libart.so (art_quick_invoke_stub+338)
runtime.cc:655]   native: #24 pc 001d0501  /apex/com.android.art/lib/libart.so (art::ArtMethod::Invoke(art::Thread*, unsigned int*, unsigned int, art::JValue*, char const*)+241)
runtime.cc:655]   native: #25 pc 00386881  /apex/com.android.art/lib/libart.so (art::interpreter::ArtInterpreterToCompiledCodeBridge(art::Thread*, art::ArtMethod*, art::ShadowFrame*, unsigned short, art::JValue*)+385)
runtime.cc:655]   native: #26 pc 0037abbe  /apex/com.android.art/lib/libart.so (bool art::interpreter::DoCall<false, false>(art::ArtMethod*, art::Thread*, art::ShadowFrame&, art::Instruction const*, unsigned short, art::JValue*)+1070)
runtime.cc:655]   native: #27 pc 007a5fd9  /apex/com.android.art/lib/libart.so (MterpInvokeDirect+633)
runtime.cc:655]   native: #28 pc 00135a21  /apex/com.android.art/lib/libart.so (mterp_op_invoke_direct+33)
runtime.cc:655]   native: #29 pc 00481386  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/base.apk (offset 2743000) (io.zenoh.jni.JNISession.declareKeyExpr-IoAF18A+34)
runtime.cc:655]   native: #30 pc 007a335e  /apex/com.android.art/lib/libart.so (MterpInvokeVirtual+1806)
runtime.cc:655]   native: #31 pc 00135921  /apex/com.android.art/lib/libart.so (mterp_op_invoke_virtual+33)
runtime.cc:655]   native: #32 pc 0047d0e4  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/base.apk (offset 2743000) (io.zenoh.Session.declareKeyExpr-IoAF18A+20)
runtime.cc:655]   native: #33 pc 007a335e  /apex/com.android.art/lib/libart.so (MterpInvokeVirtual+1806)
runtime.cc:655]   native: #34 pc 00135921  /apex/com.android.art/lib/libart.so (mterp_op_invoke_virtual+33)
runtime.cc:655]   native: #35 pc 00009f10  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/base.apk (offset 38e1000) (com.myapp.network.ZenohClient$subscribe$2.invokeSuspend+72)
runtime.cc:655]   native: #36 pc 007a335e  /apex/com.android.art/lib/libart.so (MterpInvokeVirtual+1806)
runtime.cc:655]   native: #37 pc 00135921  /apex/com.android.art/lib/libart.so (mterp_op_invoke_virtual+33)
runtime.cc:655]   native: #38 pc 004e5d76  [anon:dalvik-classes.dex extracted in memory from /data/app/~~w_I4onzSn3hZreaUFwVXmw==/com.atakmap.app.civ-WwRU1m-Y3zBsDKevYZWaJw==/base.apk] (kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith+42)
runtime.cc:655]   native: #39 pc 007a53be  /apex/com.android.art/lib/libart.so (MterpInvokeInterface+2126)
runtime.cc:655]   native: #40 pc 00135b21  /apex/com.android.art/lib/libart.so (mterp_op_invoke_interface+33)
runtime.cc:655]   native: #41 pc 000822c8  [anon:dalvik-classes17.dex extracted in memory from /data/app/~~w_I4onzSn3hZreaUFwVXmw==/com.atakmap.app.civ-WwRU1m-Y3zBsDKevYZWaJw==/base.apk!classes17.dex] (kotlinx.coroutines.DispatchedTask.run+444)
runtime.cc:655]   native: #42 pc 007a53be  /apex/com.android.art/lib/libart.so (MterpInvokeInterface+2126)
runtime.cc:655]   native: #43 pc 00135b21  /apex/com.android.art/lib/libart.so (mterp_op_invoke_interface+33)
runtime.cc:655]   native: #67 pc 006313f5  /apex/com.android.art/lib/libart.so (art::JValue art::InvokeVirtualOrInterfaceWithJValues<_jmethodID*>(art::ScopedObjectAccessAlreadyRunnable const&, _jobject*, _jmethodID*, jvalue const*)+85)
runtime.cc:655]   native: #68 pc 00699561  /apex/com.android.art/lib/libart.so (art::Thread::CreateCallback(void*)+1537)
runtime.cc:655]   native: #69 pc 000e6964  /apex/com.android.runtime/lib/bionic/libc.so (__pthread_start(void*)+100)
runtime.cc:655]   native: #70 pc 00078557  /apex/com.android.runtime/lib/bionic/libc.so (__start_thread+71)
runtime.cc:655]   at io.zenoh.jni.JNISession.declareKeyExprViaJNI(Native method)
runtime.cc:655]   at io.zenoh.jni.JNISession.declareKeyExpr-IoAF18A(JNISession.kt:211)
runtime.cc:655]   at io.zenoh.Session.declareKeyExpr-IoAF18A(Session.kt:415)
runtime.cc:655]   at com.myapp.network.ZenohClient$subscribe$2.invokeSuspend(ZenohClient.kt:135)
runtime.cc:655]   at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(SourceFile:33)
runtime.cc:655]   at kotlinx.coroutines.DispatchedTask.run(SourceFile:106)
runtime.cc:655]   at kotlinx.coroutines.internal.LimitedDispatcher.run(SourceFile:42)
runtime.cc:655]   at kotlinx.coroutines.scheduling.TaskImpl.run(SourceFile:95)
runtime.cc:655]   at kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely(SourceFile:570)
runtime.cc:655]   at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.executeTask(SourceFile:750)
runtime.cc:655]   at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.runWorker(SourceFile:677)
runtime.cc:655]   at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run(SourceFile:664)
runtime.cc:655] 
runtime.cc:655] "main" prio=10 tid=1 Native
runtime.cc:655]   | group="" sCount=1 dsCount=0 flags=1 obj=0x71e32c28 self=0xdf446210
runtime.cc:655]   | sysTid=9056 nice=-10 cgrp=top-app sched=0/0 handle=0xeda2f478
runtime.cc:655]   | state=S schedstat=( 3883319539 486307899 3829 ) utm=324 stm=64 core=3 HZ=100
runtime.cc:655]   | stack=0xff139000-0xff13b000 stackSize=8192KB
runtime.cc:655]   | held mutexes=
runtime.cc:655]   native: #00 pc 00000b87  [vdso] (__kernel_vsyscall+7)
runtime.cc:655]   native: #01 pc 0005ad58  /apex/com.android.runtime/lib/bionic/libc.so (syscall+40)
runtime.cc:655]   native: #02 pc 001d846c  /apex/com.android.art/lib/libart.so (art::ConditionVariable::WaitHoldingLocks(art::Thread*)+108)
runtime.cc:655]   native: #03 pc 001d83f3  /apex/com.android.art/lib/libart.so (art::ConditionVariable::Wait(art::Thread*)+35)
runtime.cc:655]   native: #04 pc 003f8368  /apex/com.android.art/lib/libart.so (art::(anonymous namespace)::CheckJNI::CallMethodV(char const*, _JNIEnv*, _jobject*, _jclass*, _jmethodID*, char*, art::Primitive::Type, art::InvokeType)+600)
runtime.cc:655]   native: #05 pc 003e33d9  /apex/com.android.art/lib/libart.so (art::(anonymous namespace)::CheckJNI::CallObjectMethodV(_JNIEnv*, _jobject*, _jmethodID*, char*)+73)
runtime.cc:655]   native: #06 pc 000046de  /apex/com.android.art/lib/libnativehelper.so (_JNIEnv::CallObjectMethod(_jobject*, _jmethodID*, ...)+62)
runtime.cc:655]   native: #07 pc 00004691  /apex/com.android.art/lib/libnativehelper.so (jniGetReferent+49)
runtime.cc:655]   native: #08 pc 00157bad  /system/lib/libandroid_runtime.so ((anonymous namespace)::Receiver::handleEvent(int, int, void*)+109)
runtime.cc:655]   native: #09 pc 0001a367  /system/lib/libutils.so (android::Looper::pollInner(int)+1127)
runtime.cc:655]   native: #10 pc 00019e96  /system/lib/libutils.so (android::Looper::pollOnce(int, int*, int*, void**)+118)
runtime.cc:655]   native: #11 pc 0010ef8b  /system/lib/libandroid_runtime.so (android::android_os_MessageQueue_nativePollOnce(_JNIEnv*, _jobject*, long long, int)+59)
runtime.cc:655]   at android.os.MessageQueue.nativePollOnce(Native method)
runtime.cc:655]   at android.os.MessageQueue.next(MessageQueue.java:335)
runtime.cc:655]   at android.os.Looper.loop(Looper.java:183)
runtime.cc:655]   native: #09 pc 00406098  /apex/com.android.art/lib/libart.so (art::JavaVMExt::JniAbortF(char const*, char const*, ...)+120)
runtime.cc:655]   native: #10 pc 006aa337  /apex/com.android.art/lib/libart.so (art::Thread::DecodeJObject(_jobject*) const+935)
runtime.cc:655]   native: #11 pc 003f56bb  /apex/com.android.art/lib/libart.so (art::(anonymous namespace)::ScopedCheck::CheckInstance(art::ScopedObjectAccess&, art::(anonymous namespace)::ScopedCheck::InstanceKind, _jobject*, bool)+91)
runtime.cc:655]   native: #12 pc 003f407e  /apex/com.android.art/lib/libart.so (art::(anonymous namespace)::ScopedCheck::CheckPossibleHeapValue(art::ScopedObjectAccess&, char, art::(anonymous namespace)::JniValueType)+446)
runtime.cc:655]   native: #13 pc 003f3669  /apex/com.android.art/lib/libart.so (art::(anonymous namespace)::ScopedCheck::Check(art::ScopedObjectAccess&, bool, char const*, art::(anonymous namespace)::JniValueType*)+969)
runtime.cc:655]   native: #14 pc 003e253e  /apex/com.android.art/lib/libart.so (art::(anonymous namespace)::CheckJNI::GetObjectClass(_JNIEnv*, _jobject*)+798)
runtime.cc:655]   native: #15 pc 01317263  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/lib/x86/libzenoh_jni.so (jni::wrapper::jnienv::JNIEnv::get_string::hee0db855e37a8fb2+547)
runtime.cc:655]   native: #16 pc 0083b0a2  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/lib/x86/libzenoh_jni.so (???)
runtime.cc:655]   native: #17 pc 00835395  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/lib/x86/libzenoh_jni.so (Java_io_zenoh_jni_JNISession_declareKeyExprViaJNI+53)
runtime.cc:655]   native: #18 pc 001422b2  /apex/com.android.art/lib/libart.so (art_quick_generic_jni_trampoline+82)
runtime.cc:655]   native: #19 pc 0013baa2  /apex/com.android.art/lib/libart.so (art_quick_invoke_stub+338)
runtime.cc:655]   native: #20 pc 001d0501  /apex/com.android.art/lib/libart.so (art::ArtMethod::Invoke(art::Thread*, unsigned int*, unsigned int, art::JValue*, char const*)+241)
runtime.cc:655]   native: #21 pc 00386881  /apex/com.android.art/lib/libart.so (art::interpreter::ArtInterpreterToCompiledCodeBridge(art::Thread*, art::ArtMethod*, art::ShadowFrame*, unsigned short, art::JValue*)+385)
runtime.cc:655]   native: #22 pc 0037abbe  /apex/com.android.art/lib/libart.so (bool art::interpreter::DoCall<false, false>(art::ArtMethod*, art::Thread*, art::ShadowFrame&, art::Instruction const*, unsigned short, art::JValue*)+1070)
runtime.cc:655]   native: #23 pc 007a5fd9  /apex/com.android.art/lib/libart.so (MterpInvokeDirect+633)
runtime.cc:655]   native: #24 pc 00135a21  /apex/com.android.art/lib/libart.so (mterp_op_invoke_direct+33)
runtime.cc:655]   native: #25 pc 00481386  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/base.apk (offset 2743000) (io.zenoh.jni.JNISession.declareKeyExpr-IoAF18A+34)
runtime.cc:655]   native: #26 pc 007a335e  /apex/com.android.art/lib/libart.so (MterpInvokeVirtual+1806)
runtime.cc:655]   native: #27 pc 00135921  /apex/com.android.art/lib/libart.so (mterp_op_invoke_virtual+33)
runtime.cc:655]   native: #28 pc 0047d0e4  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/base.apk (offset 2743000) (io.zenoh.Session.declareKeyExpr-IoAF18A+20)
runtime.cc:655]   native: #29 pc 007a335e  /apex/com.android.art/lib/libart.so (MterpInvokeVirtual+1806)
runtime.cc:655]   native: #30 pc 00135921  /apex/com.android.art/lib/libart.so (mterp_op_invoke_virtual+33)
runtime.cc:655]   native: #31 pc 00009f10  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/base.apk (offset 38e1000) (com.myapp.network.ZenohClient$subscribe$2.invokeSuspend+72)
runtime.cc:655]   native: #32 pc 007a335e  /apex/com.android.art/lib/libart.so (MterpInvokeVirtual+1806)
runtime.cc:655]   native: #21 pc 001d0501  /apex/com.android.art/lib/libart.so (art::ArtMethod::Invoke(art::Thread*, unsigned int*, unsigned int, art::JValue*, char const*)+241)
runtime.cc:655]   native: #22 pc 00386881  /apex/com.android.art/lib/libart.so (art::interpreter::ArtInterpreterToCompiledCodeBridge(art::Thread*, art::ArtMethod*, art::ShadowFrame*, unsigned short, art::JValue*)+385)
runtime.cc:655]   native: #23 pc 0037abbe  /apex/com.android.art/lib/libart.so (bool art::interpreter::DoCall<false, false>(art::ArtMethod*, art::Thread*, art::ShadowFrame&, art::Instruction const*, unsigned short, art::JValue*)+1070)
runtime.cc:655]   native: #24 pc 007a5fd9  /apex/com.android.art/lib/libart.so (MterpInvokeDirect+633)
runtime.cc:655]   native: #25 pc 00135a21  /apex/com.android.art/lib/libart.so (mterp_op_invoke_direct+33)
runtime.cc:655]   native: #26 pc 00481386  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/base.apk (offset 2743000) (io.zenoh.jni.JNISession.declareKeyExpr-IoAF18A+34)
runtime.cc:655]   native: #27 pc 007a335e  /apex/com.android.art/lib/libart.so (MterpInvokeVirtual+1806)
runtime.cc:655]   native: #28 pc 00135921  /apex/com.android.art/lib/libart.so (mterp_op_invoke_virtual+33)
runtime.cc:655]   native: #29 pc 0047d0e4  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/base.apk (offset 2743000) (io.zenoh.Session.declareKeyExpr-IoAF18A+20)
runtime.cc:655]   native: #30 pc 007a335e  /apex/com.android.art/lib/libart.so (MterpInvokeVirtual+1806)
runtime.cc:655]   native: #31 pc 00135921  /apex/com.android.art/lib/libart.so (mterp_op_invoke_virtual+33)
runtime.cc:655]   native: #32 pc 00009f10  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/base.apk (offset 38e1000) (com.myapp.network.ZenohClient$subscribe$2.invokeSuspend+72)
runtime.cc:655]   native: #33 pc 007a335e  /apex/com.android.art/lib/libart.so (MterpInvokeVirtual+1806)
runtime.cc:655]   native: #34 pc 00135921  /apex/com.android.art/lib/libart.so (mterp_op_invoke_virtual+33)
runtime.cc:655]   native: #35 pc 004e5d76  [anon:dalvik-classes.dex extracted in memory from /data/app/~~w_I4onzSn3hZreaUFwVXmw==/com.atakmap.app.civ-WwRU1m-Y3zBsDKevYZWaJw==/base.apk] (kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith+42)
runtime.cc:655]   native: #36 pc 007a53be  /apex/com.android.art/lib/libart.so (MterpInvokeInterface+2126)
runtime.cc:655]   native: #37 pc 00135b21  /apex/com.android.art/lib/libart.so (mterp_op_invoke_interface+33)
runtime.cc:655]   native: #38 pc 000822c8  [anon:dalvik-classes17.dex extracted in memory from /data/app/~~w_I4onzSn3hZreaUFwVXmw==/com.atakmap.app.civ-WwRU1m-Y3zBsDKevYZWaJw==/base.apk!classes17.dex] (kotlinx.coroutines.DispatchedTask.run+444)
runtime.cc:655]   native: #39 pc 007a53be  /apex/com.android.art/lib/libart.so (MterpInvokeInterface+2126)
runtime.cc:655]   native: #40 pc 00135b21  /apex/com.android.art/lib/libart.so (mterp_op_invoke_interface+33)
runtime.cc:655]   native: #41 pc 000b78be  [anon:dalvik-classes17.dex extracted in memory from /data/app/~~w_I4onzSn3hZreaUFwVXmw==/com.atakmap.app.civ-WwRU1m-Y3zBsDKevYZWaJw==/base.apk!classes17.dex] (kotlinx.coroutines.internal.LimitedDispatcher.run+26)
runtime.cc:655]   native: #42 pc 007a53be  /apex/com.android.art/lib/libart.so (MterpInvokeInterface+2126)
runtime.cc:655]   native: #43 pc 00135b21  /apex/com.android.art/lib/libart.so (mterp_op_invoke_interface+33)
runtime.cc:655]   native: #44 pc 000bf362  [anon:dalvik-classes17.dex extracted in memory from /data/app/~~w_I4onzSn3hZreaUFwVXmw==/com.atakmap.app.civ-WwRU1m-Y3zBsDKevYZWaJw==/base.apk!classes17.dex] (kotlinx.coroutines.scheduling.TaskImpl.run+6)
runtime.cc:655]   native: #45 pc 007a335e  /apex/com.android.art/lib/libart.so (MterpInvokeVirtual+1806)
runtime.cc:655]   native: #46 pc 00135921  /apex/com.android.art/lib/libart.so (mterp_op_invoke_virtual+33)
runtime.cc:655]   native: #47 pc 000be46a  [anon:dalvik-classes17.dex extracted in memory from /data/app/~~w_I4onzSn3hZreaUFwVXmw==/com.atakmap.app.civ-WwRU1m-Y3zBsDKevYZWaJw==/base.apk!classes17.dex] (kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely+2)
runtime.cc:655]   native: #48 pc 007a335e  /apex/com.android.art/lib/libart.so (MterpInvokeVirtual+1806)
runtime.cc:655]   native: #49 pc 00135921  /apex/com.android.art/lib/libart.so (mterp_op_invoke_virtual+33)
runtime.cc:655]   native: #50 pc 000bd17e  [anon:dalvik-classes17.dex extracted in memory from /data/app/~~w_I4onzSn3hZreaUFwVXmw==/com.atakmap.app.civ-WwRU1m-Y3zBsDKevYZWaJw==/base.apk!classes17.dex] (kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.executeTask+34)
runtime.cc:655]   native: #51 pc 007a630e  /apex/com.android.art/lib/libart.so (MterpInvokeDirect+1454)
runtime.cc:655]   native: #52 pc 00135a21  /apex/com.android.art/lib/libart.so (mterp_op_invoke_direct+33)
runtime.cc:655]   native: #53 pc 000bd2ac  [anon:dalvik-classes17.dex extracted in memory from /data/app/~~w_I4onzSn3hZreaUFwVXmw==/com.atakmap.app.civ-WwRU1m-Y3zBsDKevYZWaJw==/base.apk!classes17.dex] (kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.runWorker+56)
runtime.cc:655]   native: #54 pc 007a630e  /apex/com.android.art/lib/libart.so (MterpInvokeDirect+1454)
runtime.cc:655]   native: #55 pc 00135a21  /apex/com.android.art/lib/libart.so (mterp_op_invoke_direct+33)
runtime.cc:655]   native: #56 pc 000bd25c  [anon:dalvik-classes17.dex extracted in memory from /data/app/~~w_I4onzSn3hZreaUFwVXmw==/com.atakmap.app.civ-WwRU1m-Y3zBsDKevYZWaJw==/base.apk!classes17.dex] (kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run)
runtime.cc:655]   native: #57 pc 0036fc82  /apex/com.android.art/lib/libart.so (art::interpreter::Execute(art::Thread*, art::CodeItemDataAccessor const&, art::ShadowFrame&, art::JValue, bool, bool) (.llvm.10914192770458939989)+370)
runtime.cc:655]   native: #58 pc 00379c80  /apex/com.android.art/lib/libart.so (art::interpreter::EnterInterpreterFromEntryPoint(art::Thread*, art::CodeItemDataAccessor const&, art::ShadowFrame*)+176)
runtime.cc:655]   native: #59 pc 0078d185  /apex/com.android.art/lib/libart.so (artQuickToInterpreterBridge+1061)
runtime.cc:655]   native: #60 pc 0014238d  /apex/com.android.art/lib/libart.so (art_quick_to_interpreter_bridge+77)
runtime.cc:655]   native: #61 pc 0013baa2  /apex/com.android.art/lib/libart.so (art_quick_invoke_stub+338)
runtime.cc:655]   native: #62 pc 001d0501  /apex/com.android.art/lib/libart.so (art::ArtMethod::Invoke(art::Thread*, unsigned int*, unsigned int, art::JValue*, char const*)+241)
runtime.cc:655]   native: #63 pc 006311dc  /apex/com.android.art/lib/libart.so (art::JValue art::InvokeVirtualOrInterfaceWithJValues<art::ArtMethod*>(art::ScopedObjectAccessAlreadyRunnable const&, _jobject*, art::ArtMethod*, jvalue const*)+620)
runtime.cc:655]   native: #64 pc 006313f5  /apex/com.android.art/lib/libart.so (art::JValue art::InvokeVirtualOrInterfaceWithJValues<_jmethodID*>(art::ScopedObjectAccessAlreadyRunnable const&, _jobject*, _jmethodID*, jvalue const*)+85)
runtime.cc:655]   native: #65 pc 00699561  /apex/com.android.art/lib/libart.so (art::Thread::CreateCallback(void*)+1537)
runtime.cc:655]   native: #66 pc 000e6964  /apex/com.android.runtime/lib/bionic/libc.so (__pthread_start(void*)+100)
runtime.cc:655]   native: #67 pc 00078557  /apex/com.android.runtime/lib/bionic/libc.so (__start_thread+71)
runtime.cc:655]   at io.zenoh.jni.JNISession.declareKeyExprViaJNI(Native method)
runtime.cc:655]   at io.zenoh.jni.JNISession.declareKeyExpr-IoAF18A(JNISession.kt:211)
runtime.cc:655]   at io.zenoh.Session.declareKeyExpr-IoAF18A(Session.kt:415)
runtime.cc:655]   at com.myapp.network.ZenohClient$subscribe$2.invokeSuspend(ZenohClient.kt:135)
runtime.cc:663] JNI DETECTED ERROR IN APPLICATION: use of invalid jobject 0x2dfdb718
runtime.cc:663]     from long io.zenoh.jni.JNISession.declareKeyExprViaJNI(long, java.lang.String)

Abort message: 'JNI DETECTED ERROR IN APPLICATION: use of invalid jobject 0x2dfdb718
                                                          from long io.zenoh.jni.JNISession.declareKeyExprViaJNI(long, java.lang.String)'
      #15 pc 01317263  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/lib/x86/libzenoh_jni.so (jni::wrapper::jnienv::JNIEnv::get_string::hee0db855e37a8fb2+547)
      #16 pc 0083b0a2  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/lib/x86/libzenoh_jni.so
      #17 pc 00835395  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/lib/x86/libzenoh_jni.so (Java_io_zenoh_jni_JNISession_declareKeyExprViaJNI+53)
      #25 pc 00481386  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/base.apk (offset 0x2743000) (io.zenoh.jni.JNISession.declareKeyExpr-IoAF18A+34)
      #28 pc 0047d0e4  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/base.apk (offset 0x2743000) (io.zenoh.Session.declareKeyExpr-IoAF18A+20)
      #31 pc 00009f10  /data/app/~~bGjE2jw-WcNyDW97z165eQ==/com.myapp-0Ynmu8fbFC868WkRi8zrLA==/base.apk (offset 0x38e1000) (com.myapp.network.ZenohClient$subscribe$2.invokeSuspend+72)
message.txt
```
