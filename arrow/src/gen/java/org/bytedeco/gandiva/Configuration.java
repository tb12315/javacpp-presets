// Targeted by JavaCPP version 1.5.3-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.gandiva;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import org.bytedeco.arrow.*;
import static org.bytedeco.arrow.global.arrow.*;

import static org.bytedeco.arrow.global.gandiva.*;

/** \brief runtime config for gandiva
 * 
 *  It contains elements to customize gandiva execution
 *  at run time. */
@Namespace("gandiva") @Properties(inherit = org.bytedeco.arrow.presets.gandiva.class)
public class Configuration extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public Configuration() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public Configuration(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public Configuration(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public Configuration position(long position) {
        return (Configuration)super.position(position);
    }


  public native @Cast("std::size_t") long Hash();
  public native @Cast("bool") @Name("operator ==") boolean equals(@Const @ByRef Configuration other);
  public native @Cast("bool") @Name("operator !=") boolean notEquals(@Const @ByRef Configuration other);
}
