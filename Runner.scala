package com.anowell.servur.runner;

import io.Source
import java.io.File

object Runner {
    def main(args: Array[String]) {
        val runnerPath = sys.env("RUN_PATH")
        val runnerClassName = sys.env("RUN_CLASS")
        val runnerMethodName = sys.env.getOrElse("RUN_METHOD", "apply")
        val stdin = Source.stdin.mkString
        println(s"----------Start ${runnerClassName}----------")

        // http://stackoverflow.com/questions/8867766/scala-dynamic-object-class-loading
        var classLoader = new java.net.URLClassLoader(
            Array(new File(runnerPath).toURI.toURL),
            this.getClass.getClassLoader
        )
        var klass = classLoader.loadClass(runnerClassName)

        // TODO: klass.getDeclaredMethods.filter({|m| m.getName == "apply"})
        //       for each => attempt to cast stdin to m.getParameterTypes
        //       For now, just hard-code string input
        val method = klass.getDeclaredMethod(runnerMethodName, classOf[String])
        val retVal = method.invoke(null, stdin)

        // TODO: do something more interesting with the result the print to stdout
        println("----------Finished ${runnerClassName}----------")
        println(s"${klass.getName}.${method.getName} returned ${method.getReturnType}:")
        println(retVal)
    }
}
