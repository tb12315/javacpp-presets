// Targeted by JavaCPP version 1.5.3-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.parquet;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import org.bytedeco.arrow.*;
import static org.bytedeco.arrow.global.arrow.*;

import static org.bytedeco.arrow.global.parquet.*;
  // namespace BitUtil
@Namespace("arrow::util") @Opaque @Properties(inherit = org.bytedeco.arrow.presets.parquet.class)
public class RleEncoder extends Pointer {
    /** Empty constructor. Calls {@code super((Pointer)null)}. */
    public RleEncoder() { super((Pointer)null); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public RleEncoder(Pointer p) { super(p); }
}
