// Targeted by JavaCPP version 1.5.3-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.arrow_dataset;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import org.bytedeco.arrow.*;
import static org.bytedeco.arrow.global.arrow.*;
import org.bytedeco.parquet.*;
import static org.bytedeco.arrow.global.parquet.*;

import static org.bytedeco.arrow.global.arrow_dataset.*;


/** \brief Conditions to apply to a dataset when reading to include or
 *  exclude fragments, filter out rows, etc. */
@Namespace("arrow::dataset") @Properties(inherit = org.bytedeco.arrow.presets.arrow_dataset.class)
public class DataSelector extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public DataSelector() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public DataSelector(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public DataSelector(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public DataSelector position(long position) {
        return (DataSelector)super.position(position);
    }

  public native @SharedPtr @StdVector PointerPointer filters(); public native DataSelector filters(PointerPointer setter);

  // TODO(wesm): Select specific partition keys, file path globs, or
  // other common desirable selections
}
