/*
 * Copyright (C) 2020 Samuel Audet
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
    inherit = arrow.class,
    value = {
        @Platform(
            include = {
                "parquet/api/io.h",
                "parquet/api/schema.h",
                "parquet/api/reader.h",
                "parquet/api/writer.h",
                "parquet/platform.h",
                "parquet/types.h",
                "parquet/deprecated_io.h",
                "parquet/exception.h",
                "parquet/schema.h",
                "parquet/column_reader.h",
                "parquet/column_scanner.h",
                "parquet/column_writer.h",
                "parquet/properties.h",
                "parquet/metadata.h",
                "parquet/file_reader.h",
                "parquet/file_writer.h",
                "parquet/printer.h",
                "parquet/statistics.h",
            },
            link = "parquet@.15",
            resource = {"include", "lib"}
        ),
    },
    target = "org.bytedeco.parquet",
    global = "org.bytedeco.arrow.global.parquet"
)
public class parquet implements InfoMapper {
    static { Loader.checkVersion("org.bytedeco", "parquet"); }

    public void map(InfoMap infoMap) {
        infoMap.put(new Info("PARQUET_EXPORT", "PARQUET_TEMPLATE_EXPORT", "PARQUET_NORETURN", "PARQUET_DISALLOW_COPY_AND_ASSIGN").cppTypes().annotations())
               .put(new Info("parquet::DEFAULT_BUFFER_SIZE").javaText("@Namespace(\"parquet\") @MemberGetter public static native @Cast(\"int64_t\") long DEFAULT_BUFFER_SIZE();"))
               .put(new Info("parquet::DEFAULT_USE_BUFFERED_STREAM").javaText("@Namespace(\"parquet\") @MemberGetter public static native @Cast(\"bool\") boolean DEFAULT_USE_BUFFERED_STREAM();"))

               .put(new Info("PARQUET_DEPRECATED").cppText("#define PARQUET_DEPRECATED(...) deprecated").cppTypes())
               .put(new Info("deprecated").annotations("@Deprecated"))

               .put(new Info("Compression::type").valueTypes("Compression.type", "@Cast(\"arrow::Compression::type\") int").pointerTypes("IntPointer", "IntBuffer", "int[]"))
               .put(new Info("arrow::io::OutputStream").pointerTypes("org.bytedeco.arrow.OutputStream"))
               .put(new Info("parquet::OutputStream").pointerTypes("org.bytedeco.parquet.OutputStream"))
               .put(new Info("parquet::Type").pointerTypes("org.bytedeco.parquet.Type"))
               .put(new Info("std::list<int>").pointerTypes("IntList").define())
               .put(new Info("std::shared_ptr<const parquet::LogicalType>").annotations("@Cast(\"const parquet::LogicalType*\") @SharedPtr").pointerTypes("LogicalType"))
               .put(new Info("std::shared_ptr<parquet::schema::Node>").annotations("@SharedPtr").pointerTypes("Node"))
               .put(new Info("std::vector<std::shared_ptr<parquet::schema::Node> >").pointerTypes("NodeVector").define())
        ;
    }
}
