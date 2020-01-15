// Targeted by JavaCPP version 1.5.3-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.opencv.opencv_highgui;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.openblas.global.openblas_nolapack.*;
import static org.bytedeco.openblas.global.openblas.*;
import org.bytedeco.opencv.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_core.*;
import org.bytedeco.opencv.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import org.bytedeco.opencv.opencv_videoio.*;
import static org.bytedeco.opencv.global.opencv_videoio.*;

import static org.bytedeco.opencv.global.opencv_highgui.*;


/** \brief Callback function defined to be called every frame. See cv::setOpenGlDrawCallback
@param userdata The optional parameter.
 */
@Properties(inherit = org.bytedeco.opencv.presets.opencv_highgui.class)
public class OpenGlDrawCallback extends FunctionPointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public    OpenGlDrawCallback(Pointer p) { super(p); }
    protected OpenGlDrawCallback() { allocate(); }
    private native void allocate();
    public native void call(Pointer userdata);
}