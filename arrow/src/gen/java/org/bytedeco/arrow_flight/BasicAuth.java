// Targeted by JavaCPP version 1.5.3-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.arrow_flight;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import org.bytedeco.arrow.*;
import static org.bytedeco.arrow.global.arrow.*;

import static org.bytedeco.arrow.global.arrow_flight.*;


/** \brief message for simple auth */
@Namespace("arrow::flight") @Properties(inherit = org.bytedeco.arrow.presets.arrow_flight.class)
public class BasicAuth extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public BasicAuth() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public BasicAuth(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public BasicAuth(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public BasicAuth position(long position) {
        return (BasicAuth)super.position(position);
    }

  public native @StdString String username(); public native BasicAuth username(String setter);
  public native @StdString String password(); public native BasicAuth password(String setter);

  public static native @ByVal Status Deserialize(@StdString String serialized, BasicAuth out);
  public static native @ByVal Status Deserialize(@StdString BytePointer serialized, BasicAuth out);

  public static native @ByVal Status Serialize(@Const @ByRef BasicAuth basic_auth, @StdString @Cast({"char*", "std::string*"}) BytePointer out);
}
