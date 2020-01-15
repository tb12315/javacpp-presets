/*
 * Copyright (C) 2019-2020 Samuel Audet
 *
 * Licensed either under the Apache License, Version 2.0, or (at your option)
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation (subject to the "Classpath" exception),
 * either version 2, or any later version (collectively, the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     http://www.gnu.org/licenses/
 *     http://www.gnu.org/software/classpath/license.html
 *
 * or as provided in the LICENSE.txt file that accompanied this code.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bytedeco.arrow.presets;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.NoException;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

/**
 *
 * @author Samuel Audet
 */
@Properties(
    names = {"linux", "macosx", "windows"},
    value = {
        @Platform(
            compiler = "cpp11",
            define = {"UNIQUE_PTR_NAMESPACE std", "SHARED_PTR_NAMESPACE std"},
            include = {
                "arrow/api.h",
                "arrow/util/config.h",
                "arrow/util/checked_cast.h",
                "arrow/util/macros.h",
                "arrow/util/type_traits.h",
                "arrow/util/visibility.h",
                "arrow/util/compression.h",
                "arrow/util/functional.h",
                "arrow/util/iterator.h",
                "arrow/util/memory.h",
                "arrow/util/variant.h",
                "arrow/util/bit_util.h",
                "arrow/util/ubsan.h",
                "arrow/util/key_value_metadata.h",
                "arrow/util/string_builder.h",
//                "arrow/util/string_view.h",
//                "arrow/vendored/string_view.hpp",
//                "arrow/vendored/variant.hpp",
                "arrow/status.h",
                "arrow/memory_pool.h",
                "arrow/buffer.h",
                "arrow/buffer_builder.h",
                "arrow/compare.h",
                "arrow/result.h",
                "arrow/type_fwd.h",
                "arrow/type_traits.h",
                "arrow/util/basic_decimal.h",
                "arrow/util/decimal.h",
                "arrow/type.h",
                "arrow/scalar.h",
                "arrow/visitor.h",
                "arrow/array.h",
                "arrow/array/concatenate.h",
                "arrow/array/builder_base.h",
//                "arrow/array/builder_adaptive.h",
                "arrow/array/builder_binary.h",
                "arrow/array/builder_decimal.h",
                "arrow/array/builder_dict.h",
                "arrow/array/builder_nested.h",
                "arrow/array/builder_primitive.h",
                "arrow/array/builder_time.h",
                "arrow/array/builder_union.h",
                "arrow/builder.h",
                "arrow/extension_type.h",
                "arrow/pretty_print.h",
                "arrow/record_batch.h",
                "arrow/table.h",
                "arrow/table_builder.h",
                "arrow/tensor.h",
                "arrow/io/api.h",
                "arrow/io/interfaces.h",
                "arrow/io/concurrency.h",
                "arrow/io/file.h",
                "arrow/io/memory.h",
                "arrow/io/slow.h",
                "arrow/filesystem/api.h",
                "arrow/filesystem/filesystem.h",
                "arrow/filesystem/localfs.h",
                "arrow/filesystem/mockfs.h",
//                "arrow/filesystem/s3fs.h",
                "arrow/csv/api.h",
                "arrow/csv/options.h",
                "arrow/csv/reader.h",
                "arrow/compute/api.h",
                "arrow/compute/kernel.h",
                "arrow/compute/kernels/compare.h",
                "arrow/ipc/api.h",
                "arrow/ipc/dictionary.h",
                "arrow/ipc/feather.h",
                "arrow/ipc/json_simple.h",
                "arrow/ipc/options.h",
                "arrow/ipc/message.h",
                "arrow/ipc/reader.h",
                "arrow/ipc/writer.h",
            },
            link = "arrow@.15",
            resource = {"include", "lib"}
        ),
    },
    target = "org.bytedeco.arrow",
    global = "org.bytedeco.arrow.global.arrow"
)
public class arrow implements InfoMapper {
    static { Loader.checkVersion("org.bytedeco", "arrow"); }

    public void map(InfoMap infoMap) {
        infoMap.put(new Info().enumerate())
               .put(new Info("defined(__cplusplus)").define())
               .put(new Info("__cplusplus_cli", "ARROW_EXTRA_ERROR_CONTEXT").define(false))
               .put(new Info("ARROW_NORETURN", "ARROW_MUST_USE_RESULT", "NULLPTR", "ARROW_EXPORT", "ARROW_FORCE_INLINE",
                             "ARROW_MEMORY_POOL_DEFAULT", "ARROW_BYTE_SWAP64", "ARROW_BYTE_SWAP32").cppTypes().annotations())
               .put(new Info("ARROW_BITNESS", "ARROW_LITTLE_ENDIAN").translate(false))

               .put(new Info("ARROW_DEPRECATED").cppText("#define ARROW_DEPRECATED(...) deprecated").cppTypes())
               .put(new Info("deprecated").annotations("@Deprecated"))

               .put(new Info("arrow::internal::BitsetStack::reference").pointerTypes("BoolPointer"))
               .put(new Info("arrow::util::string_view").skip())
               .put(new Info("arrow::Buffer").pointerTypes("ArrowBuffer"))
               .put(new Info("arrow::EqualOptions::nans_equal", "arrow::EqualOptions::atol", "arrow::EqualOptions::diff_sink").annotations("@Function"))
               .put(new Info("arrow::detail::CTypeImpl", "arrow::detail::IntegerTypeImpl", "arrow::util::internal::non_null_filler", "arrow::TypeTraits").skip())

               .put(new Info("std::size_t").cast().valueTypes("long").pointerTypes("SizeTPointer"))
               .put(new Info("const char").valueTypes("byte").pointerTypes("String", "@Cast(\"const char*\") BytePointer"))
               .put(new Info("std::string").annotations("@StdString").valueTypes("String", "BytePointer").pointerTypes("@Cast({\"char*\", \"std::string*\"}) BytePointer"))
               .put(new Info("std::array<uint8_t,16>").pointerTypes("Byte16Array").define())
               .put(new Info("std::vector<bool>").pointerTypes("BoolVector").define())
               .put(new Info("std::vector<std::string>").pointerTypes("StringVector").define())
               .put(new Info("std::vector<std::pair<std::string,std::string> >").pointerTypes("StringStringPairVector").define())
               .put(new Info("std::unordered_map<std::string,std::string>").pointerTypes("StringStringMap").define())
               .put(new Info("arrow::Type::type").cast().valueTypes("int").pointerTypes("IntPointer", "IntBuffer", "int[]"))

               .put(new Info("arrow::NumericArray<arrow::Int8Type>::value_type",
                             "arrow::NumericArray<arrow::UInt8Type>::value_type",
                             "arrow::NumericBuilder<arrow::Int8Type>::value_type",
                             "arrow::NumericBuilder<arrow::UInt8Type>::value_type").cast().valueTypes("byte").pointerTypes("BytePointer", "ByteBuffer", "byte[]"))
               .put(new Info("arrow::NumericBuilder<arrow::Int8Type>::ArrayType",
                             "arrow::NumericBuilder<arrow::UInt8Type>::ArrayType").cast().valueTypes("BytePointer", "ByteBuffer", "byte[]"))
               .put(new Info("arrow::NumericArray<arrow::Int16Type>::value_type",
                             "arrow::NumericArray<arrow::UInt16Type>::value_type",
                             "arrow::NumericBuilder<arrow::Int16Type>::value_type",
                             "arrow::NumericBuilder<arrow::UInt16Type>::value_type").cast().valueTypes("short").pointerTypes("ShortPointer", "ShortBuffer", "short[]"))
               .put(new Info("arrow::NumericBuilder<arrow::Int16Type>::ArrayType",
                             "arrow::NumericBuilder<arrow::UInt16Type>::ArrayType").cast().valueTypes("ShortPointer", "ShortBuffer", "short[]"))
               .put(new Info("arrow::NumericArray<arrow::Int32Type>::value_type",
                             "arrow::NumericArray<arrow::UInt32Type>::value_type",
                             "arrow::NumericArray<arrow::Date32Type>::value_type",
                             "arrow::NumericArray<arrow::Time32Type>::value_type",
                             "arrow::NumericArray<arrow::MonthIntervalType>::value_type",
                             "arrow::BaseListArray<arrow::ListType>::offset_type",
                             "arrow::BaseBinaryArray<arrow::BinaryType>::offset_type",
                             "arrow::BaseListBuilder<arrow::ListType>::offset_type",
                             "arrow::BaseBinaryBuilder<arrow::BinaryType>::offset_type",
                             "arrow::NumericBuilder<arrow::Int32Type>::value_type",
                             "arrow::NumericBuilder<arrow::UInt32Type>::value_type").cast().valueTypes("int").pointerTypes("IntPointer", "IntBuffer", "int[]"))
               .put(new Info("arrow::NumericBuilder<arrow::Int32Type>::ArrayType",
                             "arrow::NumericBuilder<arrow::UInt32Type>::ArrayType").cast().valueTypes("IntPointer", "IntBuffer", "int[]"))
               .put(new Info("arrow::NumericArray<arrow::Int64Type>::value_type",
                             "arrow::NumericArray<arrow::UInt64Type>::value_type",
                             "arrow::NumericArray<arrow::Date64Type>::value_type",
                             "arrow::NumericArray<arrow::Time64Type>::value_type",
                             "arrow::NumericArray<arrow::DayTimeIntervalType>::value_type",
                             "arrow::NumericArray<arrow::DurationType>::value_type",
                             "arrow::NumericArray<arrow::TimestampType>::value_type",
                             "arrow::BaseListArray<arrow::LargeListType>::offset_type",
                             "arrow::BaseBinaryArray<arrow::LargeBinaryType>::offset_type",
                             "arrow::BaseListBuilder<arrow::LargeListType>::offset_type",
                             "arrow::BaseBinaryBuilder<arrow::LargeBinaryType>::offset_type",
                             "arrow::NumericBuilder<arrow::Int64Type>::value_type",
                             "arrow::NumericBuilder<arrow::UInt64Type>::value_type").cast().valueTypes("long").pointerTypes("LongPointer", "LongBuffer", "long[]"))
               .put(new Info("arrow::NumericBuilder<arrow::Int64Type>::ArrayType",
                             "arrow::NumericBuilder<arrow::UInt64Type>::ArrayType").cast().valueTypes("LongPointer", "LongBuffer", "long[]"))
               .put(new Info("arrow::NumericArray<arrow::HalfFloatType>::value_type", 
                             "arrow::NumericBuilder<arrow::HalfFloatType>::value_type",
                             "arrow::NumericBuilder<arrow::HalfFloatType>::value_type").cast().valueTypes("short").pointerTypes("ShortPointer", "ShortBuffer", "short[]"))
               .put(new Info("arrow::NumericBuilder<arrow::HalfFloatType>::ArrayType",
                             "arrow::NumericBuilder<arrow::HalfFloatType>::ArrayType").cast().valueTypes("ShortPointer", "ShortBuffer", "short[]"))
               .put(new Info("arrow::NumericArray<arrow::FloatType>::value_type",
                             "arrow::NumericBuilder<arrow::FloatType>::value_type",
                             "arrow::NumericBuilder<arrow::FloatType>::value_type").cast().valueTypes("float").pointerTypes("FloatPointer", "FloatBuffer", "float[]"))
               .put(new Info("arrow::NumericBuilder<arrow::FloatType>::ArrayType",
                             "arrow::NumericBuilder<arrow::FloatType>::ArrayType").cast().valueTypes("FloatPointer", "FloatBuffer", "float[]"))
               .put(new Info("arrow::NumericArray<arrow::DoubleType>::value_type",
                             "arrow::NumericBuilder<arrow::DoubleType>::value_type",
                             "arrow::NumericBuilder<arrow::DoubleType>::value_type").cast().valueTypes("double").pointerTypes("DoublePointer", "DoubleBuffer", "double[]"))
               .put(new Info("arrow::NumericBuilder<arrow::DoubleType>::ArrayType",
                             "arrow::NumericBuilder<arrow::DoubleType>::ArrayType").cast().valueTypes("DoublePointer", "DoubleBuffer", "double[]"))

               .put(new Info("arrow::DayTimeIntervalArray::TypeClass::DayMilliseconds").pointerTypes("DayTimeIntervalType.DayMilliseconds"))

               .put(new Info("arrow::BaseListType", "arrow::BaseBinaryType", "arrow::BaseListScalar", "arrow::NestedType", "arrow::NumberType",
                             "arrow::PrimitiveCType", "arrow::internal::PrimitiveScalar", "arrow::Scalar", "arrow::StructScalar", "arrow::TemporalType",
                             "arrow::compute::CompareFunction").purify())

               .put(new Info("arrow::BaseBinaryScalar<arrow::BinaryType>").pointerTypes("BaseBinaryScalar").define())
               .put(new Info("arrow::BaseBinaryScalar<arrow::LargeBinaryType>").pointerTypes("BaseLargeBinaryScalar").define())
               .put(new Info("arrow::NumericScalar<arrow::Date32Type>").pointerTypes("BaseDate32Scalar").define().skipDefaults())
               .put(new Info("arrow::NumericScalar<arrow::Date64Type>").pointerTypes("BaseDate64Scalar").define().skipDefaults())

               .put(new Info("arrow::NumericArray<arrow::Int8Type>").pointerTypes("Int8Array").define())
               .put(new Info("arrow::NumericArray<arrow::Int16Type>").pointerTypes("Int16Array").define())
               .put(new Info("arrow::NumericArray<arrow::Int32Type>").pointerTypes("Int32Array").define())
               .put(new Info("arrow::NumericArray<arrow::Int64Type>").pointerTypes("Int64Array").define())
               .put(new Info("arrow::NumericArray<arrow::UInt8Type>").pointerTypes("UInt8Array").define())
               .put(new Info("arrow::NumericArray<arrow::UInt16Type>").pointerTypes("UInt16Array").define())
               .put(new Info("arrow::NumericArray<arrow::UInt32Type>").pointerTypes("UInt32Array").define())
               .put(new Info("arrow::NumericArray<arrow::UInt64Type>").pointerTypes("UInt64Array").define())
               .put(new Info("arrow::NumericArray<arrow::HalfFloatType>").pointerTypes("HalfFloatArray").define())
               .put(new Info("arrow::NumericArray<arrow::FloatType>").pointerTypes("FloatArray").define())
               .put(new Info("arrow::NumericArray<arrow::DoubleType>").pointerTypes("DoubleArray").define())
               .put(new Info("arrow::NumericArray<arrow::Date64Type>").pointerTypes("Date64Array").define())
               .put(new Info("arrow::NumericArray<arrow::Date32Type>").pointerTypes("Date32Array").define())
               .put(new Info("arrow::NumericArray<arrow::Time32Type>").pointerTypes("Time32Array").define())
               .put(new Info("arrow::NumericArray<arrow::Time64Type>").pointerTypes("Time64Array").define())
               .put(new Info("arrow::NumericArray<arrow::TimestampType>").pointerTypes("TimestampArray").define())
               .put(new Info("arrow::NumericArray<arrow::MonthIntervalType>").pointerTypes("MonthIntervalArray").define())
//               .put(new Info("arrow::NumericArray<arrow::DayTimeInterval>").pointerTypes("DayTimeIntervalArray").define())
               .put(new Info("arrow::NumericArray<arrow::DurationType>").pointerTypes("DurationArray").define())
//               .put(new Info("arrow::NumericArray<arrow::ExtensionType>").pointerTypes("ExtensionArray").define())

               .put(new Info("arrow::ArrayData::GetValues<jbyte>").javaNames("GetValuesByte"))
               .put(new Info("arrow::ArrayData::GetValues<jshort>").javaNames("GetValuesShort"))
               .put(new Info("arrow::ArrayData::GetValues<jint>").javaNames("GetValuesInt"))
               .put(new Info("arrow::ArrayData::GetValues<float>").javaNames("GetValuesFloat"))
               .put(new Info("arrow::ArrayData::GetValues<double>").javaNames("GetValuesDouble"))
               .put(new Info("arrow::ArrayData::GetValues<jboolean>").javaNames("GetValuesBoolean"))
               .put(new Info("arrow::ArrayData::GetValues<jchar>").javaNames("GetValuesChar"))

               .put(new Info("std::shared_ptr<arrow::Scalar>").annotations("@SharedPtr").valueTypes("@Cast({\"\", \"std::shared_ptr<arrow::Scalar>\"}) Scalar").pointerTypes("Scalar"))
               .put(new Info("std::shared_ptr<arrow::Field>").annotations("@SharedPtr").pointerTypes("Field"))
               .put(new Info("std::shared_ptr<arrow::Array>").annotations("@SharedPtr").valueTypes("@Cast({\"\", \"std::shared_ptr<arrow::Array>\"}) Array").pointerTypes("Array"))
               .put(new Info("std::shared_ptr<arrow::ArrayData>").annotations("@SharedPtr").valueTypes("@Cast({\"\", \"std::shared_ptr<arrow::ArrayData>\"}) ArrayData").pointerTypes("ArrayData"))
               .put(new Info("std::shared_ptr<arrow::Buffer>").annotations("@SharedPtr").pointerTypes("ArrowBuffer"))
               .put(new Info("std::shared_ptr<arrow::ArrayBuilder>").annotations("@SharedPtr").pointerTypes("ArrayBuilder"))
               .put(new Info("std::shared_ptr<arrow::RecordBatch>").annotations("@SharedPtr").valueTypes("@Cast({\"\", \"std::shared_ptr<arrow::RecordBatch>\"}) RecordBatch").pointerTypes("RecordBatch"))
               .put(new Info("std::shared_ptr<arrow::ChunkedArray>").annotations("@SharedPtr").valueTypes("@Cast({\"\", \"std::shared_ptr<arrow::ChunkedArray>\"}) ChunkedArray").pointerTypes("ChunkedArray"))
               .put(new Info("std::shared_ptr<arrow::Table>").annotations("@SharedPtr").valueTypes("@Cast({\"\", \"std::shared_ptr<arrow::Table>\"}) Table").pointerTypes("Table"))
               .put(new Info("std::shared_ptr<arrow::ListArray>").annotations("@SharedPtr").pointerTypes("ListArray"))
               .put(new Info("std::shared_ptr<arrow::BinaryArray>").annotations("@SharedPtr").pointerTypes("BinaryArray"))
               .put(new Info("std::shared_ptr<arrow::StructArray>").annotations("@SharedPtr").pointerTypes("StructArray"))
               .put(new Info("std::shared_ptr<arrow::DataType>").annotations("@SharedPtr").valueTypes("@Cast({\"\", \"std::shared_ptr<arrow::DataType>\"}) DataType")
                                                                                          .pointerTypes("@Cast({\"\", \"std::shared_ptr<arrow::DataType>*\"}) DataType"))
               .put(new Info("std::shared_ptr<const arrow::KeyValueMetadata>").annotations("@Cast(\"const arrow::KeyValueMetadata*\") @SharedPtr").pointerTypes("KeyValueMetadata"))

               .put(new Info("std::vector<std::shared_ptr<arrow::Scalar> >").pointerTypes("ScalarVector").define())
               .put(new Info("std::vector<std::shared_ptr<arrow::Field> >").pointerTypes("FieldVector").define())
               .put(new Info("std::vector<std::shared_ptr<arrow::Array> >").pointerTypes("ArrayVector").define())
               .put(new Info("std::vector<std::shared_ptr<arrow::ArrayData> >").pointerTypes("ArrayDataVector").define())
               .put(new Info("std::vector<std::shared_ptr<arrow::Buffer> >").pointerTypes("ArrowBufferVector").define())
               .put(new Info("std::vector<std::shared_ptr<arrow::ArrayBuilder> >").pointerTypes("ArrowBuilderVector").define())
               .put(new Info("std::vector<std::shared_ptr<arrow::RecordBatch> >").pointerTypes("RecordBatchVector").define())
               .put(new Info("std::vector<std::shared_ptr<arrow::ChunkedArray> >").pointerTypes("ChunkedArrayVector").define())
               .put(new Info("std::vector<std::shared_ptr<arrow::Table> >").pointerTypes("TableVector").define())
               .put(new Info("std::vector<std::vector<std::shared_ptr<arrow::Array> > >", "std::vector<arrow::ArrayVector>").pointerTypes("ArrayVectorVector").define())
               .put(new Info("std::vector<arrow::compute::Datum>").pointerTypes("DatumVector").define())
               .put(new Info("arrow::BaseListArray<arrow::ListType>").pointerTypes("BaseListArray").define())
               .put(new Info("arrow::BaseBinaryArray<arrow::BinaryType>").pointerTypes("BaseBinaryArray").define())
               .put(new Info("arrow::BaseListArray<arrow::LargeListType>").pointerTypes("BaseLargeListArray").define())
               .put(new Info("arrow::BaseBinaryArray<arrow::LargeBinaryType>").pointerTypes("BaseLargeBinaryArray").define())
               .put(new Info("arrow::Result<std::shared_ptr<arrow::DataType> >").pointerTypes("DataTypeResult").define())
               .put(new Info("arrow::Result<std::shared_ptr<arrow::ListArray> >").pointerTypes("ListArrayResult").define())
               .put(new Info("arrow::Result<std::shared_ptr<arrow::BinaryArray> >").pointerTypes("BinaryArrayResult").define())
               .put(new Info("arrow::Result<std::shared_ptr<arrow::StructArray> >").pointerTypes("StructArrayResult").define())
               .put(new Info("arrow::Result<arrow::compute::Datum>").pointerTypes("DatumResult").define())
               .put(new Info("arrow::Result<std::shared_ptr<arrow::ipc::RecordBatchWriter> >").pointerTypes("RecordBatchWriterSharedResult").define())
               .put(new Info("arrow::Result<std::unique_ptr<arrow::ipc::RecordBatchWriter> >").pointerTypes("RecordBatchWriterUniqueResult").define())
               .put(new Info("arrow::Result<std::unique_ptr<arrow::ipc::RecordBatchWriter> >(const arrow::Result<std::unique_ptr<arrow::ipc::RecordBatchWriter> >&)",
                             "arrow::Result<std::unique_ptr<arrow::ipc::RecordBatchWriter> >::operator =").skip())
               .put(new Info("std::unordered_map<std::string,std::shared_ptr<arrow::DataType> >").pointerTypes("StringDataTypeMap").define())

               .put(new Info("arrow::BaseListBuilder<arrow::ListType>").pointerTypes("BaseListBuilder").define().purify())
               .put(new Info("arrow::BaseListBuilder<arrow::LargeListType>").pointerTypes("BaseLargeListBuilder").define().purify())
               .put(new Info("arrow::BaseBinaryBuilder<arrow::BinaryType>").pointerTypes("BaseBinaryBuilder").define().purify())
               .put(new Info("arrow::BaseBinaryBuilder<arrow::LargeBinaryType>").pointerTypes("BaseLargeBinaryBuilder").define().purify())
               .put(new Info("arrow::internal::BinaryDictionaryBuilderImpl<arrow::BinaryType>",
                             "arrow::internal::BinaryDictionaryBuilderImpl<arrow::StringType>",
                             "arrow::internal::BinaryDictionary32BuilderImpl<arrow::BinaryType>",
                             "arrow::internal::BinaryDictionary32BuilderImpl<arrow::StringType>", "arrow::fs::TimePoint").cast().pointerTypes("Pointer"))
               .put(new Info("arrow::internal::DictionaryScalar", "arrow::NullBuilder::Append(std::nullptr_t)", "arrow::compute::Datum::value",
                             "arrow::ipc::DictionaryMemo(arrow::ipc::DictionaryMemo)", "arrow::ipc::DictionaryMemo::operator =", "arrow::ipc::ReadSparseTensor").skip())
               .put(new Info("arrow::compute::Datum::type").enumerate(false).valueTypes("int"))

               .put(new Info("arrow::NumericBuilder<arrow::Int8Type>").pointerTypes("Int8Builder").define())
               .put(new Info("arrow::NumericBuilder<arrow::Int16Type>").pointerTypes("Int16Builder").define())
               .put(new Info("arrow::NumericBuilder<arrow::Int32Type>").pointerTypes("Int32Builder").define())
               .put(new Info("arrow::NumericBuilder<arrow::Int64Type>").pointerTypes("Int64Builder").define())
               .put(new Info("arrow::NumericBuilder<arrow::UInt8Type>").pointerTypes("UInt8Builder").define())
               .put(new Info("arrow::NumericBuilder<arrow::UInt16Type>").pointerTypes("UInt16Builder").define())
               .put(new Info("arrow::NumericBuilder<arrow::UInt32Type>").pointerTypes("UInt32Builder").define())
               .put(new Info("arrow::NumericBuilder<arrow::UInt64Type>").pointerTypes("UInt64Builder").define())
               .put(new Info("arrow::NumericBuilder<arrow::HalfFloatType>").pointerTypes("HalfFloatBuilder").define())
               .put(new Info("arrow::NumericBuilder<arrow::FloatType>").pointerTypes("FloatBuilder").define())
               .put(new Info("arrow::NumericBuilder<arrow::DoubleType>").pointerTypes("DoubleBuilder").define())

               .put(new Info("arrow::io::FileSystem").pointerTypes("IOFileSystem"))
               .put(new Info("arrow::io::internal::SharedLockGuard<arrow::io::internal::SharedExclusiveChecker>").pointerTypes("SharedExclusiveCheckerSharedLockGuard").define())
               .put(new Info("arrow::io::internal::ExclusiveLockGuard<arrow::io::internal::SharedExclusiveChecker>").pointerTypes("SharedExclusiveCheckerExclusiveLockGuard").define())
               .put(new Info("arrow::io::internal::RandomAccessFileConcurrencyWrapper<arrow::io::ReadableFile>",
                             "arrow::io::internal::RandomAccessFileConcurrencyWrapper<ReadableFile>").pointerTypes("ReadableFileRandomAccessFileConcurrencyWrapper").define().purify())
               .put(new Info("arrow::io::internal::RandomAccessFileConcurrencyWrapper<arrow::io::BufferReader>",
                             "arrow::io::internal::RandomAccessFileConcurrencyWrapper<BufferReader>").pointerTypes("BufferReaderRandomAccessFileConcurrencyWrapper").define().purify())
               .put(new Info("arrow::io::SlowInputStreamBase<arrow::io::InputStream>").pointerTypes("InputStreamSlowInputStreamBase").define().purify())
               .put(new Info("arrow::io::SlowInputStreamBase<arrow::io::RandomAccessFile>").pointerTypes("RandomAccessFileSlowInputStreamBase").define().purify())

               .put(new Info("arrow::Buffer::FromString(std::string)").javaText(
                       "public static native @SharedPtr @ByVal ArrowBuffer FromString(@Cast({\"\", \"std::string&&\"}) @StdString BytePointer data);\n"
                     + "public static native @SharedPtr @ByVal ArrowBuffer FromString(@Cast({\"\", \"std::string&&\"}) @StdString String data);\n"))
               .put(new Info("arrow::Decimal128(const std::string&)").javaText(
                       "public Decimal128(@StdString String value) { super((Pointer)null); allocate(value); }\n"
                     + "private native void allocate(@StdString String value);\n"))
        ;
    }
}
