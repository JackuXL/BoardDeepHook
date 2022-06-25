package cn.jackuxl.boarddeephook

import android.content.Context
import com.github.kyuubiran.ezxhelper.init.EzXHelperInit
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.github.kyuubiran.ezxhelper.utils.hookMethod
import com.github.kyuubiran.ezxhelper.utils.hookReplace
import de.robv.android.xposed.*
import de.robv.android.xposed.callbacks.XC_LoadPackage
import de.robv.android.xposed.XC_MethodHook.MethodHookParam

import de.robv.android.xposed.XC_MethodReplacement

import de.robv.android.xposed.XposedHelpers





class MainHook : IXposedHookLoadPackage {
    @Throws(Throwable::class)
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        EzXHelperInit.initHandleLoadPackage(lpparam)
        if (lpparam.packageName.equals("com.broaddeep.discipline")) {
            findMethod("com.stub.StubApp"){
                name=="a"
            }.hookAfter {
                val context = it.args[0] as Context
                val classLoader = context.classLoader
                XposedBridge.log("ClassLoader gotten.")
                EzXHelperInit.setEzClassLoader(classLoader)
                findMethod("j31"){
                    name=="isVip"
                }.hookReplace {
                    true
                }
            }
//            XposedHelpers.findAndHookMethod("com.stub.StubApp",
//                lpparam.classLoader,
//                "a",
//                Context::class.java,
//                object : XC_MethodHook() {
//                    @Throws(Throwable::class)
//                    override fun afterHookedMethod(param: MethodHookParam) {
//                        super.afterHookedMethod(param)
//                        val context = param.args[0] as Context
//                        val classLoader = context.classLoader
//                        XposedBridge.log("ClassLoader gotten.")
//                        XposedHelpers.findAndHookMethod(
//                            "j31",
//                            classLoader,
//                            "isVip",
//                            object : XC_MethodReplacement() {
//                                //类名和方法名
//                                @Throws(Throwable::class)
//                                override fun replaceHookedMethod(methodHookParam: MethodHookParam): Boolean {
//                                    return true
//                                }
//                            }
//                        )
//
//                    }
//                })
        }

    }
}