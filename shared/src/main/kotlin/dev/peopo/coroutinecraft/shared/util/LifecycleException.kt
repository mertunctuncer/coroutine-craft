package dev.peopo.coroutinecraft.shared.util

public class LifecycleException: Exception {
    public constructor(message: String) : super(message)
    public constructor(message: String, cause: Throwable) : super(message, cause)
}