// Targeted by JavaCPP version 1.5.3-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.leptonica;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.leptonica.global.lept.*;


/** Deallocator function type */
@Properties(inherit = org.bytedeco.leptonica.presets.lept.class)
public class dealloc_fn extends FunctionPointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public    dealloc_fn(Pointer p) { super(p); }
    protected dealloc_fn() { allocate(); }
    private native void allocate();
    public native void call(Pointer arg0);
}