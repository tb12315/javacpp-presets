// Targeted by JavaCPP version 1.5.3-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.opencv.opencv_stitching;

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
import org.bytedeco.opencv.opencv_highgui.*;
import static org.bytedeco.opencv.global.opencv_highgui.*;
import org.bytedeco.opencv.opencv_flann.*;
import static org.bytedeco.opencv.global.opencv_flann.*;
import org.bytedeco.opencv.opencv_features2d.*;
import static org.bytedeco.opencv.global.opencv_features2d.*;
import org.bytedeco.opencv.opencv_calib3d.*;
import static org.bytedeco.opencv.global.opencv_calib3d.*;
import org.bytedeco.opencv.opencv_objdetect.*;
import static org.bytedeco.opencv.global.opencv_objdetect.*;
import org.bytedeco.opencv.opencv_video.*;
import static org.bytedeco.opencv.global.opencv_video.*;
import org.bytedeco.opencv.opencv_ml.*;
import static org.bytedeco.opencv.global.opencv_ml.*;
import org.bytedeco.opencv.opencv_shape.*;
import static org.bytedeco.opencv.global.opencv_shape.*;
import org.bytedeco.opencv.opencv_xfeatures2d.*;
import static org.bytedeco.opencv.global.opencv_xfeatures2d.*;

import static org.bytedeco.opencv.global.opencv_stitching.*;


/** \addtogroup stitching
 *  \{
<p>
/** \example samples/cpp/stitching.cpp
A basic example on image stitching
*/

/** \example samples/python/stitching.py
A basic example on image stitching in Python.
*/

/** \example samples/cpp/stitching_detailed.cpp
A detailed example on image stitching
*/

/** \brief High level image stitcher.
<p>
It's possible to use this class without being aware of the entire stitching pipeline. However, to
be able to achieve higher stitching stability and quality of the final images at least being
familiar with the theory is recommended.
<p>
\note
-   A basic example on image stitching can be found at
    opencv_source_code/samples/cpp/stitching.cpp
-   A basic example on image stitching in Python can be found at
    opencv_source_code/samples/python/stitching.py
-   A detailed example on image stitching can be found at
    opencv_source_code/samples/cpp/stitching_detailed.cpp
 */
@Namespace("cv") @Properties(inherit = org.bytedeco.opencv.presets.opencv_stitching.class)
public class Stitcher extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public Stitcher() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public Stitcher(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public Stitcher(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public Stitcher position(long position) {
        return (Stitcher)super.position(position);
    }

    /**
     * When setting a resolution for stitching, this values is a placeholder
     * for preserving the original resolution.
     */
// #if __cplusplus >= 201103L || (defined(_MSC_VER) && _MSC_VER >= 1900/*MSVS 2015*/)
    @MemberGetter public static native double ORIG_RESOL();
    public static final double ORIG_RESOL = ORIG_RESOL();
// #else
    // support MSVS 2013 // Initialized in stitcher.cpp
// #endif

    /** enum cv::Stitcher::Status */
    public static final int
        OK = 0,
        ERR_NEED_MORE_IMGS = 1,
        ERR_HOMOGRAPHY_EST_FAIL = 2,
        ERR_CAMERA_PARAMS_ADJUST_FAIL = 3;

    /** enum cv::Stitcher::Mode */
    public static final int
        /** Mode for creating photo panoramas. Expects images under perspective
        transformation and projects resulting pano to sphere.
        <p>
        @see detail::BestOf2NearestMatcher SphericalWarper
        */
        PANORAMA = 0,
        /** Mode for composing scans. Expects images under affine transformation does
        not compensate exposure by default.
        <p>
        @see detail::AffineBestOf2NearestMatcher AffineWarper
        */
        SCANS = 1;

    /** \brief Creates a Stitcher configured in one of the stitching modes.
    <p>
    @param mode Scenario for stitcher operation. This is usually determined by source of images
    to stitch and their transformation. Default parameters will be chosen for operation in given
    scenario.
    @return Stitcher class instance.
     */
    public static native @Ptr Stitcher create(@Cast("cv::Stitcher::Mode") int mode/*=cv::Stitcher::PANORAMA*/);
    public static native @Ptr Stitcher create();

    public native double registrationResol();
    public native void setRegistrationResol(double resol_mpx);

    public native double seamEstimationResol();
    public native void setSeamEstimationResol(double resol_mpx);

    public native double compositingResol();
    public native void setCompositingResol(double resol_mpx);

    public native double panoConfidenceThresh();
    public native void setPanoConfidenceThresh(double conf_thresh);

    public native @Cast("bool") boolean waveCorrection();
    public native void setWaveCorrection(@Cast("bool") boolean flag);

    public native @Cast("cv::InterpolationFlags") int interpolationFlags();
    public native void setInterpolationFlags(@Cast("cv::InterpolationFlags") int interp_flags);

    public native @Cast("cv::detail::WaveCorrectKind") int waveCorrectKind();
    public native void setWaveCorrectKind(@Cast("cv::detail::WaveCorrectKind") int kind);

    public native @Ptr Feature2D featuresFinder();
    public native void setFeaturesFinder(@Ptr Feature2D features_finder);

    public native @Ptr FeaturesMatcher featuresMatcher();
    public native void setFeaturesMatcher(@Ptr FeaturesMatcher features_matcher);

    public native @Const @ByRef UMat matchingMask();
    public native void setMatchingMask(@Const @ByRef UMat mask);

    public native @Ptr BundleAdjusterBase bundleAdjuster();
    public native void setBundleAdjuster(@Ptr BundleAdjusterBase bundle_adjuster);

    public native @Ptr Estimator estimator();
    public native void setEstimator(@Ptr Estimator estimator);

    public native @Ptr WarperCreator warper();
    public native void setWarper(@Ptr WarperCreator creator);

    public native @Ptr ExposureCompensator exposureCompensator();
    public native void setExposureCompensator(@Ptr ExposureCompensator exposure_comp);

    public native @Ptr SeamFinder seamFinder();
    public native void setSeamFinder(@Ptr SeamFinder seam_finder);

    public native @Ptr Blender blender();
    public native void setBlender(@Ptr Blender b);

    /** \brief These functions try to match the given images and to estimate rotations of each camera.
    <p>
    \note Use the functions only if you're aware of the stitching pipeline, otherwise use
    Stitcher::stitch.
    <p>
    @param images Input images.
    @param masks Masks for each input image specifying where to look for keypoints (optional).
    @return Status code.
     */
    public native @Cast("cv::Stitcher::Status") int estimateTransform(@ByVal MatVector images, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector masks);
    public native @Cast("cv::Stitcher::Status") int estimateTransform(@ByVal MatVector images);
    public native @Cast("cv::Stitcher::Status") int estimateTransform(@ByVal UMatVector images, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector masks);
    public native @Cast("cv::Stitcher::Status") int estimateTransform(@ByVal UMatVector images);
    public native @Cast("cv::Stitcher::Status") int estimateTransform(@ByVal GpuMatVector images, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector masks);
    public native @Cast("cv::Stitcher::Status") int estimateTransform(@ByVal GpuMatVector images);

    /** \overload */
    public native @Cast("cv::Stitcher::Status") int composePanorama(@ByVal Mat pano);
    public native @Cast("cv::Stitcher::Status") int composePanorama(@ByVal UMat pano);
    public native @Cast("cv::Stitcher::Status") int composePanorama(@ByVal GpuMat pano);
    /** \brief These functions try to compose the given images (or images stored internally from the other function
    calls) into the final pano under the assumption that the image transformations were estimated
    before.
    <p>
    \note Use the functions only if you're aware of the stitching pipeline, otherwise use
    Stitcher::stitch.
    <p>
    @param images Input images.
    @param pano Final pano.
    @return Status code.
     */
    public native @Cast("cv::Stitcher::Status") int composePanorama(@ByVal MatVector images, @ByVal Mat pano);
    public native @Cast("cv::Stitcher::Status") int composePanorama(@ByVal UMatVector images, @ByVal Mat pano);
    public native @Cast("cv::Stitcher::Status") int composePanorama(@ByVal GpuMatVector images, @ByVal Mat pano);
    public native @Cast("cv::Stitcher::Status") int composePanorama(@ByVal MatVector images, @ByVal UMat pano);
    public native @Cast("cv::Stitcher::Status") int composePanorama(@ByVal UMatVector images, @ByVal UMat pano);
    public native @Cast("cv::Stitcher::Status") int composePanorama(@ByVal GpuMatVector images, @ByVal UMat pano);
    public native @Cast("cv::Stitcher::Status") int composePanorama(@ByVal MatVector images, @ByVal GpuMat pano);
    public native @Cast("cv::Stitcher::Status") int composePanorama(@ByVal UMatVector images, @ByVal GpuMat pano);
    public native @Cast("cv::Stitcher::Status") int composePanorama(@ByVal GpuMatVector images, @ByVal GpuMat pano);

    /** \overload */
    public native @Cast("cv::Stitcher::Status") int stitch(@ByVal MatVector images, @ByVal Mat pano);
    public native @Cast("cv::Stitcher::Status") int stitch(@ByVal UMatVector images, @ByVal Mat pano);
    public native @Cast("cv::Stitcher::Status") int stitch(@ByVal GpuMatVector images, @ByVal Mat pano);
    public native @Cast("cv::Stitcher::Status") int stitch(@ByVal MatVector images, @ByVal UMat pano);
    public native @Cast("cv::Stitcher::Status") int stitch(@ByVal UMatVector images, @ByVal UMat pano);
    public native @Cast("cv::Stitcher::Status") int stitch(@ByVal GpuMatVector images, @ByVal UMat pano);
    public native @Cast("cv::Stitcher::Status") int stitch(@ByVal MatVector images, @ByVal GpuMat pano);
    public native @Cast("cv::Stitcher::Status") int stitch(@ByVal UMatVector images, @ByVal GpuMat pano);
    public native @Cast("cv::Stitcher::Status") int stitch(@ByVal GpuMatVector images, @ByVal GpuMat pano);
    /** \brief These functions try to stitch the given images.
    <p>
    @param images Input images.
    @param masks Masks for each input image specifying where to look for keypoints (optional).
    @param pano Final pano.
    @return Status code.
     */
    public native @Cast("cv::Stitcher::Status") int stitch(@ByVal MatVector images, @ByVal MatVector masks, @ByVal Mat pano);
    public native @Cast("cv::Stitcher::Status") int stitch(@ByVal UMatVector images, @ByVal UMatVector masks, @ByVal Mat pano);
    public native @Cast("cv::Stitcher::Status") int stitch(@ByVal GpuMatVector images, @ByVal GpuMatVector masks, @ByVal Mat pano);
    public native @Cast("cv::Stitcher::Status") int stitch(@ByVal MatVector images, @ByVal MatVector masks, @ByVal UMat pano);
    public native @Cast("cv::Stitcher::Status") int stitch(@ByVal UMatVector images, @ByVal UMatVector masks, @ByVal UMat pano);
    public native @Cast("cv::Stitcher::Status") int stitch(@ByVal GpuMatVector images, @ByVal GpuMatVector masks, @ByVal UMat pano);
    public native @Cast("cv::Stitcher::Status") int stitch(@ByVal MatVector images, @ByVal MatVector masks, @ByVal GpuMat pano);
    public native @Cast("cv::Stitcher::Status") int stitch(@ByVal UMatVector images, @ByVal UMatVector masks, @ByVal GpuMat pano);
    public native @Cast("cv::Stitcher::Status") int stitch(@ByVal GpuMatVector images, @ByVal GpuMatVector masks, @ByVal GpuMat pano);

    public native @StdVector IntPointer component();
    public native @StdVector CameraParams cameras();
    public native double workScale();
    public native @ByVal UMat resultMask();
}