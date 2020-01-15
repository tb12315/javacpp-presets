// Targeted by JavaCPP version 1.5.3-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.tensorflow;

import org.bytedeco.tensorflow.Allocator;
import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.tensorflow.global.tensorflow.*;


// A tensor slice represents a slice of a given tensor. It is represented by a
// list of (start, length) pairs, where the size of the list is the rank of the
// tensor.

@Namespace("tensorflow") @NoOffset @Properties(inherit = org.bytedeco.tensorflow.presets.tensorflow.class)
public class TensorSlice extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public TensorSlice(Pointer p) { super(p); }

  // Construct a tensor slice: you have a number of ways:
  // -- creating an empty slice
  // -- from just a dimension (in this case it will create a full slice)
  // -- from an array of pairs of integers.
  // -- from a TensorSliceProto protocol buffer
  // -- from a string format of "start,length:start,length..." where each
  //    "start,length" pair represents the slice on one dimension. We allow a
  //    special "-" that means "everything for this dimension". One such example
  //    is:  0,10:-:14,1:-:-
  public TensorSlice() { super((Pointer)null); allocate(); }
  private native void allocate();
  public TensorSlice(int dim) { super((Pointer)null); allocate(dim); }
  private native void allocate(int dim);
  public TensorSlice(@Const @ByRef TensorSliceProto proto) { super((Pointer)null); allocate(proto); }
  private native void allocate(@Const @ByRef TensorSliceProto proto);

  public static native @ByVal Status Parse(@StdString BytePointer str, TensorSlice output);
  public static native @ByVal Status Parse(@StdString String str, TensorSlice output);
  public static native @ByVal TensorSlice ParseOrDie(@StdString BytePointer str);
  public static native @ByVal TensorSlice ParseOrDie(@StdString String str);

  public native void Clear();

  // Accessors
  public native int dims();

  public native @Cast("tensorflow::int64") long start(int d);

  public native @Cast("tensorflow::int64") long length(int d);

  public native @Cast("tensorflow::int64") long end(int d);

  public native void set_start(int d, @Cast("tensorflow::int64") long x);

  public native void set_length(int d, @Cast("tensorflow::int64") long x);

  // If we have a full slice along dimension "d".
  public native @Cast("bool") boolean IsFullAt(int d);

  // If this is a full slice, i.e. IsFullAt(d) for every d.
  public native @Cast("bool") boolean IsFull();

  // Set the slice to be a full slice of "dim" dimensions
  public native void SetFullSlice(int dim);

  // Extend a slice to "dim" dimensions: all the added dimensions are full.
  // Requires: dim >= dims().
  public native void Extend(int dim);

  // Conversion of a TensorSlice to other formats
  public native void AsProto(TensorSliceProto proto);
  public native @StdString BytePointer DebugString();

  // Fill *indices and *sizes from *this (so that we can use the slice()
  // function in eigen tensor). We need a tensor shape in case some of the
  // slices are full slices.
  // We allow NDIMS to be greater than dims(), in which case we will pad the
  // higher dimensions with trivial dimensions.

  // Interaction with other TensorSlices.

  // Compute the intersection with another slice and if "result" is not
  // nullptr, store the results in *result; returns true if there is any real
  // intersection.
  public native @Cast("bool") boolean Intersect(@Const @ByRef TensorSlice other, TensorSlice result);
  // A short hand.
  public native @Cast("bool") boolean Overlaps(@Const @ByRef TensorSlice other);

  // Equals iff "*this" and "other" are logically equivalent.
  public native @Cast("bool") @Name("operator ==") boolean equals(@Const @ByRef TensorSlice other);
  public native @Cast("bool") @Name("operator !=") boolean notEquals(@Const @ByRef TensorSlice other);

  // Interaction with TensorShape.

  // Slices a shape and stores the result into *result_shape.
  // Requires that the shape and *this have the same rank.
  // For example, given a tensor shape of {3, 4, 5}, and a slice of
  // 1,2:-:0,2, the result shape is {2, 4, 2}.
  public native @ByVal Status SliceTensorShape(@Const @ByRef TensorShape shape,
                            TensorShape result_shape);

  // Given slice "sub" where "sub" is fully contained in *this,
  // (meaning that the intersection of "sub" and *this equals "sub"), computes
  // the "relative" slice of "sub" with respect to *this.
  //
  // In other words, if we use A>S to denote slicing a shape S with a slice A,
  // then the function is computing a slice X such that:
  //   X > (this > S) = sub > S
  // for any shape S.
  //
  // In general, along every dimension, the start of the relative slice is the
  // start of the "sub" slice minus the start of *this; the length of the
  // relative slice is the length of the "sub" slice.
  //
  // For example, say we have a shape of {3, 4, 5}, "this" is 0,2:-:1,2, and
  // "sub" is 1,1:2:2,1,2, then the related slice is 1,1:2,2:0,2.
  //
  // The caller needs to make sure that "sub" is indeed a sub-slice of *this;
  // otherwise the result is undefined.
  public native void ComputeRelative(@Const @ByRef TensorSlice sub, TensorSlice relative);

  // Updates the slice in such a way that it fully covers "other" slice.
  // Note, "other" slice should refer to the same tensor shape.
  // Example:
  //   given a slice [2:4, :, 3:] and "other" slice [:, 1:4, 2:4] the
  //   updated slice would be [:, :, 2:]. Here is why:
  //   dim 0: "2:4"  U  ":"    ->  ":"
  //   dim 1: ":"    U  "1-4"  ->  ":"
  //   dim 2: "3:"   U  "2:4"  ->  "2:"
  public native void UpdateToCover(@Const @ByRef TensorSlice other);

  // Returns true if the length field was specified in an Extent.
  public static native @Cast("bool") boolean HasExtentLength(@Cast("const tensorflow::TensorSliceProto::Extent*") @ByRef TensorSliceProto_Extent extent);

  // Returns the value of the length field in an Extent, or -1 if it
  // is not present.
  public static native @Cast("tensorflow::int64") long GetExtentLength(@Cast("const tensorflow::TensorSliceProto::Extent*") @ByRef TensorSliceProto_Extent extent);
}