// Targeted by JavaCPP version 1.5.3-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.tensorflow;

import org.bytedeco.tensorflow.Allocator;
import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.tensorflow.global.tensorflow.*;


/** \defgroup random_ops Random Ops
 *  \{
 <p>
 *  Draws samples from a multinomial distribution.
 * 
 *  Arguments:
 *  * scope: A Scope object
 *  * logits: 2-D Tensor with shape {@code [batch_size, num_classes]}.  Each slice {@code [i, :]}
 *  represents the unnormalized log probabilities for all classes.
 *  * num_samples: 0-D.  Number of independent samples to draw for each row slice.
 * 
 *  Optional attributes (see {@code Attrs}):
 *  * seed: If either seed or seed2 is set to be non-zero, the internal random number
 *  generator is seeded by the given seed.  Otherwise, a random seed is used.
 *  * seed2: A second seed to avoid seed collision.
 * 
 *  Returns:
 *  * {@code Output}: 2-D Tensor with shape {@code [batch_size, num_samples]}.  Each slice {@code [i, :]}
 *  contains the drawn class labels with range {@code [0, num_classes)}. */
@Namespace("tensorflow::ops") @NoOffset @Properties(inherit = org.bytedeco.tensorflow.presets.tensorflow.class)
public class Multinomial extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public Multinomial(Pointer p) { super(p); }

  /** Optional attribute setters for Multinomial */
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
  
    /** If either seed or seed2 is set to be non-zero, the internal random number
     *  generator is seeded by the given seed.  Otherwise, a random seed is used.
     * 
     *  Defaults to 0 */
    
    ///
    public native @ByVal Attrs Seed(@Cast("tensorflow::int64") long x);

    /** A second seed to avoid seed collision.
     * 
     *  Defaults to 0 */
    public native @ByVal Attrs Seed2(@Cast("tensorflow::int64") long x);

    /** Defaults to DT_INT64 */
    public native @ByVal Attrs OutputDtype(@Cast("tensorflow::DataType") int x);

    public native @Cast("tensorflow::int64") long seed_(); public native Attrs seed_(long setter);
    public native @Cast("tensorflow::int64") long seed2_(); public native Attrs seed2_(long setter);
    public native @Cast("tensorflow::DataType") int output_dtype_(); public native Attrs output_dtype_(int setter);
  }
  public Multinomial(@Const @ByRef Scope scope, @ByVal Input logits,
              @ByVal Input num_samples) { super((Pointer)null); allocate(scope, logits, num_samples); }
  private native void allocate(@Const @ByRef Scope scope, @ByVal Input logits,
              @ByVal Input num_samples);
  public Multinomial(@Const @ByRef Scope scope, @ByVal Input logits,
              @ByVal Input num_samples, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, logits, num_samples, attrs); }
  private native void allocate(@Const @ByRef Scope scope, @ByVal Input logits,
              @ByVal Input num_samples, @Const @ByRef Attrs attrs);
  public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
  public native @ByVal @Name("operator tensorflow::Input") Input asInput();
  public native Node node();

  public static native @ByVal Attrs Seed(@Cast("tensorflow::int64") long x);
  public static native @ByVal Attrs Seed2(@Cast("tensorflow::int64") long x);
  public static native @ByVal Attrs OutputDtype(@Cast("tensorflow::DataType") int x);

  public native @ByRef Operation operation(); public native Multinomial operation(Operation setter);
  public native @ByRef Output output(); public native Multinomial output(Output setter);
}