// Targeted by JavaCPP version 1.5.3-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.arrow.global;

import org.bytedeco.plasma.*;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import org.bytedeco.arrow.*;
import static org.bytedeco.arrow.global.arrow.*;

public class plasma extends org.bytedeco.arrow.presets.plasma {
    static { Loader.load(); }

// Parsed from plasma/compat.h

// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

// #ifndef PLASMA_COMPAT_H
// #define PLASMA_COMPAT_H

// Workaround for multithreading on XCode 9, see
// https://issues.apache.org/jira/browse/ARROW-1622 and
// https://github.com/tensorflow/tensorflow/issues/13220#issuecomment-331579775
// This should be a short-term fix until the problem is fixed upstream.
// #ifdef __APPLE__
// #endif /* __APPLE__ */

// #endif  // PLASMA_COMPAT_H


// Parsed from plasma/common.h

// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

// #ifndef PLASMA_COMMON_H
// #define PLASMA_COMMON_H

// #include <stddef.h>

// #include <cstring>
// #include <memory>
// #include <string>
// TODO(pcm): Convert getopt and sscanf in the store to use more idiomatic C++
// and get rid of the next three lines:
// #ifndef __STDC_FORMAT_MACROS
// #define __STDC_FORMAT_MACROS
// #endif
// #include <unordered_map>

// #include "plasma/compat.h"

// #include "arrow/status.h"
// #ifdef PLASMA_CUDA
// #endif

@Namespace("plasma") public enum ObjectLocation { Local(0), Remote(1), Nonexistent(2);

    public final int value;
    private ObjectLocation(int v) { this.value = v; }
    private ObjectLocation(ObjectLocation e) { this.value = e.value; }
    public ObjectLocation intern() { for (ObjectLocation e : values()) if (e.value == value) return e; return this; }
    @Override public String toString() { return intern().name(); }
}

@Namespace("plasma") public enum PlasmaErrorCode {
  PlasmaObjectExists((byte)1),
  PlasmaObjectNonexistent((byte)2),
  PlasmaStoreFull((byte)3),
  PlasmaObjectAlreadySealed((byte)4);

    public final byte value;
    private PlasmaErrorCode(byte v) { this.value = v; }
    private PlasmaErrorCode(PlasmaErrorCode e) { this.value = e.value; }
    public PlasmaErrorCode intern() { for (PlasmaErrorCode e : values()) if (e.value == value) return e; return this; }
    @Override public String toString() { return intern().name(); }
}

@Namespace("plasma") public static native @ByVal Status MakePlasmaError(PlasmaErrorCode code, @StdString String message);
@Namespace("plasma") public static native @ByVal Status MakePlasmaError(@Cast("plasma::PlasmaErrorCode") byte code, @StdString BytePointer message);
/** Return true iff the status indicates an already existing Plasma object. */
@Namespace("plasma") public static native @Cast("bool") boolean IsPlasmaObjectExists(@Const @ByRef Status status);
/** Return true iff the status indicates a non-existent Plasma object. */
@Namespace("plasma") public static native @Cast("bool") boolean IsPlasmaObjectNonexistent(@Const @ByRef Status status);
/** Return true iff the status indicates an already sealed Plasma object. */
@Namespace("plasma") public static native @Cast("bool") boolean IsPlasmaObjectAlreadySealed(@Const @ByRef Status status);
/** Return true iff the status indicates the Plasma store reached its capacity limit. */
@Namespace("plasma") public static native @Cast("bool") boolean IsPlasmaStoreFull(@Const @ByRef Status status);

@Namespace("plasma") @MemberGetter public static native @Cast("const int64_t") long kUniqueIDSize();
// Targeting ../../plasma/UniqueID.java



/** Size of object hash digests. */
@Namespace("plasma") @MemberGetter public static native @Cast("const int64_t") long kDigestSize();

@Namespace("plasma") public enum ObjectState {
  /** Object was created but not sealed in the local Plasma Store. */
  PLASMA_CREATED(1),
  /** Object is sealed and stored in the local Plasma Store. */
  PLASMA_SEALED(2),
  /** Object is evicted to external store. */
  PLASMA_EVICTED(3);

    public final int value;
    private ObjectState(int v) { this.value = v; }
    private ObjectState(ObjectState e) { this.value = e.value; }
    public ObjectState intern() { for (ObjectState e : values()) if (e.value == value) return e; return this; }
    @Override public String toString() { return intern().name(); }
}
// Targeting ../../plasma/CudaIpcPlaceholder.java




// Targeting ../../plasma/ObjectTableEntry.java



/** Mapping from ObjectIDs to information about the object. */
// Targeting ../../plasma/PlasmaStoreInfo.java


@Namespace("plasma") @MemberGetter public static native @Const PlasmaStoreInfo plasma_config();

// Targeting ../../plasma/UniqueIDHash.java


  // namespace std

// #endif  // PLASMA_COMMON_H


// Parsed from plasma/client.h

// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

// #ifndef PLASMA_CLIENT_H
// #define PLASMA_CLIENT_H

// #include <functional>
// #include <memory>
// #include <string>
// #include <vector>

// #include "arrow/buffer.h"
// #include "arrow/status.h"
// #include "arrow/util/macros.h"
// #include "arrow/util/visibility.h"
// #include "plasma/common.h"
// Targeting ../../plasma/ObjectBuffer.java


// Targeting ../../plasma/PlasmaClient.java



  // namespace plasma

// #endif  // PLASMA_CLIENT_H


// Parsed from plasma/events.h

// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

// #ifndef PLASMA_EVENTS
// #define PLASMA_EVENTS

// #include <functional>
// #include <memory>
// #include <unordered_map>
// Targeting ../../plasma/aeEventLoop.java



// The constants below are defined using hardcoded values taken from ae.h so
// that ae.h does not need to be included in this file.

/** Constant specifying that the timer is done and it will be removed. */
@Namespace("plasma") @MemberGetter public static native int kEventLoopTimerDone();  // AE_NOMORE

/** A successful status. */
@Namespace("plasma") @MemberGetter public static native int kEventLoopOk();  // AE_OK

/** Read event on the file descriptor. */
@Namespace("plasma") @MemberGetter public static native int kEventLoopRead();  // AE_READABLE

/** Write event on the file descriptor. */
@Namespace("plasma") @MemberGetter public static native int kEventLoopWrite();  // AE_WRITABLE
// Targeting ../../plasma/EventLoop.java



  // namespace plasma

// #endif  // PLASMA_EVENTS


// Parsed from plasma/test_util.h

// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

// #ifndef PLASMA_TEST_UTIL_H
// #define PLASMA_TEST_UTIL_H

// #include <algorithm>
// #include <limits>
// #include <random>

// #include "plasma/common.h"

@Namespace("plasma") public static native @ByVal @Cast("plasma::ObjectID*") UniqueID random_object_id();

// #define PLASMA_CHECK_SYSTEM(expr)
//   do {
//     int status__ = (expr);
//     EXPECT_TRUE(WIFEXITED(status__));
//     EXPECT_EQ(WEXITSTATUS(status__), 0);
//   } while (false);

  // namespace plasma

// #endif  // PLASMA_TEST_UTIL_H


}
