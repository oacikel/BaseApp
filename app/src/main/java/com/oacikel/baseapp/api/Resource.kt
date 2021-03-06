package com.oacikel.baseapp.api

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
class Resource<T>(val status: Status, val data: T?, val message: String?, val errorCode: Int?) {

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }

        val resource = o as Resource<*>?

        if (status != resource!!.status) {
            return false
        }
        if (if (message != null) message != resource.message else resource.message != null) {
            return false
        }
        return if (data != null) data == resource.data else resource.data == null
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + (message?.hashCode() ?: 0)
        result = 31 * result + (data?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\''.toString() +
                ", data=" + data +
                '}'.toString()
    }

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null)
        }

        fun <T> error(code: Int, msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg, code)
        }

        fun <T> unvisibleError(code: Int, msg: String, data: T?): Resource<T> {
            return Resource(Status.INVISIBLE_ERROR, data, msg, code)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null, null)
        }

        fun <T> offline(data: T?): Resource<T> {
            return Resource(Status.OFFLINE, data, null, null)
        }
    }
}