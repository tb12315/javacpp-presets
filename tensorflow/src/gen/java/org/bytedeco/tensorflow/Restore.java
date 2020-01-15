// Targeted by JavaCPP version 1.5.3-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.tensorflow;

import org.bytedeco.tensorflow.Allocator;
import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.tensorflow.global.tensorflow.*;


/** Restores a tensor from checkpoint files.
 * 
 *  Reads a tensor stored in one or several files. If there are several files (for
 *  instance because a tensor was saved as slices), {@code file_pattern} may contain
 *  wildcard symbols ({@code *} and {@code ?}) in the filename portion only, not in the
 *  directory portion.
 * 
 *  If a {@code file_pattern} matches several files, {@code preferred_shard} can be used to hint
 *  in which file the requested tensor is likely to be found. This op will first
 *  open the file at index {@code preferred_shard} in the list of matching files and try
 *  to restore tensors from that file.  Only if some tensors or tensor slices are
 *  not found in that first file, then the Op opens all the files. Setting
 *  {@code preferred_shard} to match the value passed as the {@code shard} input
 *  of a matching {@code Save} Op may speed up Restore.  This attribute only affects
 *  performance, not correctness.  The default value -1 means files are processed in
 *  order.
 * 
 *  See also {@code RestoreSlice}.
 * 
 *  Arguments:
 *  * scope: A Scope object
 *  * file_pattern: Must have a single element. The pattern of the files from
 *  which we read the tensor.
 *  * tensor_name: Must have a single element. The name of the tensor to be
 *  restored.
 *  * dt: The type of the tensor to be restored.
 * 
 *  Optional attributes (see {@code Attrs}):
 *  * preferred_shard: Index of file to open first if multiple files match
 *  {@code file_pattern}.
 * 
 *  Returns:
 *  * {@code Output}: The restored tensor. */
@Namespace("tensorflow::ops") @NoOffset @Properties(inherit = org.bytedeco.tensorflow.presets.tensorflow.class)
public class Restore extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public Restore(Pointer p) { super(p); }

  /** Optional attribute setters for Restore */
  public static class Attrs extends Pointer {
      static { Loader.load(); }
      /** Default native constructor. */
      public Attrs() { super((Pointer)null); allocate(); }
      /** Native array allocator. Access with {@link Pointer#position(long)}. */
      public Attrs(long size) { super((Pointer)null); allocateArray(size); }
      /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
      public Attrs(Pointer p) { super(p); }
      private native void allocate();
      private native void allocateArray(long size);
      @Override public Attrs position(long position) {
          return (Attrs)super.position(position);
      }
  
    /** Index of file to open first if multiple files match
     *  {@code file_pattern}.
     * 
     *  Defaults to -1 */
    public native @ByVal Attrs PreferredShard(@Cast("tensorflow::int64") long x);

    public native @Cast("tensorflow::int64") long preferred_shard_(); public native Attrs preferred_shard_(long setter);
  }
  public Restore(@Const @ByRef Scope scope, @ByVal Input file_pattern,
          @ByVal Input tensor_name, @Cast("tensorflow::DataType") int dt) { super((Pointer)null); allocate(scope, file_pattern, tensor_name, dt); }
  private native void allocate(@Const @ByRef Scope scope, @ByVal Input file_pattern,
          @ByVal Input tensor_name, @Cast("tensorflow::DataType") int dt);
  public Restore(@Const @ByRef Scope scope, @ByVal Input file_pattern,
          @ByVal Input tensor_name, @Cast("tensorflow::DataType") int dt, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, file_pattern, tensor_name, dt, attrs); }
  private native void allocate(@Const @ByRef Scope scope, @ByVal Input file_pattern,
          @ByVal Input tensor_name, @Cast("tensorflow::DataType") int dt, @Const @ByRef Attrs attrs);
  public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
  public native @ByVal @Name("operator tensorflow::Input") Input asInput();
  public native Node node();

  public static native @ByVal Attrs PreferredShard(@Cast("tensorflow::int64") long x);

  public native @ByRef Operation operation(); public native Restore operation(Operation setter);
  public native @ByRef Output tensor(); public native Restore tensor(Output setter);
}