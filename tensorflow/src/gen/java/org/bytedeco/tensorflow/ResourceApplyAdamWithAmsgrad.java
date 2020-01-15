// Targeted by JavaCPP version 1.5.3-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.tensorflow;

import org.bytedeco.tensorflow.Allocator;
import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.tensorflow.global.tensorflow.*;


/** Update '*var' according to the Adam algorithm.
 * 
 *  $$lr_t := \text{learning\_rate} * \sqrt{1 - beta_2^t} / (1 - beta_1^t)$$
 *  $$m_t := beta_1 * m_{t-1} + (1 - beta_1) * g$$
 *  $$v_t := beta_2 * v_{t-1} + (1 - beta_2) * g * g$$
 *  $$vhat_t := max{vhat_{t-1}, v_t}$$
 *  $$variable := variable - lr_t * m_t / (\sqrt{vhat_t} + \epsilon)$$
 * 
 *  Arguments:
 *  * scope: A Scope object
 *  * var: Should be from a Variable().
 *  * m: Should be from a Variable().
 *  * v: Should be from a Variable().
 *  * vhat: Should be from a Variable().
 *  * beta1_power: Must be a scalar.
 *  * beta2_power: Must be a scalar.
 *  * lr: Scaling factor. Must be a scalar.
 *  * beta1: Momentum factor. Must be a scalar.
 *  * beta2: Momentum factor. Must be a scalar.
 *  * epsilon: Ridge term. Must be a scalar.
 *  * grad: The gradient.
 * 
 *  Optional attributes (see {@code Attrs}):
 *  * use_locking: If {@code True}, updating of the var, m, and v tensors will be protected
 *  by a lock; otherwise the behavior is undefined, but may exhibit less
 *  contention.
 * 
 *  Returns:
 *  * the created {@code Operation} */
@Namespace("tensorflow::ops") @NoOffset @Properties(inherit = org.bytedeco.tensorflow.presets.tensorflow.class)
public class ResourceApplyAdamWithAmsgrad extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public ResourceApplyAdamWithAmsgrad(Pointer p) { super(p); }

  /** Optional attribute setters for ResourceApplyAdamWithAmsgrad */
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
  
    /** If {@code True}, updating of the var, m, and v tensors will be protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     * 
     *  Defaults to false */
    public native @ByVal Attrs UseLocking(@Cast("bool") boolean x);

    public native @Cast("bool") boolean use_locking_(); public native Attrs use_locking_(boolean setter);
  }
  public ResourceApplyAdamWithAmsgrad(@Const @ByRef Scope scope,
                               @ByVal Input var, @ByVal Input m,
                               @ByVal Input v, @ByVal Input vhat,
                               @ByVal Input beta1_power,
                               @ByVal Input beta2_power,
                               @ByVal Input lr, @ByVal Input beta1,
                               @ByVal Input beta2, @ByVal Input epsilon, @ByVal Input grad) { super((Pointer)null); allocate(scope, var, m, v, vhat, beta1_power, beta2_power, lr, beta1, beta2, epsilon, grad); }
  private native void allocate(@Const @ByRef Scope scope,
                               @ByVal Input var, @ByVal Input m,
                               @ByVal Input v, @ByVal Input vhat,
                               @ByVal Input beta1_power,
                               @ByVal Input beta2_power,
                               @ByVal Input lr, @ByVal Input beta1,
                               @ByVal Input beta2, @ByVal Input epsilon, @ByVal Input grad);
  public ResourceApplyAdamWithAmsgrad(@Const @ByRef Scope scope,
                               @ByVal Input var, @ByVal Input m,
                               @ByVal Input v, @ByVal Input vhat,
                               @ByVal Input beta1_power,
                               @ByVal Input beta2_power,
                               @ByVal Input lr, @ByVal Input beta1,
                               @ByVal Input beta2, @ByVal Input epsilon, @ByVal Input grad, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, var, m, v, vhat, beta1_power, beta2_power, lr, beta1, beta2, epsilon, grad, attrs); }
  private native void allocate(@Const @ByRef Scope scope,
                               @ByVal Input var, @ByVal Input m,
                               @ByVal Input v, @ByVal Input vhat,
                               @ByVal Input beta1_power,
                               @ByVal Input beta2_power,
                               @ByVal Input lr, @ByVal Input beta1,
                               @ByVal Input beta2, @ByVal Input epsilon, @ByVal Input grad, @Const @ByRef Attrs attrs);
  public native @ByVal @Name("operator tensorflow::Operation") Operation asOperation();

  public static native @ByVal Attrs UseLocking(@Cast("bool") boolean x);

  public native @ByRef Operation operation(); public native ResourceApplyAdamWithAmsgrad operation(Operation setter);
}