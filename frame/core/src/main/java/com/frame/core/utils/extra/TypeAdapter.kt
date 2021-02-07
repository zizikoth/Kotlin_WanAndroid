package com.frame.core.utils.more

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException


class BooleanTypeAdapter : TypeAdapter<Boolean>() {
    @Throws(IOException::class)
    override fun read(reader: JsonReader): Boolean {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return false
        }
        return reader.nextBoolean()
    }

    @Throws(IOException::class)
    override fun write(writer: JsonWriter, value: Boolean?) {
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(value)
    }
}

class DoubleTypeAdapter : TypeAdapter<Double>() {
    @Throws(IOException::class)
    override fun read(reader: JsonReader): Double {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return 0.0
        }
        return reader.nextDouble()
    }

    @Throws(IOException::class)
    override fun write(writer: JsonWriter, value: Double?) {
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(value)
    }
}

class FloatTypeAdapter : TypeAdapter<Float>() {
    @Throws(IOException::class)
    override fun read(reader: JsonReader): Float {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return 0f
        }
        return reader.nextDouble().toFloat()
    }

    @Throws(IOException::class)
    override fun write(writer: JsonWriter, value: Float?) {
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(value)
    }
}

class IntTypeAdapter : TypeAdapter<Int>() {
    @Throws(IOException::class)
    override fun read(reader: JsonReader): Int {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return 0
        }
        return reader.nextInt()
    }

    @Throws(IOException::class)
    override fun write(writer: JsonWriter, value: Int?) {
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(value)
    }
}

class LongTypeAdapter : TypeAdapter<Long>() {
    @Throws(IOException::class)
    override fun read(reader: JsonReader): Long {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return 0L
        }
        return reader.nextLong()
    }

    @Throws(IOException::class)
    override fun write(writer: JsonWriter, value: Long?) {
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(value)
    }
}

class StringTypeAdapter : TypeAdapter<String>() {
    @Throws(IOException::class)
    override fun read(reader: JsonReader): String {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return ""
        }
        return reader.nextString()
    }

    @Throws(IOException::class)
    override fun write(writer: JsonWriter, value: String?) {
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(value)
    }
}