package com.ocean.myappcheckrooted

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

object DeviceRooted {

    val isDeviceRooted: Boolean get() = checkRootMethod1() || checkRootMethod2() || checkRootMethod3()

    private fun checkRootMethod1(): Boolean{
        val buildTags = android.os.Build.TAGS
        return buildTags != null && buildTags.contains("test-keys")
    }

    private fun checkRootMethod2(): Boolean{
        val paths = arrayOf("/system/app/Superuser.apk",
            "/sbin/su",
            "/system/bin/su",
            "/system/xbin/su",
            "/data/local/xbin/su",
            "/data/local/bin/su",
            "/system/sd/xbin/su",
            "/system/bin/failsafe/su",
            "/data/local/su")
        return paths.any{File(it).exists()}
    }

    private fun checkRootMethod3(): Boolean{
        var process: Process? = null
        return try {
            process = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
            BufferedReader(InputStreamReader(process.inputStream)).use {
                it.readLine() != null
            }
        }catch (t: Throwable){
            false
        }finally {
            process?.destroy()
        }
    }
}