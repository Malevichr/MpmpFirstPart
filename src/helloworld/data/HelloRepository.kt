package helloworld.data

interface HelloRepository {
    fun load(): LoadResult
    class Base : HelloRepository{
        override fun load(): LoadResult {
            return LoadResult.Success
        }
    }
}
