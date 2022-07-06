package cn.jackuxl.boarddeephook

import android.content.Context
import com.github.kyuubiran.ezxhelper.init.EzXHelperInit
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.github.kyuubiran.ezxhelper.utils.hookReplace
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage

class MainHook : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        EzXHelperInit.initHandleLoadPackage(lpparam)
        if (lpparam.packageName == "com.broaddeep.discipline") {
            findMethod("com.stub.StubApp") {
                name == "a"
            }.hookAfter {
                val context = it.args[0] as Context
                val classLoader = context.classLoader
                EzXHelperInit.setEzClassLoader(classLoader)
                findMethod("j31") {
                    name == "isVip"
                }.hookReplace {
                    true
                }
            }
        }

    }
}