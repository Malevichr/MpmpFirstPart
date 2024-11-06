package helloworld.data

interface LoadResult {
    fun isError() : Boolean
    object Success : LoadResult {
        override fun isError() = false
    }
    object Error : LoadResult {
        override fun isError() = true
    }
}
