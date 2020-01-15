// Targeted by JavaCPP version 1.5.3-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.parquet;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import org.bytedeco.arrow.*;
import static org.bytedeco.arrow.global.arrow.*;

import static org.bytedeco.arrow.global.parquet.*;


@Namespace("parquet") @NoOffset @Properties(inherit = org.bytedeco.arrow.presets.parquet.class)
public class ColumnProperties extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public ColumnProperties(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public ColumnProperties(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public ColumnProperties position(long position) {
        return (ColumnProperties)super.position(position);
    }

  public ColumnProperties(Encoding.type encoding/*=parquet::DEFAULT_ENCODING*/,
                     Compression.type codec/*=parquet::DEFAULT_COMPRESSION_TYPE*/,
                     @Cast("bool") boolean dictionary_enabled/*=parquet::DEFAULT_IS_DICTIONARY_ENABLED*/,
                     @Cast("bool") boolean statistics_enabled/*=parquet::DEFAULT_ARE_STATISTICS_ENABLED*/,
                     @Cast("size_t") long max_stats_size/*=parquet::DEFAULT_MAX_STATISTICS_SIZE*/) { super((Pointer)null); allocate(encoding, codec, dictionary_enabled, statistics_enabled, max_stats_size); }
  private native void allocate(Encoding.type encoding/*=parquet::DEFAULT_ENCODING*/,
                     Compression.type codec/*=parquet::DEFAULT_COMPRESSION_TYPE*/,
                     @Cast("bool") boolean dictionary_enabled/*=parquet::DEFAULT_IS_DICTIONARY_ENABLED*/,
                     @Cast("bool") boolean statistics_enabled/*=parquet::DEFAULT_ARE_STATISTICS_ENABLED*/,
                     @Cast("size_t") long max_stats_size/*=parquet::DEFAULT_MAX_STATISTICS_SIZE*/);
  public ColumnProperties() { super((Pointer)null); allocate(); }
  private native void allocate();
  public ColumnProperties(@Cast("parquet::Encoding::type") int encoding/*=parquet::DEFAULT_ENCODING*/,
                     @Cast("arrow::Compression::type") int codec/*=parquet::DEFAULT_COMPRESSION_TYPE*/,
                     @Cast("bool") boolean dictionary_enabled/*=parquet::DEFAULT_IS_DICTIONARY_ENABLED*/,
                     @Cast("bool") boolean statistics_enabled/*=parquet::DEFAULT_ARE_STATISTICS_ENABLED*/,
                     @Cast("size_t") long max_stats_size/*=parquet::DEFAULT_MAX_STATISTICS_SIZE*/) { super((Pointer)null); allocate(encoding, codec, dictionary_enabled, statistics_enabled, max_stats_size); }
  private native void allocate(@Cast("parquet::Encoding::type") int encoding/*=parquet::DEFAULT_ENCODING*/,
                     @Cast("arrow::Compression::type") int codec/*=parquet::DEFAULT_COMPRESSION_TYPE*/,
                     @Cast("bool") boolean dictionary_enabled/*=parquet::DEFAULT_IS_DICTIONARY_ENABLED*/,
                     @Cast("bool") boolean statistics_enabled/*=parquet::DEFAULT_ARE_STATISTICS_ENABLED*/,
                     @Cast("size_t") long max_stats_size/*=parquet::DEFAULT_MAX_STATISTICS_SIZE*/);

  public native void set_encoding(Encoding.type encoding);
  public native void set_encoding(@Cast("parquet::Encoding::type") int encoding);

  public native void set_compression(Compression.type codec);
  public native void set_compression(@Cast("arrow::Compression::type") int codec);

  public native void set_dictionary_enabled(@Cast("bool") boolean dictionary_enabled);

  public native void set_statistics_enabled(@Cast("bool") boolean statistics_enabled);

  public native void set_max_statistics_size(@Cast("size_t") long max_stats_size);

  public native void set_compression_level(int compression_level);

  public native Encoding.type encoding();

  public native Compression.type compression();

  public native @Cast("bool") boolean dictionary_enabled();

  public native @Cast("bool") boolean statistics_enabled();

  public native @Cast("size_t") long max_statistics_size();

  public native int compression_level();
}
